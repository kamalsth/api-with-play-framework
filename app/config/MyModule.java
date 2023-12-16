package config;

import com.google.inject.AbstractModule;
import service.AuthService;
import service.StaffService;
import service.serviceimpl.AuthServiceImpl;
import service.serviceimpl.StaffServiceImpl;

public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AuthService.class).to(AuthServiceImpl.class);
        bind(StaffService.class).to(StaffServiceImpl.class);
    }
}
