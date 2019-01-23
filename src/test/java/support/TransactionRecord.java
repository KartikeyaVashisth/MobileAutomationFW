package support;

public class TransactionRecord {

	String categoryType;
	String amount;
	String account;
	String payee;
	String category;
	String tags;
	String note;
	
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
	
	public void setTags(String Tags) {
		
		this.tags = Tags;
	}
	
	public void setNote(String Note) {
		
		this.note = Note;
	}
	
	public String getCategoryType() {
		
		return categoryType;
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
	
	public String getTag() {
		
		return this.tags;
	}

	public String getNote() {
	
	return this.note;
	}
}
