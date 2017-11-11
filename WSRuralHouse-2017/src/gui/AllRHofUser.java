package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.ExtendedIterator;
import businessLogic.FacadeImplementationWS;
import businessLogic.RhTable;
import businessLogic.RuralHouseIterator;
import domain.RuralHouse;

import javax.swing.JTable;

public class AllRHofUser extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private ExtendedIterator rh1= new RuralHouseIterator();
	static ApplicationFacadeInterfaceWS facade= new FacadeImplementationWS();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		facade.setCurrentUser("owner");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AllRHofUser frame = new AllRHofUser();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AllRHofUser() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		
	
  		Vector<RuralHouse> vr = new Vector<>();
  		rh1= facade.ruralHouseIterator();
  		vr = facade.getRHOfUser(facade.getCurrentUser());
  		
  	
  		RhTable tt = new RhTable(facade.getCurrentUser(), vr);
  		
  		table = new JTable(tt);
		table.setBounds(5, 5, 434, 261);
		contentPane.add(table);
	}

}
