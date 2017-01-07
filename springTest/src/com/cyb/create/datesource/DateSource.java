package com.cyb.create.datesource;

import java.util.Properties;

public class DateSource {
	
	private Properties propertis;

	public Properties getPropertis() {
		return propertis;
	}

	public void setPropertis(Properties propertis) {
		this.propertis = propertis;
	}

	@Override
	public String toString() {
		return "DateSource [propertis=" + propertis + "]";
	}
}
