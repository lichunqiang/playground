package lzuer.net.bubblelayout;

/**
 * what is enum
 */
enum ArrowDirection {
    LEFT(0),
    RIGHT(1),
    TOP(2),
    BOTTOM(3);

    private int value;

    ArrowDirection(int value) {
        this.value = value;
    }

    public static ArrowDirection fromInt(int value) {
        for (ArrowDirection arrowDirection : ArrowDirection.values()) {
            if (value == arrowDirection.getValue()) {
                return arrowDirection;
            }
        }
        return LEFT;
    }

    public int getValue() {
        return value;
    }
}
