package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterfaceWS;
import dataAccess.DataAccess;
import domain.Offer;
import domain.RuralHouse;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Vector;
import java.awt.Font;
import java.awt.Color;

public class EditOfferGUI extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private JPanel contentPane;


	protected JLabel lblSelectOffer;
	protected JLabel lblSelectRH;
	private DefaultComboBoxModel<RuralHouse> rhInfo = new DefaultComboBoxModel<RuralHouse>();
	private DefaultComboBoxModel<Offer> offerInfo = new DefaultComboBoxModel<Offer>();
	private JButton btnEditOffer;
	private Offer SOffer;
	private JComboBox<Offer> comboBoxOffer;
	private JComboBox<RuralHouse> comboBoxRH;
	private JButton btnCancel;
	private JLabel lblFondo;

	


	/**
	 * Create the frame.
	 */
	public EditOfferGUI(Vector<RuralHouse> v) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 433, 301);
		this.getJContentPane();
		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
		v = facade.getCurrentUser().getUserRH();
		
		for(RuralHouse r: v){rhInfo.addElement(r);}
	}
	
	public JPanel getJContentPane(){
		if(contentPane == null){
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			contentPane.add(getSelectRHLabel());
			contentPane.add(getSelectOfferLabel());
			contentPane.add(getJComboBoxOffer());
			contentPane.add(getJComboBoxRH());
			contentPane.add(getEditOfferButton());
			contentPane.add(getBtnCancel());
			contentPane.add(getLblFondo());
			
			
		}
		return contentPane;
		}
	public JLabel getSelectOfferLabel(){
		if (lblSelectOffer == null){
			lblSelectOffer = new JLabel("Select Offer");
			lblSelectOffer.setForeground(Color.WHITE);
			lblSelectOffer.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblSelectOffer.setBounds(38, 105, 335, 31);
		}return lblSelectOffer;
	}
	public JLabel getSelectRHLabel(){
		if (lblSelectRH == null){
			lblSelectRH = new JLabel("Select Rural House");
			lblSelectRH.setForeground(Color.WHITE);
			lblSelectRH.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblSelectRH.setBounds(38, 11, 335, 31);
			}return lblSelectRH;
	}
	public JComboBox<RuralHouse> getJComboBoxRH(){
		comboBoxRH = new JComboBox<RuralHouse>();
		comboBoxRH.setModel(rhInfo);
		comboBoxRH.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				RuralHouse rh = (RuralHouse) comboBoxRH.getSelectedItem();
				int n=offerInfo.getSize();
				Vector<Offer> v = facade.getAllOffersOfRH(rh);
				for (Offer o: v){offerInfo.addElement(o);}
				for (int i=0;i<n;i++){offerInfo.removeElementAt(0);}
				
			}catch(Exception e2){}
			}
		});
		comboBoxRH.setBounds(38, 53, 335, 53);
		comboBoxRH.setModel(rhInfo);
		return comboBoxRH;
		}
	public JComboBox<Offer> getJComboBoxOffer(){
		comboBoxOffer = new JComboBox<Offer>();
		comboBoxOffer.setBounds(38, 134, 335, 53);
		comboBoxOffer.setModel(offerInfo);
		return comboBoxOffer;
	}
	public JButton getEditOfferButton(){
		if (btnEditOffer == null){
			btnEditOffer = new JButton("EDIT OFFER");
			btnEditOffer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!(offerInfo.getSize()==0)){
					SOffer = (Offer) comboBoxOffer.getSelectedItem();
					EditOffer2GUI a = new EditOffer2GUI(SOffer);
					a.setVisible(true);
					dispose();}
				}
			});
			btnEditOffer.setBounds(95, 208, 103, 31);
		}
		return btnEditOffer;
	} 
	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("CANCEL");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnCancel.setBounds(208, 208, 103, 31);
		}
		return btnCancel;
	}
	private JLabel getLblFondo() {
		if (lblFondo == null) {
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 417, 262);
			ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
			ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
			lblFondo.setIcon(icono);
		}
		return lblFondo;
	}
	}
	


