package exception;

public class ErrorHandle {

    private final String errorStatus;
    private final String errorMessage;


    public ErrorHandle(String errorStatus, String errorMessage) {
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


}
