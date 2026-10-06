public class Main {

    public static void main(String[] args) {

        // NORMAL = clean presentation trace
        // DETAILED = complete step-by-step trace
        TraceConfig.setMode(TraceConfig.Mode.NORMAL);

        String plaintext = "0123456789ABCDEF";
        String key = "133457799BBCDFF1";

        String ciphertext = DES.encrypt(plaintext, key);

        System.out.println();
        System.out.println("========================================");
        System.out.println("              VERIFICATION");
        System.out.println("========================================");
        System.out.println("Expected   : 85E813540F0AB405");
        System.out.println("Calculated : " + ciphertext);
        System.out.println("Status     : " +
                ("85E813540F0AB405".equals(ciphertext) ? "PASS" : "FAIL"));
        System.out.println("========================================");
    }
}
