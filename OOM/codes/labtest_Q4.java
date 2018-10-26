package lab.test;
import java.util.*;
public class Q4 
{
	public static void main(String args[])
	{
		Scanner scan=new Scanner(System.in);
		int t=scan.nextInt();
		while(t-->0)
		{
			int n=scan.nextInt();
			festival f=new festival();
			while(n-->0)
			{
				String id=scan.next(),name=scan.next(),desc=scan.next();
				int day=scan.nextInt();String start=scan.next();int duration=scan.nextInt();
				String type=scan.next();
				if(type.equals("general")) f.addEvent(new event(id,name,desc,day,start,duration));
				else if(type.equals("course"))
				{
					course C=new course(id,name,desc,day,start,duration);
					int c=scan.nextInt();
					while(c-->0) C.addCourse(scan.next());
					f.addEvent(C);
				}
				else if(type.equals("level"))
				{
					level L=new level(id,name,desc,day,start,duration);
					int l=scan.nextInt();
					while(l-->0) L.addLevel(scan.next());
					f.addEvent(L);
				}
			}
			int s=scan.nextInt();
			while(s-->0)
			{
				String roll=scan.next(),name=scan.next(),level=scan.next(),course=scan.next();
				f.addStudent(new student(roll,name,level,course));
			}
			int r=scan.nextInt();
			while(r-->0)
			{
				String roll=scan.next(),ev=scan.next();
				f.register(roll,ev);
			}
			f.printSchedule();
			
			int q=scan.nextInt();
			while(q-->0)
			{
				String roll=scan.next();
				f.searchStudent(roll).printSchedule();
			}
		}
		scan.close();
	}
}
class student implements Comparable<student>
{
	private String roll;
	private String name;
	private String level;
	private String course;
	ArrayList<event> events;
	student(String roll,String name,String level,String course)
	{
		this.roll=roll;this.name=name;this.level=level;this.course=course;
		events=new ArrayList<event>();
	}
	public String getRoll()
	{
		return roll;
	}
	public String getLevel()
	{
		return level;
	}
	public String getCourse()
	{
		return course;
	}
	public int compareTo(student s) 
	{
		return roll.compareTo(s.roll);
	}
	void addEvent(event e) 
	{
		events.add(e);
	}
	public boolean tryRegister(event e)
	{
		for(event ei:events)
		{
			int endi=ei.getStartTimeInt()+ei.getDuration()*100;
			int end=e.getStartTimeInt()+e.getDuration()*100;
			if(!(ei.getStartTimeInt()<=e.getStartTimeInt() && endi<=e.getStartTimeInt() || ei.getStartTimeInt()>=end && endi>=end)) return false;
		}
		return true;
	}
	void printSchedule()
	{
		System.out.println(toString());
		Collections.sort(events);
		int pd2=-1;
		for(event e:events)
		{
			if(e.getDay()!=pd2) {pd2=e.getDay();System.out.println("Day "+e.getDay());}
			e.printDetailsWithoutRegistration();
		}
	}
	public String toString()
	{
		return ""+roll+" "+name+" "+level+" "+course;
	}
}
class event implements Comparable<event>
{
	private String id;
	private String name;
	private String description;
	private String startTime;
	private int day;
	private int duration;
	ArrayList<student> registrations;
	event(String id, String name, String description,int day, String start,int duration)
	{
		this.id=id;this.name=name;this.description=description;
		this.day=day;this.startTime=start;this.duration=duration;
		registrations=new ArrayList<student>();
	}
	public String getID()
	{
		return id;
	}
	public int getStartTimeInt()
	{
		return Integer.parseInt(""+startTime.charAt(0)+startTime.charAt(1)+startTime.charAt(3)+startTime.charAt(4));
	}
	public int getDuration()
	{
		return duration;
	}
	public int getDay()
	{
		return day;
	}
	public int compareTo(event e)
	{
		if(day<e.day || day==e.day && getStartTimeInt()<e.getStartTimeInt()) return -1;
		if(day==e.day && getStartTimeInt()==e.getStartTimeInt()) return id.compareTo(e.id);
		return 1;
	}
	public void addRegistration(student s) 
	{
		registrations.add(s);
	}
	public boolean tryRegistration(student s)
	{
		return true;
	}
	public void printDetails()
	{
		System.out.println(startTime+"("+duration+") "+id+" "+name+" "+description);
		Collections.sort(registrations);
		for(student si:registrations) 
			System.out.println(si);
	}
	public void printDetailsWithoutRegistration()
	{
		System.out.println(startTime+"("+duration+") "+id+" "+name+" "+description);
		Collections.sort(registrations);
	}
}
class course extends event
{
	private ArrayList<String> courses;
	course(String id, String name, String desciption,int day, String start,int duration)
	{
		super(id,name,desciption,day,start,duration);
		courses=new ArrayList<String>();
	}
	public void addCourse(String name) 
	{
		courses.add(name);
	}
	public boolean tryRegistration(student s)
	{
		for(String c:courses) 
			if(c.equals(s.getCourse())) 
				return true;
		return false;
	}
}
class level extends event
{
	private ArrayList<String> levels;
	level(String id, String name, String description,int day, String start,int duration)
	{
		super(id,name,description,day,start,duration);
		levels=new ArrayList<String>();
	}
	public void addLevel(String name) 
	{
		levels.add(name);
	}
	public boolean tryRegistration(student s)
	{
		for(String l:levels) 
			if(l.equals(s.getLevel())) 
				return true;
		return false;
	}
}

class festival
{
	private ArrayList<event> E;
	private ArrayList<student> S;
	festival()
	{
		E=new ArrayList<event>();
		S=new ArrayList<student>();
	}
	public void addEvent(event e)
	{
		E.add(e);
	}
	public void addStudent(student s)
	{
		S.add(s);
	}
	public student searchStudent(String roll)
	{
		for(student si:S) 
			if(si.getRoll().equals(roll))
				return si;
		return null;
	}
	public event searchEvent(String eid)
	{
		for(event ei:E) 
			if(ei.getID().equals(eid))
				return ei;
		return null;
	}
	void register(String roll,String eid)
	{
		event ei=searchEvent(eid);
		student si=searchStudent(roll);
		if(si.tryRegister(ei) && ei.tryRegistration(si))
		{
			ei.addRegistration(si);si.addEvent(ei);
		}
	}
	void printSchedule()
	{
		Collections.sort(E);
		int pd=-1;
		for(event e:E)
		{
			if(e.getDay()!=pd) {pd=e.getDay();System.out.println("Day "+e.getDay());}
			e.printDetails();
		}
	}
}
