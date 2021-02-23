package com.example.mybatis.grpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * @Classname GrpcServerService
 * @Description TODO
 * @Date 2021/2/22 11:52 上午
 * @Author z7-x
 */
@Slf4j
@GrpcService
public class GrpcServerService extends SayHelloGrpc.SayHelloImplBase{

    @Override
    public void sayHello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        log.info("接收到 grpc-client 消息:{}",req.getName(),req.getSex());
        HelloReply reply = HelloReply.newBuilder().setMessage("Hello =============> " + req.getName()).build();
        HelloReply build = reply.toBuilder().build();
        responseObserver.onNext(build);
        responseObserver.onCompleted();
    }
}
