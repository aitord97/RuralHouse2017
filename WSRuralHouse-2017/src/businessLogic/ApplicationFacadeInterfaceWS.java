package businessLogic;

import java.util.List;
import java.util.Vector;
import java.util.Date;

















import dataAccess.DataAccess;
//import domain.Booking;
import domain.Offer;
import domain.Reserva;
import domain.RuralHouse;
import domain.User;
import exceptions.BadDates;
import exceptions.OverlappingOfferExists;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface ApplicationFacadeInterfaceWS  {
	

	
	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */

	@WebMethod Offer createOffer(RuralHouse ruralHouse, Date firstDay, Date lastDay,
			float price) throws  OverlappingOfferExists, BadDates;
	/**
	 * This method creates a book with a corresponding parameters
	 * 
	 * @param First
	 *            day, last day, house number and telephone
	 * @return a book
	 */
	
	/**
	 * This method retrieves the existing  rural houses 
	 * 
	 * @return a Set of rural houses
	 */
	@WebMethod public Vector<RuralHouse> getAllRuralHouses();
	
	/**
	 * This method obtains the  offers of a ruralHouse in the provided dates 
	 * 
	 * @param ruralHouse, the ruralHouse to inspect 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there is no overlapping offer
	 */

	@WebMethod public Vector<Offer> getOffers( RuralHouse rh, Date firstDay,  Date lastDay) ;
	@WebMethod public void initializeBD();
	@WebMethod public void createRuralHouse(String description, String city,
			int numBanos,int numDormitorios,int numCocinas, int numComedores,int numPlazasGaraje, 
			Vector<String> fl, String img);
	
	public boolean checkLogin(String u, String p);
	public void strAccount(String uc, String up, String ut);
	public void updateOff(Offer o,Date firstDay,Date lastDay,float price);
	public Vector<Offer> getAllOffersOfRH(RuralHouse rh);
	public String getUserTypeByAcc(String ac);
	public User getCurrentUser ();
	public void setCurrentUser(String u);
	//public void bookOffer(Offer o);
	public void setOfferStatus (Offer o, boolean b);
	public boolean getOfferStatus (Offer o);
	public Vector<Offer> getOffersOfUser (User u);
	public Vector<RuralHouse> getRHOfUser(User u);
	public void setOfferOfUser (User u, Offer o);
	public void addCreatedOffer(User u, Offer o );
	public Vector<Offer> getCreatedOfferVector(User u);
	public void setRHOfUser(User u, RuralHouse rh);	
	public List<String> getFiltersOfRH(RuralHouse rh);
	public void cancelOffer(User u,Reserva reserva);
	public void setPassByAcc (User u, String pass);
	
	public String getUserName(User u);
	public void setUserName(User u, String userName);
	public String getUserSurname(User u);
	public void setUserSurname(User u, String userSurname);
	public int getUserPhone(User u);
	public void setUserPhone(User u,int userPhone);
	public void updateTwitterProfile(User u,String token, String tokenSecret, String twUser);
	public void setReservaToUser(Reserva r);
	public void setMonedero(User u, float monedero);
	public float getMonedero();
	
	public Vector<Reserva> getReservasOfUser(User u);
	public Vector<Reserva> getReservasNoPagadas(User u);
	public Vector<Reserva> getReservasPagadas(User u);
	
	public void pagarReserva(Reserva rs, float f, User u);
	public int delateCreatedOffer (Offer o, User u);

	public void removeUserAccount(User u);


}