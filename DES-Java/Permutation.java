public class Permutation {

    public static String permute(String input, int[] table) {

        StringBuilder output = new StringBuilder();

        for (int position : table) {

            // DES tables use 1-based positions.
            // Java String indexing uses 0-based positions.
            output.append(input.charAt(position - 1));
        }

        return output.toString();
    }


    public static String initialPermutation(String input) {

        String output = permute(input, DESTables.IP);

        if (TraceConfig.isDetailed()) {
            System.out.println("\n[Initial Permutation]");
            System.out.println("Input  : " + binaryToHex(input));
            System.out.println("Output : " + binaryToHex(output));
        }

        return output;
    }


    public static String finalPermutation(String input) {

        String output = permute(input, DESTables.FP);

        if (TraceConfig.isDetailed()) {
            System.out.println("\n[Final Permutation]");
            System.out.println("Input  : " + binaryToHex(input));
            System.out.println("Output : " + binaryToHex(output));
        } else {
            System.out.println("Final Permutation = " + binaryToHex(output));
        }

        return output;
    }


    public static String expansion(String input) {

        String output = permute(input, DESTables.EXPANSION);

        if (TraceConfig.isDetailed()) {
            System.out.println("\n[Expansion Permutation]");
            System.out.println("Input  : " + binaryToHex(input));
            System.out.println("Output : " + binaryToHex(output));
        }

        return output;
    }


    public static String straightPermutation(String input) {

        String output = permute(input, DESTables.P);

        if (TraceConfig.isDetailed()) {
            System.out.println("\n[Straight Permutation]");
            System.out.println("Input  : " + binaryToHex(input));
            System.out.println("Output : " + binaryToHex(output));
        }

        return output;
    }

    public static String xor(String a, String b) {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < a.length(); i++) {

            if (a.charAt(i) == b.charAt(i)) {
                result.append('0');
            } else {
                result.append('1');
            }
        }

        return result.toString();
    }


    public static String splitLeft(String block) {
        return block.substring(0, block.length() / 2);
    }


    public static String splitRight(String block) {
        return block.substring(block.length() / 2);
    }


    public static String combine(String left, String right) {
        return left + right;
    }
    public static String leftCircularShift(String input, int positions) {

        positions = positions % input.length();

        return input.substring(positions)
                + input.substring(0, positions);
    }
    public static String hexToBinary(String hex) {

        StringBuilder binary = new StringBuilder();

        for (char c : hex.toUpperCase().toCharArray()) {

            switch (c) {

                case '0' -> binary.append("0000");
                case '1' -> binary.append("0001");
                case '2' -> binary.append("0010");
                case '3' -> binary.append("0011");
                case '4' -> binary.append("0100");
                case '5' -> binary.append("0101");
                case '6' -> binary.append("0110");
                case '7' -> binary.append("0111");
                case '8' -> binary.append("1000");
                case '9' -> binary.append("1001");
                case 'A' -> binary.append("1010");
                case 'B' -> binary.append("1011");
                case 'C' -> binary.append("1100");
                case 'D' -> binary.append("1101");
                case 'E' -> binary.append("1110");
                case 'F' -> binary.append("1111");

                default ->
                        throw new IllegalArgumentException(
                                "Invalid hexadecimal character: " + c
                        );
            }
        }

        return binary.toString();
    }
    public static String binaryToHex(String binary) {
        if (binary == null || binary.isEmpty()) {
            return "";
        }

        int remainder = binary.length() % 4;
        if (remainder != 0) {
            binary = "0".repeat(4 - remainder) + binary;
        }

        StringBuilder hex = new StringBuilder();

        for (int i = 0; i < binary.length(); i += 4) {

            String group =
                    binary.substring(i, i + 4);

            int value =
                    Integer.parseInt(group, 2);

            hex.append(
                    Integer.toHexString(value)
                            .toUpperCase()
            );
        }

        return hex.toString();
    }
}
