import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
public class DayTime implements Comparable<DayTime>
{
	Scanner input = new Scanner(System.in);
	private String day;
	private int hour;
	private int minute;
	private int second;
	public DayTime() 
	{

		System.out.println("Enter day: ");
		String day = input.nextLine();
		setDay(day);
		while(true)
		{
			try {
				System.out.printf("Enter hour: ");
				int hour = input.nextInt();
				setHour(hour);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
				input.nextLine();
			}
		}
		while(true)
		{
			try {
				System.out.printf("Enter minute: ");
				int minute = input.nextInt();
				setMinute(minute);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
				input.nextLine();

			}
		}
		while(true)
		{
			try {
				System.out.printf("Enter second: ");
				int second = input.nextInt();
				setSecond(second);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
				input.nextLine();
			}
		}	
	}
	public DayTime(String day) 
	{
		while(true)
		{
			try {
				System.out.printf("Enter hour: ");
				int hour = input.nextInt();
				setHour(hour);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
			}
		}
		while(true)
		{
			try {
				System.out.printf("Enter minute: ");
				int minute = input.nextInt();
				setMinute(minute);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
			}
		}
		while(true)
		{
			try {
				System.out.printf("Enter second: ");
				int second = input.nextInt();
				setSecond(second);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
			}
		}
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	public DayTime(String day, int hour) 
	{
		while(true)
		{
			try {
				System.out.printf("Enter minute: ");
				int minute = input.nextInt();
				setMinute(minute);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
			}
		}
		while(true)
		{
			try {
				System.out.printf("Enter second: ");
				int second = input.nextInt();
				setSecond(second);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
			}
		}
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	public DayTime(String day, int hour, int minute) 
	{
		while(true)
		{
			try {
				System.out.printf("Enter second: ");
				int second = input.nextInt();
				setSecond(second);
				break;
			} catch (InputMismatchException e) {
				System.out.println("Enter string");
			}
		}
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	public DayTime(String day, int hour, int minute, int second) 
	{
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	
	
	public void setDay(String day) {
		day.toLowerCase();
		if((Objects.equals(day, "monday"))||(Objects.equals(day, "tuesday"))||(Objects.equals(day, "wednesday"))||(Objects.equals(day, "thursday"))||(Objects.equals(day, "friday"))||(Objects.equals(day, "saturday"))||(Objects.equals(day, "sunday")))
			this.day = day;
		else 
			this.day = null;

	}
	public void setHour(int hour) {
		this.hour = ((hour >= 0 && hour < 24) ? hour : -1);
	}
	public void setMinute(int minute) {
		this.minute = ((minute >= 0 && minute < 60) ? minute : -1);
	}
	public void setSecond(int second) {
		this.second = ((second >= 0 && second < 60) ? second : -1);
	}
	public String getDay() {
		return day;
	}
	public int getHour() {
		return hour;
	}
	public int getMinute() {
		return minute;
	}
	public int getSecond() {
		return second;
	}
	
	
	@Override
	public String toString() {
		return String.format("%s,%d:%d:%d", getDay(),getHour(),getMinute(),getSecond());
	}
	
	@Override
	public int compareTo(DayTime o) 
	{
		int dayNum1=0;
		if(Objects.equals(this.day, "monday"))
			dayNum1 = 1;
		else if(Objects.equals(this.day, "tuesday"))
			dayNum1 = 2;
		else if(Objects.equals(this.day, "wednesday"))
			dayNum1 = 3;
		else if(Objects.equals(this.day, "thursday"))
			dayNum1 = 4;
		else if(Objects.equals(this.day, "friday"))
			dayNum1 = 5;
		else if(Objects.equals(this.day, "saturday"))
			dayNum1= 6;
		else if(Objects.equals(this.day, "sunday"))
			dayNum1 = 7;
		
		int dayNum2=0;
		if(Objects.equals(o.day, "monday"))
			dayNum2 = 1;
		else if(Objects.equals(o.day, "tuesday"))
			dayNum2 = 2;
		else if(Objects.equals(o.day, "wednesday"))
			dayNum2 = 3;
		else if(Objects.equals(o.day, "thursday"))
			dayNum2 = 4;
		else if(Objects.equals(o.day, "friday"))
			dayNum2 = 5;
		else if(Objects.equals(o.day, "saturday"))
			dayNum2= 6;
		else if(Objects.equals(o.day, "sunday"))
			dayNum2 = 7;
		
		if(dayNum1>dayNum2)
			return 1;
		else if(dayNum1<dayNum2)
			return -1;
		else
		{
			if(this.hour > o.hour)
				return 1;
			else if(this.hour < o.hour)
				return -1;
			else
			{
				if(this.minute >o.minute)
					return 1;
				else if(this.minute <o.minute)
					return -1;
				else
				{
					if(this.second > o.second)
						return 1;
					if(this.second<o.second)
						return -1;
					else
						return 0;
				}
			}
		}
		
	}
}