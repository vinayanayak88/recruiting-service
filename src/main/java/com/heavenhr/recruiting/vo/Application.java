package com.heavenhr.recruiting.vo;

/**
 * @author Vinaya Nayak
 * @date 09-Dec-2017 Application.java
 */
public class Application {

	private Long id;
	private String emailId;
	private String resumeText;
	private String applcationStatus;
	private Long offerId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getResumeText() {
		return resumeText;
	}

	public void setResumeText(String resumeText) {
		this.resumeText = resumeText;
	}

	public String getApplcationStatus() {
		return applcationStatus;
	}

	public void setApplcationStatus(String applcationStatus) {
		this.applcationStatus = applcationStatus;
	}

	public Long getOfferId() {
		return offerId;
	}

	public void setOfferId(Long offerId) {
		this.offerId = offerId;
	}
}
