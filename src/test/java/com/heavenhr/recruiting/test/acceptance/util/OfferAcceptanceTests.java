package com.heavenhr.recruiting.test.acceptance.util;

import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/acceptance/offer.feature", glue = {"com.heavenhr.recruiting.test.acceptance.step"})
@WebAppConfiguration
public class OfferAcceptanceTests {

}
