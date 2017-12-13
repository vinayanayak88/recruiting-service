package com.heavenhr.recruiting.util;

/**
 * @author Vinaya Nayak
 * @date 12-Dec-2017
 * RestReponse.java
 */
public class RestReponse {
	
	private String statusMessage;
	private Object data;

	public String getStatusMessage() {
		return statusMessage;
	}

	public void setStatusMessage(String statusMessage) {
		this.statusMessage = statusMessage;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
