
package com.heavenhr.recruiting.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.heavenhr.recruiting.vo.Application;
import com.heavenhr.recruiting.vo.Offer;

/**
 * @author Vinaya Nayak
 * @date 08-Dec-2017 JobOfferService.java
 */
@Service
public interface JobOfferService {

	public Offer createOffer(Offer vo);
	
	public List<Offer> findAllOffers();

	public Offer findbyId(Long id);
	
	public List<Application> listAllApplicationsPerOffer(Long id);
	
	public boolean isDuplicateOffer(Offer offer);
	
	public Application getAplicationPerOffer(Long id, Long appId);
}
