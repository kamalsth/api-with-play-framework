package service;


import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;


public interface AuthService {
    CompletionStage<Result> login(Http.Request request);

    CompletionStage<Result> register(Http.Request request);

    CompletionStage<Result> aboutMe(Http.Request request);
}
