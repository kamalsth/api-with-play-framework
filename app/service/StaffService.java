package service;

import play.mvc.Http;
import play.mvc.Result;

import java.util.concurrent.CompletionStage;

public interface StaffService {

    CompletionStage<Result> addStaff(Http.Request request);

    CompletionStage<Result> getStaffById(Http.Request request, String id);

    CompletionStage<Result> updateStaff(Http.Request request, String id);

    CompletionStage<Result> deleteStaff(Http.Request request, String id);

    CompletionStage<Result> getAllStaff(Http.Request request, int pageNumber, int pageSize);


    CompletionStage<Result> taxCalculation(Http.Request request, String id);
}
