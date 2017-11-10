package twitter4j;
import gui.MainGUI;
import gui.ManageCreatedOffersGUI;
import gui.TwitterPinRequestDIALOG;

import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

 








import javax.swing.JDialog;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class TwitterManager {

		
	    private final static String CONSUMER_KEY = "FqVQfTOIRo03X5tYi2v4SXNKE";
	    private final static String CONSUMER_KEY_SECRET = "VwHfL4XeaB32M60tdyHzrV2shGz3F7Ent2rJ8PHivsCef0G1xA";
	    private final static String DEC_SEC_KEY = "857333028011552770-j45Cm81ORhPRsxoKnt4lrdWM9emw6SW";
	    private final static String DEC_SEC_KEY_SECRET = "diPi3ZJe81sJmJMbLRjL4vvqdFEvN4arcpz5unyKRRfq9";
		
	    public Twitter start(TwitterProfile tp) throws TwitterException, IOException {
	    
	    
	    	ConfigurationBuilder configuration = new ConfigurationBuilder();
	        configuration.setDebugEnabled(true)
	                .setOAuthConsumerKey(CONSUMER_KEY)
	                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
	                .setOAuthAccessToken(null)
	                .setOAuthAccessTokenSecret(null);
	        TwitterFactory tf = new TwitterFactory(configuration.build());
	    
	        Twitter twitter = tf.getInstance();
	        
	        	AccessToken accessToken = new AccessToken(tp.getAccessToken(),tp.getAccessTokenSecret());
	        	twitter.setOAuthAccessToken(accessToken);
	        	return twitter;}
	    
	    public void PostTweet(TwitterProfile tp, String message) throws TwitterException, IOException{
	    	Twitter twitter = start(tp);
	    	twitter.updateStatus(message);
	    }
	    public void SendDM(TwitterProfile dest, String message) throws TwitterException, IOException{
	    	ConfigurationBuilder configuration = new ConfigurationBuilder();
	        configuration.setDebugEnabled(true)
	                .setOAuthConsumerKey(CONSUMER_KEY)
	                .setOAuthConsumerSecret(CONSUMER_KEY_SECRET)
	                .setOAuthAccessToken(null)
	                .setOAuthAccessTokenSecret(null);
	        TwitterFactory tf = new TwitterFactory(configuration.build());
	    
	        Twitter twitter = tf.getInstance();
	        
	        	AccessToken accessToken = new AccessToken(DEC_SEC_KEY,DEC_SEC_KEY_SECRET);
	        	twitter.setOAuthAccessToken(accessToken);
	    	twitter.sendDirectMessage(dest.getTwUserName(), message);
	    }
	        	
	// twitter.updateStatus("hi.. im updating this using Namex Tweet for Demo");
	
	 
	        
	
	 
	    

	
	   
}




