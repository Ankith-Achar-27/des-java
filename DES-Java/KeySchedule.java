public class KeySchedule {

    public static String[] generateKeys(String key) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("          DES KEY SCHEDULE");
        System.out.println("========================================");

        System.out.println();
        System.out.println("Original 64-bit key:");
        System.out.println(key);

        String binaryKey = Permutation.hexToBinary(key);

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("[HEX → BINARY]");
            System.out.println("Hex    : " + key);
            System.out.println("Binary : " + Permutation.binaryToHex(binaryKey));
        }

        String permutedKey = Permutation.permute(binaryKey, DESTables.PC1);

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("[PC-1]");
            System.out.println("64-bit key : " + Permutation.binaryToHex(binaryKey));
            System.out.println("56-bit key : " + Permutation.binaryToHex(permutedKey));
        }

        String C = Permutation.splitLeft(permutedKey);
        String D = Permutation.splitRight(permutedKey);

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("[Split]");
            System.out.println("C0 = " + Permutation.binaryToHex(C));
            System.out.println("D0 = " + Permutation.binaryToHex(D));
        }

        String[] roundKeys = new String[16];

        for (int round = 0; round < 16; round++) {

            int shift = DESTables.SHIFT_SCHEDULE[round];

            C = Permutation.leftCircularShift(C, shift);
            D = Permutation.leftCircularShift(D, shift);

            String combined = C + D;
            String roundKey = Permutation.permute(combined, DESTables.PC2);
            roundKeys[round] = roundKey;

            if (TraceConfig.isDetailed()) {
                System.out.println();
                System.out.println("----------------------------------------");
                System.out.println("ROUND " + (round + 1));
                System.out.println("----------------------------------------");
                System.out.println("Shift amount = " + shift);
                System.out.println("C" + (round + 1) + " = " + Permutation.binaryToHex(C));
                System.out.println("D" + (round + 1) + " = " + Permutation.binaryToHex(D));
                System.out.println("C" + (round + 1) + "D" + (round + 1) + " = " + Permutation.binaryToHex(combined));
                System.out.println("[PC-2]");
                System.out.println("K" + (round + 1) + " = " + Permutation.binaryToHex(roundKey));
            } else {
                System.out.printf("K%-2d = %s%n", round + 1, Permutation.binaryToHex(roundKey));
            }
        }

        return roundKeys;
    }
}
