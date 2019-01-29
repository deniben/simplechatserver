package network;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;

public class TCPCOnnection {
    private final Socket socket;
    private final Thread rxThread;
    private final BufferedReader in;
    private final BufferedWriter out;
    TCPPConnectionListener eventListener;
//    private final TCPPConnectionListener eventListener;


    public TCPCOnnection(TCPPConnectionListener eventListener, String ipAddr, int  port) throws IOException {
    this(eventListener, new Socket(ipAddr, port));

    }
    public TCPCOnnection(TCPPConnectionListener eventListener, Socket socket) throws IOException {
        this.socket = socket;

        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), Charset.forName("UTF-8")));
        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), Charset.forName("UTF-8")));
        rxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    eventListener.onConnectionReady(TCPCOnnection.this);
                    while (!rxThread.isInterrupted()){
                        eventListener.onRecieveString(TCPCOnnection.this, in.readLine());
                    }
                } catch (IOException e) {
                   eventListener.onException(TCPCOnnection.this, e);
                } finally {
               eventListener.onDisconnect(TCPCOnnection.this);
                }
            }
        });
        rxThread.start();
    }

    public synchronized void sendString(String value){
    try{
        out.write(value + "\r\n");
        out.flush();
    } catch (IOException e) {
        eventListener.onException(TCPCOnnection.this, e);
        disconnect();
    }
    }
    public synchronized void disconnect() {
        rxThread.interrupt();
        try {
            socket.close();
        } catch (IOException e) {

            eventListener.onException(TCPCOnnection.this, e);
            e.printStackTrace();
        }
    }
    public String toString(){
        return "TCPConnection:" +  socket.getInetAddress()+":"+socket.getPort();
    }
}
