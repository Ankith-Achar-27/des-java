public class DESFunction {

    public static String function(String input, String roundKey) {

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("             DES FUNCTION");
            System.out.println("========================================");
            System.out.println();
            System.out.println("[STEP 1: EXPANSION]");
        }

        String expanded = Permutation.expansion(input);

        if (TraceConfig.isDetailed()) {
            System.out.println("Expanded R = " + Permutation.binaryToHex(expanded));
            System.out.println();
            System.out.println("[STEP 2: XOR WITH ROUND KEY]");
            System.out.println("Expanded R = " + Permutation.binaryToHex(expanded));
            System.out.println("Round Key  = " + Permutation.binaryToHex(roundKey));
        }

        String xored = Permutation.xor(expanded, roundKey);

        if (TraceConfig.isDetailed()) {
            System.out.println("XOR Result = " + Permutation.binaryToHex(xored));
            System.out.println();
            System.out.println("[STEP 3: S-BOX SUBSTITUTION]");
        }

        String substituted = SBox.substitute(xored);

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("[STEP 4: STRAIGHT PERMUTATION]");
        }

        String output = Permutation.straightPermutation(substituted);

        if (TraceConfig.isDetailed()) {
            System.out.println("Function output = " + Permutation.binaryToHex(output));
        }

        return output;
    }
}
