package config;

import com.google.inject.AbstractModule;
import service.AuthService;
import service.serviceimpl.AuthServiceImpl;

public class MyModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(AuthService.class).to(AuthServiceImpl.class);
    }
}
