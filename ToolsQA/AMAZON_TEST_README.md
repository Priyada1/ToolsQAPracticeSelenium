# Amazon BDD Cucumber Framework

This framework provides automated testing for Amazon product search and cart functionality using Java, Selenium, TestNG, and Cucumber BDD.

## Framework Structure

```
ToolsQA/
├── config/
│   ├── config.properties          # Original ToolsQA configuration
│   └── amazon-config.properties    # Amazon-specific configuration
├── src/
│   ├── main/java/org/example/
│   │   ├── base/
│   │   │   ├── BaseClass.java           # Original base class
│   │   │   └── AmazonBaseClass.java      # Amazon-specific base class
│   │   ├── page_object/
│   │   │   ├── AmazonHomePage.java      # Amazon homepage page object
│   │   │   ├── AmazonSearchResultsPage.java
│   │   │   ├── AmazonProductPage.java
│   │   │   └── AmazonCartPage.java
│   │   └── utilities/
│   │       └── AmazonConfigReader.java  # Amazon configuration reader
│   └── test/
│       ├── java/
│       │   ├── runners/
│       │   │   ├── AmazonTestRunner.java
│       │   │   ├── AmazonSmokeTestRunner.java
│       │   │   └── AmazonRegressionTestRunner.java
│       │   └── step_definations_bdd/
│       │       ├── AmazonHomePageSteps.java
│       │       ├── AmazonSearchResultsSteps.java
│       │       ├── AmazonProductPageSteps.java
│       │       ├── AmazonCartPageSteps.java
│       │       └── Hooks.java
│       └── test-resources/features/
│           └── amazon_product_search.feature
├── amazon-testng.xml              # Amazon-specific TestNG configuration
└── pom.xml                        # Maven dependencies
```

## Configuration

### Amazon Configuration (amazon-config.properties)
```properties
amazonBaseUrl = https://www.amazon.in
browser = chrome
timeout = 10
implicitWait = 10
pageLoadTimeout = 30
```

### Original Configuration (config.properties)
```properties
baseUrl = https://demoqa.com/login
browser = chrome
userName = test4321
password = Test@4321
```

## Features

### Test Scenarios
1. **Smoke Test**: Search for Samsung S24 Ultra 5G mobile and add to cart
2. **Regression Test**: Verify product search functionality
3. **Regression Test**: Verify cart functionality

### Page Objects
- **AmazonHomePage**: Handles homepage interactions (search box, search button)
- **AmazonSearchResultsPage**: Manages search results and product selection
- **AmazonProductPage**: Handles product details and add to cart functionality
- **AmazonCartPage**: Manages cart operations and verification

## Running Tests

### 1. Run All Amazon Tests
```bash
mvn test -Dtest=AmazonTestRunner
```

### 2. Run Smoke Tests Only
```bash
mvn test -Dtest=AmazonSmokeTestRunner
```

### 3. Run Regression Tests Only
```bash
mvn test -Dtest=AmazonRegressionTestRunner
```

### 4. Run with TestNG XML
```bash
mvn test -DsuiteXmlFile=amazon-testng.xml
```

### 5. Run Specific Tags
```bash
mvn test -Dcucumber.filter.tags="@Amazon and @SmokeTest"
```

## Test Reports

Reports are generated in the following locations:
- **HTML Reports**: `target/cucumber-reports/`
- **JSON Reports**: `target/cucumber-reports/Cucumber.json`
- **JUnit Reports**: `target/cucumber-reports/Cucumber.xml`

## Dependencies

The framework uses the following key dependencies:
- Selenium WebDriver 4.28.1
- TestNG 7.10.2
- Cucumber Java 7.18.0
- Cucumber TestNG 7.18.0
- WebDriverManager 5.6.0

## Browser Support

Currently configured for Chrome browser. To change browser, update `amazon-config.properties`:
```properties
browser = firefox  # or chrome
```

## Parallel Execution

The framework supports parallel execution:
- Configure `thread-count` in `amazon-testng.xml`
- Use `@DataProvider(parallel = true)` in test runners

## Screenshots

Failed test scenarios automatically capture screenshots and attach them to the Cucumber report.

## Best Practices

1. **Separation of Concerns**: Amazon tests use separate configuration and base class
2. **Page Object Model**: Each page has its own page object class
3. **BDD Approach**: Business-readable feature files with step definitions
4. **Proper Wait Strategies**: Explicit waits for better reliability
5. **Error Handling**: Comprehensive error handling and logging
6. **Test Data Management**: Externalized configuration properties
