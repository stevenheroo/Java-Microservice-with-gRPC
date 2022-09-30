package org.github.skep.service;

import io.grpc.stub.StreamObserver;
import lombok.val;
import org.github.skep.dao.UserDao;
import org.github.skep.user.Gender;
import org.github.skep.user.UserRequest;
import org.github.skep.user.UserResponse;
import org.github.skep.user.UserServiceGrpc;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUserService(UserRequest request, StreamObserver<UserResponse> responseObserver) {
//        super.getUserService(request, responseObserver);
        UserDao dao = new UserDao();
        val user = dao.getUserDetails(request.getUsername());

        UserResponse.Builder respBuilder =
                UserResponse.newBuilder()
                        .setId(user.getId())
                        .setUsername(user.getUsername())
                        .setName(user.getName())
                        .setAge(user.getAge())
                        .setGender(Gender.valueOf(user.getGender()));
        respBuilder.build();

        //send the response back to the client
        responseObserver.onNext(respBuilder.build());
        responseObserver.onCompleted();
    }
}
