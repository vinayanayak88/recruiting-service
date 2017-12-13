/**
 * 
 */
package com.heavenhr.recruiting.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.heavenhr.recruiting.entity.ApplicationEntity;
import com.heavenhr.recruiting.entity.OfferEntity;

/**
 * @author Vinaya Nayak
 * @date 09-Dec-2017 JobOfferRepository.java
 */
@Repository
public class JobOfferRepository implements BaseRepository<OfferEntity> {

	@Autowired
	private IdGenerator idGenerator;

	private Map<Long, OfferEntity> offers = Collections.synchronizedMap(new HashMap<Long, OfferEntity>());

	@Override
	public OfferEntity create(OfferEntity entity) {
		long id = idGenerator.getNextId();
		offers.put(id, entity);
		entity.setId(id);
		return entity;
	}

	@Override
	public List<OfferEntity> findAll() {
		List<OfferEntity> offersList = new ArrayList<OfferEntity>(offers.values());
		return offersList;
	}

	@Override
	public OfferEntity findById(Long id) {
		OfferEntity offer = offers.get(id);
		return offer;
	}

	@Override
	public int getCount() {
		return offers.size();
	}

	public List<ApplicationEntity> getAllApplicationPerOffer(Long id) {
		OfferEntity entity = offers.get(id);
		List<ApplicationEntity> applicationList = entity.getApplicationList();
		return applicationList;
	}

	public Map<Long, OfferEntity> getOffers() {
		return offers;
	}

	public void setOffers(Map<Long, OfferEntity> offers) {
		this.offers = offers;
	}

	@Override
	public void clear() {
		offers.clear();
	}

}
