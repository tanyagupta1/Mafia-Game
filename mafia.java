import java.util.*;
public class mafia extends player
{
	private List<mafia> mafias=new ArrayList<>();
	public mafia(){setHp(2500);}
	public void addMafia(ArrayList<mafia> m) {mafias = (ArrayList<mafia>)m.clone();}
	
	public  static int vote(player[] o)
	{
	List<Integer> votebank=new ArrayList<>();
	for(int i=1;i<o.length;i++)
	{
		if(!(o[i] instanceof mafia) &&((o[i]).getStatus()))votebank.add(i);
	}
	int [] count=new int[o.length];
	for(int i=1;i<o.length;i++)
	{
		if((o[i] instanceof mafia) &&((o[i]).getStatus()))
			{
			Random random = new Random();
		    int f= random.ints(0,votebank.size()).findFirst().getAsInt();
		    count[votebank.get(f)]++;
		    //System.out.println("mafia "+i+" voted for "+votebank.get(f));
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
	
	public static <T extends player> int kill(player [] o, T target)
	{
		
		double mafiaHp=0; double tHp=(target).getHp();
		int mCount=0;
		for(int i=1;i<o.length;i++) {if(((o[i] instanceof mafia) &&((o[i]).getStatus())))
			{
			mafiaHp+=(o[i]).getHp();
			mCount++;
			}
		}
		//System.out.println("initial count is "+mCount); 
		if(tHp<mafiaHp) (target).setHp(0);
		else (target).setHp(tHp-mafiaHp);
		List<Integer> zeroM=new ArrayList<>();
		while(mCount>0 && tHp>0.0001)
		{
		double surplus=0;	
		int zero=0;
		
		for(int i=1;i<o.length;i++) 
		{
			if(((o[i] instanceof mafia) &&((o[i]).getStatus())))
			{
				if(zeroM.contains(i)) continue;
				double h=Math.max(0, ((o[i]).getHp()-(tHp/mCount)));
				//System.out.println("h is "+h+" is is "+i);
				if(h==0) 
				{
					zero++;
					zeroM.add(i);
					surplus+=tHp/mCount-(o[i]).getHp();
				}
				
				(o[i]).setHp(h);
			}
		}
		tHp=surplus;
		mCount-=zero;
		//System.out.println("thp left is"+tHp+"mCou"+mCount);
		//System.out.println(zeroM);
		}
		return 0;
		
	}
	
	
	
	
}
