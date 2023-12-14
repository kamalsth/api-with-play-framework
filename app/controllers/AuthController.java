package controllers;


import model.Login;
import model.Register;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.AuthService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class AuthController extends Controller {


    private final AuthService authService;

    @Inject
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    public CompletionStage<Result> login(Http.Request request) {
        Login loginRequest = Json.fromJson(request.body().asJson(), Login.class);
        return authService.login(loginRequest);
    }


    public CompletionStage<Result> register(Http.Request request) {
        Register registerRequest = Json.fromJson(request.body().asJson(), Register.class);
        return authService.register(registerRequest);
    }

}
