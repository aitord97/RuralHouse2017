package gui;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import businessLogic.ApplicationFacadeInterfaceWS;

import com.toedter.calendar.JCalendar;

import domain.Offer;
import domain.RuralHouse;
import exceptions.OverlappingOfferExists;
import exceptions.BadDates;



public class EditOffer2GUI extends JFrame  {
private static final long serialVersionUID = 1L;
  private JLabel jLabel2 = new JLabel();
  private JTextField jTextField1 = new JTextField();
  private JLabel jLabel3 = new JLabel();
  private JTextField jTextField2 = new JTextField();
  private JLabel jLabel4 = new JLabel();
  private JTextField jTextField3 = new JTextField();
  private JButton jButton1 = new JButton();
  
  // Code for JCalendar
  private JCalendar jCalendar1 = new JCalendar();
  private JCalendar jCalendar2 = new JCalendar();
  private Calendar calendarInicio = null;
  private Calendar calendarFin = null;
  private JLabel jLabel5 = new JLabel();
  private final JLabel lblNewLabel = new JLabel("");
  private final JLabel lblEditing = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EditOffer2GUI.lblEditing.text")); //$NON-NLS-1$ //$NON-NLS-2$
  private Offer selectedOffer;
  private final JCheckBox chckbxPrice = new JCheckBox(ResourceBundle.getBundle("Etiquetas").getString("EditOffer2GUI.chckbxNewCheckBox.text")); //$NON-NLS-1$ //$NON-NLS-2$
  private final JCheckBox chckbxCalendar = new JCheckBox(ResourceBundle.getBundle("Etiquetas").getString("EditOffer2GUI.chckbxNewCheckBox.text_1")); //$NON-NLS-1$ //$NON-NLS-2$
  private final JLabel lblFondo = new JLabel("");
  public EditOffer2GUI(Offer o)
  {
    try
    {
      jbInit(o);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  private void jbInit(Offer o) throws Exception
  {
	selectedOffer = o;
    this.getContentPane().setLayout(null);
    this.setSize(new Dimension(513, 433));
    this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("SetAvailability"));
    jLabel2.setForeground(Color.WHITE);
    jLabel2.setText(ResourceBundle.getBundle("Etiquetas").getString("FirstDay"));
    jLabel2.setBounds(new Rectangle(25, 80, 205, 20));
    jTextField1.setBounds(new Rectangle(25, 265, 220, 25));
    jTextField1.setEditable(false);
    jLabel3.setForeground(Color.WHITE);
    jLabel3.setText(ResourceBundle.getBundle("Etiquetas").getString("LastDay"));
    jLabel3.setBounds(new Rectangle(260, 80, 75, 20));
    jTextField2.setBounds(new Rectangle(260, 265, 220, 25));
    jTextField2.setEditable(false);
    jLabel4.setForeground(Color.WHITE);
    jLabel4.setText(ResourceBundle.getBundle("Etiquetas").getString("Price"));
    jLabel4.setBounds(new Rectangle(260, 30, 75, 20));
    jTextField3.setBounds(new Rectangle(350, 30, 115, 20));
    jTextField3.setText("0");
    jButton1.setText(ResourceBundle.getBundle("Etiquetas").getString("Accept"));
    jButton1.setBounds(new Rectangle(185, 351, 130, 30));
    jCalendar1.setEnabled(false);
    jCalendar2.setEnabled(false);
    jTextField3.setEnabled(false);
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
    jButton1.addActionListener(new ActionListener()
      {
        public void actionPerformed(ActionEvent e)
        {
          jButton1_actionPerformed(e);
        }
      });
    jLabel5.setBounds(new Rectangle(100, 320, 300, 20));
    jLabel5.setForeground(Color.red);
    jLabel5.setSize(new Dimension(305, 20));
    jCalendar1.setBounds(new Rectangle(25, 100, 220, 165));
    jCalendar2.setBounds(new Rectangle(260, 100, 220, 165));
    
    // Code for  JCalendar
    this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField1.setText(dateformat.format(calendarInicio.getTime()));
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarInicio = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
          jTextField1.setText(dateformat1.format(calendarInicio.getTime()));
          jCalendar1.setCalendar(calendarInicio);
        }
      } 
    });
    
    this.jCalendar2.addPropertyChangeListener(new PropertyChangeListener()
    {
      public void propertyChange(PropertyChangeEvent propertychangeevent)
      {
        if (propertychangeevent.getPropertyName().equals("locale"))
        {
          jCalendar2.setLocale((Locale) propertychangeevent.getNewValue());
          DateFormat dateformat = DateFormat.getDateInstance(1, jCalendar2.getLocale());
          jTextField2.setText(dateformat.format(calendarFin.getTime()));
        }
        else if (propertychangeevent.getPropertyName().equals("calendar"))
        {
          calendarFin = (Calendar) propertychangeevent.getNewValue();
          DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar2.getLocale());
          jTextField2.setText(dateformat1.format(calendarFin.getTime()));
          jCalendar2.setCalendar(calendarFin);
        }
      } 
    });
    
    
    this.getContentPane().add(jCalendar2, null);
    this.getContentPane().add(jCalendar1, null);
    this.getContentPane().add(jLabel5, null);
    this.getContentPane().add(jButton1, null);
    this.getContentPane().add(jTextField3, null);
    this.getContentPane().add(jLabel4, null);
    this.getContentPane().add(jTextField2, null);
    this.getContentPane().add(jLabel3, null);
    this.getContentPane().add(jTextField1, null);
    this.getContentPane().add(jLabel2, null);
    lblNewLabel.setBounds(115, 301, 298, 38);
    
    getContentPane().add(lblNewLabel);
    lblEditing.setForeground(Color.WHITE);
    lblEditing.setBounds(10, 30, 46, 17);
    
    getContentPane().add(lblEditing);
    
    JTextArea textArea = new JTextArea();
    textArea.setText(selectedOffer.toString());
    textArea.setBounds(54, 28, 189, 20);
    getContentPane().add(textArea);
    chckbxPrice.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		if(!chckbxPrice.isSelected()){jTextField3.setEnabled(false);}
    	else{jTextField3.setEnabled(true);}
    	}
    });
    chckbxPrice.setBounds(377, 7, 114, 20);
    
    getContentPane().add(chckbxPrice);
    chckbxCalendar.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent arg0) {
    		if(!chckbxCalendar.isSelected()){jCalendar1.setEnabled(false); jCalendar2.setEnabled(false);
    		}else{jCalendar1.setEnabled(true); jCalendar2.setEnabled(true);}
    	}
    });
    chckbxCalendar.setBounds(64, 59, 115, 14);
    
    getContentPane().add(chckbxCalendar);
    lblFondo.setBounds(0, 0, 497, 394);
    
    getContentPane().add(lblFondo);
    ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
	ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
	lblFondo.setIcon(icono);
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
	  	

	  	// The next instruction creates a java.util.Date object from the date selected in the JCalendar object
	  	// and removes the hour, minute, second and ms from the date
	  	
	  	Date firstDay=trim(new Date(jCalendar1.getCalendar().getTime().getTime()));
	  	

	  	Date lastDay=trim(new Date(jCalendar2.getCalendar().getTime().getTime()));
	  	
	  	if(firstDay == null){firstDay = selectedOffer.getFirstDay();}
	  	if(lastDay == null){lastDay = selectedOffer.getLastDay();}
	  	

	  	
	  	try {

	  		//It could be to trigger an exception if the introduced string is not a number
	  		float price= Float.parseFloat(jTextField3.getText());
	  		if (price == 0){price = selectedOffer.getPrice();}
	  		//Obtain the business logic from a StartWindow class (local or remote)
	  		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();
	  		
	  		facade.updateOff(selectedOffer, firstDay, lastDay, price);
	  		JOptionPane.showMessageDialog(getContentPane(), "Oferta editada");
	  		dispose();
	  	} catch (Exception e1) {

	  		e1.printStackTrace();
	  	}
  }
  
 private void jTextField3_focusLost()
 {
   try
  {
    new Integer (jTextField3.getText());
    jLabel5.setText("");
  }
  catch (NumberFormatException ex)
  {
    jLabel5.setText(ResourceBundle.getBundle("Etiquetas").getString("ErrorNumber"));
  }
 }
}
