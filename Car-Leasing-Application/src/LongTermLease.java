
public class LongTermLease extends Lease
{
	public LongTermLease()
	{
		super();
	}
	public LongTermLease(int carModelYear)
	{
		super(carModelYear);
	}
	
	@Override
	public String calculateTotalPrice() 
	{
		double Price = Double.parseDouble(super.calculateTotalPrice());
		Price = Price*10;
		return Double.toString(Price);
	}
	
	
	@Override
	public void displayInfo() 
	{
		int modelYear = car.getCarModelYear();
		String mYear = Integer.toString(modelYear);
		
		if(calculateTotalPrice()=="0")
		{
			System.out.println("Because of invalid inputs lease of " + choice + " " +car.getCarBrandModel()+ " cancelled!");
		}
		else if(car.getCarModelYear()!=0)
		{
			System.out.println("Lease of " + choice + " " +car.getCarBrandModel()+" for the " + mYear + " model year starts at " + super.getLeaseStart() + ". Year ends at " + super.getLeaseEnd() + " .Year"
					+ " with cost " + this.calculateTotalPrice());

		}	
	}
	@Override
	public double calculateInsurance() {
		double cost = Double.parseDouble(calculateTotalPrice());
		return cost*(0.35);
	}
}
