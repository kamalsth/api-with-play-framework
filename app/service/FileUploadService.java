package service;

import play.mvc.Http;
import play.mvc.Result;

import java.io.IOException;
import java.util.concurrent.CompletionStage;

public interface FileUploadService {
    public CompletionStage<Result> uploadFile(Http.Request request, int staffId) throws IOException;
    public CompletionStage<Result> uploadImage(Http.Request request, int staffId) throws IOException;

}
