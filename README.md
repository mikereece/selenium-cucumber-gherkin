# selenium-cucumber-gherkin

This project utilises Selenium, Cucumber and Gherkin to provide a POC for a UI Automation Testing framework. Selenium provides the WebDriver interface and configuration for automating actions in a web browser. Cucumber allows you to define test scenarios based on User Story Acceptance Criteria - defined using the DSL Gherkin - written in the GIVEN-WHEN-THEN format. The behaviour for the test scenarios is written within Test classes referred to as Step Definitions. These classes are ran from a Test Runner driven by Cucumber.

## Getting Started

Functionality wise, the project is configured and ready to run out of the box, and there are sample tests to provide an example of the content of a Feature file and a Step Definition java class. To add your own automation tests, follow the steps below:

1. Add a **.feature** file under the src/test/resources/gherkin/features/ directory to define your Test Scenario's and Acceptance Criteria. See <http://docs.behat.org/en/v2.5/guides/1.gherkin.html> for a guide on writing Feature files in Gherkin. You can name the feature file with the Issue reference of the User Story or Task.

2. Cucumber is able to auto generate method stubs required to be added to the Step Definition test class to define the behavior of a Test Scenario. To do this locate the **TestRunner** class under <src/test/java/com/example/cucumber/runner> directory. Within the **@CucumberOptions** annotation ensure that the attribute called *dryRun* is set to true and then run the **TestRunner** class as a jUnit test. The method stubs for your Step Definition test class will be generated within the Console output.

3. Create the Step Definition test java class under the <src/test/java/com/example/cucumber/stepdefinitions> directory. You can name the class with the issue reference of the User Story or Task, post fixed with 'Test' to indicate this is a Test class. Copy and paste the method stubs from the console into the class. Ensure the class extends the BaseTest class located in the same directory as this provides a number of utility methods that can be reused. Note that the WebDriver is initialised when calling one of the action based methods from the BaseTest class e.g. *navigateToUrl(url)* or *findElementByXpath(xPath)*. Typically, one of the first actions performed when defining the behaviour of your test scenario would be to *navigateToUrl(url)*.

4. When defining the behaviour of the Step Definition Test Class you will refer to UI elements within the Web Browser which can be located by Selenium using their xPaths. You can derive the xPath of any web element by inspecting that element (e.g. input box, label, image, button) within the web browser and choosing to 'Copy xPath'. As good practice, you should use a properties file to hold all the web element xPaths and any other properties such as URL's, you wish to use within your Step Definition Test Class. You can then set the property name as a String constant within the Test class. To ensure the properties file is loaded into the test class, create an Epic, Story or Task properties file within the same directory as your Feature file. Then, in the constructor of the Test class set the *propertiesFilePath* variable to the name and path of your properties file.

5. Once the behaviour of your Step Definition Test Class has been completed, you are now ready to run your tests using the Cucumber TestRunner class. For more details about this class and how to run it, please see the **Running the tests** section.

## Prerequisites

The setup and running of this project assumes that you have Java, Maven and an IDE (e.g. Eclipse) running, although the tests can be run from the Command Line.

## Running the tests

Locate the **TestRunner** class under <src/test/java/com/example/cucumber/runner> directory. Within the **@CucumberOptions** annotation ensure that the attribute called *dryRun* is set to false and that the *features* and *glue* attributes point to the directory of the **.feature** files and **Step Definition Test Classes** respectively. You will also notice the *plugin* attribute, which can be configured to generate a test report. Currently, the reports are generated in the <target/reports> directory. To run the **TestRunner** class, right click and select 'Run As' > 'jUnit Test'.

For me information on Cucumber options, see <https://testingneeds.wordpress.com/2015/09/15/junit-runner-with-cucumberoptions/>.

## Deployment

This project is built and deployed using Maven.

## Versioning

0.0.1-SNAPSHOT (First Pass)

## Authors

Michael Reece