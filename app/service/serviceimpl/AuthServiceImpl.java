package service.serviceimpl;

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
        generatedClasses.AuthServiceGrpc.AuthServiceBlockingStub authService = createAuthServiceStub();
        generatedClasses.LoginResponseOuterClass.LoginResponse loginResponse = authService
                .login(MapperConfig.INSTANCE.mapToLoginRequest(loginRequest));
        ;
        return CompletableFuture.completedFuture(ok(Json.toJson(loginResponse.getToken())));
    }

    @Override
    public CompletionStage<Result> register(Http.Request request) {
        Register registerRequest = Json.fromJson(request.body().asJson(), Register.class);
        generatedClasses.AuthServiceGrpc.AuthServiceBlockingStub authService = createAuthServiceStub();
        generatedClasses.RegisterResponseOuterClass.RegisterResponse registerResponse = authService
                .register(generatedClasses.RegisterRequestOuterClass.RegisterRequest.newBuilder()
                        .setUser(MapperConfig.INSTANCE.mapToUser(registerRequest))
                        .build());
        return CompletableFuture.completedFuture(ok(Json.toJson(registerResponse.getStatus())));

    }

    private generatedClasses.AuthServiceGrpc.AuthServiceBlockingStub createAuthServiceStub() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        return generatedClasses.AuthServiceGrpc.newBlockingStub(managedChannel);
    }
}
