package com.pms.patient_service.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceGrpcClient {
  private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;
  private final Logger logger = LoggerFactory.getLogger(BillingServiceGrpcClient.class);

  public BillingServiceGrpcClient(@Value("${billing.service.address:localhost}") String serverAddress,
      @Value("${billing.service.grpc.port:9001}") int serverPort) {
    logger.info("Connecting to Billing Service ");
    ManagedChannel managedChannel = ManagedChannelBuilder.forAddress(serverAddress, serverPort).usePlaintext().build();

    blockingStub = BillingServiceGrpc.newBlockingStub(managedChannel);
  }
  
  public BillingResponse createBillingAccount(String patientId, String name, String email) {
    BillingRequest billingRequest =
        BillingRequest.newBuilder().setEmail(email).setName(name).setPatientId(patientId).build();
    BillingResponse response = blockingStub.createBillingAccount(billingRequest);
    logger.info("Received response from  GRPC {}", response.toString());
    return response;
  }
}
