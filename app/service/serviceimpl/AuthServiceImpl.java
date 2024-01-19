package service.serviceimpl;

import com.ks.proto.auth.AuthServiceGrpc;
import com.ks.proto.auth.LoginResponse;
import com.ks.proto.auth.RegisterRequest;
import com.ks.proto.common.StatusResponse;
import config.MapperConfig;
import exception.HandleGrpcException;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.Login;
import model.Register;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import service.AuthService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.*;

public class AuthServiceImpl implements AuthService {
    @Override
    public CompletionStage<Result> login(Http.Request request) {
        Login loginRequest = Json.fromJson(request.body().asJson(), Login.class);

        try {
            AuthServiceGrpc.AuthServiceBlockingStub authService = createAuthServiceStub();
            LoginResponse loginResponse = authService
                    .login(MapperConfig.INSTANCE.mapToLoginRequest(loginRequest));
            return CompletableFuture.completedFuture(ok(Json.toJson(loginResponse.getToken())));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(forbidden(Json.toJson(HandleGrpcException.handleGrpcException(e))));
        }
    }

    @Override
    public CompletionStage<Result> register(Http.Request request) {
        Register registerRequest = Json.fromJson(request.body().asJson(), Register.class);
        try {
            AuthServiceGrpc.AuthServiceBlockingStub authService = createAuthServiceStub();
            StatusResponse registerResponse = authService
                    .register(RegisterRequest.newBuilder()
                            .setUser(MapperConfig.INSTANCE.mapToUser(registerRequest))
                            .build());
            return CompletableFuture.completedFuture(ok(Json.toJson(registerResponse.getStatus())));
        } catch (Exception e) {
            return CompletableFuture.completedFuture(internalServerError(Json.toJson(HandleGrpcException.handleGrpcException(e))));
        }

    }

    private AuthServiceGrpc.AuthServiceBlockingStub createAuthServiceStub() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        return AuthServiceGrpc.newBlockingStub(managedChannel);
    }
}
