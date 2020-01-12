import java.util.ArrayList;

public class PT extends Lecturer //Part Time Lecturer 
{
	ArrayList<Course> postgradCourses = new ArrayList<Course>();
	public PT() throws IDMismatchException
	{
		super();
	}
	public PT(String name, Long id) throws IDMismatchException
	{
		super(name, id);
	}
	
}
