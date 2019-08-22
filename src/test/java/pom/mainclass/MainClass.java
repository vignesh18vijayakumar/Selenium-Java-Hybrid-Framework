package pom.mainclass;

import org.testng.TestNG;

import pom.base.Base;
import pom.listener.TestListener;
import pom.testcases.HomePageTest;

/**
 * Hello world!
 */
public class MainClass extends Base
{
    public static void main( String[] args )
    {
    	TestNG testSuite = new TestNG();
    	testSuite.setTestClasses(new Class[] { HomePageTest.class });
    	testSuite.addListener(new TestListener());
    	testSuite.setDefaultSuiteName("Project name");
    	testSuite.setDefaultTestName("sanity");
    	testSuite.run();
    }
}
