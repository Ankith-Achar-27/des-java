public class Mixer {

    public static String mixer(
            String leftBlock,
            String rightBlock,
            String roundKey) {

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("========================================");
            System.out.println("                 MIXER");
            System.out.println("========================================");
            System.out.println("Left Block  = " + Permutation.binaryToHex(leftBlock));
            System.out.println("Right Block = " + Permutation.binaryToHex(rightBlock));
            System.out.println("Round Key   = " + Permutation.binaryToHex(roundKey));
        }

        String T1 = rightBlock;

        if (TraceConfig.isDetailed()) {
            System.out.println();
            System.out.println("[STEP 1: COPY RIGHT BLOCK]");
            System.out.println("T1 = " + Permutation.binaryToHex(T1));
            System.out.println();
            System.out.println("[STEP 2: FUNCTION(T1, ROUND KEY)]");
        }

        String T2 = DESFunction.function(T1, roundKey);

        if (TraceConfig.isDetailed()) {
            System.out.println("T2 = " + Permutation.binaryToHex(T2));
            System.out.println();
            System.out.println("[STEP 3: LEFT BLOCK XOR T2]");
            System.out.println("Left Block = " + Permutation.binaryToHex(leftBlock));
            System.out.println("T2         = " + Permutation.binaryToHex(T2));
        }

        String T3 = Permutation.xor(leftBlock, T2);

        if (TraceConfig.isDetailed()) {
            System.out.println("T3 = " + Permutation.binaryToHex(T3));
            System.out.println();
            System.out.println("[STEP 4: COPY T3 INTO RIGHT BLOCK]");
            System.out.println("New Right Block = " + Permutation.binaryToHex(T3));
        }

        if (!TraceConfig.isDetailed()) {
            System.out.println("   f(R, K)      = " + Permutation.binaryToHex(T2));
            System.out.println("   L XOR f(R,K) = " + Permutation.binaryToHex(T3));
        }

        return T3;
    }
}
