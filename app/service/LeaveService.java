package service;

import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public interface LeaveService {
    CompletionStage<Result> leaveRequest(Http.Request request);

    CompletionStage<Result> getAllLeaveRequest(Http.Request request, int pageNumber, int pageSize);

    CompletionStage<Result> getLeaveRequestById(Http.Request request, String id);

    CompletionStage<Result> updateLeaveRequest(Http.Request request, String id);

    CompletionStage<Result> deleteLeaveRequest(Http.Request request, String id);

    CompletionStage<Result> confirmLeaveRequest(Http.Request request);
}
