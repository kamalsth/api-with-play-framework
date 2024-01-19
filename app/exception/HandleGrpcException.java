package exception;

public class HandleGrpcException {
    private final String errorFrom;
    private final String errorMessage;


    public HandleGrpcException(String errorFrom, String errorMessage) {
        this.errorFrom = errorFrom;
        this.errorMessage = errorMessage;
    }



    public String getErrorFrom() {
        return errorFrom;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static HandleGrpcException handleGrpcException(Exception e) {
        return new HandleGrpcException("GRPC Server", e.getMessage());
    }
}
