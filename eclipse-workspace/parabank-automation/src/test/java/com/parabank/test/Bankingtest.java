package com.parabank.test;

import org.openqa.selenium.By;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.parabank.base.Baseclass;

public class Bankingtest extends Baseclass {

	@Test
	public void automateCustomerBankingFlow() {
	    String username = System.getenv("PARABANK_USERNAME");
	    String password = System.getenv("PARABANK_PASSWORD");

	    Assert.assertNotNull(username, "Set PARABANK_USERNAME first");
	    Assert.assertNotNull(password, "Set PARABANK_PASSWORD first");

	    lp.setLoginValues(username, password);
	    lp.clickLogin();

	    Assert.assertTrue(
	            bp.accountsOverviewDisplayed(),
	            "Accounts Overview should appear after login");

	    // Open a new checking account.
	    String existingAccount = bp.getFirstAccountNumber();

	    bp.openNewCheckingAccount(existingAccount);
	    String newAccount = bp.getNewAccountNumber();

	    Assert.assertNotNull(newAccount, "The new account number should not be null");
	    Assert.assertFalse(newAccount.isEmpty(), "The new account number should not be empty");

	    // Transfer funds between the two accounts.
	    bp.transferFunds("5", existingAccount, newAccount);

	    By transferConfirmation = By.xpath(
	            "//*[@id='rightPanel']//*[contains(normalize-space(.),'Transfer Complete')]");

	    Assert.assertTrue(
	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    transferConfirmation)).isDisplayed(),
	            "Transfer confirmation should appear");

	    // Pay a sample bill from the existing account.
	    bp.payBill(
	            "Saritha P",
	            "Gandhi Street",
	            "Springfield",
	            "AR",
	            "90210",
	            "9549876543",
	            "9087654341",
	            "143",
	            existingAccount);

	    By billPaymentConfirmation = By.xpath(
	            "//*[@id='rightPanel']//*[contains(normalize-space(.),'Bill Payment Complete')]");

	    Assert.assertTrue(
	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    billPaymentConfirmation)).isDisplayed(),
	            "Bill payment confirmation should appear");

	    // Search for the bill payment using the same amount: 143.
	    bp.findTransactionByAmount(existingAccount, "143");

	    By transactionResults = By.xpath("//table[@id='transactionTable']");

	    Assert.assertTrue(
	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    transactionResults)).isDisplayed(),
	            "Transaction results should appear");

	    // Log out and check for the public Register link.
	    bp.logout();

	    By registerLink = By.xpath(
	            "//div[@id='loginPanel']//a[normalize-space()='Register']");

	    Assert.assertTrue(
	            wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    registerLink)).isDisplayed(),
	            "The public page should appear after logout");
	}
}