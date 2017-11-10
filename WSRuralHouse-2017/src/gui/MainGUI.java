package gui;

/**
 * @author Software Engineering teachers & DecSec

 */


import javax.swing.*;

import configuration.ConfigXML;
import domain.Offer;
import domain.Reserva;
import domain.RuralHouse;
import domain.User;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeImplementationWS;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.FlowLayout;

import javax.swing.GroupLayout.Alignment;

import twitter4j.TwitterException;
import gui.TwitterPinRequestDIALOG;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;
	//ImageIcon imagen = new ImageIcon("resources/ descarga.jpg");
	private JPanel jContentPane = null;
	private JButton boton1 = null;
	private JButton boton2 = null;
	private JButton boton3 = null;

    private static ApplicationFacadeInterfaceWS appFacadeInterface;
	
	public static ApplicationFacadeInterfaceWS getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (ApplicationFacadeInterfaceWS afi){
		appFacadeInterface=afi;
	}
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	private JRadioButton rdbtnNewRadioButton_2;
	private JPanel panel;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton button;
	private JButton btnEditOffer;
	private JButton btnCancelOffer;
	private JButton btnNewButton;
	

	private JLabel lblTwitterName;
	ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
	private JLabel lblCurrentuser;
	private JButton btnPagares;
	private JButton btnHistpag;
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
		super();
		
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
				try {
					
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});

		try {
			initialize();
		} catch (TwitterException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 * @throws IOException 
	 * @throws TwitterException 
	 */
	private void initialize() throws TwitterException, IOException {
		// this.setSize(271, 295);
		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
		this.setSize(672, 461);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
		
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 * @throws IOException 
	 * @throws TwitterException 
	 */
	private JPanel getJContentPane() throws TwitterException, IOException {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			JButton button_1 = new JButton("");
			button_1.setBounds(605, 11, 36, 36);
			ImageIcon imagen = new ImageIcon("resources/Assets/option.png");
			ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(button_1.getWidth(), button_1.getHeight(), Image.SCALE_DEFAULT));
			button_1.setIcon(icono);
			button_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {

								UserAreaGUI a = new UserAreaGUI();
								a.setVisible(true);
								dispose();
				}
			});
			
			jContentPane.add(button_1);
			jContentPane.add(getBoton3());
			jContentPane.add(getBoton2());
			jContentPane.add(getButton());
			jContentPane.add(getPanel());
			jContentPane.add(getBtnEditOffer());
			
			
			JButton btnLogout = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnLogout.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnLogout.setBounds(404, 375, 130, 36);
			btnLogout.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					LoginGUI a = new LoginGUI ();
					a.setVisible(true);
					dispose();
				}
			});
			jContentPane.add(btnLogout);
			jContentPane.add(getBtnCancelOffer());
			
			
			JButton btnGestionarOfertasCreadas = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnGestionarOfertasCreadas.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnGestionarOfertasCreadas.setBounds(88, 296, 218, 53);
			btnGestionarOfertasCreadas.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ManageCreatedOffersGUI frame = new ManageCreatedOffersGUI();
					frame.setVisible(true);
				}
			});
			jContentPane.add(btnGestionarOfertasCreadas);
			
			jContentPane.add(getLblCurrentuser());
			jContentPane.add(getBtnPagares());
			jContentPane.add(getBtnHistpag());
			
			JLabel lblNewLabel_1 = new JLabel(); //$NON-NLS-1$ //$NON-NLS-2$
			
			lblNewLabel_1.setIcon(new ImageIcon("resources/Assets/prueba1.png"));
			lblNewLabel_1.setBounds(0, 0, 656, 422);
			jContentPane.add(lblNewLabel_1);
			
			
		}
			
		
		return jContentPane;
	}

	
	

	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton2() {
		if (boton2 == null) {
			boton2 = new JButton();
			boton2.setBounds(88, 43, 446, 53);
			//Color imagen= new Color("resources/Assets/prueba2.png");
			//Color icono = new Color(imagen.getImage().getScaledInstance(boton2.getWidth(), boton2.getHeight(), Image.SCALE_DEFAULT));
			//boton2.setForeground(icono);
			boton2.setText(ResourceBundle.getBundle("Etiquetas").getString("SetAvailability"));

			boton2.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
					User u = facade.getCurrentUser();
					Vector<RuralHouse> rhs=facade.getRHOfUser(u);
					JFrame a = new SetAvailabilityGUI(rhs);
					a.setVisible(true);
				}
			});
		}
		return boton2;
	}
	
	/**
	 * This method initializes boton3
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBoton3() {
		if (boton3 == null) {
			boton3 = new JButton();
			boton3.setBounds(88, 171, 218, 53);
			boton3.setText("Book Offers");
			boton3.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new BookOfferGUI();
					a.setVisible(true);
				}
			});
		}
		return boton3;
	}
	private JRadioButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JRadioButton("English");
			rdbtnNewRadioButton.setBounds(203, 5, 81, 23);
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton);
		}
		return rdbtnNewRadioButton;
	}
	private JRadioButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JRadioButton("Euskara");
			rdbtnNewRadioButton_1.setBounds(6, 5, 81, 23);
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();				}
			});
			buttonGroup.add(rdbtnNewRadioButton_1);
		}
		return rdbtnNewRadioButton_1;
	}
	private JRadioButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JRadioButton("Castellano");
			rdbtnNewRadioButton_2.setBounds(89, 5, 93, 23);
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			buttonGroup.add(rdbtnNewRadioButton_2);
		}
		return rdbtnNewRadioButton_2;
	}
	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBounds(88, 375, 291, 36);
			panel.setLayout(null);
			panel.add(getRdbtnNewRadioButton_2());
			panel.add(getRdbtnNewRadioButton());
			panel.add(getRdbtnNewRadioButton_1());
		}
		return panel;
	}
	
	private void redibujar() {
		//lblNewLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		boton3.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryAvailability"));
		boton2.setText(ResourceBundle.getBundle("Etiquetas").getString("SetAvailability"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	private JButton getButton() {
		if (button == null) {
			button = new JButton();	
			button.setBounds(88, 107, 446, 53);
			button.setText("Create new RuralHouse");
			
			button.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new NewRuralHouseGUI();
					a.setVisible(true);
					
				}
			});
		
		}
		return button;
	}
	private JButton getBtnEditOffer() {
		if (btnEditOffer == null) {
			btnEditOffer = new JButton();
			btnEditOffer.setBounds(88, 235, 218, 53);
			btnEditOffer.setText(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnEditOffer.text"));
			
			btnEditOffer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
			  		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();

					EditOfferGUI a = new EditOfferGUI(facade.getAllRuralHouses());
					a.setVisible(true);
					
				}
			});
			
		}
		return btnEditOffer;
	
	}
	private JButton getBtnCancelOffer() {
		if (btnCancelOffer == null) {
			btnCancelOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnCancelOffer.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCancelOffer.setBounds(316, 235, 218, 53);
			btnCancelOffer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();

					Vector<Reserva> v = facade.getCurrentUser().getUserBookVector();
					CancelOfferGUI a = new CancelOfferGUI(v);
					a.setVisible(true);
				}
			});
		}
		return btnCancelOffer;
	}
	
	
	private JLabel getLblCurrentuser() {
		if (lblCurrentuser == null) {
			lblCurrentuser = new JLabel(facade.getCurrentUser().getUserName()+" "+facade.getCurrentUser().getUserSurname());
			lblCurrentuser.setForeground(Color.WHITE);
			lblCurrentuser.setBackground(Color.WHITE);
			lblCurrentuser.setHorizontalAlignment(SwingConstants.CENTER);
			lblCurrentuser.setBounds(416, 13, 138, 19);
		}
		return lblCurrentuser;
	}
	private JButton getBtnPagares() {
		if (btnPagares == null) {
			btnPagares = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnPagares.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnPagares.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PayBookGUI a = new PayBookGUI();
					a.setVisible(true);
				}
			});
			btnPagares.setBounds(316, 171, 218, 53);
		}
		return btnPagares;
	}
	private JButton getBtnHistpag() {
		if (btnHistpag == null) {
			btnHistpag = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MainGUI.btnHistpag.text")); //$NON-NLS-1$ //$NON-NLS-2$
			btnHistpag.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					PaidBookHist a = new PaidBookHist();
					a.setVisible(true);
				}
			});
			btnHistpag.setBounds(316, 296, 218, 53);
		}
		return btnHistpag;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

