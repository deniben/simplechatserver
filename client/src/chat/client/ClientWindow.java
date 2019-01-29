package chat.client;

import network.TCPCOnnection;
import network.TCPPConnectionListener;
import sun.rmi.transport.tcp.TCPConnection;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ClientWindow extends JFrame implements ActionListener, TCPPConnectionListener {
    private static final String IP_ADDR = "127.0.0.1";
    private static final int PORT = 8189;
    private static final int WIDTH = 600;
    private static final int HEIGHT = 400;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });
    }
    private final JTextArea log = new JTextArea();
    private final JTextField fieldNickname = new JTextField();
    private final JTextField fieldInput = new JTextField();

    private TCPConnection connection;
    private ClientWindow() throws IOException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(WIDTH,HEIGHT);
        setLocationRelativeTo(null);
        setAlwaysOnTop(true);

        log.setEditable(false);
        log.setLineWrap(true);

        add(log, BorderLayout.CENTER);
        fieldInput.addActionListener(this);
        add(fieldInput, BorderLayout.SOUTH);
        add(fieldNickname, BorderLayout.NORTH);

        connection = new TCPConnection(this, IP_ADDR, PORT);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void onConnectionReady(TCPCOnnection tcpConnection) {

    }

    @Override
    public void onRecieveString(TCPCOnnection tcpConnection, String value) {

    }

    @Override
    public void onDisconnect(TCPCOnnection tcpConnection) {

    }

    @Override
    public void onException(TCPCOnnection tcpConnection, Exception e) {

    }
}
