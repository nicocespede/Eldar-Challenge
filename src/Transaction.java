import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class Transaction {

	private CreditCard creditCard;
	private double amount;
	private double rechargedAmount;

	public Transaction(CreditCard creditCard, double amount) throws Exception {

		if (CreditCard.validateOperability(creditCard))
			this.creditCard = creditCard;
		else
			throw new Exception("Expired credit card");

		if (amount > 0) {
			this.amount = amount;
			this.rechargedAmount = amount + Transaction.calculateServiceFee(creditCard.getBrand(), amount);
		} else
			throw new Exception("Invalid amount");
	}

	public static double calculateServiceFee(Brand brand, double amount) throws Exception {

		double fee;

		Calendar today = Calendar.getInstance();
		double actualMonth = today.get(Calendar.MONTH) + 1;

		switch (brand) {

		case VISA:
			DateFormat df = new SimpleDateFormat("yy");
			double actualYear = Double.parseDouble(df.format(today.getTime()));
			fee = (actualYear / actualMonth) / 100.0;
			break;

		case NARA:
			double actualDay = today.get(Calendar.DAY_OF_MONTH);
			fee = (actualDay * 0.5) / 100.0;
			break;

		case AMEX:
			fee = (actualMonth * 0.1) / 100.0;
			break;

		default:
			throw new Exception("Invalid brand");
		}

		double MIN_FEE = 0.3 / 100.0; // 0.3% es la tasa de servicio mínima
		double MAX_FEE = 5.0 / 100.0; // 5% es la tasa de servicio máxima

		if (fee < MIN_FEE)
			return amount * MIN_FEE;
		else if (fee > MAX_FEE)
			return amount * MAX_FEE;
		else
			return amount * fee;
	}

	public static boolean isValid(ArrayList<Transaction> history, Transaction transaction) {
		ArrayList<Transaction> transactions = new ArrayList<Transaction>(history);
		transactions.removeIf(t -> !t.getCreditCard().equals(transaction.getCreditCard()));

		double totalAmount = 0;
		for (Transaction t : transactions) {
			totalAmount += t.getRechargedAmount();
			//System.out.println(totalAmount);
		}

		return totalAmount + transaction.getRechargedAmount() >= 1000.0 ? false : true;
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public double getAmount() {
		return amount;
	}

	public double getRechargedAmount() {
		return rechargedAmount;
	}
}
