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
        return authService.login(request);
    }


    public CompletionStage<Result> register(Http.Request request) {
        return authService.register(request);
    }

    public CompletionStage<Result> aboutMe(Http.Request request) {
        return authService.aboutMe(request);
    }

    public CompletionStage<Result> changePassword(Http.Request request) {
        return authService.changePassword(request);
    }

}
