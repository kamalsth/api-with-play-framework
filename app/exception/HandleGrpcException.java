package exception;

import io.grpc.StatusRuntimeException;

public class HandleGrpcException extends ErrorHandle {

    public HandleGrpcException(String errorStatus, String errorMessage) {
        super(errorStatus, errorMessage);
    }

    public static HandleGrpcException handleGrpcException(Exception e) {
        if (e instanceof StatusRuntimeException statusRuntimeException) {
            return new HandleGrpcException(statusRuntimeException.getStatus().getCode().name(), statusRuntimeException.getStatus().getDescription());
        } else {
            String[] message = e.getMessage().split(":", 2);
            return new HandleGrpcException(message[0], message[1]);
        }

    }

}
