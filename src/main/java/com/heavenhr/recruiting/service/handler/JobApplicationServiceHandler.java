package com.heavenhr.recruiting.service.handler;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruiting.entity.ApplicationEntity;
import com.heavenhr.recruiting.entity.OfferEntity;
import com.heavenhr.recruiting.repository.JobApplicationRepository;
import com.heavenhr.recruiting.repository.JobOfferRepository;
import com.heavenhr.recruiting.services.JobApplicationService;
import com.heavenhr.recruiting.util.ServiceUtil;
import com.heavenhr.recruiting.vo.Application;

/** Service Handler for creating and retrieving application
 * @author Vinaya Nayak
 * @date 09-Dec-2017 JobApplicationServiceHandler.java
 */
@Service
public class JobApplicationServiceHandler implements JobApplicationService {

	private static final Logger log = LoggerFactory.getLogger(JobApplicationServiceHandler.class);

	@Autowired
	JobApplicationRepository repository;
	@Autowired
	JobOfferRepository offerRepository;
	@Autowired
	ServiceUtil util;

	@Override
	public Application createApplication(Application application) {
		ApplicationEntity createdEntity = saveApplication(application);
		Application appResponse = null;
		if(null != createdEntity) {
			 appResponse = util.buildApplicationResponse(createdEntity);
		}
		return appResponse;
	}

	private ApplicationEntity saveApplication(Application application) {
		ApplicationEntity entity = new ApplicationEntity();
		Map<Long, OfferEntity> offersList = offerRepository.getOffers();
		List<OfferEntity> offer = offersList.entrySet().stream().filter(map -> map.getKey() == application.getOfferId())
				.map(map -> map.getValue()).collect(Collectors.toList());
		OfferEntity offerEntity = new OfferEntity();
		offerEntity.setId(offer.get(0).getId());
		offerEntity.setJobTitle(offer.get(0).getJobTitle());
		offerEntity.setStartDate(offer.get(0).getStartDate());
		entity.setOffer(offerEntity);
		entity.setEmailId(application.getEmailId());
		entity.setApplcationStatus(
				null != application.getApplcationStatus() ? application.getApplcationStatus() : "APPLIED");
		entity.setResumeText(application.getResumeText());
		entity = repository.create(entity);
		offer.get(0).getApplicationList().add(entity);
		offerRepository.getOffers().put(offer.get(0).getId(), offer.get(0));
		ApplicationEntity createdEntity = repository.getApplications().get(entity.getId());
		return createdEntity;
	}

	@Override
	public Application updateStatus(Application application, Long id) {
		ApplicationEntity app = repository.getApplications().get(id);
		if (null != app) {
			app.setApplcationStatus(application.getApplcationStatus());
		}
		Application appResponse = util.buildApplicationResponse(app);
		return appResponse;
	}

	@Override
	public Application findbyId(Long id) {
		ApplicationEntity entity = repository.findById(id);
		Application response = null;
		if (null != entity) {
			response = util.buildApplicationResponse(entity);
		}
		return response;
	}

	@Override
	public boolean isDuplicateEmail(Application application) {
		Map<Long, ApplicationEntity> applicationMap = repository.getApplications();
		boolean value = applicationMap.entrySet().stream()
				.anyMatch(map -> map.getValue().getEmailId().equals(application.getEmailId()));
		return value;
	}
}
