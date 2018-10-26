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
    public String getmarksA()
    {
        return this.marks;
    } 
}

class AssignmentGroup{
    private int size;
    private Assignment AssignmentAarray[];
    private int i;
    private int k;
    private int n;
    AssignmentGroup(int size,int n)
    {
        i=0;
        this.size=size;
        this.n=n;
        AssignmentAarray= new Assignment[size];
    }
    public void add(int marks,String name)
    {
        AssignmentAarray[i]=new Assignment(name,marks);
        i++;
    }

    public String getAssignmentmarks(int j)
    {
        int na= AssignmentAarray[j].getmarksA();
        return na;
    }

     public String getAssignmentname(int j)
    {
        String na= AssignmentAarray[j].getnameA();
        return na;
    }


    // public void AssociateStudent(int i)
    // {   
    //     i++;
    //     int start=(i-1)*k;
    //     int end=i*k;
    //     if(end>n)
    //         end=n;
    //     if(start<n && end<=n)
    //     {
    //     i--;
    //     for(int j=start;j<end;j++)
    //     {      
    //         AssignmentAarray[i].addStudent(j);
    //     }
    // }
    // }

    // public void AssociateStudentLast(int i)
    // {
    //     i++;
    //     int start=(i-1)*k;
    //     int end=n;
    //     if(start<=n && end<=n)
    //     {
    //     i--;
    //     for(int j=start;j<end;j++)
    //     {      
    //         AssignmentAarray[i].addStudent(j);
    //     }
    // }
    // }

    // public ArrayList<Integer> returnStudentList(int i)
    // {

        
    //     return AssignmentAarray[i].returnList();
    // }

    // public int getStudentSize(int i)
    // {
    //     return AssignmentAarray[i].getStudentArraysize();
    // }

}


class student
{
    private String name;
    private String rollno;
    private ArrayList<Integer> AssignmentList=new ArrayList<Integer>();
    
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

    public void addAssignment(int i)
    {
        Integer ii=new Integer(i);
        AssignmentList.add(ii);
    }
    public int getAssignmentArraySize()
    {
        int ret= this.AssignmentList.size();
        return ret;
    }
    
    public ArrayList<Integer> returnList()
    {
        return AssignmentList;
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

    public ArrayList<Integer> returnAssignmentList(int i)
    {
        return s[i].returnList();
    }

    public int getAssignmentSize(int i)
    {
        return s[i].getAssignmentArraysize();
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

    

}


class Q5 {
    public static void main(String args[] ) throws Exception {
        
        Scanner sa = new Scanner(System.in);
        int testcase = Integer.parseInt(sa.next());
        for(int b=0;b<testcase;b++)
        {
            int a = Integer.parseInt(sa.next());
            int n = Integer.parseInt(sa.next());
            AssignmentGroup A = new AssignmentGroup(a,n);
            Group G = new Group(n,a); 
            for(int i=0;i<n;i++)
            {
                
                String roll= sa.next();
                String nam = sa.next();
                G.add(roll,nam);
            }


            for(int i=0;i<a;i++)
            {
                
                int marks = Integer.parseInt(sa.next());
                String nam = sa.next();
                A.add(marks,nam);
            }


            G.Sort();
            String nam;
            String rol;
            String anam;
            int amarks;
            // int taid;
            int siz;
            // int data,j;

            ArrayList<Integer> arr;

            // for(int i=0;i<n;i++)
            // {
            //     G.AssociateTAid(i);
            // }
            
            for(int i=0;i<n;i++)
            {
                na=G.getstudentname(i);
                r=G.getstudentroll(i);
                taid=G.gettaid(i);
                tna=T.getTAname(taid);
                tr=T.getTAroll(taid);
                System.out.println(r+" "+na+" "+tr+" "+tna);
            }

            for(int i=0;i<t-1;i++)
            {
                T.AssociateStudent(i);
            }
            T.AssociateStudentLast(t-1);
            for(int i=0;i<t;i++)
            {
                tr=T.getTAroll(i);
                tna=T.getTAname(i);
                arr= T.returnStudentList(i);
                System.out.println(tr+" "+tna);
                siz=T.getStudentSize(i);
                for(data=0;data<siz;data++)
                {
                    j=arr.get(data);
                    na=G.getstudentname(j);
                    r=G.getstudentroll(j);
                    System.out.println(r+" "+na);
                }
                
                
            }

            
        }
    }
}
