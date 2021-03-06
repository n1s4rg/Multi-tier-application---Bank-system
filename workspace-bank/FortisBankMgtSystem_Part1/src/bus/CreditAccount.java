package bus;


public class CreditAccount extends Account  {

	private double limitAmount;

	public CreditAccount() {
		super();
		this.limitAmount = 0.0;
	}
	
	public CreditAccount(double amount, double extraFee, double limitAmount) throws InvalidInputException {
		super(amount,extraFee);
		this.limitAmount = limitAmount;
	}

	public double getLimitAmount() {
		return limitAmount;
	}

	public void setLimitAmount(double limitAmount) throws InvalidInputException {
		Validator.isPositive(limitAmount);
		this.limitAmount = limitAmount;
	}

	@Override
	public String viewAccount() {
		// TODO Auto-generated method stub
		String str = "Credit Account ";
		str += "Account Number: "+super.getAccountNum()+", Balance: "+super.getAmount()+", Amount left: "+this.limitAmount;

		return str;
	}
	
}
