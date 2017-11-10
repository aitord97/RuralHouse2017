package domain;
import java.io.*;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlIDREF;

@XmlAccessorType(XmlAccessType.FIELD)
@Entity
public class Reserva {
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Id
	private int bookId;
	private Date bookedDate;
	private Offer book;
	private boolean paid;
	private String paidMethod;
	private Date paidDate;
	private Date cancelDate;
	
	public Reserva(Date bookdate, Offer book){
		this.bookedDate = bookdate;
		this.book = book;
		this.paid = false;
		this.paidMethod="";
		this.paidDate = null;
		this.cancelDate = null;
	}

	public Date getBookedDate() {
		return bookedDate;
	}

	public void setBookedDate(Date bookedDate) {
		this.bookedDate = bookedDate;
	}

	public Offer getBook() {
		return book;
	}

	public void setBook(Offer book) {
		this.book = book;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

	public String getPaidMethod() {
		return paidMethod;
	}

	public void setPaidMethod(String paidMethod) {
		this.paidMethod = paidMethod;
	}

	public Date getCancelDate() {
		return cancelDate;
	}

	public void setCancelDate(Date cancelDate) {
		this.cancelDate = cancelDate;
	}
	public String toString(){
		return bookedDate.toString()+";"+book.toString();
	}
	public String toStringHist(){
		return paidDate.toString()+"; "+book.toString();
	}

	public Date getPaidDate() {
		return paidDate;
	}

	public void setPaidDate(Date paidDate) {
		this.paidDate = paidDate;
	}
	public int getBookId(){
		return this.bookId;
	}
	
	

}
