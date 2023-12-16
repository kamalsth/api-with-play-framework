package service.serviceimpl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.Login;
import model.Register;
import play.libs.Json;
import play.mvc.Result;
import service.AuthService;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

public class AuthServiceImpl implements AuthService {
    @Override
    public CompletionStage<Result> login(Login loginRequest) {
        generatedClasses.AuthServiceGrpc.AuthServiceBlockingStub authService = createAuthServiceStub();
        generatedClasses.LoginResponseOuterClass.LoginResponse loginResponse = authService
                .login(generatedClasses.LoginRequestOuterClass.LoginRequest.newBuilder()
                        .setUsername(loginRequest.getUsername())
                        .setPassword(loginRequest.getPassword())
                        .build());
        return CompletableFuture.completedFuture(ok(Json.toJson(loginResponse.getToken())));
    }

    @Override
    public CompletionStage<Result> register(Register registerRequest) {
        generatedClasses.AuthServiceGrpc.AuthServiceBlockingStub authService = createAuthServiceStub();
        generatedClasses.RegisterResponseOuterClass.RegisterResponse registerResponse = authService
                .register(generatedClasses.RegisterRequestOuterClass.RegisterRequest.newBuilder()
                        .setUser(generatedClasses.UserOuterClass.User.newBuilder()
                                .setUsername(registerRequest.getUsername())
                                .setPassword(registerRequest.getPassword())
                                .setName(registerRequest.getName())
                                .setEmail(registerRequest.getEmail())
                                .setPhone(registerRequest.getPhone())
                                .build())
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
