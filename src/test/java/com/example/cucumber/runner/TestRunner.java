package com.example.cucumber.runner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

/**
 * Test Runner class for Cucumber which can be used to generate Step Definition method stubs from
 * the Feature files as well as run the Cucumber Test Suite
 * 
 * For details on Cucumber Options @see
 * https://testingneeds.wordpress.com/2015/09/15/junit-runner-with-cucumberoptions/
 * 
 * @author michaere
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/gherkin/features", glue = {"com.example.cucumber.stepdefinitions"},
        plugin = {"pretty", "html:target/reports"}, monochrome = true, strict = false, dryRun = false)
public class TestRunner {

}
