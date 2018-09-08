package enumeration.response;

public enum ResponseEnum {
    UNKNOW_ERROR(-1, "unknow error"),
    SUCCESS(0, "success");

    private int code;
    private String description;

    ResponseEnum(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "ResponseEnum{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}
