package com.intellect.auto.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Prepare {
	static boolean flag = true;
	static  String filename =  "D:\\Learning\\New folder\\File\\html.xml";
	public static void main(String[] args) {
		 
	        String path = new File(filename).getAbsolutePath();
	        
	        StringBuffer sb = new StringBuffer();
			BufferedReader br = null;
			FileReader fr = null;
			
			try {

 
				String sCurrentLine;

				br = new BufferedReader(new FileReader(path));

				while ((sCurrentLine = br.readLine()) != null) {
					
					if(sCurrentLine.contains("<%")){
						flag = false;
					}
					if(sCurrentLine.contains("%>")){
						flag = true;
						continue;
					}
					if(flag){
						if(sCurrentLine.contains("&nbsp")){
							sCurrentLine = 	sCurrentLine.replaceAll("&nbsp", "");
						}
						if(sCurrentLine.contains("&&")){
						sCurrentLine = 	sCurrentLine.replaceAll("&&", "");
						}
						if(sCurrentLine.contains("OPTION")){
							//sCurrentLine = 	sCurrentLine.replaceAll("<", "");
							continue;
						}
						
						
						
						
						sb.append(sCurrentLine+"\n");
					System.out.println("  "+sCurrentLine);
						
					 
					}
				}
				
				write(sb.toString());
				//System.out.println(cou+" COUNT    "+cou);
				flag = false;

		}catch(Exception e){
			
		}


	}
	
	private static boolean write(String content){
		BufferedWriter bw = null;
		FileWriter fw = null;

		try {

			//String content = "This is the content to write into file\n";

			fw = new FileWriter(filename);
			bw = new BufferedWriter(fw);
			bw.write(content);

			System.out.println("Done");

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}
		return false;

	}


}
