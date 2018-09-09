package pl.sdacademy.vending.util;

public class StringUtils {


    public static String adjustText(String textToMatch, Integer expectedTextLength) {
        if (textToMatch.length() < expectedTextLength) {
            int requiredSpaces = expectedTextLength - textToMatch.length();
            int spacesPerSide = requiredSpaces / 2;
            int spacesOnLeft = (requiredSpaces + 1) / 2;
            int spacesOnRight = requiredSpaces / 2;
            return multiplyText(" ", spacesOnLeft) + textToMatch + multiplyText(" ", spacesOnRight);
        } else {
            return textToMatch.substring(0, expectedTextLength);
        }
    }

    public static String multiplyText(String textToMultiply, Integer times) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < times; i++) {
            stringBuilder = stringBuilder.append(textToMultiply);
        }
        return stringBuilder.toString();
    }

//    public static String adjustNumber(Long numberToAdjust) {
//        StringBuilder stringBuilder = new StringBuilder();
//        String number = "" + numberToAdjust;
//        if (number.length() < 2) {
//            return stringBuilder.append("0,0").append("" + numberToAdjust).toString();
//        } else if (number.length() == 2) {
//            return stringBuilder.append("0,").append("" + numberToAdjust).toString();
//        } else if (number.length() > 2) {
//            for (int i = number.length() - 1; i > number.length() - 3; i--) {
//                stringBuilder = stringBuilder.append(number.charAt(i));
//            }
//            stringBuilder.append(",");
//            int spaceCounter = 0;
//            for (int i = number.length() - 3; i >= 0; i--) {
//                stringBuilder = stringBuilder.append(number.charAt(i));
//                spaceCounter++;
//                if (spaceCounter % 3 == 0) {
//                    stringBuilder = stringBuilder.append(" ");
//                }
//            }
//            return stringBuilder.reverse().toString().trim();
//        }
//        return "";
//    }

    public static String formatMoney(Long money) {
        return formatMoneyIntegrals(money) + "," + formatMoneyDecimals(money);
    }

    private static String formatMoneyIntegrals(Long money) {
        String integrals = Long.toString(money / 100);
        StringBuilder formattedIntegralsBuilder = new StringBuilder();
        int spaceCounter = 0;
        for (int charIndex = integrals.length() - 1; charIndex >= 0; charIndex--) {
            formattedIntegralsBuilder = formattedIntegralsBuilder.append(integrals.charAt(charIndex));
            spaceCounter++;
            if (spaceCounter >= 3) {
                formattedIntegralsBuilder = formattedIntegralsBuilder.append(" ");
                spaceCounter = 0;
            }
        }
        return formattedIntegralsBuilder.reverse().toString().trim();
    }

    private static String formatMoneyDecimals(Long money) {
        String cents = Long.toString(money % 100);
        if (cents.length() == 1) {
            return "0" + cents;
        }
        return cents;
    }

}
