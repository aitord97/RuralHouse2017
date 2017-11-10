package businessLogic;

import java.util.Iterator;

import domain.RuralHouse;

public interface ExtendedIterator extends Iterator<RuralHouse> {
	//devuelve el elemento actual y pasa al anterior
	public Object previous();
	//true si existe el elemento anterior
	public boolean hasPrevious();
	//Se posiciona en el primer elemento
	public void goFirst();
	//Se posiciona en el último elemento
	public void goLast();

}
