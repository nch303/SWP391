package koicare.koiCareProject.exception;


// tạo ra các Error code cho từng lỗi khác nhau

public enum ErrorCode {
    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USER_EXISTED(1001, "User existed"),
    INVALID_KEY(1002, "Invalid message key"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters!"),
    INVALID_PASSWORD(1004, "Password must be at least 8 characters!"),
    INVALID_FIRSTNAME(1005, "Firstname must be at least 8 characters!"),
    USER_NOT_EXISTED(1006, "User not existed"),
    UNAUTHENTICATED(1007, "Unauthenticated"),
    LOGIN_FAIL(1008, "Username or Password invalid"),
    USERNAME_EXISTED(1009,"Duplicate code");


    private int code;
    private String message;

    ErrorCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}