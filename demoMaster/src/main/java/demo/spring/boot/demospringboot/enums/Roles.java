package demo.spring.boot.demospringboot.enums;

public enum Roles {
    GUEST("GUEST"),//游客
    USER("USER"),//游客
    ADMIN("ADMIN"),//管理员
    ;
    String value;

    Roles(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
