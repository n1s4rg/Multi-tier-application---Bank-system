package bus;

public class SavingAccount extends Account  {
	
	private double interestRate;
	private double annualGain;
	
	
	public SavingAccount() {
		super();
		this.interestRate = 0.0;
		this.annualGain = 0.0;
	}
	
	public SavingAccount(double amount, double extraFee, double interestRate, double annualGain) throws InvalidInputException {
		super(amount, extraFee);
		this.interestRate = interestRate;
		this.annualGain = annualGain;
	}
	
	public double getInterestRate() {
		return interestRate;
	}
	public void setInterestRate(double interestRate) {
		
		this.interestRate = interestRate;
	}
	public double getAnnualGain() {
		return annualGain;
	}
	public void setAnnualGain(double annualGain) {
		this.annualGain = annualGain;
	}

	@Override
	public String viewAccount() {
		// TODO Auto-generated method stub
		String str = "Saving Account ";
		str += "Account Number: "+super.getAccountNum()+", Balance: "+super.getAmount()+", Interest Rate: "+this.interestRate;
		str += ", Annual Gain: "+this.annualGain;
		return str;
	}
	
	
}
