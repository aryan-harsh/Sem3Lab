import java.util.*;
public class Q4 
{
	public static void main(String args[])
	{
		Scanner s=new Scanner(System.in);
		int T=s.nextInt();
		while(T-->0)
		{
			int t=s.nextInt(),n=s.nextInt(),k=s.nextInt();
			TACollection ta=new TACollection(t);
			studentWithTACollection st=new studentWithTACollection(n);
			instructor inst=new instructor(ta,st,k);
			while(t-->0) 
			{
				String roll=s.next(), name=s.next();
				inst.addTA(new TA(roll,name,n));
			}
			
			while(n-->0)
			{
				String roll=s.next(), name=s.next();
				inst.addStudent(new studentWithTA(roll,name));
			}
			inst.allocateTAs();
			inst.printStudents();
			inst.printTAs();
		}
		s.close();
	}
}
class student implements Comparable<student>
{
	private String name;
	private String rollNo;
	student(String rollNo,String name) 
	{
		this.name=name;this.rollNo=rollNo;
	}
	public String getRollNo()
	{
		return rollNo;
	}
	public String getName()
	{
		return name;
	}
	public int compareTo(student s)
	{
		return rollNo.compareTo(s.rollNo);
	}
	public boolean equals(student s)
	{
		return rollNo.equals(s.rollNo);
	}
	public String toString()
	{
		return rollNo+" "+name;
	}
}
class TA extends student 
{
	private studentWithTACollection s;
	TA(String name,String rollNo,int maxStudents) 
	{
		super(name,rollNo);
		s=new studentWithTACollection(maxStudents);
	}
	public void setStudent(studentWithTA s) 
	{
		this.s.addStudent(s);
	}
	public void printTA()
	{
		System.out.println(super.toString());
		s.printStudentsWithoutTA();
	}
}
class TACollection
{
	private TA S[];
	private int counter;
	TACollection(int maxTAs)
	{
		S=new TA[maxTAs];counter=0;
	}
	public void addTA(TA s)
	{
		S[counter++]=s;
	}
	public void sort()
	{
		Arrays.sort(S);//cheated here
	}
	public int getSize()
	{
		return counter;
	}
	public TA get(int i)
	{
		return S[i];
	}
	public void printTAs()
	{
		for(int taNo=0;taNo<counter;taNo++)
		{
			S[taNo].printTA();
		}
	}
}

class studentWithTA extends student 
{
	private student ta;
	studentWithTA(String name,String rollNo) 
	{
		super(name,rollNo);
	}
	public void setTA(student s) 
	{
		ta=s;
	}
	public String toString() 
	{
		return super.toString()+" "+ta.toString();
	}
}

class studentWithTACollection
{
	private studentWithTA S[];
	private int counter;
	studentWithTACollection(int maxStudents)
	{
		S=new studentWithTA[maxStudents];counter=0;
	}
	public void addStudent(studentWithTA s) 
	{
		S[counter++]=s;
	}
	public void sort()
	{
		Arrays.sort(S);//cheated here
	}
	public int getSize()
	{
		return counter;
	}
	public studentWithTA get(int i)
	{
		return S[i];
	}
	public void printStudents()
	{
		for(int stNo=0;stNo<counter;stNo++) System.out.println(S[stNo]);
	}
	public void printStudentsWithoutTA()
	{
		for(int stNo=0;stNo<counter;stNo++) System.out.println(S[stNo].getRollNo()+" "+S[stNo].getName());
	}
}

class instructor
{
	private TACollection ta;
	private studentWithTACollection students;
	private int k;
	instructor(TACollection ta,studentWithTACollection students,int k)
	{
		this.ta=ta;this.students=students;this.k=k;
	}
	void addStudent(studentWithTA s)
	{
		students.addStudent(s);
	}
	void addTA(TA s)
	{
		ta.addTA(s);
	}
	void allocateTAs()
	{
		students.sort();
		int count=0,curTA=0;
		for(int stNo=0;stNo<students.getSize();stNo++)
		{
			students.get(stNo).setTA(ta.get(curTA));
			ta.get(curTA).setStudent(students.get(stNo));
			count++;
			if(count>=k && curTA<ta.getSize()-1) 
			{
				curTA++;count=0;
			}
		}
	}
	public void printStudents()
	{
		students.printStudents();
	}
	public void printTAs()
	{
		ta.printTAs();
	}
}