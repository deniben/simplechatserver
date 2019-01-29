package chat.sever;

import network.TCPCOnnection;
import network.TCPPConnectionListener;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;

public class ChatServer implements TCPPConnectionListener {
    public static void main(String[] args) {
    new ChatServer();
    }

    private final ArrayList<TCPCOnnection> connections = new ArrayList<>();
    private ChatServer(){
        System.out.println("Server running...");
        try(ServerSocket serverSocket = new ServerSocket(8189)){
            while(true){
                try{
                    new TCPCOnnection(this, serverSocket.accept());
                } catch (IOException e) {
                    System.out.println("TCPConnection:  " + e);

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public synchronized void onConnectionReady(TCPCOnnection tcpConnection) {
    connections.add(tcpConnection);
    sendToAllConnections("Client connected: "+ tcpConnection);
    }

    @Override
    public synchronized void onRecieveString(TCPCOnnection tcpConnection, String value) {
    sendToAllConnections(value);
    }

    @Override
    public synchronized void onDisconnect(TCPCOnnection tcpConnection) {
    connections.remove(tcpConnection);
        sendToAllConnections("Client disconnected: "+ tcpConnection);
    }

    @Override
    public synchronized void onException(TCPCOnnection tcpConnection, Exception e) {
        System.out.println("TCPConnection exception: "+ e);
    }

    private void sendToAllConnections(String value){
        System.out.println(value);
        final int cnt = connections.size();
        for (int i=0; i < cnt;i++){ connections.get(i).sendString(value);}
    }
}
