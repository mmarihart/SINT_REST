package at.technikum.sint.rest.util;

/**
 * Created by @author
 */
public class CurrencyTranslator {

    public static double convertValue(double value, String sourceCurrency, String destinationCurrency) {
        // TODO: implement magic currency translation stuff = SOAP call to Mario's service
        // sourceCurrency for example: USD
        // destinationCurrency for example: GBP
        // multiple conversions might be necessary: for example USD => EUR => GBP if no USD/GBP mapping is available
        return 0.0;
    }
}
