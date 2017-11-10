package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Offer;
import domain.Reserva;

import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class ManageCreatedOffersGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
	private JPanel contentPane;
	private JList<Offer> list;
	private Vector<Offer> ress = facade.getOffersOfUser(facade.getCurrentUser());
	private JButton btnDelateOffer;
	private JScrollPane scrollPane_1;
	private JLabel labelFondo;
	/**
	 *
	 * Launch the application.
	 */


	/**
	 * Create the frame.
	 */
	public ManageCreatedOffersGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel lblCreatedOfferts = new JLabel("Created Offerts");
		lblCreatedOfferts.setForeground(Color.WHITE);
		lblCreatedOfferts.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCreatedOfferts.setBounds(149, 31, 139, 14);
		contentPane.add(lblCreatedOfferts);
		DefaultListModel<Offer> modelo = new DefaultListModel<Offer>();
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setViewportView(list);

		list = new JList<Offer>();
		list.setBounds(52, 185, 351, 31);
	//	contentPane.add(list);
		
		list.setModel(modelo);
		
		modelo.removeAllElements();
		for (Offer off : ress) {
			modelo.addElement(off);
			
			
		}
		
		btnDelateOffer = new JButton("Delate!");
		btnDelateOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Offer o = list.getSelectedValue();
				int p = facade.delateCreatedOffer(o, facade.getCurrentUser());
				System.out.println(o);
				if(p==1){
					JOptionPane.showMessageDialog(getContentPane(), "Oferta eliminada correctamente");
					facade.getCurrentUser().getUserOff();
				}else{
					JOptionPane.showMessageDialog(getContentPane(), "Imposible eliminar la oferta. Está reservada!");
				}
						
			}
		});
		
		scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(23, 71, 401, 130);
		contentPane.add(scrollPane_1);
		scrollPane_1.setViewportView(list);
		
		
		btnDelateOffer.setBounds(163, 219, 125, 31);
		contentPane.add(btnDelateOffer);
		
		labelFondo = new JLabel("");
		labelFondo.setBounds(0, 0, 434, 261);
		contentPane.add(labelFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(labelFondo.getWidth(), labelFondo.getHeight(), Image.SCALE_DEFAULT));
		labelFondo.setIcon(icono);
	}
}
