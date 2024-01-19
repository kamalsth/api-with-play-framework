package exception;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ErrorHandle {

    @JsonIgnore
    private final int errorCode;
    private final String errorStatus;
    private final String errorMessage;


    public ErrorHandle(int errorCode, String errorStatus, String errorMessage) {
        this.errorCode = errorCode;
        this.errorStatus = errorStatus;
        this.errorMessage = errorMessage;
    }

    public String getErrorFrom() {
        return "GRPC server";
    }

    public String getErrorStatus() {
        return errorStatus;
    }

    public String getErrorMessage() {
        return errorMessage;
    }


    public int getErrorCode() {
        return errorCode;
    }
}
