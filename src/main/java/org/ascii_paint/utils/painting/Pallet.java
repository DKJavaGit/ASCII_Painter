package org.ascii_paint.utils.painting;

/**
 * Class for working with pallets
 * */

public class Pallet {
    private static final char[] BASE_PALLET;
    private static final char[] DETAILED_PALLET;

    static {
        BASE_PALLET = "@#%&*+=-:. ".toCharArray();
        DETAILED_PALLET = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\\\|()1{}[]?-_+~<>i!lI;:,\\\"^'".toCharArray();
    }

    // Getting pallet based of quality
    public static char[] getPallet(Detail detail) {
        return switch (detail) {
            case BASE -> BASE_PALLET;
            case DETAILED -> DETAILED_PALLET;
        };
    }
}
