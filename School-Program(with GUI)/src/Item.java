import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class Item 
{
	Scanner input = new Scanner(System.in);
	private  static ArrayList<Person> people = new ArrayList<Person>();
	private String CourseName;

	public Item()
	{
		System.out.println("Enter Course Name: ");
		String CName = input.nextLine();
		setCourseName(CName);
	}
	public Item(String CourseName)
	{
		setCourseName(CourseName);
	}
	public void setCourseName(String courseName) {
		this.CourseName = courseName;
	}
	public String getCourseName() {
		return CourseName;
	}
	public ArrayList<Person> getPeople() {
		return people;
	}
	public boolean addNewPostGradStudent(String fullName, Long id) //FOR GUI
	{
		PostGrad st;
		try {
			st = new PostGrad(fullName,id);
			boolean isStExist = false;
			for(int i = 0; i < people.size(); i++)
			{
				if(people.get(i).getId() == st.getId())
				{
					isStExist = true;
				}
			}
			if(!isStExist)
			{
				people.add(st);
				return true;
			}
		} catch (IDMismatchException e) {
		}
		return false;
	}
	public boolean addNewUnderGradStudent(String fullName, Long id) //FOR GUI
	{
		UnderGrad st;
		try {
			st = new UnderGrad(fullName,id);
			boolean isStExist = false;
			for(int i = 0; i < people.size(); i++)
			{
				if(people.get(i).getId() == st.getId())
				{
					isStExist = true;
				}
			}
			if(!isStExist)
			{
				people.add(st);
				return true;
			}
		} catch (IDMismatchException e) {
		}
		return false;
	}
	public boolean addNewPartTimeLecturer(String fullName, Long id) //FOR GUI
	{
		
		PT st;
		boolean isStExist = false;
		try {
			st = new PT(fullName,id);
			for(int i = 0; i < people.size(); i++)
			{
				if(people.get(i).getId() == st.getId())
				{
					isStExist = true;
					return false;
				}
			}
			if(!isStExist)
			{
				people.add(st);
				return true;
			}
		} catch (IDMismatchException e) {
		}
		return false;
		
	}
	public boolean addNewFullTimeLecturer(String fullName, Long id) //FOR GUI
	{
		
		FT st;
		boolean isStExist = false;
		try {
			st = new FT(fullName,id);
			for(int i = 0; i < people.size(); i++)
			{
				if(people.get(i).getId() == st.getId())
				{
					isStExist = true;
					return false;
				}
			}
			if(!isStExist)
			{
				people.add(st);
				System.out.println(people);
				return true;
			}
		} catch (IDMismatchException e) {
		}
		return false;
	}
	
	public void addNewPerson()
	{
		while(true)
		{
			try 
			{
				System.out.println("#1 add new Student\n"
								 + "#2 add new Lecturer");
				int choice = input.nextInt();
				if(choice==1)
				{
					System.out.println("#1 add new PostGraduate Student\n"
							+ "#2 add new UnderGraduate Student");
					int choice2 = input.nextInt();
					if(choice2==1)
					{
						PostGrad st;
						try {
							st = new PostGrad();
						} catch (IDMismatchException e) {
							break;
						}
						boolean isStExist = false;
						for(int i = 0; i < people.size(); i++)
						{
							if(people.get(i).getId() == st.getId())
							{
								System.out.println("You already registered!");
								isStExist = true;
							}
						}
						if(!isStExist)
							people.add(st);
						break;
						
					}
					else if(choice2==2)
					{
						UnderGrad st;
						try {
							st = new UnderGrad();
						} catch (IDMismatchException e) {
							break;
						}
						boolean isStExist = false;
						for(int i = 0; i < people.size(); i++)
						{
							if(people.get(i).getId() == st.getId())
							{
								isStExist = true;
							}
						}
						if(!isStExist)
							people.add(st);
						break;
					}
					else 
					{
						System.out.println("Invalid input try again!");
						continue;
					}
				}
				else if(choice==2)
				{
					System.out.println("#1 add new PartTime Lecturer\n"
							+ "#2 add new FullTime Lecturer");
					int choice3 = input.nextInt();
					if(choice3==1)
					{
						PT st;
						try {
							st = new PT();
						} catch (IDMismatchException e) {
							break;
						}
						boolean isStExist = false;
						for(int i = 0; i < people.size(); i++)
						{
							if(people.get(i).getId() == st.getId())
							{
								System.out.println("You already registered!");
								isStExist = true;
							}
						}
						if(!isStExist)
							people.add(st);
						break;
					}
					else if(choice3==2)
					{
						FT st;
						try {
							st = new FT();
						} catch (IDMismatchException e) {
							break;
						}
						boolean isStExist = false;
						for(int i = 0; i < people.size(); i++)
						{
							if(people.get(i).getId() == st.getId())
							{
								System.out.println("You already registered!");
								isStExist = true;
							}
						}
						if(!isStExist)
							people.add(st);
						break;
					}
					else 
					{
						System.out.println("Invalid input try again");
						continue;
					}
				}
				else 
				{
					System.out.println("Invalid input");
					continue;
				}
			} catch (InputMismatchException e){
				System.out.println("Invalid input try again!");
				input.nextLine();
			}		
		}

		
		
	}
	public void courseRegistration(Course courseObj, Person person)
	{
		boolean isPersonAStudent = false;
		boolean isCourseAdded = false;
		for (int i = 0; i < people.size(); i++) 
		{
			if(people.get(i).getId() == person.id && Objects.equals(person.fullname, people.get(i).fullname))
			{
				if(people.get(i) instanceof UnderGrad)
				{
					UnderGrad temp = (UnderGrad) people.get(i);
					temp.addNewCourse(courseObj);
					people.set(i, temp);
					isPersonAStudent = true;
					isCourseAdded = true;
					break;
				}
				else if(people.get(i) instanceof PostGrad)
				{
					PostGrad temp = (PostGrad) people.get(i);
					temp.addNewCourse(courseObj);
					people.set(i, temp);
					isPersonAStudent = true;
					isCourseAdded = true;
				}
			}
		}
		if(!isPersonAStudent)
		{
			for (int i = 0; i < people.size(); i++) 
			{
				if(people.get(i).getId() == person.id && Objects.equals(person.fullname, people.get(i).fullname))
				{
					if(people.get(i) instanceof Lecturer)
					{
						Lecturer temp = (Lecturer) people.get(i);
						temp.addNewCourse(courseObj);
						people.set(i, temp);
						isCourseAdded = true;
					}
				}
			}
		}
		if(!isCourseAdded)
		{
			System.out.println("You should register first!");
		}
	}
	public static void displaySchSelection()
	{
		Scanner input = new Scanner(System.in);
		int choice;
		while(true)
		{
			try {
				System.out.println("#1 For planning exam locations\n"
						 + "#2 For lecturers/students");
				choice = input.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("You should enter integer!");
				input.nextLine();
			}			
		}

		input.nextLine();
		if(choice==1)
		{
			Course temp = new Course(null, null);
			temp.displaySchedule();
		}
		else if(choice==2)
		{
			try {
				String a = "44497498896";			
				Person temp = new Person(null, Long.parseLong(a)); 
				temp.displaySchedule();
			} catch (IDMismatchException e) {
				return;
			}
		}
	}
	public static void sortCourses(ArrayList<Course> myCourses)
	{
		Collections.sort(myCourses,Course::compareTo);
	}
}
