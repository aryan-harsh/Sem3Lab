#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/types.h>
int main(int argc, char* argv[])
{
	//printf("Child reached");
	pid_t pid=getpid();
	if(argc==0)
		return 0;
	float x=atof(argv[1]);
	int n=atoi(argv[2]);
	int i,fact=1;
	float ans=0,power=1;
	//printf("%f %d\n",x,n);
	for(i=0;i<=n;i++)
	{
		//printf("%f %f %d\n",ans,power,fact);
		ans=ans+(power)/fact;
		if(i!=0)		
			fact=fact*i;
		power=power*x;
	}
	printf("Child(PID=%d) : For x = %f the first %d terms yields %f\n",pid,x,n,ans);
	exit(0);
}
