package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/test-resources/features",
    glue = {"step_definations_bdd"},
    plugin = {
        "pretty",
        "html:report/smoke-reports.html",
        "json:report/smoke-reports.json"
    },
    tags = "@Amazon and @SmokeTest",
    monochrome = true,
    dryRun = false
)
public class AmazonSmokeTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}