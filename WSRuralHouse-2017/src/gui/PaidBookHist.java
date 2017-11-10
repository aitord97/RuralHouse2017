package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextArea;

import domain.Reserva;
import businessLogic.ApplicationFacadeInterfaceWS;

import javax.swing.JList;

import java.awt.Font;
import java.awt.Color;

public class PaidBookHist extends JFrame {

	private JPanel contentPane;
	private ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
	private Vector<Reserva>ress = facade.getReservasPagadas(facade.getCurrentUser());
	private JTextPane textPane;
	private JLabel lblFondo;


	
	

	/**
	 * Create the frame.
	 */
	public PaidBookHist() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHisorialDePagos = new JLabel("Payment History");
		lblHisorialDePagos.setForeground(Color.WHITE);
		lblHisorialDePagos.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblHisorialDePagos.setHorizontalAlignment(SwingConstants.CENTER);
		lblHisorialDePagos.setBounds(52, 11, 320, 24);
		contentPane.add(lblHisorialDePagos);
		
		
		String strBuffer="";
		for(Reserva r: ress){strBuffer += r.toStringHist()+"\n";}
		textPane = new JTextPane();
		textPane.setBounds(10, 46, 414, 204);
		textPane.setEditable(false);
		textPane.setText(strBuffer);
		contentPane.add(textPane);
		
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 434, 261);
		contentPane.add(lblFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
		
		
		
		
	
		
		
	}
}
