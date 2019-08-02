package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class APIController {

	private static String accessToken = null;
	private static String location = "UT";
	
	public static void GetAccessToken(){
		String command = "	curl -d \"grant_type=client_credentials&client_id=dSkqy54SfVuraExWGMSnVPzGnMwuSG1CRyjiGcAGQ6u09BcmAR&client_secret=rRlJx0QzZ110wboZJ6Td4I2dNI2lrO67R5VdJgTy\" https://api.petfinder.com/v2/oauth2/token";
		Process process = null;
		
		try {
			//set the process to execute the command
			process = Runtime.getRuntime().exec(command);
			//get the io streams
			OutputStream out = process.getOutputStream();
			InputStream in = process.getInputStream();
			//create a buffered reader to get response from API
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//get the response
			String response = new String();
			for (String line; (line = br.readLine()) != null; response += line);
			//just making sure we have what we want
			System.out.println(response);
			//get the access token and save it
			String[] info = response.split("\"");
			accessToken = info[info.length-2];
			System.out.println(accessToken);

			//close our streams and destroy our processes
			in.close();
			out.close();
			process.destroy();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void Request() {
		String command = "curl -H \"Authorization: Bearer " + accessToken + "\" GET https://api.petfinder.com/v2/animals?location=" + location;
		Process process = null;
		int pages = 1 ;
		
		try {
			//set the process to execute the command
			process = Runtime.getRuntime().exec(command);
			//get the io streams
			OutputStream out = process.getOutputStream();
			InputStream in = process.getInputStream();
			//create a buffered reader to get response from API
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//get the response
			
			String response = new String();
			for (String line; (line = br.readLine()) != null; response += line );

			//just making sure we have what we want
			System.out.println(response.isEmpty());
			System.out.println(response);
			
			//close our streams and destroy our processes
			in.close();
			out.close();
			process.destroy();
			
			//getting the number of pages
			
			String[] info = response.split(",");
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	public static String getLocation() {
		return location;
	}

	public static void setLocation(String location) {
		APIController.location = location;
	}
}
