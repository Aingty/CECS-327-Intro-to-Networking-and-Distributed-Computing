import java.net.*;
import java.io.*;
public class Server
{
    public static void main(String args[])
    { 
        // Boolean Used to stop the sever
        boolean keepGoing = true;

        //String used to get client message
        String clientMessage;

        DatagramSocket aSocket = null;

        // Check to see if Argugment exist for port number.
        if (args.length != 1)
        {
            System.out.println("Please indicate the Port number for your Server: \'java Server [port]\'");
        }
        else
        {
            System.out.println("\nServer Starting...... Started!");
            try
            {
                aSocket = new DatagramSocket(Integer.parseInt(args[0]));
                while(keepGoing)
                {
                    byte[] buffer = new byte[1000];
                    DatagramPacket request = new DatagramPacket(buffer, buffer.length);
                    System.out.print("\n\nWaiting for message....");
                    aSocket.receive(request);
                    buffer = request.getData();
                    DatagramPacket reply = new DatagramPacket(request.getData(), request.getLength(), request.getAddress(), request.getPort());
                    clientMessage = new String(buffer, 0, request.getLength());
                    System.out.print("\n\tMessage From Client: " + clientMessage);
                    if (clientMessage.equalsIgnoreCase("quit"))
                    {
                        keepGoing = false;
                        System.out.print("\n\tQuitting..... GOODBYE!!\n");
                    }
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
}