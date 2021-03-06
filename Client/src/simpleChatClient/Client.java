package simpleChatClient;

import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JFrame;

public class Client implements Runnable {

	private Socket socket;//MAKE SOCKET INSTANCE VARIABLE
	private GUI frame;
	
	public Client(Socket s, GUI frame)
	{
		socket = s;//INSTANTIATE THE INSTANCE VARIABLE
		this.frame = frame;
	}
	
	@Override
	public void run()//INHERIT THE RUN METHOD FROM THE Runnable INTERFACE
	{
		try //try hard
		{
                    String message = frame.getMessage();
                    if(message != null){
			//Scanner chat = new Scanner(message);//GET THE INPUT FROM THE CMD
                        
                        System.out.println("Got " + message);
			Scanner in = new Scanner(socket.getInputStream());//GET THE CLIENTS INPUT STREAM (USED TO READ DATA SENT FROM THE SERVER)
			PrintWriter out = new PrintWriter(socket.getOutputStream());//GET THE CLIENTS OUTPUT STREAM (USED TO SEND DATA TO THE SERVER)
			
			while (true)//WHILE THE PROGRAM IS RUNNING
			{						
				String input = frame.getMessage();	//SET NEW VARIABLE input TO THE VALUE OF WHAT THE CLIENT TYPED IN
				out.print(input);//SEND IT TO THE SERVER
				
				out.flush();//FLUSH THE STREAM
				
				if(in.hasNext())//IF THE SERVER SENT US SOMETHING
					//System.out.println(in.nextLine());//PRINT IT OUT
                                frame.getTextArea().append(in.nextLine());
			}
                    }
		}
		catch (Exception e)
		{
			e.printStackTrace();//MOST LIKELY WONT BE AN ERROR, GOOD PRACTICE TO CATCH THOUGH
		} 
	}

}

