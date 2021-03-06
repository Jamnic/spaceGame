package game.engine.utils;

public final class Text {

    /* ========== PRIVATE ========== */
    private Text() {
    }

    public static String text(double number) {

        StringBuilder suffix = new StringBuilder();

        while (number > 1000 || number < -1000) {
            suffix.append("k");
            number /= 1000;
        }

        return String.format("%.2f" + suffix, number).toString();
    }

}
