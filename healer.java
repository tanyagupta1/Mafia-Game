import java.util.*;
public class healer extends player
{
	private List<healer> healers=new ArrayList<>();
	public healer(){setHp(800);}
	public void addHealer(ArrayList<healer> h) {healers = (ArrayList<healer>)h.clone();}
	public  static int vote(player[] o)
	{
	List<Integer> votebank=new ArrayList<>();
	for(int i=1;i<o.length;i++)
	{
	   if((((player)o[i]).getStatus()))votebank.add(i);
	}
	int [] count=new int[o.length];
	for(int i=1;i<o.length;i++)
	{
		if((o[i] instanceof healer) &&((o[i]).getStatus()))
			{
			Random random = new Random();
		    int f= random.ints(0,votebank.size()).findFirst().getAsInt();
		    count[votebank.get(f)]++;
		    //System.out.println("healer "+i+" voted for "+votebank.get(f));
			}
	}
	int max=0; int ans=-1;
	for(int i=0;i<count.length;i++) 
		{
		//System.out.print(count[i]+" ");
		if(count[i]>max)
		{
			ans=i;
			max=count[i];
		}
		}
	return ans;
	}
	/*public static void heal(player [] o, int healed)
	{
		if(healed==-1);
		else
			{
			double hp=(o[healed]).getHp();
			(o[healed]).setHp(hp+500);
			}
		return;
	}*/
	
	public static <T extends player> void  heal(player [] o, T toheal)
	{
		
			
			double hp=toheal.getHp();
			toheal.setHp(hp+500);
			
		return;
		
	}
	

}
