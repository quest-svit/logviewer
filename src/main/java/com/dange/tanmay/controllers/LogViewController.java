package com.dange.tanmay.controllers;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@Controller
public class LogViewController {
	
	
	
	@RequestMapping(method = RequestMethod.GET, value="/viewLog")
	@ResponseBody
	public StringBuffer  viewLog(@RequestParam String filePath) {
		
		BufferedReader reader;
		StringBuffer strbuf= new StringBuffer();
		
		try {
			
			
			reader = new BufferedReader(new FileReader(filePath));
			String line = reader.readLine();
			while (line != null) {
				//System.out.println(line);
				strbuf.append("<br>"+line);
				// read next line
				line = reader.readLine();
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	return strbuf;
	}
	

}
