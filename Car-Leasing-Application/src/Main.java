import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Collections;
import java.util.HashMap;
import java.util.InputMismatchException;
public class Main {

	public static void main(String[] args) 
	{
		Scanner input = new Scanner(System.in);
		
		List<Lease> myLeases = new ArrayList<Lease>();  
		List<Car> myCars = new ArrayList<Car>();
		Map<String, String> users = new HashMap<String, String>();
		//font for buttons
				Font plain = new Font("Arial", Font.PLAIN, 18);
				
				//main frame
				JFrame frame;
				frame = new JFrame("Car Leasing System");
				frame.setSize(500,600);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
				frame.setResizable(false); //user cannot drag and resize window
				frame.setLocationRelativeTo(null); //window will appear in the center of the screen
				
				JButton leaseCarButton = new JButton("Lease a Car");
				leaseCarButton.setBounds(150, 150, 200, 50);
				leaseCarButton.setFont(plain);
				JButton displayLeasesButton = new JButton("Display all Leases");
				displayLeasesButton.setFont(plain);
				displayLeasesButton.setBounds(150, 300, 200, 50);
				JButton exitButton = new JButton("Exit");
				exitButton.setBounds(150, 450, 200, 50);
				exitButton.setFont(plain);

				frame.add(leaseCarButton);
				frame.add(displayLeasesButton);
				frame.add(exitButton);

				
				//Label
				JLabel label = new JLabel("Car Leasing System");
				label.setBounds(125, 50, 400, 50);
				label.setFont(new Font("Arial",Font.BOLD,25));
				frame.add(label);
				
				
				frame.setLayout(null); 
				frame.setVisible(true); //in default Jframe is not visible 
				
				
				
				//leasing information frame
				JFrame leaseCarFrame = new JFrame("Lease car");
				leaseCarFrame.setSize(500,600);
				leaseCarFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //if you won't write this after close clicked your game will contiune execution in backround
				leaseCarFrame.setResizable(false); //user cannot drag and resize window
				leaseCarFrame.setLocationRelativeTo(null); //window will appear in the center of the screen
				
				//Enter Lease info Label
				JLabel enterInfoLabel = new JLabel("Enter lease Information");
				enterInfoLabel.setBounds(100, 50, 400, 50);
				enterInfoLabel.setFont(new Font("Arial",Font.BOLD,25));
				leaseCarFrame.add(enterInfoLabel);
				
				

				
				JLabel driverNameLabel = new JLabel("Driver Name: ");
				driverNameLabel.setBounds(10, 120, 400, 50);
				driverNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
				leaseCarFrame.add(driverNameLabel);
				
				JTextField enterDriverNametxField = new JTextField("Enter driver name");
				enterDriverNametxField.setBounds(180, 125, 250, 40);
				leaseCarFrame.add(enterDriverNametxField);
				
				JLabel carBrandModelLbl = new JLabel("Car brand and model: ");
				carBrandModelLbl.setBounds(10, 200, 400, 50);
				carBrandModelLbl.setFont(new Font("Arial", Font.PLAIN, 16));
				leaseCarFrame.add(carBrandModelLbl);
				
				JTextField enterBrandModelTxField = new JTextField("Enter brand model");
				enterBrandModelTxField.setBounds(180, 205, 250, 40);
				leaseCarFrame.add(enterBrandModelTxField);
				
				JLabel durationLabel = new JLabel("Lease Duration: ");
				durationLabel.setBounds(10, 280, 400, 50);
				durationLabel.setFont(new Font("Arial", Font.PLAIN, 16));
				leaseCarFrame.add(durationLabel);
				
				JTextField enterDurationtxField = new JTextField("Enter duration");
				enterDurationtxField.setBounds(180, 285, 250, 40);
				leaseCarFrame.add(enterDurationtxField);
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setBounds(200, 520, 150, 50);
				cancelButton.setFont(plain);
				leaseCarFrame.add(cancelButton);
				
				JButton saveButton = new JButton("Save");
				saveButton.setBounds(350, 520, 150, 50);
				saveButton.setFont(plain);
				leaseCarFrame.add(saveButton);
				
				leaseCarFrame.setLayout(null);

				class ButtonHandler implements ActionListener
				{
					@Override
					public void actionPerformed(ActionEvent e) 
					{
							if(e.getSource()==leaseCarButton)
							{
								leaseCarFrame.setVisible(true);						
								frame.setVisible(false);
							}
							if(e.getSource()==displayLeasesButton)
							{														
								//JOptionPane.showMessageDialog(null, new JScrollPane(new JList(myTickets.toArray())));
								    String output = "";
								    for (int i=0; i<myLeases.size(); i++) 
								    {	
								    	int num = i+1;
								    	Integer.toString(num);
								        output += "Lease #"+ num + "\n" +myLeases.get(i) + "\n\n";
								    }
								    JOptionPane.showMessageDialog(frame,output);
							}
							if(e.getSource()==saveButton)
							{
								String brandModel = enterBrandModelTxField.getText();
								String duration = enterDurationtxField.getText();
								String driverName = enterDriverNametxField.getText();
								
								myLeases.add(new Lease(brandModel,duration,driverName));
								
								
								enterDurationtxField.setText("Enter duration");
								enterBrandModelTxField.setText("Enter brand model");
								enterDriverNametxField.setText("Enter driver name");

								leaseCarFrame.setVisible(false);
								frame.setVisible(true);
							}
							if(e.getSource()==cancelButton)
							{								
								enterDurationtxField.setText("Enter duration");
								enterBrandModelTxField.setText("Enter brand model");
								enterDriverNametxField.setText("Enter driver name");
								leaseCarFrame.setVisible(false);
								
								frame.setVisible(true);
							}
							if(e.getSource()==exitButton)
							{
								System.exit(0);
							}
					}
				}
				ButtonHandler handler = new ButtonHandler();
				leaseCarButton.addActionListener(handler);
				displayLeasesButton.addActionListener(handler);
				exitButton.addActionListener(handler);
				saveButton.addActionListener(handler);
				cancelButton.addActionListener(handler);
				exitButton.addActionListener(handler);
		while(true)
		{
			System.out.println("1. Create new Lease with model year\n" + 
					   "2. Display all Leases\n" + 
					   "3. Calculate total Insurance of Leasing Company\n" +
					   "4. Display all users\n" + 
					   "0. Exit");
			int choice = input.nextInt();
			input.nextLine();
			if(choice==1)
			{
				String username=null;
				String password=null;
				boolean isPasswordTrue = false;
				boolean isUserNameTrue = false;
				
				do  // do while for user name correction
				{
					try {
						System.out.printf("Enter username: ");
						username = input.nextLine();
						
						//checking is user name already taken
						Set<String> AllUserNames  = users.keySet();
						boolean isUserNameTaken = false;
						for(String nickName : AllUserNames)
						{
							if(Objects.equals(nickName, username))
							{
								System.out.println("This username is already taken. Try again!");
								isUserNameTaken = true;
							}
						}
						//if its not then check does user name meet the minimum requirements
						if(!isUserNameTaken)
						{
							boolean isLastIndexDigit = false;
							if(Character.isDigit(username.charAt(username.length()-1)))
								isLastIndexDigit = true;
							if(username.length()<8 || isLastIndexDigit)
							{
								throw new Exception();
							}
							else {
								isUserNameTrue = true;
							}						
						}
					}
					catch (Exception e) {
						System.out.println("Username does not meet the minimum requirements. Try again!\n");						
					}
				}while(!isUserNameTrue);
				
				do	 //do while for password correction
				{	
					try
					{
						System.out.printf("Enter password: ");
						password = input.nextLine();
						boolean isUpperCaseExist = false;
			
						for(int i=0; i<password.length(); i++)
						{
							if(Character.isUpperCase(password.charAt(i)))
							{
								isUpperCaseExist = true;
								break;
							}
						}
						if(!isUpperCaseExist||password.length()<8)
						{
							throw new Exception();
						}
						else {
							isPasswordTrue = true;
						}
					}
					catch (Exception e) {
						System.out.println("Password does not meet the minimum requirements. Try again!\n");						
					}
				} while (!isPasswordTrue);
				
				System.out.println("\n*********************************\nYou have successfully registered!\n*********************************\n");	
				users.put(username, password);
				
				
				System.out.println("1. Create a monthly Lease (Lease)\n" + 
						   "2. Create a ShortTermLease\n" +
						   "3. Create a LongTermLease");
				int choice2 = input.nextInt();
				if(choice2==1)
				{
					int carModelYear = 0;
					while(true)
					{
						try 
						{
							System.out.println("Enter car model year: ");
							carModelYear = input.nextInt();
							break;
						} catch (InputMismatchException e) 
						{
							System.out.println("Please enter the model year correctly!");
							input.nextLine();
						}
					}
					input.nextLine();
					Lease newL = new Lease(carModelYear);
					myLeases.add(newL);
					System.out.println("Lease load done with model year! "+ myLeases.get(myLeases.size()-1).car.getCarModelYear());
					System.out.println();
					
					//if created car in lease class does not exist in our car array then add it.
					Car newCar = myLeases.get(myLeases.size()-1).car;
					myCars.add(newCar);
					for(int i=0;i<myCars.size()-1;i++)
					{
						if((Objects.equals(myCars.get(i).getCarBrandModel(), newCar.getCarBrandModel())) && (myCars.get(i).getCarModelYear()==newCar.getCarModelYear()))
						{
							myCars.remove(myCars.size()-1);
						}
					}						
					


				}
				else if(choice2==2)
				{
					int carModelYear = 0;
					while(true)
					{
						try 
						{
							System.out.println("Enter car model year: ");
							carModelYear = input.nextInt();
							break;
						} catch (InputMismatchException e) 
						{
							System.out.println("Please enter the model year correctly!");
							input.nextLine();
						}
					}
					input.nextLine();
					ShortTermLease newLS = new ShortTermLease(carModelYear);
					myLeases.add(newLS);
					System.out.println("Lease load done with model year! "+ myLeases.get(myLeases.size()-1).car.getCarModelYear());
					System.out.println();

					//if created car in lease class does not exist in our car array then add it.
					Car newCar = myLeases.get(myLeases.size()-1).car;
					myCars.add(newCar);
					for(int i=0;i<myCars.size()-1;i++)
					{
						if((Objects.equals(myCars.get(i).getCarBrandModel(), newCar.getCarBrandModel())) && (myCars.get(i).getCarModelYear()==newCar.getCarModelYear()))
						{
							myCars.remove(myCars.size()-1);
						}
					}
				}
				else if(choice2==3) 
				{
					int carModelYear = 0;
					while(true)
					{
						try 
						{
							System.out.println("Enter car model year: ");
							carModelYear = input.nextInt();
							break;
						} catch (InputMismatchException e) 
						{
							System.out.println("Please enter the model year correctly!");
							input.nextLine();
						}
					}
					input.nextLine();
					LongTermLease newLL = new LongTermLease(carModelYear);
					myLeases.add(newLL);
					System.out.println("Lease load done with model year! "+ myLeases.get(myLeases.size()-1).car.getCarBrandModel());
					System.out.println();

					//if created car in lease class does not exist in our car array then add it.
					Car newCar = myLeases.get(myLeases.size()-1).car;
					myCars.add(newCar);
					for(int i=0;i<myCars.size()-1;i++)
					{
						if((Objects.equals(myCars.get(i).getCarBrandModel(), newCar.getCarBrandModel())) && (myCars.get(i).getCarModelYear()==newCar.getCarModelYear()))
						{
							myCars.remove(myCars.size()-1);
						}
					}
				}
				else {
					System.out.println("Invalid choice!");
				}

			}
			else if(choice==2)	
			{
				System.out.println();
				Collections.sort(myLeases, Lease::compareTo);
				System.out.println("1. Ascending order\n2. Descending order");
				int choice2 = input.nextInt();
				if(choice2==1)
				{
					for(int j=0;j<myLeases.size();j++)
						myLeases.get(j).displayInfo();
				}
				else if(choice2==2)
				{
					Collections.reverse(myLeases);
					for(int j=0;j<myLeases.size();j++)
						myLeases.get(j).displayInfo();
				}
				else 
					System.out.println("Invalid input");

				System.out.println();
			}
			else if(choice==3)
			{
				double totalInsurance = 0;
				for(int i=0;i<myCars.size();i++)
				{
					totalInsurance += myCars.get(i).calculateInsurance();
				}
				for(int i=0;i<myLeases.size();i++)
				{
					totalInsurance += myLeases.get(i).calculateInsurance();
				}
				System.out.println();
				System.out.println("Total Insurance: " + totalInsurance);
				System.out.println();
			}
			else if(choice==4)
			{
				Set<String> usernames = users.keySet();
				for(String nickname : usernames)
				{
					System.out.println(nickname);
				}
				System.out.println();
			}
			else if(choice==0)
				break;
			else
			{
				System.out.println();
				System.out.println("Enter Valid Choice!");
				System.out.println();
			}
			
		}
		
	}

}
