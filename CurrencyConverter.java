import java.util.Scanner;
class CurrencyConverter 
{

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        // Exchange rates
        double usdToEurRate = 0.91;
        double eurToUsdRate = 1.10;

        System.out.println("Currency Converter Program");
        System.out.println("Available currencies: USD, EUR");
        System.out.print("Enter the amount: ");
        double amount = scan.nextDouble();

        System.out.print("Enter the source currency (USD/EUR): ");
        String sourceCurrency = scan.next().toUpperCase();

        System.out.print("Enter the target currency (USD/EUR): ");
        String targetCurrency = scan.next().toUpperCase();

        double convertedAmount;
        if (sourceCurrency.equals("USD") && targetCurrency.equals("EUR")) {
            convertedAmount = amount * usdToEurRate;
            System.out.println(amount + " USD is equal to " + convertedAmount + " EUR");
        } else if (sourceCurrency.equals("EUR") && targetCurrency.equals("USD")) {
            convertedAmount = amount * eurToUsdRate;
            System.out.println(amount + " EUR is equal to " + convertedAmount + " USD");
        } else {
            System.out.println("Invalid currency input or conversion not supported.");
        }

        scan.close();
    }
}