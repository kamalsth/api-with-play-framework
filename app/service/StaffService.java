package service;

import config.MapperConfig;
import generatedClasses.StaffListRequestOuterClass;
import generatedClasses.StaffListResponseOuterClass;
import generatedClasses.StaffRequestOuterClass;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import model.Staff;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

public interface StaffService {

    public CompletionStage<Result> addStaff(Http.Request request);
    public CompletionStage<Result> getStaffById(Http.Request request, int id);
    public CompletionStage<Result> updateStaff(Http.Request request, int id);
    public CompletionStage<Result> deleteStaff(Http.Request request, int id);
    public CompletionStage<Result> getAllStaff(Http.Request request);



}
