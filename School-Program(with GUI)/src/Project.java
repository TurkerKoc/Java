import java.util.ArrayList;
import java.util.Objects;

public class Project extends Assignment 
{
	public Project(String CName, String AId, String workType)
	{
		super(CName, AId, workType);
	}
	public Project(String CName, String AId, String dDate, String workType) 
	{
		super(CName, AId, dDate, workType);
	}
	
	@Override
	public double getAverage(ArrayList<Submission> mySubmission, Long StudentId, String CName) 
	{
		double grade = -1;
		boolean isThereAnySubmission = false;
		for (int j = 0; j < mySubmission.size(); j++) 
		{
			if(StudentId==Long.parseLong(mySubmission.get(j).getStudentId()) && Objects.equals(CName, mySubmission.get(j).getCourseName()) && Objects.equals(mySubmission.get(j).getSubmissionType(),"project"))
			{
				grade = mySubmission.get(j).getGrade();
				isThereAnySubmission = true;
			}
		}
		if(!isThereAnySubmission)
			return -1;
		return grade;
	}

}
