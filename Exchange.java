/**
 * The class Exchange allows to convert an amount in euro.
 * 
 * Version 2.
 */

public class Exchange {

	/*
	 * Problems resolved in this 2nd version are :
	 * - Names of the identifiers
	 * - Completeness of the class
	 * - Use of Error().
	 * 
	 * This solution may be improved :
	 * - It is not possible to add a currency
	 * - It is not possible to add functionnalities on a currency (multiple names
	 * for example)
	 * - The method convert() is not efficient
	 */

	private static final String[] CURRENCIES = { "Franc", "Mark", "Dollard",
			"Euro" };

	private static final double[] RATES = { 6.55957, 1.95583, 1.2713, 1. };

	/*
	 * This method avoid code duplication. But a better solution would be to use
	 * an exception in the method currencyIndex()
	 */
	private static int searchCurrencyIndex(String currency) {
		for (int i = 0; i < CURRENCIES.length; i++) {
			if (CURRENCIES[i].equals(currency)) {
				return i;
			}
		}
		return -1;
	}

	private static int currencyIndex(String currency) {
		int index = searchCurrencyIndex(currency);
		if (index == -1) {
			throw new Error("Currency " + currency + " unknown");
		} else {
			return index;
		}
	}

	private static double convert(double amountToConvert, String currency,
			boolean toEuro) {
		int index = currencyIndex(currency);
		return amountToConvert
				* ((toEuro) ? 1 / RATES[index] : RATES[index]);
	}

	public static double convertToEuro(double amountToConvert, String currency) {
		return convert(amountToConvert, currency, true);
	}

	public static double convertFromEuro(double amountToConvert,
			String currencyTarget) {
		return convert(amountToConvert, currencyTarget, false);
	}

	public static boolean processedCurrency(String currency) {
		return searchCurrencyIndex(currency) != -1;
	}

	public static String[] processedCurrencies() {
		return (String []) CURRENCIES.clone();
	}

	public static double exchangeRate(String currency) {
		return RATES[currencyIndex(currency)];
	}

	public static void main(String[] args) {
		double amountToConvert = Double.valueOf(args[0]).doubleValue();
		String currency = args[1];

		if (processedCurrency(currency)) {
			double convertedAmount = convertToEuro(amountToConvert, currency);
			System.out.println(convertedAmount + " Euros");
		} else {
			String[] currencies = processedCurrencies();
			System.out.print("Unknown currency (processed currencies =");
			for (int i = 0; i < currencies.length; ++i) {
				System.out.print(" " + currencies[i]);
			}
			System.out.println(")");
		}
	}
}
