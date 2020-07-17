package com.dange.tanmay.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.apache.commons.io.input.ReversedLinesFileReader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.dange.tanmay.Constants;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class LogViewController {
	
	public static Logger log = LoggerFactory.getLogger(LogViewController.class);
	
	@RequestMapping(method = RequestMethod.GET, value="/viewLog")
	@ResponseBody
	public StringBuffer  viewLog(@RequestParam int lines,@RequestParam String filePath,@RequestParam  String direction) {
		
		BufferedReader reader;
		StringBuffer strbuf= new StringBuffer();
		log.info("FilePath: "+ filePath);
		log.info("No of Lines to View: "+ lines);
		log.info("Direction: "+ direction);
		
		try {	
			reader = new BufferedReader(new FileReader(filePath));
			direction = Objects.nonNull(direction)? direction : Constants.DIRECTION_TAIL;
			
			if (Constants.DIRECTION_HEAD.equals(direction)) {
				log.debug("Direction:"+ direction);
				int counter=0;
				reader.readLine();
				while (counter < lines) {
					strbuf.append("<br>"+reader.readLine());
					counter++;
				}
			}else {
				log.debug("Direction is TAIL");
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
				object.close();
			}
			reader.close();
		} catch (IOException e) {
			log.error("IO Exception occurred" , e);
		}
	return strbuf;
	}
}
