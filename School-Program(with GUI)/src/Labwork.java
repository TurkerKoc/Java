import java.util.ArrayList;
import java.util.Objects;

public class Labwork extends Assignment
{
	public Labwork(String CName, String LabId, String workType)
	{
		super(CName, LabId, workType);
	}
	public Labwork(String CName, String AId, String dDate, String workType) 
	{
		super(CName, AId, dDate, workType);
	}
	@Override
	public double getAverage(ArrayList<Submission> mySubmission, Long StudentId, String CName) 
	{
		double LabProduct = 1;
		double LabNum = 0;
		boolean isThereAnySubmission = false;
		for (int j = 0; j < mySubmission.size(); j++) 
		{
			if(StudentId==Long.parseLong(mySubmission.get(j).getStudentId()) && Objects.equals(CName, mySubmission.get(j).getCourseName()) && Objects.equals(mySubmission.get(j).getSubmissionType(),"labwork"))
			{
				LabProduct *= mySubmission.get(j).getGrade();
				LabNum++;	
				isThereAnySubmission = true;
			}
		}
		if(!isThereAnySubmission)
			return -1;
		double LabAverage = Math.pow(LabProduct, 1.0 / (double) LabNum);
		return LabAverage;			
	}
}
