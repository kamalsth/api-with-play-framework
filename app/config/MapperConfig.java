package config;

import com.ks.proto.auth.LoginRequest;
import com.ks.proto.staff.StaffResponse;
import com.ks.proto.user.User;
import com.ks.proto.user.UserRole;
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
}
