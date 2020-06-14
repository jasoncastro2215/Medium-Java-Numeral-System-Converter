import java.util.Scanner;
public class NumberConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int sourceRadix;
        String num;
        int targetRadix;
        try {
            System.out.print("Source Radix: ");
            sourceRadix = Integer.parseInt(scanner.nextLine());
            if (sourceRadix < 1 || sourceRadix > 36) {
                System.out.println("error");
                return;
            }
            System.out.print("Source Radix Value: ");
            num = scanner.nextLine();
            System.out.print("Target Radix (Convert to): ");
            targetRadix = Integer.parseInt(scanner.nextLine());
            if (targetRadix < 1 || targetRadix > 36) {
                System.out.println("error");
                return;
            }
        } catch (Exception e) {
            System.out.println("error");
            return;
        }

        String input = num.replace(".", "");
        for (int i = 0; i < input.length(); i++) {
            if (sourceRadix != 1) {
                try {
                    Integer.parseInt(Character.toString(input.charAt(i)), sourceRadix);
                } catch (Exception e) {
                    System.out.println("error");
                    return;
                }
            }
        }

        if (targetRadix == 1 && sourceRadix == 10) {
            System.out.printf("%.5f",Double.parseDouble(decToOne(num)));
        } else if (targetRadix == 1 && sourceRadix != 10) {
            if (sourceRadix != 10) {
                num = anyToDec(num, sourceRadix);
            }
            System.out.println(Double.parseDouble(decToOne(num)));
        } else if (sourceRadix == 1 && targetRadix == 10) {
            System.out.println(num.length());
        } else if (sourceRadix == 1 && targetRadix != 10) {
            int temp = num.length();
            System.out.println(Integer.toString(temp, targetRadix));
        } else {
            anyToAny(sourceRadix, num, targetRadix);
        }
    }

    static String anyToDec(String fraction, int sourceRadix) {
        if (fraction.contains(".")) {
            String[] arr = fraction.split("\\.");
            double f = 0;
            for (int i = 0; i < arr[1].length(); i++) {
                int place = Integer.parseInt(Character.toString(arr[1].charAt(i)),
                        sourceRadix);
                f += (double) place/Math.pow(sourceRadix, i+1);
            }
            return Integer.toString(Integer.parseInt(arr[0], sourceRadix)) + "." +
                    Double.toString(f).split("\\.")[1];
        }
        return Integer.toString(Integer.parseInt(fraction, sourceRadix));
    }

    static void anyToAny(int sourceRadix, String fraction, int targetRadix) {
        if (sourceRadix != 10) {
            fraction = anyToDec(fraction, sourceRadix);
        }

        if (fraction.contains(".")) {
            String[] arr = fraction.split("\\.");
            String dec = Integer.toString(Integer.parseInt(arr[0]), targetRadix);
            StringBuilder sb = new StringBuilder();
            String frac = fraction;
            while (sb.length() < 5) {
                String[] temp = frac.split("\\.");
                String f = "."+temp[1];
                frac = Double.toString(Double.parseDouble(f) * targetRadix);
                sb.append(Integer.toString(Integer.parseInt(frac.split("\\.")[0]),
                        targetRadix));
            }
            System.out.println(dec+"."+sb);
        } else {
            System.out.println(Integer.toString(Integer.parseInt(fraction), targetRadix));
        }
    }

    static String decToOne(String num) {
        String dec = "";
        num = num.split("\\.")[0];
        for (int i = 0; i < Integer.parseInt(num); i++) {
            dec += "1";
        }
            return dec;
    }
}
