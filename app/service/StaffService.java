package service;

import config.MapperConfig;
import generatedClasses.StaffListRequestOuterClass;
import generatedClasses.StaffListResponseOuterClass;
import generatedClasses.StaffRequestOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import model.Staff;
import play.libs.Json;
import play.mvc.Result;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

public class StaffService {

    public CompletionStage<Result> addStaff(Staff staff) {

        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub();
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


    public CompletionStage<Result> getStaffById(int id) {

        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub();
        StaffRequestOuterClass.StaffRequest1 staffRequest = StaffRequestOuterClass.StaffRequest1.newBuilder()
                .setStaffId(id)
                .build();
        generatedClasses.StaffResponseOuterClass.StaffResponse staff2 = staffService.getStaffInfo(staffRequest);

        Staff staff3 = MapperConfig.INSTANCE.mapToStaff(staff2);
        return CompletableFuture.completedFuture(ok(Json.toJson(staff3)));
    }


    public CompletionStage<Result> getAllStaff() {
        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub();
        StaffListRequestOuterClass.StaffListRequest staffRequest = StaffListRequestOuterClass.StaffListRequest.newBuilder()
                .build();

        List<Staff> staffList = new ArrayList<>();
        StaffListResponseOuterClass.StaffListResponse staffListResponse = staffService.getAllStaffInfo(staffRequest).next();
        staffListResponse.getStaffList().forEach(staff ->
                staffList.add(MapperConfig.INSTANCE.mapToListStaff(staff))
        );
        return CompletableFuture.completedFuture(ok(Json.toJson(staffList)));
    }


    public CompletionStage<Result> updateStaff(Staff staff, int id) {
        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub();
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


    public CompletionStage<Result> deleteStaff(int id) {
        generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub();
        StaffRequestOuterClass.StaffRequest1 staffRequest = StaffRequestOuterClass.StaffRequest1.newBuilder()
                .setStaffId(id)
                .build();
        generatedClasses.StatusResponseOuterClass.StatusResponse statusResponse = staffService.removeStaff(staffRequest);

        return CompletableFuture.completedFuture(ok(Json.toJson(statusResponse.getStatus())));
    }


    private generatedClasses.StaffServiceGrpc.StaffServiceBlockingStub createStaffServiceStub() {
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        return generatedClasses.StaffServiceGrpc.newBlockingStub(managedChannel);
    }
}
