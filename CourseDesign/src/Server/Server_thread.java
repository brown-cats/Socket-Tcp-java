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
            s=in.readUTF();//����״̬�����Ƕ�ȡ����Ϣ�� ��ȡ�������� 
            if(s.equals(password)) {
            	out.writeUTF("true");
            }
            else {  //���벻��ȷ �򷵻�
            	out.writeUTF("false"); //����Ϣ���ݵ�socket�� 
            }
            }
         catch (IOException e) 
            {  
               System.out.println("�ͻ��뿪");
               break;
             }
         //catch(InterruptedException e){}
      }
   } 
}