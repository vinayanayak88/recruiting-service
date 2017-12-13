package com.heavenhr.recruiting.test.util;

import org.junit.Assert;

import com.heavenhr.recruiting.vo.Offer;

/**
 * @author Vinaya Nayak
 * @date 13-Dec-2017
 * JobOfferTestUtil.java
 */
public class JobOfferTestUtil {
	
	public static void assertAllButIdsMatchBetweenOffers(Offer expected, Offer actual) {
    	Assert.assertEquals(expected.getJobTitle(), actual.getJobTitle());
    	Assert.assertEquals(expected.getStartDate(), actual.getStartDate());
    }
	
    public static Offer generateTestOffer() {
    	Offer offer = new Offer();
    offer.setJobTitle("Python Developer");
    offer.setStartDate("10/12/2017");
    	return offer;
    }
}
