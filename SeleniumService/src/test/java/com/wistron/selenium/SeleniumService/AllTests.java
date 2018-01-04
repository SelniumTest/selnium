package com.wistron.selenium.SeleniumService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;

import org.apache.tools.ant.taskdefs.optional.junit.JUnitTest;
import org.apache.tools.ant.taskdefs.optional.junit.XMLJUnitResultFormatter;

import com.wistron.selenium.SeleniumService.util.ClassFinder;

import junit.framework.TestCase;
import junit.framework.TestResult;
import junit.framework.TestSuite;

public class AllTests {
	public static void main(String[] args) {

		TestResult tr = new TestResult();
		TestSuite shaofuTS = new TestSuite("suite");
		JUnitTest jut = new JUnitTest("JTestUnit");
		XMLJUnitResultFormatter JunitResultFormatter = new XMLJUnitResultFormatter();
		// String timeStamp = new SimpleDateFormat("yyMMdd_HHmmss").format(Calendar.getInstance().getTime());
		// String fileName = "report" + timeStamp + ".xml";
		try {
			JunitResultFormatter.startTestSuite(jut);
			OutputStream w = new FileOutputStream(new File("report.xml"));
			JunitResultFormatter.setOutput(w);
			tr.addListener(JunitResultFormatter);
			List<Class<?>> classes = ClassFinder.find("com.wistron.selenium.SeleniumService.testcase");
			for (Class c : classes) {
				if (c.getSimpleName().startsWith("Test")) {
					String name = c.getSimpleName();
					TestCase tc = (TestCase) c.newInstance();
					tc.setName(name.toLowerCase());
					shaofuTS.addTest(tc);
				}
			}
			long start = new Date().getTime();
			shaofuTS.run(tr);
			long end = new Date().getTime();
			// jut.
			jut.setCounts(tr.runCount(), tr.failureCount(), tr.errorCount());
			jut.setRunTime(end - start);
			JunitResultFormatter.endTestSuite(jut);
			w.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
