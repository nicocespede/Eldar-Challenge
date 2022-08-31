import java.util.Date;
import java.util.regex.*;

public class CreditCard {

	private Brand brand;
	private String number;
	private String cardholderFullName;
	private Date expirationDate;

	public CreditCard(Brand brand, String number, String cardholderFullName, Date expirationDate) throws Exception {
		Pattern pattern = Pattern.compile("^(\\d{10}|\\d{12})$");
		Matcher matcher = pattern.matcher(number);

		this.brand = brand;

		if (matcher.matches()) // Chequeo que el número tenga 10 o 12 dígitos
			this.number = number;
		else
			throw new Exception("Invalid card number");

		if (cardholderFullName.split(" ").length > 1) // Chequeo que haya al menos 2 palabras
			this.cardholderFullName = cardholderFullName;
		else
			throw new Exception("Invalid cardholder name");

		this.expirationDate = expirationDate;
	}

	public boolean equals(CreditCard creditCard) {
		if (creditCard.getBrand() == this.brand && creditCard.getCardholderFullName().equals(this.cardholderFullName)
				&& creditCard.getNumber().equals(this.number)
				&& creditCard.getExpirationDate().equals(this.expirationDate))
			return true;
		else
			return false;
	}

	public static boolean validateOperability(CreditCard card) {
		return card.getExpirationDate().before(new Date()) ? false : true;
	}

	public Brand getBrand() {
		return brand;
	}

	public String getNumber() {
		return number;
	}

	public String getCardholderFullName() {
		return cardholderFullName;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}
}
