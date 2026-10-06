public class SBox {

    public static String substitute(String input) {

        StringBuilder output = new StringBuilder();

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("[S-BOX SUBSTITUTION]");
            System.out.println("48-bit input: " + Permutation.binaryToHex(input));
        }

        for (int i = 0; i < 8; i++) {

            String sixBits = input.substring(i * 6, i * 6 + 6);

            int row = Integer.parseInt(
                    "" + sixBits.charAt(0) + sixBits.charAt(5), 2);

            int column = Integer.parseInt(
                    sixBits.substring(1, 5), 2);

            int value = DESTables.S_BOXES[i][row][column];

            String fourBits = String.format(
                    "%4s", Integer.toBinaryString(value))
                    .replace(' ', '0');

            if (TraceConfig.isDetailed()) {
                System.out.println();
                System.out.println("S" + (i + 1));
                System.out.println("Input  = " + sixBits);
                System.out.println("Row    = " + row +
                        " (" + toTwoBitBinary(row) + ")");
                System.out.println("Column = " + column +
                        " (" + toFourBitBinary(column) + ")");
                System.out.println("Value  = " + value);
                System.out.println("Output = " + fourBits);
            }

            output.append(fourBits);
        }

        String result = output.toString();

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("32-bit S-Box output: " + Permutation.binaryToHex(result));
        } else {
            System.out.println("   S-Box output = " + Permutation.binaryToHex(result));
        }

        return result;
    }

    private static String toTwoBitBinary(int value) {
        return String.format("%2s", Integer.toBinaryString(value))
                .replace(' ', '0');
    }

    private static String toFourBitBinary(int value) {
        return String.format("%4s", Integer.toBinaryString(value))
                .replace(' ', '0');
    }
}
