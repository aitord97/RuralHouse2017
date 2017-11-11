package businessLogic;
import domain.*;
import java.util.ArrayList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class AlertRH implements Subject {
	@GeneratedValue
	@Id
	private int id;
	private static ArrayList<IAlertRuralHouse> observadores = new ArrayList<IAlertRuralHouse>();
	
	public void attach (IAlertRuralHouse observador) {
		observadores.add(observador);
	}
	public void dettach(IAlertRuralHouse observador) {
		observadores.remove(observador);
	}
	public void notifyObservers() {
		for (int i=0; i<observadores.size(); i++) {
			observadores.get(i).update();
		}
	}

}
