import java.util.*;
public class Q5 
{
	public static void main(String args[])
	{ 
		Scanner s=new Scanner(System.in);
		int T=s.nextInt();
		while(T-->0)
		{
			int n=s.nextInt();int maxAssignments=s.nextInt();
			assignmentStudentCollection S=new assignmentStudentCollection(n);
			while(n-->0) 
			{
				String roll=s.next(), name=s.next();
				S.add(new assignmentStudent(roll,name,maxAssignments));
			}
			S.sort();
			for(int i2=0;i2<maxAssignments;i2++)
			{
				String assignmentName=s.next();
				assignment assign=new assignment(assignmentName);
				int noStudents=s.nextInt();
				while(noStudents-->0)
				{
					String roll=s.next();int marks=s.nextInt();
					S.search(roll).addAssignment(assign,marks);
				}
			}
			S.printStudents();
		}
		s.close();
	}
}
class assignmentStudent extends student
{
	private int counter;
	private attemptedAssignment assignments[];
	assignmentStudent(String rollNo,String name,int maxAssignments) 
	{
		super(rollNo,name);
		assignments=new attemptedAssignment[maxAssignments];
		counter=0;
	}
	public void addAssignment(assignment assign,int marksObtained) 
	{
		assignments[counter++]=new attemptedAssignment(assign,marksObtained);
	}
	public int getTotal()
	{
		int s=0;
		for(int i=0;i<counter;i++) s+=assignments[i].getMarksObtained();
		return s;
	}
	public void printStudent()
	{
		System.out.print(super.toString());
		for(int iter=0;iter<counter;iter++)
		{
			if(iter==0) System.out.print(" "+assignments[iter]);
			else System.out.print(" + "+assignments[iter]);
		}
		System.out.println(" = "+getTotal());
	}
}
class assignmentStudentCollection
{
	private assignmentStudent S[];
	private int counter;
	assignmentStudentCollection(int maxCapacity)
	{
		S=new assignmentStudent[maxCapacity];counter=0;
	}
	public void add(assignmentStudent s) {S[counter++]=s;}
	public void sort()
	{
		Arrays.sort(S);//Cheating here
	}
	public assignmentStudent search(String s)
	{
		for(assignmentStudent s1:S) 
			if(s1.getRollNo().equals(s)) return s1;
		return null;
	}
	public void printStudents()
	{
		for(assignmentStudent s1:S) 
		{
			s1.printStudent();
		}
	}
}
class assignment
{
	private String assignmentName;
	assignment(String assignmentName)
	{
		this.assignmentName=assignmentName;
	}
	public String toString()
	{
		return assignmentName;
	}
}
class attemptedAssignment
{
	private assignment assign;
	private int marksObtained;
	attemptedAssignment(assignment assign,int marksObtained)
	{
		this.assign=assign;
		this.marksObtained=marksObtained;
	}
	public int getMarksObtained()
	{
		return marksObtained;
	}
	public String toString()
	{
		return marksObtained+" "+assign;
	}
}
