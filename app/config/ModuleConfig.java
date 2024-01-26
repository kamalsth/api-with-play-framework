package config;

import com.google.inject.AbstractModule;
import service.*;
import service.serviceimpl.*;

public class ModuleConfig extends AbstractModule {
    @Override
    protected void configure() {
        bind(AuthService.class).to(AuthServiceImpl.class);
        bind(StaffService.class).to(StaffServiceImpl.class);
        bind(FileUploadService.class).to(FileUploadServiceImpl.class);
        bind(LeaveService.class).to(LeaveServiceImpl.class);
        bind(NoticeService.class).to(NoticeServiceImpl.class);
    }
}
