package Serialization;

import io.netty.handler.codec.serialization.ObjectDecoderInputStream;
import io.netty.handler.codec.serialization.ObjectEncoderOutputStream;
import java.net.Socket;

public class MyClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 8189);
             ObjectEncoderOutputStream oeos = new ObjectEncoderOutputStream(socket.getOutputStream());
             ObjectDecoderInputStream odis = new ObjectDecoderInputStream(socket.getInputStream(), 100 * 1024 * 1024)) {
            MyMessage textMessage = new MyMessage("Hello MyServer!");
            oeos.writeObject(textMessage);
            oeos.flush();
            MyMessage messageFromMyServer = (MyMessage) odis.readObject();
            System.out.println("Answer from MyServer: " + messageFromMyServer.getText());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

