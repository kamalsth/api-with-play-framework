package controllers;

import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import service.FileUploadService;
import service.StaffService;

import javax.inject.Inject;
import java.io.IOException;
import java.util.concurrent.CompletionStage;

public class StaffController extends Controller {


    private final StaffService staffService;
    private final FileUploadService fileUploadService;

    @Inject
    public StaffController(StaffService staffService, FileUploadService fileUploadService) {
        this.staffService = staffService;
        this.fileUploadService = fileUploadService;
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


    public CompletionStage<Result> uploadFile(Http.Request request,int id) throws IOException {
        return fileUploadService.uploadFile(request,id);
    }

    public CompletionStage<Result> uploadImage(Http.Request request,int id) throws IOException {
        return fileUploadService.uploadImage(request,id);
    }

    public CompletionStage<Result> taxCalculation(Http.Request request,int id) {
        return staffService.taxCalculation(request,id);
    }
}
