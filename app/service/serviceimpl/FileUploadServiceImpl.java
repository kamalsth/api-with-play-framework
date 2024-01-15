package service.serviceimpl;

import com.ks.proto.staff.FileUploadRequest;
import com.ks.proto.staff.FileUploadServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.Metadata;
import io.grpc.stub.MetadataUtils;
import play.libs.Files;
import play.libs.Json;
import play.mvc.Http;
import play.mvc.Result;
import play.mvc.Results;
import service.FileUploadService;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.mvc.Results.ok;

public class FileUploadServiceImpl implements FileUploadService {
    @Override
    public CompletionStage<Result> uploadFile(Http.Request request, int staffId) throws IOException {
        return uploadFile(request, staffId, false);
    }


    @Override
    public CompletionStage<Result> uploadImage(Http.Request request, int staffId) throws IOException {
        return uploadFile(request, staffId, true);
    }


    private String storeFileAndImage(int staffId, Http.Request request) throws IOException {
        String UploadDir = "/media/kamal/Software/Treeleaf/output";
        Http.MultipartFormData<Files.TemporaryFile> body = request.body().asMultipartFormData();
        Http.MultipartFormData.FilePart<Files.TemporaryFile> file = body.getFile("file");
        String imageName = file.getFilename();
        Path uploadPath = Path.of(UploadDir, imageName);
        java.nio.file.Files.copy(file.getRef().path(), uploadPath, StandardCopyOption.REPLACE_EXISTING);
        return uploadPath.toString();

    }

    private CompletionStage<Result> uploadFile(Http.Request request, int staffId, boolean isImage) throws IOException {
        String filePath = storeFileAndImage(staffId, request);
        FileUploadServiceGrpc.FileUploadServiceBlockingStub stub = createUploadServiceStub(request);
        if (stub == null) {
            return CompletableFuture.completedFuture(Results.unauthorized("Unauthorized !! Invalid token"));
        }

        FileUploadRequest fileUploadRequest = FileUploadRequest.newBuilder()
                .setStaffId(staffId)
                .setFilePath(filePath)
                .build();
        if (isImage) {
            stub.uploadImage(fileUploadRequest);
            return CompletableFuture.completedFuture(ok("Image upload successfully"));

        } else {
            stub.uploadFile(fileUploadRequest);
            return CompletableFuture.completedFuture(ok(Json.toJson("FileUpload successfully")));

        }

    }

    private FileUploadServiceGrpc.FileUploadServiceBlockingStub createUploadServiceStub(Http.Request request) {
        String jwtToken = request.headers().get("Authorization").orElse("");

        if (jwtToken.isEmpty()) {
            return null;
        }
        ManagedChannel managedChannel = ManagedChannelBuilder.forAddress("localhost", 9090)
                .usePlaintext()
                .build();

        Metadata metadata = new Metadata();
        metadata.put(Metadata.Key.of("Authorization", Metadata.ASCII_STRING_MARSHALLER), jwtToken);
        return FileUploadServiceGrpc.newBlockingStub(managedChannel)
                .withInterceptors(MetadataUtils.newAttachHeadersInterceptor(metadata));

    }

}
