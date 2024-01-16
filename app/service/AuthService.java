package service;


import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;


public interface AuthService {
    public CompletionStage<Result> login(Http.Request request);

    public CompletionStage<Result> register(Http.Request request);

}
