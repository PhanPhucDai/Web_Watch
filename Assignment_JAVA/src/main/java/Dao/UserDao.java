package Dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import Bean.User;
import Interface.InterfaceDao;
import untils.XJPA;

public class UserDao implements InterfaceDao<User> {
	EntityManager em = XJPA.getEntityManager();

	public User getUserByAccount(String nameAccount, String passwordAccount) {
		// TODO : Kiểm tra tài khoản và mật khẩu
		User user = null;
		try {
			em.getTransaction().begin();
			String sql = "SELECT o FROM User o WHERE o.id = :id AND o.password = :password";
			TypedQuery<User> excuteQuery = em.createQuery(sql, User.class);
			excuteQuery.setParameter("id", nameAccount);
			excuteQuery.setParameter("password", passwordAccount);
			user = excuteQuery.getSingleResult();
			em.getTransaction().commit();
		} catch (Exception exception) {
			em.getTransaction().rollback();
			exception.printStackTrace();
		}
		return user;
	}

	@Override
	public void addItems(User item) {
		// TODO : Thêm ngừời dùng
		try {
			em.getTransaction().begin();
			em.persist(item);
			em.getTransaction().commit();
		} catch (Exception exception) {
			em.getTransaction().rollback();
			exception.printStackTrace();
		}
	}
		public User update(User entity) {
			try {
				em.getTransaction().begin();
				em.merge(entity);
				em.getTransaction().commit();
				return entity;
			}catch (Exception e) {
				em.getTransaction().rollback();
				throw new RuntimeException(e);
			}
		}

	

	@Override
	public boolean deleteById(User user) {
		// TODO : Xóa ngừời dùng
		try {
			em.getTransaction().begin();
			em.remove(user);
			em.getTransaction().commit();
		} catch (Exception exception) {
			em.getTransaction().rollback();
			exception.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<User> getAllItems() {
		//TODO : Lấy tất cả người dùng
		List<User> list = null;
		try {
			em.getTransaction().begin();
			String jpql = "SELECT o FROM User o";
			TypedQuery<User> query = em.createQuery(jpql, User.class);
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
	public User getItemById(String id) {
		 User user = null;
		try {
			em.getTransaction().begin();
			user= em.find(User.class, id);
			em.getTransaction().commit();
		} catch (Exception exception) {
			em.getTransaction().rollback();
			exception.printStackTrace();
			return  user;
		}
		return user;

	}

}
