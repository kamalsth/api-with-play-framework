package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.NoticeService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class NoticeController extends Controller {
    private final NoticeService noticeService;

    @Inject
    public NoticeController(NoticeService noticeService) {
        this.noticeService = noticeService;
    }



    public CompletionStage<Result> addNotice(Http.Request request) {
        return noticeService.addNotice(request);
    }

    public CompletionStage<Result> getAllNotice(Http.Request request, int pageNumber, int pageSize) {
        return noticeService.getAllNotice(request, pageNumber, pageSize);
    }


    public CompletionStage<Result> updateNotice(Http.Request request, String id) {
        return noticeService.updateNotice(request, id);
    }


    public CompletionStage<Result> deleteNotice(Http.Request request, String id) {
        return noticeService.deleteNotice(request, id);
    }

}
