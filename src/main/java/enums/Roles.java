package enums;

public enum Roles {
    ADMIN(1L),
    USER_NORMAL(2L),
    USER_TRADE(3L);

    private final Long value;
    Roles(Long value) {
        this.value= value;
    }
}
