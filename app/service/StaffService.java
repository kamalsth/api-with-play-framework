package service;

import config.MapperConfig;
import generatedClasses.StaffListRequestOuterClass;
import generatedClasses.StaffListResponseOuterClass;
import generatedClasses.StaffRequestOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import model.Staff;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

public class StaffService {

    public CompletionStage<Result> addStaff(Http.Request request) {
        Staff staff = Json.fromJson(request.body().asJson(), Staff.class);

        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        generatedClasses.StaffRequestOuterClass.StaffRequest staffRequest = generatedClasses.StaffRequestOuterClass.StaffRequest.newBuilder()
                .setStaff(generatedClasses.StaffOuterClass.Staff.newBuilder()
                        .setName(staff.getName())
                        .setPersonalPhone(staff.getPersonalPhone())
                        .setEmergencyContactNumber(staff.getEmergencyContactNumber())
                        .setPosition(staff.getPosition())
                        .setJoinDate(Long.parseLong(staff.getJoinDate()))
                        .setContactRenewDate(Long.parseLong(staff.getContactRenewDate()))
                        .build())
                .build();

        generatedClasses.StaffResponseOuterClass.StaffResponse staff2 = staffService.addStaff(staffRequest);


        Staff staff3 = MapperConfig.INSTANCE.mapToStaff(staff2);
        return CompletableFuture.completedFuture(ok(Json.toJson(staff3)));
    }


    public CompletionStage<Result> getStaffById(Http.Request request, int id) {

        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffRequestOuterClass.StaffRequest1 staffRequest = StaffRequestOuterClass.StaffRequest1.newBuilder()
                .setStaffId(id)
                .build();
        generatedClasses.StaffResponseOuterClass.StaffResponse staff2 = staffService.getStaffInfo(staffRequest);

        Staff staff3 = MapperConfig.INSTANCE.mapToStaff(staff2);
        return CompletableFuture.completedFuture(ok(Json.toJson(staff3)));
    }


    public CompletionStage<Result> updateStaff(Http.Request request, int id) {
        Staff staff = Json.fromJson(request.body().asJson(), Staff.class);

        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        generatedClasses.StaffRequestOuterClass.StaffRequest staffRequest = generatedClasses.StaffRequestOuterClass.StaffRequest.newBuilder()
                .setStaff(generatedClasses.StaffOuterClass.Staff.newBuilder()
                        .setStaffId(id)
                        .setName(staff.getName())
                        .setPersonalPhone(staff.getPersonalPhone())
                        .setEmergencyContactNumber(staff.getEmergencyContactNumber())
                        .setPosition(staff.getPosition())
                        .setJoinDate(Long.parseLong(staff.getJoinDate()))
                        .setContactRenewDate(Long.parseLong(staff.getContactRenewDate()))
                        .build())
                .build();

        generatedClasses.StaffResponseOuterClass.StaffResponse staff2 = staffService.updateStaff(staffRequest);

        Staff staff3 = MapperConfig.INSTANCE.mapToStaff(staff2);
        return CompletableFuture.completedFuture(ok(Json.toJson(staff3)));
    }


    public CompletionStage<Result> deleteStaff(Http.Request request, int id) {
        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffRequestOuterClass.StaffRequest1 staffRequest = StaffRequestOuterClass.StaffRequest1.newBuilder()
                .setStaffId(id)
                .build();
        generatedClasses.StatusResponseOuterClass.StatusResponse statusResponse = staffService.removeStaff(staffRequest);

        return CompletableFuture.completedFuture(ok(Json.toJson(statusResponse.getStatus())));
    }

    public CompletionStage<Result> getAllStaff(Http.Request request) {

        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffListRequestOuterClass.StaffListRequest staffRequest = StaffListRequestOuterClass.StaffListRequest.newBuilder()
                .build();

        List<Staff> staffList = new ArrayList<>();
        StaffListResponseOuterClass.StaffListResponse staffListResponse = staffService
                .getAllStaffInfo(staffRequest).next();
        staffListResponse.getStaffList().forEach(staff ->
                staffList.add(MapperConfig.INSTANCE.mapToListStaff(staff))
        );
        return CompletableFuture.completedFuture(ok(Json.toJson(staffList)));
    }


    private generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub createStaffServiceStub(Http.Request request) {
        String jwtToken = request.headers().get("Authorization").orElse("");

        if (jwtToken.isEmpty()) {
            return null;
        }
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), jwtToken);

        return generatedClasses.StaffServiceGrpc.newBlockingStub(managedChannel)
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));

    }


}
