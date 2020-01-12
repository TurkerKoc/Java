
public class Car implements Insurance
{
	private String carBrandModel;
	private int carModelYear;
	
	public Car(String carModel, int carYear)
	{
		setCarBrandModel(carModel);
		setCarModelYear(carYear);
	}
	public void setCarBrandModel(String carBrandModel) {
		this.carBrandModel = carBrandModel;
	}
	public void setCarModelYear(int carModelYear) {
		this.carModelYear = carModelYear;
	}
	public String getCarBrandModel() {
		return carBrandModel;
	}
	public String getBrand()
	{
		String brand = "";
		for(int i=0;i<carBrandModel.length();i++)
		{
			char index = carBrandModel.charAt(i);
			if(index == ' ') 
			{
				break;
			}
			brand += index;
		}
		return brand;
	}
	
	public int getCarModelYear() {
		return carModelYear;
	}
	@Override
	public double calculateInsurance() 
	{
		double difference = carModelYear-2000;
		return difference*100;
	}
}
