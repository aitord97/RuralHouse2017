package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.ExtendedIterator;
import businessLogic.FacadeImplementationWS;
import businessLogic.RuralHouseIterator;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import domain.RuralHouse;
import domain.*;
import exceptions.DB4oManagerCreationException;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JTabbedPane;

import java.awt.Font;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

public class LoginGUI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnLogin;
	protected JLabel lblUser;
	protected JLabel lblPassword;
	private JButton btnSignUp;
	private JButton btnCancel;
	private JLabel lblImgPer;
	private JLabel lblFondo;


	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		super();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				//try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				//} catch (Exception e1) {
					// TODO Auto-generated catch block
					//System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				//}
				System.exit(1);
			}
		});
		initialize();		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
}
		
		
		
		private void initialize(){
		setBounds(100, 100, 206, 337);
		this.getJContentPane();
		
	}


		private JPanel getJContentPane(){
			if (contentPane == null){
				
			contentPane = new JPanel();
			contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
			setContentPane(contentPane);
			contentPane.setLayout(null);
			contentPane.add(getLabelUser());
			contentPane.add(getLabelPass());
			contentPane.add(getTextField());
			contentPane.add(getPasswordField());
			contentPane.add(getLoginButton());
			contentPane.add(getBtnSignUp());
			
			btnCancel = new JButton("Cancel");
			btnCancel.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					MainMainGUI a = new MainMainGUI();
					a.setVisible(true);
					dispose();
				}
			});
			btnCancel.setBounds(10, 252, 170, 35);
			contentPane.add(btnCancel);
			
			lblImgPer = new JLabel("");
			lblImgPer.setBounds(57, 11, 70, 70);
			ImageIcon imagen = new ImageIcon("resources/Assets/login.png");
			ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblImgPer.getWidth(), lblImgPer.getHeight(), Image.SCALE_DEFAULT));
			lblImgPer.setIcon(icono);
			contentPane.add(lblImgPer);
			contentPane.add(getLblFondo());}
			return contentPane;
		}
		
		
		private JLabel getLabelUser() {
			if(lblUser == null){
			lblUser = new JLabel("USER");
			lblUser.setForeground(Color.WHITE);
			lblUser.setHorizontalAlignment(SwingConstants.CENTER);
			lblUser.setBounds(65, 94, 62, 14);}
			return lblUser;
		}
		
		private JLabel getLabelPass(){
			if (lblPassword == null){
				lblPassword = new JLabel("PASSWORD");
				lblPassword.setForeground(Color.WHITE);
				lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
				lblPassword.setBounds(44, 150, 97, 14);
			}
			return lblPassword;
		}
		
		private JTextField getTextField(){
			if(textField == null){
			textField = new JTextField();
			textField.setBounds(10, 119, 170, 20);
			textField.setColumns(10);}
			return textField;
		}
		
		private JPasswordField getPasswordField(){
			if(passwordField == null){
			passwordField = new JPasswordField();
			passwordField.addKeyListener(new KeyAdapter() {
				@Override
				public void keyReleased(KeyEvent e) {
					if (e.VK_ENTER==e.getKeyCode())
			         {
						ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
						String u = textField.getText();
						String p = new String(passwordField.getPassword());
						try {
					if (facade.checkLogin(u, p)){
							facade.setCurrentUser(u);
							System.out.println("Usuario actual: "+facade.getCurrentUser());
						if(facade.getUserTypeByAcc(u).compareTo("Owner")==0){
						MainGUI a = new MainGUI();
						a.setVisible(true);
						dispose();}
						else if(facade.getUserTypeByAcc(u).compareTo("Client")==0){
							ClientMainGUI a = new ClientMainGUI();
							a.setVisible(true);
							dispose();
						}
					
						
					}else{
						JOptionPane.showMessageDialog(getContentPane(), "Usuario o contrase�a incorrecta");
						
					}
						} catch (Exception e2) {
							System.out.println(e2.toString());
						}	
			  }
				}
			});
			passwordField.setBounds(10, 175, 170, 20);
			contentPane.add(passwordField);}
			return passwordField;
		}
		
		private JButton getLoginButton(){
			if (btnLogin == null){
			btnLogin = new JButton("Login");
			btnLogin.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
					String u = textField.getText();
					String p = new String(passwordField.getPassword());
					
					try {
					if(u.compareTo("UsuarioParticular")==0) {
						ExtendedIterator rhs = new RuralHouseIterator();
						AlertRuralHouseParticular a = new AlertRuralHouseParticular(rhs,new UsuarioParticular("UsuarioParticular","",""));

						a.setVisible(true);
						
						dispose();
					}
					if(u.compareTo("AgenciaDeViajes")==0) {
					ExtendedIterator rhs = new RuralHouseIterator();
					AlertRuralHouseAgencia b = new AlertRuralHouseAgencia(rhs,new AgenciaDeViajes("AgenciaDeViajes","",""));
					b.setVisible(true);
					dispose();
					}
					
					else if (facade.checkLogin(u, p)){
						facade.setCurrentUser(u);
						System.out.println("Usuario actual: "+facade.getCurrentUser());
					if(facade.getUserTypeByAcc(u).compareTo("Owner")==0){
					MainGUI a = new MainGUI();
					a.setVisible(true);
					dispose();}
					else if(facade.getUserTypeByAcc(u).compareTo("Client")==0){
						ClientMainGUI a = new ClientMainGUI();
						a.setVisible(true);
						dispose();
					}
				
					
				}else{
					JOptionPane.showMessageDialog(getContentPane(), "Contrase�a incorrecta");
					
				}
					} catch (Exception e2) {
						System.out.println(e2.toString());
						JOptionPane.showMessageDialog(getContentPane(), "El usuario no existe");
					}	
				}
			});
			btnLogin.setBounds(10, 206, 170, 35);}
			return btnLogin;
			
			
		}

	private JButton getBtnSignUp() {
		if (btnSignUp == null) {
			btnSignUp = new JButton("Sign Up");
			btnSignUp.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					JFrame a = new RegisterGUI();
					a.setVisible(true);
				}
			});
			btnSignUp.setBounds(335, 11, 89, 23);
		}
		return btnSignUp;
	}
	private JLabel getLblFondo() {
		if (lblFondo == null) {
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 190, 298);
			ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
			ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
			lblFondo.setIcon(icono);
		}
		return lblFondo;
	}
}

