package com.dxc.mts.enums;

/**
 * 
 * @author mkhan339
 *
 */
public enum BaseAppConstants {

	LOGIN("LOGIN");

	private String value;

	BaseAppConstants(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}