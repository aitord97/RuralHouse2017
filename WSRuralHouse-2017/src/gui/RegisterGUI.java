package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;

import businessLogic.ApplicationFacadeInterfaceWS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class RegisterGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	protected JLabel lblUserName;
	protected JLabel lblPassword;
	protected JLabel lblRepeatPassword;
	private JButton btnSignUp;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JRadioButton rdbtnOwner;
	private JRadioButton rdbtnClient;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JPanel panel;
	private JLabel lblUserType;
	private JLabel lblNewLabel;
	private JLabel lblFondo;



	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		setForeground(Color.BLACK);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 272, 354);
		this.getJContentPanel();
		
	}
	
	private JPanel getJContentPanel (){
		if (contentPane == null){
			
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	    contentPane.setLayout(null);
	    contentPane.add(getUserNameLabel());
	    contentPane.add(getPassLabel());
	    contentPane.add(getRePassField());
	    contentPane.add(getRepPassLabel());
	    contentPane.add(getPassField());
	    contentPane.add(getTextField());
	    contentPane.add(getSignUpButton());
	    contentPane.add(getPanel());
	    contentPane.add(getLblUserType());
	    contentPane.add(getLblNewLabel());
	    contentPane.add(getLblFondo());
		}
	
	return contentPane;
	}
	private JLabel getUserNameLabel(){
		if (lblUserName == null){
		lblUserName = new JLabel("User Name");
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserName.setBounds(79, 60, 90, 28);}
		return lblUserName;
	}
	private JLabel getPassLabel(){
		if(lblPassword== null){
		lblPassword = new JLabel("Password");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setBounds(79, 106, 90, 28);
		}
		return lblPassword;
		}
	private JLabel getRepPassLabel(){
		if (lblRepeatPassword == null){
			lblRepeatPassword = new JLabel("Repeat Password");
			lblRepeatPassword.setForeground(Color.WHITE);
			lblRepeatPassword.setHorizontalAlignment(SwingConstants.CENTER);
			lblRepeatPassword.setBounds(67, 149, 102, 28);
		}
		return lblRepeatPassword;
	}
	private JTextField getTextField(){
		if (textField == null){
			textField = new JTextField();
			textField.setBounds(10, 89, 228, 20);
			textField.setColumns(10);
		}
		return textField;
	}
	private JPasswordField getPassField(){
		if(passwordField==null){
			passwordField = new JPasswordField();
			passwordField.setBounds(10, 131, 228, 20);
		}
		return passwordField;
	}
	private JPasswordField getRePassField(){
		if(passwordField_1==null){
			passwordField_1 = new JPasswordField();
			passwordField_1.setBounds(10, 174, 228, 20);
		}
		return passwordField_1;
	}
	private JButton getSignUpButton(){
		if (btnSignUp == null){
			btnSignUp = new JButton("Sign up");
			btnSignUp.setBounds(10, 264, 240, 40);
			btnSignUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String uc,up,up1,ut;
					uc=textField.getText();
					up= new String(passwordField.getPassword());
					up1= new String(passwordField_1.getPassword());
					if(rdbtnClient.isSelected()){ut= "Client";}
					else if(rdbtnOwner.isSelected()){ut="Owner";}
					else{ut=null;}
					try{
					if(up.compareTo(up1)==0){
						ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
						facade.strAccount(uc, up, ut);
						JOptionPane.showMessageDialog(contentPane, "Usuario registrado");
						dispose();}
					else{JOptionPane.showMessageDialog(contentPane, "Las contraseñas no coinciden");
					}
				}catch(Exception e){JOptionPane.showMessageDialog(contentPane, "Usuario en uso");}
				}});
		}
		return btnSignUp;
	}
	private JRadioButton getOwnerButton(){
		if (rdbtnOwner == null){
			rdbtnOwner = new JRadioButton("Owner");
			rdbtnOwner.setBounds(6, 7, 63, 23);
			buttonGroup_1.add(rdbtnOwner);
		    }
		return rdbtnOwner;
		}
	private JRadioButton getClientButton(){
		if(rdbtnClient==null){
		rdbtnClient = new JRadioButton("Client");
		rdbtnClient.setBounds(159, 7, 63, 23);
		buttonGroup_1.add(rdbtnClient);
	    }
		return rdbtnClient;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(10, 218, 240, 35);
			panel.setLayout(null);
			panel.add(getClientButton());
			panel.add(getOwnerButton());
		}
		return panel;
	}
	private JLabel getLblUserType() {
		if (lblUserType == null) {
			lblUserType = new JLabel("User Type");
			lblUserType.setForeground(Color.WHITE);
			lblUserType.setHorizontalAlignment(SwingConstants.CENTER);
			lblUserType.setBounds(79, 205, 79, 14);
		}
		return lblUserType;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("SingUp");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setBounds(10, 11, 228, 38);
		}
		return lblNewLabel;
	}
	private JLabel getLblFondo() {
		if (lblFondo == null) {
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 256, 315);
			ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
			ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
			lblFondo.setIcon(icono);
		}
		return lblFondo;
	}
	}

