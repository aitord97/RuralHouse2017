package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Offer;
import domain.Reserva;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

public class CancelOfferGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<Reserva> comboBox_Offers;
	protected JLabel lblSelectAnOffer;
	private JButton btnCancelOffer;
	private DefaultComboBoxModel<Reserva> offerinf = new DefaultComboBoxModel<Reserva>();
	private JLabel lblFondo;


	/**
	 * Create the frame.
	 */
	public CancelOfferGUI(Vector<Reserva> v) {
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 414, 239);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblSelectAnOffer = new JLabel("Select book to cancel");
		lblSelectAnOffer.setForeground(Color.WHITE);
		lblSelectAnOffer.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSelectAnOffer.setBounds(76, 21, 217, 29);
		contentPane.add(lblSelectAnOffer);
		
		for(Reserva of: v){offerinf.addElement(of);
		System.out.println(of);}
		
		comboBox_Offers = new JComboBox();
		comboBox_Offers.setBounds(40, 61, 322, 68);
		contentPane.add(comboBox_Offers);
		comboBox_Offers.setModel(offerinf);
		
		btnCancelOffer = new JButton("Cancel Offer");
		btnCancelOffer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try{
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				facade.cancelOffer(facade.getCurrentUser(), (Reserva)offerinf.getSelectedItem());
				offerinf.removeAllElements();
				for(Reserva of: facade.getCurrentUser().getUserBookVector()){offerinf.addElement(of);}
				}catch(Exception e){}
			}
		});
		btnCancelOffer.setBounds(126, 143, 135, 34);
		contentPane.add(btnCancelOffer);
		
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 398, 200);
		contentPane.add(lblFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}
}
