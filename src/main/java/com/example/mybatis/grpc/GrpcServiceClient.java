package com.example.mybatis.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.TimeUnit;

/**
 * @Classname GrpcServiceClient
 * @Description TODO 客户端调用SayHello
 * @Date 2021/2/22 11:57 上午
 * @Author z7-x
 */
public class GrpcServiceClient {

    private ManagedChannel channel;
    private SayHelloGrpc.SayHelloBlockingStub greeterBlockingStub;

    @Test
    public void test() {
        try {
            HelloReply reply = greeterBlockingStub.sayHello(convertRequest("张三", "男"));
            System.out.println("接收到服务端返回结果:{}" + reply.toString());
        } finally {
            shutdown();
        }
    }

    @Before
    public void init() {
        System.out.println(" grpc-client connect start.");
        channel = ManagedChannelBuilder.forAddress("127.0.0.1", 9099)
                .usePlaintext()
                .build();//池化处理 成本高
        greeterBlockingStub = SayHelloGrpc.newBlockingStub(channel);
    }

    public void shutdown() {
        try {
            channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private HelloRequest convertRequest(String name, String sex) {
        HelloRequest helloRequest = HelloRequest.newBuilder()
                .setName(name)
                .setSex(sex)
                .build();
        return helloRequest;
    }
}
