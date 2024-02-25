package online.hackpi.utils;

public enum ProductPath {
    ROOT(System.getProperty("user.dir"));
    private final String value;
    private ProductPath(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
