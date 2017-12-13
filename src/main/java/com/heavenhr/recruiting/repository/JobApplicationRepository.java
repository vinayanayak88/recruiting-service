package com.heavenhr.recruiting.repository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.heavenhr.recruiting.entity.ApplicationEntity;

/**
 * @author Vinaya Nayak
 * @date 10-Dec-2017 JobApplicationRepository.java
 */
@Repository
public class JobApplicationRepository implements BaseRepository<ApplicationEntity> {

	@Autowired
	private IdGenerator idGenerator;

	private Map<Long, ApplicationEntity> applications = Collections
			.synchronizedMap(new HashMap<Long, ApplicationEntity>());

	@Override
	public ApplicationEntity create(ApplicationEntity entity) {
		long id = idGenerator.getNextId();
		applications.put(id, entity);
		entity.setId(id);
		return entity;
	}

	@Override
	public List<ApplicationEntity> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ApplicationEntity findById(Long id) {
		ApplicationEntity application = applications.get(id);
		return application;

	}

	@Override
	public int getCount() {
		return applications.size();
	}

	public Map<Long, ApplicationEntity> getApplications() {
		return applications;
	}

	public void setApplications(Map<Long, ApplicationEntity> applications) {
		this.applications = applications;
	}

	@Override
	public void clear() {
		applications.clear();
	}

}
