package support;

public class TransactionRecord {

	String categoryType;
	String amount;
	String account;
	String payee;
	String category;
	String[] tags;
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
	
	public void setPayee(String Payee) {
		
		this.payee = Payee;
	}
	
	public void setCategory(String Category) {
		
		this.category = Category;
	}
	
	public void setTags(String[] Tags) {
		
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
	
	public String getPayee() {
		
		return this.payee;
	}
	
	public String getCategory() {
		
		return this.category;
	}
	
	public String[] getTags() {
		
		return this.tags;
	}

	public String getNote() {
	
	return this.note;
	}
}
