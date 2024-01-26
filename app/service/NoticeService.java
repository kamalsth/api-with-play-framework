package service;

import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public interface NoticeService {
    CompletionStage<Result> addNotice(Http.Request request);

    CompletionStage<Result> getAllNotice(Http.Request request, int pageNumber, int pageSize);

    CompletionStage<Result> updateNotice(Http.Request request, String id);

    CompletionStage<Result> deleteNotice(Http.Request request, String id);
}
