import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class Main 
{

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		ArrayList<Course> myCourse = new ArrayList<Course>(); //all courses
		
		JFrame frame = new JFrame("Main Menu");
		final String[] menus = {"#1 Add new Course","#2 Add new Assignment","#3 Submit assignment","#4 Grade assignments","#5 Display grades","#6 Display average grade of each student","#7 Schedule courses","#8 Lecturer / student registration","#9 Course registration","#10 Display schedule","#11 Exit"};
		JList menu = new JList(menus);
		menu.setFont(new Font("Arial", 12, 24));
		menu.setVisibleRowCount(11);
		menu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		
		
		
//#1 is selected ->ADD COURSE FRAME
		JFrame addCourseFrame = new JFrame("Add Course");
		addCourseFrame.setLayout(null);

		JLabel courseNameLabel = new JLabel("Course Name:");
		courseNameLabel.setBounds(10, 100, 200, 100);
		courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		addCourseFrame.add(courseNameLabel);
		
		JTextField enterCourseNameTF = new JTextField();
		enterCourseNameTF.setBounds(150, 135, 300, 30);
		addCourseFrame.add(enterCourseNameTF);
		
		JLabel courseCodeLabel = new JLabel("Course Code:");
		courseCodeLabel.setBounds(10, 300, 200, 100);
		courseCodeLabel.setFont(new Font("Arial", Font.BOLD, 20));
		addCourseFrame.add(courseCodeLabel);
		
		JTextField enterCourseCodeTF = new JTextField();
		enterCourseCodeTF.setBounds(145, 335, 300, 30);
		addCourseFrame.add(enterCourseCodeTF);
		
		JButton saveButton = new JButton("Save");
		saveButton.setBounds(400,500,80,50);
		addCourseFrame.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {				
				String courseName = enterCourseNameTF.getText();
				String courseCode = enterCourseCodeTF.getText();
				int code;
				try {
					code = Integer.parseInt(courseCode);
					Course temp = new Course(courseName,code);
					myCourse.add(temp);
					enterCourseCodeTF.setText("");
					enterCourseNameTF.setText("");
					addCourseFrame.setVisible(false);
					frame.setVisible(true);
					
					System.out.println(myCourse);
				} catch (Exception e2) {
					System.err.println("Problem occurs Course not added");
				}
				
			}
		});
		
		JButton cancelButton = new JButton("Cancel");
		cancelButton.setBounds(20,500,80,50);
		addCourseFrame.add(cancelButton);
		cancelButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				enterCourseCodeTF.setText("");
				enterCourseNameTF.setText("");
				addCourseFrame.setVisible(false);
				frame.setVisible(true);
			}
		});
		
		addCourseFrame.setSize(500,600);
		addCourseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
		addCourseFrame.setResizable(false); //user cannot drag and resize window
		addCourseFrame.setLocationRelativeTo(null); //window will appear in the center of the screen
		
//#1 -> ADD COURSE FRAME DONE 


		

		

		

		


//#2 -> ADD ASSIGNMENT FRAME
		
	//#2 -> CHOOSE TYPE OF AN ASSIGNMENT FRAME
	JFrame chooseAsTypeFrame = new JFrame("Choose assignment type");
	final String[] AsTypes = {"#1 Add new Assignment","#2 Add new Labwork","#3 Add new Project"};
	JList AsTypeMenu = new JList(AsTypes);
	AsTypeMenu.setFont(new Font("Arial", 12, 24));
	AsTypeMenu.setVisibleRowCount(3);
	AsTypeMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	AsTypeMenu.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected ="";
			if(e.getValueIsAdjusting())
			{
				JList source = (JList)e.getSource();
				selected = source.getSelectedValue().toString();
			}
			if(Objects.equals(selected, "#1 Add new Assignment"))
			{
				JFrame addAsFrame = new JFrame("Add Assignment");
				addAsFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addAsFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				addAsFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Assignment ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addAsFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				addAsFrame.add(enterAsIdTF);
				
				JLabel dueDateLabel = new JLabel("Due Date:");
				dueDateLabel.setBounds(10, 150, 200, 100);
				dueDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addAsFrame.add(dueDateLabel);
				
				JTextField enterdueDateTF = new JTextField("day/month/year");
				enterdueDateTF.setBounds(155, 185, 300, 30);
				addAsFrame.add(enterdueDateTF);
				
				JButton saveButton = new JButton("Save");
				saveButton.setBounds(400,500,80,50);
				addAsFrame.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {					
						String CName = enterCourseNameTF.getText();
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								isCourseValid=true;
								String asId = enterAsIdTF.getText();
								String dueDate = enterdueDateTF.getText();
								myCourse.get(i).addNewAssignment(CName, asId, dueDate);
								break;
							}
						}
						if(!isCourseValid)							
							JOptionPane.showMessageDialog(addAsFrame, "Coruse not Exist!", "Add assignment",JOptionPane.INFORMATION_MESSAGE);
						enterdueDateTF.setText("day/month/year");
						try {
							AsTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						addAsFrame.setVisible(false);
						frame.setVisible(true);
					}
				});
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(20,500,80,50);
				addAsFrame.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						try {
							AsTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						addAsFrame.setVisible(false);						
					}
				});
				
				addAsFrame.setSize(500,600);
				addAsFrame.setLocationRelativeTo(null);
				addAsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addAsFrame.setVisible(true);
							
				
				chooseAsTypeFrame.setVisible(false);
				addAsFrame.setVisible(true);
			}
			else if(Objects.equals(selected, "#2 Add new Labwork"))
			{
				JFrame addLabFrame = new JFrame("Add Labwork");
				addLabFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addLabFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				addLabFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Labwork ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addLabFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				addLabFrame.add(enterAsIdTF);
				
				JLabel dueDateLabel = new JLabel("Due Date:");
				dueDateLabel.setBounds(10, 150, 200, 100);
				dueDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addLabFrame.add(dueDateLabel);
				
				JTextField enterdueDateTF = new JTextField("day/month/year");
				enterdueDateTF.setBounds(155, 185, 300, 30);
				addLabFrame.add(enterdueDateTF);
				
				JButton saveButton = new JButton("Save");
				saveButton.setBounds(400,500,80,50);
				addLabFrame.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {					
						String CName = enterCourseNameTF.getText();
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								isCourseValid=true;
								String asId = enterAsIdTF.getText();
								String dueDate = enterdueDateTF.getText();
								myCourse.get(i).addNewLabwork(CName, asId, dueDate);
								break;
							}
						}
						if(!isCourseValid)							
							JOptionPane.showMessageDialog(addLabFrame, "Coruse not Exist!", "Add assignment",JOptionPane.INFORMATION_MESSAGE);
						enterdueDateTF.setText("day/month/year");
						try {
							AsTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}	
						addLabFrame.setVisible(false);
						frame.setVisible(true);
					}
				});
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(20,500,80,50);
				addLabFrame.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						try {
							AsTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						addLabFrame.setVisible(false);						
					}
				});
				
				addLabFrame.setSize(500,600);
				addLabFrame.setLocationRelativeTo(null);
				addLabFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addLabFrame.setVisible(true);
							
				
				chooseAsTypeFrame.setVisible(false);
				addLabFrame.setVisible(true);
			}
			else if(Objects.equals(selected, "#3 Add new Project"))
			{
				JFrame addProjectFrame = new JFrame("Add Project");
				addProjectFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addProjectFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				addProjectFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Project ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addProjectFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				addProjectFrame.add(enterAsIdTF);
				
				JLabel dueDateLabel = new JLabel("Due Date:");
				dueDateLabel.setBounds(10, 150, 200, 100);
				dueDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
				addProjectFrame.add(dueDateLabel);
				
				JTextField enterdueDateTF = new JTextField("day/month/year");
				enterdueDateTF.setBounds(155, 185, 300, 30);
				addProjectFrame.add(enterdueDateTF);
				
				JButton saveButton = new JButton("Save");
				saveButton.setBounds(400,500,80,50);
				addProjectFrame.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {					
						String CName = enterCourseNameTF.getText();
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								isCourseValid=true;
								String asId = enterAsIdTF.getText();
								String dueDate = enterdueDateTF.getText();
								myCourse.get(i).addNewProject(CName, asId, dueDate);
								break;
							}
						}
						if(!isCourseValid)							
							JOptionPane.showMessageDialog(addProjectFrame, "Coruse not Exist!", "Add assignment",JOptionPane.INFORMATION_MESSAGE);
						enterdueDateTF.setText("day/month/year");
						try {
							AsTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}								
						addProjectFrame.setVisible(false);
						frame.setVisible(true);
					}
				});
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(20,500,80,50);
				addProjectFrame.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						try {
							AsTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						addProjectFrame.setVisible(false);						
					}
				});
				
				addProjectFrame.setSize(500,600);
				addProjectFrame.setLocationRelativeTo(null);
				addProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				addProjectFrame.setVisible(true);
							
				
				chooseAsTypeFrame.setVisible(false);
				addProjectFrame.setVisible(true);
			}
				
		}
	});
	//#2 -> CHOOSE TYPE OF AN ASSIGNMENT FRAME DONE

	chooseAsTypeFrame.add(AsTypeMenu);
	chooseAsTypeFrame.setSize(500,600);
	chooseAsTypeFrame.setLocationRelativeTo(null);
	chooseAsTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//#2 -> ADD ASSIGNMENT FRAME DONE
		

	
	
	
	
	
	
	
//#3 -> SUBMIT ASSIGNMENT FRAME
	
	//#3 -> CHOOSE TYPE OF AN ASSIGNMENT FRAME
	JFrame chooseSubmitTypeFrame = new JFrame("Choose Submission type");
	final String[] SubTypes = {"#1 Submit new Assignment","#2 Submit new Labwork","#3 Submit new Project"};
	JList SubTypeMenu = new JList(SubTypes);
	SubTypeMenu.setFont(new Font("Arial", 12, 24));
	SubTypeMenu.setVisibleRowCount(3);
	SubTypeMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	SubTypeMenu.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected ="";
			if(e.getValueIsAdjusting())
			{
				JList source = (JList)e.getSource();
				selected = source.getSelectedValue().toString();
			}
			if(Objects.equals(selected, "#1 Submit new Assignment"))
			{
				JFrame submitAsFrame = new JFrame("Submit Assignment");
				submitAsFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 0, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitAsFrame.add(courseNameLabel);
				
				JTextField courseNameTF = new JTextField();
				courseNameTF.setBounds(155, 35, 300, 30);
				submitAsFrame.add(courseNameTF);
				
				
				JLabel assIdLabel = new JLabel("Assignment ID:");
				assIdLabel.setBounds(10, 50, 200, 100);
				assIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitAsFrame.add(assIdLabel);
				
				JTextField enterAssIdTF = new JTextField();
				enterAssIdTF.setBounds(155, 85, 300, 30);
				submitAsFrame.add(enterAssIdTF);
				
				JLabel stNameLabel = new JLabel("Student Name:");
				stNameLabel.setBounds(10, 100, 200, 100);
				stNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitAsFrame.add(stNameLabel);
				
				JTextField stNameTF = new JTextField();
				stNameTF.setBounds(155, 135, 300, 30);
				submitAsFrame.add(stNameTF);
				
				JLabel stIdLabel = new JLabel("Student ID:");
				stIdLabel.setBounds(10, 150, 200, 100);
				stIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitAsFrame.add(stIdLabel);
				
				JTextField stIdTF = new JTextField();
				stIdTF.setBounds(155, 185, 300, 30);
				submitAsFrame.add(stIdTF);
				
				JLabel subDateLabel = new JLabel("Sub Date:");
				subDateLabel.setBounds(10, 200, 200, 100);
				subDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitAsFrame.add(subDateLabel);
				
				JTextField subDateTF = new JTextField("day/month/year");
				subDateTF.setBounds(155, 235, 300, 30);
				submitAsFrame.add(subDateTF);
				
				
				JButton saveButton = new JButton("Save");
				saveButton.setBounds(400,500,80,50);
				submitAsFrame.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {					
						String CName = courseNameTF.getText();
						String stame = stNameTF.getText();
						String stid = stIdTF.getText();
						String assId = enterAssIdTF.getText();
						String subDate = subDateTF.getText();
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								isCourseValid=true;
								myCourse.get(i).addNewAssSubmission(CName, stame, stid, assId, subDate);
								break;								
							}
							
						}
						if(!isCourseValid)
							JOptionPane.showMessageDialog(submitAsFrame, "Coruse not Exist!", "",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(true);
						try {
							SubTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						submitAsFrame.setVisible(false);	
					}
				});
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(20,500,80,50);
				submitAsFrame.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						try {
							SubTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						submitAsFrame.setVisible(false);						
					}
				});
				submitAsFrame.setSize(500,600);
				submitAsFrame.setLocationRelativeTo(null);
				submitAsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				submitAsFrame.setVisible(true);
				chooseSubmitTypeFrame.setVisible(false);
				
			}
			else if(Objects.equals(selected, "#2 Submit new Labwork"))
			{
				JFrame submitLabFrame = new JFrame("Submit Labwork");
				submitLabFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 0, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitLabFrame.add(courseNameLabel);
				
				JTextField courseNameTF = new JTextField();
				courseNameTF.setBounds(155, 35, 300, 30);
				submitLabFrame.add(courseNameTF);
				
				
				JLabel assIdLabel = new JLabel("Labwork ID:");
				assIdLabel.setBounds(10, 50, 200, 100);
				assIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitLabFrame.add(assIdLabel);
				
				JTextField enterAssIdTF = new JTextField();
				enterAssIdTF.setBounds(155, 85, 300, 30);
				submitLabFrame.add(enterAssIdTF);
				
				JLabel stNameLabel = new JLabel("Student Name:");
				stNameLabel.setBounds(10, 100, 200, 100);
				stNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitLabFrame.add(stNameLabel);
				
				JTextField stNameTF = new JTextField();
				stNameTF.setBounds(155, 135, 300, 30);
				submitLabFrame.add(stNameTF);
				
				JLabel stIdLabel = new JLabel("Student ID:");
				stIdLabel.setBounds(10, 150, 200, 100);
				stIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitLabFrame.add(stIdLabel);
				
				JTextField stIdTF = new JTextField();
				stIdTF.setBounds(155, 185, 300, 30);
				submitLabFrame.add(stIdTF);
				
				JLabel subDateLabel = new JLabel("Sub Date:");
				subDateLabel.setBounds(10, 200, 200, 100);
				subDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitLabFrame.add(subDateLabel);
				
				JTextField subDateTF = new JTextField("day/month/year");
				subDateTF.setBounds(155, 235, 300, 30);
				submitLabFrame.add(subDateTF);
				
				
				JButton saveButton = new JButton("Save");
				saveButton.setBounds(400,500,80,50);
				submitLabFrame.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {					
						String CName = courseNameTF.getText();
						String stame = stNameTF.getText();
						String stid = stIdTF.getText();
						String assId = enterAssIdTF.getText();
						String subDate = subDateTF.getText();
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								isCourseValid=true;
								myCourse.get(i).addNewLabSubmission(CName, stame, stid, assId, subDate);
								break;								
							}
							
						}
						if(!isCourseValid)
							JOptionPane.showMessageDialog(submitLabFrame, "Coruse not Exist!", "",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(true);
						try {
							SubTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						submitLabFrame.setVisible(false);	
					}
				});
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(20,500,80,50);
				submitLabFrame.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						try {
							SubTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						submitLabFrame.setVisible(false);						
					}
				});
				submitLabFrame.setSize(500,600);
				submitLabFrame.setLocationRelativeTo(null);
				submitLabFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				submitLabFrame.setVisible(true);
				chooseSubmitTypeFrame.setVisible(false);
			}
			else if(Objects.equals(selected, "#3 Submit new Project"))
			{
				JFrame submitProjectFrame = new JFrame("Submit Project");
				submitProjectFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 0, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitProjectFrame.add(courseNameLabel);
				
				JTextField courseNameTF = new JTextField();
				courseNameTF.setBounds(155, 35, 300, 30);
				submitProjectFrame.add(courseNameTF);
				
				
				JLabel assIdLabel = new JLabel("Project ID:");
				assIdLabel.setBounds(10, 50, 200, 100);
				assIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitProjectFrame.add(assIdLabel);
				
				JTextField enterAssIdTF = new JTextField();
				enterAssIdTF.setBounds(155, 85, 300, 30);
				submitProjectFrame.add(enterAssIdTF);
				
				JLabel stNameLabel = new JLabel("Student Name:");
				stNameLabel.setBounds(10, 100, 200, 100);
				stNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitProjectFrame.add(stNameLabel);
				
				JTextField stNameTF = new JTextField();
				stNameTF.setBounds(155, 135, 300, 30);
				submitProjectFrame.add(stNameTF);
				
				JLabel stIdLabel = new JLabel("Student ID:");
				stIdLabel.setBounds(10, 150, 200, 100);
				stIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitProjectFrame.add(stIdLabel);
				
				JTextField stIdTF = new JTextField();
				stIdTF.setBounds(155, 185, 300, 30);
				submitProjectFrame.add(stIdTF);
				
				JLabel subDateLabel = new JLabel("Sub Date:");
				subDateLabel.setBounds(10, 200, 200, 100);
				subDateLabel.setFont(new Font("Arial", Font.BOLD, 20));
				submitProjectFrame.add(subDateLabel);
				
				JTextField subDateTF = new JTextField("day/month/year");
				subDateTF.setBounds(155, 235, 300, 30);
				submitProjectFrame.add(subDateTF);
				
				
				JButton saveButton = new JButton("Save");
				saveButton.setBounds(400,500,80,50);
				submitProjectFrame.add(saveButton);
				saveButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {					
						String CName = courseNameTF.getText();
						String stame = stNameTF.getText();
						String stid = stIdTF.getText();
						String assId = enterAssIdTF.getText();
						String subDate = subDateTF.getText();
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								isCourseValid=true;
								myCourse.get(i).addNewProjectSubmission(CName, stame, stid, assId, subDate);
								break;								
							}
							
						}
						if(!isCourseValid)
							JOptionPane.showMessageDialog(submitProjectFrame, "Coruse not Exist!", "",JOptionPane.INFORMATION_MESSAGE);
						frame.setVisible(true);
						try {
							SubTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						submitProjectFrame.setVisible(false);	
					}
				});
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(20,500,80,50);
				submitProjectFrame.add(cancelButton);
				cancelButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						try {
							SubTypeMenu.clearSelection();
						} catch (Exception e2) {
							// TODO: handle exception
						}							
						submitProjectFrame.setVisible(false);						
					}
				});
				submitProjectFrame.setSize(500,600);
				submitProjectFrame.setLocationRelativeTo(null);
				submitProjectFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				submitProjectFrame.setVisible(true);
				chooseSubmitTypeFrame.setVisible(false);
			}
			
			
		}
	});

	//#3 -> CHOOSE TYPE OF AN ASSIGNMENT FRAME DONE
	chooseSubmitTypeFrame.add(SubTypeMenu);
	chooseSubmitTypeFrame.setSize(500,600);
	chooseSubmitTypeFrame.setLocationRelativeTo(null);
	chooseSubmitTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//#3 -> SUBMIT ASSIGNMENT FRAME DONE
	
	
	
	

	
	
	
	
	
//#4 -> GRADE ASSIGNMENTS FRAME
	//#4 -> CHOOSE ASSIGNMENT TYPE
	JFrame chooseWorkTypeFrame = new JFrame("Choose Work type");
	final String[] workType = {"#1 Grade Assignment","#2 Grade Labwork","#3 Grade Project"};
	JList workTypeMenu = new JList(workType);
	workTypeMenu.setFont(new Font("Arial", 12, 24));
	workTypeMenu.setVisibleRowCount(3);
	workTypeMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	workTypeMenu.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected ="";
			if(e.getValueIsAdjusting())
			{
				JList source = (JList)e.getSource();
				selected = source.getSelectedValue().toString();
			}
			if(Objects.equals(selected, "#1 Grade Assignment"))
			{
				JFrame assGradeFrame = new JFrame("Grade Assignment");
				assGradeFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				assGradeFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				assGradeFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Assignment ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				assGradeFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				assGradeFrame.add(enterAsIdTF);
							
				
				JButton okButton = new JButton("OK");
				okButton.setBounds(400,500,80,50);
				assGradeFrame.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String CName = enterCourseNameTF.getText();
						String assId = enterAsIdTF.getText();
										
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								//myCourse.get(i).gradeSubmissions();
								isCourseValid=true;
								
								ArrayList<Submission> mySubmissions = myCourse.get(i).getMySubmissions();
								for(int j=0;j<mySubmissions.size();j++)
								{
									if(Objects.equals(CName, mySubmissions.get(j).getCourseName()) && Objects.equals(assId, mySubmissions.get(j).getAssignmentId()) && Objects.equals(mySubmissions.get(j).getSubmissionType(), "assignment")) //if submission's CName and assId is valid
									{
										//isAssignmentExist=true;
										int grade;
										
											String GRADE = JOptionPane.showInputDialog("Enter grade for: "+ mySubmissions.get(j).getstudentName()+" ID: "+ mySubmissions.get(j).getStudentId());
											grade = Integer.valueOf(GRADE);
											mySubmissions.get(j).setGrade(grade); //take grade as an input
										
									}
								}
		  					}
						}
						if(!isCourseValid)
							System.out.println("Course not exist!");
					}
				});
				JButton backButton = new JButton("Back");
				backButton.setBounds(10,500,80,50);
				assGradeFrame.add(backButton);
				backButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						assGradeFrame.setVisible(false);
						
					}
				});
				
				assGradeFrame.setSize(500,600);
				assGradeFrame.setLocationRelativeTo(null);
				assGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				assGradeFrame.setVisible(true);
				chooseWorkTypeFrame.setVisible(false);
				
			}
			else if(Objects.equals(selected, "#2 Grade Labwork"))
			{
				JFrame LabGradeFrame = new JFrame("Grade Labworks");
				LabGradeFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				LabGradeFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				LabGradeFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Labwork ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				LabGradeFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				LabGradeFrame.add(enterAsIdTF);
							
				
				JButton okButton = new JButton("OK");
				okButton.setBounds(400,500,80,50);
				LabGradeFrame.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String CName = enterCourseNameTF.getText();
						String assId = enterAsIdTF.getText();
										
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								//myCourse.get(i).gradeSubmissions();
								isCourseValid=true;
								
								ArrayList<Submission> mySubmissions = myCourse.get(i).getMySubmissions();
								for(int j=0;j<mySubmissions.size();j++)
								{
									if(Objects.equals(CName, mySubmissions.get(j).getCourseName()) && Objects.equals(assId, mySubmissions.get(j).getAssignmentId()) && Objects.equals(mySubmissions.get(j).getSubmissionType(), "labwork")) //if submission's CName and assId is valid
									{
										//isAssignmentExist=true;
										int grade;
										
											String GRADE = JOptionPane.showInputDialog("Enter grade for: "+ mySubmissions.get(j).getstudentName()+" ID: "+ mySubmissions.get(j).getStudentId());
											grade = Integer.valueOf(GRADE);
											mySubmissions.get(j).setGrade(grade); //take grade as an input
										
									}
								}
		  					}
						}
						if(!isCourseValid)
							System.out.println("Course not exist!");
					}
				});
				JButton backButton = new JButton("Back");
				backButton.setBounds(10,500,80,50);
				LabGradeFrame.add(backButton);
				backButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						LabGradeFrame.setVisible(false);
						
					}
				});
				
				LabGradeFrame.setSize(500,600);
				LabGradeFrame.setLocationRelativeTo(null);
				LabGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				LabGradeFrame.setVisible(true);
				chooseWorkTypeFrame.setVisible(false);
			}
			else if(Objects.equals(selected, "#3 Grade Project"))
			{
				JFrame ProjectGradeFrame = new JFrame("Grade Projects");
				ProjectGradeFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				ProjectGradeFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				ProjectGradeFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Project ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				ProjectGradeFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				ProjectGradeFrame.add(enterAsIdTF);
							
				
				JButton okButton = new JButton("OK");
				okButton.setBounds(400,500,80,50);
				ProjectGradeFrame.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String CName = enterCourseNameTF.getText();
						String assId = enterAsIdTF.getText();
										
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								//myCourse.get(i).gradeSubmissions();
								isCourseValid=true;
								
								ArrayList<Submission> mySubmissions = myCourse.get(i).getMySubmissions();
								for(int j=0;j<mySubmissions.size();j++)
								{
									if(Objects.equals(CName, mySubmissions.get(j).getCourseName()) && Objects.equals(assId, mySubmissions.get(j).getAssignmentId()) && Objects.equals(mySubmissions.get(j).getSubmissionType(), "project")) //if submission's CName and assId is valid
									{
										//isAssignmentExist=true;
										int grade;
										
											String GRADE = JOptionPane.showInputDialog("Enter grade for: "+ mySubmissions.get(j).getstudentName()+" ID: "+ mySubmissions.get(j).getStudentId());
											grade = Integer.valueOf(GRADE);
											mySubmissions.get(j).setGrade(grade); //take grade as an input
										
									}
								}
		  					}
						}
						if(!isCourseValid)
							System.out.println("Course not exist!");
					}
				});
				JButton backButton = new JButton("Back");
				backButton.setBounds(10,500,80,50);
				ProjectGradeFrame.add(backButton);
				backButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						ProjectGradeFrame.setVisible(false);
						
					}
				});
				
				ProjectGradeFrame.setSize(500,600);
				ProjectGradeFrame.setLocationRelativeTo(null);
				ProjectGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ProjectGradeFrame.setVisible(true);
				chooseWorkTypeFrame.setVisible(false);
			}			
		}
	});
	//#4 -> CHOOSE ASSIGNMENT TYPE DONE
	chooseWorkTypeFrame.add(workTypeMenu);
	chooseWorkTypeFrame.setSize(500,600);
	chooseWorkTypeFrame.setLocationRelativeTo(null);
	chooseWorkTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//#4 -> GRADE ASSIGNMENTS FRAME DONE	
	
	

	
	
	
	
	
//#5 -> DISPLAY GRADES
	//#5 -> CHOOSE ASSIGNMENT TYPE
	JFrame chooseWorkTypeFrame2 = new JFrame("Choose Work type");
	final String[] workType2 = {"#1 Assignment Grades","#2 Labwork Grades","#3 Project Grades"};
	JList workTypeMenu2 = new JList(workType2);
	workTypeMenu2.setFont(new Font("Arial", 12, 24));
	workTypeMenu2.setVisibleRowCount(3);
	workTypeMenu2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
	workTypeMenu2.addListSelectionListener(new ListSelectionListener() {
		
		@Override
		public void valueChanged(ListSelectionEvent e) {
			String selected ="";
			if(e.getValueIsAdjusting())
			{
				JList source = (JList)e.getSource();
				selected = source.getSelectedValue().toString();
			}
			if(Objects.equals(selected, "#1 Assignment Grades"))
			{
				JFrame assGradeFrame = new JFrame("Grade Assignment");
				assGradeFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				assGradeFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				assGradeFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Assignment ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				assGradeFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				assGradeFrame.add(enterAsIdTF);
							
				
				JButton okButton = new JButton("OK");
				okButton.setBounds(400,500,80,50);
				assGradeFrame.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String CName = enterCourseNameTF.getText();
						String assId = enterAsIdTF.getText();
										
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								//myCourse.get(i).gradeSubmissions();
								isCourseValid=true;
								
								ArrayList<Submission> mySubmissions = myCourse.get(i).getMySubmissions();
								for(int j=0;j<mySubmissions.size();j++)
								{
									if(Objects.equals(CName, mySubmissions.get(j).getCourseName()) && Objects.equals(assId, mySubmissions.get(j).getAssignmentId()) && Objects.equals(mySubmissions.get(j).getSubmissionType(), "assignment")) //if submission's CName and assId is valid
									{
										JOptionPane.showMessageDialog(assGradeFrame,mySubmissions.get(j).getstudentName()+", ID: "+ mySubmissions.get(j).getStudentId() + " Grade: " +mySubmissions.get(j).getGrade());										
									}
								}
		  					}
						}
						if(!isCourseValid)
							System.out.println("Course not exist!");
					}
				});
				JButton backButton = new JButton("Back");
				backButton.setBounds(10,500,80,50);
				assGradeFrame.add(backButton);
				backButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						assGradeFrame.setVisible(false);
						
					}
				});
				assGradeFrame.setSize(500,600);
				assGradeFrame.setLocationRelativeTo(null);
				assGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				assGradeFrame.setVisible(true);
				chooseWorkTypeFrame2.setVisible(false);
				
			}
			else if(Objects.equals(selected, "#2 Labwork Grades"))
			{
				JFrame LabGradeFrame = new JFrame("Grade Labwork");
				LabGradeFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				LabGradeFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				LabGradeFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Labwork ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				LabGradeFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				LabGradeFrame.add(enterAsIdTF);
							
				
				JButton okButton = new JButton("OK");
				okButton.setBounds(400,500,80,50);
				LabGradeFrame.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String CName = enterCourseNameTF.getText();
						String assId = enterAsIdTF.getText();
										
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								//myCourse.get(i).gradeSubmissions();
								isCourseValid=true;
								
								ArrayList<Submission> mySubmissions = myCourse.get(i).getMySubmissions();
								for(int j=0;j<mySubmissions.size();j++)
								{
									if(Objects.equals(CName, mySubmissions.get(j).getCourseName()) && Objects.equals(assId, mySubmissions.get(j).getAssignmentId()) && Objects.equals(mySubmissions.get(j).getSubmissionType(), "labwork")) //if submission's CName and assId is valid
									{
										JOptionPane.showMessageDialog(LabGradeFrame,mySubmissions.get(j).getstudentName()+", ID: "+ mySubmissions.get(j).getStudentId() + " Grade: " +mySubmissions.get(j).getGrade());										
									}
								}
		  					}
						}
						if(!isCourseValid)
							System.out.println("Course not exist!");
					}
				});
				JButton backButton = new JButton("Back");
				backButton.setBounds(10,500,80,50);
				LabGradeFrame.add(backButton);
				backButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						LabGradeFrame.setVisible(false);
						
					}
				});
				LabGradeFrame.setSize(500,600);
				LabGradeFrame.setLocationRelativeTo(null);
				LabGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				LabGradeFrame.setVisible(true);
				chooseWorkTypeFrame2.setVisible(false);
			}
			else if(Objects.equals(selected, "#3 Project Grades"))
			{
				JFrame ProjectGradeFrame = new JFrame("Grade Project");
				ProjectGradeFrame.setLayout(null);
				
				JLabel courseNameLabel = new JLabel("Course Name:");
				courseNameLabel.setBounds(10, 50, 200, 100);
				courseNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
				ProjectGradeFrame.add(courseNameLabel);
				
				JTextField enterCourseNameTF = new JTextField();
				enterCourseNameTF.setBounds(155, 85, 300, 30);
				ProjectGradeFrame.add(enterCourseNameTF);
				
				JLabel asIdLabel = new JLabel("Project ID:");
				asIdLabel.setBounds(10, 100, 200, 100);
				asIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
				ProjectGradeFrame.add(asIdLabel);
				
				JTextField enterAsIdTF = new JTextField();
				enterAsIdTF.setBounds(155, 135, 300, 30);
				ProjectGradeFrame.add(enterAsIdTF);
							
				
				JButton okButton = new JButton("OK");
				okButton.setBounds(400,500,80,50);
				ProjectGradeFrame.add(okButton);
				okButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						String CName = enterCourseNameTF.getText();
						String assId = enterAsIdTF.getText();
										
						boolean isCourseValid=false;
						for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
						{
							if(Objects.equals(CName, myCourse.get(i).getCourseName()))
							{
								//myCourse.get(i).gradeSubmissions();
								isCourseValid=true;
								
								ArrayList<Submission> mySubmissions = myCourse.get(i).getMySubmissions();
								for(int j=0;j<mySubmissions.size();j++)
								{
									if(Objects.equals(CName, mySubmissions.get(j).getCourseName()) && Objects.equals(assId, mySubmissions.get(j).getAssignmentId()) && Objects.equals(mySubmissions.get(j).getSubmissionType(), "project")) //if submission's CName and assId is valid
									{
										JOptionPane.showMessageDialog(ProjectGradeFrame,mySubmissions.get(j).getstudentName()+", ID: "+ mySubmissions.get(j).getStudentId() + " Grade: " +mySubmissions.get(j).getGrade());										
									}
								}
		  					}
						}
						if(!isCourseValid)
							System.out.println("Course not exist!");
					}
				});
				JButton backButton = new JButton("Back");
				backButton.setBounds(10,500,80,50);
				ProjectGradeFrame.add(backButton);
				backButton.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						frame.setVisible(true);
						ProjectGradeFrame.setVisible(false);
						
					}
				});
				ProjectGradeFrame.setSize(500,600);
				ProjectGradeFrame.setLocationRelativeTo(null);
				ProjectGradeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				ProjectGradeFrame.setVisible(true);
				chooseWorkTypeFrame2.setVisible(false);
			}
		}
		
	});
	//#5 -> CHOOSE ASSIGNMENT TYPE DONE
	chooseWorkTypeFrame2.add(workTypeMenu2);
	chooseWorkTypeFrame2.setSize(500,600);
	chooseWorkTypeFrame2.setLocationRelativeTo(null);
	chooseWorkTypeFrame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//#5 -> DISPLAY GRADES

	

	
	

	
//#DISPLAY AVERAGE GRADES
	JFrame displayAvgFrame = new JFrame();
	displayAvgFrame.setLayout(null);

	JLabel cNameLabel = new JLabel("Course Name:");
	cNameLabel.setBounds(10, 10, 200, 100);
	cNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
	displayAvgFrame.add(cNameLabel);
	
	JTextField entercNameTF7 = new JTextField();
	entercNameTF7.setBounds(150, 45, 300, 30);
	displayAvgFrame.add(entercNameTF7);
	
	JButton displaybutton = new JButton("Display");
	displaybutton.setBounds(400,500,80,50);
	displayAvgFrame.add(displaybutton);
	displaybutton.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e)
		{
			JFrame actualDisplayFrame = new JFrame();
			String CName = entercNameTF7.getText();
			boolean isCourseValid=false;
			for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
			{
				if(Objects.equals(CName, myCourse.get(i).getCourseName()))
				{
					String result = myCourse.get(i).avgGrade();
					JTextArea text = new JTextArea();
					text.setText(result);
					text.setBounds(0, 0, 500, 600);
					actualDisplayFrame.add(text);
					isCourseValid=true;
					break;
				}
				
			}
			if(!isCourseValid)
				JOptionPane.showMessageDialog(displayAvgFrame, "course not found");
			
			actualDisplayFrame.setSize(500,600);
			actualDisplayFrame.setLocationRelativeTo(null);
			actualDisplayFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			actualDisplayFrame.setVisible(true);
			displayAvgFrame.setVisible(false);
			
		}
	});
	displayAvgFrame.setSize(500,600);
	displayAvgFrame.setLocationRelativeTo(null);
	displayAvgFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
//DISPLAY AVERAGE GRADES DONE
	

	
	
	
	
	
	
	
	
//#7 -> SCHEDULE COURSES FRAME
	JFrame scheduleCoursesFrame = new JFrame("Schedule Course");
	scheduleCoursesFrame.setLayout(null);

	JLabel courseNameLabel7 = new JLabel("Course Name:");
	courseNameLabel7.setBounds(10, 10, 200, 100);
	courseNameLabel7.setFont(new Font("Arial", Font.BOLD, 20));
	scheduleCoursesFrame.add(courseNameLabel7);
	
	JTextField enterCourseNameTF7 = new JTextField();
	enterCourseNameTF7.setBounds(150, 45, 300, 30);
	scheduleCoursesFrame.add(enterCourseNameTF7);
	
	
	JLabel enterDayLabel = new JLabel("Enter Day:");
	enterDayLabel.setBounds(10, 110, 200, 100);
	enterDayLabel.setFont(new Font("Arial", Font.BOLD, 20));
	scheduleCoursesFrame.add(enterDayLabel);
	
	JTextField enterDayTF = new JTextField();
	enterDayTF.setBounds(145, 145, 300, 30);
	scheduleCoursesFrame.add(enterDayTF);
	
	JLabel enterHourLabel = new JLabel("Enter Hour:");
	enterHourLabel.setBounds(10, 210, 200, 100);
	enterHourLabel.setFont(new Font("Arial", Font.BOLD, 20));
	scheduleCoursesFrame.add(enterHourLabel);
	
	JTextField enterHourTF = new JTextField();
	enterHourTF.setBounds(145, 245, 300, 30);
	scheduleCoursesFrame.add(enterHourTF);
	
	JLabel enterMinuteLabel = new JLabel("Enter Minute:");
	enterMinuteLabel.setBounds(10, 310, 200, 100);
	enterMinuteLabel.setFont(new Font("Arial", Font.BOLD, 20));
	scheduleCoursesFrame.add(enterMinuteLabel);
	
	JTextField enterMinuteTF = new JTextField();
	enterMinuteTF.setBounds(145, 345, 300, 30);
	scheduleCoursesFrame.add(enterMinuteTF);
	
	JLabel enterSecondLabel = new JLabel("Enter Second:");
	enterSecondLabel.setBounds(10, 410, 200, 100);
	enterSecondLabel.setFont(new Font("Arial", Font.BOLD, 20));
	scheduleCoursesFrame.add(enterSecondLabel);
	
	JTextField enterSecondTF = new JTextField();
	enterSecondTF.setBounds(145, 445, 300, 30);
	scheduleCoursesFrame.add(enterSecondTF);
	
	JButton saveButton7 = new JButton("Save");
	saveButton7.setBounds(400,500,80,50);
	scheduleCoursesFrame.add(saveButton7);
	saveButton7.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) 
		{
			String CName = enterCourseNameTF7.getText();
			String day = enterDayTF.getText();
			
			int hour = Integer.valueOf(enterHourTF.getText());
			int minute = Integer.valueOf(enterMinuteTF.getText());
			int second = Integer.valueOf(enterSecondTF.getText());
			
			boolean isCourseValid=false;
			for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
			{
				if(Objects.equals(CName, myCourse.get(i).getCourseName()))
				{
					DayTime time = new DayTime(day, hour, minute, second);
					myCourse.get(i).setDayTime(time);;
					isCourseValid=true;
					frame.setVisible(true);
					scheduleCoursesFrame.setVisible(false);
					enterCourseNameTF7.setText("");
					enterDayTF.setText("");
					enterMinuteTF.setText("");
					enterSecondTF.setText("");	
					System.out.println(myCourse);
				}
				
			}
			if(!isCourseValid)
				JOptionPane.showMessageDialog(scheduleCoursesFrame, "Coruse not Exist!", "schedule course",JOptionPane.INFORMATION_MESSAGE);			
		}
	});
	
	JButton cancelButton7 = new JButton("Cancel");
	cancelButton7.setBounds(20,500,80,50);
	scheduleCoursesFrame.add(cancelButton7);
	cancelButton7.addActionListener(new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			scheduleCoursesFrame.setVisible(false);
			frame.setVisible(true);
		}
	});
	
	scheduleCoursesFrame.setSize(500,600);
	scheduleCoursesFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
	scheduleCoursesFrame.setResizable(false); //user cannot drag and resize window
	scheduleCoursesFrame.setLocationRelativeTo(null); //window will appear in the center of the screen
	
//#7 -> SCHEDULE COURSES FRAME DONE
	

	
	
	
	

	
//#8 -> LECTURER/STUDENT REGISTRATION
	//#8 -> CHOOSE TYPE OF AN REGISTRATION FRAME
		JFrame chooseRegTypeFrame = new JFrame("Choose Registraiton type");
		final String[] regTypes = {"#1 add new Student","#2 add new Lecturer"};
		JList regTypeMenu = new JList(regTypes);
		regTypeMenu.setFont(new Font("Arial", 12, 24));
		regTypeMenu.setVisibleRowCount(3);
		regTypeMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		regTypeMenu.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selected ="";
				if(e.getValueIsAdjusting())
				{
					JList source = (JList)e.getSource();
					selected = source.getSelectedValue().toString();
				}
				if(Objects.equals(selected, "#1 add new Student"))
				{
					try {
						regTypeMenu.clearSelection();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					JFrame chooseStudentTypeFrame = new JFrame("Choose Student type");
					final String[] studentTypes = {"#1 add new PostGraduate Student","#2 add new UnderGraduate Student"};
					JList studentTypeMenu = new JList(studentTypes);
					studentTypeMenu.setFont(new Font("Arial", 12, 24));
					studentTypeMenu.setVisibleRowCount(3);
					studentTypeMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					studentTypeMenu.addListSelectionListener(new ListSelectionListener() {
						
						@Override
						public void valueChanged(ListSelectionEvent e) {
							String selected ="";
							if(e.getValueIsAdjusting())
							{
								JList source = (JList)e.getSource();
								selected = source.getSelectedValue().toString();
							}
							if(Objects.equals(selected, "#1 add new PostGraduate Student"))
							{
								JFrame regPostGradFrame = new JFrame("Register Postgraduate student");
								regPostGradFrame.setLayout(null);

								JLabel enterNameLabel = new JLabel("Enter Name:");
								enterNameLabel.setBounds(10, 10, 200, 100);
								enterNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
								regPostGradFrame.add(enterNameLabel);
								
								JTextField enterNameTF = new JTextField();
								enterNameTF.setBounds(150, 45, 300, 30);
								regPostGradFrame.add(enterNameTF);
								
								
								JLabel enterIdLabel = new JLabel("Enter Id:");
								enterIdLabel.setBounds(10, 110, 200, 100);
								enterIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
								regPostGradFrame.add(enterIdLabel);
								
								JTextField enterIdTF = new JTextField();
								enterIdTF.setBounds(145, 145, 300, 30);
								regPostGradFrame.add(enterIdTF);
								

								JButton saveButton = new JButton("Save");
								saveButton.setBounds(400,500,80,50);
								regPostGradFrame.add(saveButton);
								saveButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) 
									{
										String name = enterNameTF.getText();
										Long id=0L;
										try {
											id = Long.valueOf(enterIdTF.getText());
										} catch (NumberFormatException e2) {
											JOptionPane.showMessageDialog(regPostGradFrame, "Enter valid id", "",JOptionPane.INFORMATION_MESSAGE);
										}										boolean isAdded = false;
										if(!myCourse.isEmpty())
										{
											isAdded = myCourse.get(0).addNewPostGradStudent(name,id);
											if(!isAdded)
												JOptionPane.showMessageDialog(regPostGradFrame, "Error!", "",JOptionPane.INFORMATION_MESSAGE);
											regPostGradFrame.setVisible(false);
											frame.setVisible(true);
											
										}
										else {
											JOptionPane.showMessageDialog(regPostGradFrame, "You should create course first!", "",JOptionPane.INFORMATION_MESSAGE);
										}
										
									}
								});
								
								JButton cancelButton = new JButton("Cancel");
								cancelButton.setBounds(20,500,80,50);
								regPostGradFrame.add(cancelButton);
								cancelButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										regPostGradFrame.setVisible(false);
										frame.setVisible(true);
									}
								});
								
								regPostGradFrame.setSize(500,600);
								regPostGradFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
								regPostGradFrame.setResizable(false); //user cannot drag and resize window
								regPostGradFrame.setLocationRelativeTo(null); //window will appear in the center of the screen
								
								regPostGradFrame.setVisible(true);
								chooseStudentTypeFrame.setVisible(false);
							}
							else if (Objects.equals(selected, "#2 add new UnderGraduate Student") )
							{
								JFrame regUnderGradFrame = new JFrame("Register Undergraduate student");
								regUnderGradFrame.setLayout(null);

								JLabel enterNameLabel = new JLabel("Enter Name:");
								enterNameLabel.setBounds(10, 10, 200, 100);
								enterNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
								regUnderGradFrame.add(enterNameLabel);
								
								JTextField enterNameTF = new JTextField();
								enterNameTF.setBounds(150, 45, 300, 30);
								regUnderGradFrame.add(enterNameTF);
								
								
								JLabel enterIdLabel = new JLabel("Enter Id:");
								enterIdLabel.setBounds(10, 110, 200, 100);
								enterIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
								regUnderGradFrame.add(enterIdLabel);
								
								JTextField enterIdTF = new JTextField();
								enterIdTF.setBounds(145, 145, 300, 30);
								regUnderGradFrame.add(enterIdTF);
								

								JButton saveButton = new JButton("Save");
								saveButton.setBounds(400,500,80,50);
								regUnderGradFrame.add(saveButton);
								saveButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) 
									{
										String name = enterNameTF.getText();
										Long id=0L;
										try {
											id = Long.valueOf(enterIdTF.getText());
										} catch (NumberFormatException e2) {
											JOptionPane.showMessageDialog(regUnderGradFrame, "Enter valid id", "",JOptionPane.INFORMATION_MESSAGE);
										}
										boolean isAdded = false;
										if(!myCourse.isEmpty())
										{
											isAdded = myCourse.get(0).addNewUnderGradStudent(name,id);
											if(!isAdded)
												JOptionPane.showMessageDialog(regUnderGradFrame, "Error!", "",JOptionPane.INFORMATION_MESSAGE);
											regUnderGradFrame.setVisible(false);
											frame.setVisible(true);
											
										}
										else {
											JOptionPane.showMessageDialog(regUnderGradFrame, "You should create course first!", "",JOptionPane.INFORMATION_MESSAGE);
										}
										
									}
								});
								
								JButton cancelButton = new JButton("Cancel");
								cancelButton.setBounds(20,500,80,50);
								regUnderGradFrame.add(cancelButton);
								cancelButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										regUnderGradFrame.setVisible(false);
										frame.setVisible(true);
									}
								});
								
								regUnderGradFrame.setSize(500,600);
								regUnderGradFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
								regUnderGradFrame.setResizable(false); //user cannot drag and resize window
								regUnderGradFrame.setLocationRelativeTo(null); //window will appear in the center of the screen
								
								regUnderGradFrame.setVisible(true);
								chooseStudentTypeFrame.setVisible(false);
							}
							
						}
					});

					chooseStudentTypeFrame.add(studentTypeMenu);
					chooseStudentTypeFrame.setSize(500,600);
					chooseStudentTypeFrame.setLocationRelativeTo(null);
					chooseStudentTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
					
					frame.setVisible(false);
					chooseRegTypeFrame.setVisible(false);
					chooseStudentTypeFrame.setVisible(true);

				}
				else if (Objects.equals(selected, "#2 add new Lecturer")) 
				{
					try {
						regTypeMenu.clearSelection();
					} catch (Exception e2) {
						// TODO: handle exception
					}
					JFrame chooseLecturerTypeFrame = new JFrame("Choose Lecturer type");
					final String[] LecturerTypes = {"#1 add new PartTime Lecturer","#2 add new FullTime Lecturer"};
					JList LecturerTypeMenu = new JList(LecturerTypes);
					LecturerTypeMenu.setFont(new Font("Arial", 12, 24));
					LecturerTypeMenu.setVisibleRowCount(3);
					LecturerTypeMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					LecturerTypeMenu.addListSelectionListener(new ListSelectionListener() {
						
						@Override
						public void valueChanged(ListSelectionEvent e) {
							String selected ="";
							if(e.getValueIsAdjusting())
							{
								JList source = (JList)e.getSource();
								selected = source.getSelectedValue().toString();
							}
							if(Objects.equals(selected, "#1 add new PartTime Lecturer"))
							{
								JFrame regPartTimeLecturerFrame = new JFrame("Register PartTime Lecturer");
								regPartTimeLecturerFrame.setLayout(null);

								JLabel enterNameLabel = new JLabel("Enter Name:");
								enterNameLabel.setBounds(10, 10, 200, 100);
								enterNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
								regPartTimeLecturerFrame.add(enterNameLabel);
								
								JTextField enterNameTF = new JTextField();
								enterNameTF.setBounds(150, 45, 300, 30);
								regPartTimeLecturerFrame.add(enterNameTF);
								
								
								JLabel enterIdLabel = new JLabel("Enter Id:");
								enterIdLabel.setBounds(10, 110, 200, 100);
								enterIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
								regPartTimeLecturerFrame.add(enterIdLabel);
								
								JTextField enterIdTF = new JTextField();
								enterIdTF.setBounds(145, 145, 300, 30);
								regPartTimeLecturerFrame.add(enterIdTF);
								

								JButton saveButton = new JButton("Save");
								saveButton.setBounds(400,500,80,50);
								regPartTimeLecturerFrame.add(saveButton);
								saveButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) 
									{
										String name = enterNameTF.getText();
										Long id=0L;
										try {
											id = Long.valueOf(enterIdTF.getText());
										} catch (NumberFormatException e2) {
											JOptionPane.showMessageDialog(regPartTimeLecturerFrame, "Enter valid id", "",JOptionPane.INFORMATION_MESSAGE);
										}
										boolean isAdded = false;
										if(!myCourse.isEmpty())
										{
											isAdded = myCourse.get(0).addNewPartTimeLecturer(name,id);
											if(!isAdded)
												JOptionPane.showMessageDialog(regPartTimeLecturerFrame, "Error!", "",JOptionPane.INFORMATION_MESSAGE);
											regPartTimeLecturerFrame.setVisible(false);
											frame.setVisible(true);
											
										}
										else {
											JOptionPane.showMessageDialog(regPartTimeLecturerFrame, "You should create course first!", "",JOptionPane.INFORMATION_MESSAGE);
										}
										
									}
								});
								
								JButton cancelButton = new JButton("Cancel");
								cancelButton.setBounds(20,500,80,50);
								regPartTimeLecturerFrame.add(cancelButton);
								cancelButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										regPartTimeLecturerFrame.setVisible(false);
										frame.setVisible(true);
									}
								});
								
								regPartTimeLecturerFrame.setSize(500,600);
								regPartTimeLecturerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
								regPartTimeLecturerFrame.setResizable(false); //user cannot drag and resize window
								regPartTimeLecturerFrame.setLocationRelativeTo(null); //window will appear in the center of the screen
								
								regPartTimeLecturerFrame.setVisible(true);
								chooseLecturerTypeFrame.setVisible(false);
							}
							else if (Objects.equals(selected, "#2 add new FullTime Lecturer") )
							{
								JFrame regFullTimeLecturerFrame = new JFrame("Register Fulltime Lecturer");
								regFullTimeLecturerFrame.setLayout(null);

								JLabel enterNameLabel = new JLabel("Enter Name:");
								enterNameLabel.setBounds(10, 10, 200, 100);
								enterNameLabel.setFont(new Font("Arial", Font.BOLD, 20));
								regFullTimeLecturerFrame.add(enterNameLabel);
								
								JTextField enterNameTF = new JTextField();
								enterNameTF.setBounds(150, 45, 300, 30);
								regFullTimeLecturerFrame.add(enterNameTF);
								
								
								JLabel enterIdLabel = new JLabel("Enter Id:");
								enterIdLabel.setBounds(10, 110, 200, 100);
								enterIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
								regFullTimeLecturerFrame.add(enterIdLabel);
								
								JTextField enterIdTF = new JTextField();
								enterIdTF.setBounds(145, 145, 300, 30);
								regFullTimeLecturerFrame.add(enterIdTF);
								

								JButton saveButton = new JButton("Save");
								saveButton.setBounds(400,500,80,50);
								regFullTimeLecturerFrame.add(saveButton);
								saveButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) 
									{
										String name = enterNameTF.getText();
										Long id=0L;
										try {
											id = Long.valueOf(enterIdTF.getText());
										} catch (NumberFormatException e2) {
											JOptionPane.showMessageDialog(regFullTimeLecturerFrame, "Enter valid id", "",JOptionPane.INFORMATION_MESSAGE);
										}
										boolean isAdded = false;
										if(!myCourse.isEmpty())
										{
											isAdded = myCourse.get(0).addNewFullTimeLecturer(name,id);
											if(!isAdded)
												JOptionPane.showMessageDialog(regFullTimeLecturerFrame, "Error!", "",JOptionPane.INFORMATION_MESSAGE);
											regFullTimeLecturerFrame.setVisible(false);
											frame.setVisible(true);
											
										}
										else {
											JOptionPane.showMessageDialog(regFullTimeLecturerFrame, "You should create course first!", "",JOptionPane.INFORMATION_MESSAGE);
										}
										
									}
								});
								
								JButton cancelButton = new JButton("Cancel");
								cancelButton.setBounds(20,500,80,50);
								regFullTimeLecturerFrame.add(cancelButton);
								cancelButton.addActionListener(new ActionListener() {
									
									@Override
									public void actionPerformed(ActionEvent e) {
										regFullTimeLecturerFrame.setVisible(false);
										frame.setVisible(true);
									}
								});
								
								regFullTimeLecturerFrame.setSize(500,600);
								regFullTimeLecturerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
								regFullTimeLecturerFrame.setResizable(false); //user cannot drag and resize window
								regFullTimeLecturerFrame.setLocationRelativeTo(null); //window will appear in the center of the screen
								
								regFullTimeLecturerFrame.setVisible(true);
								chooseLecturerTypeFrame.setVisible(false);
							}
							
						}
					});

					chooseLecturerTypeFrame.add(LecturerTypeMenu);
					chooseLecturerTypeFrame.setSize(500,600);
					chooseLecturerTypeFrame.setLocationRelativeTo(null);
					chooseLecturerTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
					
					frame.setVisible(false);
					chooseRegTypeFrame.setVisible(false);
					chooseLecturerTypeFrame.setVisible(true);
				}
				
			}
		});
	//#8 -> CHOOSE TYPE OF AN REGISTRATION FRAME DONE
		chooseRegTypeFrame.add(regTypeMenu);
		chooseRegTypeFrame.setSize(500,600);
		chooseRegTypeFrame.setLocationRelativeTo(null);
		chooseRegTypeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
//#8 -> LECTURER/STUDENT REGISTRATION DONE
	
	
		
		
		
		
		
		
		
		
//#9 -> COURSE REGISTRATION FRAME	
		JFrame courseRegFrame = new JFrame("Course Registration");
		courseRegFrame.setLayout(null);
		
		JLabel nameLabel = new JLabel("Enter Name:");
		nameLabel.setBounds(10, 50, 200, 100);
		nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
		courseRegFrame.add(nameLabel);
		
		JTextField enterNameTF = new JTextField();
		enterNameTF.setBounds(155, 85, 300, 30);
		courseRegFrame.add(enterNameTF);
		
		JLabel enterIdLabel = new JLabel("Enter ID:");
		enterIdLabel.setBounds(10, 100, 200, 100);
		enterIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
		courseRegFrame.add(enterIdLabel);
		
		JTextField enterIdTF = new JTextField();
		enterIdTF.setBounds(155, 135, 300, 30);
		courseRegFrame.add(enterIdTF);
		
		JLabel enterCourseNameLbl = new JLabel("Course Name:");
		enterCourseNameLbl.setBounds(10, 150, 200, 100);
		enterCourseNameLbl.setFont(new Font("Arial", Font.BOLD, 20));
		courseRegFrame.add(enterCourseNameLbl);
		
		JTextField enterCourseNameTF9 = new JTextField();
		enterCourseNameTF9.setBounds(155, 185, 300, 30);
		courseRegFrame.add(enterCourseNameTF9);
		
		JButton saveButton9 = new JButton("Save");
		saveButton9.setBounds(400,500,80,50);
		courseRegFrame.add(saveButton9);
		saveButton9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String name = enterNameTF.getText();
				Long id=0L;
				try {
					id = Long.valueOf(enterIdTF.getText());
				} catch (NumberFormatException e2) {
					JOptionPane.showMessageDialog(courseRegFrame, "Enter valid id", "",JOptionPane.INFORMATION_MESSAGE);
				}
				String CName = enterCourseNameTF9.getText();
				
				Person person;
				try {
						person = new Person(name, id);
				} catch (IDMismatchException ex) {
					return;
				}
				boolean isCourseValid=false;
				for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
				{
					if(Objects.equals(CName, myCourse.get(i).getCourseName()))
					{
						myCourse.get(i).courseRegistration(myCourse.get(i), person);
						isCourseValid=true;								
					}
					
				}
				if(!isCourseValid)
					JOptionPane.showMessageDialog(courseRegFrame, "Course not exist", "",JOptionPane.INFORMATION_MESSAGE);
				frame.setVisible(true);
				courseRegFrame.setVisible(false);
				enterNameTF.setText("");
				enterIdTF.setText("");
				enterCourseNameTF9.setText("");		
				
			}
		});
		
		JButton cancelButton9 = new JButton("Cancel");
		cancelButton9.setBounds(20,500,80,50);
		courseRegFrame.add(cancelButton9);
		cancelButton9.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(true);
				courseRegFrame.setVisible(false);
				enterNameTF.setText("");
				enterIdTF.setText("");
				enterCourseNameTF9.setText("");				
			}
		});
		
		
		courseRegFrame.setSize(500,600);
		courseRegFrame.setLocationRelativeTo(null);
		courseRegFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
//#9 -> COURSE REGISTRATION FRAME DONE
	
		
		
		
	
		
		
//#10 -> DISPLAY SCHEDULE FRAME
	//DISPLAY SCH SELECTION FRAME
		JFrame dispSchFrame = new JFrame("Choose display type");
		final String[] disSchTypes = {"#1 For planning exam locations","#2 For lecturers/students"};
		JList disSchTypeMenu = new JList(disSchTypes);
		disSchTypeMenu.setFont(new Font("Arial", 12, 24));
		disSchTypeMenu.setVisibleRowCount(3);
		disSchTypeMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		disSchTypeMenu.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				String selected ="";
				if(e.getValueIsAdjusting())
				{
					JList source = (JList)e.getSource();
					selected = source.getSelectedValue().toString();
				}
				if(Objects.equals(selected, "#1 For planning exam locations"))
				{
					JFrame forExcamLocFrame = new JFrame();
					ArrayList<Course> sortedCourse = myCourse.get(0).dispScheduleExamArrayList();
					Object array[] = sortedCourse.toArray();
					
					JList<Object> coursesMenu = new JList<Object>(array);
					coursesMenu.setFont(new Font("Arial", 12, 24));
					coursesMenu.setVisibleRowCount(3);
					coursesMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
					
					forExcamLocFrame.add(coursesMenu);
					forExcamLocFrame.setSize(500,600);
					forExcamLocFrame.setLocationRelativeTo(null);
					forExcamLocFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
					
					forExcamLocFrame.setVisible(true);
					dispSchFrame.setVisible(false);
					
				}
				else if(Objects.equals(selected, "#2 For lecturers/students"))
				{
					JFrame getPersonInfoFrame = new JFrame();
					getPersonInfoFrame.setLayout(null);
					
					JLabel nameLabel = new JLabel("Enter Name:");
					nameLabel.setBounds(10, 50, 200, 100);
					nameLabel.setFont(new Font("Arial", Font.BOLD, 20));
					getPersonInfoFrame.add(nameLabel);
					
					JTextField enterNameTF = new JTextField();
					enterNameTF.setBounds(155, 85, 300, 30);
					getPersonInfoFrame.add(enterNameTF);
					
					JLabel enterIdLabel = new JLabel("Enter ID:");
					enterIdLabel.setBounds(10, 100, 200, 100);
					enterIdLabel.setFont(new Font("Arial", Font.BOLD, 20));
					getPersonInfoFrame.add(enterIdLabel);
					
					JTextField enterIdTF = new JTextField();
					enterIdTF.setBounds(155, 135, 300, 30);
					getPersonInfoFrame.add(enterIdTF);
					

					JButton okButton = new JButton("OK");
					okButton.setBounds(400,500,80,50);
					getPersonInfoFrame.add(okButton);
					okButton.addActionListener(new ActionListener() {
						
						@Override
						public void actionPerformed(ActionEvent e) {
							String name = enterNameTF.getText();
							Long id=0L;
							try {
								id = Long.valueOf(enterIdTF.getText());
							} catch (NumberFormatException e2) {
								JOptionPane.showMessageDialog(getPersonInfoFrame, "Enter valid id", "",JOptionPane.INFORMATION_MESSAGE);
							}					
							
							JFrame forPersonFrame = new JFrame();	
							
							Person person = null;
							try {
								person = new Person("", 30427915090L);
							} catch (IDMismatchException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							ArrayList<Course> sortedCourse = person.displaySchForPerson(name, id);
							Object array[] = sortedCourse.toArray();
							
							JList<Object> coursesMenu = new JList<Object>(array);
							coursesMenu.setFont(new Font("Arial", 12, 24));
							coursesMenu.setVisibleRowCount(3);
							coursesMenu.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
							
							forPersonFrame.add(coursesMenu);
							forPersonFrame.setSize(500,600);
							forPersonFrame.setLocationRelativeTo(null);
							forPersonFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
							
							forPersonFrame.setVisible(true);
							getPersonInfoFrame.setVisible(false);
							
						}
					});
					
					getPersonInfoFrame.setSize(500,600);
					getPersonInfoFrame.setLocationRelativeTo(null);
					getPersonInfoFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
					getPersonInfoFrame.setVisible(true);										
				}
				
			}
		});		
	//DISPLAY SCH SELECTION FRAME DONE
		dispSchFrame.add(disSchTypeMenu);
		dispSchFrame.setSize(500,600);
		dispSchFrame.setLocationRelativeTo(null);
		dispSchFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);	
//#10 -> DISPLAY SCHEDULE FRAME DONE
		
		
		menu.addListSelectionListener(
				new ListSelectionListener() {
					
					@Override
					public void valueChanged(ListSelectionEvent e) 
					{
						String selected ="";
						if(e.getValueIsAdjusting())
						{
							JList source = (JList)e.getSource();
							selected = source.getSelectedValue().toString();
						}
						if(Objects.equals(selected, "#1 Add new Course"))
						{
							frame.setVisible(false);
							addCourseFrame.setVisible(true);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}
							
						}
						else if(Objects.equals(selected, "#2 Add new Assignment"))
						{
							frame.setVisible(false);
							chooseAsTypeFrame.setVisible(true);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}					
						}
						else if(Objects.equals(selected, "#3 Submit assignment"))
						{
							frame.setVisible(false);
							chooseSubmitTypeFrame.setVisible(true);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}							
						}
						else if(Objects.equals(selected, "#4 Grade assignments"))
						{
							frame.setVisible(false);
							chooseWorkTypeFrame.setVisible(true);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}	
							
						}
						else if(Objects.equals(selected, "#5 Display grades"))
						{
							frame.setVisible(false);
							chooseWorkTypeFrame2.setVisible(true);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}	

						}
						else if(Objects.equals(selected, "#6 Display average grade of each student"))
						{
							frame.setVisible(false);
							displayAvgFrame.setVisible(true);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}
							/*
							System.out.println("Enter Course Name: ");
							String CName = input.nextLine();
							boolean isCourseValid=false;
							for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
							{
								if(Objects.equals(CName, myCourse.get(i).getCourseName()))
								{
									myCourse.get(i).displayAverageGrade();
									isCourseValid=true;
								}
								
							}
							if(!isCourseValid)
								System.out.println("Course not exist!");
							System.out.println();
							*/
						}
						else if(Objects.equals(selected, "#7 Schedule courses"))
						{
							frame.setVisible(false);
							scheduleCoursesFrame.setVisible(true);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}	
			
						}
						else if(Objects.equals(selected, "#8 Lecturer / student registration"))
						{
							frame.setVisible(false);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}
							chooseRegTypeFrame.setVisible(true);
						}
						else if(Objects.equals(selected, "#9 Course registration"))
						{
							frame.setVisible(false);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}
							courseRegFrame.setVisible(true);
					
						}
						else if(Objects.equals(selected, "#10 Display schedule"))
						{
							frame.setVisible(false);
							try {
								menu.clearSelection();
							} catch (Exception e2) {
								// TODO: handle exception
							}
							dispSchFrame.setVisible(true);
						}
						else if(Objects.equals(selected, "#11 Exit"))
						{
							System.out.println("11");
							System.exit(-1);
						}
						
					}
				}
			);

		
		
		frame.add(menu);
		frame.setSize(500,600);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
		
		
		/*
		while(true)
		{
			try {
				System.out.println("#1 Add new Course\n"
						+ "#2 Add new Assignment\n"
						+ "#3 Submit assignment\n"
						+ "#4 Grade assignments\n"
						+ "#5 Display grades\n"
						+ "#6 Display average grade of each student.\n"
						+ "#7 Schedule courses.\n"
						+ "#8 Lecturer / student registration\n"
						+ "#9 Course registration\n"
						+ "#10 Display schedule\n"
						+ "#11 Exit");
				System.out.printf("Your choice: ");
				int choice = input.nextInt();
				input.nextLine();
				if(choice==1)   //adding new Course
				{
					try {
						Course temp = new Course();
						myCourse.add(temp);
						System.out.println();	
					} catch (InputMismatchException e) {
						System.out.println("You should enter integer code");
					}
				}
				else if(choice == 2) //Adding new Assignment or lab work or project
				{
					System.out.println("Enter Course Name: ");
					String CName = input.nextLine();
					boolean isCourseValid=false;
					for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
					{
						if(Objects.equals(CName, myCourse.get(i).getCourseName()))
						{
							isCourseValid=true;
							myCourse.get(i).addNewWork();
							break;
						}
					}
					if(!isCourseValid)
						System.out.println("Course not exist!");
					System.out.println();
					
				}
				else if(choice == 3) //submitting assignment
				{
					System.out.println("Enter Course Name: ");
					String CName = input.nextLine();
					boolean isCourseValid=false;
					for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
					{
						
						if(Objects.equals(CName, myCourse.get(i).getCourseName()))
						{
							isCourseValid=true;
							myCourse.get(i).addNewSubmission();
							break;								
						}
						
					}
					if(!isCourseValid)
						System.out.println("Course not exist!");
					System.out.println();
				}
				else if(choice == 4)
				{
					
					System.out.println("Enter Course Name: ");
					String CName = input.nextLine();
					boolean isCourseValid=false;
					for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
					{
						if(Objects.equals(CName, myCourse.get(i).getCourseName()))
						{
							myCourse.get(i).gradeSubmissions();
							isCourseValid=true;
						}
					}
					if(!isCourseValid)
						System.out.println("Course not exist!");
					System.out.println();
				}
				else if(choice == 5)
				{
					System.out.println("Enter Course Name: ");
					String CName = input.nextLine();
					boolean isCourseValid=false;
					for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
					{
						if(Objects.equals(CName, myCourse.get(i).getCourseName()))
						{
							myCourse.get(i).displayGrades();
							isCourseValid=true;
						}
					}
					if(!isCourseValid)
						System.out.println("Course not exist!");
					System.out.println();
				}
				else if(choice == 6)
				{
					System.out.println("Enter Course Name: ");
					String CName = input.nextLine();
					boolean isCourseValid=false;
					for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
					{
						if(Objects.equals(CName, myCourse.get(i).getCourseName()))
						{
							myCourse.get(i).displayAverageGrade();
							isCourseValid=true;
						}
						
					}
					if(!isCourseValid)
						System.out.println("Course not exist!");
					System.out.println();
				}
				else if (choice == 7) 
				{
					System.out.println("Enter Course Name: ");
					String CName = input.nextLine();
					boolean isCourseValid=false;
					for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
					{
						if(Objects.equals(CName, myCourse.get(i).getCourseName()))
						{
							myCourse.get(i).setDayTime();
							isCourseValid=true;
						}
						
					}
					if(!isCourseValid)
						System.out.println("Course not exist!");
					System.out.println();
				}
				else if (choice == 8) 
				{
					if(!myCourse.isEmpty())
					{
						myCourse.get(0).addNewPerson();
					}
					else {
						System.out.println("You should create courses first!");
					}
					System.out.println();
				}
				else if (choice == 9) 
				{
					System.out.printf("Enter your name: ");
					String name = input.nextLine();
					System.out.printf("Enter your id: ");
					Long id  = input.nextLong();
					input.nextLine();
					System.out.printf("Enter course name: ");
					String CName = input.nextLine();
					Person person;
					try {
							person = new Person(name, id);
					} catch (IDMismatchException e) {
						break;
					}
					boolean isCourseValid=false;
					for(int i=0;i<myCourse.size();i++)		//checking for is course exist in course array
					{
						if(Objects.equals(CName, myCourse.get(i).getCourseName()))
						{
							myCourse.get(i).courseRegistration(myCourse.get(i), person);
							isCourseValid=true;								
						}
						
					}
					if(!isCourseValid)
						System.out.println("Course not exist!");
					System.out.println();
				}
				else if (choice == 10) 
				{
					if(!myCourse.isEmpty())
						Item.displaySchSelection();
					else {
						System.out.println("You should create courses first!");
					}
					System.out.println();
				}
				else if(choice == 11)
				{
					return;
				}
				else
				{
					System.out.println("Invalid input enter valid input\n");
				}
				
			} catch (InputMismatchException e)
			{
				System.out.println("You should enter integer!\n");
				input.nextLine();
			}
		}
		*/
	}
	
}
