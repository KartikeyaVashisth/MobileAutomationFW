package support;

public class TransactionRecord {

	String categoryType;
	String amount;
	String account;
	String fromAccount;
	String toAccount;
	String payee;
//	String from;
//	String description;
	String frequency;
	String every;
	String day;
	String every2WeeksOn;
	String firstOn;
	String secondOn;
	String yearlyDate;
	String firstOnDate;
	String secondOnDate;
	String endOnDate;
	String numberofReminders;
	String category;
	String tags;
	String note;
	String transactionType;
	String mddyyyy;
	
	public void setCategoryType(String category) {
		
		this.categoryType = category;
	}
	
	public void setAmount(String amount) {
		
		this.amount = amount;
	}
	
	public void setAccount(String accountName) {
		
		this.account = accountName;
	}
	
	public void setFromAccount(String fromAccountName) {
		
		this.fromAccount = fromAccountName;
	}

	public void setToAccount(String toAccountName) {
	
	this.toAccount = toAccountName;
	}
	
	public void setPayee(String Payee) {
		
		this.payee = Payee;
	}
	
//	public void setFrom(String Payee) {
//		
//		this.payee = Payee;
//	}
//	
//	public void setDescription(String Payee) {
//		
//		this.payee = Payee;
//	}
	
	public void setFrequency(String Frequency) {
		
		this.frequency = Frequency;
	}

	public void setEvery(String Every) {
		
		this.every = Every;
	}
	
	public void setDay(String Day) {
		
		this.day = Day;
	}

	public void setEvery2WeeksOn(String Every2WeeksOn) {
		
		this.every2WeeksOn = Every2WeeksOn;
	}
	
	public void setFirstOn(String FirstOn) {
		
		this.firstOn = FirstOn;
	}
	
	public void setSecondOn(String SecondOn) {
		
		this.secondOn = SecondOn;
	}

	public void setYearlyDate(String mddyyyy) {
		
		this.yearlyDate = mddyyyy;
	}
	
	public void setFirstOnDate(String mddyyyy) {
		
		this.firstOnDate = mddyyyy;
	}
	
	public void setSecondOnDate(String mddyyyy) {
		
		this.secondOnDate = mddyyyy;
	}
	
	public void setEndOnDate(String EndOnDate) {
		
		this.endOnDate = EndOnDate;
	}
	
	public void setEndAfterNumberOfReminders(String NumberofReminders) {
		
		this.numberofReminders = NumberofReminders;
	}
	
	public void setCategory(String Category) {
		
		this.category = Category;
	}
	
	public void setTags(String Tags) {
		
		this.tags = Tags;
	}
	
	public void setNote(String Note) {
		
		this.note = Note;
	}
	
	public void setTransactionType(String transactionType) {
		
		this.transactionType = transactionType;
	}
	
	public void setDate(String mddyyyy) {
		
		this.mddyyyy = mddyyyy;
	}
	
	public String getCategoryType() {
		
		return categoryType;
	}
	
	public String getDate() {
		
		return mddyyyy;
	}
	
	public String getTransactionType() {
		
		return transactionType;
	}
	
	public String getAmount() {
		
		return this.amount;
	}

	public String getAccount() {
	
		return this.account;
	}
	
	public String getFromAccount() {
		
		return this.fromAccount;
	}
	
	public String getToAccount() {
		
		return this.toAccount;
	}
	
	public String getPayee() {
		
		return this.payee;
	}
	
//	public String getFrom() {
//		
//		return this.payee;
//	}
//	
//	public String getDescription() {
//		
//		return this.payee;
//	}
	
	public String getFrequency() {
		
		return this.frequency;
	}

	public String getEvery() {
		
		return this.every;
	}
	
	public String getDay() {
		
		return this.day;
	}
	
	public String getEvery2WeeksOn() {
		
		return this.every2WeeksOn;
	}
	
	public String getFirstOn() {
		
		return this.firstOn;
	}
	
	public String getSecondOn() {
		
		return this.secondOn;
	}
	
	public String getYearlyDate() {
		
		return yearlyDate;
	}
	
	public String getFirstOnDate() {
		
		return firstOnDate;
	}
	
	public String getSecondOnDate() {
		
		return secondOnDate;
	}
	
	public String getEndOnDate() {
		
		return this.endOnDate;
	}
	
	public String getEndAfterNumberOfReminders() {
		
		return this.numberofReminders;
	}
	
	public String getCategory() {
		
		return this.category;
	}
	
	public String getTags() {
		
		return this.tags;
	}

	public String getNote() {
	
		return this.note;
	}
}
