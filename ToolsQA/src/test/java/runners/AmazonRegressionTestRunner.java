package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/test-resources/features",
    glue = {"step_definations_bdd"},
    plugin = {
        "pretty",
        "html:report/regression-reports.html",
        "json:report/regression-reports.json"
    },
    tags = "@Amazon and @RegressionTest",
    monochrome = true,
    dryRun = false
)
public class AmazonRegressionTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}