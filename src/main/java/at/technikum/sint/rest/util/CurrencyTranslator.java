package at.technikum.sint.rest.util;
import org.tempuri.IService1;
import org.tempuri.Service1Locator;

/**
 * Created by @author
 */
public class CurrencyTranslator {

    public static double convertValue(double value, String sourceCurrency, String destinationCurrency) {

        Service1Locator locator = new Service1Locator();
        try {
            IService1 iservice1 = locator.getBasicHttpBinding_IService1();
            // convert(price, act_curr, des_curr) method from Mario 's service
            double translatedvalue = iservice1.convert(value, sourceCurrency, destinationCurrency);
            System.out.println("Translated value: " + translatedvalue);
            //JOptionPane.showMessageDialog(null, "The translated value is: " + translatedvalue);

            return translatedvalue;

        } catch (Exception e) {
            e.printStackTrace();
            //show message if unreachable
            //JOptionPane.showMessageDialog(null, "Unable to reach the service Waehrungsumrechner..." + e.getMessage());

            return 0.0;
        }



    }
}
