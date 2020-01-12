import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class Club //club class for creating object of each football team
{
	String ClubName;
	int ClubLevel;
	String ClubStadium;
	int ClubCity;
	
	public Club(String clubName, int clubLevel, String clubStadium, int clubCity) 
	{
		ClubName = clubName;
		ClubLevel = clubLevel;
		ClubStadium = clubStadium;
		ClubCity = clubCity;
 	}
}


class Match //match class for creating a match object for each match
{
	Club HomeTeam;
	Club GuestTeam;
	public Match(Club homeTeam, Club guestTeam)
	{
		HomeTeam = homeTeam;
		GuestTeam = guestTeam;
	}
}

public class Main {

	public static void main(String[] args)
	{
		ArrayList<Club> clubs = new ArrayList<Club>(); //all football teams are stored in clubs
		clubs.add(new Club("Alanyaspor", 2, "Bahçeşehir Okulları Stadyumu", 7));
		clubs.add(new Club("Antalyaspor", 2, "Antalya Stadyumu",7));
		clubs.add(new Club("Beşiktaş", 1, "Vodafone Park", 34));
		clubs.add(new Club("Çaykur Rizespor", 2, "Çaykur Didi Stadyumu",53));
		clubs.add(new Club("Denizlispor", 2, "Denizli Atatürk Stadyumu",20));
		clubs.add(new Club("Fenerbahçe", 1, "Fenerbahçe Şükrü Saraçoğlu Stadyumu",34));
		/*
		clubs.add(new Club("Galatasaray", 1, "Türk Telekom Stadyumu",34));
		clubs.add(new Club("Gaziantep FK", 2, "Gaziantep Kalyon Stadyumu",27));
		clubs.add(new Club("Gençlerbirliği", 2, "Ankara Ondokuz Mayıs Stadyumu",6));
		clubs.add(new Club("Göztepe", 2, "İzmir Atatürk Stadyumu", 35));
		clubs.add(new Club("İstanbul Başakşehir", 2, "Başakşehir Fatih Terim Stadyumu",34));
		clubs.add(new Club("Kasımpaşa", 2, "Recep Tayyip Erdoğan Stadyumu", 34));
		clubs.add(new Club("Kayserispor", 2, "Kadir Has Şehir Stadı", 38));
		clubs.add(new Club("Konyaspor", 2, "Konya Büyükşehir Belediye Stadyumu", 42));
		clubs.add(new Club("MKE Ankaragücü", 2, " Ankara Ondokuz Mayıs Stadyumu", 6));
		clubs.add(new Club("Sivasspor", 2, "Yeni Sivas 4 Eylül Stadyumu", 58));
		clubs.add(new Club("Trabzonspor", 1, "Medical Park Stadyumu", 61));
		clubs.add(new Club("Yeni Malatyaspor", 2, "Yeni Malatya Stadyumu", 44));
		
		
		Collections.shuffle(clubs);
		
		*/
		
        int totalWeeks = clubs.size() - 1; //for first half ex: 18 teams -> 17 weeks
        int matchesPerWeek = clubs.size() / 2; //number of matches per week ex: 18 teams -> 9 matches per week
        
        Match[][] weeks = new Match[totalWeeks][matchesPerWeek]; //all matches for all weeks
        
        //construction of matches by cyclic algorithm last team is static other teams are rotating in clockwise
        for (int week = 0; week < totalWeeks; week++) 
        {
            for (int match = 0; match < matchesPerWeek; match++)
            {
                int home = (week + match) % (totalWeeks);
                int guest = (totalWeeks - match + week) % (totalWeeks);
             
                if (match == 0) //last team is static.
                {
                    guest = totalWeeks;//clubs.size()-1
                }
                
                weeks[week][match] = new Match(clubs.get(home), clubs.get(guest)); //create new match object(getting teams from clubs ArrayList) and put it in weeks array
            }
        }
        
        //change weeks so home and guest matches fiarly distributed
        Match[][] changedWeeks = new Match[totalWeeks][matchesPerWeek];
        
        int even = 0;
        int odd = (clubs.size() / 2);
        for (int i = 0; i < weeks.length; i++) 
        {
            if (i % 2 == 0) //if i%2 is 0 put even
            {
                changedWeeks[i] = weeks[even++];
            } 
            else //else put odd
            {
                changedWeeks[i] = weeks[odd++];
            }
        }
        
        weeks = changedWeeks; //change original weeks ArrayList
        
        //After all these operations everything is done except last team is always in guest position.
        //So change odd it to home on odd weeks
        for (int week = 0; week < weeks.length; week++)
        {
            if (week % 2 == 1) 
            {
            	Club temp = weeks[week][0].HomeTeam;
            	weeks[week][0].HomeTeam = weeks[week][0].GuestTeam;
            	weeks[week][0].GuestTeam = temp;
            }
        }
        
        
        /*
        //if there is a derby match in first 2 weeks or last 2 weeks change them with a random match in the middle weeks
        for(int week = 0; week < weeks.length; week++)
        {
        	    if(week==0||week==1||week==(totalWeeks-2)||week==(totalWeeks-1))
        	    {
        	    	for(int i=0;i<matchesPerWeek;i++)
        	    	{
        	    		if(weeks[week][i].HomeTeam.ClubLevel==1&&weeks[week][i].GuestTeam.ClubLevel==1)
        	    		{        	    			
        	    			Match temp = weeks[week][i];      
        	    			weeks[week][i] = weeks[totalWeeks/2][i];
        	    			weeks[totalWeeks/2][i] = temp;
        	    		}
        	    	}
        	    }
        	    
        }
        */
        
        
        //print first half
        for(int i=0;i<totalWeeks;i++)
        {
        	for(int j=0;j<matchesPerWeek;j++)
        	{
        		if(weeks[i][j].HomeTeam.ClubLevel==1&&weeks[i][j].GuestTeam.ClubLevel==1)
        			System.out.println("Week: "+(i+1)+" "+weeks[i][j].HomeTeam.ClubName+" vs "+weeks[i][j].GuestTeam.ClubName + " at "+weeks[i][j].HomeTeam.ClubStadium+"("+weeks[i][j].HomeTeam.ClubCity+") DERBY!!!!");
        		else
        			System.out.println("Week: "+(i+1)+" "+weeks[i][j].HomeTeam.ClubName+" vs "+weeks[i][j].GuestTeam.ClubName + " at "+weeks[i][j].HomeTeam.ClubStadium+"("+weeks[i][j].HomeTeam.ClubCity+") ");
        	}
        	System.out.println();
        }
        //print second half by changing guests to home
        for(int i=0;i<totalWeeks;i++)
        {
        	for(int j=0;j<matchesPerWeek;j++)
        	{
        		if(weeks[i][j].HomeTeam.ClubLevel==1&&weeks[i][j].GuestTeam.ClubLevel==1)
        			System.out.println("Week: "+(i+totalWeeks+1)+" "+weeks[i][j].GuestTeam.ClubName+" vs "+weeks[i][j].HomeTeam.ClubName + " at "+weeks[i][j].GuestTeam.ClubStadium+"("+weeks[i][j].GuestTeam.ClubCity+") DERBY!!!!");
        		else 
        			System.out.println("Week: "+(i+totalWeeks+1)+" "+weeks[i][j].GuestTeam.ClubName+" vs "+weeks[i][j].HomeTeam.ClubName + " at "+weeks[i][j].GuestTeam.ClubStadium+"("+weeks[i][j].GuestTeam.ClubCity+")");				
        	}
        	System.out.println();
        }
		
	}

}
