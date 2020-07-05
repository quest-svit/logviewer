package com.dange.tanmay.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.dange.tanmay.Constants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

@RestController
public class LogViewController {
	
	@RequestMapping(method = RequestMethod.GET, value="/viewLog")
	@ResponseBody
	public StringBuffer  viewLog(@RequestParam int lines,@RequestParam String filePath,@RequestParam  String direction) {
		
		BufferedReader reader;
		StringBuffer strbuf= new StringBuffer();
		
		try {	
			reader = new BufferedReader(new FileReader(filePath));
			direction = Objects.nonNull(direction)? direction : Constants.DIRECTION_TAIL;
			
			if (Constants.DIRECTION_HEAD.equals(direction)) {
				int counter=0;
				String line = reader.readLine();
				while (counter < lines) {
					strbuf.append("<br>"+reader.readLine());
					counter++;
				}
			}else {
				int counter=0;
				@SuppressWarnings("deprecation")
				ReversedLinesFileReader object = new ReversedLinesFileReader(new File(filePath));
				List<String> reversedLines = new ArrayList<>();
				while (counter < lines) {
					reversedLines.add("<br>"+reader.readLine());
					counter++;
				}
				for (int i=reversedLines.size()-1; i>=0;i--) {
					strbuf.append(reversedLines.get(i));
				}
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	return strbuf;
	}
}
