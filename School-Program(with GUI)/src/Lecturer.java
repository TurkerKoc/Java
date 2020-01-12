import java.util.ArrayList;
import java.util.Objects;

public class Lecturer extends Person 
{
	ArrayList<Course> courses  = new ArrayList<Course>();
	public Lecturer() throws IDMismatchException 
	{
		super();
	}
	public Lecturer(String name, Long id) throws IDMismatchException 
	{
		super(name ,id);
	}
	public void addNewCourse(Course courseObj)
	{
		boolean isCourseRegistered = false;
		for (int i = 0; i < courses.size(); i++) 
		{
			if(Objects.equals(courses.get(i).getCourseName(), courseObj.getCourseName()))
			{
				System.out.println("You already registered!");
				isCourseRegistered = true;
			}
		}
		if (!isCourseRegistered) {
			courses.add(courseObj);
		}
	}
	public boolean isCourseExist(String CourseName)
	{
		boolean isCourseExist = false;
		for(int i=0; i<courses.size(); i++)
		{
			if(Objects.equals(CourseName, courses.get(i).getCourseName()))
				isCourseExist=true;
		}
		return isCourseExist;
	}
}
