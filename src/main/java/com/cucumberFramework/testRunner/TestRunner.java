package com.cucumberFramework.testRunner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(features = "src/test/resources/features/login/login_logout.feature", glue = {
		"com/cucumberFramework/stepdefinitions" }, plugin = { "pretty", "html:target/cucumber-reports/cucumber-pretty",
				"json:target/cucumber-reports/CucumberTestReport.json", "rerun:target/cucumber-reports/rerun.txt",
		/*
		 * "com.cucumber.listener.ExtentCucumberFormatter:target/html/ExtentReport.html"
		 */ }, monochrome = true, dryRun = false)
public class TestRunner {

	private TestNGCucumberRunner testNGCucumberRunner;

	@BeforeClass(alwaysRun = true)
	public void setUpClass() {
		testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(groups = "cucumber", description = "Runs cucmber Features", dataProvider = "features")
	public void feature(CucumberFeatureWrapper cucumberFeature) {
		testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());
	}

	@DataProvider
	public Object[][] features() {
		return testNGCucumberRunner.provideFeatures();
	}

	@AfterClass(alwaysRun = true)
	public void testDownClass() {

//		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
//		// Reporter.setSystemInfo("Test User", System.getProperty("user.name"));
//		Reporter.setSystemInfo("User Name", "AJ");
//		Reporter.setSystemInfo("Application Name", "Test App ");
//		Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
//		Reporter.setSystemInfo("Environment", "Production");
//		Reporter.setTestRunnerOutput("Test Execution Cucumber Report");

		testNGCucumberRunner.finish();

	}

}
