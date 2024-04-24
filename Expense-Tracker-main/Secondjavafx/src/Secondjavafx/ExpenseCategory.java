package Secondjavafx;

public class ExpenseCategory {
	private String category;
	private String amount;
	public ExpenseCategory(String cate,String amount){
		this.category = cate;
		this.amount = amount;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
}
