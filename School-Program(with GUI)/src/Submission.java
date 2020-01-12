import java.util.Scanner;
import java.util.Comparator;

public class Submission implements Comparable<Submission>
{
	Scanner input = new Scanner(System.in);

	private String studentName;
	private String studentId;
	private String CourseName;
	private String assignmentId;
	private String submissionDate;
	private int grade;
	DateClass mySubDate;
	private String SubmissionType;

	public Submission(String CName, String AssId,String SubmissionType)
	{
		System.out.println("Enter Student Name: ");
		String StName = input.nextLine();
		System.out.println("Enter Student ID: ");
		String StId = input.nextLine();
		System.out.println("Enter Submission Date(day/month/year): ");
		String SubDate = input.nextLine();
		setCourseName(CName);
		setAssignmentId(AssId);
		setStudentId(StId);
		setStudentName(StName);
		setSubmissionDate(SubDate);
		this.SubmissionType = SubmissionType;
	}
	public Submission(String StName, String StId, String CName, String AssId, String SubmissionType)
	{
		setStudentId(StId);
		setStudentName(StName);
		setCourseName(CName);
		setAssignmentId(AssId);
		this.SubmissionType = SubmissionType;

		System.out.println("Enter Submission Date(day/month/year): ");
		String SubDate = input.nextLine();
		setSubmissionDate(SubDate);
	}
	public Submission(String StName, String StId, String CName, String AssId, String SubDate, String SubmissionType)
	{
		setStudentId(StId);
		setStudentName(StName);
		setCourseName(CName);
		setAssignmentId(AssId);
		setSubmissionDate(SubDate);
		this.SubmissionType = SubmissionType;
	}
	
	@Override
	public int compareTo(Submission sub) 
	{
		if(this.getGrade()<sub.getGrade())
			return -1;
		else if(this.getGrade()>sub.getGrade())
			return 1;
		else 
			return 0;
	}
	
	public void setStudentName(String StName)
	{
		studentName = StName;
	}
	public void setStudentId(String StId)
	{
		studentId = StId;
	}
	public void setCourseName(String CName)
	{
		CourseName = CName;
	}
	public void setAssignmentId(String AssId)
	{
		assignmentId = AssId;
	}
	public void setSubmissionDate(String SubDate)
	{
		submissionDate=SubDate;
		mySubDate = new DateClass(SubDate);
	}
	public void setGrade(int g)
	{
		if(g>-1&&g<101)
		{
			grade = g;
		}
		else
		{
			System.out.println("Invalid grade!(Enter grade between 0-100)");
		}
	}
	
	
	
	
	public String getstudentName()
	{
		return studentName;
	}
	public String getStudentId()
	{
		return studentId;
	}
	public String getCourseName()
	{
		return CourseName;
	}
	public String getAssignmentId()
	{
		return assignmentId;
	}
	
	public String getSubmissionDate()
	{
		return mySubDate.getDate();
	}
	public int getGrade()
	{
		return grade;
	}
	public String getSubmissionType() {
		return SubmissionType;
	}
}

