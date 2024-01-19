package exception;

import io.grpc.StatusRuntimeException;
import play.mvc.Http;

public class HandleGrpcException extends ErrorHandle {

    public HandleGrpcException(int errorCode,String errorStatus, String errorMessage) {
        super(errorCode,errorStatus, errorMessage);
    }

    public static HandleGrpcException handleGrpcException(Exception e) {
        if (e instanceof StatusRuntimeException statusRuntimeException) {
            return switch (statusRuntimeException.getStatus().getCode().name()) {
                case "INVALID_ARGUMENT" ->
                        new HandleGrpcException(Http.Status.BAD_REQUEST, statusRuntimeException.getStatus().getCode().name(), statusRuntimeException.getStatus().getDescription());
                case "UNAUTHENTICATED" ->
                        new HandleGrpcException(Http.Status.UNAUTHORIZED, statusRuntimeException.getStatus().getCode().name(), "Unauthorized !! Invalid token");
                case "ALREADY_EXISTS" ->
                        new HandleGrpcException(Http.Status.CONFLICT, statusRuntimeException.getStatus().getCode().name(),statusRuntimeException.getStatus().getDescription());
                case "NOT_FOUND" ->
                        new HandleGrpcException(Http.Status.NOT_FOUND, statusRuntimeException.getStatus().getCode().name(), statusRuntimeException.getStatus().getDescription());
                case "INTERNAL" ->
                        new HandleGrpcException(Http.Status.INTERNAL_SERVER_ERROR, statusRuntimeException.getStatus().getCode().name(), statusRuntimeException.getStatus().getDescription());
                case "UNAVAILABLE" ->
                        new HandleGrpcException(Http.Status.SERVICE_UNAVAILABLE, statusRuntimeException.getStatus().getCode().name(), "Service unavailable");
                case "DEADLINE_EXCEEDED" ->
                        new HandleGrpcException(Http.Status.REQUEST_TIMEOUT, statusRuntimeException.getStatus().getCode().name(), statusRuntimeException.getStatus().getDescription());
                case "PERMISSION_DENIED" ->
                        new HandleGrpcException(Http.Status.FORBIDDEN, statusRuntimeException.getStatus().getCode().name(), "Permission denied");
                case "RESOURCE_EXHAUSTED" ->
                        new HandleGrpcException(Http.Status.TOO_MANY_REQUESTS, statusRuntimeException.getStatus().getCode().name(), statusRuntimeException.getStatus().getDescription());
                case "UNIMPLEMENTED" ->
                        new HandleGrpcException(Http.Status.NOT_IMPLEMENTED, statusRuntimeException.getStatus().getCode().name(), statusRuntimeException.getStatus().getDescription());
                default ->
                        new HandleGrpcException(Http.Status.INTERNAL_SERVER_ERROR, statusRuntimeException.getStatus().getCode().name(), "Unknown error");
            };
        } else {
            String[] messageArray = e.getMessage().split(":", 2);
            return new HandleGrpcException(Http.Status.INTERNAL_SERVER_ERROR, messageArray[0], messageArray[1]);
        }
    }

}
