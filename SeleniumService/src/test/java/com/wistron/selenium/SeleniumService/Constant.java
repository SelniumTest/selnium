package com.wistron.selenium.SeleniumService;

public class Constant {
	
	public static final String WIAccessControl_URL = "https://192.168.21.236/admin/";
	public static final String PROJECT_SETUP = "https://192.168.21.236/admin/#/access/setup";
	public static final String SUPER_USERID = "10501303";
	public static final String PWD = "P@ss4444";
	public static final String WORKING_DIR = SeleniumTest.class.getClassLoader().getResource("").toString();
	public static final String ERROR_PASSWORD = "Incorrect account or password!";
	public static final String ERROR_ACCOUNT = "The account does not exist.";
}
