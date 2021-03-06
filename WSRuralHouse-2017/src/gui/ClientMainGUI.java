package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.ButtonGroup;

import domain.Offer;
import domain.Reserva;
import businessLogic.ApplicationFacadeInterfaceWS;

public class ClientMainGUI extends JFrame {

	private JPanel contentPane;
	private JButton boton3;
	private JRadioButton rdbtnNewRadioButton;
	private Container buttonGroup;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	private JButton btnLogout;
	private JButton button;
	private JLabel getLabel;
	ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
	private JLabel lblFondo;

	/**
	 * Create the frame.
	 */
	public ClientMainGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 393);
		setContentPane(getJContentPane());
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ClientMainGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame a = new BookOfferGUI();
				a.setVisible(true);
				
			}
		});
		btnNewButton.setBounds(10, 126, 446, 53);
		contentPane.add(btnNewButton);
		contentPane.add(getBtnLogout());
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 264, 258, 36);
		contentPane.add(panel);
		panel.add(getRdbtnNewRadioButton_2());
		panel.add(getRdbtnNewRadioButton_1());
		panel.add(getRdbtnNewRadioButton());
		
		JButton button_1 = new JButton("HistPag");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PaidBookHist a = new PaidBookHist();
				a.setVisible(true);
			}
		});
		button_1.setBounds(256, 62, 200, 53);
		contentPane.add(button_1);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UserAreaGUI a = new UserAreaGUI();
				a.setVisible(true);
				dispose();
			}
		});
		button_2.setBounds(416, 11, 40, 40);
		contentPane.add(button_2);
		ImageIcon imagenn = new ImageIcon("resources/Assets/option.png");
		ImageIcon iconon = new ImageIcon(imagenn.getImage().getScaledInstance(button_2.getWidth(), button_2.getHeight(), Image.SCALE_DEFAULT));
		button_2.setIcon(iconon);
		contentPane.add(getButton());
		contentPane.add(getLabel());
		contentPane.add(getLabel_1());
		
	}
	
	private Container getJContentPane() {
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		contentPane.add(getBoton3());
		return contentPane;
	}

	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setBounds(10, 190, 446, 63);
			boton3.setText("Cancel Offers");
			boton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
					Vector<Reserva> v = facade.getCurrentUser().getUserBookVector();
					JFrame a = new CancelOfferGUI(v);
					a.setVisible(true);
				}
			});
		}
		return boton3;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			buttonGroup_1.add(rdbtnNewRadioButton);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			buttonGroup_1.add(rdbtnNewRadioButton_1);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			buttonGroup_1.add(rdbtnNewRadioButton_2);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
		
		}
		return rdbtnNewRadioButton_2;
	}
	
	private void redibujar() {
		//lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		boton3.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	private JButton getBtnLogout() {
		if (btnLogout == null) {
			btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("ClientMainGUI.btnLogout.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LoginGUI a = new LoginGUI();
					a.setVisible(true);
					dispose();
				}
			});
			btnLogout.setBounds(295, 264, 130, 36);
		}
		return btnLogout;
	}
	private JButton getButton() {
		if (button == null) {
			button = new JButton("PagaRES");
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					PayBookGUI a = new PayBookGUI();
					a.setVisible(true);
				}
			});
			button.setBounds(10, 62, 200, 53);
		}
		return button;
	}
	private JLabel getLabel() {
		if (getLabel == null) {
			getLabel = new JLabel(facade.getCurrentUser().getUserName()+" "+facade.getCurrentUser().getUserSurname());
			getLabel.setForeground(Color.WHITE);
			getLabel.setHorizontalAlignment(SwingConstants.CENTER);
			getLabel.setBounds(256, 22, 138, 19);
		}
		return getLabel;
	}
	private JLabel getLabel_1() {
		if (lblFondo == null) {
			lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 485, 354);
			ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
			ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
			lblFondo.setIcon(icono);
		}
		return lblFondo;
	}
}
