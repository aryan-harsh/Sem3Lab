#include<stdio.h>
#include <stdlib.h>
 
int main()
{
    int n,i,j;
    char c[200];
    FILE *f;
     printf("THIS IS THE EXECUTION FOR FIRST COME FIRST SERVE SCHEDULING\n\n");


    if ((f = fopen("records.txt", "r")) == NULL)
    {
        printf("File cannot be opened. encountered an error");        
        exit(1);         
    }
    printf("\nFile linked succesfully, staring to read processes\n");
    //to scan a string from file
    fscanf(f,"%s",c);
    //to convert string to int
    n=atoi(c);
    
    int arrival_time[n],burst_time[n],waiting_time[n],sum,turn_around_time[n],avg_tat,avg_wt;
    
    for(i=1;i<n+1;i++){
     fscanf(f,"%s", c);
     arrival_time[i]=atoi(c);
     
    
     fscanf(f,"%s", c);
     burst_time[i]=atoi(c); 
     }

    fclose(f);
    
    
    int completion_time[n];
    waiting_time[0]=0;    
    for(i=1;i<n;i++)
    {
        waiting_time[i]=0;
        for(j=0;j<i;j++)
            waiting_time[i]+=burst_time[j+1];
    }
 
    
     int completion_time[n];
    //calculating turnaround time
    avg_tat=0;
    for(i=0;i<n;i++)
    {
        completion_time[i]=burst_time[i+1]+waiting_time[i];
        avg_wt+=waiting_time[i];
        turn_around_time[i]=completion_time[i]-arrival_time[i+1];
        avg_tat+=turn_around_time[i];
        
        printf("\nwaiting time for process %d is: %d",i+1,waiting_time[i]);
        printf("\nturn around time for process %d is : %d ",i+1,turn_around_time[i]);
        
      
    }
    
    avg_wt/=n;
    avg_tat/=n;
    printf("\n\nAverage Waiting Time is %d",avg_wt);
    printf("\nAverage Turnaround Time is %d\n",avg_tat);
 
    return 0;
}



