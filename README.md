# PlaywrightJavaFramework – Java Test Automation

**A Java-based web automation testing framework using Playwright, Maven, and GitHub.**

[View GitHub Repository](https://github.com/mohiteganesh7997-boop/PlaywrightJavaFramework)

## 1. Project Overview

PlaywrightJavaFramework is an automation testing project developed using Java and Playwright to automate web application testing scenarios.

The project is organized into separate packages for page components, test classes, and utility methods to support readable, reusable, and maintainable automation code.

The repository includes examples of file upload automation, API verification, screenshot artifacts, and a GitHub Actions workflow configuration.

## 2. Technologies Used

- **Programming Language:** Java
- **Automation Framework:** Playwright for Java
- **Build and Dependency Management:** Maven
- **IDE:** IntelliJ IDEA
- **Version Control:** Git and GitHub
- **CI Workflow Configuration:** GitHub Actions

## 3. Project Structure

The following structure is based on the project folders and files in the repository.

```text
PlaywrightJavaFramework/
│
├── .github/
│   └── workflows/
│       └── playwright.yml
│
├── .idea/
├── .mvn/
├── .playwright-mcp/
│
├── screenshots/
│
├── src/
│   ├── main/
│   │
│   └── test/
│       ├── java/
│       │   ├── pages/
│       │   ├── tests/
│       │   └── utils/
│       │
│       └── resources/
│
├── .gitignore
├── pom.xml
├── editboxscreenshot.png
├── pagesscreenshot.png
└── README.md
```

**Package responsibilities:**

- `pages/` – Organizes page-related classes and web page interactions.
- `tests/` – Contains automation test classes and test scenarios.
- `utils/` – Provides a place for reusable helper methods and supporting utilities.
- `src/test/resources/` – Stores test resources, such as files required for automation.
- `screenshots/` – Stores screenshot artifacts.
- `.github/workflows/playwright.yml` – Contains the GitHub Actions workflow configuration.
- `pom.xml` – Defines Maven project configuration and dependencies.
- `.gitignore` – Specifies files and directories Git should ignore.

## 4. Key Automation Examples

### File Upload Automation

The project contains a `HandleFileUploadTest.java` test class that demonstrates file upload automation using Playwright.

The visible implementation includes:

- Retrieving a test file from the classpath resources.
- Validating that the resource file is available.
- Converting the resource URL into a file path.
- Uploading the file using Playwright's `setInputFiles()` method.
- Logging the selected file name and upload-related information.

This example demonstrates how Playwright can interact with file upload elements in a web application.

### API Verification

The project also includes a `VerifyAPI.java` file. This indicates an API verification example within the project.

Refer to the implementation of this class for the specific endpoints, request types, response validations, and assertions supported by the test.

### Screenshot Artifacts

The repository contains screenshot files, including `editboxscreenshot.png` and `pagesscreenshot.png`, as well as a `screenshots/` directory.

These artifacts can help demonstrate or troubleshoot web automation scenarios.

## 5. Prerequisites

Before running the project, ensure that the following are available:

- Java JDK compatible with the project configuration.
- Maven, or the configured Maven Wrapper.
- Git.
- IntelliJ IDEA or another Java IDE.
- Playwright browser binaries required by the project.

## 6. Clone the Repository

Clone the project using Git:

```bash
git clone https://github.com/mohiteganesh7997-boop/PlaywrightJavaFramework.git
```

Navigate to the project directory:

```bash
cd PlaywrightJavaFramework
```

## 7. Import the Project

1. Open IntelliJ IDEA.
2. Select **Open** and choose the cloned project directory.
3. Allow IntelliJ IDEA to import the Maven project.
4. Verify the Java SDK and Maven configuration.
5. Wait for dependencies to download successfully.

## 8. Build and Execute Tests

To build the project and resolve dependencies, run:

```bash
mvn clean install
```

To execute tests through Maven, run:

```bash
mvn test
```

These commands assume the Maven configuration supports the corresponding lifecycle goals and test execution. Ensure that the required test runner, plugins, and Playwright browser binaries are configured correctly.

For Playwright for Java documentation, refer to:

https://playwright.dev/java/docs/intro

## 9. GitHub Actions

The repository contains the workflow file:

`.github/workflows/playwright.yml`

This file is the configuration entry point for the project's GitHub Actions automation workflow.

Review the workflow configuration to understand the configured build and test execution steps. Successful automated execution depends on the workflow setup, required dependencies, browser installation, and test configuration.

## 10. Project Objectives

This project demonstrates practical work with:

- Java-based browser automation using Playwright.
- Organizing automation code into separate packages.
- Automating file upload interactions.
- Working with test resources and file paths.
- Exploring API verification through a dedicated Java class.
- Managing a Maven-based automation project in GitHub.
- Maintaining a GitHub Actions workflow configuration.

## 11. Future Enhancements

Potential improvements, where not already implemented, include:

- Expanding reusable Page Object Model components.
- Adding data-driven test execution.
- Integrating a structured test reporting solution.
- Capturing screenshots automatically on test failures.
- Improving CI execution and publishing test results.
- Extending browser and test coverage.

## 12. Repository

**GitHub:** https://github.com/mohiteganesh7997-boop/PlaywrightJavaFramework

This project serves as a practical example of Java and Playwright automation development, with an emphasis on organized test code, browser interactions, test resources, and version control.
