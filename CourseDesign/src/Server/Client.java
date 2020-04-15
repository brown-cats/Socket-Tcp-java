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
        	  System.out.println("请输入密码:");
               tem=sc.next();
               out.writeUTF(tem);  //传到socket里 
               s=in.readUTF();
               if(s.equals(password)) {
            	System.out.println("密码输入正确,登陆成功");
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
               	System.out.println("密码错误，请重新输入");
               }
            } 
         }
       catch(IOException e)
         {  
       	   System.out.println(e.getMessage());
         }
   } 
}
