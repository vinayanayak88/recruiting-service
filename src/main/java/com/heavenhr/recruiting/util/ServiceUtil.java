package com.heavenhr.recruiting.util;

import java.util.ArrayList;

import org.springframework.stereotype.Component;

import com.heavenhr.recruiting.entity.ApplicationEntity;
import com.heavenhr.recruiting.entity.OfferEntity;
import com.heavenhr.recruiting.vo.Application;
import com.heavenhr.recruiting.vo.Offer;

/**
 * @author Vinaya Nayak
 * @date 12-Dec-2017
 * ServiceUtil.java
 */
@Component
public class ServiceUtil {
	
	public Application buildApplicationResponse(ApplicationEntity app) {
		Application response = new Application();
		response.setId(app.getId());
		response.setApplcationStatus(app.getApplcationStatus());
		response.setEmailId(app.getEmailId());
		response.setOfferId(app.getOffer().getId());
		response.setResumeText(app.getResumeText());
		return response;
	}
	
	public Offer buildOfferReponse(OfferEntity offer) {
		Offer response = new Offer();
		response.setId(offer.getId());
		response.setJobTitle(offer.getJobTitle());
		response.setStartDate(offer.getStartDate());
		response.setNumberOfApplications(null != offer.getApplicationList() ? offer.getApplicationList().size() : 0);
		if(null != offer.getApplicationList()) {
			offer.getApplicationList().forEach(item -> {
			response.getApplicationIds().add(item.getId());
		});}
		else {
			response.setApplicationIds(new ArrayList<Long>());
		}
		return response;
	}
}
