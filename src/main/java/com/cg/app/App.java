package com.cg.app;

import java.util.logging.Logger;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cg.app.account.ui.AccountCUI;

public class App {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(App.class.getName());
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		logger.info("passing object");
		AccountCUI accountCUI = context.getBean(AccountCUI.class);
		logger.info("object created successfully");
		accountCUI.start();
	}

}
