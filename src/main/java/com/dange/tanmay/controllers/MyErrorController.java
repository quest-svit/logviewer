package com.dange.tanmay.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyErrorController implements ErrorController{
	
	@RequestMapping(method = RequestMethod.GET, value="/error")
	@ResponseBody
	public StringBuffer  error() {
		
		BufferedReader reader;
		StringBuffer strbuf= new StringBuffer();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			File file = new File(classLoader.getResource("DefaultLog.txt").getFile());
			reader = new BufferedReader(new FileReader(file));
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
 
    @Override
    public String getErrorPath() {
        return "/error";
    }

}
