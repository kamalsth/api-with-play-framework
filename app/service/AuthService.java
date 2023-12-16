package service;

import model.Login;
import model.Register;
import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;


public interface AuthService {
    public CompletionStage<Result> login(Http.Request request);

    public CompletionStage<Result> register(Http.Request request);

}
