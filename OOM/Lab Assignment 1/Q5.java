import java.util.*;
import java.io.*;


class Assignment
{
    private String name;
    private int marks;
    
    Assignment(String name,int marks)
    {
        this.name=name;
        this.marks=marks;
    }
    public void setnameA(String name)
    {
        this.name=name;
    } 
    public void setmarksA(int marks)
    {
        this.marks=marks;
    } 
    public String getnameA()
    {
        return this.name;
    }
    public int getmarksA()
    {
        return this.marks;
    } 
}



class student
{
    private String name;
    private String rollno;
    private ArrayList<Assignment> AssignmentList=new ArrayList<Assignment>();
    
    student(String nam,String roll)
    {
        this.name=nam;
        this.rollno=roll;
    }

    public void setname(String name)
    {
        this.name=name;
    } 

    public void setrol(String roll)
    {
        this.rollno=roll;
    } 

    public String getname()
    {
        return this.name;
    } 


    public String getrol()
    {
        return this.rollno;
    } 

    public void addAssignment(String name,int marks)
    {
        Assignment a1=new Assignment(name,marks);
        AssignmentList.add(a1);
    }

    
    
    public ArrayList<Assignment> returnList()
    {
        return AssignmentList;
    }

    public int getAssignmentArraySize()
    {
        int ret= AssignmentList.size();
        return ret;
    }

    public void addtoAssignmentList(Assignment a)
    {
        AssignmentList.add(a);
    }
}

class Group{
    private int size;
    private student s[];
    private int a;
    private int i;
    
    Group(int size,int a)
    {
        this.a=a;
        i=0;
        this.size=size;
        s= new student[size];
    }

    public void add(String roll,String name)
    {
        s[i]=new student(name,roll);
        i++;
    }

    public String getstudentroll(int j)
    {
        String n= s[j].getrol();
        return n;
    }

     public String getstudentname(int j)
    {
        String n= s[j].getname();
        return n;
    }

    public ArrayList<Assignment> returnAssignmentList(int i)
    {
        return s[i].returnList();
    }

    public int getAssignmentSize(int i)
    {
        int ret= s[i].getAssignmentArraySize();
        return ret;
    }

    public void addAssignmentA(int i,String name,int marks)
    {
        Assignment a1=new Assignment(name,marks);
        s[i].addtoAssignmentList(a1);
    }

    public void Sort()
    {
        int n=size;
        String rol1;
        String rol2;
        String nam1;
        String nam2;
        
        for(int i=0;i<n-1;i++)
        {
            
            for(int j=0;j<n-i-1;j++)
            {
                rol1=s[j].getrol();
                nam1=s[j].getname();
                rol2=s[j+1].getrol();
                nam2=s[j+1].getname();
                if(rol1.compareTo(rol2)>0)
                {
                    
                     s[j].setrol(rol2);
                     s[j].setname(nam2);
                     s[j+1].setname(nam1);
                     s[j+1].setrol(rol1);
                }
                
            }
            
        }
    }

    public int findIndex(String roll)
    {
        int thisisit=-1;
        for(i=0;i<size;i++)
        {
            String rno;
            rno=s[i].getrol();
            if(rno.compareTo(roll)==0)
                thisisit=i;
        }
    return thisisit;

    }
}


class Q5 {
    public static void main(String args[] ) throws Exception {
        
        Scanner sa = new Scanner(System.in);
        int testcase = Integer.parseInt(sa.next());
        
        for(int b=0;b<testcase;b++)
        {
            int n = Integer.parseInt(sa.next());
            int a = Integer.parseInt(sa.next());
            Group G = new Group(n,a); 
            for(int i=0;i<n;i++)
            {
                String roll= sa.next();
                String nam = sa.next();
                G.add(roll,nam);
            }

            G.Sort();

            for(int i=0;i<a;i++)
            {
                String subject= sa.next();
                int jn = Integer.parseInt(sa.next());
                for(int j=0;j<jn;j++)
                {
                    String rol = sa.next();
                    int index=G.findIndex(rol);
                    int marks = Integer.parseInt(sa.next());
                    G.addAssignmentA(index,subject,marks);
                }
            }

            

            String na;
            String r;
            String anam;
            int assignmentsize;
            int mark;
            int sum;
            Assignment a1;
            ArrayList<Assignment> arr;

            for(int i=0;i<n;i++)
            {
                sum=0;
                na=G.getstudentname(i);
                r=G.getstudentroll(i);
                System.out.print(r+" "+na);
                assignmentsize=G.getAssignmentSize(i);
                arr=G.returnAssignmentList(i);
                
                 a1=arr.get(0);
                    mark=a1.getmarksA();
                    sum+=mark;
                    anam=a1.getnameA();
                    System.out.print(" "+mark+" "+anam);
                for(int k=1;k<assignmentsize;k++)
                {
                    a1=arr.get(k);
                    mark=a1.getmarksA();
                    sum+=mark;
                    anam=a1.getnameA();
                    System.out.print(" + "+mark+" "+anam);
                }


                System.out.print(" = "+sum);
                System.out.println("");
            }

            


            
        }
    }
}
