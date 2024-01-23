package service.serviceimpl;

import com.google.protobuf.Empty;
import com.ks.proto.auth.AuthServiceGrpc;
import com.ks.proto.auth.LoginResponse;
import com.ks.proto.auth.RegisterRequest;
import com.ks.proto.common.StatusResponse;
import com.ks.proto.staff.StaffServiceGrpc;
import com.ks.proto.user.UserResponse;
import config.MapperConfig;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import model.Login;
import model.Register;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import service.AuthService;
import utils.ExceptionUtils;

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
            return ExceptionUtils.handleException(e);
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
            return ExceptionUtils.handleException(e);
        }


    }

    @Override
    public CompletionStage<Result> aboutMe(Http.Request request) {
        AuthServiceGrpc.AuthServiceBlockingStub authService = createBlockingStub(request);
        if (authService == null) {
            return CompletableFuture.completedFuture(unauthorized("Unauthorized !! Invalid token"));
        }
        try {
            UserResponse userResponse = authService.aboutMe(Empty.newBuilder().build());
            return CompletableFuture.completedFuture(ok(Json.toJson(MapperConfig.INSTANCE.mapToUserDetail(userResponse))));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }

    }

    private AuthServiceGrpc.AuthServiceBlockingStub createAuthServiceStub() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        return AuthServiceGrpc.newBlockingStub(managedChannel);
    }


    private AuthServiceGrpc.AuthServiceBlockingStub createBlockingStub(Http.Request request) {
        String jwtToken = request.headers().get("Authorization").orElse("");
        if (jwtToken.isEmpty()) {
            return null;
        }
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), jwtToken);

        return AuthServiceGrpc.newBlockingStub(managedChannel)
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));


    }

}
