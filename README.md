'PracticeSelenium' project
1) You have navigated to the project using the link -https://github.com/IrinaCh29/PracticeSelenium;
2) There is an IntelliJ IDEA is installed on your PC;
3) There is a Chrome browser is installed on your PC;
4) Check that the 'master' branch is opened by default, so 'code' green button is available to click: 
either download the ZIP file or Clone the Project;
5) This 'PracticeSelenium' project can be opened by installed IntelliJ IDEA on your PC;
6) Open test.xml file, where some tests are ready to be run by default:
- "LoginTest" 
- "CreateIssueTest" 
- "EditIssueTest" - 
- "ViewIssueTest"
- "TestWithParameters"
7) Run test.xml file: click right mouse on the file and Run test.xml file;     
8) The success result should be met for each file one by one;
9) After that, open test.xml file and change default data:
- do disabled  'suite name="Sample Test Suite" parallel="false"'
- do able 'suite name="Sample Test Suite" parallel="classes" thread-count="2"'
- do disabled next classes:
            - "LoginTest"
            - "CreateIssueTest"
            - "EditIssueTest"
            - "ViewIssueTest"
            -"TestWithParameters" include name="testWithParametersInListener"
- do able next classes:
           - "Empty1Test"
           - "Empty2Test"
10) Run test.xml file once again;
11) Two parallel tests should be run: one test failed and one test passed;

Thank you for testing this project!