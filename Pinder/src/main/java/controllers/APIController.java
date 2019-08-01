package controllers;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class APIController {

	public static void POSTAPI(){
		String command = "	curl -d \"grant_type=client_credentials&client_id=dSkqy54SfVuraExWGMSnVPzGnMwuSG1CRyjiGcAGQ6u09BcmAR&client_secret=rRlJx0QzZ110wboZJ6Td4I2dNI2lrO67R5VdJgTy\" https://api.petfinder.com/v2/oauth2/token";
		Process process = null;
		try {
			process = Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(process.toString());

		OutputStream out = process.getOutputStream();
		InputStream in = process.getInputStream();
		
		System.out.println(out);
		System.out.println(in);
		
		try {
			byte[] bufferArray = new byte[1000];
			in.read(bufferArray);
			String info = new String(bufferArray);
			System.out.println(info);
			in.close();
			out.close();
			process.destroy();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
