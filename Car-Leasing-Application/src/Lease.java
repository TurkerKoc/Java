import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

enum Categories
{
	Sedan, HatchPack, Van, Convertible;
}
public class Lease implements Insurance ,Comparable<Lease>
{

	Scanner input = new Scanner(System.in);

	private String driverName;
	private String duration;
	private int leaseStart;
	private int leaseEnd;
	private int monthlyCost;
	Car car;
	Categories choice;

	public Lease(String brandModel,String duration,String driverName)
	{
		Car myCar = new Car(brandModel, 2015);
		car = myCar;
		setLeaseStart(5);
		setLeaseEnd(11);
		choice = Categories.Sedan;
		setDriverName(driverName);
		setDuration(duration);

	}
	public Lease() 
	{
		int carModelYear=0;
		System.out.println("Enter car model year: ");
		carModelYear = input.nextInt();

		input.nextLine();
		System.out.println("Brand and Model: ");
		String Model = input.nextLine();
		System.out.println("Hire car type:\nPlease Type \"S\" for Sedan, \"H\" for HatchPack, \"V\" for Van, \"C\" for Convertible: ");
		String select = input.nextLine();
		System.out.println("Lease Starts: ");
		int Start = input.nextInt();
		System.out.println("Lease Ends: ");
		int End = input.nextInt();
		input.nextLine();
		if(Objects.equals(select, "S"))
		{
			choice = Categories.Sedan;
			System.out.println("Sedan car was leased!");
		}
		else if(Objects.equals(select, "H"))
		{
			choice = Categories.HatchPack;
			System.out.println("Hatchpack car was leased!");
		}
		else if(Objects.equals(select, "V"))
		{
			choice= Categories.Van;
			System.out.println("Van car was leased!");
		}
		else if(Objects.equals(select, "C"))
		{
			choice = Categories.Convertible;
			System.out.println("Convertible car was leased!");
		}
		else
			System.out.println("Invalid selection!\n");	
		
		
		Car myCar = new Car(Model, carModelYear);
		car = myCar;
		calculateCost();
		setLeaseStart(Start);
		setLeaseEnd(End);
	}
	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	public String getDriverName() {
		return driverName;
	}
	public String getDuration() {
		return duration;
	}
	public Lease(int carModelYear) 
	{
		System.out.println("Brand and Model: ");
		String Model = input.nextLine();
		System.out.println("Hire car type:\nPlease Type \"S\" for Sedan, \"H\" for HatchPack, \"V\" for Van, \"C\" for Convertible: ");
		String select = input.nextLine();
		System.out.println("Lease Starts: ");
		int Start = input.nextInt();
		System.out.println("Lease Ends: ");
		int End = input.nextInt();
		input.nextLine();
		if(Objects.equals(select, "S"))
		{
			choice = Categories.Sedan;
			System.out.println("Sedan car was leased!");
		}
		else if(Objects.equals(select, "H"))
		{
			choice = Categories.HatchPack;
			System.out.println("Hatchpack car was leased!");
		}
		else if(Objects.equals(select, "V"))
		{
			choice= Categories.Van;
			System.out.println("Van car was leased!");
		}
		else if(Objects.equals(select, "C"))
		{
			choice = Categories.Convertible;
			System.out.println("Convertible car was leased!");
		}
		else
			System.out.println("Invalid selection!\n");	
		
		Car myCar = new Car(Model, carModelYear);
		car = myCar;
		calculateCost();
		setLeaseStart(Start);
		setLeaseEnd(End);
		
	}
	@Override
	public int compareTo(Lease tmpLease) 
	{
		if(this.car.getCarModelYear() > tmpLease.car.getCarModelYear())
			return 1;
		else if(this.car.getCarModelYear() < tmpLease.car.getCarModelYear())
			return -1;
		else return 0;
	}
	public void setLeaseStart(int Start)
	{
		leaseStart = Start;
	}
	public void setLeaseEnd(int End)
	{
		leaseEnd = End;

	}
	public void setMonthlyCost(int Cost)
	{
		monthlyCost = Cost;
	}
	
	
	public void displayInfo()
	{
		int modelYear = car.getCarModelYear();
		String mYear = Integer.toString(modelYear);
		
		if(calculateTotalPrice()=="0")
		{
			System.out.println("Because of invalid inputs lease of " + choice + " " +car.getCarBrandModel()+ " cancelled!");
		}
		else if(car.getCarModelYear()==0)
		{
			System.out.println("Lease of " + choice + " " +car.getCarBrandModel()+" starts at " + leaseStart + ". Month ends at " + leaseEnd + " .Month with cost " + calculateTotalPrice());
		}
		else if(car.getCarModelYear()!=0)
		{
			System.out.println("Lease of " + choice + " " +car.getCarBrandModel()+" for the " + mYear + " model year starts at " + leaseStart + ". Month ends at " + leaseEnd + " .Month with cost " + calculateTotalPrice());

		}
	}
	public String calculateTotalPrice()
	{
		calculateCost();
		int price=0;
		int totalMonth=0;
		String priceResult="0";
		if(leaseEnd!=0&&leaseStart!=0&&leaseEnd>=leaseStart)
		{
			totalMonth = leaseEnd-leaseStart+1;
			price = totalMonth*monthlyCost;
			priceResult = Integer.toString(price);
			return priceResult;
		}
		else if(leaseEnd!=0&&leaseStart!=0&&leaseEnd<leaseStart)
		{
			totalMonth = leaseEnd+(12-leaseStart)+1;
			price = totalMonth*monthlyCost;
			priceResult = Integer.toString(price);
			return priceResult;
		}
		else
			return priceResult;
	}
	public String calculateCost()
	{
		int price = 500;
		String priceResult="0";
		int modelYear = car.getCarModelYear();
		if(modelYear>2000 && modelYear<=2005)
			price = 1000;
		else if(modelYear>2005 && modelYear<=2010)
			price = 1500;
		else if(modelYear>2010 && modelYear<=2015)
			price = 2000;
		else if(modelYear>2015)
			price = 2500;
		
		setMonthlyCost(price);
		
		priceResult = Integer.toString(price);
		return priceResult;	
	}
	public int getLeaseStart() {
		return leaseStart;
	}
	public int getLeaseEnd() {
		return leaseEnd;
	}
	public int getMonthlyCost() {
		return monthlyCost;
	}
	@Override
	public double calculateInsurance() {
		// TODO Auto-generated method stub
		double cost = Double.parseDouble(calculateTotalPrice());
		return cost*(0.25);
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("Driver Name: %s\nCar brand and Model: %s\nDuration: %s",getDriverName(), car.getCarBrandModel(), getDuration());
	}
}
