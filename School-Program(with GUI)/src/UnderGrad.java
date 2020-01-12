import java.util.ArrayList;
import java.util.Objects;

public class UnderGrad extends Student 
{
	ArrayList<Course> undergradCourses = new ArrayList<Course>();

	public UnderGrad() throws IDMismatchException
	{
		super();
	}
	public UnderGrad(String name, Long id) throws IDMismatchException
	{
		super(name,id);
	}
	public void addNewCourse(Course courseObj)
	{
		boolean isCourseRegistered = false;
		for (int i = 0; i < undergradCourses.size(); i++) 
		{
			if(Objects.equals(undergradCourses.get(i).getCourseName(), courseObj.getCourseName()))
			{
				System.out.println("You already registered!");
				isCourseRegistered = true;
			}
		}
		if (!isCourseRegistered) {
			undergradCourses.add(courseObj);
		}
	}
	public boolean isCourseExist(String courseName)
	{
		boolean isCourseExist = false;
		for(int i=0; i<undergradCourses.size(); i++)
		{
			if(Objects.equals(courseName, undergradCourses.get(i).getCourseName()))
				isCourseExist=true;
		}
		return isCourseExist;
	}
}
