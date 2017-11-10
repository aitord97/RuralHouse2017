package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import domain.RuralHouse;

import javax.swing.JButton;

import twitter4j.TwitterException;
import twitter4j.TwitterManager;
import businessLogic.ApplicationFacadeInterfaceWS;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.awt.Color;

public class RuralHouseDetailsGUI extends JFrame {

	private JPanel contentPane;
	private JLabel lblImgRH;
	private JButton btnShare;


	/**
	 * Create the frame.
	 */
	public RuralHouseDetailsGUI(final RuralHouse rh) {
		final ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();

		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblImgRH = new JLabel("");
		lblImgRH.setBounds(172, 80, 252, 170);
		contentPane.add(lblImgRH);
		ImageIcon icon = new ImageIcon(rh.getImgPath());
		Icon icono = new ImageIcon(icon.getImage().getScaledInstance(lblImgRH.getWidth(),
				lblImgRH.getHeight(),Image.SCALE_DEFAULT));
		lblImgRH.setIcon(icono);
		
		JLabel lblCiudad = new JLabel("Ciudad: "+rh.getCity());
		lblCiudad.setForeground(Color.WHITE);
		lblCiudad.setBounds(10, 46, 144, 24);
		contentPane.add(lblCiudad);
		
		JLabel lblNumHabitaciones = new JLabel("Num Habitaciones: "+rh.getNumDormitorios());
		lblNumHabitaciones.setForeground(Color.WHITE);
		lblNumHabitaciones.setBounds(10, 70, 144, 24);
		contentPane.add(lblNumHabitaciones);
		
		JLabel lblNumCom = new JLabel("Num Comedores: "+rh.getNumComedores());
		lblNumCom.setForeground(Color.WHITE);
		lblNumCom.setBounds(10, 116, 144, 24);
		contentPane.add(lblNumCom);
		
		JLabel lblNumCocinas = new JLabel("Num Cocinas "+rh.getNumCocinas());
		lblNumCocinas.setForeground(Color.WHITE);
		lblNumCocinas.setBounds(10, 93, 144, 24);
		contentPane.add(lblNumCocinas);
		
		JLabel lblDescripcion = new JLabel("Direccion: "+rh.getDescription());
		lblDescripcion.setForeground(Color.WHITE);
		lblDescripcion.setBounds(10, 152, 162, 24);
		contentPane.add(lblDescripcion);
				
		btnShare = new JButton("Compartir");
		btnShare.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TwitterManager twitter = new TwitterManager();
				String mens = "¿Seguro que quieres compartir la casa: "+rh.toString()+"?";
				String sharMens = "Me ha gustado la casa: "+rh.toString()+" vista en la aplicacion de @DecSecEHU";
				int inf=JOptionPane.showConfirmDialog(null, mens,"Confirmacion compartir", JOptionPane.YES_NO_OPTION);
				if (inf == JOptionPane.YES_OPTION){
					try {
						twitter.PostTweet(facade.getCurrentUser().getTwitterProfile(), sharMens);
					} catch (TwitterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		});
		if(facade.getCurrentUser().getTwitterProfile().getAccessToken()=="") btnShare.setEnabled(false);

		btnShare.setBounds(305, 11, 119, 25);
		contentPane.add(btnShare);
		
		JLabel lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 434, 261);
		contentPane.add(lblFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono1 = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono1);
	}
}
