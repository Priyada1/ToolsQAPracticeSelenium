# XPath Locator Guide for ToolsQA Automation

## Overview
This guide explains how XPath locators are correctly written in the ToolsQA automation framework. XPath is used to locate elements on web pages when IDs, names, or other simple locators are not available or reliable.

---

## XPath Syntax Basics

### 1. **Absolute vs Relative XPath**

**Absolute XPath** (starts with `/`):
```xpath
/html/body/div[1]/div/div/div[2]/div[1]/div/div/div[1]/div/ul/li[1]
```
- ❌ **Not Recommended**: Breaks easily when page structure changes
- Starts from root element

**Relative XPath** (starts with `//`):
```xpath
//div[@class='header-text' and text()='Widgets']
```
- ✅ **Recommended**: More flexible and maintainable
- Searches anywhere in the document

---

## Common XPath Patterns Used in WidgetsPage

### 2. **Finding Elements by Attribute and Text**

**Pattern**: `//tagName[@attribute='value' and text()='Text']`

**Example 1**: Widgets Section
```java
@FindBy(xpath = "//div[@class='header-text' and text()='Widgets']")
private WebElement widgets;
```
**Explanation**:
- `//div` - Find any `div` element anywhere in the document
- `[@class='header-text']` - Where class attribute equals 'header-text'
- `and text()='Widgets'` - AND the text content equals 'Widgets'

**Example 2**: Select Menu
```java
@FindBy(xpath = "//span[@class ='text' and text()='Select Menu']")
private WebElement selectMenu;
```
**Explanation**:
- `//span` - Find any `span` element
- `[@class ='text']` - With class attribute = 'text'
- `and text()='Select Menu'` - AND text equals 'Select Menu'

**Example 3**: Date Picker
```java
@FindBy(xpath = "//span[@class ='text' and text()='Date Picker']")
private WebElement datePicker;
```

---

### 3. **Finding Elements by ID and Child Elements**

**Pattern**: `//parent[@id='value']/childTag[condition]`

**Example**: Select Menu Heading
```java
@FindBy(xpath = "//div[@id='selectMenuContainer']/h1[text()='Select Menu']")
private WebElement selectMenuHeading;
```
**Explanation**:
- `//div[@id='selectMenuContainer']` - Find div with id='selectMenuContainer'
- `/h1` - Direct child `h1` element
- `[text()='Select Menu']` - Where text equals 'Select Menu'

---

### 4. **Finding Elements by Type Attribute**

**Pattern**: `//tagName[@type='value']`

**Example**: Slider Range Input
```java
@FindBy(xpath = "//input[@type='range']")
private WebElement sliderRange;
```
**Explanation**:
- `//input` - Find any input element
- `[@type='range']` - Where type attribute equals 'range'

---

### 5. **Finding Elements with Multiple Conditions**

**Pattern**: `//tagName[@attr1='val1' and @attr2='val2']`

**Example**: Submit Button
```java
@FindBy(xpath = "//button[@id='submit' and @type ='button' and text()='Submit']")
private WebElement submitButton;
```
**Explanation**:
- `//button` - Find button element
- `[@id='submit']` - With id='submit'
- `and @type ='button'` - AND type='button'
- `and text()='Submit'` - AND text='Submit'

---

### 6. **Using `contains()` Function for Partial Matches**

**Pattern**: `//tagName[contains(@attribute,'partialValue')]`

**Example**: Menu Items
```java
By.xpath("//div[contains(@class,'element-list')]//span[@class='text']")
```
**Explanation**:
- `//div[contains(@class,'element-list')]` - Find div where class contains 'element-list'
- `//span[@class='text']` - Then find span with class='text' anywhere inside

**Why use `contains()`?**
- Useful when class names have multiple values: `class="element-list active"`
- More flexible than exact match

---

### 7. **Using `descendant` and `child` Axes**

**Pattern**: `//parent/descendant::tag or //parent/child::tag`

**Example**: Dropdown Option
```java
By.xpath("//div[@class =' css-26l3qy-menu']/descendant::div[2]/child::div[2]/div[1]")
```
**Explanation**:
- `//div[@class =' css-26l3qy-menu']` - Find parent div
- `/descendant::div[2]` - 2nd descendant div (anywhere below)
- `/child::div[2]` - 2nd direct child div
- `/div[1]` - 1st direct child div

**Note**: This is complex and fragile. Use simpler locators when possible.

---

### 8. **Finding Elements with `not()` Function**

**Pattern**: `//tagName[not(contains(@class,'excluded'))]`

**Example**: Today's Date in Calendar
```java
By.xpath("//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='15']")
```
**Explanation**:
- `//div[contains(@class,'react-datepicker__day')]` - Div with class containing 'react-datepicker__day'
- `and not(contains(@class,'outside-month'))` - AND class does NOT contain 'outside-month'
- `and text()='15'` - AND text equals '15'

**Why use `not()`?**
- Excludes elements from other months in calendar
- Filters out disabled/hidden elements

---

### 9. **Dynamic XPath with String Formatting**

**Pattern**: Using `String.format()` for dynamic values

**Example**: Selecting Today's Date
```java
int day = today.getDayOfMonth();
String todayXpath = String.format("//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='%d']", day);
```
**Explanation**:
- `%d` is replaced with the actual day number
- Creates dynamic XPath based on current date
- Example: If day=15, XPath becomes: `//div[...] and text()='15']`

---

## Best Practices for Writing XPath

### ✅ **DO:**

1. **Use relative XPath** (`//` instead of `/`)
   ```xpath
   ✅ //div[@class='widget']
   ❌ /html/body/div[1]/div[2]
   ```

2. **Combine multiple attributes** for uniqueness
   ```xpath
   ✅ //button[@id='submit' and @type='button']
   ❌ //button[@id='submit']
   ```

3. **Use text() for visible text** when needed
   ```xpath
   ✅ //span[@class='text' and text()='Select Menu']
   ```

4. **Use contains() for partial matches**
   ```xpath
   ✅ //div[contains(@class,'element-list')]
   ```

5. **Use not() to exclude unwanted elements**
   ```xpath
   ✅ //div[contains(@class,'day') and not(contains(@class,'disabled'))]
   ```

### ❌ **DON'T:**

1. **Avoid absolute XPath** with hardcoded indices
   ```xpath
   ❌ //div[1]/div[2]/div[3]
   ```

2. **Avoid overly complex nested paths**
   ```xpath
   ❌ //div/div/div/div/span
   ✅ //span[@class='text']
   ```

3. **Avoid using only text()** (can break with whitespace)
   ```xpath
   ❌ //span[text()='Select Menu']
   ✅ //span[@class='text' and text()='Select Menu']
   ```

4. **Avoid XPath with position()** when possible
   ```xpath
   ❌ //div[position()=2]
   ✅ //div[@id='specific-id']
   ```

---

## Common XPath Functions Reference

| Function | Example | Purpose |
|----------|---------|---------|
| `text()` | `text()='Widgets'` | Match exact text content |
| `contains()` | `contains(@class,'active')` | Partial attribute match |
| `not()` | `not(contains(@class,'disabled'))` | Exclude elements |
| `starts-with()` | `starts-with(@id,'btn-')` | Match beginning of value |
| `normalize-space()` | `normalize-space(text())='Text'` | Remove extra whitespace |

---

## How XPath Works in Page Object Model

### Step 1: Define with @FindBy
```java
@FindBy(xpath = "//span[@class ='text' and text()='Date Picker']")
private WebElement datePicker;
```

### Step 2: PageFactory Initialization
```java
public WidgetsPage(WebDriver driver) {
    this.driver = driver;
    PageFactory.initElements(driver, this);  // Initializes @FindBy elements
    explicitWait = new WebDriverWait(driver, Duration.ofSeconds(10));
}
```

### Step 3: Use in Methods
```java
public void clickOnDatePicker() {
    datePicker.click();  // Element is already located by PageFactory
}
```

### Step 4: Dynamic XPath in Methods
```java
By datePickerLocator = By.xpath("//span[@class ='text' and text()='Date Picker']");
WebElement element = explicitWait.until(ExpectedConditions.presenceOfElementLocated(datePickerLocator));
```

---

## Debugging XPath

### 1. **Test XPath in Browser Console**
```javascript
$x("//span[@class ='text' and text()='Date Picker']")
```

### 2. **Verify Element Count**
```java
List<WebElement> elements = driver.findElements(By.xpath("//span[@class='text']"));
System.out.println("Found " + elements.size() + " elements");
```

### 3. **Check Element Visibility**
```java
WebElement element = driver.findElement(By.xpath("//span[@class='text']"));
System.out.println("Is Displayed: " + element.isDisplayed());
System.out.println("Is Enabled: " + element.isEnabled());
```

---

## Real Examples from WidgetsPage

### Example 1: Widgets Section Click
```java
@FindBy(xpath = "//div[@class='header-text' and text()='Widgets']")
private WebElement widgets;

public void clickOnWidgetsSection() {
    widgets.click();  // Simple click using @FindBy
}
```

### Example 2: Dynamic Menu Item Selection
```java
By datePickerLocator = By.xpath("//span[@class ='text' and text()='Date Picker']");
WebElement element = explicitWait.until(ExpectedConditions.presenceOfElementLocated(datePickerLocator));
element.click();
```

### Example 3: Complex Calendar Date Selection
```java
String todayXpath = String.format(
    "//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='%d']", 
    day
);
WebElement todayDateElement = explicitWait.until(ExpectedConditions.elementToBeClickable(By.xpath(todayXpath)));
todayDateElement.click();
```

---

## Summary

1. **Use relative XPath** (`//`) for flexibility
2. **Combine attributes** (`@class` and `text()`) for uniqueness
3. **Use `contains()`** for partial matches
4. **Use `not()`** to exclude unwanted elements
5. **Use `String.format()`** for dynamic XPath
6. **Test XPath** in browser console before using in code
7. **Prefer simpler locators** (ID, name) when available
8. **Use explicit waits** with XPath for reliability

---

## Quick Reference Cheat Sheet

```xpath
# Basic Patterns
//tagName[@attribute='value']                    # By attribute
//tagName[text()='Text']                        # By text
//tagName[@attr='val' and text()='Text']        # Combined

# Advanced Patterns
//tagName[contains(@class,'partial')]            # Partial match
//tagName[not(contains(@class,'excluded'))]     # Exclude
//parent/child[@attr='val']                     # Parent-child
//parent//descendant[@attr='val']                # Descendant
//tagName[position()=1]                         # Position (avoid if possible)

# Functions
text()='Value'                                   # Exact text
contains(@attr,'partial')                       # Partial attribute
starts-with(@attr,'prefix')                     # Starts with
normalize-space(text())='Value'                 # Trim whitespace
```

