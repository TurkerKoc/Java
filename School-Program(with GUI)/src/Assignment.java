import java.util.ArrayList;
import java.util.Objects;

public class Assignment extends Item
{
	private String assignmentId;
	private String dueDate;
	DateClass myDueDate;
	private String workType; //to determine its assignment
	public Assignment(String CName, String workType)
	{
		super(CName);
		System.out.println("Enter Course Assignment ID: ");
		String AssId = input.nextLine();
		System.out.println("Enter Due date(day/month/year): ");
		String DueDate = input.nextLine();
		setAssignmentId(AssId);
		setdueDate(DueDate);
		setWorkType(workType);
	}
	public Assignment(String CName, String AId, String workType)
	{
		super(CName);
		System.out.println("Enter Due date(day/month/year): ");
		String DueDate = input.nextLine();
		setAssignmentId(AId);
		setdueDate(DueDate);
		setWorkType(workType);

	}
	public Assignment(String CName, String AId, String dDate, String workType)
	{
		super(CName);
		setAssignmentId(AId);
		setdueDate(dDate);
		setWorkType("assignment");
		setWorkType(workType);

	}
	
	public double getAverage(ArrayList<Submission> mySubmission, Long StudentId, String CName)
	{

		double AssSum = 0;
		double AssNum = 0;
		boolean isThereAnySubmission = false;
		for (int j = 0; j < mySubmission.size(); j++) 
		{
			if(StudentId==Long.parseLong(mySubmission.get(j).getStudentId()) && Objects.equals(CName, mySubmission.get(j).getCourseName()) && Objects.equals(mySubmission.get(j).getSubmissionType(),"assignment"))
			{
				AssSum += mySubmission.get(j).getGrade();
				AssNum++;	
				isThereAnySubmission = true;
			}
		}
		if(!isThereAnySubmission)
			return -1;
		double AssAverage = AssSum/AssNum;
		return AssAverage;		
	}
	


	public void setAssignmentId(String AId)
	{
		assignmentId = AId;
	}
	public void setdueDate(String dDate)
	{
		dueDate = dDate;
		myDueDate = new DateClass(dDate);		
	}

	
	
	public String getAssId()
	{
		return assignmentId;
	}

	public String getDueDate()
	{
		return myDueDate.getDate();
	}
	public void setWorkType(String workType)
	{
		this.workType = workType;
	}
	public String getWorkType()
	{
		return workType;
	}
}
