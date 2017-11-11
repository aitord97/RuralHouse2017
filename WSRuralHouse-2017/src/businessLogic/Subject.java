package businessLogic;
import domain.*;

public interface Subject {
	
	public void attach (IAlertRuralHouse observador);
	public void dettach (IAlertRuralHouse observador);
	public void notifyObservers();

}
