package controllers;

import play.mvc.Http;
import play.mvc.Result;
import service.LeaveService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class LeaveController {
    private final LeaveService leaveService;

    @Inject
    public LeaveController(LeaveService leaveService) {
        this.leaveService = leaveService;
    }

    public CompletionStage<Result> leaveRequest(Http.Request request) {
        return leaveService.leaveRequest(request);
    }

    public CompletionStage<Result> getAllLeaveRequest(Http.Request request) {
        return leaveService.getAllLeaveRequest(request);
    }

    public CompletionStage<Result> getLeaveRequestById(Http.Request request, String id) {
        return leaveService.getLeaveRequestById(request, id);
    }


    public CompletionStage<Result> updateLeaveRequest(Http.Request request, String id) {
        return leaveService.updateLeaveRequest(request, id);
    }

    public CompletionStage<Result> deleteLeaveRequest(Http.Request request, String id) {
        return leaveService.deleteLeaveRequest(request, id);
    }

    public CompletionStage<Result> confirmLeaveRequest(Http.Request request) {
        return leaveService.confirmLeaveRequest(request);
    }
}
