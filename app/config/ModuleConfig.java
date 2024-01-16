package config;

import com.google.inject.AbstractModule;
import service.AuthService;
import service.FileUploadService;
import service.LeaveService;
import service.StaffService;
import service.serviceimpl.AuthServiceImpl;
import service.serviceimpl.FileUploadServiceImpl;
import service.serviceimpl.LeaveServiceImpl;
import service.serviceimpl.StaffServiceImpl;

public class ModuleConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(AuthService.class).to(AuthServiceImpl.class);
        bind(StaffService.class).to(StaffServiceImpl.class);
        bind(FileUploadService.class).to(FileUploadServiceImpl.class);
        bind(LeaveService.class).to(LeaveServiceImpl.class);
    }
}
