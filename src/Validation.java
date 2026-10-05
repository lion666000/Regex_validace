import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
    private static final int POCET_UDAJU = 5;

    private static final Pattern NAME =Pattern.compile("^\\p{Lu}\\p{Ll}+\\s\\p{Lu}*\\p{Ll}+$");

    private static final Pattern EMAIL =Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$");

    private static final Pattern PRODUCT =Pattern.compile("^(Herní myš|Klávesnice|Monitor|Grafická karta)$");

    private static final Pattern PRICE =Pattern.compile("^([1-9]\\d*,\\d{2}|0,(?!00)\\d{2})$");

    private static final Pattern QUANTITY =Pattern.compile("^([1-9]|10)$");

    private final String radek;

    Validation(String radek) {
        this.radek = radek;
    }

    public Objednavka validation(){
        System.out.println("Radek: " + radek);

        String[] data = radek.split(";", -1);

        if (data.length != POCET_UDAJU) {
            System.out.println("  NEVALIDNÍ - špatný počet údajů (nalezeno " + data.length
                    + ", očekáváno " + POCET_UDAJU + ")");
            return null;
        }

        for (int i = 0; i < data.length; i++) {
            data[i] = data[i].trim();
        }

        List<String> chyby = new ArrayList<>();

        if (!NAME.matcher(data[0]).matches()) {
            chyby.add("neplatné jméno");
        }

        if (!EMAIL.matcher(data[1]).matches()) {
            chyby.add("neplatný e-mail");
        }

        if (!PRODUCT.matcher(data[2]).matches()) {
            chyby.add("neplatný produkt");
        }

        Double price = null;
        if (PRICE.matcher(data[3]).matches()) {
            price = Double.parseDouble(data[3].replace(",","."));
        } else {
            chyby.add("neplatný formát ceny");
        }

        int quantity = 0;
        if (QUANTITY.matcher(data[4]).matches()) {
            quantity = Integer.parseInt(data[4]);
        } else {
            chyby.add("neplatný formát množství");
        }

        if (!chyby.isEmpty()) {
            System.out.println("  NEVALIDNÍ - " + String.join(", ", chyby));
            return null;
        }

        System.out.println("  VALIDNÍ");
        return new Objednavka(data[0], data[1], data[2], price, quantity);
    }
}
