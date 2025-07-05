package com.pms.billing_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@GrpcService
public class BillingGrpcService extends BillingServiceGrpc.BillingServiceImplBase {
  private static final Logger logger = LoggerFactory.getLogger(BillingGrpcService.class);

  @Override
  public void createBillingAccount(BillingRequest billingRequest,
      StreamObserver<BillingResponse> responseStreamObserver) {
    logger.info("createBillingAccount request received {}", billingRequest.toString());
    //Business logic
    BillingResponse response = BillingResponse.newBuilder()
                                   .setAccountId("randomUser1234")
                                   .setStatus("ACTIVE").build();
    responseStreamObserver.onNext(response);
    responseStreamObserver.onCompleted();
  }
}
