package com.jr.util;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil implements Serializable, Cloneable {
	private HibernateUtil() {
	}

	private static volatile SessionFactory factory;

	public static SessionFactory getSessionFactory() {

		if (HibernateUtil.factory == null) synchronized (SessionFactory.class) {
			if (HibernateUtil.factory == null) {
				Configuration config = new Configuration();
				config.configure("com/jr/config/hibernate.cfg.xml");
				HibernateUtil.factory = config.buildSessionFactory();
			}
		}
		return HibernateUtil.factory;
	}

	protected Object readResolve() {
		return HibernateUtil.factory;
	}

	@Override
	protected Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
