/**
 * 
 */
package com.heavenhr.recruiting.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * @author Vinaya Nayak
 * @date 08-Dec-2017
 * BaseRepository.java
 */
@Repository
public interface BaseRepository<T> {
	
	public T create(T element) ;
	
	public List<T> findAll() ;
	
	public T findById(Long id) ;
	
	public int getCount();
	
	public void clear();

}
