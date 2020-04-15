package Server;
import java.io.*;import java.net.*;
public class Computer_server 
{  public static void main(String args[])
   {
      ServerSocket server=null;
      Server_thread thread;
      Socket you=null;
     try{  
        server=new ServerSocket(4033);        
        System.out.println("服务器已连接，客户端可以连接");
      }
     catch(IOException e) 
     {  
     	System.out.println(e.getMessage()); 
     }
      while(true) 
       { 
          try{  
        	  you=server.accept();  
             }
         catch (IOException e1){
        	 System.out.println("正在等待客户");
         }
         if(you!=null) 
        	new Server_thread(you).start(); //为每个客户启动一个专门的线程。  
         else 
	       {  continue;
	        }
      }
   }
	
 }


