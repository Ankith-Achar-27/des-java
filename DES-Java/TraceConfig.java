public class TraceConfig {

    public enum Mode {
        NORMAL,
        DETAILED
    }

    private static Mode mode = Mode.NORMAL;

    public static void setMode(Mode newMode) {
        mode = newMode;
    }

    public static boolean isDetailed() {
        return mode == Mode.DETAILED;
    }
}
