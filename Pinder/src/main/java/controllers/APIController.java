
package controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Collections;

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
			//get the access token and save it
			String[] info = response.split("\"");
			accessToken = info[info.length-2];
			//close our streams and destroy our processes
			in.close();
			out.close();
			process.destroy();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String animalRequest(int page) {
		String command = "curl -H \"Authorization: Bearer " + accessToken + "\" GET https://api.petfinder.com/v2/animals?status=adoptable&location=" + location +"&page=" + page + "&limit=100";
		Process process = null;
		String response = new String();
		try {
			System.out.println("getting animals (this may take a while)");
			//set the process to execute the command
			process = Runtime.getRuntime().exec(command);
			//get the io streams
			InputStream in = process.getInputStream();
			//create a buffered reader to get response from API
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//get the response
			for (String line; (line = br.readLine()) != null; response += line );
			//close our streams and destroy our processes
			in.close();
			process.destroy();
			//getting the number of pages
			String[] info = response.split(",");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
			response = "";
			command = "curl -H \"Authorization: Bearer " + accessToken + "\" GET https://api.petfinder.com/v2/animals?location=" + location +"&page=" + page + "&limit=100";
			try {
				//set the process to execute the command
				process = Runtime.getRuntime().exec(command);
				//get the io streams
				InputStream in = process.getInputStream();
				//create a buffered reader to get response from API
				BufferedReader br = new BufferedReader(new InputStreamReader(in));
				//get the response
				for (String line; (line = br.readLine()) != null; response += line );
				//close our streams and destroy our processes
				in.close();
				process.destroy();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return response;
	}

	public static String organizationRequest() {
		System.out.println("getting organizations");
		String command = "curl -H \"Authorization: Bearer " + accessToken + "\" GET https://api.petfinder.com/v2/organizations?state=" + location + "&limit=100";
		Process process = null;
		String response = new String();
		try {
			//set the process to execute the command
			process = Runtime.getRuntime().exec(command);
			//get the io streams
			InputStream in = process.getInputStream();
			//create a buffered reader to get response from API
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			//get the response
			for (String line; (line = br.readLine()) != null; response += line );
			//close our streams and destroy our processes
			in.close();
			process.destroy();
			//db.insertAnimalRecords(response);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}
	
	public static String getLocation() {
		return location;
	}
	
	public static void setLocation(String location) {
		APIController.location = location;
	}

	private static void printProgress(int current, int total) {
		total = total + 1;
	    StringBuilder string = new StringBuilder();   
	    int percent = current * 100 / total;
	    string
	        .append(String.join("", Collections.nCopies(percent == 0 ? 2 : 2 - (int) (Math.log10(percent)), " ")))
	        .append(String.format(" %d%% [", percent))
	        .append(String.join("", Collections.nCopies(percent/2, "=")))
	        .append(String.join("", Collections.nCopies(50 - percent/2, " ")))
	        .append(']')
	        .append(String.join("", Collections.nCopies((int) (Math.log10(total)) - (int) (Math.log10(current)), " ")))
	        .append(String.format(" %d/%d", current, total));
	    System.out.println(string);
	}
	
}
