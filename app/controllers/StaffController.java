package controllers;

import model.Staff;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
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
        Staff staff = Json.fromJson(request.body().asJson(), Staff.class);
        return staffService.addStaff(staff);
    }

    public CompletionStage<Result> getStaffById(int id) {
        return staffService.getStaffById(id);
    }

    public CompletionStage<Result> getAllStaff() {
        return staffService.getAllStaff();
    }

    public CompletionStage<Result> updateStaff(Http.Request request, int id) {
        Staff staff = Json.fromJson(request.body().asJson(), Staff.class);
        return staffService.updateStaff(staff, id);
    }

    public CompletionStage<Result> deleteStaff(int id) {
        return staffService.deleteStaff(id);
    }
}
