package selfLearning;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JavaDateandTime {
	public static void main(String[] args) throws IOException, ParseException {
		
		String res = findDay(8, 05, 2015);
		System.out.println(res.toUpperCase());

			}
	
	public static String findDay(int month, int day, int year) throws ParseException  {

		//Calendar c = Calendar.getInstance();
		//c.setTime(yourDate);
		//int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
		
		 String input_date=day +"/"+month+"/"+year;
		  SimpleDateFormat format1=new SimpleDateFormat("dd/MM/yyyy");
		  Date dt1=format1.parse(input_date);
		  DateFormat format2=new SimpleDateFormat("EEEE"); 
		  String finalDay=format2.format(dt1);
		  
		return finalDay;
	}
}
