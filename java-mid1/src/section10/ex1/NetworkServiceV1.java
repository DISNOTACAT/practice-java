package section10.ex1;

public class NetworkServiceV1 {

  public void sendMessage(String data){
    String address = "http://example.com";
    NetworkClientV1 client = new NetworkClientV1(address);
    client.initError(data);

    client.connect();
    client.send(data);
    client.disconnect();
  }

}
