package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.Vector;

import domain.Offer;
import domain.Reserva;
import domain.RuralHouse;
import domain.User;
import exceptions.BadDates;
import exceptions.OverlappingOfferExists;
import gui.MainGUI;

public class pruebaIterator {
	public static void main(String[] args) {
		 
		 //obtener el objeto fachada
		 ApplicationFacadeInterfaceWS facade= new FacadeImplementationWS();
		 ExtendedIterator i=facade.ruralHouseIterator();
		 RuralHouse rh;
		 i.goLast();
		 while (i.hasPrevious()){
		 rh=(RuralHouse) i.previous();
		 System.out.println(rh.toString());
		 }
		 //Aunque suponemos que hemos llegado al principio, realizamos la operación
		 i.goFirst();
		 while (i.hasNext()){
		 rh=i.next();
		 System.out.println(rh.toString());
		 }
	}
}
