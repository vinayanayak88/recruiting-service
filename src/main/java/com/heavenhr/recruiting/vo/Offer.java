/**
 * 
 */
package com.heavenhr.recruiting.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vinaya Nayak
 * @date 08-Dec-2017 Offer.java
 */
public class Offer {

	private Long id;
	private String jobTitle;
	private String startDate;
	private int numberOfApplications;
	private List<Long> applicationIds = new ArrayList<Long>();

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

	public int getNumberOfApplications() {
		return numberOfApplications;
	}

	public void setNumberOfApplications(int numberOfApplications) {
		this.numberOfApplications = numberOfApplications;
	}

	public List<Long> getApplicationIds() {
		return applicationIds;
	}

	public void setApplicationIds(List<Long> applicationIds) {
		this.applicationIds = applicationIds;
	}
}
