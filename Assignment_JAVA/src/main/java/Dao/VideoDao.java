package Dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

 

import Bean.Videos;
import DTO.VideoMaxFavoriteDTO;
import Interface.InterfaceDao;
import untils.XJPA;

public class VideoDao implements InterfaceDao<Videos> {
    EntityManager em = XJPA.getEntityManager();

    @Override
    public void addItems(Videos item) {
        // Thêm 1 video
        try {
            // Kiểm tra nếu giao dịch chưa bắt đầu
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.persist(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }
    
    public void updateItems(Videos item) {
        // Thêm 1 video
        try {
            // Kiểm tra nếu giao dịch chưa bắt đầu
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            em.merge(item);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        }
    }

    public boolean deleteById(String videoId) {
        // Xóa 1 video
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            Videos video =  em.find(Videos.class, videoId);
            em.remove(video);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Videos> getAllItems() {
        // Lấy tất cả các video
        List<Videos> listVideos = null;
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            String jpql = "select o from Videos o";
            TypedQuery<Videos> video = em.createQuery(jpql, Videos.class);
            listVideos = video.getResultList();
            em.getTransaction().commit();
            return listVideos;
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
            return listVideos;
        }
    }

    @Override
    public Videos getItemById(String id) {
        Videos video = null;
        try {
            if (!em.getTransaction().isActive()) {
                em.getTransaction().begin();
            }
            video = em.find(Videos.class, id);
            em.getTransaction().commit();
        } catch (Exception exception) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            exception.printStackTrace();
            return video;
        }
        return video;
    }
    
    public List<Videos> getItemBySearchKey(String key) {
    	List<Videos> video = null;
        try {
          
            em.getTransaction().begin();
            String sql="select o from Videos o where o.title like :key";
            TypedQuery<Videos> typedQuery= em.createQuery(sql, Videos.class);
            typedQuery.setParameter("key", "%" + key + "%");
            video = typedQuery.getResultList();	
            em.getTransaction().commit();
        } catch (Exception exception) { 
            em.getTransaction().rollback();
            exception.printStackTrace();
            return video;
        }
        return video;
    }
    
    public List<Videos> getItemMaxView() {
    	List<Videos> video = null;
        try {
          
            em.getTransaction().begin();
            String sql="select o from Videos o order by o.views desc  ";
            TypedQuery<Videos> typedQuery= em.createQuery(sql, Videos.class);
            video = typedQuery.getResultList();	
            em.getTransaction().commit();
        } catch (Exception exception) { 
            em.getTransaction().rollback();
            exception.printStackTrace();
            return video;
        }
        return video;
    }
    public List<VideoMaxFavoriteDTO> getItemMaxFavourite() {
        List<VideoMaxFavoriteDTO> result = new ArrayList<>();
        try {
            em.getTransaction().begin();
            String jpql = "SELECT v.id, v.title, v.poster, v.description, v.views, v.active, v.detailVideo, COUNT(f) " +
                          "FROM Videos v LEFT JOIN v.favorites f " +
                          "GROUP BY v.id, v.title, v.poster, v.description, v.views, v.active, v.detailVideo " +
                          "ORDER BY COUNT(f) DESC";
            TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
            List<Object[]> rows = query.getResultList();

            for (Object[] row : rows) {
                String id = (String) row[0];
                String title = (String) row[1];
                String poster = (String) row[2];
                String description = (String) row[3];
                int views = (int) row[4];
                boolean active = (boolean) row[5];
                String detailVideo = (String) row[6];
                long favoriteCount = (long) row[7];

                VideoMaxFavoriteDTO dto = new VideoMaxFavoriteDTO(id, title, poster, description, views, active, detailVideo, favoriteCount);
                result.add(dto);
            }

            em.getTransaction().commit();
        } catch (Exception exception) {
            em.getTransaction().rollback();
            exception.printStackTrace();
        }
        return result;
    }

	@Override
	public boolean deleteById(Videos id) {
		// TODO Auto-generated method stub
		return false;
	}
}
