package demo.spring.boot.demospringboot.enums;

/**
 * 是否删除 1 -> 删除 0 -> 未删除
 */
public enum DeleteStatus {
    IN_DELETE(1), NOT_IN_DELETE(0);

    private int value;

    DeleteStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
