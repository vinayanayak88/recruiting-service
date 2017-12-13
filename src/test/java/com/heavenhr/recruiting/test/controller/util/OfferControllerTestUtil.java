package com.heavenhr.recruiting.test.controller.util;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.springframework.test.web.servlet.ResultMatcher;

import com.heavenhr.recruiting.vo.Application;
import com.heavenhr.recruiting.vo.Offer;

/**
 * @author Vinaya Nayak
 * @date 13-Dec-2017 OfferControllerTestUtil.java
 */
public class OfferControllerTestUtil {

	public static ResultMatcher offerAtIndexIsCorrect(int index, Offer expected) {
		return new CompositeResultMatcher().addMatcher(jsonPath("$.[" + index + "].id").value(expected.getId()))
				.addMatcher(jsonPath("$.[" + index + "].jobTitle").value(expected.getJobTitle()))
				.addMatcher(jsonPath("$.[" + index + "].startDate").value(expected.getStartDate())).addMatcher(
						jsonPath("$.[" + index + "].numberOfApplications").value(expected.getNumberOfApplications()));
	}

	public static ResultMatcher offerIsCorrect(Offer expected) {
		return offerIsCorrect(expected.getId(), expected);
	}

	private static ResultMatcher offerIsCorrect(Long expectedId, Offer expected) {
		return new CompositeResultMatcher().addMatcher(jsonPath("$.id").value(expectedId))
				.addMatcher(jsonPath("$.jobTitle").value(expected.getJobTitle()))
				.addMatcher(jsonPath("$.startDate").value(expected.getStartDate()))
				.addMatcher(jsonPath("$.numberOfApplications").value(expected.getNumberOfApplications()));
	}

}
