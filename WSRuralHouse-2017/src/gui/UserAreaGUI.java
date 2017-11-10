package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JButton;

import twitter4j.TwitterException;
import businessLogic.ApplicationFacadeInterfaceWS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.SwingConstants;

import domain.User;

import java.awt.Font;
import java.awt.Color;

public class UserAreaGUI extends JFrame {



	private JPanel contentPane;
	private JLabel lblTwitterAccount;

	

	/**
	 * Create the frame.
	 */
	public UserAreaGUI() {
		
		final ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 396, 376);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblName = new JLabel("Name");
		lblName.setForeground(Color.WHITE);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblName.setBounds(10, 79, 81, 14);
		contentPane.add(lblName);
		
		JLabel lblLastName = new JLabel("Surname");
		lblLastName.setForeground(Color.WHITE);
		lblLastName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblLastName.setBounds(10, 121, 81, 14);
		contentPane.add(lblLastName);
		
		JLabel lblTelephone = new JLabel("Phone");
		lblTelephone.setForeground(Color.WHITE);
		lblTelephone.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelephone.setBounds(10, 164, 81, 14);
		contentPane.add(lblTelephone);
		
		JButton btnChangePassword = new JButton("Change Password");
		btnChangePassword.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePassGUI a = new ChangePassGUI();
				a.setVisible(true);
				dispose();
			}
		});
		btnChangePassword.setBounds(214, 292, 157, 34);
		contentPane.add(btnChangePassword);
		
		JLabel lblUserNAme = new JLabel(facade.getCurrentUser().getUserName());
		lblUserNAme.setForeground(Color.WHITE);
		lblUserNAme.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserNAme.setBounds(89, 79, 81, 14);
		contentPane.add(lblUserNAme);
		
		JLabel lblUserSurname = new JLabel(facade.getCurrentUser().getUserSurname());
		lblUserSurname.setForeground(Color.WHITE);
		lblUserSurname.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserSurname.setBounds(89, 121, 115, 14);
		contentPane.add(lblUserSurname);
		
		JLabel lblUserPhone = new JLabel( String.valueOf(facade.getCurrentUser().getUserPhone()));
		lblUserPhone.setForeground(Color.WHITE);
		lblUserPhone.setHorizontalAlignment(SwingConstants.LEFT);
		lblUserPhone.setBounds(89, 164, 115, 14);
		contentPane.add(lblUserPhone);
		
		JLabel lblSaldo = new JLabel(String.valueOf(facade.getMonedero()));
		lblSaldo.setForeground(Color.WHITE);
		lblSaldo.setHorizontalAlignment(SwingConstants.LEFT);
		lblSaldo.setBounds(89, 209, 115, 14);
		contentPane.add(lblSaldo);
		
		JButton btnAddOrEdit_Name = new JButton("Create or Edit");
		btnAddOrEdit_Name.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				String p = JOptionPane.showInputDialog("Introduce tu nombre");
				facade.setUserName(facade.getCurrentUser(), p);
			}
		});
		btnAddOrEdit_Name.setBounds(214, 69, 157, 34);
		contentPane.add(btnAddOrEdit_Name);
		
		JButton btnAddOrEdit_Surname = new JButton("Create or Edit");
		btnAddOrEdit_Surname.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
					String p = JOptionPane.showInputDialog("Introduce tus 2 apellidos");
					facade.setUserSurname(facade.getCurrentUser(), p);
			}
		});
		btnAddOrEdit_Surname.setBounds(214, 111, 157, 34);
		contentPane.add(btnAddOrEdit_Surname);
		
		JButton btnAddOrEdit_Phone = new JButton("Create or Edit");
		btnAddOrEdit_Phone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try{
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				String p = JOptionPane.showInputDialog("Introduce tu numero");
				int num = Integer.parseInt(p);
				facade.setUserPhone(facade.getCurrentUser(), num);
				}catch (Exception e2){
					System.out.println(e2.toString());
				}
			}
		});
		btnAddOrEdit_Phone.setBounds(214, 154, 157, 34);
		contentPane.add(btnAddOrEdit_Phone);
		
		JButton btnLinkYourTwitter = new JButton("Link Twitter account");
		btnLinkYourTwitter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TwitterPinRequestDIALOG a = null;
				
				try {
					a = new TwitterPinRequestDIALOG();
					 
					
						
				} catch (IOException | TwitterException e) {
					 //TODO Auto-generated catch block
					e.printStackTrace();
				}
			
			a.setVisible(true);
			dispose();
			}
		});
		btnLinkYourTwitter.setBounds(214, 247, 157, 34);
		contentPane.add(btnLinkYourTwitter);
		
		
		lblTwitterAccount = new JLabel("");
		lblTwitterAccount.setHorizontalAlignment(SwingConstants.LEFT);
		lblTwitterAccount.setBounds(10, 251, 160, 19);
		contentPane.add(lblTwitterAccount);
		if(facade.getCurrentUser().getTwitterProfile().getAccessToken()!=""){
			btnLinkYourTwitter.setEnabled(false);
			lblTwitterAccount.setText("@"+facade.getCurrentUser().getTwitterProfile().getTwUserName());}
		
		JButton btnOk = new JButton("OK!");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (facade.getCurrentUser().getUserType().compareTo("Owner")==0){
					MainGUI a = new MainGUI();
					a.setVisible(true);
				}else{ClientMainGUI a = new ClientMainGUI();
				a.setVisible(true);
				}
				dispose();
			}
		});
		btnOk.setBounds(10, 292, 176, 34);
		contentPane.add(btnOk);
		
		JLabel lblMonedero = new JLabel("Monedero");
		lblMonedero.setForeground(Color.WHITE);
		lblMonedero.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMonedero.setBounds(10, 209, 81, 14);
		contentPane.add(lblMonedero);
		
		JButton btnAadeSaldo = new JButton("A\u00F1ade saldo");
		btnAadeSaldo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				String p = JOptionPane.showInputDialog("Introduce tu saldo a añadir");
				int num = Integer.parseInt(p);
				facade.setMonedero(facade.getCurrentUser(), num);
				
			}
		});
		btnAadeSaldo.setBounds(214, 199, 157, 34);
		contentPane.add(btnAadeSaldo);
		
		JLabel lblNewLabel = new JLabel("User Area");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(61, 11, 262, 47);
		contentPane.add(lblNewLabel);
		
		JButton btnDeleteAccount = new JButton("Delete account");
		btnDeleteAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int resp=JOptionPane.showConfirmDialog(null, "¿Estas seguro? Esta accion borrará todas tus casas rurales",
						"Delete your account",JOptionPane.YES_NO_OPTION);
				if(resp == JOptionPane.YES_OPTION){
					User u = facade.getCurrentUser();
					if( u.getUserOff().size() == 0 && u.getUserBookVector().size()==0){
						facade.removeUserAccount(facade.getCurrentUser());
						JOptionPane.showMessageDialog(null, "Se ha borrado la cuenta con exito");
						MainMainGUI a = new MainMainGUI();
						a.setVisible(true);
						dispose();
					}else{
						JOptionPane.showMessageDialog(null,"No se ha podido borrar la cuenta. Tienes ofertas creadas o reservadas." );
					}
				}
			}
		});
		btnDeleteAccount.setBounds(266, 26, 105, 23);
		contentPane.add(btnDeleteAccount);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 380, 337);
		contentPane.add(lblFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);

	
	}
}
