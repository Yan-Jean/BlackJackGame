import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Console {

    private static final Scanner scan = new Scanner(System.in);

    public static String getString(String prompt) {
        System.out.print(prompt);
        return scan.nextLine();
    }

    public static String getString(String prompt, String[] allowedValues) {
        while (true) {
            System.out.print(prompt);
            String value = scan.nextLine();
            for (String s : allowedValues) {
                if (s.equalsIgnoreCase(value)) {
                    return value;
                }
            }
            System.out.println("Error! Value must be in this list: "
                    + Arrays.toString(allowedValues)
                    + ".");
        }
    }

    public static int getInt(String prompt){
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error! Invalid integer value.");
            }
        }
    }

    public static int getInt(String prompt, int min, int max) {
        while(true){
            int value = getInt(prompt);
            if (value > min && value < max) {
                return value;
            } else {
                System.out.println("Error! Number must be greater than "
                        + min
                        + " and less than "
                        + max
                        + ".");
            }
        }
    }

    public static double getDouble(String prompt){
        while (true){
            System.out.print(prompt);
            try {
                return Double.parseDouble(scan.nextLine());
            }catch (NumberFormatException e) {
                System.out.println("Error! Invalid numeric value.");
            }
        }
    }

    public static double getDouble(String prompt, double min, double max) {
        while(true){
            double value = getDouble(prompt);
            if (value >=  min && value <= max){
                return value;
            } else {
                System.out.println("Error! Number must be greater than " + Console.formatNumber(min) + " and less than " + Console.formatNumber(max) + ".");
            }
        }
    }

    public static String formatNumber (double number) {
        NumberFormat unformatedNumber = NumberFormat.getCurrencyInstance(Locale.CANADA);
        return unformatedNumber.format(number);
    }
}
