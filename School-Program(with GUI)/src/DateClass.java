import java.text.SimpleDateFormat;
import java.util.Date;


public class DateClass
{
	private int day;
	private int month;
	private int year;
	
	public DateClass(String d)
	{
		if(d.length()==10) //if input is not in this format: 27/06/1999
		{
			char aChar1 = d.charAt(0);
			char aChar2 = d.charAt(1);
			int day0 = Character.getNumericValue(aChar1);
			int day1 = Character.getNumericValue(aChar2);
			day = (day0*10)+day1;
			
			aChar1 = d.charAt(3);
			aChar2 = d.charAt(4);
			int month0 = Character.getNumericValue(aChar1);
			int month1 = Character.getNumericValue(aChar2);
			month = (month0*10)+month1;
			
			aChar1 = d.charAt(6);
			aChar2 = d.charAt(7);
			char aChar3 = d.charAt(8);
			char aChar4 = d.charAt(9);
			int year0 = Character.getNumericValue(aChar1);
			int year1 = Character.getNumericValue(aChar2);
			int year2 = Character.getNumericValue(aChar3);
			int year3 = Character.getNumericValue(aChar4);
			year = (year0*1000)+(year1*100)+(year2*10)+year3;
		}
		else
		{	
			System.out.println("Invalid input!");
		}
	}
	
	public void setDay(int d)
	{
		if(d<32&&d>0)
		{
			day = d;
		}
		else
		{
			day = 0;
		}
	}
	public void setMonth(int m)
	{
		if(m<13&&m>0)
		{
			month = m;
		}
		else
		{
			month = 0;
		}
	}
	public void setYear(int y)
	{
		if(y>999)
		{
			year = y;
		}
		else 
		{
			year = 0;
		}
	}
	
	public String getDate()
	{
		String d = Integer.toString(day);
		String m = Integer.toString(month);
		if(month < 10)
			m = "0"+m;
		String y = Integer.toString(year);
		String FullDate = d+"/"+m+"/"+y;
		return FullDate;
	}
	public static int differenceBtwSubAndDueDate(String SubDate, String DueDate)
	{
		SimpleDateFormat myDate = new SimpleDateFormat("dd/MM/yyyy");
		float daysBetween=0;
		try 
		{
			Date date1 = myDate.parse(SubDate);
			Date date2 = myDate.parse(DueDate);
			long difference = date1.getTime() - date2.getTime(); //difference in milliseconds
			daysBetween = (difference / (1000*60*60*24));
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return (int)daysBetween;		
	}
}
