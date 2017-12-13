package com.heavenhr.recruiting.test.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.ResultActions;

import com.heavenhr.recruiting.controller.App;
import com.heavenhr.recruiting.repository.JobOfferRepository;
import com.heavenhr.recruiting.services.JobOfferService;
import com.heavenhr.recruiting.test.controller.util.OfferControllerTestUtil;
import com.heavenhr.recruiting.test.util.JobOfferTestUtil;
import com.heavenhr.recruiting.vo.Offer;

/**
 * @author Vinaya Nayak
 * @date 13-Dec-2017
 * JobOfferControllerTest.java
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class JobOfferControllerTest extends ControllerIntegrationTest{
	
	private static final String TEST_OFFER = "{\"jobTitle\": \"Scala Developer\", \"startDate\": \"19/09/2017\"}";

	@Autowired
	JobOfferRepository repository;
	
	@Autowired
	JobOfferService service;
	
	@Before
	public void setUp() {
		repository.clear();
	}
	
	@Test
    public void testGetAllEmptyListEnsureCorrectResponse() throws Exception {
    	assertNoOffers();
    	getOffer()
        	.andExpect(status().isOk())
            .andExpect(content().string(equalTo("[]")));
    }
	
	private ResultActions getOffer() throws Exception {
		return get("/offer");
	}
    
    private void assertNoOffers() {
    	assertOfferCountIs(0);
    }
    
    private void assertOfferCountIs(int count) {
    	Assert.assertEquals(count, repository.getCount());
    }
    
    @Test
    public void testGetAllOneOfferEnsureCorrectResponse() throws Exception {
    	Offer injectedOffer = injectOffer();
    	assertOfferCountIs(1);
        getOffer()
        	.andExpect(status().isOk())
        .andExpect(OfferControllerTestUtil.offerAtIndexIsCorrect(0, injectedOffer));
    }
    
    private Offer injectOffer() {
    	Offer offer = new Offer();
    	offer.setJobTitle("Java Developer");
    	offer.setStartDate("10/10/2017");
    	return service.createOffer(offer);
    }
    
    @Test
    public void testGetNonexistentOfferEnsureNotFoundResponse() throws Exception {
    	assertNoOffers();
        getOffer(200)
        	.andExpect(status().isNotFound());
    }

	private ResultActions getOffer(long id) throws Exception {
		return get("/offer/{id}", id);
	}

	@Test
    public void testGetExistingOfferEnsureCorrectResponse() throws Exception {
		Offer injectedOffer = injectOffer();
    	assertOfferCountIs(1);
        getOffer(injectedOffer.getId())
        	.andExpect(status().isOk())
        	.andExpect(OfferControllerTestUtil.offerIsCorrect(injectedOffer));
    }
	
	@Test
    public void testCreateNewOfferEnsureOfferCreated() throws Exception {
		assertNoOffers();
		Offer desiredOffer = JobOfferTestUtil.generateTestOffer();
    	createOffer(toJsonString(desiredOffer));
    	assertOfferCountIs(1);
    	JobOfferTestUtil.assertAllButIdsMatchBetweenOffers(desiredOffer, getCreatedOffer());
    }
    
    private ResultActions createOffer(String payload) throws Exception {
    	return post("/offer", payload);
    }

	private Offer getCreatedOffer() {
		Offer offer = service.findbyId((long) 2);
		return offer;
	}
	
	@Test
    public void testCreateNewOfferEnsureCorrectResponse() throws Exception {
    	assertNoOffers();
    	createOffer(TEST_OFFER)
		.andExpect(status().isCreated());
    }
}
