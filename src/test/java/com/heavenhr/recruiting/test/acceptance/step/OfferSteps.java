package com.heavenhr.recruiting.test.acceptance.step;

import java.util.Map;

import org.junit.Assert;

import com.fasterxml.jackson.core.type.TypeReference;
import com.heavenhr.recruiting.test.acceptance.util.AbstractSteps;
import com.heavenhr.recruiting.vo.Offer;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class OfferSteps extends AbstractSteps {
	
	//private static final String TEST_OFFER = "{\"description\": \"some test order\", \"lineItems\": [{\"name\": \"test item 1\", \"description\": \"some test item 1\", \"costInCents\": 100}, {\"name\": \"test item 2\", \"description\": \"some test item 2\", \"costInCents\": 200}]}";
	private static final TypeReference<Map<String, Object>> RESOURCE_TYPE = new TypeReference<Map<String, Object>>() {};
	private static final TypeReference<Map<String, Object>> RESOURCE_TYPE1 = new TypeReference<Map<String, Object>>() {};
	private static final String TEST_OFFER = "{\"jobTitle\": \"Scala Developer\", \"startDate\": \"19/09/2017\"}";
	
	@Given("^an offer exists$")
	public void anOfferExists() throws Throwable {
		createOffer();
	}
	
	private void createOffer() throws Exception {
		post("/offer", TEST_OFFER);
	}
	
//	@When("^the user creates an offer$")
//	public void theUserCallsGetOffers() throws Throwable {
//		createOffer();
//	}
//	
	private Object getCreatedId() throws Exception {
		return getLastPostContentAs(RESOURCE_TYPE).get("id");
	}
//	
//	@And("^the offer is successfully created$")
//	public void theOfferIsSuccessfullyCreated() {
//		 Assert.assertEquals(201, getLastPostResponse().getStatus());
//	}
	
	@And("^the user gets the created offer$")
	public void theUserRetrievesTheOffer() throws Throwable {
		get("/offer/{id}", getCreatedId());
	}
	
	@Then("^the user receives status code of (\\d+)$")
	public void theUserReceivesStatusCodeOf(int statusCode) throws Throwable {
        Assert.assertEquals(statusCode, getLastStatusCode());
	}
	
	@And("^the retrieved offer is correct$")
	public void theRetrievedOfferIsCorrect() throws Throwable {
		assertOfferResourcesMatch(200, getLastStatusCode());
	}
	
	private static void assertOfferResourcesMatch(int expected, int actual) {
		Assert.assertEquals(expected,actual);
	}
}
