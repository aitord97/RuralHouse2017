package gui;

import businessLogic.ApplicationFacadeInterfaceWS;

import com.toedter.calendar.JCalendar;

import domain.Offer;
import domain.Reserva;
import domain.RuralHouse;
import domain.User;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.IOException;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;

import twitter4j.TwitterException;
import twitter4j.TwitterManager;





public class BookOfferGUI extends JFrame {
private static final long serialVersionUID = 1L;

  private JLabel jLabel1 = new JLabel();
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JTextField jTextField3 = new JTextField();
  private JButton jButton1 = new JButton();
  private JButton jButton2 = new JButton();
  private DefaultComboBoxModel<RuralHouse> rhinf = new DefaultComboBoxModel<RuralHouse>();
  
  // Code for JCalendar
  private JCalendar jCalendar1 = new JCalendar();
  private Calendar calendarMio = null;
  private JLabel jLabel4 = new JLabel();
  private JScrollPane scrollPane = new JScrollPane();
  private JComboBox<RuralHouse> comboBox ;
  private JTable table;
  private Offer of;
  private DefaultTableModel tableModel;
  private final JLabel labelNoOffers = new JLabel("");
  private String[] columnNames = new String[] {
		  ResourceBundle.getBundle("Etiquetas").getString("OfferN"), 
		  ResourceBundle.getBundle("Etiquetas").getString("RuralHouse"), 
		  ResourceBundle.getBundle("Etiquetas").getString("FirstDay"), 
		  ResourceBundle.getBundle("Etiquetas").getString("LastDay"), 
		  ResourceBundle.getBundle("Etiquetas").getString("Price") 
  	};
  private final JButton btnBookOffer = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BookOfferGUI.btnBookOffer.text")); //$NON-NLS-1$ //$NON-NLS-2$

private JRadioButton chckbxMonte;

protected JLabel lblEtiquetasCasas;

private JRadioButton chckbxPlaya;

private JRadioButton chckbxUrbano;

private JRadioButton chckbxRural;
private Vector<String> filterlist = new Vector<String>();
private Vector<RuralHouse> rhs = new Vector<RuralHouse>();
private Vector<RuralHouse> rh1= new Vector<RuralHouse>();
private final ButtonGroup buttonGroup = new ButtonGroup();
private final JLabel lblFondo = new JLabel("");

 // private static configuration.ConfigXML c;
	
  public BookOfferGUI()
  {
  	
    try
    {
      jbInit();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

  }

  private void jbInit() throws Exception
  {
	 
	ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
		
	rh1=facade.getAllRuralHouses();
	for (RuralHouse r: rh1){
		rhinf.addElement(r);
		//JOptionPane.showMessageDialog(getContentPane(), "Offer Boooked");
		dispose();
	}
	comboBox = new JComboBox();
	comboBox.setBounds(189, 22, 115, 20);
	comboBox.setModel(rhinf);
    this.setSize(new Dimension(433, 548));
    this.setTitle("Book Offer");
    jLabel1.setForeground(Color.WHITE);
    jLabel1.setBounds(40, 20, 145, 25);
    jLabel1.setText(ResourceBundle.getBundle("Etiquetas").getString("RuralHouse"));
    jLabel2.setForeground(Color.WHITE);
    jLabel2.setBounds(40, 55, 140, 25);
    jLabel2.setText(ResourceBundle.getBundle("Etiquetas").getString("FirstDay"));
    jTextField2.setBounds(190, 210, 155, 25);
    jTextField2.setEditable(false);
    jLabel3.setForeground(Color.WHITE);
    jLabel3.setBounds(40, 250, 115, 25);
    jLabel3.setText(ResourceBundle.getBundle("Etiquetas").getString("NumNights"));
    jTextField3.setBounds(190, 250, 155, 25);
    jTextField3.setText("0");
    jButton1.setBounds(55, 432, 130, 30);
    jButton1.setText(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
    jButton1.addActionListener(new ActionListener()
    	      {
    	        public void actionPerformed(ActionEvent e)
    	        {
    	          jButton1_actionPerformed(e);
    	        }
    	      });
    jButton2.setBounds(234, 432, 130, 30);
    jButton2.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
    
   
    jTextField3.addFocusListener(new FocusListener()
      {
          public void focusGained(FocusEvent e)
          {
          }
  
          public void focusLost(FocusEvent e)
          {
            jTextField3_focusLost();
          }
      });
    jButton2.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton2_actionPerformed(e);
        }
      });
    jLabel4.setBounds(55, 300, 305, 30);
    jLabel4.setForeground(Color.red);
    getContentPane().setLayout(null);
    scrollPane.setBounds(45, 305, 320, 116);
    
    this.getContentPane().add(scrollPane);
    
    table = new JTable();
    table.addMouseListener(new MouseAdapter() {
    	@Override
    	public void mouseClicked(MouseEvent e) {
    		//int i=table.getSelectedRow();
    		//int houseNumber = (int) tableModel.getValueAt(i,1);
       		//Date firstDate=new Date(((java.util.Date)tableModel.getValueAt(i,2)).getTime());
       		//Date lastDate=new Date(((java.util.Date)tableModel.getValueAt(i,3)).getTime());
	
			//BookRuralHouseGUI b=new BookRuralHouseGUI(houseNumber,firstDate,lastDate);
			//b.setVisible(true);
       		}
    });

    scrollPane.setViewportView(table);
    tableModel = new DefaultTableModel(
    			null,
            	columnNames);
        	
    table.setModel(tableModel);
    jCalendar1.setBounds(190, 60, 225, 150);
    this.getContentPane().add(jCalendar1);
    this.getContentPane().add(jLabel4);
    this.getContentPane().add(jButton2);
    this.getContentPane().add(jButton1);
    this.getContentPane().add(jTextField3);
    this.getContentPane().add(jLabel3);
    this.getContentPane().add(jTextField2);
    this.getContentPane().add(jLabel2);
    this.getContentPane().add(jLabel1);
    
    getContentPane().add(comboBox);
    labelNoOffers.setBounds(80, 420, 265, 14);
    
    getContentPane().add(labelNoOffers);
    btnBookOffer.setBounds(104, 473, 200, 25);
    btnBookOffer.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		
    		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
    		TwitterManager twitter = new TwitterManager();
    		
    		
    		 int p = JOptionPane.showConfirmDialog(getContentPane(), "Seguro que quieres reservar?","Cancel Window", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE); 
    		 if(p == JOptionPane.YES_OPTION){ 
    			 
    				Reserva reser = new Reserva(new Date(),of);
    	    		facade.setOfferStatus(of, true);
    	    		facade.setReservaToUser(reser);
    	    		String mens = "El usuario "+facade.getCurrentUser().getUserAcc()+" acaba de reservar"
    	    				+ " tu oferta: "+of.toString();
    	    		try {
						twitter.SendDM(of.offOwner.getTwitterProfile(), mens);
					} catch (TwitterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
    				JOptionPane.showMessageDialog(getContentPane(), "Offer Booked");
    			
    		 }else{
    		 		//Reserva reser = new Reserva(new Date(),of);
    	    		//facade.setOfferStatus(of, true);
    	    		//facade.setReservaToUser(reser);
    	    		//lblBookOffer.setText("Offer Booked");
    			 
    		 }
    		
    		
   
    		
    		
    		
    		
    	}
    });
    
    getContentPane().add(btnBookOffer);
    
    chckbxMonte = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("BookOfferGUI.chckbxMonte.text")); //$NON-NLS-1$ //$NON-NLS-2$
    buttonGroup.add(chckbxMonte);
    chckbxMonte.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		ApplicationFacadeInterfaceWS facade = MainGUI.getBusinessLogic();
    		
    		rhinf.removeAllElements();
    		for (RuralHouse r: rh1){
    			if (chckbxMonte.isSelected()){
    			if (facade.getFiltersOfRH(r).contains("Monte")){rhinf.addElement(r);}
    		}
    			
    			}
    	}
    });
    chckbxMonte.setBounds(19, 120, 97, 23);
    getContentPane().add(chckbxMonte);
    
    lblEtiquetasCasas = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BookOfferGUI.lblEtiquetasDeLas.text")); //$NON-NLS-1$ //$NON-NLS-2$
    lblEtiquetasCasas.setForeground(Color.WHITE);
    lblEtiquetasCasas.setBounds(19, 99, 130, 14);
    getContentPane().add(lblEtiquetasCasas);
    
    chckbxPlaya = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("BookOfferGUI.chckbxPlaya.text")); //$NON-NLS-1$ //$NON-NLS-2$
    buttonGroup.add(chckbxPlaya);
    chckbxPlaya.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
    		
    		rhinf.removeAllElements();
    		for (RuralHouse r: rh1){
    			if (chckbxPlaya.isSelected()){
    			if (facade.getFiltersOfRH(r).contains("Playa")){rhinf.addElement(r);}
    		}
    			
    			}
    	}
    });
    chckbxPlaya.setBounds(19, 146, 97, 23);
    getContentPane().add(chckbxPlaya);
    
    chckbxUrbano = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("BookOfferGUI.chckbxUrbano.text")); //$NON-NLS-1$ //$NON-NLS-2$
    buttonGroup.add(chckbxUrbano);
    chckbxUrbano.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
    		rhinf.removeAllElements();
    		for (RuralHouse r: rh1){
    			if (chckbxUrbano.isSelected()){
    			if (facade.getFiltersOfRH(r).contains("Urbano")){rhinf.addElement(r);}
    		}
    						}
    	}
    });
    chckbxUrbano.setBounds(19, 172, 97, 23);
    getContentPane().add(chckbxUrbano);
    
    chckbxRural = new JRadioButton(ResourceBundle.getBundle("Etiquetas").getString("BookOfferGUI.chckbxRural.text")); //$NON-NLS-1$ //$NON-NLS-2$
    buttonGroup.add(chckbxRural);
    chckbxRural.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
    		rhinf.removeAllElements();
    		for (RuralHouse r: rh1){
    			if (chckbxRural.isSelected()){
    			if (facade.getFiltersOfRH(r).contains("Rural")){rhinf.addElement(r);}
    		}
    			
    			}
    	}
    });
    chckbxRural.setBounds(19, 198, 97, 23);
    getContentPane().add(chckbxRural);
    
    JButton btnVerCasa = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BookOfferGUI.btnVerCasa.text")); //$NON-NLS-1$ //$NON-NLS-2$
    btnVerCasa.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		RuralHouseDetailsGUI a = new RuralHouseDetailsGUI((RuralHouse)rhinf.getSelectedItem());
    		a.setVisible(true);
    	}
    });
    btnVerCasa.setBounds(326, 21, 89, 23);
    getContentPane().add(btnVerCasa);
    lblFondo.setBounds(0, 0, 415, 509);
    
    getContentPane().add(lblFondo);
    ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
	ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
	lblFondo.setIcon(icono);
    
    // Codigo para el JCalendar
    this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField2.setText(dateformat.format(calendarMio.getTime()));
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarMio = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField2.setText(dateformat1.format(calendarMio.getTime()));
          jCalendar1.setCalendar(calendarMio);
        }
      } 
    });
    

  }

  private void jButton2_actionPerformed(ActionEvent e)
  {
    this.setVisible(false);
  }
  
  
 private void jTextField3_focusLost()
 {
   try
  {
    new Integer (jTextField3.getText());
    jLabel4.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel4.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
  }
 }
 private Date trim(Date date) {

     Calendar calendar = Calendar.getInstance();
     calendar.setTime(date);
     calendar.set(Calendar.MILLISECOND, 0);
     calendar.set(Calendar.SECOND, 0);
     calendar.set(Calendar.MINUTE, 0);
     calendar.set(Calendar.HOUR_OF_DAY, 0);
     return calendar.getTime();
 }

 private void jButton1_actionPerformed(ActionEvent e)
 {		
	 
	 	Calendar calendar = Calendar.getInstance();
   	       
 		// House object
 		RuralHouse rh=(RuralHouse)comboBox.getSelectedItem();
 		// First day

 	    //Remove the hour:minute:second:ms from the date 
 		Date firstDay=trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

    	calendar.setTime(firstDay);
 
 		int numDias= Integer.parseInt(jTextField3.getText());  	
        calendar.add(Calendar.DAY_OF_YEAR, numDias); 

        // Last day
    	Date lastDay= calendar.getTime(); 	
    	
//    	System.out.println("firstDay= "+firstDay+" lastDay= "+lastDay);
     
    	
    	try {
    		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
    		
    		Vector<Offer> t=facade.getOffers(rh,firstDay, lastDay);
    		Vector <Offer>v = new Vector<Offer>(); for (Offer o: t){if (!o.getOfferStatus()){v.addElement(o);}}
    		//Vector<Offer> v=rh.getOffers(firstDay, lastDay);
  
			Enumeration<Offer> en=v.elements();
			
			tableModel.setDataVector(null, columnNames);
			if (!en.hasMoreElements())
				labelNoOffers.setText(ResourceBundle.getBundle("Etiquetas").getString("NoOffers"));
			else {
				labelNoOffers.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOffer"));

				while (en.hasMoreElements()) {
					of=en.nextElement();
					System.out.println("Offer retrieved: "+of.toString());
					Vector<Object> row = new Vector<Object>();
					row.add(of.getOfferNumber());

					// row.add(of.getRuralHouse().getHouseNumber()); // It does not contain the rural house, when access through web services
					row.add(rh.getHouseNumber());					 // Rural houses are not serialized with offers
	
					// Dates are stored in db4o as java.util.Date objects instead of java.sql.Date objects
					// They have to be converted into java.sql.Date objects before
					java.sql.Date firstDaySqlDate = new java.sql.Date(of.getFirstDay().getTime());
					java.sql.Date lastDaySqlDate = new java.sql.Date(of.getLastDay().getTime());
					row.add(firstDaySqlDate);
					row.add(lastDaySqlDate);
					row.add(of.getPrice());
					
					tableModel.addRow(row);						
				}
			}

    		
       } catch (Exception e1) {

    	   labelNoOffers.setText(e1.getMessage());
	}}	
}
  
  
  
  
  
  
 