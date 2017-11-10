package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import businessLogic.ApplicationFacadeInterfaceWS;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterManager;
import twitter4j.TwitterProfile;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.IOException;

import javax.swing.UIManager;

import java.awt.Font;
import java.awt.Color;

public class TwitterPinRequestDIALOG extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private RequestToken requestToken;
	private static JTextField textField;
	private AccessToken accessToken;
	private static String pin;
	  private final static String CONSUMER_KEY = "FqVQfTOIRo03X5tYi2v4SXNKE";
	    private final static String CONSUMER_KEY_SECRET = "VwHfL4XeaB32M60tdyHzrV2shGz3F7Ent2rJ8PHivsCef0G1xA";
		ApplicationFacadeInterfaceWS facade=MainGUI.getBusinessLogic();


	/**
	 * Create the dialog.
	 * @throws IOException 
	 * @throws TwitterException 
	 */
	public TwitterPinRequestDIALOG() throws IOException, TwitterException {
		setBounds(100, 100, 332, 228);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		ConfigurationBuilder configuration = new ConfigurationBuilder();
        configuration.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
                .setOAuthAccessToken(null)
                .setOAuthAccessTokenSecret(null);
        TwitterFactory tf = new TwitterFactory(configuration.build());
    
        final Twitter twitter = tf.getInstance();
       
			requestToken = twitter.getOAuthRequestToken();
		
		
   	 
   	 
   	  Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+requestToken.getAuthorizationURL());
        
   
   	 
   	
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setFont(new Font("Tahoma", Font.BOLD, 15));
		textField.setBounds(10, 47, 296, 87);
		contentPanel.add(textField);
		textField.setColumns(10);
		
		JLabel lblAuthorizeRequest = new JLabel("AUTHORIZE REQUEST");
		lblAuthorizeRequest.setForeground(Color.WHITE);
		lblAuthorizeRequest.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblAuthorizeRequest.setHorizontalAlignment(SwingConstants.CENTER);
		lblAuthorizeRequest.setBounds(64, 0, 195, 38);
		contentPanel.add(lblAuthorizeRequest);
		{
			JLabel lblFondo = new JLabel("");
			lblFondo.setBounds(0, 0, 316, 190);
			contentPanel.add(lblFondo);
			ImageIcon imagen = new ImageIcon("resources/Assets/prueba1.png");
			ImageIcon icono = new ImageIcon(imagen.getImage().getScaledInstance(lblFondo.getWidth(), lblFondo.getHeight(), Image.SCALE_DEFAULT));
			lblFondo.setIcon(icono);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					

					public void actionPerformed(ActionEvent arg0) {
						try {
							TwitterManager twManager = new TwitterManager();
							String men;
							pin = textField.getText();
							accessToken = twitter.getOAuthAccessToken(requestToken, pin);
							facade.updateTwitterProfile(facade.getCurrentUser(),accessToken.getToken().toString(), 
									accessToken.getTokenSecret().toString(), accessToken.getScreenName().toString());
							System.out.println(accessToken.getToken());
							System.out.println(accessToken.getScreenName());
							twitter.createFriendship("decsecehu");
							men = "Hola @"+accessToken.getScreenName()+". Gracias por vincular tu cuenta de Twitter";
							twManager.SendDM(facade.getCurrentUser().getTwitterProfile(), men);
							UserAreaGUI a = new UserAreaGUI();
							a.setVisible(true);
							
						} catch (TwitterException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						dispose();
					}
				});
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						MainGUI a = new MainGUI();
						a.setVisible(true);
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
	}

	/*  public static void start(TwitterProfile tp) throws TwitterException, IOException {
	  
		
	  
	    
	    
	    	ConfigurationBuilder configuration = new ConfigurationBuilder();
	        configuration.setDebugEnabled(true)
	                .setOAuthConsumerKey(CONSUMER_KEY)
	                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
	                .setOAuthAccessToken(null)
	                .setOAuthAccessTokenSecret(null);
	        TwitterFactory tf = new TwitterFactory(configuration.build());
	    
	        Twitter twitter = tf.getInstance();
	        if(tp.getAccessToken()==""||tp.getAccessTokenSecret()==""){
	        RequestToken requestToken = twitter.getOAuthRequestToken();
	   	 
	   	 
	   	  Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler "+requestToken.getAuthorizationURL());
	        
	   	 AccessToken accessToken = null;
	   	
	   	 while (null == accessToken) {
	   	
	   	     try {
	   	    	
	   	  accessToken = twitter.getOAuthAccessToken(requestToken, pin);
	   	
	   	 

	   	     } catch (TwitterException te) {

	   	 

	   	  System.out.println("Failed to get access token, caused by: "
	   	   + te.getMessage());

	   	  System.out.println("Retry input PIN");

	   	 

	   	     }}}
	   	     else{
	        	AccessToken accessToken = new AccessToken(tp.getAccessToken(),tp.getAccessTokenSecret());
	        	twitter.setOAuthAccessToken(accessToken);
	        	tp.setTwUserName(twitter.getScreenName());}
	
	// twitter.updateStatus("hi.. im updating this using Namex Tweet for Demo");
	
	 
	        
	
	 
	    

	}
	   */
}
	

