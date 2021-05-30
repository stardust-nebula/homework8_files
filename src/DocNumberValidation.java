public class DocNumberValidation {

    public static boolean isNumberLengthValid(String line) {
        int validNumberLength = 15;
        if (line.length() == validNumberLength) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean isPrefixOfNumberValid(String line) {
        String validPrefix1 = "docnum";
        String validPrefix2 = "kontract";

        if (line.startsWith(validPrefix1) || line.startsWith(validPrefix2)) {
            return true;
        } else {
            return false;
        }
    }
}


