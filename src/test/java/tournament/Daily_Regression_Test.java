package tournament;

import support.Recovery;

import org.testng.annotations.Test;
	import org.testng.asserts.SoftAssert;

	import com.relevantcodes.extentreports.LogStatus;

	import dugout.BankingAndCreditCardPage;
	import dugout.InvestingPage;
	import dugout.NetIncomeOverTimePage;
	import dugout.OverviewPage;
	import dugout.SettingsPage;
	import dugout.SignInPage;
	import dugout.SpendingOverTimePage;
	import dugout.SpendingTrendPage;
	import dugout.TransactionDetailPage;
	import dugout.TransactionSummaryPage;
	import dugout.TransactionsPage;
	import io.appium.java_client.MobileElement;
	import referee.Commentary;
	import referee.Verify;
	import support.Helper;
	import support.Recovery;
	import support.TransactionRecord;

	public class Daily_Regression_Test extends Recovery {
		String sUserName = "yuvaraju.boligorla@quicken.com";
		String sPassword = "Intuit!1";
		//String sDataset = "TodaysBalancesTest";
		String sDataset = "ProjectedBalances";
		String sManualChecking = "Manual_Checking";
		String sOnlineChecking ="onl_checking1";
		String sManualCreditCard = "Manual_CC";
		String sOnlineCreditCard ="onl_cc";
		String sManualCash="Manual_cash";
		String sOnlineCash="onl_cash";
		String sManualSaving = "Manual_Savings";
		String sOnlineSaving = "onl_savings1";
		String backButton1_ios = "Banking & Credit";
		String s;
		
		// Add Transaction
			@Test(priority = 0)
			public void TC1_ValidateAddTransaction() throws Exception {
				String sChecking_before, sTotal_before, sChecking_after,sTotal_after ;
				SignInPage si = new SignInPage();
				si.signIn(sUserName, sPassword, sDataset);
				SoftAssert sa = new SoftAssert();

				Helper h = new Helper();
				
				Commentary.log(LogStatus.INFO, "Add an expense transaction for an manual checking account, verify checking & total balance on overview screen accounts card");
				String time = h.getCurrentTime();
				
				
				TransactionDetailPage td = new TransactionDetailPage();
				TransactionRecord tRec = new TransactionRecord();
				tRec.setAmount("5.00");
				tRec.setAccount(sManualChecking);
				tRec.setCategory("Internet");
				tRec.setPayee(time);
				tRec.setTransactionType("expense");
				//tRec.setDate(h.getFutureDaysDate(30));
				h.getContext();
				
				OverviewPage op = new OverviewPage();
				sChecking_before = op.checkingBalance.getText();
				sTotal_before = op.totalBalance.getText();
				Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
				Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
				Double d = Double.parseDouble(tRec.getAmount());
				Double dChecking_before = h.processBalanceAmount(sChecking_before);
				Double dTotal_before = h.processBalanceAmount(sTotal_before);
				
				op.addTransaction.click();
				td.addTransaction(tRec);
				Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type expense, amount "+tRec.getAmount());
				
				
				h.getContext();
				sChecking_after = op.checkingBalance.getText();
				sTotal_after = op.totalBalance.getText();
				Double dChecking_after = h.processBalanceAmount(sChecking_after);
				Double dTotal_after = h.processBalanceAmount(sTotal_after);
				
				if (dChecking_before-d==dChecking_after)
					Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
				else
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
				
				if (dTotal_before-d==dTotal_after)
					Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
				else
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
				
				sa.assertAll();	
			
				
			}
			// Edit Transaction
			
			@Test(priority=1)
			public void TC2_ValidateEditTrasaction() throws Exception {
				
				String sChecking_before, sTotal_before, sChecking_after,sTotal_after ;
				Commentary.log(LogStatus.INFO, "EDIT an expense transaction for an manual checking account, verify checking & total balance on overview screen accounts card");

			
				
				TransactionsPage tp = new TransactionsPage();
				TransactionRecord tRec = new TransactionRecord();
				Helper h = new Helper();
			
				OverviewPage op = new OverviewPage();
				sChecking_before = op.checkingBalance.getText();
				sTotal_before = op.totalBalance.getText();
				Commentary.log(LogStatus.INFO, "Checking balance before adding the transaction ["+sChecking_before+"]");
				Commentary.log(LogStatus.INFO, "Total balance before adding the transaction ["+sTotal_before+"]");
				
				TransactionDetailPage td = new TransactionDetailPage();
				tp.searchTransaction("Internet");
				tp.tapOnFirstTransation();		
				SoftAssert sa = new SoftAssert();

				tRec.setAmount("10.00");
				tRec.setAccount(sManualChecking);
				tRec.setCategory("Hotel");
				tRec.setPayee("shop");
				tRec.setTransactionType("expense");
				//tRec.setDate(h.getYesterdaysDate());
				Double txnAmount_before = h.processBalanceAmount(td.getTransactionAmount().replace("Amount: ", ""));
				System.out.println(txnAmount_before);
				td.editTransaction(tRec);
				h.getContext();
				
				System.out.println("Actual trans amount "+txnAmount_before);
				
				Double d = Double.parseDouble(tRec.getAmount());
				Double dChecking_before = h.processBalanceAmount(sChecking_before);
				Double dTotal_before = h.processBalanceAmount(sTotal_before);
				
				Double d_After = d+txnAmount_before;
				//System.out.println("D_AFTER "+ d_After);
				
				//td.addTransaction(tRec);
				Commentary.log(LogStatus.INFO, "Transaction added successfully for the account ["+tRec.getAccount()+"], transaction type income, amount "+ tRec.getAmount());
				
				SettingsPage sp = new SettingsPage();
				
				sp.selectBack(backButton1_ios);
				Thread.sleep(2000);
				sp.selectBack("Back");
				Thread.sleep(5000);
				
				sChecking_after = op.checkingBalance.getText();
				sTotal_after = op.totalBalance.getText();
				Double dChecking_after = h.processBalanceAmount(sChecking_after);
				Double dTotal_after = h.processBalanceAmount(sTotal_after);
				
				System.out.println("CHECKING BEFORE "+dChecking_before);
				System.out.println("CHECKING AFTER "+dChecking_after);
				//System.out.println("EXPECTED  "+(dChecking_before-d_After));
				
				
				
				if (dChecking_before-d_After==dChecking_after)
					Commentary.log(LogStatus.INFO, "PASS: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
				else
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Checking balance was ["+dChecking_before+"], added expense transaction for ["+tRec.getAmount()+"], now the checking balance shows ["+dChecking_after+"]");
				
				if (dTotal_before-d_After==dTotal_after)
					Commentary.log(LogStatus.INFO, "PASS: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
				else
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Total balance was ["+dTotal_before+"], added expense transaction for ["+tRec.getAmount()+"], now the total balance shows ["+dTotal_after+"]");
				
				sa.assertAll();	
				
			}
			//Verify Delete Transaction
			@Test(priority = 3)
			public void TC3_ValidateDeleteTransaction() throws Exception {
				Commentary.log(LogStatus.INFO, "Delete an expense transaction for an manual savings account, verify checking & total balance on overview screen accounts card");
				Helper h = new Helper();
				SoftAssert sa = new SoftAssert();
				OverviewPage op = new OverviewPage();
				op.navigateToAcctList();
				
				BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
				//bcc.txtTodaysBalance.click();
				bcc.allTransactionButton.click();
				
				TransactionsPage tp = new TransactionsPage();
				TransactionDetailPage td = new TransactionDetailPage();
				TransactionRecord tRec = new TransactionRecord();
				String payeeName = h.getCurrentTime();
				
				tRec.setAmount("5.00");
				tRec.setAccount(sManualChecking);
				tRec.setCategory("Internet");
				tRec.setPayee(payeeName);
				tRec.setTransactionType("expense");
				h.getContext();
						
				tp.addTransaction.click();
				td.addTransaction(tRec);
				Thread.sleep(2000);
				
				tp.searchTransactionTxtField.click();
				tp.searchTransactionTxtField.sendKeys(payeeName);
				
				
				//assertTrue(tp.getPayeeName().getText().equals(payeeName), "Created Transaction is not displayed");	
				tp.tapOnFirstTransation();
				Thread.sleep(2000);
				
				op.scroll_down();
				td.deleteTransaction.click();
				if (Verify.objExists_check(td.deleteTransactionAlertButton)) {
				td.deleteTransactionAlertButton.click();
				}
				Thread.sleep(4000);
				
				
				tp.searchTransactionTxtField.click();
				tp.searchTransactionTxtField.clear();
				tp.searchTransactionTxtField.sendKeys(payeeName);
				
				if (Verify.objExists(tp.txtNoResultFound))
					Commentary.log(LogStatus.INFO, "PASS: Successfully Deleted the selected transaction");
				else
					Commentary.log(sa, LogStatus.FAIL, "Unable to Delete the selected transaction");
				
				sa.assertAll();		
				
			}	
			
			@Test (priority = 4)
			public void TC15_VerifyTransactionSummary() throws Exception {
				
				Commentary.log(LogStatus.INFO, "Validating Transaction Summary details");
				OverviewPage op = new OverviewPage();
				op.tapOnTransactionSummaryCard();
				SoftAssert sa = new SoftAssert();
				
				TransactionSummaryPage ts = new TransactionSummaryPage();
				
				if (Verify.objExists(ts.backButtonOnHeader))
					Commentary.log(LogStatus.INFO, "PASS: Back button is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Back button is not displayed");
				
				if (Verify.objExists(ts.transactionSummaryHeader))
					Commentary.log(LogStatus.INFO, "PASS: Transaction Summary header is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Transaction Summary is not displayed");

				if (Verify.objExists(ts.categoryTab))
					Commentary.log(LogStatus.INFO, "PASS: Category button is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Category button is not displayed");
				
				if (Verify.objExists(ts.payeeTab))
					Commentary.log(LogStatus.INFO, "PASS: Payee button is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Payee button is not displayed");
				
				if (Verify.objExists(ts.getCurrentMonthYear()))
					Commentary.log(LogStatus.INFO, "PASS: Current month and year text is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Current month and year text is Not displayed");
					
				sa.assertAll();
				
				
			}
			
			@Test(priority=5)
			public void TC5_VerifyFilterForSpendingTrendCard() throws Exception {
				SoftAssert sa = new SoftAssert();
				Commentary.log(LogStatus.INFO, "Verify filter chips on trending screen should appear and selecting it reflects the category");
					
				// get balances from accounts card
				OverviewPage op = new OverviewPage();
				op.tapOnTrendingCard();
				
				SpendingTrendPage st = new SpendingTrendPage();
				
				if (Verify.objExists(st.last30Days))
					Commentary.log(sa, LogStatus.INFO, "Pass: Last 30 days filter chip is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Fail: Last 30 days filter chip is Not displayed");
				st.last30Days.click();
				
				if (Verify.objExists(st.thisMonth))
					Commentary.log(sa, LogStatus.INFO, "Pass: This month filter chip is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Fail: This month filter chip is Not displayed");
				st.thisMonth.click();
				
				if (Verify.objExists(st.lastMonth))
					Commentary.log(sa, LogStatus.INFO, "Pass: Last month filter chip is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Fail: Last month filter chip is Not displayed");
				st.lastMonth.click();
				
				if (Verify.objExists(st.monthToDate))
					Commentary.log(sa, LogStatus.INFO, "Pass: Month to date filter chip is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Fail: Month to date filter chip is Not displayed");
				st.monthToDate.click();
				
				st.scrollFilter();
				
				if (Verify.objExists(st.yearToDate))
					Commentary.log(sa, LogStatus.INFO, "Pass: Year to date filter chip is displayed");
				else
					Commentary.log(sa, LogStatus.FAIL, "Fail: Year to date filter chip is Not displayed");
				st.yearToDate.click();
				
				sa.assertAll();
			}
			@Test (priority=6)
			public void VerifySpendingOverTimeCard () throws Exception {
				
				SoftAssert sa = new SoftAssert();
				Helper h = new Helper();
				String sMonth = h.getLastSixMonths()[0];
				String sActual;
				Commentary.log(LogStatus.INFO, "Verify by default current month is highlighted/selected on the graph");
				
				
				// get balances from accounts card
				OverviewPage op = new OverviewPage();
				op.tapOnSpendingOverTimeCard();
				
				SpendingOverTimePage sot = new SpendingOverTimePage();
				sActual = sot.getSelectedMonth();
				
				if (sActual.equals(sMonth))
					Commentary.log(LogStatus.INFO, "PASS: Spending OverTime screen, by default current month "+sMonth+" is selected on the graph");
				else
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime screen by default current month ["+sMonth+"] should be selected but "+sActual +" was seen");	
				
				sa.assertAll();
				
			}
			
			@Test(priority = 7)
			public void VerifySpendingOverTimeCard_LastSixMonth() throws Exception {
				
				SoftAssert sa = new SoftAssert();
				Helper h = new Helper();
				Commentary.log(LogStatus.INFO, "Verify past six months names appear on the Spending Over Time screen");
				
				// get balances from accounts card
				OverviewPage op = new OverviewPage();
				op.tapOnSpendingOverTimeCard();
				
				String [] lastSixMonths = h.getLastSixMonths();
				
				Integer iCount;
				
				SpendingOverTimePage sot = new SpendingOverTimePage();
				
				for (iCount=0; iCount<lastSixMonths.length; iCount++) {
					if (sot.verifyMonth(lastSixMonths[iCount]))
						Commentary.log(LogStatus.INFO, "PASS: Spending OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen");
					else
						Commentary.log(sa, LogStatus.FAIL, "FAIL: Spending OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen");	
					
				}
				
				sa.assertAll();
				
			}
			@Test(priority = 8)
			public void VerifyNetIncomeOverTimeCard() throws Exception {
				
				SoftAssert sa = new SoftAssert();
				Helper h = new Helper();
				String sMonth = h.getLastSixMonths()[0];
				String sActual;
				Commentary.log(LogStatus.INFO, "Verify by default current month is highlighted/selected on the graph");
				
				// get balances from accounts card
				OverviewPage op = new OverviewPage();
				op.tapOnNetIncomeOverTimeCard();
				
				NetIncomeOverTimePage not = new NetIncomeOverTimePage();
				sActual = not.getSelectedMonth();
				
				if (sActual.equals(sMonth))
					Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime screen, by default current month "+sMonth+" is selected on the graph");
				else
					Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime screen by default current month ["+sMonth+"] should be selected but "+sActual +" was seen");	
				
				sa.assertAll();
				
			}
			
			@Test(priority = 9)
			public void VerifyNetIncomeOverTimeCard_LastSixMonth() throws Exception {
				
				SoftAssert sa = new SoftAssert();
				Helper h = new Helper();
				Commentary.log(LogStatus.INFO, "Verify past six months names appear on the NetIncome Over Time screen");
				
				// get balances from accounts card
				OverviewPage op = new OverviewPage();
				op.tapOnNetIncomeOverTimeCard();
				
				String [] lastSixMonths = h.getLastSixMonths();
				
				Integer iCount;
				
				NetIncomeOverTimePage not = new NetIncomeOverTimePage();
				
				for (iCount=0; iCount<lastSixMonths.length; iCount++) {
					if (not.verifyMonth(lastSixMonths[iCount]))
						Commentary.log(LogStatus.INFO, "PASS: NetIncome OverTime ["+lastSixMonths[iCount]+"] month appeared on the screen");
					else
						Commentary.log(sa, LogStatus.FAIL, "FAIL: NetIncome OverTime Card ["+lastSixMonths[iCount]+"] didn't appear on the screen");	
					
				}
				
				sa.assertAll();
				
			}
			@Test(priority=10)
			public void ValidateBalancesOnAccountCard() throws Exception{
				
				String sChecking, sCredit, sSaving, sTotal;
				Commentary.log(LogStatus.INFO, "Verify balances on accounts card");
				OverviewPage op = new OverviewPage();
				sChecking = op.checkingBalance.getText();
				sCredit = op.creditBalance.getText();
				sSaving = op.savingsBalance.getText();
				sTotal = op.totalBalance.getText();
				
				Helper h = new Helper();
				SoftAssert sa = new SoftAssert();
				Double dChecking = h.processBalanceAmount(sChecking);
				Double dCredit = h.processBalanceAmount(sCredit);
				Double dSaving = h.processBalanceAmount(sSaving);
				double dTotal = h.processBalanceAmount(sTotal);

				Double eTotal = (double) Math.round((dChecking+dCredit+dSaving)*100);
				eTotal = eTotal/100;
				
				if (eTotal==dTotal)
					Commentary.log(LogStatus.INFO, "PASS: Overview Page > Balances and Total amount are correct");
				else
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Overview Page > Balances and Total amount is incorrect");
				
				//Navigate to Account cards
				op.navigateToAcctList();
				BankingAndCreditCardPage bcc = new BankingAndCreditCardPage();
				
				
				sChecking = bcc.checkingBalance.getText();
				sCredit = bcc.creditCardBalance.getText();
				sSaving = bcc.savingsBalance.getText();
				sTotal = bcc.txtTodaysBalanceAmount.getText();
				
				dChecking = h.processBalanceAmount(sChecking.replace("SubTotal: ", ""));
				dCredit = h.processBalanceAmount(sCredit.replace("SubTotal: ", ""));
				dSaving = h.processBalanceAmount(sSaving.replace("SubTotal: ", ""));
				dTotal = h.processBalanceAmount(sTotal);
				
				eTotal = (double) Math.round((dChecking+dCredit+dSaving)*100);
				eTotal = eTotal/100;

				if (eTotal==dTotal)
					Commentary.log(LogStatus.INFO, "PASS: Account card Page > Balances and Total amount are correct");
				else
					Commentary.log(sa, LogStatus.FAIL, "FAIL: Account card Page > Balances and Total amount is incorrect");
				
				sa.assertAll();
			}
			
	}

