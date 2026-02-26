# BekaCookware UI Automation Framework

## Overview

This project is a Selenium-based UI Automation Framework developed using:

- Java
- Selenium WebDriver
- TestNG
- Maven
- Extent Reports
- Page Object Model (POM)
- JSON-based Localization Validation

The framework automates UI validation for the Beka Cookware website and supports multi-language verification.

---

## 🏗 Project Structure
src
├── main
│ └── java
│ ├── pages
│ │ ├── CartPage.java
│ │ ├── HomePage.java
│ │ ├── ProductPage.java
│ │ └── SearchPage.java
│ │
│ └── utilities
│ ├── ConfigProperty.java
│ ├── DriverManager.java
│ ├── ExtentReportManager.java
│ ├── LanguageManager.java
│ ├── ScreenshotManager.java
│ └── WaitManager.java
│
└── test
└── java
└── BekaCookware
├── TestNG_APITest.java
├── TestNG_CartTest.java
└── TestNG_HomePage.java
├── TestNG_ProductTest.java
└── TestNG_SearchPage.java

testng.xml
pom.xml
reports/
screenshots/

---

## 🧩 Framework Design

### 📂 pages
Contains Page Object classes following the Page Object Model pattern.

Each page:
- Stores WebElements using `@FindBy`
- Contains reusable business methods
- Keeps tests clean and readable

---

### ⚙ utilities

- **DriverManager** → WebDriver initialization and lifecycle handling  
- **WaitManager** → Explicit wait reusable methods  
- **LanguageManager** → Loads localization values from JSON files  
- **ScreenshotManager** → Captures screenshots on failure  
- **ExtentReportManager** → Generates Extent HTML reports  
- **ConfigProperty** → Reads configuration values  

---

### 🧪 test/BekaCookware

Contains TestNG test classes:

- `TestNG_HomePage`
- `TestNG_CartTest`
- `TestNG_APITest`

Tests are grouped and can be executed as:
- Smoke
- Regression
- Full Suite

---

## ▶️ How To Run Tests

### 🔹 Run All Tests

```bash
mvn clean test
________________________________________
🔹 Run Smoke Tests Only
If tests are tagged:
@Test(groups = "smoke")
Run:
mvn clean test -Dgroups=smoke
________________________________________
🔹 Run Using testng.xml
Right-click testng.xml → Run
Or:
mvn test -Dsurefire.suiteXmlFiles=testng.xml
________________________________________
⚡ Parallel Execution
Enable parallel execution inside testng.xml:
<suite name="Parallel Suite"
       parallel="classes"
       thread-count="3">
Framework supports parallel execution when DriverManager is Thread-safe.
________________________________________
🌍 Localization Validation
Localization values are stored in JSON files and loaded using:
LanguageManager.loadLanguage("en");
UI labels are compared against expected JSON values to validate translations.
In the tests, the dataprovider method is used to run tests in English and Dutch.
________________________________________
📸 Screenshot on Failure
Screenshots are automatically captured on test failure.
Saved in:
/screenshots
Attached to Extent Report.
________________________________________
📊 Extent Reporting
After execution, report is generated in:
/reports/ExtentReport.html
Open in browser to view detailed results including:
•	Pass / Fail status
•	Error stacktrace
•	Attached screenshots
________________________________________
🏷 Test Tagging
Example:
@Test(groups = {"smoke"})
Supported groups:
•	Smoke
•	Localization
________________________________________
🔧 Key Features
✔ Page Object Model architecture
✔ Reusable utility classes
✔ Explicit wait handling
✔ JSON-driven localization validation
✔ Screenshot capture on failure
✔ Extent HTML reporting
✔ Maven integration
✔ Parallel execution support
________________________________________
🚀 Future Improvements
•	Cross-browser execution
•	CI/CD integration
•	API + UI combined validation
•	Enhance reusalibilty
________________________________________
👤 Author
Automation Engineer : Rati Anand Khatri
UI Automation Framework – BekaCookware


