package section10.ex2;

public class NetworkServiceV2 {

  public void sendMessage(String data) throws NetworkClientExceptionV2{
    String address = "http://example.com";
    NetworkClientV2 client = new NetworkClientV2(address);
    client.initError(data);

    client.connect();
    client.send(data);
    client.disconnect();
  }

}
