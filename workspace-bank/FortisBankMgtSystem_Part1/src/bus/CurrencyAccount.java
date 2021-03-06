package bus;


public class CurrencyAccount extends Account {
	
	private EnumCurrency currentType;
	
	public CurrencyAccount() {
		super();
		this.currentType = EnumCurrency.UNDEFINED;
	}
	
	public CurrencyAccount(double amount, double extraFee, EnumCurrency currentType) throws InvalidInputException {
		super(amount,extraFee);
		this.currentType = currentType;
	}
	
	public EnumCurrency getCurrentType() {
		return currentType;
	}
	public void setCurrentType(EnumCurrency currentType) {
		this.currentType = currentType;
	}
	

	@Override
	public String viewAccount() {
		// TODO Auto-generated method stub
		String str = "Currency Account ";
		str += "Account Number: "+super.getAccountNum()+", Balance: "+super.getAmount()+", Currency: "+this.getCurrentType().toString();

		return str;
	}
	
}
