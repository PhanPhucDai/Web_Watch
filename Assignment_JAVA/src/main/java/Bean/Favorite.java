package Bean;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "Favorites", uniqueConstraints = {
		@UniqueConstraint(columnNames = {"VideoId","UserId"})
})
public class Favorite {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	@ManyToOne
	@JoinColumn(name = "UserId")
	User user;
	@ManyToOne
	@JoinColumn(name = "VideoId")
	Videos video;
 	Date likeDate = new Date(0);

 	
 	
 	 public Favorite() {
 	}

	// Constructor nhận vào Videos, User và Date
    public Favorite(Videos video, User user, Date likeDate) {
        this.video = video;
        this.user = user;
        this.likeDate = likeDate;
    }
 
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Videos getVideo() {
		return video;
	}
	public void setVideo(Videos video) {
		this.video = video;
	}
	public Date getLikeDate() {
		return likeDate;
	}
	public void setLikeDate(Date likeDate) {
		this.likeDate = likeDate;
	}
}
