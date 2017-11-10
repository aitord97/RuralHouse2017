package dataAccess;


import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.swing.JOptionPane;

import configuration.ConfigXML;
//import domain.Booking;
import domain.Offer;
import domain.Reserva;
import domain.RuralHouse;
import domain.User;
import exceptions.OverlappingOfferExists;

public class DataAccess  {

	public static String fileName;
	protected static EntityManagerFactory emf;
	protected static EntityManager  db;
	

	ConfigXML c;

	public DataAccess()  {
		
		c=ConfigXML.getInstance();

		System.out.println("Creating objectdb instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String filename=c.getDbFilename();
		
		if (c.isDatabaseLocal()) {

			emf = Persistence.createEntityManagerFactory(c.getDbFilename());
			db = emf.createEntityManager();			
		} else {		
				  Map<String, String> properties = new HashMap<String, String>();
				  properties.put("javax.persistence.jdbc.user", c.getUser());
				  properties.put("javax.persistence.jdbc.password", c.getPassword());
				  emf = Persistence.createEntityManagerFactory(
					      "objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+c.getDbFilename(), properties);
				  db = emf.createEntityManager();				
		}
		}

	
	public void initializeDB(){
		
		/*db.getTransaction().begin();
		try{			
			
			TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c", RuralHouse.class);
			List<RuralHouse> results = query.getResultList();

			Iterator<RuralHouse> itr = results.iterator();

			while (itr.hasNext()){
				RuralHouse rh=itr.next();
				db.remove(rh);				
			}
			
			//RuralHouse rh1=new RuralHouse("Ezkioko etxea","Ezkio");
			//RuralHouse rh2=new RuralHouse("Etxetxikia","Iruna");
			//RuralHouse rh3=new RuralHouse("Udaletxea","Bilbo");
			//RuralHouse rh4=new RuralHouse("Gaztetxea","Renteria");
			//User u1 = new User ("aitor","aaa","Admin");
			//db.persist(rh1);
			//db.persist(rh2);
			//db.persist(rh3);
			//db.persist(rh4);
			//db.persist(u1);
			db.getTransaction().commit();
			System.out.println("Db initialized");

		}
		catch (Exception e){
			e.printStackTrace();
		}*/
	}
	
	public Offer createOffer(User u,RuralHouse ruralHouse, Date firstDay, Date lastDay, float price) {
		System.out.println(">> DataAccess: createOffer=> ruralHouse= "+ruralHouse+" firstDay= "+firstDay+" lastDay="+lastDay+" price="+price);
		try {	
			RuralHouse rh = db.find(RuralHouse.class, ruralHouse.getHouseNumber());
			User us = db.find(User.class, u.getUserAcc());
			db.getTransaction().begin();
			Offer o = rh.createOffer(u,firstDay, lastDay, price);
			db.persist(o);
			us.setUserOff(o);
			db.getTransaction().commit();
			return o;
		}
		catch (Exception e){
			System.out.println("Offer not created: "+e.toString());
			return null;
		}
	}
	public void createRuralHouse( User u, String description, String city,
			int numBanos,int numDormitorios,int numCocinas, int numComedores,int numPlazasGaraje, Vector<String> fl,
			String img){
		db.getTransaction().begin();
		User user=db.find(User.class, u.getUserAcc());
		RuralHouse rh = new RuralHouse(user,description,city,numBanos,numDormitorios,numCocinas,numComedores,numPlazasGaraje,fl,
				img);
		user.setUserRH(rh);
		db.persist(rh);
		db.persist(user);
		db.getTransaction().commit();
	}
	public void updateOfferById(Offer o,Date firstDay,Date lastDay,float price){
		Offer off = db.find(Offer.class, o.getOfferNumber());
		db.getTransaction().begin();
		off.setFirstDay(firstDay);
		off.setLastDay(lastDay);
		off.setPrice(price);
		db.getTransaction().commit();
	}
	/*public void bookOffer(User u,Offer o){
		Offer off = db.find(Offer.class, o.getOfferNumber());
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM Offer o where o.offerNumber = ?1",Offer.class);
		query.setParameter(1, off.getOfferNumber());		int deletedPilots = query.executeUpdate();
		System.out.println("Pilotos borrados: " + deletedPilots);
		db.getTransaction().commit();
	}*/

	public boolean existsOverlappingOffer(RuralHouse rh,Date firstDay, Date lastDay) throws  OverlappingOfferExists{
		try{
			RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
			if (rhn.overlapsWith(firstDay,lastDay)!=null) return true;
		} catch (Exception e){
				System.out.println("Error: "+e.toString());
				return true;
		}
	return false;
	}
	public Vector<RuralHouse> getAllRuralHouses() {
		System.out.println(">> DataAccess: getAllRuralHouses");
		Vector<RuralHouse> res = new Vector<>();
		TypedQuery<RuralHouse> query = db.createQuery("SELECT c FROM RuralHouse c", RuralHouse.class);
		List<RuralHouse> results = query.getResultList();
		Iterator<RuralHouse> itr = results.iterator();
		while (itr.hasNext()){
			res.add(itr.next());
		}
		return res;
	}
	public void addCreatedOffer(User u , Offer o){
		User up=db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		up.setUserOff(o);
		db.getTransaction().commit();
	}
	public void setOfferOfUser (User u, Offer o){
		User up=db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		up.setUserOff(o);
		db.getTransaction().commit();
	}
	public void setRHOfUser (User u, RuralHouse rh){
		User up=db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		up.setUserRH(rh);
		db.getTransaction().commit();
	}
	 public Vector<Offer> getOffers( RuralHouse rh, Date firstDay,  Date lastDay) {
			System.out.println(">> DataAccess: getOffers");
			Vector<Offer> res=new Vector<>();
			RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
			res = rhn.getOffers(firstDay,lastDay);
			return res;
	 }
	 public Vector<Offer> getAllOffersOfHouse(RuralHouse rh){
		 Vector<Offer> res = new Vector<>();
		 RuralHouse rhn = db.find(RuralHouse.class, rh.getHouseNumber());
		 res = rhn.getAllOffers();
		 return res;
	 }
	public void storeAccount(User u){
		db.getTransaction().begin();
		db.persist(u);
		db.getTransaction().commit();
	}
	public void changeOffer (Offer o, boolean b){
		Offer off = db.find(Offer.class, o.getOfferNumber());
		db.getTransaction().begin();
		off.setOfferStatus(b);
		db.getTransaction().commit();
	}
	public String getPassByAcc(User u){
		User up=db.find(User.class, u.getUserAcc());
		return up.getUserPass();
	}
	
	public void setPassByAcc(User u, String pass){
		
		User up=db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		up.setUserPass(pass);
		db.getTransaction().commit();
	
	}
	public boolean getOfferStatus (Offer o){
		Offer off = db.find(Offer.class, o.getOfferNumber());
		return off.getOfferStatus();
	}
	public String getUserType(String ac){	
		User u = db.find(User.class,ac);
		return u.getUserType();
	}
	public User getUserByAcc(String ac){
		User u = db.find(User.class, ac);
		return u;
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	public void cancelOffer(User u,Offer o){
		changeOffer(o, false);
		User us = db.find(User.class, u.getUserAcc());
		Vector<Offer> vo = us.getUserOff();
		db.getTransaction().begin();
		for(int i=0;i<vo.size();i++){if (vo.get(i).toString().compareTo(o.toString())==0){us.removeOffer(i);}}
		db.persist(us);
		db.getTransaction().commit();
	}
	public void setUserName(User u, String userName) {
		User up=db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		up.setUserName(userName);
		db.getTransaction().commit();
	}
	public void setUserSurname(User u, String userSurname) {
		User up=db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		up.setUserSurname(userSurname);
		db.getTransaction().commit();
	}
	public void setUserPhone(User u,int userPhone) {
		User up=db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		up.setUserPhone(userPhone);
		db.getTransaction().commit();
	}
	public void updateTwitterProfile(User u,String token, String tokenSecret, String twUser){
		User us = db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		us.getTwitterProfile().setAccessToken(token);
		us.getTwitterProfile().setAccessTokenSecret(tokenSecret);
		us.getTwitterProfile().setTwUserName(twUser);
		db.persist(us.getTwitterProfile());
		db.getTransaction().commit();
		
	}


	public void setReservaToUser(Reserva r, User u) {
	
		User up=db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		up.setUserBook(r);
		db.getTransaction().commit();
	}
	
	public void setMonedero(User u , float monedero){
		User us = db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		us.setMonedero(monedero);
		db.getTransaction().commit();
		
	}


	public Vector<Reserva> getReservasOfUser(User u) {
	User us = db.find(User.class, u.getUserAcc());
	return 	us.getUserBookVector();
		
	}


	public void pagarReserva(Reserva rs, float dinero,User u) {
	Reserva rss = db.find(Reserva.class, rs.getBookId());
	User us = db.find(User.class, u.getUserAcc());
	db.getTransaction().begin();
	rss.setPaid(true);
	rss.setPaidDate(new Date());
	float aux= us.getMonedero();
	aux= aux - dinero;
	us.setMonedero(aux);
	//db.persist(us);
	db.getTransaction().commit();
		
	}
// Si devuelve un 1 la funcion es que ha sido eliminada corrcatamente.
	//Si por e contrario devuelve un 0 signifia que la oferta estaba reservada o no existia

	public int delateCreatedOffer(Offer o, User currentUser) {
		User us = db.find(User.class, currentUser.getUserAcc());
		int p = 0;
		Vector<Offer> vo = us.getUserOff();
		System.out.println("/"+us.getUserOff());
		//db.getTransaction().begin();
		for(int i=0;i<vo.size();i++){
			//Busca la oferta en el vector de ofertas creadas por el usuario
			if (vo.get(i).toString().compareTo(o.toString())==0){
				//Compruba s i al oferta esta reservada
				if(vo.get(i).getOfferStatus()==false){
					db.getTransaction().begin();
					Query query = db.createQuery("SELECT FROM RuralHouse p WHERE p.offers.offerNumber = "+o.getOfferNumber());
					RuralHouse rh = (RuralHouse)query.getResultList().get(0);
					rh.removeOffer(o);
					db.getTransaction().commit();
					us.removeOffer(i);
					eliminarOferta(o);
					p=1;
				
				//Offer offer = db.find(Offer.class, vo.get(i).getOfferNumber());
				//System.out.println("--->"+offer);
				//System.out.println("+"+us.getUserOff());
				}else{
				
					p=0;
				}
					
				}
				
				//Query query = db.createQuery("DELETE FROM Offer p WHERE p.offerNumber = "+offer.getOfferNumber());
				//int deletedPilots = query.executeUpdate();
				//System.out.println(deletedPilots);
				//System.out.println("-"+us.getUserOff());
				//db.remove(offer);
				//db.persist(us);

				//p=1;
				//return p;
				//}else{
					//p=0;
					//return p;
				}
			//}
		//}
		
		//db.persist(us);
		//db.getTransaction().commit();
		
		return p;
	}
	public void eliminarOferta(Offer o){
		Offer of = db.find(Offer.class, o.getOfferNumber());
		db.getTransaction().begin();
		db.remove(of);
		db.getTransaction().commit();
	}


	public void removeUserAccount(User u) {
		User us = db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		db.remove(us);
		db.getTransaction().commit();
		
	}


	public void removeBook(Reserva reser, User u) {
		
		Reserva r = db.find(Reserva.class,reser.getBookId());
		User us = db.find(User.class, u.getUserAcc());
		db.getTransaction().begin();
		us.removeBook(reser);
		db.remove(r);
		db.getTransaction().commit();
		
				
	}
	
}

