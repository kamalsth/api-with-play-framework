package generatedClasses;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.59.0)",
    comments = "Source: FileUpload.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class FileUploadServiceGrpc {

  private FileUploadServiceGrpc() {}

  public static final java.lang.String SERVICE_NAME = "proto.FileUploadService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<generatedClasses.FileUpload.FileUploadRequest,
      generatedClasses.FileUpload.FileUploadResponse> getUploadFileMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "uploadFile",
      requestType = generatedClasses.FileUpload.FileUploadRequest.class,
      responseType = generatedClasses.FileUpload.FileUploadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generatedClasses.FileUpload.FileUploadRequest,
      generatedClasses.FileUpload.FileUploadResponse> getUploadFileMethod() {
    io.grpc.MethodDescriptor<generatedClasses.FileUpload.FileUploadRequest, generatedClasses.FileUpload.FileUploadResponse> getUploadFileMethod;
    if ((getUploadFileMethod = FileUploadServiceGrpc.getUploadFileMethod) == null) {
      synchronized (FileUploadServiceGrpc.class) {
        if ((getUploadFileMethod = FileUploadServiceGrpc.getUploadFileMethod) == null) {
          FileUploadServiceGrpc.getUploadFileMethod = getUploadFileMethod =
              io.grpc.MethodDescriptor.<generatedClasses.FileUpload.FileUploadRequest, generatedClasses.FileUpload.FileUploadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "uploadFile"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generatedClasses.FileUpload.FileUploadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generatedClasses.FileUpload.FileUploadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileUploadServiceMethodDescriptorSupplier("uploadFile"))
              .build();
        }
      }
    }
    return getUploadFileMethod;
  }

  private static volatile io.grpc.MethodDescriptor<generatedClasses.FileUpload.FileUploadRequest,
      generatedClasses.FileUpload.FileUploadResponse> getUploadImageMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "uploadImage",
      requestType = generatedClasses.FileUpload.FileUploadRequest.class,
      responseType = generatedClasses.FileUpload.FileUploadResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<generatedClasses.FileUpload.FileUploadRequest,
      generatedClasses.FileUpload.FileUploadResponse> getUploadImageMethod() {
    io.grpc.MethodDescriptor<generatedClasses.FileUpload.FileUploadRequest, generatedClasses.FileUpload.FileUploadResponse> getUploadImageMethod;
    if ((getUploadImageMethod = FileUploadServiceGrpc.getUploadImageMethod) == null) {
      synchronized (FileUploadServiceGrpc.class) {
        if ((getUploadImageMethod = FileUploadServiceGrpc.getUploadImageMethod) == null) {
          FileUploadServiceGrpc.getUploadImageMethod = getUploadImageMethod =
              io.grpc.MethodDescriptor.<generatedClasses.FileUpload.FileUploadRequest, generatedClasses.FileUpload.FileUploadResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "uploadImage"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generatedClasses.FileUpload.FileUploadRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  generatedClasses.FileUpload.FileUploadResponse.getDefaultInstance()))
              .setSchemaDescriptor(new FileUploadServiceMethodDescriptorSupplier("uploadImage"))
              .build();
        }
      }
    }
    return getUploadImageMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static FileUploadServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileUploadServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileUploadServiceStub>() {
        @java.lang.Override
        public FileUploadServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileUploadServiceStub(channel, callOptions);
        }
      };
    return FileUploadServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static FileUploadServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileUploadServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileUploadServiceBlockingStub>() {
        @java.lang.Override
        public FileUploadServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileUploadServiceBlockingStub(channel, callOptions);
        }
      };
    return FileUploadServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static FileUploadServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<FileUploadServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<FileUploadServiceFutureStub>() {
        @java.lang.Override
        public FileUploadServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new FileUploadServiceFutureStub(channel, callOptions);
        }
      };
    return FileUploadServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void uploadFile(generatedClasses.FileUpload.FileUploadRequest request,
        io.grpc.stub.StreamObserver<generatedClasses.FileUpload.FileUploadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadFileMethod(), responseObserver);
    }

    /**
     */
    default void uploadImage(generatedClasses.FileUpload.FileUploadRequest request,
        io.grpc.stub.StreamObserver<generatedClasses.FileUpload.FileUploadResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUploadImageMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service FileUploadService.
   */
  public static abstract class FileUploadServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return FileUploadServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service FileUploadService.
   */
  public static final class FileUploadServiceStub
      extends io.grpc.stub.AbstractAsyncStub<FileUploadServiceStub> {
    private FileUploadServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileUploadServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileUploadServiceStub(channel, callOptions);
    }

    /**
     */
    public void uploadFile(generatedClasses.FileUpload.FileUploadRequest request,
        io.grpc.stub.StreamObserver<generatedClasses.FileUpload.FileUploadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadFileMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void uploadImage(generatedClasses.FileUpload.FileUploadRequest request,
        io.grpc.stub.StreamObserver<generatedClasses.FileUpload.FileUploadResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUploadImageMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service FileUploadService.
   */
  public static final class FileUploadServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<FileUploadServiceBlockingStub> {
    private FileUploadServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileUploadServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileUploadServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public generatedClasses.FileUpload.FileUploadResponse uploadFile(generatedClasses.FileUpload.FileUploadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadFileMethod(), getCallOptions(), request);
    }

    /**
     */
    public generatedClasses.FileUpload.FileUploadResponse uploadImage(generatedClasses.FileUpload.FileUploadRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUploadImageMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service FileUploadService.
   */
  public static final class FileUploadServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<FileUploadServiceFutureStub> {
    private FileUploadServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected FileUploadServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new FileUploadServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generatedClasses.FileUpload.FileUploadResponse> uploadFile(
        generatedClasses.FileUpload.FileUploadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadFileMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<generatedClasses.FileUpload.FileUploadResponse> uploadImage(
        generatedClasses.FileUpload.FileUploadRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUploadImageMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_UPLOAD_FILE = 0;
  private static final int METHODID_UPLOAD_IMAGE = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_UPLOAD_FILE:
          serviceImpl.uploadFile((generatedClasses.FileUpload.FileUploadRequest) request,
              (io.grpc.stub.StreamObserver<generatedClasses.FileUpload.FileUploadResponse>) responseObserver);
          break;
        case METHODID_UPLOAD_IMAGE:
          serviceImpl.uploadImage((generatedClasses.FileUpload.FileUploadRequest) request,
              (io.grpc.stub.StreamObserver<generatedClasses.FileUpload.FileUploadResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getUploadFileMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generatedClasses.FileUpload.FileUploadRequest,
              generatedClasses.FileUpload.FileUploadResponse>(
                service, METHODID_UPLOAD_FILE)))
        .addMethod(
          getUploadImageMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              generatedClasses.FileUpload.FileUploadRequest,
              generatedClasses.FileUpload.FileUploadResponse>(
                service, METHODID_UPLOAD_IMAGE)))
        .build();
  }

  private static abstract class FileUploadServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    FileUploadServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return generatedClasses.FileUpload.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("FileUploadService");
    }
  }

  private static final class FileUploadServiceFileDescriptorSupplier
      extends FileUploadServiceBaseDescriptorSupplier {
    FileUploadServiceFileDescriptorSupplier() {}
  }

  private static final class FileUploadServiceMethodDescriptorSupplier
      extends FileUploadServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final java.lang.String methodName;

    FileUploadServiceMethodDescriptorSupplier(java.lang.String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (FileUploadServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new FileUploadServiceFileDescriptorSupplier())
              .addMethod(getUploadFileMethod())
              .addMethod(getUploadImageMethod())
              .build();
        }
      }
    }
    return result;
  }
}
