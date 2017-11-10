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
import javax.swing.JList;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import twitter4j.TwitterException;
import twitter4j.TwitterManager;
import businessLogic.ApplicationFacadeInterfaceWS;
import domain.Reserva;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.io.IOException;
import java.awt.Color;

public class PayBookGUI extends JFrame {

	private JPanel contentPane;
	private JComboBox<Reserva> comboBoxBooks;
	private DefaultComboBoxModel<Reserva> bookinf = new DefaultComboBoxModel<Reserva>();
	private JLabel lblChooseABook;
	private ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
	private JLabel lblFondo;

	
	/**
	 * Create the frame.
	 */
	public PayBookGUI() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 455, 221);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblChooseABook = new JLabel("Choose a book to pay");
		lblChooseABook.setForeground(Color.WHITE);
		lblChooseABook.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblChooseABook.setBounds(131, 11, 217, 24);
		contentPane.add(lblChooseABook);
		
		Vector<Reserva>rs=facade.getReservasNoPagadas(facade.getCurrentUser());
		for(Reserva r:rs){bookinf.addElement(r);}
		comboBoxBooks = new JComboBox<Reserva>();
		comboBoxBooks.setBounds(61, 58, 320, 56);
		comboBoxBooks.setModel(bookinf);
		contentPane.add(comboBoxBooks);
		
		JButton btnPaySelectedBook = new JButton("Pay selected book");
		btnPaySelectedBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				TwitterManager twitter = new TwitterManager();
					if(bookinf.getSize()!=0){
				String mensaje = "¿Seguro que quieres terminar de pagar?";
				int rep = JOptionPane.showConfirmDialog(null, mensaje, "Confirmacion del pago",
						JOptionPane.YES_NO_OPTION);
				if(rep==JOptionPane.YES_OPTION){
					if(facade.getCurrentUser().getTwitterProfile().getAccessToken()!=""){
						JOptionPane.showMessageDialog(getContentPane(), "El codigo ha sido enviado a su cuenta: @" + facade.getCurrentUser().getTwitterProfile().getTwUserName());
						int random = (int)(Math.random()*99+1);
						String mconfirmacion ="Su codigo de verificación de pago es: " + random ;
						try {
							twitter.SendDM(facade.getCurrentUser().getTwitterProfile(),mconfirmacion);
						} catch (TwitterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						String p = JOptionPane.showInputDialog("Introduce el numero");
						int num = Integer.parseInt(p);
						if (num==random){
							JOptionPane.showMessageDialog(getContentPane(),"Correcto!");
							Reserva res = (Reserva)bookinf.getSelectedItem();
							facade.pagarReserva(res,res.getBook().getPrice(),facade.getCurrentUser());
								JOptionPane.showMessageDialog(null, "Pago realizado correctamente");
								dispose();
						}else{
							JOptionPane.showMessageDialog(getContentPane(),"Codigo incorrecto");
						}
						
					}else{
						Reserva res = (Reserva)bookinf.getSelectedItem();
						facade.pagarReserva(res,res.getBook().getPrice(),facade.getCurrentUser());
						JOptionPane.showMessageDialog(getContentPane(),"Oferta pagada");
						dispose();
					}
					
					
					
					
					
					
				}
					}
				else{JOptionPane.showMessageDialog(null, "No hay ninguna reserva seleccionada");}


				
				
				
			}
		});
		btnPaySelectedBook.setBounds(61, 125, 150, 41);
		contentPane.add(btnPaySelectedBook);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dispose();
			}
		});
		btnCancel.setBounds(231, 125, 150, 41);
		contentPane.add(btnCancel);
		
		lblFondo = new JLabel("");
		lblFondo.setBounds(0, 0, 439, 182);
		contentPane.add(lblFondo);
		ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
		ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
		lblFondo.setIcon(icono);
	}
}
