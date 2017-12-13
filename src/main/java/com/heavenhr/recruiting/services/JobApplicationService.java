package com.heavenhr.recruiting.services;

import org.springframework.stereotype.Service;

import com.heavenhr.recruiting.vo.Application;
import com.heavenhr.recruiting.vo.Offer;

/**
 * @author Vinaya Nayak
 * @date 09-Dec-2017
 * JobApplicationService.java
 */
@Service
public interface JobApplicationService {
	
	public Application createApplication(Application application);
	
	public Application updateStatus(Application application, Long id);
	
	public Application findbyId(Long id);
	
	public boolean isDuplicateEmail(Application application);

}
