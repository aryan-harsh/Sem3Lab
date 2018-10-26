/* IMPORTANT: Multiple classes and nested static classes are supported */

// uncomment this if you want to read input.
//imports for BufferedReader
import java.io.BufferedReader;
import java.io.InputStreamReader;

//import for Scanner and other utility classes
import java.util.*;
import java.io.*;

class student
{
	private String name;
	private String rollno;

	student(String nam,String roll)
	{
		this.name=nam;
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
}


// Warning: Printing unwanted or ill-formatted data to output will cause the test cases to fail

class ques1 {
    public static void main(String args[] ) throws Exception {
        

        // //Scanner
        // Scanner s = new Scanner(System.in);
        // String name = s.next();                 // Reading input from STDIN
        // System.out.println("Hi, " + name + ".");    // Writing output to STDOUT


        // Write your code here
        
        Scanner sa = new Scanner(System.in);
        int n = sa.nextInt();
        student s[] = new student[n];
        
        for(int i=0;i<n;i++)
        {
            
            String roll= sa.next();
            String nam = sa.next();
            s[i]= new student(nam,roll);  
        }
        String na;
        String r;
        for(int i=0;i<n;i++)
        {
            na=s[i].getname();
            r=s[i].getrol();
            System.out.println (r+" "+na);
        }

    }
}



