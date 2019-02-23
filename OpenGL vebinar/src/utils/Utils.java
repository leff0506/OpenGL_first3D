package utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class Utils {
	 public static String loadResource(String path) {
	        StringBuffer result=new StringBuffer();
	        try {
				BufferedReader reader = new BufferedReader(new FileReader(path));
				String buffer ="";
				while((buffer=reader.readLine())!=null) {
					result.append(buffer);
					result.append("\n");
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	        return result.toString();
	 }
}
