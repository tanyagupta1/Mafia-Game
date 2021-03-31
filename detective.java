import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class detective extends player
{
	private List<detective> detectives=new ArrayList<>();
    public detective(){setHp(800);}
    public void addDetectives(ArrayList<detective> d) {detectives = (ArrayList<detective>)d.clone();}
    public  static int vote(player[] o)
	{
	List<Integer> votebank=new ArrayList<>();
	for(int i=1;i<o.length;i++)
	{
	   if(!(o[i] instanceof detective)&&((o[i]).getStatus()))votebank.add(i);
	}
	int [] count=new int[o.length];
	for(int i=1;i<o.length;i++)
	{
		if((o[i] instanceof detective) &&((o[i]).getStatus()))
			{
			Random random = new Random();
		    int f= random.ints(0,votebank.size()).findFirst().getAsInt();
		    count[votebank.get(f)]++;
		    //System.out.println("detective "+i+" voted for "+votebank.get(f));
			}
	}
	int max=0; int ans=-1;
	for(int i=count.length-1;i>-1;i--) 
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
   /* public static boolean test(player [] o, int target)
    {   
    	if(target==-1) return false;
    	if(o[target] instanceof mafia) return true;
    	return false;
    } */
    public static <T extends player> boolean test(player [] o, T target)
    {   
    	
    	if(target instanceof mafia) return true;
    	return false;
    }
    

}
