package service.serviceimpl;

import com.ks.proto.common.StatusResponse;
import com.ks.proto.leave.*;
import config.MapperConfig;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import model.LeaveRequestModel;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import service.LeaveService;
import utils.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

public class LeaveServiceImpl implements LeaveService {
    @Override
    public CompletionStage<Result> leaveRequest(Http.Request request) {
        LeaveRequestModel leaveRequestModel = Json.fromJson(request.body().asJson(), LeaveRequestModel.class);

        LeaveServiceGrpc.LeaveServiceBlockingStub leaveService = createLeaveServiceStub(request);

        if (leaveService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }

        LeaveRequest leaveRequest = LeaveRequest.newBuilder()
                .setFrom(leaveRequestModel.getFrom())
                .setTo(leaveRequestModel.getTo())
                .setSubject(leaveRequestModel.getSubject())
                .build();

        try {
            LeaveResponse leaveResponse = leaveService.requestLeave(leaveRequest);
            LeaveRequestModel model = MapperConfig.INSTANCE.mapToLeaveRequestModel(leaveResponse);
            return CompletableFuture.completedFuture(ok(Json.toJson(model)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);


        }


    }

    @Override
    public CompletionStage<Result> getAllLeaveRequest(Http.Request request, int pageNumber, int pageSize) {
        LeaveServiceGrpc.LeaveServiceBlockingStub leaveService = createLeaveServiceStub(request);

        if (leaveService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }

        List<LeaveRequestModel> leaveRequestModels = new ArrayList<>();

        try {
            LeaveListResponse leaveListResponse = leaveService.getLeaveList(LeaveListRequest.newBuilder().setPageNumber(pageNumber).setPageSize(pageSize).build()).next();
            leaveListResponse.getLeaveResponseList().forEach(leaveResponse -> {
                leaveRequestModels.add(MapperConfig.INSTANCE.mapToLeaveRequestModel(leaveResponse));
            });
            return CompletableFuture.completedFuture(ok(Json.toJson(leaveRequestModels)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

    @Override
    public CompletionStage<Result> getLeaveRequestById(Http.Request request, String id) {
        LeaveServiceGrpc.LeaveServiceBlockingStub leaveService = createLeaveServiceStub(request);

        if (leaveService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }

        LeaveRequestById leaveRequest = LeaveRequestById.newBuilder()
                .setId(id)
                .build();

        try {
            LeaveResponse leaveResponse = leaveService.getLeave(leaveRequest);
            LeaveRequestModel leaveRequestModel = MapperConfig.INSTANCE.mapToLeaveRequestModel(leaveResponse);
            return CompletableFuture.completedFuture(ok(Json.toJson(leaveRequestModel)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);


        }
    }

    @Override
    public CompletionStage<Result> updateLeaveRequest(Http.Request request, String id) {
        LeaveRequestModel leaveRequestModel = Json.fromJson(request.body().asJson(), LeaveRequestModel.class);

        LeaveServiceGrpc.LeaveServiceBlockingStub leaveService = createLeaveServiceStub(request);

        if (leaveService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }

        LeaveRequest leaveRequest = LeaveRequest.newBuilder()
                .setId(id)
                .setFrom(leaveRequestModel.getFrom())
                .setTo(leaveRequestModel.getTo())
                .setSubject(leaveRequestModel.getSubject())
                .build();

        try {
            LeaveResponse leaveResponse = leaveService.updateLeave(leaveRequest);
            LeaveRequestModel model = MapperConfig.INSTANCE.mapToLeaveRequestModel(leaveResponse);
            return CompletableFuture.completedFuture(ok(Json.toJson(model)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

    @Override
    public CompletionStage<Result> deleteLeaveRequest(Http.Request request, String id) {
        LeaveServiceGrpc.LeaveServiceBlockingStub leaveService = createLeaveServiceStub(request);

        if (leaveService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }

        LeaveRequestById leaveRequest = LeaveRequestById.newBuilder()
                .setId(id)
                .build();

        try {
            StatusResponse statusResponse = leaveService.deleteLeave(leaveRequest);
            return CompletableFuture.completedFuture(ok(Json.toJson(statusResponse.getStatus())));

        } catch (Exception e) {
            return ExceptionUtils.handleException(e);

        }
    }

    @Override
    public CompletionStage<Result> confirmLeaveRequest(Http.Request request) {
        if(request.body().asJson().get("leaveStatus").asText().isEmpty() || request.body().asJson().get("id").asText().isEmpty()){
            return CompletableFuture.completedFuture(Results.badRequest("Empty field !!"));
        }
        model.ConfirmLeaveRequest confirmLeaveRequest = Json.fromJson(request.body().asJson(), model.ConfirmLeaveRequest.class);
        LeaveServiceGrpc.LeaveServiceBlockingStub leaveService = createLeaveServiceStub(request);

        if (leaveService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        try {
            StatusResponse statusResponse = leaveService.confirmLeave(MapperConfig.INSTANCE.mapToConfirmLEaveRequestProto(confirmLeaveRequest));
            return CompletableFuture.completedFuture(ok(Json.toJson(statusResponse.getStatus())));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

    @Override
    public CompletionStage<Result> getAllLeaveRequestByUser(Http.Request request, int pageNumber, int pageSize) {
        LeaveServiceGrpc.LeaveServiceBlockingStub leaveService = createLeaveServiceStub(request);

        if (leaveService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }

        List<LeaveRequestModel> leaveRequestModels = new ArrayList<>();

        try {
            LeaveListResponse leaveListResponse = leaveService.getLeaveRequestListByUser(LeaveListRequest.newBuilder().setPageNumber(pageNumber).setPageSize(pageSize).build()).next();
            leaveListResponse.getLeaveResponseList().forEach(leaveResponse -> {
                leaveRequestModels.add(MapperConfig.INSTANCE.mapToLeaveRequestModel(leaveResponse));
            });
            return CompletableFuture.completedFuture(ok(Json.toJson(leaveRequestModels)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }


    private LeaveServiceGrpc.LeaveServiceBlockingStub createLeaveServiceStub(Http.Request request) {
        String jwtToken = request.headers().get("Authorization").orElse("");

        if (jwtToken.isEmpty()) {
            return null;
        }
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), jwtToken);

        return LeaveServiceGrpc.newBlockingStub(managedChannel)
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));

    }
}
