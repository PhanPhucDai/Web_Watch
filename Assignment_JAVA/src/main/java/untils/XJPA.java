package untils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class XJPA {
	private static EntityManagerFactory factory;
	static public EntityManager getEntityManager() {
		if(factory == null || !factory.isOpen()) {
			factory = Persistence.createEntityManagerFactory("PolyOE");
		}
		return factory.createEntityManager();
	}
	static public void shutdown() {
		if(factory!=null && factory.isOpen()) {
			factory.close();
		}
		factory=null;
	}

}
