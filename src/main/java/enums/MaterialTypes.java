package enums;

public enum MaterialTypes {
    COPPER(1L),
    LEAD(2L),
    BRASS(3L),
    ZINC(4L);

    private final Long value;
    MaterialTypes(Long value) {
        this.value= value;
    }

    public Long getId() {
        return this.value;
    }
}
