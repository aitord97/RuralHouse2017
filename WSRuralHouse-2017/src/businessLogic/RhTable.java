package businessLogic;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import domain.RuralHouse;
import domain.User;



@SuppressWarnings("serial")
public class RhTable extends AbstractTableModel {
	String[] columName = {"User", "Number", "Description", "city","numba�os","dormitorios","cocinas","comedores", "Plazasgaraje"};
	Vector<RuralHouse> rh;
	User u;
	
	public RhTable(User u, Vector<RuralHouse> rh ) {
		this.u=u;
		this.rh=rh;
	}
	@Override
	public int getColumnCount() {
		
		return 9;
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		return rh.size();
	}

	@Override
	public String getValueAt(int arg0, int arg1) {
		switch (arg1) {
		case 0:
			return rh.get(arg0).getRHOwner().toString();
		case 1:
			return rh.get(arg0).getHouseNumber().toString();
			
		case 2:
			return rh.get(arg0).getDescription();
			
		case 3:
			return rh.get(arg0).getCity();
		
		case 4:
			return String.valueOf(rh.get(arg0).numBanos());
		
		case 5:
			return String.valueOf(rh.get(arg0).getNumDormitorios());

			
		case 6:
			return String.valueOf(rh.get(arg0).getNumCocinas());

		case 7:
			return String.valueOf(rh.get(arg0).getNumComedores());

		case 8:
			return String.valueOf(rh.get(arg0).getNumPlazasGaraje());

		}
		return null;
	}
}
