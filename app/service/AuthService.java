package service;

import model.Login;
import model.Register;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;


public interface AuthService {
    public CompletionStage<Result> login(Login loginRequest);

    public CompletionStage<Result> register(Register registerRequest);

}
