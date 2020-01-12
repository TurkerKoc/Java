import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;


public class Person extends Item implements Schedule
{
	//ArrayList<Course> courses = new ArrayList<Course>();
	String fullname;
	long id;
	public Person() throws IDMismatchException
	{
		super(null);
		System.out.println("Enter full name: ");
		fullname = input.nextLine();
		System.out.println("Enter id: ");
		String temp = input.nextLine();
		if(authenticateID(temp))
			id = Long.parseLong(temp);
	}
	public Person(String fullname) throws IDMismatchException
	{
		super(null);
		System.out.println("Enter id: ");
		String temp = input.nextLine();
		if(authenticateID(temp))
			id = Long.parseLong(temp);
		this.fullname = fullname;
	}
	public Person(long id) throws IDMismatchException
	{
		super(null);
		System.out.println("Enter full name: ");
		fullname = input.nextLine();
		if(authenticateID(Long.toString(id)))
			this.id =id;
	}
	public Person(String fullname, long id) throws IDMismatchException
	{
		super(null);
		this.fullname = fullname;
		if(authenticateID(Long.toString(id)))
			this.id =id;
	}
	
	public boolean authenticateID(String id) throws IDMismatchException
	{
		if(id.charAt(0)=='0') {
			System.out.println("ID Must start with non-zero integer\n");
			throw new IDMismatchException(Long.parseLong(id));
		}
		else 
		{
			long tempId;
			int length=0;
			try {
				tempId = Long.parseLong(id);
			} catch (NumberFormatException e) {
				System.out.println("invalid ID!");
				return false;
			}
			while(tempId!=0)
			{
				tempId=tempId/10;
				length++;
			} 
			//String a = Long.toString(id);
			if(length!=11)
			{
				System.out.println("ID must be 11 digits");
				throw new IDMismatchException(Long.parseLong(id));				
			}
			else 
			{
				long temp = Long.parseLong(id);
				Long[] IdDigits = new Long[11];
				int index = 10;
				while (temp != 0) 
				{
					IdDigits[index] = temp%10;
					index--;
					temp=temp/10;
				}
				long cond1=IdDigits[0]+IdDigits[2]+IdDigits[4]+IdDigits[6]+IdDigits[8];
				long cond2=IdDigits[1]+IdDigits[3]+IdDigits[5]+IdDigits[7];
				if(IdDigits[9] != (7*(cond1) - cond2)%10)
				{
					System.out.println("Invalid id!");
					throw new IDMismatchException(Long.parseLong(id));
					
				}
				else if(IdDigits[10] != (IdDigits[0]+IdDigits[1]+IdDigits[2]+IdDigits[3]+IdDigits[4]+IdDigits[5]+IdDigits[6]+IdDigits[7]+IdDigits[8]+IdDigits[9])%10)
				{
					System.out.println("Invalid id!");
					throw new IDMismatchException(Long.parseLong(id));
				}	
				
				
			}
		}
		return true;
	}
	public String getFullname() {
		return fullname;
	}
	public long getId() {
		return id;
	}

	
	@Override
	public void displaySchedule()
	{
		ArrayList<Person> people = new ArrayList<Person>(getPeople());
		System.out.printf("Enter your name: ");
		String name = input.nextLine();
		System.out.printf("Enter your ID: ");
		long id = input.nextLong();
		boolean isPersonExist = false;
		for(int i=0;i<people.size();i++)
		{
			if(Objects.equals(people.get(i).fullname, name) && people.get(i).getId()==id)
			{
				if(people.get(i) instanceof UnderGrad)
				{
					UnderGrad temp = (UnderGrad)people.get(i);
					isPersonExist=true;					
					if(!temp.undergradCourses.isEmpty())
					{
						try {
							Collections.sort(temp.undergradCourses, Course::compareTo);
						} catch (NullPointerException e) {
							System.out.println("Course Times not assigned!");
							return;
						}
						for (int j = 0; j < temp.undergradCourses.size(); j++) 
						{
							System.out.println(temp.undergradCourses.get(j));						
						}					
					}
					else {
						System.out.println("You don't have any courses!");
					}
				}
				else if(people.get(i) instanceof PostGrad)
				{
					PostGrad temp = (PostGrad)people.get(i);
					isPersonExist=true;						
					if(!temp.postGradCourses.isEmpty())
					{
						try {
							Collections.sort(temp.postGradCourses, Course::compareTo);
						} catch (NullPointerException e) {
							System.out.println("Course Times not assigned!");
							return;
						}
						for (int j = 0; j < temp.postGradCourses.size(); j++) 
						{
							System.out.println(temp.postGradCourses.get(j));						
						}
					}
					else {
						System.out.println("You don't have any courses!");
					}
				}
				else if(people.get(i) instanceof Lecturer)
				{
					Lecturer temp = (Lecturer)people.get(i);
					isPersonExist=true;						

					if(!temp.courses.isEmpty())
					{
						try {
							Collections.sort(temp.courses, Course::compareTo);
						} catch (NullPointerException e) {
							System.out.println("Course Times not assigned!");
							return;
						}
						for (int j = 0; j < temp.courses.size(); j++) 
						{
							System.out.println(temp.courses.get(j));						
						}					
					}
					else {
						System.out.println("You don't have any courses!");
					}
				}
				
			}
		}
		if(!isPersonExist)
			System.out.println("Person not exist!");
	}
	public ArrayList<Course> displaySchForPerson(String Name, Long ID) //GUI
	{
		ArrayList<Person> people = new ArrayList<Person>(getPeople());
		String name = Name;
		long id = ID;
		boolean isPersonExist = false;
		for(int i=0;i<people.size();i++)
		{
			if(Objects.equals(people.get(i).fullname, name) && people.get(i).getId()==id)
			{
				if(people.get(i) instanceof UnderGrad)
				{
					UnderGrad temp = (UnderGrad)people.get(i);
					isPersonExist=true;					
					if(!temp.undergradCourses.isEmpty())
					{
						try {
							Collections.sort(temp.undergradCourses, Course::compareTo);
						} catch (NullPointerException e) {
							System.out.println("Course Times not assigned!");
							return null;
						}
						return temp.undergradCourses;
				
					}
					else {
						System.out.println("You don't have any courses!");
					}
				}
				else if(people.get(i) instanceof PostGrad)
				{
					PostGrad temp = (PostGrad)people.get(i);
					isPersonExist=true;						
					if(!temp.postGradCourses.isEmpty())
					{
						try {
							Collections.sort(temp.postGradCourses, Course::compareTo);
						} catch (NullPointerException e) {
							System.out.println("Course Times not assigned!");
							return null;
						}
						return temp.postGradCourses;

					}
					else {
						System.out.println("You don't have any courses!");
					}
				}
				else if(people.get(i) instanceof Lecturer)
				{
					Lecturer temp = (Lecturer)people.get(i);
					isPersonExist=true;						

					if(!temp.courses.isEmpty())
					{
						try {
							Collections.sort(temp.courses, Course::compareTo);
						} catch (NullPointerException e) {
							System.out.println("Course Times not assigned!");
							return null;
						}
						return temp.courses;
					
					}
					else {
						System.out.println("You don't have any courses!");
					}
				}
				
			}
		}
		if(!isPersonExist)
			System.out.println("Person not exist!");
		return null;
	}
	
}
