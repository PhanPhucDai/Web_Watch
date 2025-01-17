package Bean;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Videos")

 
public class Videos {
	@Id
	String id;
	String title;
	String poster;
	String description;
	Integer views = 0;
	Boolean active = true;
	String detailVideo;
	@OneToMany(mappedBy = "video")
	List<Favorite> favorites;
	
	
	
	public Videos() {
	}
	public Videos(String id, String title, String poster, String description, Integer views, Boolean active, String detailVideo ) {
 		this.id = id;
		this.title = title;
		this.poster = poster;
		this.description = description;
		this.views = views;
		this.active = active;
		this.detailVideo = detailVideo;
	 
	}
	public String getDetailVideo() {
		return detailVideo;
	}
	public void setDetailVideo(String detailVideo) {
		this.detailVideo = detailVideo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPoster() {
		return poster;
	}
	public void setPoster(String poster) {
		this.poster = poster;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public Boolean getActive() {
		return active;
	}
	public void setActive(Boolean active) {
		this.active = active;
	}
	public List<Favorite> getFavorites() {
		return favorites;
	}
	public void setFavorites(List<Favorite> favorites) {
		this.favorites = favorites;
	}



}
