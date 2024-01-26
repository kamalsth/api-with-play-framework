package service.serviceimpl;

import com.ks.proto.common.StatusResponse;
import com.ks.proto.notice.*;
import config.MapperConfig;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import model.Notice;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import service.NoticeService;
import utils.ExceptionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

public class NoticeServiceImpl implements NoticeService {

    @Override
    public CompletionStage<Result> addNotice(Http.Request request) {
        Notice notice = Json.fromJson(request.body().asJson(), Notice.class);
        NoticeServiceGrpc.NoticeServiceBlockingStub stub = createNoticeServiceStub(request);
        if (stub == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        NoticeRequest noticeRequest = NoticeRequest.newBuilder()
                .setTitle(notice.getTitle())
                .setContent(notice.getContent())
                .build();

        try {
            NoticeResponse noticeResponse = stub.addNotice(noticeRequest);
            Notice notice1 = MapperConfig.INSTANCE.mapNoticeResponseToNotice(noticeResponse);
            return CompletableFuture.completedFuture(ok(Json.toJson(notice1)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

    @Override
    public CompletionStage<Result> getAllNotice(Http.Request request, int pageNumber, int pageSize) {
        NoticeServiceGrpc.NoticeServiceBlockingStub stub = createNoticeServiceStub(request);
        if (stub == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }

        List<Notice> notices = new ArrayList<>();
        try {
            NoticeListResponse noticeListResponse = stub.getAllNotice(NoticeListRequest.newBuilder()
                    .setPageNumber(pageNumber)
                    .setPageSize(pageSize)
                    .build()).next();
            noticeListResponse.getNoticesList().forEach(noticeResponse -> notices.add(MapperConfig.INSTANCE.mapNoticeResponseToNotice(noticeResponse)));
            return CompletableFuture.completedFuture(ok(Json.toJson(notices)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

    @Override
    public CompletionStage<Result> updateNotice(Http.Request request, String id) {
        Notice notice = Json.fromJson(request.body().asJson(), Notice.class);
        NoticeServiceGrpc.NoticeServiceBlockingStub stub = createNoticeServiceStub(request);
        if (stub == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        NoticeRequest noticeRequest = NoticeRequest.newBuilder()
                .setNoticeId(id)
                .setTitle(notice.getTitle())
                .setContent(notice.getContent())
                .build();

        try {
            NoticeResponse noticeResponse = stub.updateNotice(noticeRequest);
            Notice notice1 = MapperConfig.INSTANCE.mapNoticeResponseToNotice(noticeResponse);
            return CompletableFuture.completedFuture(ok(Json.toJson(notice1)));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

    @Override
    public CompletionStage<Result> deleteNotice(Http.Request request, String id) {
        NoticeServiceGrpc.NoticeServiceBlockingStub stub = createNoticeServiceStub(request);
        if (stub == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }
        NoticeRequestById noticeRequest = NoticeRequestById.newBuilder()
                .setNoticeId(id)
                .build();
        try {
            StatusResponse statusResponse = stub.deleteNotice(noticeRequest);
            return CompletableFuture.completedFuture(ok(Json.toJson(statusResponse.getStatus())));
        } catch (Exception e) {
            return ExceptionUtils.handleException(e);
        }
    }

    private NoticeServiceGrpc.NoticeServiceBlockingStub createNoticeServiceStub(Http.Request request) {
        String token = request.headers().get("Authorization").orElse("");
        if (token.isEmpty()) {
            return null;
        }
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();
        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), token);
        return NoticeServiceGrpc.newBlockingStub(managedChannel)
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));
    }
}
