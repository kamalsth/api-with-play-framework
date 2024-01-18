package service;

import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public interface StaffService {

    public CompletionStage<Result> addStaff(Http.Request request);
    public CompletionStage<Result> getStaffById(Http.Request request, String id);
    public CompletionStage<Result> updateStaff(Http.Request request, String id);
    public CompletionStage<Result> deleteStaff(Http.Request request, String id);
    public CompletionStage<Result> getAllStaff(Http.Request request);


    CompletionStage<Result> taxCalculation(Http.Request request, String id);
}
