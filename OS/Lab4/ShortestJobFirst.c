//SHORTEST JOB FIRST 

#include<bits/stdc++.h>

using namespace std;
int main()
{
	
	//we are assuming inputs to be already available to us for simplicity
	
	//this can be extended to the form as in question
	//by using file handling. we can declare a struct with all process details
	//and loop on until end of file reading one structure at a time and storing it.
	
    string p[]={"p1","p2","p3","p4"};
    int a[]={0,2,6,4};
    int b[]={20,15,27,36};
    
    int res[10]={0};
    int ct[10]={0};
    int n=4;
    int wt[10]={0},tat[10]={0};
    wt[0]=0;
    res[0]=0;
    
    //simple sorting processes 
    for(int i=0;i<n;i++)
    {
        for(int j=i;j<n;j++)
        {
            if(b[j]<b[i])
            {
                int temp=b[j];
                b[j]=b[i];
                b[i]=temp;
                  temp=a[j];
                a[j]=a[i];
                a[i]=temp;
                 string temp1=p[j];
                p[j]=p[i];
                p[i]=temp1;
            }
        }
    }
    
    //display order of execution, which is the sorted array of processes
    cout<<"order of execution "<<endl;
    for(int i=0;i<n;i++)
    cout<<p[i]<<endl;
    
    
    //calclulating Response time for a process
    //response time= response time + burst time of previos processes - arrival time of this process
    
    for(int i=1;i<n;i++)
    {
        res[i]=res[i-1]+b[i-1];
        res[i]-=a[i];
    }
    
    //calculating total waiting time and turn around time
    int tot_wt=0,tot_tat=0;
    for(int i=0;i<n;i++)
    {
        wt[i]=res[i];
        tot_wt+=wt[i];
    }
   
	   
    for(int i=0;i<n;i++)
    {
        tat[i]=b[i]+wt[i];
        tot_tat+=tat[i];
        ct[i]=tat[i]+a[i];
    }

	//displaying the burst time and waiting time for each process
	//this could jave been displayed in another file to according to the question
	//but for simlplicity we simply declare it here    
    for(int i=0;i<n;i++)
    {
        cout <<"burst time="<< b[i]<<"\t\twaiting time="<<wt[i]<<"\t\tturn around time="<<tat[i]<<endl;
    }
    
    //calculating and displaying average waiting time
    float avg=(float)tot_wt/n,ans=0;
    cout<<"average waiting time= "<<avg<<endl;
    
    return 0;
}
