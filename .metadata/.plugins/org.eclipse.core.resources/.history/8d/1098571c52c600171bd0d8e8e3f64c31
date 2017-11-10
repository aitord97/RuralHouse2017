package businessLogic;

import java.io.File;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;

//import domain.Booking;
import domain.Offer;
import domain.Reserva;
import domain.RuralHouse;
import domain.User;
import exceptions.BadDates;
import exceptions.OverlappingOfferExists;

//Service Implementation
@WebService(endpointInterface = "businessLogic.ApplicationFacadeInterfaceWS")
public class FacadeImplementationWS  implements ApplicationFacadeInterfaceWS {
public User currentUser;	

	/**
	 * 
	 */

	public FacadeImplementationWS()  {
		ConfigXML c=ConfigXML.getInstance();
		if (c.getDataBaseOpenMode().equals("initialize")) {
			DataAccess dbManager=new DataAccess();
			dbManager.initializeDB();
			dbManager.close();
		}
	}
	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return the created offer, or null, or an exception
	 */
	public Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws OverlappingOfferExists, BadDates {
		System.out.println(">> FacadeImplementationWS: createOffer=> owner="+currentUser+"ruralHouse= "+ruralHouse+" firstDay= "+firstDay+" lastDay="+lastDay+" price="+price);
		
		DataAccess dbManager=new DataAccess();
		Offer o=null;
		if (firstDay.compareTo(lastDay)>=0) { dbManager.close(); throw new BadDates();}
		
		boolean b = dbManager.existsOverlappingOffer(ruralHouse,firstDay,lastDay); 
		if (!b) o=dbManager.createOffer(currentUser,ruralHouse,firstDay,lastDay,price);		

		dbManager.close();
		System.out.println("<< FacadeImplementationWS: createOffer=> O= "+o);
		return o;
	}
	public void createRuralHouse(String description, String city,
			int numBanos,int numDormitorios,int numCocinas, int numComedores,int numPlazasGaraje, Vector<String> fl,
			String img){
		DataAccess dbManager=new DataAccess();
		dbManager.createRuralHouse(getCurrentUser(),description,city,numBanos,numDormitorios,numCocinas,numComedores,numPlazasGaraje,fl,
				img);
	}
	/*
	public void bookOffer(Offer o){
		DataAccess dbManager=new DataAccess();
		dbManager.bookOffer(o);
	}
 */
	public void addCreatedOffer(User u, Offer o ){
		DataAccess dbManager=new DataAccess();
		dbManager.addCreatedOffer(getCurrentUser(),o);
	}
	public Vector<Offer> getCreatedOfferVector(User u){
		Vector<Offer>  CreatedOfferVector=getCurrentUser().getUserOff();
		return CreatedOfferVector;		
	}
	public void setOfferStatus (Offer o, boolean b){
		DataAccess dbManager=new DataAccess();
		dbManager.changeOffer(o,b);	
	}
	public boolean getOfferStatus (Offer o){
		DataAccess dbManager=new DataAccess();
		boolean r=dbManager.getOfferStatus(o);
		return r;	
	}
	public void setPassByAcc (User u, String pass){
		DataAccess dbManager=new DataAccess();
		dbManager.setPassByAcc(u, pass);
	}
	public Vector<RuralHouse> getAllRuralHouses()  {
		System.out.println(">> FacadeImplementationWS: getAllRuralHouses");
		DataAccess dbManager=new DataAccess();
		Vector<RuralHouse>  ruralHouses=dbManager.getAllRuralHouses();
		dbManager.close();
		System.out.println("<< FacadeImplementationWS:: getAllRuralHouses");
		return ruralHouses;
	}
	public Vector<Offer> getOffersOfUser (User u){
		System.out.println(">> FacadeImplementationWS: getOffersOfUser");
		Vector<Offer>  offerVector=getCurrentUser().getUserOff();
		System.out.println("<< FacadeImplementationWS:: getOffersOfUser");
		return offerVector;
	}
	public Vector<RuralHouse> getRHOfUser(User u){
		System.out.println(">> FacadeImplementationWS: getRHOfUser");
		Vector<RuralHouse>  RHVector=getCurrentUser().getUserRH();
		System.out.println("<< FacadeImplementationWS:: getOffersOfUser");
		return RHVector;
	}
	public void setOfferOfUser (User u, Offer o){
		System.out.println(">> FacadeImplementationWS: setOffersOfUser");
		DataAccess dbManager=new DataAccess();
		dbManager.setOfferOfUser(getCurrentUser(),o );
		System.out.println("<< FacadeImplementationWS:: setOffersOfUser");
	}
	public void setRHOfUser(User u, RuralHouse rh){
		System.out.println(">> FacadeImplementationWS: getRHOfUser");
		DataAccess dbManager=new DataAccess();
		dbManager.setRHOfUser(getCurrentUser(),rh);
		System.out.println("<< FacadeImplementationWS:: getOffersOfUser");
	}
	public List<String> getFiltersOfRH(RuralHouse rh){
		return rh.getFilterList();
	}
	/**
	 * This method obtains the  offers of a ruralHouse in the provided dates 
	 * 
	 * @param ruralHouse, the ruralHouse to inspect 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there is no overlapping offer
	 */
	@WebMethod 
	public Vector<Offer> getOffers( RuralHouse rh, Date firstDay,  Date lastDay) {
		DataAccess dbManager=new DataAccess();
		Vector<Offer>  offers=new Vector<Offer>();
		offers=dbManager.getOffers(rh,firstDay,lastDay);
		dbManager.close();
		return offers;
	}	
	public void close() {
		DataAccess dbManager=new DataAccess();
		dbManager.close();
	}
	public void initializeBD(){
		DataAccess dbManager=new DataAccess();
		dbManager.initializeDB();
		dbManager.close();
	}
	public void setCurrentUser(String u){
		DataAccess dbManager=new DataAccess();
		currentUser= dbManager.getUserByAcc(u);
	}
	public User getCurrentUser (){
		DataAccess dbManager=new DataAccess();
		
		currentUser=dbManager.getUserByAcc(currentUser.getUserAcc());
		return currentUser;
	}
	public boolean checkLogin(String u, String p){
		DataAccess dbManager=new DataAccess();
		User us = new User(u,p,null);
		if (p.compareTo(dbManager.getPassByAcc(us))==0){
		return true;}
		else{ return false;}
	}
	public void strAccount(String uc, String up, String ut){
		DataAccess dbManager=new DataAccess();
		User u = new User(uc,up,ut);
		dbManager.storeAccount(u);
	}
	public void updateOff(Offer o,Date firstDay,Date lastDay,float price){
		DataAccess dbManager = new DataAccess();
		dbManager.updateOfferById(o, firstDay, lastDay, price);
	}
	public Vector<Offer> getAllOffersOfRH(RuralHouse rh){
		DataAccess dbManager = new DataAccess();
		Vector<Offer> v = dbManager.getAllOffersOfHouse(rh);
		return v;
	}
	public String getUserTypeByAcc(String ac){
		DataAccess dbManager = new DataAccess();
		String type = dbManager.getUserType(ac);
		return type;
	}
	public void cancelOffer(User u,Reserva reser){
		DataAccess dbManager = new DataAccess();
		dbManager.removeBook(reser,u);
		dbManager.changeOffer(reser.getBook(), false);;
	}
	

	public String getUserName(User u) {
		return u.getUserName();
	}
	public void setUserName(User u, String userName) {
		DataAccess dbManager = new DataAccess();
		dbManager.setUserName(u, userName);
		
	}
	
	
	public String getUserSurname(User u) {
		return u.getUserSurname();
	}
	public void setUserSurname(User u, String userSurname) {
		DataAccess dbManager = new DataAccess();
		dbManager.setUserSurname(u, userSurname);
	}
	
	
	public int getUserPhone(User u) {
		return u.getUserPhone();
	}
	public void setUserPhone(User u,int userPhone) {
		DataAccess dbManager = new DataAccess();
		dbManager.setUserPhone(u, userPhone);
		
	}
	public void updateTwitterProfile(User u,String token, String tokenSecret, String twUser){
		DataAccess dbManager = new DataAccess();
		dbManager.updateTwitterProfile(u, token, tokenSecret, twUser);
	}
	public void setReservaToUser(Reserva r){
		DataAccess dbManager=new DataAccess();
		dbManager.setReservaToUser(r,getCurrentUser());
	}
	public Vector<Reserva> getReservasOfUser(User u){
		DataAccess dbManager = new DataAccess();
		return dbManager.getReservasOfUser(u);
	}
	public void setMonedero(User u, float monedero){
		DataAccess dbManager = new DataAccess();
		dbManager.setMonedero(u,monedero);
		
	}
	public float getMonedero(){
		return currentUser.getMonedero();
		
	}
	public Vector<Reserva> getReservasNoPagadas(User u){
		Vector<Reserva> r1 = getReservasOfUser(u);
		Vector<Reserva> r2 = new Vector<Reserva>();
		for(Reserva r:r1){if(!r.isPaid())r2.add(r);}
		return r2;
	}
	public Vector<Reserva> getReservasPagadas(User u){
		Vector<Reserva> r1 = getReservasOfUser(u);
		Vector<Reserva> r2 = new Vector<Reserva>();
		for(Reserva r:r1){if(r.isPaid())r2.add(r);}
		return r2;
	}

	public void pagarReserva(Reserva rs, float dinero, User u){
		/*
		 * Llama a la funcion pagar del dataAcces
		 * 
		 */
		DataAccess dbManager = new DataAccess();
		dbManager.pagarReserva(rs,dinero,u);
		/*
		 * Añadir aquí funcion restar dinero del monedero
		 */
	}
	public int delateCreatedOffer (Offer o, User u){
		DataAccess dbManager = new DataAccess();
		int p = dbManager.delateCreatedOffer(o,getCurrentUser());
		return p;
		
	}
	public void removeUserAccount(User u){
		DataAccess dbManager = new DataAccess();
		dbManager.removeUserAccount(u);
	}


}

