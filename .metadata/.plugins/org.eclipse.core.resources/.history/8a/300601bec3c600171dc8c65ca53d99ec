package domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.persistence.*;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class RuralHouse implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@XmlID
	@XmlJavaTypeAdapter(IntegerAdapter.class)
	@Id
	@GeneratedValue
	private Integer houseNumber;
	private String description;
	private String city; 
	private int numBanos; //>=2
	private int numDormitorios;//>=3
	private int numCocinas;//>=1
	private int numComedores;
	private int numPlazasGaraje;
	@OneToMany(fetch=FetchType.EAGER)
	private Vector<Offer> offers;
    private User rhOwner;
    @OneToMany(fetch = FetchType.EAGER)
    private Vector<String> filterList;
    private String imgPath;
    
    
	
	public RuralHouse( User owner, String description, String city,
			int numBanos,int numDormitorios,int numCocinas, int numComedores,int numPlazasGaraje, Vector<String> fl,
			String img) {
		this.rhOwner=owner;
		this.description = description;
		this.city = city;
		this.numBanos=numBanos;
		this.numCocinas=numCocinas;
		this.numDormitorios=numDormitorios;
		this.numComedores=numComedores;
		this.numPlazasGaraje=numPlazasGaraje;
		this.filterList=fl;
		this.imgPath = img;
		offers=new Vector<Offer>();
	}
	public Integer getHouseNumber() {
		return houseNumber;
	}
	public void setHouseNumber(Integer houseNumber) {
		this.houseNumber = houseNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description=description;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city=city;
	}
	public int numBanos() {
		return numBanos;
	}
	public void setNumBanos(int numBanos) {
		this.numBanos=numBanos;
	}
	public void setRhOwner(User owner){
		this.rhOwner=owner;
	}
	public User getRHOwner(){
		return this.rhOwner;
	}
	public void setFilter(String fl){
		this.filterList.add(fl);
	}
	public Vector<String> getFilterList(){
		return this.filterList;
	}
	
	/**
	 * This method creates an offer with a house number, first day, last day and price
	 * 
	 * @param House
	 *            number, start day, last day and price
	 * @return None
	 */
	public Offer createOffer(User propietario,Date firstDay, Date lastDay, float price)  {
        System.out.println("LLAMADA RuralHouse createOffer, offerNumber="+" firstDay="+firstDay+" lastDay="+lastDay+" price="+price);
        Offer off=new Offer(propietario,firstDay,lastDay,price,this);
        offers.add(off);
        return off;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + houseNumber.hashCode();
		return result;
	}
	@Override
	public String toString() {
		return this.houseNumber + ": " + this.city + " "+this.description;
	}
	@Override
	public boolean equals(Object obj) {
		RuralHouse other = (RuralHouse) obj;
		if (this == obj)
		  return true;
		if (obj == null)
		  return false;
		if (getClass() != obj.getClass())
		  return false;
//		if (houseNumber != other.houseNumber) // NO COMPARAR ASÍ ya que houseNumber NO ES "int" sino objeto de "java.lang.Integer"
		if (!houseNumber.equals(other.houseNumber))
		  return false;
   	    return true;
	}
	/**
	 * This method obtains available offers for a concrete house in a certain period 
	 * 
	 * @param houseNumber, the house number where the offers must be obtained 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return a vector of offers(Offer class)  available  in this period
	 */
	public Vector<Offer> getOffers( Date firstDay,  Date lastDay) {
		Vector<Offer> availableOffers=new Vector<Offer>();
		Iterator<Offer> e=offers.iterator();
		Offer offer;
		while (e.hasNext()){
			offer=e.next();
			if ( (offer.getFirstDay().compareTo(firstDay)>=0) && (offer.getLastDay().compareTo(lastDay)<=0)  )
				availableOffers.add(offer);
		}
		return availableOffers;
	}
	public Vector<Offer> getAllOffers() {
		Vector<Offer> res=new Vector<>();
		for (Offer o: offers){res.addElement(o);}
		return res;
	}
	/**
	 * This method obtains the first offer that overlaps with the provided dates
	 * 
	 * @param firstDay, first day in a period range 
	 * @param lastDay, last day in a period range
	 * @return the first offer that overlaps with those dates, or null if there is no overlapping offer
	 */
	public Offer overlapsWith( Date firstDay,  Date lastDay) {
		Iterator<Offer> e=offers.iterator();
		Offer offer=null;
		while (e.hasNext()){
			offer=e.next();
			if ( (offer.getFirstDay().compareTo(lastDay)<0) && (offer.getLastDay().compareTo(firstDay)>0))
				return offer;
		}
		return null;
	}
	public int getNumDormitorios() {
		return numDormitorios;
	}
	public void setNumDormitorios(int numDormitorios) {
		this.numDormitorios = numDormitorios;
	}
	public int getNumCocinas() {
		return numCocinas;
	}
	public void setNumCocinas(int numCocinas) {
		this.numCocinas = numCocinas;
	}
	public int getNumComedores() {
		return numComedores;
	}
	public void setNumComedores(int numComedores) {
		this.numComedores = numComedores;
	}
	public int getNumPlazasGaraje() {
		return numPlazasGaraje;
	}
	public void setNumPlazasGaraje(int numPlazasGaraje) {
		this.numPlazasGaraje = numPlazasGaraje;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public void removeOffer(Offer o){
		for (int i = 0; i<this.offers.size();i++){
			if(this.offers.get(i).toString().compareTo(o.toString())==0){
				this.offers.remove(i);
			}
		}
	}

}
