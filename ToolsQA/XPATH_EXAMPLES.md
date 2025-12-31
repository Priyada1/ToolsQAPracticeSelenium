# XPath Examples from WidgetsPage - Quick Reference

## How XPath Locators Work in Your Code

### 1. **Finding Menu Items by Class and Text**

**Code:**
```java
@FindBy(xpath = "//span[@class ='text' and text()='Select Menu']")
private WebElement selectMenu;
```

**How it works:**
- `//span` → Searches for ANY `<span>` element in the entire HTML document
- `[@class ='text']` → Filters to only spans where class attribute equals 'text'
- `and text()='Select Menu'` → Further filters to only where the visible text is exactly 'Select Menu'

**HTML it matches:**
```html
<span class="text">Select Menu</span>
```

**Why this works:**
- Combines attribute (`@class`) with text content (`text()`) for uniqueness
- Prevents matching other menu items with same class but different text

---

### 2. **Finding Section Headers**

**Code:**
```java
@FindBy(xpath = "//div[@class='header-text' and text()='Widgets']")
private WebElement widgets;
```

**How it works:**
- `//div` → Find any div element
- `[@class='header-text']` → Where class equals 'header-text'
- `and text()='Widgets'` → AND text content equals 'Widgets'

**HTML it matches:**
```html
<div class="header-text">Widgets</div>
```

---

### 3. **Finding Child Elements by Parent ID**

**Code:**
```java
@FindBy(xpath = "//div[@id='selectMenuContainer']/h1[text()='Select Menu']")
private WebElement selectMenuHeading;
```

**How it works:**
- `//div[@id='selectMenuContainer']` → Find div with id='selectMenuContainer'
- `/h1` → Direct child `<h1>` element (not descendant, direct child)
- `[text()='Select Menu']` → Where text equals 'Select Menu'

**HTML it matches:**
```html
<div id="selectMenuContainer">
    <h1>Select Menu</h1>  ← This matches
</div>
```

**Note:** `/` means direct child, `//` means any descendant

---

### 4. **Finding Elements by Type Attribute**

**Code:**
```java
@FindBy(xpath = "//input[@type='range']")
private WebElement sliderRange;
```

**How it works:**
- `//input` → Find any input element
- `[@type='range']` → Where type attribute equals 'range'

**HTML it matches:**
```html
<input type="range" id="sliderRange" />
```

---

### 5. **Using `contains()` for Partial Class Matching**

**Code:**
```java
By.xpath("//div[contains(@class,'element-list')]//span[@class='text']")
```

**How it works:**
- `//div[contains(@class,'element-list')]` → Find div where class contains 'element-list'
- `//span[@class='text']` → Then find any span with class='text' anywhere inside

**HTML it matches:**
```html
<div class="element-list active">  ← Matches because class contains 'element-list'
    <div>
        <span class="text">Menu Item</span>  ← This matches
    </div>
</div>
```

**Why `contains()` is useful:**
- Elements often have multiple classes: `class="element-list active selected"`
- Exact match `[@class='element-list']` would fail
- `contains()` matches if 'element-list' is anywhere in the class string

---

### 6. **Excluding Elements with `not()`**

**Code:**
```java
By.xpath("//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month'))]")
```

**How it works:**
- `//div[contains(@class,'react-datepicker__day')]` → Find div with class containing 'react-datepicker__day'
- `and not(contains(@class,'outside-month'))` → AND class does NOT contain 'outside-month'

**HTML it matches:**
```html
<!-- ✅ Matches -->
<div class="react-datepicker__day react-datepicker__day--today">15</div>

<!-- ❌ Does NOT match (has 'outside-month') -->
<div class="react-datepicker__day react-datepicker__day--outside-month">1</div>
```

**Why this is important:**
- Calendar shows dates from previous/next months (grayed out)
- We want to exclude those and only select current month dates

---

### 7. **Dynamic XPath with Variables**

**Code:**
```java
int day = today.getDayOfMonth();  // e.g., day = 15
String todayXpath = String.format(
    "//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='%d']", 
    day
);
// Result: "//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='15']"
```

**How it works:**
- `%d` is a placeholder for integer
- `String.format()` replaces `%d` with actual day value
- Creates XPath dynamically based on current date

**Example output:**
- If today is 15th: `text()='15'`
- If today is 3rd: `text()='3'`

---

### 8. **Complex Nested Path (Avoid if possible)**

**Code:**
```java
By.xpath("//div[@class =' css-26l3qy-menu']/descendant::div[2]/child::div[2]/div[1]")
```

**How it works:**
- `//div[@class =' css-26l3qy-menu']` → Find parent div
- `/descendant::div[2]` → 2nd descendant div (anywhere below, not just direct child)
- `/child::div[2]` → 2nd direct child div
- `/div[1]` → 1st direct child div

**Why this is fragile:**
- Depends on exact DOM structure
- Breaks if structure changes
- Hard to maintain

**Better approach:**
```java
// Try to find by more specific attributes
By.xpath("//div[@class='css-26l3qy-menu']//div[@role='option']")
```

---

## Common Patterns Summary

| Pattern | Example | Use Case |
|---------|---------|----------|
| **By class and text** | `//span[@class='text' and text()='Menu']` | Menu items, buttons with specific text |
| **By ID and child** | `//div[@id='container']/h1` | Headings inside specific containers |
| **By type** | `//input[@type='range']` | Form inputs, sliders |
| **Contains class** | `//div[contains(@class,'active')]` | Elements with multiple classes |
| **Exclude elements** | `//div[not(contains(@class,'disabled'))]` | Filter out disabled/hidden elements |
| **Dynamic values** | `String.format("//div[text()='%d']", day)` | Dates, dynamic content |

---

## How to Verify Your XPath

### Method 1: Browser Console
1. Open browser DevTools (F12)
2. Go to Console tab
3. Type: `$x("//span[@class='text' and text()='Select Menu']")`
4. Press Enter
5. Should return array with matching elements

### Method 2: Chrome DevTools
1. Press Ctrl+F (or Cmd+F on Mac) in Elements tab
2. Paste your XPath
3. Highlights matching elements

### Method 3: In Your Code
```java
List<WebElement> elements = driver.findElements(By.xpath("//span[@class='text']"));
System.out.println("Found " + elements.size() + " elements");
if (elements.size() == 0) {
    System.out.println("XPath found no elements - check your locator!");
}
```

---

## Tips for Writing Good XPath

1. **Start simple**: Try `//tagName[@id='value']` first
2. **Add conditions**: If not unique, add `and text()='value'`
3. **Use contains()**: When classes have multiple values
4. **Test in console**: Verify before using in code
5. **Avoid indices**: `[1]`, `[2]` break easily
6. **Prefer attributes**: `@id`, `@class` over text when possible
7. **Combine conditions**: Use `and` for uniqueness

---

## Real-World Example: Date Picker Flow

```java
// Step 1: Find Date Picker menu item
@FindBy(xpath = "//span[@class ='text' and text()='Date Picker']")
private WebElement datePicker;

// Step 2: Click to open page
datePicker.click();

// Step 3: Find date input field
@FindBy(id = "datePickerMonthYearInput")
private WebElement datePickerInput;

// Step 4: Click to open calendar
datePickerInput.click();

// Step 5: Find today's date (dynamic)
int day = LocalDate.now().getDayOfMonth();
String xpath = String.format(
    "//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='%d']", 
    day
);
WebElement today = driver.findElement(By.xpath(xpath));
today.click();
```

This shows the progression from simple static XPath to dynamic XPath based on runtime values.

