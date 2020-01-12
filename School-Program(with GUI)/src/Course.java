import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;


public class Course extends Item implements Schedule, Comparable<Course>
{
	private Integer CourseCode;
	private DayTime dayTime;
	private ArrayList<Assignment> myWorks = new ArrayList<Assignment>(); //all assignments, lab works, projects;
	private ArrayList<Submission> mySubmissions = new ArrayList<Submission>(); // all submissions

	public Course() throws InputMismatchException
	{
		super();
		System.out.println("Enter Course Code: ");
		Integer CCode = input.nextInt();
		setCourseCode(CCode);
	}
	public Course(String Cname, Integer CCode)
	{
		super(Cname);
		setCourseCode(CCode);
	}
	
	public void setCourseCode(Integer CCode)
	{
		CourseCode=CCode;
	}
	public void setDayTime()
	{
		DayTime dayTime  = new DayTime();
		this.dayTime = dayTime;
	}
	public void setDayTime(DayTime time) //	FOR GUI
	{		
		this.dayTime = time;
	}
	public Integer getCourseCode()
	{
		return CourseCode;
	}
	public DayTime getDayTime() {
		return dayTime;
	}
	
	@Override
	public int compareTo(Course o) throws NullPointerException 
	{
		// TODO Auto-generated method stub
		if(this.dayTime.compareTo(o.dayTime) == 0)
			return 0;
		else if(this.dayTime.compareTo(o.dayTime) > 0)
			return 1;
		else
			return -1;
	}
	@Override
	public void displaySchedule() 
	{
		try {
			ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
			for(int i=0;i<getPeople().size();i++)
			{
				if(getPeople().get(i) instanceof Lecturer)
				{
					Lecturer temp = (Lecturer) getPeople().get(i);
					lecturers.add(temp);
				}
			}
			
			ArrayList<Course> myCourses = new ArrayList<Course>();
			for (int i = 0; i < lecturers.size(); i++) 
			{
				for (int j = 0; j < lecturers.get(i).courses.size() ;j++) {
					myCourses.add(lecturers.get(i).courses.get(j));
				}
				
			}
			if(myCourses.isEmpty())
			{
				System.out.println("There are no courses or lecturer's not registered yet");
			}
			else {
				Collections.sort(myCourses, Course::compareTo);
				System.out.println("Course time for all courses which lecturer registered: ");
				for (int j = 0; j < myCourses.size(); j++) 
				{
					System.out.println(myCourses.get(j));
				}			
			}
			
		} catch (NullPointerException e) {
			System.out.println("Course not exist or lecturers not registered!");

		}
	}
	public ArrayList<Course> dispScheduleExamArrayList()
	{
		try {
			ArrayList<Lecturer> lecturers = new ArrayList<Lecturer>();
			for(int i=0;i<getPeople().size();i++)
			{
				if(getPeople().get(i) instanceof Lecturer)
				{
					Lecturer temp = (Lecturer) getPeople().get(i);
					lecturers.add(temp);
				}
			}
			
			ArrayList<Course> myCourses = new ArrayList<Course>();
			for (int i = 0; i < lecturers.size(); i++) 
			{
				for (int j = 0; j < lecturers.get(i).courses.size() ;j++) {
					myCourses.add(lecturers.get(i).courses.get(j));
				}
				
			}
			if(myCourses.isEmpty())
			{
				System.out.println("There are no courses or lecturer's not registered yet");
			}
			else {
				Collections.sort(myCourses, Course::compareTo);
				System.out.println("Course time for all courses which lecturer registered: ");
				return myCourses;
			}
			
		} catch (NullPointerException e) {
			System.out.println("Course not exist or lecturers not registered!");

		}
		return null;

	}
	public void addNewAssignment(String courseName,String AsId, String dueDate) //for gui
	{
		myWorks.add(new Assignment(courseName, AsId,dueDate,"assignment"));
		System.out.println(myWorks);
	}
	public void addNewLabwork(String courseName,String AsId, String dueDate) //for gui
	{
		myWorks.add(new Labwork(courseName, AsId,dueDate,"labwork"));
		System.out.println(myWorks);
	}
	public void addNewProject(String courseName,String AsId, String dueDate) //for gui
	{
		myWorks.add(new Project(courseName, AsId,dueDate,"project"));
		System.out.println(myWorks);
	}
	
	
	public boolean addNewWork() //adding new assignment or lab work or project
	{
		int choice;
		while(true)
		{
			try {
				System.out.println("#1 Add new Assignment\n#2 Add new Labwork\n#3 Add new Project");
				choice = input.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("You should enter integer!");
				input.nextLine();
			}			
		}

		input.nextLine();
		if(choice==1)			//for adding new assignment
		{
			myWorks.add(new Assignment(getCourseName(), "assignment"));
			return true;
		}
		else if (choice==2)	//for adding new lab work
		{
			System.out.println("Enter Course Labwork ID: ");
			String LabId = input.nextLine();
			myWorks.add(new Labwork(getCourseName(), LabId, "labwork"));
			return true;
		}
		else if (choice==3)	//for adding new Project
		{
			System.out.println("Enter Course Project ID: ");
			String PrId = input.nextLine();
			myWorks.add(new Project(getCourseName(), PrId, "project"));
			return true;
		}
		else
		{
			System.out.println("Invalid Choice! ");
			return false;
		}
	}
	public void addNewAssSubmission(String CName, String StName, String StId, String AssId, String SubDate) //FOR GUI
	{
		mySubmissions.add(new Submission(StName, StId, getCourseName(), AssId,SubDate, "assignment"));	
	}
	public void addNewLabSubmission(String CName, String StName, String StId, String AssId, String SubDate) //FOR GUI
	{
		mySubmissions.add(new Submission(StName, StId, getCourseName(), AssId,SubDate, "labwork"));	
		System.out.println("lab sub done");
	}
	public void addNewProjectSubmission(String CName, String StName, String StId, String AssId, String SubDate) //FOR GUI
	{
		mySubmissions.add(new Submission(StName, StId, getCourseName(), AssId,SubDate, "project"));
		System.out.println("Project sub done");
	}
	public void addNewSubmission() 
	{
		int choice2;
		while(true)
		{
			try {
				System.out.println("#1 Submit new Assignment\n#2 Submit new Labwork\n#3 Submit new Project");
				choice2 = input.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("You should enter integer!");
				input.nextLine();
			}			
		}
		
		input.nextLine();
		
		if (choice2==1)
		{
			System.out.println("Enter Assignment Id: ");
			String AssId = input.nextLine();
			String StName;
			String StId;
			boolean isAssIdValid=false;
			boolean isStudentRegisteredToCourse = false;
			for(int i=0; i<myWorks.size();i++)		//checking if there is an course and assignment in arrays
			{
				if(Objects.equals(getCourseName(), myWorks.get(i).getCourseName()) && Objects.equals(AssId, myWorks.get(i).getAssId()) &&  Objects.equals(myWorks.get(i).getWorkType(), "assignment"))
				{
					System.out.println("Enter Student Name: ");
					StName = input.nextLine();
					System.out.println("Enter Student ID: ");
					StId = input.nextLine();
					boolean isStudentExist = false;
					//checking isStudendExist?
					for (int j = 0; j < getPeople().size(); j++) 
					{
						if(Objects.equals(getPeople().get(j).fullname, StName) && Objects.equals(Long.toString(getPeople().get(j).id), StId))
						{
							if((getPeople().get(j) instanceof Lecturer))
							{
								System.out.printf("You can not add submission you are a lecturer!");
								return;
							}
							else 
							{
								isStudentExist = true;
								if(getPeople().get(j) instanceof UnderGrad)
								{
									UnderGrad temp = (UnderGrad) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								else if(getPeople().get(j) instanceof PostGrad)
								{
									PostGrad temp = (PostGrad) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								else if(getPeople().get(j) instanceof Lecturer)
								{
									Lecturer temp = (Lecturer) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								break;
							}
						}
					}
					if(isStudentExist && isStudentRegisteredToCourse)
					{
						mySubmissions.add(new Submission(StName, StId, getCourseName(), AssId, "assignment"));																				
						//is this student submitted already?
						Submission temp = mySubmissions.get(mySubmissions.size()-1);
						for(int j=0;j<mySubmissions.size()-1;j++)
						{
							//if that student already submit work then only change submission date 
							if(Objects.equals(getCourseName(), mySubmissions.get(j).getCourseName()) && Objects.equals(AssId, mySubmissions.get(j).getAssignmentId()) && Objects.equals(temp.getStudentId(), mySubmissions.get(j).getStudentId()))
							{
								if(mySubmissions.get(j).getGrade() > 0)
								{
									System.out.println("Your grade already given you can not submit again!");
									mySubmissions.remove(mySubmissions.size()-1);
								}
								else 
								{
									mySubmissions.get(j).setSubmissionDate(temp.getSubmissionDate());
									mySubmissions.remove(mySubmissions.size()-1);
								}

							}
						}

					}
					else {
						System.out.println("You should register first!");
					}
					isAssIdValid=true;
					break;
				}
			}
			if(!isAssIdValid)			//if assignment not exist
				System.out.println("Assignment not exits!");
				
			
		}
		else if (choice2==2)
		{
			System.out.println("Enter Labwork Id: ");
			String LabId = input.nextLine();
			String StName;
			String StId;
			boolean isLabIdValid=false;
			for(int i=0; i<myWorks.size();i++)		//checking if there is an course and assignment in arrays
			{
				if(Objects.equals(getCourseName(), myWorks.get(i).getCourseName()) && Objects.equals(LabId, myWorks.get(i).getAssId()) && Objects.equals(myWorks.get(i).getWorkType(), "labwork"))
				{
					System.out.println("Enter Student Name: ");
					StName = input.nextLine();
					System.out.println("Enter Student ID: ");
					StId = input.nextLine();
					boolean isStudentExist = false;
					boolean isStudentRegisteredToCourse = false;
					//checking isStudendExist?
					for (int j = 0; j < getPeople().size(); j++) 
					{
						if(Objects.equals(getPeople().get(j).fullname, StName) && Objects.equals(Long.toString(getPeople().get(j).id), StId))
						{
							if((getPeople().get(j) instanceof Lecturer))
							{
								System.out.printf("You can not add submission you are a lecturer!");
								return;
							}
							else 
							{
								isStudentExist = true;
								if(getPeople().get(j) instanceof UnderGrad)
								{
									UnderGrad temp = (UnderGrad) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								else if(getPeople().get(j) instanceof PostGrad)
								{
									PostGrad temp = (PostGrad) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								else if(getPeople().get(j) instanceof Lecturer)
								{
									Lecturer temp = (Lecturer) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								break;
							}
						}
					}
					if(isStudentExist && isStudentRegisteredToCourse)
					{
						mySubmissions.add(new Submission(StName, StId, getCourseName(), LabId, "labwork"));																										
						//is this student submitted already?
						Submission temp = mySubmissions.get(mySubmissions.size()-1);
						for(int j=0;j<mySubmissions.size()-1;j++)
						{
							//if that student already submit work then only change submission date 
							if(Objects.equals(getCourseName(), mySubmissions.get(j).getCourseName()) && Objects.equals(LabId, mySubmissions.get(j).getAssignmentId()) && Objects.equals(temp.getStudentId(), mySubmissions.get(j).getStudentId()))
							{
								//if grade is given already remove new submission
								if(mySubmissions.get(j).getGrade() > 0)
								{
									System.out.println("Your grade already given you can not submit again!");
									mySubmissions.remove(mySubmissions.size()-1);
								}
								else 
								{
									mySubmissions.get(j).setSubmissionDate(temp.getSubmissionDate());
									mySubmissions.remove(mySubmissions.size()-1);
								}
							}
						}						
					}
					else {
						System.out.println("You should register first!");
					}
					isLabIdValid=true;
					break;
				}
			}
			if(!isLabIdValid)			//if lab not work exist
				System.out.println("Lab work not exist!");	
		}
		else if(choice2==3)
		{
			System.out.println("Enter Project Id: ");
			String PrId = input.nextLine();
			boolean isPrIdValid=false;
			for(int i=0; i<myWorks.size();i++)		//checking if there is an course and project in arrays
			{
				if(Objects.equals(getCourseName(), myWorks.get(i).getCourseName()) && Objects.equals(PrId, myWorks.get(i).getAssId()) && Objects.equals(myWorks.get(i).getWorkType(), "project"))
				{
					System.out.println("Enter Student Name: ");
					String StName = input.nextLine();
					System.out.println("Enter Student ID: ");
					String StId = input.nextLine();
					boolean isStudentExist = false;
					boolean isStudentRegisteredToCourse = false;
					//checking isStudendExist?
					for (int j = 0; j < getPeople().size(); j++) 
					{
						if(Objects.equals(getPeople().get(j).fullname, StName) && Objects.equals(Long.toString(getPeople().get(j).id), StId))
						{
							if((getPeople().get(j) instanceof Lecturer))
							{
								System.out.printf("You can not add submission you are a lecturer!");
								return;
							}
							else 
							{
								isStudentExist = true;
								if(getPeople().get(j) instanceof UnderGrad)
								{
									UnderGrad temp = (UnderGrad) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								else if(getPeople().get(j) instanceof PostGrad)
								{
									PostGrad temp = (PostGrad) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								else if(getPeople().get(j) instanceof Lecturer)
								{
									Lecturer temp = (Lecturer) getPeople().get(j);
									isStudentRegisteredToCourse = temp.isCourseExist(getCourseName());
								}
								break;
							}
						}
					}
					if(isStudentExist && isStudentRegisteredToCourse)
					{
						boolean isProjectSubmitted = false;
						for (int j=0; j < mySubmissions.size(); j++)   //checking for is student submitted project already
						{
							if(Objects.equals(StName, mySubmissions.get(j).getstudentName()) && Objects.equals(StId, mySubmissions.get(j).getStudentId()) && Objects.equals(getCourseName(), mySubmissions.get(j).getCourseName()) && Objects.equals(mySubmissions.get(j).getAssignmentId(), PrId) && Objects.equals(mySubmissions.get(j).getSubmissionType(), "project"))
								isProjectSubmitted = true;
						}
						if (!isProjectSubmitted)   //if student didin't submit any project
						{
							System.out.println("Enter Submission Date(day/month/year): ");
							String SubDate = input.nextLine();
							mySubmissions.add(new Submission(StName, StId, getCourseName(), PrId, SubDate, "project"));
						}
						else 
						{
							System.out.println("You already submitted project!");
						}			
					}
					else {
						System.out.println("You should register first!");
					}
					isPrIdValid=true;
					break;
				}
			}
			if(!isPrIdValid) //if Project not exist
				System.out.println("project not exist!");	
		}
		else {
			System.out.println("Invalid Choice!");
		}
	}
	
	public void gradeSubmissions()
	{
		int choice2;
		while(true)
		{
			try {
				System.out.println("#1 Grade Assignment\n#2 Grade Labwork\n#3 Grade Project");
				choice2 = input.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("You should enter integer!");
				input.nextLine();
			}			
		}

		input.nextLine();
		
		if(choice2==1)
		{
			System.out.println("Enter Assignment Id: ");
			String AssId = input.nextLine();
			boolean isAssignmentExist=false; 
			for(int i=0;i<mySubmissions.size();i++)		//checking if submission is submitted for given course name and ass id
			{		
				if(Objects.equals(getCourseName(), mySubmissions.get(i).getCourseName()) && Objects.equals(AssId, mySubmissions.get(i).getAssignmentId()) && Objects.equals(mySubmissions.get(i).getSubmissionType(), "assignment")) //if submission's CName and assId is valid
				{
					isAssignmentExist=true;
					int grade;
					while(true)
					{
						try {
							System.out.println("Enter grade for: "+ mySubmissions.get(i).getstudentName()+" ID: "+ mySubmissions.get(i).getStudentId());
							grade = input.nextInt();
							mySubmissions.get(i).setGrade(grade); //take grade as an input
							break;
						} catch (InputMismatchException e) {
							System.out.println("You should enter integer");
							input.nextLine();
						}
					}
					mySubmissions.get(i).setGrade(grade); //take grade as an input 
					
					//now we will change grades for submitting late
					String SubmissionDate = mySubmissions.get(i).getSubmissionDate();
					String DueDate="0";
					for(int j=0;j<myWorks.size();j++) //traversing myWorks Array for finding due date of assignment
					{
						if(Objects.equals(getCourseName(), myWorks.get(j).getCourseName()) && Objects.equals(AssId,myWorks.get(j).getAssId()) && Objects.equals(myWorks.get(j).getWorkType(), "assignment"))
						{
							DueDate = myWorks.get(j).getDueDate();
						}
					}
					int Difference = DateClass.differenceBtwSubAndDueDate(SubmissionDate,DueDate); //finding difference between submission and due date
					if(Difference>0)  //if difference>0 that means submission is bigger than due date
					{
						if(Difference>=10) //if difference>10 that means in every case student will be graded as 0
						{
							mySubmissions.get(i).setGrade(0);
						}
						else 			//if 0>submissionDate<10 then change grade
						{
							int tempGrade = mySubmissions.get(i).getGrade(); //tempGrade for calculation
							if(tempGrade-(Difference*10)<0)     //if tempGrade-(Difference*10)<0 that means in every case student will be graded as 0
								mySubmissions.get(i).setGrade(0);
							else								//else change grade
								mySubmissions.get(i).setGrade(tempGrade-(Difference*10));
						}
					}
					
				}
			}
			if(!isAssignmentExist) //if ass does not exist
				System.out.println("Assignment Not Found!");			
		}
		if(choice2==2)
		{
			System.out.println("Enter Labwork Id: ");
			String LabId = input.nextLine();
			boolean isLabworkExist=false; 
			for(int i=0;i<mySubmissions.size();i++)		//checking if submission is submitted for given course name and ass id
			{		
				if(Objects.equals(getCourseName(), mySubmissions.get(i).getCourseName()) && Objects.equals(LabId, mySubmissions.get(i).getAssignmentId()) && Objects.equals(mySubmissions.get(i).getSubmissionType(), "labwork")) //if submission's CName and assId is valid
				{
					isLabworkExist=true;
					int grade;
					while(true)
					{
						try {
							System.out.println("Enter grade for: "+mySubmissions.get(i).getstudentName()+" ID: "+mySubmissions.get(i).getStudentId());
							grade = input.nextInt();
							mySubmissions.get(i).setGrade(grade); //take grade as an input
							break;
						} catch (InputMismatchException e) {
							System.out.println("You should enter integer");
							input.nextLine();
						}
					}
					//now we will change grades for submitting late
					String SubmissionDate = mySubmissions.get(i).getSubmissionDate();
					String DueDate="0";
					for(int j=0;j<myWorks.size();j++) //traversing lab Array for finding due date	
					{
						if(Objects.equals(getCourseName(), myWorks.get(j).getCourseName())&&Objects.equals(LabId,myWorks.get(j).getAssId()) && Objects.equals(myWorks.get(j).getWorkType(), "labwork"))
						{
							DueDate = myWorks.get(j).getDueDate();
						}
					}
					int Difference = DateClass.differenceBtwSubAndDueDate(SubmissionDate,DueDate); //finding difference between submission and due date
					if(Difference>0)  //if difference>0 that means submission is bigger than due date
					{
						if(Difference>=10) //if difference>10 that means in every case student will be graded as 0
						{
							mySubmissions.get(i).setGrade(0);
						}
						else 			//if 0>submissionDate<10 then change grade
						{
							int tempGrade = mySubmissions.get(i).getGrade(); //tempGrade for calculation
							if(tempGrade-(Difference*10)<0)     //if tempGrade-(Difference*10)<0 that means in every case student will be graded as 0
								mySubmissions.get(i).setGrade(0);
							else								//else change grade
								mySubmissions.get(i).setGrade(tempGrade-(Difference*10));
						}
					}
					
				}
			}
			if(!isLabworkExist) //if ass does not exist
				System.out.println("Labwork Not Found!");		
		}
		if(choice2==3)
		{
			System.out.println("Enter Project Id: ");
			String PrId = input.nextLine();
			boolean isProjectExist=false; 
			
			for(int i=0;i<mySubmissions.size();i++)		//checking if submission is submitted for given course name and ass id
			{		
				if(Objects.equals(getCourseName(), mySubmissions.get(i).getCourseName()) && Objects.equals(PrId, mySubmissions.get(i).getAssignmentId()) && Objects.equals(mySubmissions.get(i).getSubmissionType(), "project")) //if submission's CName and assId is valid
				{
					isProjectExist=true;
					int grade;
					while(true)
					{
						try {
							System.out.println("Enter grade for: "+mySubmissions.get(i).getstudentName()+" ID: "+mySubmissions.get(i).getStudentId());
							grade = input.nextInt();
							mySubmissions.get(i).setGrade(grade); //take grade as an input
							break;
						} catch (InputMismatchException e) {
							System.out.println("You should enter integer");
							input.nextLine();
						}
					}
					mySubmissions.get(i).setGrade(grade); //take grade as an input 
					
					//now we will change grades for submitting late
					String SubmissionDate = mySubmissions.get(i).getSubmissionDate();
					String DueDate="0";
					for(int j=0;j<myWorks.size();j++) //traversing project Array for finding due date	
					{
						if(Objects.equals(getCourseName(), myWorks.get(j).getCourseName())&&Objects.equals(PrId,myWorks.get(j).getAssId()) && Objects.equals(myWorks.get(j).getWorkType(), "project"))
						{
							DueDate = myWorks.get(j).getDueDate();
						}
					}
					int Difference = DateClass.differenceBtwSubAndDueDate(SubmissionDate,DueDate); //finding difference between submission and due date
					if(Difference>0)  //if difference>0 that means submission is bigger than due date
					{
						if(Difference>=10) //if difference>10 that means in every case student will be graded as 0
						{
							mySubmissions.get(i).setGrade(0);
						}
						else 			//if 0>submissionDate<10 then change grade
						{
							int tempGrade = mySubmissions.get(i).getGrade(); //tempGrade for calculation
							if(tempGrade-(Difference*10)<0)     //if tempGrade-(Difference*10)<0 that means in every case student will be graded as 0
								mySubmissions.get(i).setGrade(0);
							else								//else change grade
								mySubmissions.get(i).setGrade(tempGrade-(Difference*10));
						}
					}
					
				}
			}
			if(!isProjectExist) //if ass does not exist
				System.out.println("Project Not Found!");
		}
		System.out.println("Grade assignments done!");
	}
	
	public void displayGrades()
	{
		int choice2;
		while(true)
		{
			try {
				System.out.println("#1 Display Assignment grades\n#2 Display Labwork Grades\n#3 Display Project Grades");
				choice2 = input.nextInt();
				break;
			} catch (InputMismatchException e) {
				System.out.println("You should enter integer!");
				input.nextLine();
			}			
		}
		input.nextLine();
		if(choice2==1)   //ass
		{
			System.out.println("Enter Assignment Id: ");
			String AssId = input.nextLine();
			ArrayList<Submission> myAssSubmission = new ArrayList<Submission>();
			for(int i=0;i<mySubmissions.size();i++)
			{
				if(Objects.equals(mySubmissions.get(i).getSubmissionType(), "assignment"))
					myAssSubmission.add(mySubmissions.get(i));
			}
			boolean isAssignmentExist = false;
			Collections.sort(myAssSubmission, Submission::compareTo);
			Collections.reverse(myAssSubmission);
			for(int i=0;i<myAssSubmission.size();i++)
			{		
				if(Objects.equals(getCourseName(), myAssSubmission.get(i).getCourseName()) && Objects.equals(AssId, myAssSubmission.get(i).getAssignmentId()))
				{
					isAssignmentExist=true;
					System.out.println("Course Name: " + getCourseName());
					System.out.println("Student Name: " + myAssSubmission.get(i).getstudentName());
					System.out.println("Student ID: " + myAssSubmission.get(i).getStudentId());
					System.out.println("Student Grade: " + myAssSubmission.get(i).getGrade());
					System.out.println();
				}			
			}
			if(!isAssignmentExist)
			{
				System.out.println("Assignment does not exist!");
			}						
		}
		else if(choice2==2) //lab
		{
			System.out.println("Enter Labwork Id: ");
			String LabId = input.nextLine();
			boolean isLabExist = false;
			ArrayList<Submission> myLabSubmission = new ArrayList<Submission>();
			for(int i=0;i<mySubmissions.size();i++)
			{
				if(Objects.equals(mySubmissions.get(i).getSubmissionType(), "labwork"))
					myLabSubmission.add(mySubmissions.get(i));
			}
			Collections.sort(myLabSubmission, Submission::compareTo);
			Collections.reverse(myLabSubmission);
			for(int i=0;i<myLabSubmission.size();i++)
			{		
				if(Objects.equals(getCourseName(), myLabSubmission.get(i).getCourseName()) && Objects.equals(LabId, myLabSubmission.get(i).getAssignmentId()))
				{
					isLabExist=true;
					System.out.println("Course Name: " + getCourseName());
					System.out.println("Student Name: " + myLabSubmission.get(i).getstudentName());
					System.out.println("Student ID: " + myLabSubmission.get(i).getStudentId());
					System.out.println("Student Grade: " + myLabSubmission.get(i).getGrade());
					System.out.println();
				}			
			}
			if(!isLabExist)
			{
				System.out.println("Labwork does not exist!");
			}	
		}
		else if(choice2==3) //project
		{
			System.out.println("Enter Project Id: ");
			String PrId = input.nextLine();
			boolean isProjectExist = false;
			ArrayList<Submission> myProjectSubmission = new ArrayList<Submission>();
			for(int i=0;i<mySubmissions.size();i++)
			{
				if(Objects.equals(mySubmissions.get(i).getSubmissionType(), "project"))
					myProjectSubmission.add(mySubmissions.get(i));
			}
			Collections.sort(myProjectSubmission, Submission::compareTo);
			Collections.reverse(myProjectSubmission);
			for(int i=0;i<myProjectSubmission.size();i++)
			{		
				if(Objects.equals(getCourseName(), myProjectSubmission.get(i).getCourseName()) && Objects.equals(PrId, myProjectSubmission.get(i).getAssignmentId()))
				{
					isProjectExist=true;
					System.out.println("Course Name: " + getCourseName());
					System.out.println("Student Name: " + myProjectSubmission.get(i).getstudentName());
					System.out.println("Student ID: " + myProjectSubmission.get(i).getStudentId());
					System.out.println("Student Grade: " + myProjectSubmission.get(i).getGrade());
					System.out.println();
				}			
			}
			if(!isProjectExist)
			{
				System.out.println("Project does not exist!");
			}	
		}
		else 
			System.out.println("Invalid choice!");
	}
	public void displayAverageGrade()
	{
		ArrayList<Long> students = new ArrayList<Long>();
		for(int i=0; i<getPeople().size();i++)
		{
			if(getPeople().get(i) instanceof Student)
				students.add(getPeople().get(i).id);
		}
		for(int i=0;i<students.size();i++)  //traversing students
		{
			System.out.println();
			boolean isAnyAssExist = false;
			double AssAverage = 0;
			for(int j=0;j<myWorks.size();j++)		//traversing Ass Array to make sure there is at least 1 ass to that course
			{
				if(Objects.equals(myWorks.get(j).getCourseName(), getCourseName()));		//if it is exist then calculate averages for students
				{
					if(Objects.equals(myWorks.get(j).getWorkType(), "assignment"))
					{
						AssAverage = myWorks.get(j).getAverage(mySubmissions, students.get(i), getCourseName());
						isAnyAssExist = true;
						break;
						
					}
				}
			}
			if(!isAnyAssExist)
				System.out.println("No Assignment Found!");
			else if(AssAverage>=0) 
			{
				System.out.println("Student id: " + students.get(i));
				System.out.println("Assignment Average for " + getCourseName() + " course: "  + AssAverage);	
			}
			
			
			
			boolean isAnyLabExist = false;
			double LabAverage = 0;
			for(int j=0;j<myWorks.size();j++)		//traversing Lab Array to make sure there is at least 1 Lab to that course
			{
				if(Objects.equals(myWorks.get(j).getCourseName(), getCourseName())); 
				{
					if (Objects.equals(myWorks.get(j).getWorkType(), "labwork"))
					{
						LabAverage = myWorks.get(j).getAverage(mySubmissions, students.get(i), getCourseName());
						isAnyLabExist = true;
						break;	
					}
				}
			}
			if(!isAnyLabExist)
				System.out.println("No Labwork Found!");
			else if(LabAverage>=0) 
			{
				System.out.println("Student id: " + students.get(i));
				System.out.println("Labwork Average for " + getCourseName() + " course: "  + LabAverage);	
			}	
			
		
			boolean isAnyProjectExist = false;
			double ProjectGrade = 0;
			for(int j=0;j<myWorks.size();j++)			//traversing Project Array to make sure there is a project
			{
				if(Objects.equals(myWorks.get(j).getCourseName(), getCourseName()));
				{
					if(Objects.equals(myWorks.get(j).getWorkType(), "project"))
					{
						ProjectGrade = myWorks.get(j).getAverage(mySubmissions, students.get(i), getCourseName());
						isAnyProjectExist = true;
						break;					
					}
				}
			}
			if(!isAnyProjectExist)
				System.out.println("No Projects Found!");
			else if(ProjectGrade>=0)
			{
				System.out.println("Student id: " + students.get(i));
				System.out.println("Project Grade for " + getCourseName() + " course: "  + ProjectGrade);	
			}						
		}		
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("%s course is at %s", this.getCourseName(), this.dayTime);
	}
	public ArrayList<Submission> getMySubmissions() {
		return mySubmissions;
	}
	public String avgGrade()
	{
		String print = "";
		ArrayList<Long> students = new ArrayList<Long>();
		for(int i=0; i<getPeople().size();i++)
		{
			if(getPeople().get(i) instanceof Student)
				students.add(getPeople().get(i).id);
		}
		for(int i=0;i<students.size();i++)  //traversing students
		{
			System.out.println();
			boolean isAnyAssExist = false;
			double AssAverage = 0;
			for(int j=0;j<myWorks.size();j++)		//traversing Ass Array to make sure there is at least 1 ass to that course
			{
				if(Objects.equals(myWorks.get(j).getCourseName(), getCourseName()));		//if it is exist then calculate averages for students
				{
					if(Objects.equals(myWorks.get(j).getWorkType(), "assignment"))
					{
						AssAverage = myWorks.get(j).getAverage(mySubmissions, students.get(i), getCourseName());
						isAnyAssExist = true;
						break;
						
					}
				}
			}
			if(!isAnyAssExist)
				print += "No Assignment Found!\n";
				
			else if(AssAverage>=0) 
			{
				print += "Student id: " + students.get(i) + "\n";
				print += "Assignment Average for " + getCourseName() + " course: "  + AssAverage + "\n";			
			}
			
			
			
			boolean isAnyLabExist = false;
			double LabAverage = 0;
			for(int j=0;j<myWorks.size();j++)		//traversing Lab Array to make sure there is at least 1 Lab to that course
			{
				if(Objects.equals(myWorks.get(j).getCourseName(), getCourseName())); 
				{
					if (Objects.equals(myWorks.get(j).getWorkType(), "labwork"))
					{
						LabAverage = myWorks.get(j).getAverage(mySubmissions, students.get(i), getCourseName());
						isAnyLabExist = true;
						break;	
					}
				}
			}
			if(!isAnyLabExist)
				print += "No Labwork Found!\n";
			else if(LabAverage>=0) 
			{
				print += "Student id: " + students.get(i) + "\n";
				print += "Labwork Average for " + getCourseName() + " course: "  + LabAverage + "\n";
				
			}	
			
		
			boolean isAnyProjectExist = false;
			double ProjectGrade = 0;
			for(int j=0;j<myWorks.size();j++)			//traversing Project Array to make sure there is a project
			{
				if(Objects.equals(myWorks.get(j).getCourseName(), getCourseName()));
				{
					if(Objects.equals(myWorks.get(j).getWorkType(), "project"))
					{
						ProjectGrade = myWorks.get(j).getAverage(mySubmissions, students.get(i), getCourseName());
						isAnyProjectExist = true;
						break;					
					}
				}
			}
			if(!isAnyProjectExist)
				print += "No projects Found!\n";
			else if(ProjectGrade>=0)
			{
				print += "Student id: " + students.get(i) + "\n";
				print += "Project Grade for " + getCourseName() + " course: "  + ProjectGrade + "\n";
				
				
			}						
		}	
		return print;
	}
	

}