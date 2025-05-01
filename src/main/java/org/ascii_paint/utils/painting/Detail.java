package org.ascii_paint.utils.painting;

/**
 * Enum class of quality types
 * */

public enum Detail {
    BASE,
    DETAILED;

    // Getting quality from text
    public static Detail getDetail(String quality) {
        return switch (quality) {
            case "Базовый" -> BASE;
            case "Детальный" -> DETAILED;
            default -> BASE;
        };
    }
}
