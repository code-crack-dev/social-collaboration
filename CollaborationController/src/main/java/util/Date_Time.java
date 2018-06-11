package util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Date_Time 
{
	public String getDateTime()
	{
		DateFormat df = new SimpleDateFormat("YYYY-MM-DD HH24:mm:SS");
	       Date dateobj = new Date();
	       String datetime = df.format(dateobj).toString();
	       System.out.println("Date - "+datetime);
	       return datetime;
	}
	
	public Date toDate(String dob) throws ParseException
	{
		DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy"); 
		Date date = (Date)formatter.parse(dob);
		return date;
	}

	public String toStringDate(Date date)
	{
		Locale loc = new Locale("en", "US");
		DateFormat df = DateFormat.getDateInstance(DateFormat.DEFAULT, loc);
		String dateT = df.format(date);
		return dateT;
	}
	
	public static void main(String[] args) throws ParseException 
	{
		Date_Time dt = new Date_Time();
		dt.getDateTime();
		
		Date date = dt.toDate("22-09-1995");
		System.out.println("Date - "+date);

		String dateFormat = dt.toStringDate(date);
		System.out.println("Date Format - "+dateFormat);
	}
}