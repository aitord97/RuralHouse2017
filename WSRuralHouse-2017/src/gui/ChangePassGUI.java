package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JButton;

import businessLogic.ApplicationFacadeInterfaceWS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class ChangePassGUI extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JPasswordField passwordField_1;
	private JPasswordField passwordField_2;


	/**
	 * Create the frame.
	 */
	public ChangePassGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 465, 205);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblCurrentPassword = new JLabel("Current password");
		lblCurrentPassword.setForeground(Color.WHITE);
		lblCurrentPassword.setBounds(10, 21, 138, 14);
		contentPane.add(lblCurrentPassword);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(188, 18, 138, 20);
		contentPane.add(passwordField);
		
		JLabel lblNewPass = new JLabel("New password");
		lblNewPass.setForeground(Color.WHITE);
		lblNewPass.setBounds(10, 52, 130, 14);
		contentPane.add(lblNewPass);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(188, 49, 138, 20);
		contentPane.add(passwordField_1);
		
		
		JButton btnChange = new JButton("Change!");
		btnChange.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				
				String up0,up,up1;
				up0= new String(passwordField.getPassword());
				up= new String(passwordField_1.getPassword());
				up1= new String(passwordField_2.getPassword());
				try{
		if (passwordField.getPassword().length==0||passwordField_1.getPassword().length==0||passwordField_2.getPassword().length==0){
			JOptionPane.showMessageDialog(contentPane, "Recuerda no dejar ningun campo en blanco");
		}else{
					//verifica que sea su contraseña
			if(up0.compareTo(facade.getCurrentUser().getUserPass())==0){
				System.out.println(facade.getCurrentUser().getUserPass());
			}else{
				JOptionPane.showMessageDialog(contentPane, "Contraseña erronea");
			}
					
				if(up.compareTo(up1)==0){
				
					//Actualiza el pass si ambas coinciden
					facade.setPassByAcc(facade.getCurrentUser(), up1);
					JOptionPane.showMessageDialog(contentPane, "Contraseña cambiada");
					System.out.println(facade.getCurrentUser().getUserPass());
					dispose();}
				else{JOptionPane.showMessageDialog(contentPane, "Las contraseñas no coinciden");
				}
				
		}
			}catch(Exception e){JOptionPane.showMessageDialog(contentPane, "Usuario en uso");}
			}
		});
		btnChange.setBounds(130, 122, 117, 33);
		contentPane.add(btnChange);
		
		JLabel lblRepeatPassword = new JLabel("Repeat password");
		lblRepeatPassword.setForeground(Color.WHITE);
		lblRepeatPassword.setBounds(10, 82, 138, 14);
		contentPane.add(lblRepeatPassword);
		
		passwordField_2 = new JPasswordField();
		passwordField_2.setBounds(188, 80, 138, 20);
		contentPane.add(passwordField_2);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 449, 166);
		contentPane.add(lblFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}
}
