package utils;

import exception.HandleGrpcException;
import play.libs.Json;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.status;

public class ExceptionUtils {


    public static CompletionStage<Result> handleException(Exception e) {
        HandleGrpcException grpcException = HandleGrpcException.handleGrpcException(e);
        return CompletableFuture.completedFuture(status(grpcException.getErrorCode(), Json.toJson(grpcException)));
    }
}
