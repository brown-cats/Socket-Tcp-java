package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

class Server_thread extends Thread
{ 
   Socket socket;
   DataOutputStream out=null;
   DataInputStream  in=null;
   String password="java";
   int n=0;
   String s=null;
   Scanner sc;
   String tem;
   Server_thread(Socket t)
   {  socket=t;
      try 
       {  
      	  in=new DataInputStream(socket.getInputStream());
          out=new DataOutputStream(socket.getOutputStream());
       }
      catch (IOException e){
    	  System.out.println(e);
      }
   }  
   public void run(){        
     while(true)
      { 
         try{ 
            s=in.readUTF();//堵塞状态，除非读取到信息。 读取的是密码 
            if(s.equals(password)) {
            	out.writeUTF("true");
            }
            else {  //密码不正确 则返回
            	out.writeUTF("false"); //将信息传递到socket里 
            }
            }
         catch (IOException e) 
            {  
               System.out.println("客户离开");
               break;
             }
         //catch(InterruptedException e){}
      }
   } 
}