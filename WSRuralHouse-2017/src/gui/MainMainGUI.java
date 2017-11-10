package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;

import javax.swing.border.CompoundBorder;
import javax.swing.ImageIcon;

public class MainMainGUI extends JFrame {

	private JPanel contentPane;
	private JButton btnQueryAvailability;
	private JButton btnLogin;
	private JButton btnNewButton;

	/**
	 * Create the frame.
	 */
	public MainMainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 317, 333);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new CompoundBorder());
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		btnQueryAvailability = new JButton("Query Availability");
		btnQueryAvailability.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				QueryAvailabilityGUI a = new QueryAvailabilityGUI();
				a.setVisible(true);
		}
		});
		btnQueryAvailability.setBounds(10, 113, 281, 63);
		contentPane.add(btnQueryAvailability);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginGUI a = new LoginGUI();
				a.setVisible(true);
				dispose();
			}
		});
		btnLogin.setBounds(10, 216, 116, 46);
		contentPane.add(btnLogin);
		
		btnNewButton = new JButton("Sing up");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RegisterGUI a = new RegisterGUI();
				a.setVisible(true);
			}
		});
		btnNewButton.setBounds(174, 216, 116, 46);
		contentPane.add(btnNewButton);
		
		JLabel lblSelectOption = new JLabel("SELECT OPTION");
		lblSelectOption.setForeground(Color.WHITE);
		lblSelectOption.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblSelectOption.setBounds(110, 35, 116, 46);
		contentPane.add(lblSelectOption);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 301, 294);
		contentPane.add(lblFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}
}
