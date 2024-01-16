package config;

import com.ks.proto.auth.LoginRequest;
import com.ks.proto.leave.ConfirmLeaveRequest;
import com.ks.proto.leave.LeaveResponse;
import com.ks.proto.leave.LeaveStatus;
import com.ks.proto.staff.StaffResponse;
import com.ks.proto.user.User;
import com.ks.proto.user.UserRole;
import model.LeaveRequestModel;
import model.Login;
import model.Register;
import model.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import javax.inject.Inject;

@Mapper
public interface MapperConfig {
    @Inject
    MapperConfig INSTANCE = Mappers.getMapper(MapperConfig.class);

    Staff mapToStaff(StaffResponse staff2);

    Staff mapToListStaff(com.ks.proto.staff.Staff staff);

   LoginRequest mapToLoginRequest(Login loginRequest);

    @Mapping(source = "role", target = "role", qualifiedByName = "mapStringToRole")
    User mapToUser(Register registerRequest);
    @Named("mapStringToRole")
    default UserRole mapStringToRole(String role) {
        return UserRole.valueOf(role);
    }

   com.ks.proto.staff.Staff mapToStaffProto(Staff staff);

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
}
