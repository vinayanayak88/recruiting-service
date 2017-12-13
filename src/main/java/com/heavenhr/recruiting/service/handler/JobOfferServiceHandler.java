/**
 * 
 */
package com.heavenhr.recruiting.service.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruiting.entity.ApplicationEntity;
import com.heavenhr.recruiting.entity.OfferEntity;
import com.heavenhr.recruiting.repository.JobApplicationRepository;
import com.heavenhr.recruiting.repository.JobOfferRepository;
import com.heavenhr.recruiting.services.JobOfferService;
import com.heavenhr.recruiting.util.ServiceUtil;
import com.heavenhr.recruiting.vo.Application;
import com.heavenhr.recruiting.vo.Offer;

/** Service Handler for creating and retrieving offers
 * @author Vinaya Nayak
 * @date 08-Dec-2017 JobOfferServiceHandler.java
 */
@Service
public class JobOfferServiceHandler implements JobOfferService {

	private static final Logger log = LoggerFactory.getLogger(JobOfferServiceHandler.class);

	@Autowired
	JobOfferRepository repository;

	@Autowired
	JobApplicationRepository applicationRepository;

	@Autowired
	ServiceUtil util;

	@Override
	public Offer createOffer(Offer offer) {
		OfferEntity offerEntity = new OfferEntity();
		offerEntity.setJobTitle(offer.getJobTitle());
		offerEntity.setStartDate(offer.getStartDate());
		OfferEntity createdOffer = repository.create(offerEntity);
		Offer response = util.buildOfferReponse(createdOffer);
		return response;
	}

	/**
	 * This method checks if the offer with given job title already exists
	 * 
	 * @param offer
	 * @return
	 */
	public boolean isDuplicateOffer(Offer offer) {
		Map<Long, OfferEntity> offersMap = repository.getOffers();
		boolean value = offersMap.entrySet().stream()
				.anyMatch(map -> map.getValue().getJobTitle().equals(offer.getJobTitle()));
		return value;
	}

	@Override
	public List<Offer> findAllOffers() {
		List<OfferEntity> offers = repository.findAll();
		List<Offer> offerList = new ArrayList<Offer>();
		for (OfferEntity entity : offers) {
			Offer offer = util.buildOfferReponse(entity);
			offerList.add(offer);
		}
		return offerList;
	}

	@Override
	public Offer findbyId(Long id) {
		OfferEntity offer = repository.findById(id);
		Offer response = null;
		if (null != offer) {
			response = util.buildOfferReponse(offer);
		}
		return response;
	}

	@Override
	public List<Application> listAllApplicationsPerOffer(Long id) {
		List<ApplicationEntity> appList = repository.getAllApplicationPerOffer(id);
		List<Application> applications = new ArrayList<Application>();
		for (ApplicationEntity entity : appList) {
			Application app = util.buildApplicationResponse(entity);
			applications.add(app);
		}
		return applications;
	}

	@Override
	public Application getAplicationPerOffer(Long id, Long appId) {
		List<ApplicationEntity> appList = repository.getAllApplicationPerOffer(id);
		Application app = null;
		for (ApplicationEntity entity : appList) {
			if (entity.getId() == appId) {
				app = util.buildApplicationResponse(entity);
			}
		}
		return app;
	}
}
