package com.openkairo.genericUtilty;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {
	
	public int genRandomNumber() {
		   Random r=new Random();
		   return r.nextInt();
	   }
	   
	   public String currentDate() {
		   Date d=new Date();
		   SimpleDateFormat sim=new SimpleDateFormat("dd-MM-yyyy");
		   return sim.format(d);
	   }
	   
	   public String currentTime() {
		   Date d=new Date();
		   SimpleDateFormat sim=new SimpleDateFormat("hh:mm");
		   return sim.format(d);
	   }
	   
	   public String currentTimeAMPm() {
		   Date d=new Date();
		   SimpleDateFormat sim=new SimpleDateFormat("a");
		   return sim.format(d);
	   }
	   
	   public String calenderDate(int days) {
		   Date d=new Date();
		   SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd");
		   sim.format(d);
		   Calendar cal = sim.getCalendar();
		   cal.add(Calendar.DAY_OF_MONTH, days);               //adding required number of days to current date
		  String dateRequired = sim.format(cal.getTime());    //getTime() -convert calender value into date object ,,sim.format takes new date and converts it into required format 
		  return dateRequired;
		  
		   
		   
	   }

}
