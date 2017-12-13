package com.heavenhr.recruiting.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinaya Nayak
 * @date 10-Dec-2017 OfferEntity.java
 */
public class OfferEntity {

	private Long id;
	private String jobTitle;
	private String startDate;
	private List<ApplicationEntity> applicationList = new ArrayList<ApplicationEntity>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public List<ApplicationEntity> getApplicationList() {
		return applicationList;
	}

	public void setApplicationList(List<ApplicationEntity> applicationList) {
		this.applicationList = applicationList;
	}
}
