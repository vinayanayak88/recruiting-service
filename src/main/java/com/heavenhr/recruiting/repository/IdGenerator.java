/**
 * 
 */
package com.heavenhr.recruiting.repository;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Component;

/**
 * @author Vinaya Nayak
 * @date 09-Dec-2017
 * IdGenerator.java
 */
@Component
public class IdGenerator {
	
	private AtomicLong nextId = new AtomicLong(1);
	
	public long getNextId() {
		return nextId.getAndIncrement();
	}

}
