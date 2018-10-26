import java.io.BufferedReader;
import java.io.InputStreamReader;

import java.util.*;
import java.*;

class Student
{
    private String roll,name,course,level;
    private ArrayList<Event> EventList;
    Student(String roll,String name,String course,String level)
    {
        this.roll=roll;
        this.name=name;
        this.course=course;
        this.level=level;
        EventList = new ArrayList<Event>();
    }

    public void AddEvent(Event e)
    {
        EventList.add(e);
    }

    public Event getEvent(int j)
    {
        Event eve= EventList.get(j);
        return eve;
    }

    public int getEventSize()
    {
        int eve= EventList.size();
        return eve;
    }


    public void sortEvents()
    {
        Event extra,e1,e2;
        int n=EventList.size();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n-i-1;j++)
            {

                if(EventList.get(j).getStartTime().compareTo(EventList.get(j+1).getStartTime())>0)
                {
                    e1=EventList.get(j);
                    e2=EventList.get(j+1);
                    extra=e2;
                    e2=e1;
                    e1=extra;
                    EventList.set(j,e1);
                    EventList.set(j+1,e2);
                    
                }
                if(EventList.get(j).getStartTime().compareTo(EventList.get(j+1).getStartTime())==0)
                {
                    if(EventList.get(j).getid().compareTo(EventList.get(j+1).getid())>0)
                    {
                    e1=EventList.get(j);
                    e2=EventList.get(j+1);
                    extra=e2;
                    e2=e1;
                    e1=extra;
                    EventList.set(j,e1);
                    EventList.set(j+1,e2);
                    }
                }
            }
        }
    }

}

class Event
{
    private String id;
    private String name;
    private String desc;
    private int dayNum;
    private String startTime;
    private int duration;


    Event(String id,String name,String desc,int dayNum,String startTime,int duration)
    {
        this.id=id;
        this.name=name;
        this.desc=desc;
        this.dayNum=dayNum;
        this.startTime=startTime;
        this.duration=duration;
    }

    public String getStartTime()
    {
        return this.startTime;
    }

    public String getname()
    {
        return this.name;
    }

    public String getid()
    {
        return this.id;
    }

    public String getdesc()
    {
        return this.desc;
    }
    
    public int getdayNum()
    {
        return this.dayNum;
    }
    
    public int getduration()
    {
        return this.duration;
    }
    

}


class Day
{

    private int dayNo;
    private ArrayList<Event> EventList;

    Day(int dayNo)
    {
        this.dayNo=dayNo;
        EventList = new ArrayList<Event>();
    }

    public void AddEvent(Event e)
    {
        EventList.add(e);
    }

    public Event getEvent(int j)
    {
        Event eve= EventList.get(j);
        return eve;
    }

    public int getEventSize()
    {
        int eve= EventList.size();
        return eve;
    }


    public void sortEvents()
    {
        Event extra,e1,e2;
        int n=EventList.size();
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n-i-1;j++)
            {

                if(EventList.get(j).getStartTime().compareTo(EventList.get(j+1).getStartTime())>0)
                {
                    e1=EventList.get(j);
                    e2=EventList.get(j+1);
                    extra=e2;
                    e2=e1;
                    e1=extra;
                    EventList.set(j,e1);
                    EventList.set(j+1,e2);
                    
                }
                if(EventList.get(j).getStartTime().compareTo(EventList.get(j+1).getStartTime())==0)
                {
                    if(EventList.get(j).getid().compareTo(EventList.get(j+1).getid())>0)
                    {
                    e1=EventList.get(j);
                    e2=EventList.get(j+1);
                    extra=e2;
                    e2=e1;
                    e1=extra;
                    EventList.set(j,e1);
                    EventList.set(j+1,e2);
                    }
                }
            }
        }
    }

    public void print()
    {
        int n=EventList.size();
        String evetime,eveid,evename,evedesc;
        int evedur;
        for(int j=0;j<n;j++)
        {
            Event e=EventList.get(j);
            evetime=e.getStartTime();
            eveid=e.getid();
            evedur=e.getduration();
            evename=e.getname();
            evedesc=e.getdesc();
            System.out.println(evetime+"("+evedur+") "+eveid+" "+evename+" "+evedesc);
        }
        
    }


}





class Q2 
{
    public static void main(String args[] ) throws Exception 
    {
        Scanner sa = new Scanner(System.in);
        int t = Integer.parseInt(sa.next());
        for(int i=0;i<t;i++)
        {
            String eid,ename,edesc,estart;
            int nodays,eday,edur;
            int ne= Integer.parseInt(sa.next());
            
            Event[] EventArray = new Event[ne];
            nodays=0;

            for(int j=0;j<ne;j++)
            {
                eid=sa.next();
                ename=sa.next();
                edesc=sa.next();
                eday=Integer.parseInt(sa.next());
                estart=sa.next();
                edur=Integer.parseInt(sa.next());
                if(eday>nodays)
                    nodays=eday;
                EventArray[j]=new Event(eid,ename,edesc,eday,estart,edur);
            }
            
            Day[] DayArray =new Day[nodays];
        
            for(int j=0;j<nodays;j++)
            {
                DayArray[j]=new Day(j);
            }

            for(int j=0;j<ne;j++)
            {

                int d=EventArray[j].getdayNum();
                d--;
                DayArray[d].AddEvent(EventArray[j]);

            }

            for(int j=0;j<nodays;j++)
            {
                DayArray[j].sortEvents();

            }

            for(int j=0;j<nodays;j++)
            {

                int k=j+1;
                if(DayArray[j].getEventSize()!=0)
                System.out.println("Day "+k);
                DayArray[j].sortEvents();

                DayArray[j].print();
            }


        }

       
    }
}
