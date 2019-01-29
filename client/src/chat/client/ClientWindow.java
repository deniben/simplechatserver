package chat.client;

import javax.swing.*;

public class ClientWindow extends JFrame {
    private static final String IP_ADDR = "127.0.0.1";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClientWindow();
            }
        });
    }
    private ClientWindow(){

    }
}
