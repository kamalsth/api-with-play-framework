package config;

import generatedClasses.LoginRequestOuterClass;
import generatedClasses.StaffListResponseOuterClass;
import generatedClasses.StaffOuterClass;
import generatedClasses.StaffResponseOuterClass;
import model.Login;
import model.Staff;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import javax.inject.Inject;

@Mapper
public interface MapperConfig {
    @Inject MapperConfig INSTANCE = Mappers.getMapper(MapperConfig.class);
    Staff mapToStaff(StaffResponseOuterClass.StaffResponse staff2);

    Staff mapToListStaff(StaffOuterClass.Staff staff);

    LoginRequestOuterClass.LoginRequest mapToLoginRequest(Login loginRequest);
}
