package network;

public interface TCPPConnectionListener {
    void onConnectionReady(TCPCOnnection  tcpConnection);
    void onRecieveString(TCPCOnnection  tcpConnection, String value);
    void onDisconnect(TCPCOnnection tcpConnection);
    void onException(TCPCOnnection tcpConnection, Exception e);
}
