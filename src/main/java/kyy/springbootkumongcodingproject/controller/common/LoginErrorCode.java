package kyy.springbootkumongcodingproject.controller.common;

public enum LoginErrorCode {
    OperationNotAuthorized(6000,"Operation not authorized"),
    DuplicateIdFound(6001,"Duplicate Id"),
    //...
    UnrecognizedRole(6010,"Unrecognized Role");
    private int code;
    private String description;
    private LoginErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
    public int getCode() {
        return code;
    }
    public String getDescription() {
        return description;
    }
}
