package com.heavenhr.recruiting.entity;

/**
 * @author Vinaya Nayak
 * @date 10-Dec-2017 ApplicationEntity.java
 */
public class ApplicationEntity {

	private Long id;
	private String emailId;
	private String resumeText;
	private String applcationStatus;
	private OfferEntity offer;

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

	public OfferEntity getOffer() {
		return offer;
	}

	public void setOffer(OfferEntity offer) {
		this.offer = offer;
	}
}
