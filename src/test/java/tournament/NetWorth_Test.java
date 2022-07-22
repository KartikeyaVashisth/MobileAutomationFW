package tournament;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.relevantcodes.extentreports.LogStatus;

import dugout.BankingAndCreditCardPage;
import dugout.BillsAndIncomePage;
import dugout.NetWorthPage;
import dugout.OverviewPage;
import dugout.SignInPage;
import dugout.TransactionDetailPage;
import dugout.TransactionsPage;
import referee.Commentary;
import referee.Verify;
import support.Helper;
import support.Recovery;
import support.TransactionRecord;

public class NetWorth_Test extends Recovery {

	String sUserName = "quicken789@gmail.com";
	String sPassword = "Quicken@01";
	String sDataset = "ST Phase 2";
	String sManualChecking = "Manual_Checking";
	String sManualSaving = "Manual_Savings";
	String sManualCreditCard = "Manual_CreditCard";

	@Test (priority = 1, enabled = true)
	public void NW1_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		SignInPage si = new SignInPage();
		si.signIn(sUserName, sPassword, sDataset);

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying all the different elements of Net Worth Card.");

//		OverviewPage op = new OverviewPage();
//		op.tapOnNetWorthCard();

		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthText, 2);
		if (Verify.objExists(nw.netWorthText))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth Header text is not displayed.");

		if (Verify.objExists(nw.netWorthAmount))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth Amount is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth Amount is not displayed.");

		if (Verify.objExists(nw.backButton))
			Commentary.log(LogStatus.INFO, "PASS: Back button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Back button is not displayed.");

		if (Verify.objExists(nw.dateHeaderText))
			Commentary.log(LogStatus.INFO, "PASS: Date Header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Date Header text is not displayed.");

		if (Verify.objExists(nw.netWorthInfoButton))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth Info button is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth Info button is not displayed.");

		if (Verify.objExists(nw.assetsTab))
			Commentary.log(LogStatus.INFO, "PASS: Assets Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Assets Tab is not displayed.");

		if (Verify.objExists(nw.assetsTabAmount))
			Commentary.log(LogStatus.INFO, "PASS: Assets Tab Amount is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Assets Tab Amount is not displayed.");

		if (Verify.objExists(nw.liabilitiesTab))
			Commentary.log(LogStatus.INFO, "PASS: Liabilities Tab is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Liabilities Tab is not displayed.");

		if (Verify.objExists(nw.liabilitiesTabAmount))
			Commentary.log(LogStatus.INFO, "PASS: Liabilities Tab Amount is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Liabilities Tab Amount is not displayed.");

		sa.assertAll();
	}

	@Test (priority = 2, enabled = true)
	public void NW2_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that the Banking/Other Assets/Investments labels should be under Assets tab while Credit Card/Other Liabilities labels should be under Liabilities tab.");

//		OverviewPage op = new OverviewPage();
//		op.tapOnNetWorthCard();

		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.bankingLabel, 1);
		if (Verify.objExists(nw.bankingLabel))
			Commentary.log(LogStatus.INFO, "PASS: Banking label is displayed under Assets tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Banking label is not displayed under Assets tab.");

		nw.scrollToOtherAssetsLabel();
		
		if (Verify.objExists(nw.otherAssetsLabel))
			Commentary.log(LogStatus.INFO, "PASS: Other Assets label is displayed under Assets tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Other Assets label is not displayed under Assets tab.");

		nw.scrollToInvestmentsLabel();

		if (Verify.objExists(nw.investmentsLabel))
			Commentary.log(LogStatus.INFO, "PASS: Investments label is displayed under Assets tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Investments label is not displayed under Assets tab.");

		if (!Verify.objExists(nw.creditCardsLabel))
			Commentary.log(LogStatus.INFO, "PASS: Credit Cards label is not displayed under Assets tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Credit Cards label is displayed under Assets tab.");

		if (!Verify.objExists(nw.OtherLiabilitesLabel))
			Commentary.log(LogStatus.INFO, "PASS: Other Liabilities label is not displayed under Assets tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Other Liabilities label is displayed under Assets tab.");

		nw.tapOnLiabilitesTab();

		if (Verify.objExists(nw.creditCardsLabel))
			Commentary.log(LogStatus.INFO, "PASS: Credit Cards label is displayed under Liabilities tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Credit Cards label is not displayed under Liabilities tab.");

		if (Verify.objExists(nw.OtherLiabilitesLabel))
			Commentary.log(LogStatus.INFO, "PASS: Other Liabilities label is displayed under Liabilities tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Other Liabilities label is not displayed under Liabilities tab.");

		if (!Verify.objExists(nw.bankingLabel))
			Commentary.log(LogStatus.INFO, "PASS: Banking label is not displayed under Liabilities tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Banking label is displayed under Liabilities tab.");

		if (!Verify.objExists(nw.otherAssetsLabel))
			Commentary.log(LogStatus.INFO, "PASS: Other Assets label is not displayed under Liabilities tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Other Assets label is displayed under Liabilities tab.");

		if (!Verify.objExists(nw.investmentsLabel))
			Commentary.log(LogStatus.INFO, "PASS: Investments label is not displayed under Liabilities tab.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Investments label is displayed under Liabilities tab.");

		sa.assertAll();
	}

	@Test (priority = 3, enabled = true)
	public void NW3_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that Today's date is displayed at the top of the Net Worth card.");

//		OverviewPage op = new OverviewPage();
//		op.tapOnNetWorthCard();
		
		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();

		SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
		Date date = new Date();
		String todaysDate = sdf.format(date.getTime());

		Verify.waitForObject(nw.dateHeaderText, 1);
		String netWorthCardDate = nw.dateHeaderText.getText();
		String substring = netWorthCardDate.substring(6); //It will remove "As of".

		if(todaysDate.equals(substring)) 
			Commentary.log(LogStatus.PASS, "PASS: Today's date is displayed at the top of the Net Worth card.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Today's date is not displayed at the top of the Net Worth card.");

		sa.assertAll();
	}

	@Test (priority = 4, enabled = true)
	public void NW4_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that NetWorth amount is calculated correctly.");

//		OverviewPage op = new OverviewPage();
//		op.tapOnNetWorthCard();

		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount = nw.netWorthAmount.getText();
		String processedNetWorthAmount = netWorthAmount.replaceAll("[^0-9.-]", "");

		String assetsTabAmount = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount = assetsTabAmount.replaceAll("[^0-9.-]", "");

		String liabilitiesTabAmount = nw.liabilitiesTabAmount.getText();
		String processedLiabilitiesTabAmount = liabilitiesTabAmount.replaceAll("[^0-9.-]", "");

		//Use BigDecimal, long or int for financial calculations. If we use the BigDecimal constructor which accept double as argument we will get same result as we do while operating with double. So always use BigDecimal with String constructor.
		//Both float and double don’t produce exact results which makes them unsuitable for any financial calculation which requires exact result and not an approximation. 

		BigDecimal assets = new BigDecimal(processedAssetsTabAmount);
		BigDecimal liabilities = new BigDecimal(processedLiabilitiesTabAmount);
		BigDecimal netWorth = new BigDecimal(processedNetWorthAmount);

		Commentary.log(LogStatus.INFO, "Assets balance: ["+assets+"]");
		Commentary.log(LogStatus.INFO, "Liabilities balance: ["+liabilities+"]");
		Commentary.log(LogStatus.INFO, "Net Worth balance: ["+netWorth+"]");

		BigDecimal absoluteLiabilitiesValue = liabilities.abs();

		if((assets.subtract(absoluteLiabilitiesValue)).equals(netWorth))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth Amount is calculated correctly i.e. Assets minus Liabilities.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth Amount is not calculated correctly.");

		sa.assertAll();
	}	

	@Test (priority = 5, enabled = true)
	public void NW5_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that NetWorth amount is calculated correctly after adding a expense transaction into the asset account.");

		OverviewPage op = new OverviewPage();
//		op.tapOnNetWorthCard();

		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount_before = nw.netWorthAmount.getText();
		String processedNetWorthAmount_before = netWorthAmount_before.replaceAll("[^0-9.-]", "");

		String assetsTabAmount_before = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount_before = assetsTabAmount_before.replaceAll("[^0-9.-]", "");

		String liabilitiesTabAmount_before = nw.liabilitiesTabAmount.getText();
		String processedLiabilitiesTabAmount_before = liabilitiesTabAmount_before.replaceAll("[^0-9.-]", "");

		String bankingLabelAmount_before = nw.bankingAmount.getText();
		String processedbankingLabelAmount_before = bankingLabelAmount_before.replaceAll("[^0-9.-]", "");

		BigDecimal assets_before = new BigDecimal(processedAssetsTabAmount_before);
		BigDecimal liabilities_before = new BigDecimal(processedLiabilitiesTabAmount_before);
		BigDecimal netWorth_before = new BigDecimal(processedNetWorthAmount_before);
		BigDecimal bankingAmount_before = new BigDecimal(processedbankingLabelAmount_before);

		Commentary.log(LogStatus.INFO, "Assets balance before adding a transaction: ["+assets_before+"]");
		Commentary.log(LogStatus.INFO, "Liabilities balance before adding a transaction: ["+liabilities_before+"]");
		Commentary.log(LogStatus.INFO, "Net Worth balance before adding a transaction: ["+netWorth_before+"]");
		Commentary.log(LogStatus.INFO, "Banking Label balance before adding a transaction: ["+bankingAmount_before+"]");

		nw.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();

		String payeeName = "Payee_"+h.getCurrentTime();

		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");

		Verify.waitForObject(op.addTransaction, 2);
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);

//		op.tapOnNetWorthCard();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount_after = nw.netWorthAmount.getText();
		String processedNetWorthAmount_after = netWorthAmount_after.replaceAll("[^0-9.-]", "");

		String assetsTabAmount_after = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount_after = assetsTabAmount_after.replaceAll("[^0-9.-]", "");

		String liabilitiesTabAmount_after = nw.liabilitiesTabAmount.getText();
		String processedLiabilitiesTabAmount_after = liabilitiesTabAmount_after.replaceAll("[^0-9.-]", "");

		String bankingLabelAmount_after = nw.bankingAmount.getText();
		String processedbankingLabelAmount_after = bankingLabelAmount_after.replaceAll("[^0-9.-]", "");

		BigDecimal assets_after = new BigDecimal(processedAssetsTabAmount_after);
		BigDecimal liabilities_after = new BigDecimal(processedLiabilitiesTabAmount_after);
		BigDecimal netWorth_after = new BigDecimal(processedNetWorthAmount_after);
		BigDecimal bankingAmount_after = new BigDecimal(processedbankingLabelAmount_after);

		BigDecimal transaction_amount = new BigDecimal(tRec.getAmount());

		if((assets_before.subtract(transaction_amount)).equals(assets_after))
			Commentary.log(LogStatus.INFO, "PASS: Assets Amount is calculated correctly. Now it is: ["+assets_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Assets Amount is not calculated correctly. Now it is: ["+assets_after+"]");

		if(liabilities_before.equals(liabilities_after))
			Commentary.log(LogStatus.INFO, "PASS: Liabilities Amount is calculated correctly. Now it is: ["+liabilities_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Liabilities Amount is not calculated correctly. Now it is: ["+liabilities_after+"]");

		if((netWorth_before.subtract(transaction_amount)).equals(netWorth_after))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth Amount is calculated correctly. Now it is: ["+netWorth_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth Amount is not calculated correctly. Now it is: ["+netWorth_after+"]");

		if((bankingAmount_before.subtract(transaction_amount)).equals(bankingAmount_after))
			Commentary.log(LogStatus.INFO, "PASS: Banking Label Amount is calculated correctly. Now it is: ["+bankingAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Banking Label Amount is not calculated correctly. Now it is: ["+bankingAmount_after+"]");

		sa.assertAll();
	}

	@Test (priority = 6, enabled = true)
	public void NW6_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that NetWorth amount is calculated correctly after adding an Income transaction into the asset account.");

		OverviewPage op = new OverviewPage();
//		op.tapOnNetWorthCard();

		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount_before = nw.netWorthAmount.getText();
		String processedNetWorthAmount_before = netWorthAmount_before.replaceAll("[^0-9.-]", "");

		String assetsTabAmount_before = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount_before = assetsTabAmount_before.replaceAll("[^0-9.-]", "");

		String liabilitiesTabAmount_before = nw.liabilitiesTabAmount.getText();
		String processedLiabilitiesTabAmount_before = liabilitiesTabAmount_before.replaceAll("[^0-9.-]", "");

		String bankingLabelAmount_before = nw.bankingAmount.getText();
		String processedbankingLabelAmount_before = bankingLabelAmount_before.replaceAll("[^0-9.-]", "");

		BigDecimal assets_before = new BigDecimal(processedAssetsTabAmount_before);
		BigDecimal liabilities_before = new BigDecimal(processedLiabilitiesTabAmount_before);
		BigDecimal netWorth_before = new BigDecimal(processedNetWorthAmount_before);
		BigDecimal bankingAmount_before = new BigDecimal(processedbankingLabelAmount_before);

		Commentary.log(LogStatus.INFO, "Assets balance before adding a transaction: ["+assets_before+"]");
		Commentary.log(LogStatus.INFO, "Liabilities balance before adding a transaction: ["+liabilities_before+"]");
		Commentary.log(LogStatus.INFO, "Net Worth balance before adding a transaction: ["+netWorth_before+"]");
		Commentary.log(LogStatus.INFO, "Banking Label balance before adding a transaction: ["+bankingAmount_before+"]");

		nw.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();

		String payeeName = "Payee_"+h.getCurrentTime();

		tRec.setAmount("5.00");
		tRec.setAccount(sManualSaving);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("income");

		Verify.waitForObject(op.addTransaction, 2);
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);

//		op.tapOnNetWorthCard();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount_after = nw.netWorthAmount.getText();
		String processedNetWorthAmount_after = netWorthAmount_after.replaceAll("[^0-9.-]", "");

		String assetsTabAmount_after = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount_after = assetsTabAmount_after.replaceAll("[^0-9.-]", "");

		String liabilitiesTabAmount_after = nw.liabilitiesTabAmount.getText();
		String processedLiabilitiesTabAmount_after = liabilitiesTabAmount_after.replaceAll("[^0-9.-]", "");

		String bankingLabelAmount_after = nw.bankingAmount.getText();
		String processedbankingLabelAmount_after = bankingLabelAmount_after.replaceAll("[^0-9.-]", "");

		BigDecimal assets_after = new BigDecimal(processedAssetsTabAmount_after);
		BigDecimal liabilities_after = new BigDecimal(processedLiabilitiesTabAmount_after);
		BigDecimal netWorth_after = new BigDecimal(processedNetWorthAmount_after);
		BigDecimal bankingAmount_after = new BigDecimal(processedbankingLabelAmount_after);

		BigDecimal transaction_amount = new BigDecimal(tRec.getAmount());

		if((assets_before.add(transaction_amount)).equals(assets_after))
			Commentary.log(LogStatus.INFO, "PASS: Assets Amount is calculated correctly. Now it is: ["+assets_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Assets Amount is not calculated correctly. Now it is: ["+assets_after+"]");

		if(liabilities_before.equals(liabilities_after))
			Commentary.log(LogStatus.INFO, "PASS: Liabilities Amount is calculated correctly. Now it is: ["+liabilities_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Liabilities Amount is not calculated correctly. Now it is: ["+liabilities_after+"]");

		if((netWorth_before.add(transaction_amount)).equals(netWorth_after))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth Amount is calculated correctly. Now it is: ["+netWorth_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth Amount is not calculated correctly. Now it is: ["+netWorth_after+"]");

		if((bankingAmount_before.add(transaction_amount)).equals(bankingAmount_after))
			Commentary.log(LogStatus.INFO, "PASS: Banking Label Amount is calculated correctly. Now it is: ["+bankingAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Banking Label Amount is not calculated correctly. Now it is: ["+bankingAmount_after+"]");

		sa.assertAll();
	}

	@Test (priority = 7, enabled = true)
	public void NW7_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that NetWorth amount is calculated correctly after adding a Transfer transaction from Asset account to the Liabilities Account.");

		OverviewPage op = new OverviewPage();
//		op.tapOnNetWorthCard();

		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount_before = nw.netWorthAmount.getText();
		String processedNetWorthAmount_before = netWorthAmount_before.replaceAll("[^0-9.-]", "");

		String assetsTabAmount_before = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount_before = assetsTabAmount_before.replaceAll("[^0-9.-]", "");

		String liabilitiesTabAmount_before = nw.liabilitiesTabAmount.getText();
		String processedLiabilitiesTabAmount_before = liabilitiesTabAmount_before.replaceAll("[^0-9.-]", "");

		String bankingLabelAmount_before = nw.bankingAmount.getText();
		String processedbankingLabelAmount_before = bankingLabelAmount_before.replaceAll("[^0-9.-]", "");

		BigDecimal assets_before = new BigDecimal(processedAssetsTabAmount_before);
		BigDecimal liabilities_before = new BigDecimal(processedLiabilitiesTabAmount_before);
		BigDecimal netWorth_before = new BigDecimal(processedNetWorthAmount_before);
		BigDecimal bankingAmount_before = new BigDecimal(processedbankingLabelAmount_before);

		Commentary.log(LogStatus.INFO, "Assets balance before adding a transaction: ["+assets_before+"]");
		Commentary.log(LogStatus.INFO, "Liabilities balance before adding a transaction: ["+liabilities_before+"]");
		Commentary.log(LogStatus.INFO, "Net Worth balance before adding a transaction: ["+netWorth_before+"]");
		Commentary.log(LogStatus.INFO, "Banking Label balance before adding a transaction: ["+bankingAmount_before+"]");

		nw.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		TransactionDetailPage td = new TransactionDetailPage();
		TransactionRecord tRec = new TransactionRecord();

		String payeeName = "Payee_"+h.getCurrentTime();

		tRec.setAmount("5.00");
		tRec.setAccount(sManualChecking);
		tRec.setPayee(payeeName);
		tRec.setTransactionType("expense");
		tRec.setCategory("Transfer ["+sManualCreditCard+"]");

		Verify.waitForObject(op.addTransaction, 2);
		op.addTransaction.click();
		Verify.waitForObject(td.buttonDone, 2);
		td.addTransaction(tRec);

//		op.tapOnNetWorthCard();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount_after = nw.netWorthAmount.getText();
		String processedNetWorthAmount_after = netWorthAmount_after.replaceAll("[^0-9.-]", "");

		String assetsTabAmount_after = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount_after = assetsTabAmount_after.replaceAll("[^0-9.-]", "");

		String liabilitiesTabAmount_after = nw.liabilitiesTabAmount.getText();
		String processedLiabilitiesTabAmount_after = liabilitiesTabAmount_after.replaceAll("[^0-9.-]", "");

		String bankingLabelAmount_after = nw.bankingAmount.getText();
		String processedbankingLabelAmount_after = bankingLabelAmount_after.replaceAll("[^0-9.-]", "");

		BigDecimal assets_after = new BigDecimal(processedAssetsTabAmount_after);
		BigDecimal liabilities_after = new BigDecimal(processedLiabilitiesTabAmount_after);
		BigDecimal netWorth_after = new BigDecimal(processedNetWorthAmount_after);
		BigDecimal bankingAmount_after = new BigDecimal(processedbankingLabelAmount_after);

		BigDecimal transaction_amount = new BigDecimal(tRec.getAmount());

		if((assets_before.subtract(transaction_amount)).equals(assets_after))
			Commentary.log(LogStatus.INFO, "PASS: Assets Amount is calculated correctly. Now it is: ["+assets_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Assets Amount is not calculated correctly. Now it is: ["+assets_after+"]");

		if((liabilities_before.add(transaction_amount)).equals(liabilities_after))
			Commentary.log(LogStatus.INFO, "PASS: Liabilities Amount is calculated correctly. Now it is: ["+liabilities_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Liabilities Amount is not calculated correctly. Now it is: ["+liabilities_after+"]");

		if(netWorth_before.equals(netWorth_after))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth Amount is calculated correctly. Now it is: ["+netWorth_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth Amount is not calculated correctly. Now it is: ["+netWorth_after+"]");

		if((bankingAmount_before.subtract(transaction_amount)).equals(bankingAmount_after))
			Commentary.log(LogStatus.INFO, "PASS: Banking Label Amount is calculated correctly. Now it is: ["+bankingAmount_after+"]");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Banking Label Amount is not calculated correctly. Now it is: ["+bankingAmount_after+"]");

		sa.assertAll();
	}

	@Test (priority = 8, enabled = true)
	public void NW8_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that NetWorth amount is calculated correctly after changing the show Reminder filter setting of an account with Reminders.");

		OverviewPage op = new OverviewPage();

		BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
		TransactionsPage tp = new TransactionsPage();

		op.tapOnBillsAndIncomeCard();

		BillsAndIncomePage bi = new BillsAndIncomePage();
		bi.deleteAlreadyPresentReminderSeries();

		bi.addNewReminderSeries("Bill");

		String reminderName = "Reminder_"+h.getCurrentTime();

		TransactionRecord tRec = new TransactionRecord();
		tRec.setPayee(reminderName);
		tRec.setAmount("20.00");
		tRec.setFromAccount(sManualChecking);
		tRec.setFrequency("Weekly");

		bi.addNewReminder(tRec);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.dontShowReminderFilter)) {
			tp.dontShowReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		String sTodaysBalance_WithReminderFilterOFF = bcc.txtTodaysBalanceAmount.getText();
		String processedTodaysBalance_WithReminderFilterOFF = sTodaysBalance_WithReminderFilterOFF.replaceAll("[^0-9.-]", "");

		BigDecimal TodaysBalance_WithReminderFilterOFF = new BigDecimal(processedTodaysBalance_WithReminderFilterOFF);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();

//		op.tapOnNetWorthCard();

		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount_before = nw.netWorthAmount.getText();
		String processedNetWorthAmount_before = netWorthAmount_before.replaceAll("[^0-9.-]", "");

		String assetsTabAmount_before = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount_before = assetsTabAmount_before.replaceAll("[^0-9.-]", "");

		String bankingLabelAmount_before = nw.bankingAmount.getText();
		String processedbankingLabelAmount_before = bankingLabelAmount_before.replaceAll("[^0-9.-]", "");

		String manualCheckingAccountAmount_before = nw.manualCheckingAccountAmount.getText();
		String processedmanualCheckingAccountAmount_before = manualCheckingAccountAmount_before.replaceAll("[^0-9.-]", "");

		BigDecimal assets_before = new BigDecimal(processedAssetsTabAmount_before);
		BigDecimal netWorth_before = new BigDecimal(processedNetWorthAmount_before);
		BigDecimal bankingAmount_before = new BigDecimal(processedbankingLabelAmount_before);
		BigDecimal manualCheckingAmount_before = new BigDecimal(processedmanualCheckingAccountAmount_before);

		Commentary.log(LogStatus.INFO, "Assets balance before enabling Show Reminder filter: ["+assets_before+"]");
		Commentary.log(LogStatus.INFO, "Net Worth balance before enabling Show Reminder filter: ["+netWorth_before+"]");
		Commentary.log(LogStatus.INFO, "Banking Label balance before enabling Show Reminder filter: ["+bankingAmount_before+"]");
		Commentary.log(LogStatus.INFO, "Manual_Checking Account balance before enabling Show Reminder filter: ["+manualCheckingAmount_before+"]");

		if(TodaysBalance_WithReminderFilterOFF.equals(manualCheckingAmount_before))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Manual_Checking account's amount on Net Worth card matches with the Manual_Checking account's Today's balance amount with Show Reminder filter OFF.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual_Checking account's amount on Net Worth card does NOT match with the Manual_Checking account's Today's balance amount with Show Reminder filter OFF.");

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Thread.sleep(1000);

		op.scrollToTop();

		op.navigateToAcctList();

		bcc.selectAccount(sManualChecking);

		Verify.waitForObject(tp.buttonShowReminder, 1);
		tp.buttonShowReminder.click();

		if(Verify.objExists(tp.next7DaysReminderFilter)) {
			tp.next7DaysReminderFilter.click();
			tp.buttonApply.click();
			Thread.sleep(4000);
		}

		Verify.waitForObject(bcc.txtTodaysBalanceAmount, 1);
		String sTodaysBalance_With7DaysReminderFilter = bcc.txtTodaysBalanceAmount.getText();
		String processedTodaysBalance_With7DaysReminderFilter = sTodaysBalance_With7DaysReminderFilter.replaceAll("[^0-9.-]", "");

		BigDecimal TodaysBalance_With7DaysReminderFilter = new BigDecimal(processedTodaysBalance_With7DaysReminderFilter);

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();
		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();

//		op.tapOnNetWorthCard();
		nw.navigateToNetWorthCard();

		Verify.waitForObject(nw.netWorthAmount, 1);
		String netWorthAmount_after = nw.netWorthAmount.getText();
		String processedNetWorthAmount_after = netWorthAmount_after.replaceAll("[^0-9.-]", "");

		String assetsTabAmount_after = nw.assetsTabAmount.getText();
		String processedAssetsTabAmount_after = assetsTabAmount_after.replaceAll("[^0-9.-]", "");

		String bankingLabelAmount_after = nw.bankingAmount.getText();
		String processedbankingLabelAmount_after = bankingLabelAmount_after.replaceAll("[^0-9.-]", "");

		String manualCheckingAccountAmount_after = nw.manualCheckingAccountAmount.getText();
		String processedmanualCheckingAccountAmount_after = manualCheckingAccountAmount_after.replaceAll("[^0-9.-]", "");

		BigDecimal assets_after = new BigDecimal(processedAssetsTabAmount_after);
		BigDecimal netWorth_after = new BigDecimal(processedNetWorthAmount_after);
		BigDecimal bankingAmount_after = new BigDecimal(processedbankingLabelAmount_after);
		BigDecimal manualCheckingAmount_after = new BigDecimal(processedmanualCheckingAccountAmount_after);

		Commentary.log(LogStatus.INFO, "Assets balance after enabling Show Reminder filter: ["+assets_after+"]");
		Commentary.log(LogStatus.INFO, "Net Worth balance after enabling Show Reminder filter: ["+netWorth_after+"]");
		Commentary.log(LogStatus.INFO, "Banking Label balance after enabling Show Reminder filter: ["+bankingAmount_after+"]");
		Commentary.log(LogStatus.INFO, "Manual_Checking Account balance after enabling Show Reminder filter: ["+manualCheckingAmount_after+"]");

		if(TodaysBalance_With7DaysReminderFilter.equals(manualCheckingAmount_after))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Manual_Checking account's amount on Net Worth card matches with the Manual_Checking account's Today's balance amount with 7 Days Reminder filter set.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual_Checking account's amount on Net Worth card does NOT match with the Manual_Checking account's Today's balance amount with 7 Days Reminder filter set.");

		if(!assets_before.equals(assets_after))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Assets tab amount before and after enabling Show Reminders filter is not same.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Assets tab amount before and after enabling Show Reminders filter is seen same.");

		if(!bankingAmount_before.equals(bankingAmount_after))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Banking Label amount before and after enabling Show Reminders filter is not same.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Banking Label amount before and after enabling Show Reminders filter is seen same.");

		if(!netWorth_before.equals(netWorth_after))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Net Worth amount before and after enabling Show Reminders filter is not same.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth amount before and after enabling Show Reminders filter is seen same.");

		if(!manualCheckingAmount_before.equals(manualCheckingAmount_after))
			Commentary.log(LogStatus.INFO, "PASS: As Expected, Manual_Checking account's amount before and after enabling Show Reminders filter is not same.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Manual_Checking account's amount before and after enabling Show Reminders filter is seen same.");

		Verify.waitForObject(bi.backButton, 1);
		bi.backButton.click();

		op.tapOnBillsAndIncomeCard();

		bi.deleteSeries();

		sa.assertAll();
	}
	
	@Test (priority = 9, enabled = true)
	public void NW9_Test() throws Exception {

		SoftAssert sa = new SoftAssert();
		Helper h = new Helper();

		Commentary.log(LogStatus.INFO, "["+h.getEngine()+"]: Verifying that NetWorth Info Questions & Answers are correctly displayed.");

//		OverviewPage op = new OverviewPage();
//		op.tapOnNetWorthCard();

		NetWorthPage nw = new NetWorthPage();
		nw.navigateToNetWorthCard();
		
		Verify.waitForObject(nw.netWorthInfoButton, 1);
		nw.netWorthInfoButton.click();
		Verify.waitForObject(nw.netWorthInfoHeaderText, 1);
		
		if(Verify.objExists(nw.netWorthInfoHeaderText))
			Commentary.log(LogStatus.INFO, "PASS: Net Worth Info header text is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Net Worth Info header text is not displayed.");

		if(Verify.objExists(nw.netWorthInfoQuestion1))
			Commentary.log(LogStatus.INFO, "PASS: Question 1 is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Question 1 is either not displayed OR there are some changes in the Question's text.");

		if(Verify.objExists(nw.netWorthInfoAnswer1))
			Commentary.log(LogStatus.INFO, "PASS: Answer 1 is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Answer 1 is either not displayed OR there are some changes in the Answer's text.");
		
		if(Verify.objExists(nw.netWorthInfoQuestion2))
			Commentary.log(LogStatus.INFO, "PASS: Question 2 is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Question 2 is either not displayed OR there are some changes in the Question's text.");
		
		if(Verify.objExists(nw.netWorthInfoAnswer2))
			Commentary.log(LogStatus.INFO, "PASS: Answer 2 is displayed.");
		else
			Commentary.log(sa, LogStatus.FAIL, "FAIL: Answer 2 is either not displayed OR there are some changes in the Answer's text.");
		
		nw.netWorthInfoDrawerCloseButton.click();
		Thread.sleep(1000);
		
		sa.assertAll();
	}

}
