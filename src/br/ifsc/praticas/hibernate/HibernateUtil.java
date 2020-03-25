package br.ifsc.praticas.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateUtil {

	private SessionFactory sessionFactory;
	private StandardServiceRegistry registry;
	private static HibernateUtil HIBERNATE;

	private HibernateUtil() {

	}

	public static HibernateUtil getInstance() {
		if (HIBERNATE == null) {
			HIBERNATE = new HibernateUtil();
		}

		return HIBERNATE;
	}

	private SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			registry = new StandardServiceRegistryBuilder().configure().build();
			try {

				sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
			} catch (Exception e) {
				StandardServiceRegistryBuilder.destroy(registry);
			}
		}
		return sessionFactory;
	}

	public Session getSession() {
		return this.getSessionFactory().openSession();
	}

}
