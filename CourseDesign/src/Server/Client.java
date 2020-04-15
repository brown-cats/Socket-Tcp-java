package Server;

import java.awt.EventQueue;
import java.io.*;
import java.net.*;
import java.util.Scanner;

import Window.Window_first;
public class Client
{  public static void main(String args[])
   {  String s=null;
      Socket mysocket;
      String password="true";
      DataInputStream in=null;
      DataOutputStream out=null;
      Scanner sc;
      String tem;
      try{
          mysocket=new Socket("localhost",4033);
          in=new DataInputStream(mysocket.getInputStream());
          out=new DataOutputStream(mysocket.getOutputStream());
          sc=new Scanner(System.in);
          while(true)
            { 
        	  System.out.println("����������:");
               tem=sc.next();
               out.writeUTF(tem);  //����socket�� 
               s=in.readUTF();
               if(s.equals(password)) {
            	System.out.println("����������ȷ,��½�ɹ�");
           		EventQueue.invokeLater(new Runnable() {
        			public void run() {
        				try {
        					Window_first frame = new Window_first();
        					frame.setVisible(true);
        				} catch (Exception e) {
        					e.printStackTrace();
        				}
        			}
        		});
              }
               else {
               	System.out.println("�����������������");
               }
            } 
         }
       catch(IOException e)
         {  
       	   System.out.println(e.getMessage());
         }
   } 
}
