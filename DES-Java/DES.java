public class DES {

    public static String encrypt(String plaintext, String key) {

        System.out.println();
        System.out.println("========================================");
        System.out.println("           DES ENCRYPTION");
        System.out.println("========================================");
        System.out.println();
        System.out.println("Plaintext : " + plaintext);
        System.out.println("Key       : " + key);

        String[] roundKeys = KeySchedule.generateKeys(key);

        String binaryPlaintext = Permutation.hexToBinary(plaintext);

        System.out.println();
        System.out.println("========================================");
        System.out.println("       INITIAL PERMUTATION");
        System.out.println("========================================");

        String permutedBlock = Permutation.initialPermutation(binaryPlaintext);

        String left = Permutation.splitLeft(permutedBlock);
        String right = Permutation.splitRight(permutedBlock);

        System.out.println();
        System.out.println("[Split 64-bit block]");
        System.out.println("L0 = " + Permutation.binaryToHex(left));
        System.out.println("R0 = " + Permutation.binaryToHex(right));

        for (int round = 0; round < 16; round++) {

            System.out.println();
            System.out.println("----------------------------------------");
            System.out.println("ROUND " + (round + 1));
            System.out.println("----------------------------------------");

            System.out.println("L" + round + " = " + Permutation.binaryToHex(left));
            System.out.println("R" + round + " = " + Permutation.binaryToHex(right));
            System.out.println("K" + (round + 1) + " = " + Permutation.binaryToHex(roundKeys[round]));

            String oldRight = right;

            String newRight = Mixer.mixer(
                    left,
                    right,
                    roundKeys[round]
            );

            left = oldRight;
            right = newRight;

            System.out.println("L" + (round + 1) + " = " + Permutation.binaryToHex(left));
            System.out.println("R" + (round + 1) + " = " + Permutation.binaryToHex(right));
        }

        System.out.println();
        System.out.println("========================================");
        System.out.println("             FINAL SWAP");
        System.out.println("========================================");
        System.out.println("L16 = " + Permutation.binaryToHex(left));
        System.out.println("R16 = " + Permutation.binaryToHex(right));

        String swapped = right + left;
        System.out.println("After Swap = " + Permutation.binaryToHex(swapped));

        String combined = Permutation.combine(right, left);

        System.out.println();
        System.out.println("[Combine]");
        System.out.println("Combined block = " + Permutation.binaryToHex(combined));

        String cipherBinary = Permutation.finalPermutation(combined);
        String ciphertext = Permutation.binaryToHex(cipherBinary);

        System.out.println();
        System.out.println("========================================");
        System.out.println("             CIPHERTEXT");
        System.out.println("========================================");
        System.out.println("HEX    : " + ciphertext);

        return ciphertext;
    }
}
