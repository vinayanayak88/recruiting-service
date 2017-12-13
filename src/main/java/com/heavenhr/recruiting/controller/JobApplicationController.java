
package com.heavenhr.recruiting.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruiting.constant.Constant;
import com.heavenhr.recruiting.services.JobApplicationService;
import com.heavenhr.recruiting.util.RestReponse;
import com.heavenhr.recruiting.vo.Application;

/**
 * @author Vinaya Nayak
 * @date 09-Dec-2017
 * JobApplicationController.java
 */
@RestController
@RequestMapping(value = "/application", produces = "application/json")
public class JobApplicationController {
	
	private static final Logger log = LoggerFactory.getLogger(JobApplicationController.class);
	
	@Autowired
	JobApplicationService jobApplicationService;
	
	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<RestReponse> createApplication(@RequestBody Application application) {
		boolean isDuplicate = jobApplicationService.isDuplicateEmail(application);
		RestReponse response = new RestReponse();
		HttpStatus status  = null;
		
		if(!isDuplicate) {
			Application createdApplication = jobApplicationService.createApplication(application);
			if(null != createdApplication) {
				response.setStatusMessage(Constant.SUCCESS);
				response.setData(createdApplication);
				status = HttpStatus.CREATED;
			}
		} else {
			response.setStatusMessage(Constant.EMAILIDEXISTS);
			response.setData("");
			status = HttpStatus.CONFLICT;
		}
		
		return new ResponseEntity<>(response, status);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PATCH, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<Application> updateApplicationStatus(@RequestBody Application application, @PathVariable Long id) {
		Application updatedApplication = jobApplicationService.updateStatus(application ,id);
		return new ResponseEntity<>(updatedApplication, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Application> findApplicationById(@PathVariable Long id) {
		Application application = jobApplicationService.findbyId(id);

		if (null != application) {
			return new ResponseEntity<>(application, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
}
