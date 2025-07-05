package com.pms.billing_service;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


public class GrpcDummyClient {

  private final ManagedChannel channel;
  private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

  public GrpcDummyClient(String host, int port) {
    this.channel = ManagedChannelBuilder.forAddress(host, port)
                       .usePlaintext()  // Use plaintext for local testing
                       .build();
    this.blockingStub = BillingServiceGrpc.newBlockingStub(channel);
  }

  public static void main(String[] args) {
    GrpcDummyClient client = new GrpcDummyClient("localhost", 9001);

    try {
      client.testCreateBillingAccountService("2333", "Alice", "ex@ex.com");

      // Call more test methods
      // client.testAnotherRpc(42);
    }
    finally {
      client.shutdown();
    }
  }

  public void shutdown() {
    channel.shutdown();
  }

  public void testCreateBillingAccountService(String id, String name, String email) {
    BillingRequest request = BillingRequest.newBuilder()
                                 .setName(name).setEmail(email).setPatientId(id)
                                 .build();

    BillingResponse response = blockingStub.createBillingAccount(request);
    System.out.println("Response: " + response.toString());
  }
}
