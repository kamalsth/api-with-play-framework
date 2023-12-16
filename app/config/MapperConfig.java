package config;

import generatedClasses.LoginRequestOuterClass;
import generatedClasses.StaffOuterClass;
import generatedClasses.StaffResponseOuterClass;
import generatedClasses.UserOuterClass;
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

    Staff mapToStaff(StaffResponseOuterClass.StaffResponse staff2);

    Staff mapToListStaff(StaffOuterClass.Staff staff);

    LoginRequestOuterClass.LoginRequest mapToLoginRequest(Login loginRequest);

    @Mapping(source = "role", target = "role", qualifiedByName = "mapStringToRole")
    UserOuterClass.User mapToUser(Register registerRequest);
    @Named("mapStringToRole")
    default UserOuterClass.UserRole mapStringToRole(String role) {
        return UserOuterClass.UserRole.valueOf(role);
    }

}
