package koicare.koiCareProject.exception;


// tạo ra các Error code cho từng lỗi khác nhau

public enum ErrorCode {

    UNCATEGORIZED_EXCEPTION(9999, "Uncategorized error"),
    USER_EXISTED(1001, "User existed"),
    INVALID_KEY(1002, "Invalid message key"),
    USERNAME_INVALID(1003, "Username must be at least 3 characters!"),
    INVALID_PASSWORD(1004, "Password must be at least 6 characters!"),
    INVALID_FIRSTNAME(1005, "Firstname must be at least 8 characters!"),
    USER_NOT_EXISTED(1006, "User not existed"),
    UNAUTHENTICATED(1007, "Unauthenticated"),
    LOGIN_FAIL(1008, "Username or Password invalid"),
    USERNAME_EXISTED(1009,"Username existed"),
    VARIETY_NOT_EXISTED(1010,"Variety not existed"),
    KOIFISH_NOT_EXISTED(1011,"Koifish not existed"),
    POND_NOT_EXISTED(1012, "POND_NOT_EXISTED"),
    KOISTATUS_NOT_EXISTED(1013,"Koistatus not existed"),
    KOIREPORT_NOT_EXISTED(1014,"Koireport not existed"),
    KOIREPORT_EXISTED(1014,"Koireport Date existed"),
    KOISTANDARD_NOT_EXISTED(1014,"Koistandard not existed"),
    WATER_REPORT_NOT_EXISTED(1015, "WaterReport not existed"),
    WATER_REPORT_EXISTED(1015, "WaterReport Date existed"),
    LIST_NOT_EXISTED(1016, "List not existed"),
    EMAIL_EXISTED(1017, "Email is duplicated"),
    PHONE_EXISTED(1017, "Phone is duplicated"),
    EMPTY_TOKEN(1018, "Empty token"),
    FISH_IS_EXISTED_IN_POND(1019, "Cannot be deleted because there are still fish in the pond"),
    FISH_IS_NOT_EXISTED_IN_POND(1020, "Your pond don't have any fishes"),
    TEMP_TOO_HOT(1021, "Temperature is too high, please control lower 31."),
    TEMP_TOO_COLD(1021, "Temperature is too low, please control at least 0."),
    POST_DOES_NOT_EXIST(1022, "Post does not exist"),
    WRONG_PASSWORD(1023, "Your old password is not correct"),
    PRODUCT_TYPE_IS_NOT_EXISTED(1024, "Product type is not existed"),
    PAYMENT_METHOD_NOT_FOUND(1025, "Payment mehtod not found!!"),
    MEMBER_PACKAGE_IS_NOT_EXISTED(1026, "Member package is not existed"),
    ACCOUNT_IS_NOT_EXISTED(1026, "Account is not existed"),
    TEMPCOEF_IS_NOT_EXISTED(1027, "TempCoef is not existed"),
    FEEDCOEF_IS_NOT_EXISTED(1028, "FeedCoef is not existed"),
    PACKAGE_IS_NOT_EXISTED(1029, "Package is not existed"),
    ORDER_IS_NOT_EXISTED(1030, "Order is not existed"),

    RUN_OUT_POST(1031, "You have run out of posts to submit or duration have been validated."),
    POND_STANDARD_NOT_EXISTED(1032, "PondStandard is not existed"),
    WATER_STANDARD_NOT_EXISTED(1033, "WaterStandard is not existed"),
    WATER_STANDARD_IS_EXISTED(1034, "Can not create new Water Standard because it's already existed"),
    BLOG_NOT_EXISTED(1035, "Post not found"),

    ;




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
