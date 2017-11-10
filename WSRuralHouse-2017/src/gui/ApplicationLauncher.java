package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;

import exceptions.DB4oManagerCreationException;
import businessLogic.ApplicationFacadeInterfaceWS;
import businessLogic.FacadeFactory;
import businessLogic.FacadeImplementationWS;
import java.awt.GridLayout;

public class ApplicationLauncher {
	
	
	
	public static void main(String[] args) {
		
		FacadeFactory facadeFactory = new FacadeFactory();

		System.out.println("Locale: "+Locale.getDefault());
		try {
		MainMainGUI a=new MainMainGUI();
		a.setVisible(true);
		MainGUI.setBussinessLogic(facadeFactory.getFacade("Local"));

		}catch (Exception e) {
			//a.lblNewLabel.setText("Error: "+e.toString());
			//a.lblNewLabel.setForeground(Color.RED);		
			System.out.println("Error in ApplicationLauncher: "+e.toString());
		}
		//a.pack();


	}

}
