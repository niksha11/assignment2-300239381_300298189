import java.io.*;
import client.*;
import common.*;

//**** E50 class copied from ClientConsole.java with modifications - JS

public class ServerConsole implements ChatIF 
{
  //Class variables *************************************************
  
  /**
   * The default port to connect on.
   */
  final public static int DEFAULT_PORT = 5555;
  
  //Instance variables **********************************************
  
  /**
   * The instance of the client that created this ConsoleChat.
   */

  //**** Changed for E50 - JS
  ChatIF clientUI;
  EchoServer server;

  
  //Constructors ****************************************************

  /**
   * Constructs an instance of the ClientConsole UI.
   *
   * @param host The host to connect to.
   * @param port The port to connect on.
   */

   //**** Changed for E50 - JS
  public ServerConsole(int port) 
  {
    server = new EchoServer(port);
    try 
    {
      server.listen();
    } 
    catch(IOException exception) 
    {
      System.out.println("Error: Can't setup connection!"
                + " Terminating client.");
    }
  }

  
  //Instance methods ************************************************
  
  /**
   * This method waits for input from the console.  Once it is 
   * received, it sends it to the client's message handler.
   */

   //**** Changed for E50 - JS

  public void accept() 
  {
    try
    {
      BufferedReader fromConsole = 
        new BufferedReader(new InputStreamReader(System.in));
      String message;

      while (true) 
      {
        message = fromConsole.readLine();
        server.handleMessageFromServerConsole(message);
        display(message);
      }
    } 
    catch (Exception ex) 
    {
      System.out.println
        ("Unexpected error while reading from console!");
    }
  }

  /**
   * This method overrides the method in the ChatIF interface.  It
   * displays a message onto the screen.
   *
   * @param message The string to be displayed.
   */

   //**** Changed for E50 - JS

  public void display(String message) 
  {
    if(message.startsWith("#")) {
      return;
    } else {
      System.out.println("SERVER MSG> " + message);
    }
  }

  
  //Class methods ***************************************************
  
  /**
   * This method is responsible for the creation of the Client UI.
   *
   * @param args[0] The host to connect to.
   */

   //**** Changed for E50 - JS

  public static void main(String[] args) 
  {
    int port = 0;  //The port number

    try {
      port = Integer.parseInt(args[0]);
    } catch(Exception e) {
      port = DEFAULT_PORT;
    }
    ServerConsole server = new ServerConsole(port);
    server.accept();
  }
}
//End of ServerConsole class

