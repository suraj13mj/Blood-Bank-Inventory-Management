import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;

import com.toedter.calendar.JDateChooser;

public class ConvertSqlDate 
{
	
	static java.sql.Date SQLDate(java.util.Date date)
	{
		return new java.sql.Date(date.getTime());
	}
	
	
    static java.util.Date FrontEndDate(java.sql.Date date)
    {
    	return new java.util.Date(date.getTime());
    }
    
    
	static int getAge(java.sql.Date date) {
        java.sql.Date date1 = java.sql.Date.valueOf((date).toLocalDate());
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date1);
    	int month = cal.get(Calendar.MONTH);
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	int year = cal.get(Calendar.YEAR);
    	
    	//System.out.println(month);
    	
    	LocalDate today=LocalDate.now();
    	LocalDate bday=LocalDate.of(year, month, day);
    	
    	int age=Period.between(bday, today).getYears();
    	return age;
    }
	
	
	static int getGap(java.sql.Date date) {
        java.sql.Date date1 = java.sql.Date.valueOf((date).toLocalDate());
    	Calendar cal = Calendar.getInstance();
    	cal.setTime(date1);
    	int month = cal.get(Calendar.MONTH);
    	int day = cal.get(Calendar.DAY_OF_MONTH);
    	int year = cal.get(Calendar.YEAR);
    	
    	LocalDate today=LocalDate.now();
    	int presentyear=today.getYear();
    	LocalDate lastdonated=LocalDate.of(year, month, day);
    	
    	int gap=Period.between(lastdonated, today).getMonths();
    	for(int i=1;presentyear-year==i;i++)
    	{
    		gap=gap+(12*i);
    		break;
    	}
    	return gap;
    	
    }
}
