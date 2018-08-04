package com.nt.util;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public final class HibernateUtil implements Serializable,Cloneable{
	private HibernateUtil(){}
	
	private static volatile SessionFactory factory;
	public static SessionFactory getSessionFactory()
	{
		
		if(factory==null) {
			synchronized (SessionFactory.class) {
				if(factory==null)
				{
					Configuration config=new Configuration();
				config.configure("com/nt/config/hibernate.cfg.xml");
				 factory=config.buildSessionFactory();	
				}
			}
		}
		return factory;
	}
	
	protected Object readResolve()
	{
		return factory;
	}
	
	protected Object clone()throws CloneNotSupportedException
	{
		throw new CloneNotSupportedException();
	}
}
