package com.tpk18.SpotifyKnockoff;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class DateConverter implements AttributeConverter<String, Date>{

	
	@Override
	public Date convertToDatabaseColumn(String date) {
		Date d = null;
		String begin = date.substring(0, date.indexOf('/'));
		String middle = date.substring(date.indexOf('/')+1, date.lastIndexOf('/'));
		String end = date.substring(date.lastIndexOf('/')+1);
		String newDate = end+"-"+begin+"-"+middle;
		try {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			d = df.parse(newDate);
		} catch (ParseException e) {
			ErrorLogger.log(e.getMessage());
		}
		return d;
	}

	@Override
	public String convertToEntityAttribute(Date string) {
		return null;
	}

}
