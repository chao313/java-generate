package demo.spring.boot.demospringboot.enums;

/**
 * 是否是黑名单 1 -> 是 0 -> 不是
 */
public enum BlackStatus {
    IN_BLACK(1), NOT_IN_BLACK(0);

    private int value;

    BlackStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
