package service.serviceimpl;

import com.ks.proto.common.StatusResponse;
import com.ks.proto.staff.*;
import config.MapperConfig;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import model.Staff;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import service.StaffService;
import utils.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

public class StaffServiceImpl implements StaffService {

    @Override
    public CompletionStage<Result> addStaff(Http.Request request) {
        Staff staff = Json.fromJson(request.body().asJson(), Staff.class);
        StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffRequest staffRequest = StaffRequest.newBuilder()
                .setStaff(com.ks.proto.staff.Staff.newBuilder()
                        .setName(staff.getName())
                        .setPersonalPhone(staff.getPersonalPhone())
                        .setEmergencyContactNumber(staff.getEmergencyContactNumber())
                        .setPosition(staff.getPosition())
                        .setJoinDate(staff.getJoinDate())
                        .setContactRenewDate(staff.getContactRenewDate())
                        .setSalary(staff.getSalary())
                        .setMaritalStatus(MaritalStatus.valueOf(staff.getMaritalStatus().name()))
                        .setEmail(staff.getEmail())
                        .setSocialSecurityFund(staff.getSocialSecurityFund())
                        .setEmployeesProvidentFund(staff.getEmployeesProvidentFund())
                        .setCitizenInvestmentTrust(staff.getCitizenInvestmentTrust())
                        .setInsurance(staff.getInsurance())
                        .build())
                .build();

        try {
            StaffResponse staff2 = staffService.addStaff(staffRequest);
            Staff staff3 = MapperConfig.INSTANCE.mapToStaff(staff2);
            return CompletableFuture.completedFuture(ok(Json.toJson(staff3)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }


    @Override
    public CompletionStage<Result> getStaffById(Http.Request request, String id) {

        StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffRequestById staffRequest = StaffRequestById.newBuilder()
                .setStaffId(id)
                .build();
        try {
            StaffResponse staff2 = staffService.getStaffInfo(staffRequest);
            Staff staff3 = MapperConfig.INSTANCE.mapToStaff(staff2);
            return CompletableFuture.completedFuture(ok(Json.toJson(staff3)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }


    @Override
    public CompletionStage<Result> updateStaff(Http.Request request, String id) {
        Staff staff = Json.fromJson(request.body().asJson(), Staff.class);

        StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffRequest staffRequest = StaffRequest.newBuilder()
                .setStaff(com.ks.proto.staff.Staff.newBuilder()
                        .setStaffId(id)
                        .setName(staff.getName())
                        .setPersonalPhone(staff.getPersonalPhone())
                        .setEmergencyContactNumber(staff.getEmergencyContactNumber())
                        .setPosition(staff.getPosition())
                        .setJoinDate(staff.getJoinDate())
                        .setContactRenewDate(staff.getContactRenewDate())
                        .setSalary(staff.getSalary())
                        .setMaritalStatus(MaritalStatus.valueOf(staff.getMaritalStatus().name()))
                        .setEmail(staff.getEmail())
                        .setSocialSecurityFund(staff.getSocialSecurityFund())
                        .setEmployeesProvidentFund(staff.getEmployeesProvidentFund())
                        .setCitizenInvestmentTrust(staff.getCitizenInvestmentTrust())
                        .setInsurance(staff.getInsurance())
                        .build())
                .build();

        try {
            StaffResponse staff2 = staffService.updateStaff(staffRequest);
            Staff staff3 = MapperConfig.INSTANCE.mapToStaff(staff2);
            return CompletableFuture.completedFuture(ok(Json.toJson(staff3)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }


    @Override
    public CompletionStage<Result> deleteStaff(Http.Request request, String id) {
        StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffRequestById staffRequest = StaffRequestById.newBuilder()
                .setStaffId(id)
                .build();
        try {
            StatusResponse statusResponse = staffService.removeStaff(staffRequest);
            return CompletableFuture.completedFuture(ok(Json.toJson(statusResponse.getStatus())));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }


    @Override
    public CompletionStage<Result> getAllStaff(Http.Request request, int pageNumber, int pageSize) {

        StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
        if (staffService == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffListRequest staffRequest = StaffListRequest.newBuilder()
                .setPageNumber(pageNumber)
                .setPageSize(pageSize)
                .build();

        List<Staff> staffList = new ArrayList<>();
        try {
            StaffListResponse staffListResponse = staffService
                    .getAllStaffInfo(staffRequest).next();
            staffListResponse.getStaffListList().forEach(staff ->
                    staffList.add(MapperConfig.INSTANCE.mapToListStaff(staff))
            );
            return CompletableFuture.completedFuture(ok(Json.toJson(staffList)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

//    @Override
//    public CompletionStage<Result> taxCalculation(Http.Request request, String id) {
//        StaffServiceGrpc.StaffServiceBlockingStub staffService = createStaffServiceStub(request);
//        if (staffService == null) {
//            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
//        }
//        StaffRequestById staffRequest = StaffRequestById.newBuilder()
//                .setStaffId(id)
//                .build();
//
//        TaxResponse taxResponse = staffService.taxCalculation(staffRequest);
//
//
//        return CompletableFuture.completedFuture(ok(Json.toJson(MapperConfig.INSTANCE.mapToTax(taxResponse))));
//    }

    @Override
    public CompletionStage<Result> taxCalculation(Http.Request request, String id) {
        TaxServiceGrpc.TaxServiceBlockingStub taxServiceStub = createTaxServiceStub(request);
        if (taxServiceStub == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        StaffRequestById staffRequest = StaffRequestById.newBuilder()
                .setStaffId(id)
                .build();

        try {
            TaxCalResponse taxResponse = taxServiceStub.calculateTax(staffRequest);
            return CompletableFuture.completedFuture(ok(Json.toJson(MapperConfig.INSTANCE.mapToTaxCal(taxResponse))));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

    private TaxServiceGrpc.TaxServiceBlockingStub createTaxServiceStub(Http.Request request) {
        String jwtToken = request.headers().get("Authorization").orElse("");
        if (jwtToken.isEmpty()) {
            return null;
        }
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), jwtToken);
        return TaxServiceGrpc.newBlockingStub(managedChannel)
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));
    }


    private StaffServiceGrpc.StaffServiceBlockingStub createStaffServiceStub(Http.Request request) {
        String jwtToken = request.headers().get("Authorization").orElse("");

        if (jwtToken.isEmpty()) {
            return null;
        }
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), jwtToken);

        return StaffServiceGrpc.newBlockingStub(managedChannel)
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));

    }

}
