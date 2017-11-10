package twitter4j;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.PrimaryKey;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class TwitterProfile {
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Id
	private int id;
	private String twUserName;
	private String accessToken;
	private String accessTokenSecret;
	@OneToOne(mappedBy = "tp")
	private domain.User user;
	
	public TwitterProfile(domain.User u){
		this.accessToken="";
		this.accessTokenSecret="";
		this.twUserName="";
		this.setUser(u);
	}

	public String getAccessToken() {
		return this.accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getAccessTokenSecret() {
		return this.accessTokenSecret;
	}

	public void setAccessTokenSecret(String accessTokenSecret) {
		this.accessTokenSecret = accessTokenSecret;
	}

	public String getTwUserName() {
		return this.twUserName;
	}

	public void setTwUserName(String twUserName) {
		this.twUserName = twUserName;
	}

	public domain.User getUser() {
		return user;
	}

	public void setUser(domain.User user) {
		this.user = user;
	}
}
