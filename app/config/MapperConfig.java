package config;

import com.ks.proto.auth.LoginRequest;
import com.ks.proto.leave.ConfirmLeaveRequest;
import com.ks.proto.leave.LeaveResponse;
import com.ks.proto.leave.LeaveStatus;
import com.ks.proto.staff.StaffResponse;
import com.ks.proto.staff.TaxCalResponse;
import com.ks.proto.staff.TaxResponse;
import com.ks.proto.user.User;
import com.ks.proto.user.UserRole;
import model.*;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import javax.inject.Inject;

@Mapper(
        componentModel = "spring",
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface MapperConfig {
    @Inject
    MapperConfig INSTANCE = Mappers.getMapper(MapperConfig.class);

    @Mapping(source = "maritalStatus", target = "maritalStatus", qualifiedByName = "mapMaritalStatusToString")
    Staff mapToStaff(StaffResponse staff2);

    @Named("mapMaritalStatusToString")
    default String mapMaritalStatusToString(com.ks.proto.staff.MaritalStatus maritalStatus) {
        return maritalStatus.name();
    }

    @Mapping(source = "maritalStatus", target = "maritalStatus", qualifiedByName = "mapMaritalStatusToString")
    Staff mapToListStaff(com.ks.proto.staff.Staff staff);

   LoginRequest mapToLoginRequest(Login loginRequest);

    @Mapping(source = "role", target = "role", qualifiedByName = "mapStringToRole")
    @Mapping(target = "userId", ignore = true)
    User mapToUser(Register registerRequest);
    @Named("mapStringToRole")
    default UserRole mapStringToRole(String role) {
        return UserRole.valueOf(role);
    }

    @Mapping(source = "maritalStatus", target = "maritalStatus", qualifiedByName = "mapStringToMaritalStatus")
    @Mapping(target = "staffId", ignore = true)
   com.ks.proto.staff.Staff mapToStaffProto(Staff staff);

    @Named("mapStringToMaritalStatus")
    default com.ks.proto.staff.MaritalStatus mapStringToMaritalStatus(String maritalStatus) {
        return com.ks.proto.staff.MaritalStatus.valueOf(maritalStatus);
    }

    @Mapping(source = "leaveStatus", target = "status", qualifiedByName = "mapLeaveStatusToString")
    LeaveRequestModel mapToLeaveRequestModel(LeaveResponse leaveResponse);

    @Named("mapLeaveStatusToString")
    default String mapLeaveStatusToString(LeaveStatus leaveStatus) {
        return leaveStatus.name();
    }

    @Mapping(source = "status", target = "leaveStatus", qualifiedByName = "mapStringToLeaveStatus")
    LeaveResponse mapToLeaveRequestProto(LeaveRequestModel leaveRequestModel);

    @Named("mapStringToLeaveStatus")
    default LeaveStatus mapStringToLeaveStatus(String leaveStatus) {
        return LeaveStatus.valueOf(leaveStatus);
    }

    @Mapping(source = "leaveStatus", target = "leaveStatus", qualifiedByName = "mapStringToLeaveStatus")
    ConfirmLeaveRequest mapToConfirmLEaveRequestProto(model.ConfirmLeaveRequest confirmLeaveRequest);

    Tax mapToTax(TaxResponse taxResponse);

    TaxCalculation mapToTaxCal(TaxCalResponse taxResponse);
}
