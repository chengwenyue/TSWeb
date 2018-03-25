package com.just.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	
	public static final String DEFAULT_FROMAT="yyyy-MM-dd HH:mm";
	public static String DateFormat(Date date,String format){
		SimpleDateFormat format2=
			new SimpleDateFormat(format);
		
		return format2.format(date);
	}
	
	public static String[] getDates(String startTime, String endTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Calendar calendar = Calendar.getInstance();
		List<String> dates = new ArrayList<String>();
		try {
			Date start = null;
			Date end = null;
			if((!"".equals(startTime))&&startTime!=null){
				start = df.parse(startTime);
				calendar.setTime(start);
			}else{
				start = new Date();
			}
			if((!"".equals(startTime))&&startTime!=null){
				end = df.parse(endTime);
			}else{
				end = new Date();
			}
			do {
				dates.add(df.format(calendar.getTime()));
				calendar.set(5, calendar.get(5) + 1);
			} while (calendar.getTime().getTime() <= end.getTime());

			String[] a = { "" };
			return (String[]) dates.toArray(a);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return new String[]{""};
	}
	static Calendar calendar = Calendar.getInstance();
	//"yyyy-MM-dd HH:mm:ss"
	//"yyyy-MM-dd"
	public static String formatDate(String time, String format ,String toFormat,int addDay) throws Exception{
		SimpleDateFormat df = new SimpleDateFormat(toFormat);
		SimpleDateFormat df2 = new SimpleDateFormat(format);
		Date date2 = df2.parse(time);
		calendar.setTime(date2);
		calendar.set(5, calendar.get(5) + addDay);
		return df.format(calendar.getTime());
	}
	public static String  formatDate(Date time ,String format, int addDay ){
		calendar.setTime(time);
		calendar.set(5, calendar.get(5) + addDay);
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}
	public static String  formatDate(Date time ,String format, int addDay,int mouth){
		calendar.setTime(time);
		calendar.set(5, calendar.get(5) + addDay);
		calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH)+mouth);
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(calendar.getTime());
	}
	public static String  formatDate(String time ,String format, int addDay ) throws Exception{
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date date = dateFormat.parse(time);
		calendar.setTime(date);
		calendar.set(5, calendar.get(5) + addDay);
		return dateFormat.format(calendar.getTime());
	}
}
