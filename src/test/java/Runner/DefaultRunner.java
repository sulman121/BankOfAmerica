package Runner;

import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.PickleEventWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

@CucumberOptions(

		features = "/Users/Sulman/eclipse-workspace/BankOfAmerica/src/test/resources/Features", 
		glue = "Steps", dryRun = false, monochrome=true
		
		,plugin = {
				"pretty", "html:target/cucumber-reports/Cucumber-pretty",
				"json:target/cucumber-reports/Cucumbertestreports.json" }

)

public class DefaultRunner {

	private TestNGCucumberRunner testngcucumberrunner;

	@BeforeClass(alwaysRun = true)
	public void SetUpClass() throws Exception {

		testngcucumberrunner = new TestNGCucumberRunner(this.getClass());
	}

	@Test(dataProvider = "features")
	public void feature(PickleEventWrapper eventwrapper, CucumberFeatureWrapper cucumberFeature) throws Throwable {

		testngcucumberrunner.runScenario(eventwrapper.getPickleEvent());
		
	}

	@DataProvider(parallel =true)
	public Object[][] features() {
		return testngcucumberrunner.provideScenarios();

	}

	@AfterClass(alwaysRun = true)
	public void teardown() {
		testngcucumberrunner.finish();
	}
	

}