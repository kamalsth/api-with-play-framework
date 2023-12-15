package controllers;

import model.Staff;
import play.filters.csrf.*;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.With;
import service.StaffService;

import javax.inject.Inject;
import java.util.concurrent.CompletionStage;

public class StaffController extends Controller {


    private final StaffService staffService;

    @Inject
    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    public CompletionStage<Result> addStaff(Http.Request request) {
        return staffService.addStaff(request);
    }

    public CompletionStage<Result> getStaffById(Http.Request request,int id) {
        return staffService.getStaffById(request,id);
    }

    public CompletionStage<Result> getAllStaff(Http.Request request) {
        return staffService.getAllStaff(request);
    }

    public CompletionStage<Result> updateStaff(Http.Request request, int id) {
        return staffService.updateStaff(request, id);
    }

    public CompletionStage<Result> deleteStaff(Http.Request request,int id) {
        return staffService.deleteStaff(request,id);
    }
}
