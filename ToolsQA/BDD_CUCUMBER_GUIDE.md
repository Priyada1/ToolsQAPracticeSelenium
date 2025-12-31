# BDD Cucumber Framework Explanation - AmazonSmokeTestRunner

## Overview
This guide explains how the BDD (Behavior-Driven Development) Cucumber framework works in your Amazon automation project, including how Runner classes, Step Definitions, Hooks, and Feature files interact.

---

## 🏗️ Framework Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Feature File (.feature)                   │
│         (Gherkin syntax - Business readable scenarios)        │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       │ Cucumber reads and parses
                       │
┌──────────────────────▼──────────────────────────────────────┐
│                  Runner Class                                │
│         (AmazonSmokeTestRunner.java)                         │
│  - Configures Cucumber execution                             │
│  - Points to features and step definitions                   │
│  - Sets tags, plugins, reports                               │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       │ Executes scenarios
                       │
┌──────────────────────▼──────────────────────────────────────┐
│              Step Definitions                                │
│  (AmazonHomePageSteps, AmazonSearchResultsSteps, etc.)       │
│  - Maps Gherkin steps to Java code                           │
│  - Contains actual automation logic                          │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       │ Uses
                       │
┌──────────────────────▼──────────────────────────────────────┐
│                    Hooks                                     │
│              (Hooks.java)                                    │
│  - @Before: Setup before scenarios                           │
│  - @After: Cleanup after scenarios                           │
│  - Screenshot on failure                                     │
└──────────────────────────────────────────────────────────────┘
```

---

## 1. 📝 FEATURE FILE

**Location:** `src/test/test-resources/features/amazon_product_search.feature`

### Purpose
- Written in **Gherkin** syntax (human-readable)
- Describes **WHAT** to test (not HOW)
- Business stakeholders can read and understand
- Acts as documentation and test specification

### Structure

```gherkin
@Amazon                    # Tag - groups related scenarios
Feature: Amazon Product Search and Cart Functionality
  As a user
  I want to search for products on Amazon and add them to cart
  So that I can purchase the desired items

  Background:              # Runs before EACH scenario
    Given I am on the Amazon homepage

  @SmokeTest              # Tag - for smoke testing
  Scenario: Search for Samsung S24 Ultra 5G mobile and add to cart
    When I search for "samsung s24 ultra 5g mobile"
    Then I should see search results for Galaxy S24 Ultra 5G
    When I click on the first product from search results
    Then I should be on the product details page
    When I click on "Add to Cart" button
    Then I should see "Added to Cart" confirmation message
    And the product should be added to my cart
```

### Key Components:

1. **Tags** (`@Amazon`, `@SmokeTest`, `@RegressionTest`)
   - Used to filter which scenarios to run
   - Can be applied to Feature or Scenario level
   - Runner class uses tags to select scenarios

2. **Feature**
   - High-level description of functionality
   - Explains business value

3. **Background**
   - Steps that run before EVERY scenario in the feature
   - Common setup steps (like login, navigation)

4. **Scenario**
   - Individual test case
   - Written in Given-When-Then format:
     - **Given**: Precondition/initial state
     - **When**: Action performed
     - **Then**: Expected outcome/verification
     - **And**: Continuation of previous step

5. **Step Definitions**
   - Each line maps to a method in Step Definition classes
   - Example: `Given I am on the Amazon homepage` → `@Given` method

---

## 2. 🏃 RUNNER CLASS

**Location:** `src/test/java/runners/AmazonSmokeTestRunner.java`

### Purpose
- **Entry point** for Cucumber test execution
- **Configures** Cucumber framework
- **Connects** Feature files with Step Definitions
- **Controls** which scenarios to run (via tags)
- **Generates** test reports

### Code Breakdown

```java
@CucumberOptions(
    features = "src/test/test-resources/features",  // Path to feature files
    glue = {"step_definations_bdd"},                 // Package with step definitions
    plugin = {                                       // Report plugins
        "pretty",                                    // Console output formatting
        "html:report/smoke-reports.html",           // HTML report
        "json:report/smoke-reports.json"            // JSON report
    },
    tags = "@Amazon and @SmokeTest",                // Run scenarios with BOTH tags
    monochrome = true,                               // Clean console output
    dryRun = false                                   // false = run tests, true = validate only
)
public class AmazonSmokeTestRunner extends AbstractTestNGCucumberTests {
    
    @Override
    @DataProvider(parallel = false)                 // Sequential execution
    public Object[][] scenarios() {
        return super.scenarios();                   // Returns all scenarios to TestNG
    }
}
```

### @CucumberOptions Explained:

| Parameter | Value | Purpose |
|-----------|-------|---------|
| `features` | `"src/test/test-resources/features"` | Location of `.feature` files |
| `glue` | `{"step_definations_bdd"}` | Package containing step definition classes |
| `plugin` | `"pretty", "html:...", "json:..."` | Report formats to generate |
| `tags` | `"@Amazon and @SmokeTest"` | Filter: run scenarios with BOTH tags |
| `monochrome` | `true` | Clean console output (no colors) |
| `dryRun` | `false` | `true` = validate syntax only, `false` = run tests |

### Tag Expressions:

- `@Amazon and @SmokeTest` → Scenarios with **BOTH** tags
- `@Amazon or @SmokeTest` → Scenarios with **EITHER** tag
- `@SmokeTest and not @RegressionTest` → SmokeTest but **NOT** RegressionTest

### Execution Flow:

1. **Cucumber reads** feature files from `features` path
2. **Filters** scenarios by `tags` (`@Amazon and @SmokeTest`)
3. **Finds** step definitions in `glue` package
4. **Maps** Gherkin steps to Java methods
5. **Executes** scenarios via TestNG
6. **Generates** reports in specified formats

---

## 3. 📋 STEP DEFINITIONS

**Location:** `src/test/java/step_definations_bdd/`

### Purpose
- **Maps** Gherkin steps to Java code
- **Contains** actual automation logic
- **Implements** the "HOW" for each step
- **Uses** Page Object Model for interactions

### Step Definition Classes:

#### A. **AmazonHomePageSteps.java**

```java
@Given("I am on the Amazon homepage")
public void i_am_on_the_amazon_homepage() {
    AmazonBaseClass.setup();                                    // Initialize browser
    amazonHomePage = new AmazonHomePage(AmazonBaseClass.getDriver());
    Assert.assertTrue(amazonHomePage.isSearchBoxDisplayed(), ...);
}

@When("I search for {string}")
public void i_search_for(String productName) {
    amazonHomePage.searchForProduct(productName);              // Enter search term
    amazonHomePage.clickSearchButton();                        // Click search
}
```

**How it works:**
- `@Given("I am on the Amazon homepage")` → Matches Gherkin step exactly
- `{string}` → Parameter capture (e.g., `"samsung s24 ultra 5g mobile"`)
- Method executes automation code

#### B. **AmazonSearchResultsSteps.java**

```java
@Then("I should see search results for Samsung S24 Ultra 5G")
public void i_should_see_search_results_for_samsung_s24_ultra_5g() {
    amazonSearchResultsPage = new AmazonSearchResultsPage(AmazonBaseClass.getDriver());
    Assert.assertTrue(amazonSearchResultsPage.isSearchResultsDisplayed(), ...);
    // Validation logic...
}

@When("I click on the first product from search results")
public void i_click_on_the_first_product_from_search_results() {
    amazonSearchResultsPage = new AmazonSearchResultsPage(AmazonBaseClass.getDriver());
    amazonSearchResultsPage.clickFirstProduct();
}
```

#### C. **AmazonProductPageSteps.java**

```java
@When("I click on {string} button")
public void i_click_on_button(String buttonName) {
    amazonProductPage = new AmazonProductPage(AmazonBaseClass.getDriver());
    if (buttonName.equals("Add to Cart")) {
        amazonProductPage.clickAddToCart();
    }
}

@Then("I should see {string} confirmation message")
public void i_should_see_confirmation_message(String expectedMessage) {
    amazonProductPage = new AmazonProductPage(AmazonBaseClass.getDriver());
    Assert.assertTrue(amazonProductPage.isAddedToCartMessageDisplayed(), ...);
}
```

#### D. **AmazonCartPageSteps.java**

```java
@Then("I should see the added product in my cart")
public void i_should_see_the_added_product_in_my_cart() {
    amazonCartPage = new AmazonCartPage(AmazonBaseClass.getDriver());
    Assert.assertTrue(amazonCartPage.isCartPageLoaded(), ...);
    Assert.assertTrue(amazonCartPage.getCartItemCount() > 0, ...);
}
```

### Step Definition Annotations:

| Annotation | Purpose | Example |
|------------|---------|---------|
| `@Given` | Precondition/setup | `@Given("I am on the Amazon homepage")` |
| `@When` | Action performed | `@When("I search for {string}")` |
| `@Then` | Verification/assertion | `@Then("I should see search results")` |
| `@And` | Continuation | `@And("the product should be added")` |

### Parameter Capture:

- `{string}` → Captures quoted string: `"samsung s24 ultra 5g mobile"`
- `{int}` → Captures integer: `"1"`
- `{word}` → Captures single word (no quotes)

---

## 4. 🪝 HOOKS

**Location:** `src/test/java/step_definations_bdd/Hooks.java`

### Purpose
- **Setup** and **teardown** code
- Runs **automatically** before/after scenarios
- **Conditional** execution based on tags
- **Screenshot** capture on failure
- **Browser** cleanup

### Code Breakdown

```java
@Before("@Amazon")
public void setUp() {
    System.out.println("Setting up test environment for Amazon scenarios");
    // BaseClass setup will be called in the step definitions
}
```

**How it works:**
- `@Before("@Amazon")` → Runs **before** scenarios tagged `@Amazon`
- Can have multiple `@Before` hooks with different tags
- Execution order: Hooks → Background → Scenario steps

```java
@After("@Amazon")
public void tearDown(Scenario scenario) {
    // Take screenshot if scenario failed
    if (scenario.isFailed()) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) AmazonBaseClass.getDriver();
        byte[] screenshot = takesScreenshot.getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "Screenshot");
    }
    
    // Close browser
    if (AmazonBaseClass.getDriver() != null) {
        AmazonBaseClass.tearDown();
    }
}
```

**How it works:**
- `@After("@Amazon")` → Runs **after** scenarios tagged `@Amazon`
- `Scenario scenario` → Access to scenario information
- `scenario.isFailed()` → Check if test failed
- `scenario.attach()` → Attach screenshot to report

### Hook Execution Order:

```
1. @Before("@Amazon")           → Setup
2. @Before("@SmokeTest")       → Additional setup
3. Background steps            → Feature background
4. Scenario steps              → Actual test
5. @After("@SmokeTest")        → Cleanup
6. @After("@Amazon")           → Final cleanup + screenshot
```

### Multiple Hooks:

```java
@Before("@Amazon")              // Runs for @Amazon scenarios
@Before("@SmokeTest")          // Runs for @SmokeTest scenarios
@Before("@RegressionTest")     // Runs for @RegressionTest scenarios
```

**If scenario has `@Amazon and @SmokeTest`:**
- Both `@Before("@Amazon")` and `@Before("@SmokeTest")` execute
- Order: `@Before("@Amazon")` → `@Before("@SmokeTest")` → Background → Scenario

---

## 5. 🔄 COMPLETE EXECUTION FLOW

### Example: Running `@SmokeTest` Scenario

```
┌─────────────────────────────────────────────────────────────┐
│ STEP 1: Runner Class Starts                                 │
│ AmazonSmokeTestRunner.main() or TestNG execution            │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│ STEP 2: Cucumber Reads Feature File                        │
│ - Reads amazon_product_search.feature                        │
│ - Finds scenarios with @Amazon and @SmokeTest tags          │
│ - Scenario: "Search for Samsung S24 Ultra 5G..."           │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│ STEP 3: Hooks Execute (@Before)                            │
│ @Before("@Amazon") → setUp()                                │
│ @Before("@SmokeTest") → setUpSmokeTest()                    │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│ STEP 4: Background Executes                               │
│ Given I am on the Amazon homepage                           │
│ → Maps to: @Given in AmazonHomePageSteps                    │
│ → Executes: i_am_on_the_amazon_homepage()                  │
│ → Opens browser, navigates to Amazon                        │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│ STEP 5: Scenario Steps Execute                              │
│                                                              │
│ When I search for "samsung s24 ultra 5g mobile"            │
│ → Maps to: @When in AmazonHomePageSteps                     │
│ → Executes: i_search_for("samsung s24 ultra 5g mobile")    │
│                                                              │
│ Then I should see search results for Galaxy S24 Ultra 5G    │
│ → Maps to: @Then in AmazonSearchResultsSteps                │
│ → Executes: i_should_see_search_results_for_samsung...()   │
│                                                              │
│ When I click on the first product from search results       │
│ → Maps to: @When in AmazonSearchResultsSteps                │
│ → Executes: i_click_on_the_first_product_from_search...()   │
│                                                              │
│ ... (continues for all steps)                               │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│ STEP 6: Hooks Execute (@After)                             │
│ @After("@SmokeTest") → tearDownSmokeTest()                 │
│ @After("@Amazon") → tearDown()                              │
│ - Takes screenshot if failed                                │
│ - Closes browser                                            │
└──────────────────────┬──────────────────────────────────────┘
                       │
                       ▼
┌─────────────────────────────────────────────────────────────┐
│ STEP 7: Reports Generated                                  │
│ - HTML: report/smoke-reports.html                          │
│ - JSON: report/smoke-reports.json                          │
│ - Console: Pretty formatted output                         │
└─────────────────────────────────────────────────────────────┘
```

---

## 6. 📊 STEP MAPPING EXAMPLE

### Feature File Step:
```gherkin
When I search for "samsung s24 ultra 5g mobile"
```

### Cucumber Process:
1. **Reads** the step: `When I search for "samsung s24 ultra 5g mobile"`
2. **Searches** in `step_definations_bdd` package for matching method
3. **Finds** in `AmazonHomePageSteps.java`:
   ```java
   @When("I search for {string}")
   public void i_search_for(String productName) {
       amazonHomePage.searchForProduct(productName);
       amazonHomePage.clickSearchButton();
   }
   ```
4. **Extracts** parameter: `"samsung s24 ultra 5g mobile"` → `productName`
5. **Executes** method with parameter
6. **Continues** to next step

---

## 7. 🎯 TAG-BASED EXECUTION

### Runner Configuration:
```java
tags = "@Amazon and @SmokeTest"
```

### Feature File:
```gherkin
@Amazon
Feature: Amazon Product Search...

  @SmokeTest
  Scenario: Search for Samsung...    ← RUNS (has both tags)
  
  @RegressionTest
  Scenario: Verify product search... ← SKIPPED (missing @SmokeTest)
  
  @SmokeTest
  @RegressionTest
  Scenario: Another test...          ← SKIPPED (missing @Amazon)
```

### Execution Result:
- ✅ **Runs**: Scenarios with `@Amazon` AND `@SmokeTest`
- ❌ **Skips**: All other scenarios

---

## 8. 📁 FILE STRUCTURE

```
ToolsQA/
├── src/
│   └── test/
│       ├── java/
│       │   └── runners/
│       │       └── AmazonSmokeTestRunner.java    ← Runner class
│       │   └── step_definations_bdd/
│       │       ├── Hooks.java                    ← Hooks
│       │       ├── AmazonHomePageSteps.java      ← Step definitions
│       │       ├── AmazonSearchResultsSteps.java
│       │       ├── AmazonProductPageSteps.java
│       │       └── AmazonCartPageSteps.java
│       └── test-resources/
│           └── features/
│               └── amazon_product_search.feature ← Feature file
└── report/
    ├── smoke-reports.html                        ← Generated reports
    └── smoke-reports.json
```

---

## 9. 🔑 KEY CONCEPTS

### A. **Separation of Concerns**

| Component | Responsibility |
|-----------|---------------|
| **Feature File** | WHAT to test (business language) |
| **Step Definitions** | HOW to test (automation code) |
| **Hooks** | WHEN to setup/cleanup (before/after) |
| **Runner** | WHERE to find files and HOW to execute |

### B. **Reusability**

- **Step definitions** can be reused across multiple scenarios
- **Hooks** apply to multiple scenarios via tags
- **Background** provides common setup

### C. **Maintainability**

- **Business logic** in feature files (non-technical stakeholders can read)
- **Technical implementation** in step definitions
- **Changes** to UI only require updating step definitions, not feature files

### D. **Test Organization**

- **Tags** group related scenarios
- **Multiple runners** for different test suites (Smoke, Regression)
- **Feature files** organize by functionality

---

## 10. 🚀 RUNNING TESTS

### Via TestNG:
```java
// Right-click AmazonSmokeTestRunner.java → Run
// Or run via testng.xml
```

### Via Maven:
```bash
mvn test -Dtest=AmazonSmokeTestRunner
```

### Via Command Line:
```bash
mvn test -Dcucumber.filter.tags="@Amazon and @SmokeTest"
```

---

## 11. 📝 SUMMARY

1. **Feature File** → Describes test scenarios in Gherkin
2. **Runner Class** → Configures Cucumber, points to features and step definitions
3. **Step Definitions** → Implements automation logic for each step
4. **Hooks** → Handles setup/teardown and screenshots
5. **Tags** → Filters which scenarios to run
6. **Execution Flow**: Runner → Hooks → Background → Scenario Steps → Hooks → Reports

---

## 12. 💡 BEST PRACTICES

1. **Keep step definitions focused** - One class per page/functionality
2. **Use meaningful step names** - Clear and descriptive
3. **Leverage tags** - Organize tests by type (Smoke, Regression, etc.)
4. **Reuse steps** - Don't duplicate step definitions
5. **Use parameters** - Make steps flexible with `{string}`, `{int}`
6. **Handle failures gracefully** - Screenshots in `@After` hooks
7. **Keep features readable** - Business-friendly language

---

This framework provides a clean separation between business requirements (feature files) and technical implementation (step definitions), making tests maintainable and understandable by both technical and non-technical team members.

