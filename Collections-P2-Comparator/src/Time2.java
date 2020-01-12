import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;
public class Time2
{
	Scanner input = new Scanner(System.in);
	private String day;
	private int hour;
	private int minute;
	private int second;
	public Time2() 
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
	public Time2(String day) 
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
	public Time2(String day, int hour) 
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
	public Time2(String day, int hour, int minute) 
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
	public Time2(String day, int hour, int minute, int second) 
	{
		setDay(day);
		setHour(hour);
		setMinute(minute);
		setSecond(second);
	}
	public Time2(int hour, int minute, int second)
	{
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
	
}