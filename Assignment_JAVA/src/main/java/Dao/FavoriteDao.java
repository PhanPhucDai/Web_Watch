package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;

import Bean.Favorite;
import Bean.User;
import Interface.InterfaceDao;
import untils.XJPA;

public class FavoriteDao implements InterfaceDao<Favorite>{
	EntityManager em= XJPA.getEntityManager();
	@Override
	public void addItems(Favorite item) {
		// TODO Auto-generated method stub
		try {
			em.getTransaction().begin();
			em.persist(item);
			em.getTransaction().commit();
		} catch (Exception exception) {
			em.getTransaction().rollback();
			exception.printStackTrace();
		}
		
	}

	@Override
	public boolean deleteById(Favorite favourite) {
		// TODO : Xóa ngừời dùng
				try {
					em.getTransaction().begin();
					em.remove(favourite);
					em.getTransaction().commit();
				} catch (Exception exception) {
					em.getTransaction().rollback();
					exception.printStackTrace();
					return false;
				}
				return true;
	}
	
	public boolean deleteByIdUsedAndIdVideo(String userId,String videoId) {
		// TODO : Xóa ngừời dùng
				try {
					em.getTransaction().begin();
					String jpql = "DELETE FROM Favorite f WHERE f.user.id = :userId AND f.video.id = :videoId";
			        em.createQuery(jpql).setParameter("userId", userId).setParameter("videoId", videoId).executeUpdate();
					em.getTransaction().commit();
				} catch (Exception exception) {
					em.getTransaction().rollback();
					exception.printStackTrace();
					return false;
				}
				return true;
	}

	@Override
	public List<Favorite> getAllItems() {
		// TODO Auto-generated method stub
		List<Favorite> list = null;
		try {
			em.getTransaction().begin();
			String jpql = "SELECT o FROM Favorite o";
			TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
			list = query.getResultList();
			em.getTransaction().commit();
		} catch (Exception exception) {
			em.getTransaction().rollback();
			exception.printStackTrace();
			return list;
		}
		return list;
	}

	@Override
	public Favorite getItemById(String id) {
		Favorite favorite = null;
			try {
				em.getTransaction().begin();
				favorite= em.find(Favorite.class, id);
				em.getTransaction().commit();
			} catch (Exception exception) {
				em.getTransaction().rollback();
				exception.printStackTrace();
				return  favorite;
			}
			return favorite;
	}
	
	public List<Favorite> getItemByUserId(User User) {
		List<Favorite> favorites = null;
			try {
				em.getTransaction().begin();
				String jpql = "SELECT o FROM Favorite o WHERE o.user = :id";
				TypedQuery<Favorite> query = em.createQuery(jpql, Favorite.class);
				query.setParameter("id", User);
				favorites=query.getResultList();
				em.getTransaction().commit();
			} catch (Exception exception) {
				em.getTransaction().rollback();
				exception.printStackTrace();
				return  null;
			}
			return favorites;
	}
	
	

}
