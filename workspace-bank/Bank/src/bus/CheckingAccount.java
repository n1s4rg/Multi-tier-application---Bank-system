package bus;


public class CheckingAccount extends Account  {
	
	private int limit;

	public CheckingAccount() throws InvalidInputException {
		super();
		this.setLimit(0);
		
	}
	
	public CheckingAccount(double amount, double extraFee, int limit) throws InvalidInputException {
		super(amount, extraFee);
		this.setLimit(limit);
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) throws InvalidInputException {
		Validator.isPositive(limit);
		this.limit = limit;
	}

	@Override
	public String viewAccount() {
		String str = "Checking Account ";
		str += "Account Number: "+super.getAccountNum()+", Balance: "+super.getAmount()+", Limit: "+this.limit;
		str += ", Extra Fee: "+this.getExtraFee();
		return str;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "Checking Account ";
		str += super.toString();
		str += ", Limit: "+this.limit;
		str += ", Extra Fee: "+this.getExtraFee();
		return str;
	}
	
}
