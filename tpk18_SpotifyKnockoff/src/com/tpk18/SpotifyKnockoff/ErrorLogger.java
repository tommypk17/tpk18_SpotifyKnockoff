package com.tpk18.SpotifyKnockoff;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.Writer;
import java.util.Date;

/**
*
* @author Thomas P. Kovalchuk
* @version 1.0
*/
public class ErrorLogger {
	public static void log(String errorMessage) {
		try {
			Date currDate = new Date();
			File file = null;
			Writer wr = null;
			String fileName = "errorlog.txt";
			try {
				file = new File(fileName);
				wr = new FileWriter(file, true);
				wr.write(currDate.toString()+" "+errorMessage+"\n");
				wr.close();
			}catch(IOException e) {
				
			}
			}catch(Exception e) {
				
			}
			
	}
}
