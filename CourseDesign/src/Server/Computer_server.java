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
        System.out.println("�����������ӣ��ͻ��˿�������");
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
        	 System.out.println("���ڵȴ��ͻ�");
         }
         if(you!=null) 
        	new Server_thread(you).start(); //Ϊÿ���ͻ�����һ��ר�ŵ��̡߳�  
         else 
	       {  continue;
	        }
      }
   }
	
 }


