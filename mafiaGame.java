/*
 * Name: Tanya Gupta
 * Roll no- 2019119
 * CSE
 * Assignment 3(AP)
 */
import java.util.*;
abstract class player
{
	private double hp;
	private boolean status =true;
	public double getHp() {return hp;}
	public void setHp(double hp){this.hp = hp;}
	public boolean getStatus() {return status;}
	public void setStatus(boolean status) {this.status = status;}
}
public class mafiaGame 
{ 
	private int nMafia=0,nDetective=0,nHealer=0,nCommoner=0;
	public void printMenu()
	{
		System.out.println("Choose a Character");
		System.out.println("1) Mafia");
		System.out.println("2) Detective");
		System.out.println("3) Healer");
		System.out.println("4) Commoner");
		System.out.println("5) Assign Randomly");
	}
	public int voteout(Object[] o, Object user, int vote)
	{
		
		List<Integer> cands= new ArrayList<>();
		for(int i=1;i<o.length;i++)
		{
		if(((player)o[i]).getStatus()){cands.add(i);}
		}
		int [] votebank=new int[o.length];
		for(int i=1;i<o.length;i++)
		{
		if(((player)o[i]).getStatus())
		{
			if(o[i].equals(user))
			{
				votebank[vote]++;
			}
			else
			{
			Random random = new Random();
		    int f= random.ints(0,cands.size()).findFirst().getAsInt();
		    votebank[cands.get(f)]++;
			}
		}
		}
		int max=0; int ans=-1;
		for(int i=votebank.length-1;i>-1;i--) 
			{
			if(votebank[i]>max)
			{
				ans=i;
				max=votebank[i];
			}
			}
		return ans;
	}
	public void gameOver(int det, int com,int hel,int n)
	{
		System.out.println("Game over");
		if(nMafia==0) System.out.println("Commoners won !");
		else System.out.println("Mafias won !");	
		System.out.println("mafias were:");	for(int j=1;j<det;j++)System.out.print("Player:"+j+" ");
		System.out.println();
		System.out.println("detectives were:");for(int j=det;j<hel;j++)System.out.print("Player:"+j+" ");
		System.out.println();
		System.out.println("healers were:");for(int j=hel;j<com;j++)System.out.print("Player:"+j+" ");
		System.out.println();
		System.out.println("commoners  were:");for(int j=com;j<=n;j++)System.out.print("Player:"+j+" ");
			
		
	}
	
	public int assignUser(int choice,int user, int det, int hel,int com)
	{
		switch(choice)
		{
		case 1:
			user =1;
			System.out.println("You are Player: "+user+" You are a mafia.Other mafias are:");
			for(int j=2;j<det;j++) System.out.print("Player "+j); System.out.println();
			break;
		case 2:
			user=det;
			System.out.println("You are Player: "+user+" You are a detective.Other detectives are:");
			for(int j=det+1;j<hel;j++) System.out.print("Player "+j); System.out.println();
			break;
		case 3:
			user=hel;
			System.out.println("You are Player: "+user+" You are a healer. Other healers are:");
			for(int j=hel+1;j<com;j++) System.out.print("Player "+j); System.out.println();
			break;
		case 4:
			user=com;
			System.out.println("You are Player: "+user+" You are a commmoner");
			break;
		case 5:
			Random random = new Random();
		    choice= random.ints(1,5).findFirst().getAsInt();
		    if(choice==1)
		    {
		    	user=1;
		    	System.out.println("You are Player: "+user+" You are a mafia.Other mafias are:");
				for(int j=2;j<det;j++) System.out.print("Player "+j); System.out.println();
		    }
		    else if(choice==2)
		    {
		    	user=det;
				System.out.println("You are Player: "+user+" You are a detective.Other detectives are:");
				for(int j=det+1;j<hel;j++) System.out.print("Player "+j); System.out.println();	
		    }
		    else if(choice==3)
		    {
		    	user=hel;
				System.out.println("You are Player: "+user+" You are a healer. Other healers are:");
				for(int j=hel+1;j<com;j++) System.out.print("Player "+j); System.out.println();
		    }
		    else if(choice ==4)
		    {
		    	user=com;
				System.out.println("You are Player: "+user+" You are a commmoner");
		    }
			break;
		}
		return user;
		
	}
	
	public void playerDied(Object [] players,int target)
	{
		((player)players[target]).setStatus(false);
		System.out.println("Player:"+target+" has died");
		if(players[target] instanceof mafia) nMafia--;
		else if(players[target] instanceof detective) nDetective--;
		else if(players[target] instanceof healer) nHealer--;
		else if(players[target] instanceof commoner) nCommoner--;
		
	}
	public void playerVotedOut(Object [] players, int voteout)
	{
		((player)(players[voteout])).setStatus(false);
		if(players[voteout] instanceof mafia) nMafia--;
		else if(players[voteout] instanceof detective) nDetective--;
		else if(players[voteout] instanceof healer) nHealer--;
		else if(players[voteout] instanceof commoner) nCommoner--;
		
	}
	public void newRoundInfo(int round, Object[] players,int n)
	{
		System.out.println("Round "+round);
		System.out.println((nMafia+nDetective+nHealer+nCommoner)+ " players are remaining. They are:");
		for(int j=1;j<=n;j++) if(((player)players[j]).getStatus()) System.out.print("Player: "+j+" ");
		System.out.println();
		//System.out.println("mafia:"+nMafia+"det:"+nDetective+"heal:"+nHealer+"com:"+nCommoner);
	}
	public static void main(String [] args)
	{
	Scanner s=new Scanner(System.in);
	mafiaGame game=new mafiaGame();
	int n=0;
	System.out.println("Welcome to mafia");
	boolean number=true;
	while(number)
	{
	try
	{   System.out.println("Enter number of players:");
		n=s.nextInt();
		if(n<6) {System.out.println("Please enter a valid integer>=6");  continue;}
		number=false;
	}
	
	catch(Exception e){System.out.println("Please enter a valid integer>=6"); s.next();}
	} 
	player [] players= new player[n+1];
	int user=0;
	int i=1;int d=n/5; int h=Math.max(1, n/10);
	for(;i<=n/5;i++) players[i]=new mafia(); final int det=i;
	while(d>0) { players[i]=new detective();d--;i++;} final int hel=i;
	while(h>0) { players[i]=new healer();h--;i++;} final int com=i;
	for(;i<=n;i++) {players[i]=new commoner();}
	game.nMafia=det-1; game.nDetective=hel-det; game.nHealer=com-hel; game.nCommoner=n+1-com;
	int choice=0;
	boolean chosen=false;
	while(!chosen)
	{
	try
	{   game.printMenu();
		choice=s.nextInt();
		if((choice>5) ||(choice<1) ) {System.out.println("Please enter a valid between 1 to 5");  continue;}
		chosen=true;
	}
	
	catch(Exception e){System.out.println("Please enter a valid integer between 1 to 5"); s.next();}
	} 
	user=game.assignUser(choice, user, det, hel, com);
	
	
	
	
		
		boolean loop=(((game.nDetective+game.nHealer+game.nCommoner)==game.nMafia)||(game.nMafia==0));
		int round=0;
		while(!loop)
		{
			round++;
			
			game.newRoundInfo(round,players,n);
			int target=-1,detected=-1,healed=-1; boolean result=false;
		
				
			if((user==1)&&(players[user]).getStatus()) 
			{
				
				boolean accept=false;
				while(!accept)
				{   
					System.out.println("Choose a target");
					target=s.nextInt();	
					if(((target<1)||(target>n))||!(players[target].getStatus())) System.out.println("Invalid choice!");
					else if(players[target] instanceof mafia) System.out.println("cant chose mafia!");
					else accept=true;
				}
						
				
				mafia.kill(players, players[target]);
				System.out.println("mafias have poisoned player:"+target);
			}
			else 
			{
				target=mafia.vote(players);
				if(target>0)mafia.kill(players, players[target]);
				System.out.println("mafias have have poisoned someone ");
			}
			
				
			if((user==det)&&(players[user]).getStatus()) 
			{
				boolean accept=false;
				while(!accept)
				{   
					System.out.println("Choose a person to test");
					detected=s.nextInt();	
					if(((detected<1)||(detected>n))||!(players[detected].getStatus())) System.out.println("Invalid choice!");
					else if(players[detected] instanceof detective) System.out.println("cant chose detective!");
					else accept=true;
				}
				
				System.out.println("Detectives have chosen  player" +detected+" to test");
				result=detective.test(players, players[detected]);
				System.out.println("the result was "+result);
			}
			else 
			{
				detected=detective.vote(players);
				System.out.println("Detectives have chosen  someone to test");
				if(detected>0)result=detective.test(players, players[detected]);
				else result=false;
				//System.out.println("the result was "+result);
			}
			
				
			if((user==hel)&&(players[user]).getStatus()) 
			{
				boolean accept=false;
				while(!accept)
				{   
					System.out.println("Choose a person to heal");
					healed=s.nextInt();	
					if(((healed<1)||(healed>n))||!(players[healed].getStatus())) System.out.println("Invalid choice!");
					else accept=true;
				}
				
				healer.heal(players, players[healed]);
				System.out.println("healers healed "+healed);
			}
			else 
		    {
				healed =healer.vote(players);
				if(healed>0)healer.heal(players, players[healed]);
				System.out.println("healers healed someone");
		    }
			
			
				
			System.out.println("--End of actions");
			if((target!=-1) && (players[target]).getHp()==0)
			{   
				game.playerDied(players, target);
				if((game.nDetective+game.nHealer+game.nCommoner)==game.nMafia) break;
			} 
			else System.out.println("no one died!");
				
			int voteout=-1;
			if(result) voteout=detected;
			else
			{
				if((players[user]).getStatus()) 
				{	
					boolean accept=false;
					while(!accept)
					{
					System.out.println("Select a person to vote out");
					voteout=s.nextInt();
					if(voteout<1 || voteout>n)
						{
						System.out.println("enter valid player to voteout");
						continue;
						}
					if((players[voteout]).getStatus()) accept=true;
					else System.out.println("enter valid player to voteout");
					}
					voteout=game.voteout(players, user, voteout);
				}
				else
				{
					System.out.println("voting without you:");voteout=0;
					voteout=game.voteout(players, user, voteout);
				}
				
				}
				game.playerVotedOut(players, voteout);
				
				System.out.println("Player: "+voteout+"has been voted out");
				loop=(((game.nDetective+game.nHealer+game.nCommoner)==game.nMafia)||(game.nMafia==0));
				System.out.println("--End of round "+round);
				//test2.print(players);
				}
		        game.gameOver(det, com, hel, n);
		
		
		}
		
	}
	
	
	
	


