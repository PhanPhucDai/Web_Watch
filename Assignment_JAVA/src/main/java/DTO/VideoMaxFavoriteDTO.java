package DTO;

public class VideoMaxFavoriteDTO {
	  private String id;
	    private String title;
	    private String poster;
	    private String description;
	    private int views;
	    private boolean active;
	    private String detailVideo;
	    private long favoriteCount;
	    
	    
	    
	    
		public VideoMaxFavoriteDTO() {
			super();
		}
		public VideoMaxFavoriteDTO(String id, String title, String poster, String description, int views,
				boolean active, String detailVideo, long favoriteCount) {
			super();
			this.id = id;
			this.title = title;
			this.poster = poster;
			this.description = description;
			this.views = views;
			this.active = active;
			this.detailVideo = detailVideo;
			this.favoriteCount = favoriteCount;
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
		public int getViews() {
			return views;
		}
		public void setViews(int views) {
			this.views = views;
		}
		public boolean isActive() {
			return active;
		}
		public void setActive(boolean active) {
			this.active = active;
		}
		public String getDetailVideo() {
			return detailVideo;
		}
		public void setDetailVideo(String detailVideo) {
			this.detailVideo = detailVideo;
		}
		public long getFavoriteCount() {
			return favoriteCount;
		}
		public void setFavoriteCount(long favoriteCount) {
			this.favoriteCount = favoriteCount;
		}
	    
}
