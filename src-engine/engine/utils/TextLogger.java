package engine.utils;

import static game.architecture.Constants.K;

/**
 * Logger utility.
 */
public final class TextLogger {

    /* ========== PUBLIC ========== */
    public static String twoDecimal(double number) {

        StringBuilder suffix = new StringBuilder();

        while (number > K || number < -K) {
            suffix.append("k");
            number /= K;
        }

        return String.format("%.2f" + suffix, number).toString();
    }

    /* ========== PRIVATE ========== */
    private TextLogger() {
    }
}