import java.util.ArrayList;
import java.util.Objects;

public class Student extends Person
{
	public Student()  throws IDMismatchException
	{
		super();
	}
	public Student(String fullname)  throws IDMismatchException
	{
		super(fullname);
	}
	public Student(String fullname, Long id)  throws IDMismatchException
	{
		super(fullname,id);
	}
	public Student(Long id) throws IDMismatchException
	{
		super(id);
	}
}
