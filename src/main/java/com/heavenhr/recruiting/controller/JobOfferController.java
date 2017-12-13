
package com.heavenhr.recruiting.controller;

import java.util.Collection;
import java.util.List;

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
import com.heavenhr.recruiting.services.JobOfferService;
import com.heavenhr.recruiting.util.RestReponse;
import com.heavenhr.recruiting.vo.Application;
import com.heavenhr.recruiting.vo.Offer;

/**
 * @author Vinaya Nayak
 * @date 08-Dec-2017 JobOfferController.java
 */
@RestController
@RequestMapping(value = "/offer", produces = "application/json")
public class JobOfferController {

	private static final Logger log = LoggerFactory.getLogger(JobOfferController.class);

	@Autowired
	private JobOfferService jobOfferService;

	@RequestMapping(method = RequestMethod.POST, consumes = "application/json")
	@ResponseBody
	public ResponseEntity<RestReponse> createOffer(@RequestBody Offer offer) {
		boolean value = jobOfferService.isDuplicateOffer(offer);
		RestReponse response = new RestReponse();
		HttpStatus status = null;
		if (!value) {
			Offer createdOffer = jobOfferService.createOffer(offer);
			if (null != createdOffer) {
				response.setStatusMessage(Constant.SUCCESS);
				response.setData(createdOffer);
				status = HttpStatus.CREATED;
			}
		} else {
			response.setStatusMessage(Constant.DUPLICATE);
			response.setData("");
			status = HttpStatus.CONFLICT;
		}
		
		return new ResponseEntity<>(response, status);
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Collection<Offer>> findAllOffers() {
		List<Offer> offers = jobOfferService.findAllOffers();
		if (null != offers) {
			return new ResponseEntity<>(offers, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Offer> findOfferById(@PathVariable Long id) {
		Offer offer = jobOfferService.findbyId(id);

		if (null != offer) {
			return new ResponseEntity<>(offer, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}/application", method = RequestMethod.GET)
	public ResponseEntity<Collection<Application>> listAllApplicationsPerOffer(@PathVariable Long id) {
		List<Application> appList = jobOfferService.listAllApplicationsPerOffer(id);
		if (null != appList) {
			return new ResponseEntity<>(appList, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}/application/{appid}", method = RequestMethod.GET)
	public ResponseEntity<Application> getAplicationPerOffer(@PathVariable("appid") Long appId,
			@PathVariable("id") Long id) {
		Application app = jobOfferService.getAplicationPerOffer(id, appId);
		if (null != app) {
			return new ResponseEntity<>(app, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
