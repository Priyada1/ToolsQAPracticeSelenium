package runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.DataProvider;

@CucumberOptions(
    features = "src/test/test-resources/features",
    glue = {"step_definations_bdd"},
    plugin = {
        "pretty",
        "html:report/amazon-reports.html",
        "json:report/amazon-reports.json"
    },
    tags = "@Amazon",
    monochrome = true,
    dryRun = false
)
public class AmazonTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}