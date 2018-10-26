import java.util.*;
public class Q4 
{
	public static void main(String args[])
	{
		int T;
		Scanner s=new Scanner(System.in);
		T=s.nextInt();
		while(T--!=0)
		{
			TimeTablePopulator att=new TimeTablePopulator();
			int n=s.nextInt();
			while(n-->0)
			{
				String batch=s.next();
				att.addBatch(batch);
			}
			
			int fn=s.nextInt();
			while(fn-->0)
			{
				String fname=s.next();
				att.addFaculty(new faculty(fname));
			}
			
			int c=s.nextInt();
			course C[]=new course[c];
			for(int i=0;i<c;i++)
			{
				String id=s.next();
				String name=s.next();
				String facn=s.next();
				int priority=s.nextInt();
				String batch=s.next();
				int slots=s.nextInt();
				C[i]=new course(id,name,att.searchFaculty(facn),priority,batch);
				for(int j=0;j<slots;j++)
				{
					int dur=s.nextInt();
					String dayPref=s.next();
					String timePref=s.next();
					slot S=new slot(dur,dayPref,timePref,C[i],j);
					att.addSlot(S);
					C[i].addSlot(S);
				}
			}
			att.allocate();
			int q=s.nextInt();
			PriorityQueue<prioritizedSlot> sug=new PriorityQueue<prioritizedSlot>();
			for(int qi=0;qi<q;qi++)
			{
				String code,day,time;int slotNo,priority;
				code=s.next();slotNo=s.nextInt();priority=s.nextInt();day=s.next();time=s.next();
				slot sl=att.searchSlot(code,slotNo-1);
				sl.setDayPref(day);sl.setTimePref(time);
				sug.add(new prioritizedSlot(sl,priority));
			}
			while(!sug.isEmpty())
			{
				prioritizedSlot sugi=sug.peek();sug.remove();
				att.replaceSlot(sugi.getSlot());
			}
			att.print();
		}
		s.close();
	}
}
class faculty
{
	private String name;
	private timeTable t;
	faculty(String name) 
	{
		this.name=name;t=new timeTable();
	}
	public timeTable getTimeTable()
	{
		return t;
	}
	public String getName()
	{
		return name;
	}
}

class course
{
	private ArrayList<slot> s;
	private String id, name;
	private faculty fac;
	private int priority;
	private String batch;
	course(String id, String name, faculty fac, int priority,String batch)
	{
		this.id=id;this.name=name;this.fac=fac;this.priority=priority;this.batch=batch;
		s=new ArrayList<slot>();
	}
	void addSlot(slot s)
	{
		this.s.add(s);
	}
	public int getPriority()
	{
		return priority;
	}
	public faculty getFaculty()
	{
		return fac;
	}
	public String getID()
	{
		return id;
	}
	public String getBatch()
	{
		return batch;
	}
	public String toString()
	{
		return ""+id+" "+name+" "+fac.getName();
	}
}
class slot implements Comparable<slot>
{
	private int duration;
	private int dayPreference[];
	private int timePreference[];
	private course c;
	private int slotNo;
	slot(int duration, String dayPreference,String timePreference,course c,int slotNo)
	{
		this.duration=duration;
		this.c=c;
		this.slotNo=slotNo;
		setDayPref(dayPreference);
		setTimePref(timePreference);
	}
	public void setDayPref(String dayPreference)
	{
		if(!dayPreference.equals("NIL")) this.dayPreference=new int[dayPreference.length()];
		else this.dayPreference=new int[0];
		for(int i=0;i<this.dayPreference.length;i++)
		{
			this.dayPreference[i]=(dayPreference.charAt(i)-'0')-1;
		}
	}
	public course getCourse()
	{
		return c;
	}
	public void setTimePref(String timePreference)
	{
		if(!timePreference.equals("NIL")) this.timePreference=new int[timePreference.length()/4];
		else this.timePreference=new int[0];
		for(int i=0;i<this.timePreference.length;i++)
		{
			String t=""+timePreference.charAt(i*4)+timePreference.charAt(i*4+1)+timePreference.charAt(i*4+2)+timePreference.charAt(i*4+3);
			if(t.equals("0900")) this.timePreference[i]=Time.T0900;
			else if(t.equals("1000")) this.timePreference[i]=Time.T1000;
			else if(t.equals("1115")) this.timePreference[i]=Time.T1115;
			else if(t.equals("1215")) this.timePreference[i]=Time.T1215;
			else if(t.equals("0300")) this.timePreference[i]=Time.T0300;
			else if(t.equals("0400")) this.timePreference[i]=Time.T0400;
			else if(t.equals("0500")) this.timePreference[i]=Time.T0500;
		}
	}
	public int compareTo(slot s)
	{
		if(slotNo<s.slotNo || slotNo==s.slotNo && c.getPriority()<s.c.getPriority()) return -1;
		if(slotNo==s.slotNo && c.getPriority()==s.c.getPriority()) return 0;
		return 1;
	}
	public boolean equals(slot s)
	{
		return c.getID().equals(s.c.getID()) && slotNo==s.slotNo;
	}
	public int getDuration() 
	{
		return duration;
	}
	public int timePreferenceSize()
	{
		return timePreference.length;
	}
	public int getTimePreference(int i)
	{
		return timePreference[i];
	}
	public int dayPreferenceSize()
	{
		return dayPreference.length;
	}
	public int getDayPreference(int i)
	{
		return dayPreference[i];
	}
	public int getSlotNo()
	{
		return slotNo;
	}
}

class timeTable
{
	private slot C[][];
	private ArrayList<slot> sat;
	public timeTable()
	{
		C=new slot[5][7];sat=new ArrayList<slot>();
	}
	int getFilledOnDay(int day)
	{
		int s=0;
		for(int i=0;i<7;i++) 
			if(C[day][i]!=null) 
				s++;
		return s;
	}
	void assignSlot(int day,int time, slot S)
	{
		if(S.getDuration()==1) C[day][time]=S;
		else if(S.getDuration()==2) {C[day][time]=S;C[day][time+1]=S;}
		else if(S.getDuration()==3) {C[day][time]=S;C[day][time+1]=S;C[day][time+2]=S;}
	}
	boolean checkFeasibleSlot(int day,int time, slot S)
	{
		if(S.getDuration()==1 && C[day][time]==null) return true;
		else if(S.getDuration()==2 && (time==Time.T0900 || time==Time.T1115 || time==Time.T0300 || time==Time.T0400) && C[day][time]==null && C[day][time+1]==null) return true;
		else if(S.getDuration()==3 && time==Time.T0300 && C[day][time]==null && C[day][time+1]==null && C[day][time+2]==null) return true;
		return false;
	}
	void addSat(slot S)
	{
		sat.add(S);
	}
	void deleteSlot(slot s)
	{
		for(int i=0;i<5;i++)
			for(int j=0;j<7;j++)
				if(C[i][j]!=null && C[i][j].equals(s))
					C[i][j]=null;
		while(sat.indexOf(s)>0) sat.remove(s);
	}
	void print()
	{
		String W[]= {"Monday","Tuesday","Wednesday","Thursday","Friday"};
		String T[][]={{"9:00-10:00","10:00-11:00","11:15-12:15","12:15-1:15","3:00-4:00","4:00-5:00","5:00-6:00"},
		 {"9:00-11:00","9:00-11:00","11:15-1:15","11:15-1:15","3:00-5:00","4:00-6:00","4:00-6:00"},
		{"9:00-10:00","10:00-11:00","11:15-12:15","12:15-1:15","3:00-6:00","3:00-6:00","3:00-6:00"}};
		for(int i=0;i<5;i++)
		{
			System.out.println(W[i]);
			for(int j=0;j<7;j++)
			{
				if(C[i][j]!=null && (j==0 || C[i][j]!=C[i][j-1])) System.out.println(T[C[i][j].getDuration()-1][j]+" "+C[i][j].getCourse());
			}
		}
		System.out.println("Saturday");
		{
			sat.sort(new sortSat());
		}
		for(int i=0;i<sat.size();i++)
		{
			if(i==0 || sat.get(i)!=sat.get(i-1))
			System.out.println(sat.get(i).getCourse());
		}
	}
}
class day implements Comparable<day> 
{
	private int dayNo;
	private int priority;
	day(int dayNo,int priority) 
	{
		this.dayNo=dayNo;this.priority=priority;
	}
	public int compareTo(day d)
	{
		if(priority<d.priority || priority==d.priority && dayNo<d.dayNo) return -1;
		if(priority==d.priority && dayNo==d.dayNo) return 0;
		return 1;
	}
	public int getDayNo()
	{
		return dayNo;
	}
	public int getPriority()
	{
		return priority;
	}
}

final class Time
{
	public static final int T0900=0;
	public static final int T1000=1;
	public static final int T1115=2;
	public static final int T1215=3;
	public static final int T0300=4;
	public static final int T0400=5;
	public static final int T0500=6;
}
class TimeTablePopulator
{
	HashMap<String,timeTable> allTT;
	ArrayList<faculty> fac;
	ArrayList<String> batches;
	ArrayList<slot> slots;
	TimeTablePopulator()
	{
		allTT=new HashMap<String,timeTable>();
		fac=new ArrayList<faculty>();
		slots=new ArrayList<slot>();
		batches=new ArrayList<String>();
	}
	void addBatch(String batch)
	{
		allTT.put(batch, new timeTable());
		batches.add(batch);
	}
	void addFaculty(faculty f)
	{
		fac.add(f);
	}
	void addSlot(slot S)
	{
		slots.add(S);
	}
	void replaceSlot(slot s)
	{
		timeTable T=allTT.get(s.getCourse().getBatch());
		T.deleteSlot(s);
		s.getCourse().getFaculty().getTimeTable().deleteSlot(s);
		place(s);
	}
	public faculty searchFaculty(String facn)
	{
		for(faculty f:fac) 
			if(f.getName().equals(facn)) return f;
		return null;
	}
	void allocate()
	{
		PriorityQueue<slot> pq=new PriorityQueue<slot>();
		for(slot s:slots) pq.add(s);
		while(!pq.isEmpty())
		{
			slot S=pq.peek();pq.remove();
			place(S);
		}
	}
	void print()
	{
		for(int i=0;i<batches.size();i++)
		{
			System.out.println(batches.get(i));
			allTT.get(batches.get(i)).print();
		}
	}
	slot searchSlot(String courseID, int slotNo)
	{
		for(slot s:slots)
		{
			if(s.getCourse().getID().equals(courseID) && s.getSlotNo()==slotNo) return s;
		}
		return null;
	}
	void place(slot S)
	{
		timeTable T=allTT.get(S.getCourse().getBatch());
		boolean done=false;
		for(int i=0;i<S.timePreferenceSize();i++)
		{
			int si=S.getTimePreference(i);
			boolean dayMap[]= {false, false, false, false, false};
			for(int j=0;j<S.dayPreferenceSize();j++)
			{
				dayMap[S.getDayPreference(j)]=true;
				int daypref=S.getDayPreference(j),timepref=S.getTimePreference(i);
				if(T.checkFeasibleSlot(daypref,timepref, S) && S.getCourse().getFaculty().getTimeTable().checkFeasibleSlot(daypref,timepref,S))
				{
					T.assignSlot(daypref,timepref,S);
					S.getCourse().getFaculty().getTimeTable().assignSlot(daypref,timepref,S);
					done=true;break;
				}
			}
			if(done) break;
			PriorityQueue<day> pd=new PriorityQueue<day>();
			for(int j=0;j<5;j++)
			{
				if(!dayMap[j])
				{
					day d=new day(j,T.getFilledOnDay(j));
					pd.add(new day(j,T.getFilledOnDay(j)));
				}
			}
			while(!pd.isEmpty())
			{
				day d=pd.peek();pd.remove();
				int daypref=d.getDayNo(),timepref=S.getTimePreference(i);
				if(T.checkFeasibleSlot(daypref,timepref, S) && S.getCourse().getFaculty().getTimeTable().checkFeasibleSlot(daypref,timepref,S))
				{
					T.assignSlot(daypref,timepref,S);
					S.getCourse().getFaculty().getTimeTable().assignSlot(daypref,timepref,S);
					done=true;break;
				}
			}
			if(done) break;
		}
		if(done) return;
		
		for(int j=0;j<S.dayPreferenceSize();j++)
		{
			for(int i=0;i<7;i++)
			{
				int daypref=S.getDayPreference(j),timepref=i;
				if(T.checkFeasibleSlot(daypref,timepref, S) && S.getCourse().getFaculty().getTimeTable().checkFeasibleSlot(daypref,timepref,S))
				{
					T.assignSlot(daypref,timepref,S);
					S.getCourse().getFaculty().getTimeTable().assignSlot(daypref,timepref,S);
					done=true;break;
				}
			}
			if(done) break;
		}
		if(done) return;
		
		PriorityQueue<day> pd=new PriorityQueue<day>();
		for(int j=0;j<5;j++) pd.add(new day(j,T.getFilledOnDay(j)));
		while(!pd.isEmpty())
		{
			day d=pd.peek();pd.remove();
			for(int i=0;i<7;i++)
			{
				int daypref=d.getDayNo(),timepref=i;
				if(T.checkFeasibleSlot(daypref,timepref, S) && S.getCourse().getFaculty().getTimeTable().checkFeasibleSlot(daypref,timepref,S))
				{
					T.assignSlot(daypref,timepref,S);
					S.getCourse().getFaculty().getTimeTable().assignSlot(daypref,timepref,S);
					done=true;break;
				}
			}
			if(done) break;
		}
		if(done) return;
		T.addSat(S);
		S.getCourse().getFaculty().getTimeTable().addSat(S);
	}
}
class prioritizedSlot implements Comparable<prioritizedSlot>
{
	private slot s;
	private int priority;
	prioritizedSlot(slot s,int priority)
	{
		this.s=s;
		this.priority=priority;
	}
	public int compareTo(prioritizedSlot s2)
	{
		return priority-s2.priority;
	}
	public slot getSlot()
	{
		return s;
	}
}
class sortSat implements Comparator<slot> 
{
	public int compare(slot a, slot b) {return a.getCourse().getID().compareTo(b.getCourse().getID());}
}
