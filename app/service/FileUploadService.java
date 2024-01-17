package service;

import play.mvc.Http;
import play.mvc.Result;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public interface FileUploadService {
    public CompletionStage<Result> uploadFile(Http.Request request, String staffId) throws IOException;
    public CompletionStage<Result> uploadImage(Http.Request request, String staffId) throws IOException;

}
