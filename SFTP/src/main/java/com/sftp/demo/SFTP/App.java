package com.sftp.demo.SFTP;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

/**
 * Hello world!
 *
 */
public class App 
{
	 public static void main( String[] args ) throws IOException
	 {
	    	JSch jsch = new JSch();
	        Session session = null;
	        try 
	        {
	        	System.out.println("Hi");
	            session = jsch.getSession("root", "127.0.0.1", 22);
	            session.setConfig("StrictHostKeyChecking", "no");
	            session.setPassword("password");
	            System.out.println("Before session");
	            session.connect();
	            System.out.println("After session");
	            Channel channel = session.openChannel("sftp");
	            channel.connect();
	            ChannelSftp sftpChannel = (ChannelSftp) channel;
	            sftpChannel.cd("./test");
	            System.out.println(sftpChannel.getHome());
	            System.out.println(sftpChannel.getId());
	            InputStream stream = sftpChannel.get("test.txt");
	            
	            try 
	            {
	                BufferedReader br = new BufferedReader(new InputStreamReader(stream));
	                String line;
	                while ((line = br.readLine()) != null) 
	                {
	                    System.out.println(line);
	                }

	            }
	            catch (IOException io) 
	            {
	                System.out.println("Exception occurred during reading file from SFTP server due to " + io.getMessage());
	                io.getMessage();

	            }
	            catch (Exception e) 
	            {
	                System.out.println("Exception occurred during reading file from SFTP server due to " + e.getMessage());
	                e.getMessage();

	            }

	            sftpChannel.exit();
	            session.disconnect();
		        }
		        catch (JSchException e) 
		        {
		            e.printStackTrace();
		        }
		        catch (SftpException e) 
		        {
		            e.printStackTrace();
		        }
	    	
	    }
}
