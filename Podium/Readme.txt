****Base Package*******
Base package defines java files for Base class, Generic methods, Extent Report, Common functions and Application Common Methods

******Listener Package*******
This package consist class with implementation Listeners Interface for modifying the TESTNG behavior
It allows customizing TestNG reports or logs

*****Utilities********
This package consists of the configuration file, Object repository files, Test-data field and Runtime data file

******Test-output**********
This folders contains the test execution report in HTML format
The results are generated automatically with test steps & screen shot after completion of test execution through TestNG.xml

********POM.xml*********
The pom.xml file contains information of project and configuration information for the maven to build the project
Maven reads the pom.xml file, then executes the goal for the mapped testng.xml file

Test Execution Features
********TestNG Suite Execution*******
Test NG test suite execution can be done using testng.xml file which contains collection of classes as per packages.
We can pass the parameters for test scripts for carrying out the execution at test level and associate the Listeners at Suite level

*********Execution through Selenium grid**********
Selenium Grid is a feature in Selenium that allows you to run test cases in different machines across different platforms.
The control of triggering the test cases is on the local machine, and when the test cases are triggered, they are automatically executed by the remote machine.

*********Cloud Execution********
Supports Execution of test scripts of systems deployed on Cloud such as Browser Stack