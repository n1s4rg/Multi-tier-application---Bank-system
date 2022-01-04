package bus;

public class SavingAccount extends Account  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double interestRate;
	private double annualGain;
	
	
	public SavingAccount() throws InvalidInputException {
		super();
		this.setInterestRate(0.0);
		this.setAnnualGain(0.0);
	}
	
	public SavingAccount(double amount, double extraFee, double interestRate, double annualGain) throws InvalidInputException {
		super(amount, extraFee);
		this.setInterestRate(interestRate);
		this.setAnnualGain(annualGain);
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
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = "Saving Account ";
		str += super.toString();
		str += ", Interest Rate: "+this.interestRate;
		return str;
	}
	
}
