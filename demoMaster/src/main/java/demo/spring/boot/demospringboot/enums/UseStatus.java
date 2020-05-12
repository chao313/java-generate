package demo.spring.boot.demospringboot.enums;

/**
 * 是否启用 1-启用 0-未启用
 */
public enum UseStatus {
    IN_USE(1), NOT_IN_DELETE(0);

    private int value;

    UseStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
