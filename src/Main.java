import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Main {

	private static ArrayList<Transaction> history = new ArrayList<Transaction>();
	private static DateFormat formatter = new SimpleDateFormat("dd/MM/yy");

	public static void main(String[] args) {

		// Modificar estos 4 parámetros a gusto
		Brand brand = Brand.VISA;
		String number = "1234567890";
		String name = "Nicolás Céspede";
		String dateString = "10/09/2022";

		try {
			Date date = formatter.parse(dateString); // bug: el parseo se realiza igual con valores no válidos de
														// dateString

			CreditCard card = createCard(brand, number, name, date);

			getCreditCardInfo(card);

			sendTransaction(new Transaction(card, 500.0)); // duplicar esta línea para intentar más de una transacción con la misma tarjeta y distintos valores
			
			compareCards(card, card);
			
			CreditCard auxCard = createCard(Brand.NARA, "123456789012", "Pepito Suárez", formatter.parse("11/03/2026"));
			compareCards(card, auxCard);

			calculateServiceFee(brand, 500.0); // Modificar los parámetros a gusto

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static void sendTransaction(Transaction t) {
		if (Transaction.isValid(history, t)) {
			history.add(t);
			System.out.println("Transacción aprobada\n");
		} else
			System.out.println("Transacción rechazada, esta tarjeta ya superó el límite de $999\n");
	}

	public ArrayList<Transaction> getHistory() {
		return history;
	}

	public static CreditCard createCard(Brand brand, String number, String name, Date date) throws Exception {
		CreditCard card = new CreditCard(brand, number, name, date);
		System.out.println("Tarjeta creada\n");
		return card;
	}

	public static void calculateServiceFee(Brand brand, double amount) throws Exception {
		double fee = Transaction.calculateServiceFee(brand, amount);
		System.out.println("Tasa de servicio: $" + fee + "\n");
	}

	public static void getCreditCardInfo(CreditCard card) {
		String brandString = "Marca: " + card.getBrand().toString();
		String numberString = "Número: " + card.getNumber();
		String cardHolderString = "Titular: " + card.getCardholderFullName();
		String expirationDateString = "Fecha de expiración: " + formatter.format(card.getExpirationDate());
		String operableString = "Válida para operación: " + (CreditCard.validateOperability(card) ? "Sí" : "No");
		System.out.println("INFORMACIÓN DE TARJETA\n" + brandString + "\n" + numberString + "\n" + cardHolderString
				+ "\n" + expirationDateString + "\n" + operableString + "\n");
	}

	public static void compareCards(CreditCard card1, CreditCard card2) {
		System.out.println(card1.equals(card2) ? "Las tarjetas ingresadas son iguales\n"
				: "Las tarjetas ingresadas son diferentes\n");
	}

}
