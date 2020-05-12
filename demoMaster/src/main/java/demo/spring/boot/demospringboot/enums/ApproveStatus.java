package demo.spring.boot.demospringboot.enums;

/**
 * approve_status 审批状态 0: 待审批 1：通过 ：2 拒绝
 */
public enum ApproveStatus {
    WATTING(0), PASSED(1), NOTPASSED(2);

    private int value;

    ApproveStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
