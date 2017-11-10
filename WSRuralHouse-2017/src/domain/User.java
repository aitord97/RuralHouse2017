package domain;
import java.io.*;
import java.util.Vector;

import javax.jdo.annotations.PrimaryKey;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;

import twitter4j.TwitterProfile;

@Entity
public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	private String userAcc;
	private String userPass;
	private String userType;
	private String userName;
	private String userSurname;
	private float monedero;
	private int userPhone;
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
	private Vector<RuralHouse> RHVector;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
	private Vector<Reserva> bookVector;
  @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Vector<Offer> createdOffersVector;
@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private TwitterProfile tp;
	
	public User(String uA, String uP, String uT){
	this.userAcc = uA;
	this.userPass = uP;
	this.userType = uT;
	this.userName="";
	this.userSurname="";
	this.userPhone=0;
	this.RHVector=new Vector<RuralHouse>();
	this.bookVector=new Vector<Reserva>();
	
	this.createdOffersVector=new Vector<Offer>();
	this.tp = new TwitterProfile(this);
	this.monedero =0;
	}
	
	public void setUserAcc(String u){
		this.userAcc = u;
	}
	public String getUserAcc(){
		return this.userAcc;	
	}
	public void setUserPass(String u){
		this.userPass = u;
	}
	public String getUserPass(){
		return this.userPass;
	}
	public void setUserType(String u){
		this.userType = u;
	}
	public String getUserType(){
		return this.userType;
	}
	public void setUserRH(RuralHouse rh){
		this.RHVector.addElement(rh);
	}
	public Vector<RuralHouse> getUserRH(){
		return this.RHVector;
	}
	public Vector<Offer> getUserOff(){
		return this.createdOffersVector;
	}
	public void setUserOff(Offer o){
		this.createdOffersVector.addElement(o);
	}
	public void removeOffer(int o){
		this.createdOffersVector.remove(o);
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserSurname() {
		return userSurname;
	}
	public void setUserSurname(String userSurname) {
		this.userSurname = userSurname;
	}
	public int getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(int userPhone) {
		this.userPhone = userPhone;
	}

	public TwitterProfile getTwitterProfile() {
		return tp;
	}

	public void setTwitterProfile(TwitterProfile tp) {
		this.tp = tp;
	}

	public void setUserBook(Reserva r) {
	this.bookVector.addElement(r);
		
	}
	public Vector<Reserva> getUserBookVector(){
		return this.bookVector;
	}
	public void setMonedero(float aux){
		this.monedero=aux;
	}
	public float getMonedero(){
		return monedero;
	}

	public void removeBook(Reserva reser) {
		for(int i = 0; i<this.getUserBookVector().size();i++){
			if(this.getUserBookVector().get(i).toString().compareTo(reser.toString())==0){
				this.getUserBookVector().remove(i);
			}
		}
		
	}
	
}