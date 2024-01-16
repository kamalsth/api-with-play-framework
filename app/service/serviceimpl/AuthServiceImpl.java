package service.serviceimpl;

import com.ks.proto.auth.AuthServiceGrpc;
import com.ks.proto.auth.LoginResponse;
import com.ks.proto.auth.RegisterRequest;
import com.ks.proto.common.StatusResponse;
import config.MapperConfig;
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

import static play.mvc.Results.ok;

public class AuthServiceImpl implements AuthService {
    @Override
    public CompletionStage<Result> login(Http.Request request) {
        Login loginRequest = Json.fromJson(request.body().asJson(), Login.class);
        AuthServiceGrpc.AuthServiceBlockingStub authService = createAuthServiceStub();
        LoginResponse loginResponse = authService
                .login(MapperConfig.INSTANCE.mapToLoginRequest(loginRequest));
        ;
        return CompletableFuture.completedFuture(ok(Json.toJson(loginResponse.getToken())));
    }

    @Override
    public CompletionStage<Result> register(Http.Request request) {
        Register registerRequest = Json.fromJson(request.body().asJson(), Register.class);
        AuthServiceGrpc.AuthServiceBlockingStub authService = createAuthServiceStub();
        StatusResponse registerResponse = authService
                .register(RegisterRequest.newBuilder()
                        .setUser(MapperConfig.INSTANCE.mapToUser(registerRequest))
                        .build());
        return CompletableFuture.completedFuture(ok(Json.toJson(registerResponse.getStatus())));

    }

    private AuthServiceGrpc.AuthServiceBlockingStub createAuthServiceStub() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        return AuthServiceGrpc.newBlockingStub(managedChannel);
    }
}
