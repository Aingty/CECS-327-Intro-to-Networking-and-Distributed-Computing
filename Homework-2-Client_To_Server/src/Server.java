import java.net.*;
import java.io.*;
public class Server
{
    public static void main(String args[])
    { 
        DatagramSocket aSocket = null;
        try
        {
            aSocket = new DatagramSocket(6789);
            while(true)
            {
                byte[] buffer = new byte[1000];
                DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                aSocket.receive(request);
                System.out.println("Testing");
                buffer = request.getData();
                DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                System.out.println(new String(buffer, 0, request.getLength()));
                aSocket.send(reply);
            }
        }
        catch (SocketException e)
        {
            System.out.println("Socket: " + e.getMessage());
        }
        catch (IOException e) 
        {
            System.out.println("IO: " + e.getMessage());
        }
        finally 
        {
            if(aSocket != null) aSocket.close();
        }
    }
}