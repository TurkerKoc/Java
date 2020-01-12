import java.util.ArrayList;
import java.util.Objects;

public class PostGrad extends Student 
{
	ArrayList<Course> postGradCourses = new ArrayList<Course>();
	public PostGrad() throws IDMismatchException
	{
		super();
	}
	public PostGrad(String name, Long id) throws IDMismatchException
	{
		super(name, id);
	}
	public void addNewCourse(Course courseObj)
	{
		boolean isCourseRegistered = false;
		for (int i = 0; i < postGradCourses.size(); i++) 
		{
			if(Objects.equals(postGradCourses.get(i).getCourseName(), courseObj.getCourseName()))
			{
				System.out.println("You already registered!");
				isCourseRegistered = true;
			}
		}
		if (!isCourseRegistered) {
			postGradCourses.add(courseObj);
		}
	}
	public boolean isCourseExist(String courseName)
	{
		boolean isCourseExist = false;
		for(int i=0; i<postGradCourses.size(); i++)
		{
			if(Objects.equals(courseName, postGradCourses.get(i).getCourseName()))
				isCourseExist=true;
		}
		return isCourseExist;
	}
}
