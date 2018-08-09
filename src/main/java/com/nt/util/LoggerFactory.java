package com.nt.util;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerFactory extends Logger {
	protected LoggerFactory(String name) {
		super(name);
		PropertyConfigurator.configure("log4j.properties");
	}

}
