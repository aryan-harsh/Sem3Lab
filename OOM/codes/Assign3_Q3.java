import java.util.*;
public class Q3 
{
	public static void main(String args[])
	{
		Scanner scan=new Scanner(System.in);
		int T=scan.nextInt();
		while(T-->0)
		{
			int q=scan.nextInt(),n=scan.nextInt(),t=scan.nextInt(),k=scan.nextInt();
			instructor inst=new instructor(t);
			for(int i=0;i<q;i++) 
			{
				int id=scan.nextInt();
				String text=scan.next();
				inst.addQuestion(new question(id,text));
			}
			for(int i=0;i<n;i++) inst.addStudent(new student(scan.next()));
			for(int i=0;i<t;i++)
			{
				String name=scan.next(),type1=scan.next(),type2=scan.next();
				affinityFun af=null;
				if(type2.equals("CL")) af=new closenessLoving();
				else if(type2.equals("BR")) af=new bestRollNumber();
				else if(type2.equals("LU")) af=new lucky(scan.nextInt());
				
				if(type1.equals("G")) inst.addTA(new Greedy(name,af,k));
				else if(type1.equals("SW")) inst.addTA(new StudentWise(name,af,k));
				else if(type1.equals("QW")) inst.addTA(new QuestionWise(name,af,k));
			}
			inst.assignTAs();
			inst.assignQuestions();
			
			for(int i=0;i<n;i++)
			{
				String roll=scan.next();
				inst.searchStudentByRollNo(roll).print();
			}
		}
		scan.close();
	}
}
class question implements Comparable<question>
{
	int ID;
	String text;
	question(int ID,String text) 
	{
		this.ID=ID;this.text=text;
	}
	public int getID()
	{
		return ID;
	}
	public int compareTo(question q) 
	{
		return ID-q.ID;
	}
	public String toString()
	{
		return text;
	}
}
class student implements Comparable<student>
{
	String roll;
	ArrayList<question> qp;
	student(String roll) 
	{
		this.roll=roll;qp=new ArrayList<question>();
	}
	public int compareTo(student s) 
	{
		return roll.compareTo(s.roll);
	}
	public int getQPSize()
	{
		return qp.size();
	}
	public String getRollNo()
	{
		return roll;
	}
	public void addQuestion(question q)
	{
		qp.add(q);
	}
	public int getIntegerPart()
	{
		String s1="";
		for(int i=3;i<roll.length();i++) 
			s1+=roll.charAt(i);
		return Integer.parseInt(s1);
	}
	void print()
	{
		System.out.println(roll);
		for(question q:qp) System.out.println(q);
	}
}
class questionStudentAssignment implements Comparable<questionStudentAssignment>
{
	private question q;
	private student s;
	private affinityFun af;
	questionStudentAssignment(question q, student s,affinityFun af) 
	{
		this.q=q;this.s=s;this.af=af;
	}
	public int compareTo(questionStudentAssignment assign)
	{
		if(af.affinity(q,s)<assign.af.affinity(assign.q,assign.s) ||
				af.affinity(q,s)==assign.af.affinity(assign.q,assign.s) && q.getID()<assign.q.getID() ||
						af.affinity(q,s)==assign.af.affinity(assign.q,assign.s) && q.getID()==assign.q.getID() && s.getRollNo().compareTo(assign.s.getRollNo())<0
						)
			return -1;
		else if(af.affinity(q,s)==assign.af.affinity(assign.q,assign.s) && q.getID()==assign.q.getID() && s.getRollNo().compareTo(assign.s.getRollNo())==0) 
			return 0;
		return 1;
	}
	public question getQuestion()
	{
		return q;
	}
	public student getStudent()
	{
		return s;
	}
}

abstract class TA
{
	private String name;
	private ArrayList<question> Q;
	private ArrayList<student> S;
	private affinityFun af;
	private int k;
	TA(String name,affinityFun af,int k) 
	{
		this.name=name;this.Q=Q;S=new ArrayList<student>();this.af=af;this.k=k;
	}
	void setQuestions(ArrayList<question> Q)
	{
		this.Q=Q;
	}
	void addStudent(student s) 
	{
		S.add(s);
	}
	public ArrayList<question> getQuestions()
	{
		return Q;
	}
	public ArrayList<student> getStudents()
	{
		return S;
	}
	public affinityFun getAffinityFun()
	{
		return af;
	}
	public int getK()
	{
		return k;
	}
	public String getName()
	{
		return name;
	}
	public abstract boolean assign();
}
class Greedy extends TA
{
	Greedy(String name,affinityFun af,int k) 
	{
		super(name,af,k);
	}
	public boolean assign()
	{
		questionStudentAssignment best=null;
		for(question q:getQuestions())
			for(student s:getStudents())
			{
				if(s.getQPSize()<getK() && (best==null || (new questionStudentAssignment(q,s,getAffinityFun())).compareTo(best)<0))
					best=new questionStudentAssignment(q,s,getAffinityFun());
			}
		if(best==null) return false;
		best.getStudent().addQuestion(best.getQuestion());
		this.getQuestions().remove(best.getQuestion());
		return true;
	}
}
class StudentWise extends TA
{
	StudentWise(String name,affinityFun af,int k) 
	{
		super(name,af,k);
	}
	public boolean assign()
	{
		Collections.sort(getStudents());
		questionStudentAssignment best=null;
		for(student s:getStudents())
		{
			for(question q:getQuestions())
			{
				if(s.getQPSize()<getK() && (best==null || (new questionStudentAssignment(q,s,getAffinityFun())).compareTo(best)<0))
					best=new questionStudentAssignment(q,s,getAffinityFun());
			}
			if(best!=null) break;
		}
		if(best==null) return false;
		best.getStudent().addQuestion(best.getQuestion());
		this.getQuestions().remove(best.getQuestion());
		return true;
	}
}
class QuestionWise extends TA
{
	QuestionWise(String name,affinityFun af,int k) 
	{
		super(name,af,k);
	}
	public boolean assign()
	{
		Collections.sort(getQuestions());
		questionStudentAssignment best=null;
		for(question q:getQuestions())
		{
			for(student s:getStudents())
			{
				if(s.getQPSize()<getK() && (best==null || (new questionStudentAssignment(q,s,getAffinityFun())).compareTo(best)<0))
					best=new questionStudentAssignment(q,s,getAffinityFun());
			}
			if(best!=null) break;
		}
		if(best==null) return false;
		best.getStudent().addQuestion(best.getQuestion());
		this.getQuestions().remove(best.getQuestion());
		return true;
	}
}
interface affinityFun
{
	public int affinity(question q, student s);
}
class closenessLoving implements affinityFun
{
	public int affinity(question q, student s)
	{
		return Math.abs(s.getIntegerPart()-q.getID());
	}
}
class bestRollNumber implements affinityFun
{
	public int affinity(question q, student s)
	{
		return s.getIntegerPart();
	}
}
class lucky implements affinityFun
{
	private int l;
	lucky(int l) 
	{
		this.l=l;
	}
	public int affinity(question q,student s)
	{
		return Math.abs(s.getIntegerPart()+q.getID())%l;
	}
}

class instructor
{
	ArrayList<question> Q;
	ArrayList<student> studs;
	ArrayList<TA> ta;
	instructor(int t)
	{
		Q=new ArrayList<question>();
		studs=new ArrayList<student>();
		ta=new ArrayList<TA>();
	}
	void addQuestion(question q) 
	{
		Q.add(q);
	}
	void addStudent(student s)
	{
		studs.add(s);
	}
	void addTA(TA t)
	{
		ta.add(t);t.setQuestions(Q);
	}
	void assignTAs()
	{
		int ti=0;
		for(student si:studs)
		{
			ta.get(ti).addStudent(si);
			ti=(ti+1)%ta.size();
		}
	}
	void assignQuestions()
	{
		boolean done=false;
		while(!done)
		{
			done=true;
			for(TA ta1:ta)
			{
				if(ta1.assign()) done=false;
			}
		}
	}
	student searchStudentByRollNo(String roll)
	{
		for(student s:studs)
			if(s.getRollNo().equals(roll)) return s;
		return null;
	}
}
