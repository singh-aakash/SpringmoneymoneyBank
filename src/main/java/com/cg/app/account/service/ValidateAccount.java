package com.cg.app.account.service;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import com.cg.app.account.SavingsAccount;

@Aspect
@Component
public class ValidateAccount {

	Logger logger = Logger.getLogger(ValidateAccount.class.getName());

	@Before("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void log1() {
		logger.info("Before Starting Withdraw Method");
	}

	@After("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void log2() {
		logger.info("After Starting Withdraw Method");
	}

	@Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.withdraw(..))")
	public void withdrawValidation(ProceedingJoinPoint pjp) throws Throwable {
		Object[] parameters = pjp.getArgs();
		SavingsAccount savingsAccount = (SavingsAccount) parameters[0];
		double amountToWithdraw = (Double) parameters[1];
		double currentBalance = (Double) parameters[2];

		if (amountToWithdraw > 0 && currentBalance > amountToWithdraw) {
			pjp.proceed();
			logger.info("wthdraw successfully");
		}
	}

	@Around("execution(* com.cg.app.account.service.SavingsAccountServiceImpl.deposit(..))")
	public void depositValidation(ProceedingJoinPoint pjp) throws Throwable {
		logger.info("Before-logging");

		Object[] parameters = pjp.getArgs();
		SavingsAccount savingsAccount = (SavingsAccount) parameters[0];
		double amountToDeposit = (Double) parameters[1];

		if (amountToDeposit > 0) {
			logger.info("around after");
		}
	}

	@AfterThrowing(pointcut = "execution(* com.cg.app.service.*.*(..))", throwing = "ex")
	public void log5(Exception ex) {
		logger.info("exception is" + ex.toString());
	}

}