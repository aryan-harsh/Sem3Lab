#include<stdio.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<unistd.h>
#include<fcntl.h>
#include<string.h>

int main()
{
	int j;
	pid_t i;
	int status=0;
	float delay,delay1;
	char str[200],str2[200];
	printf("Please Enter Delay and String\n");
	scanf("%f",&delay);
	scanf("%s",str);
	

	i=fork();
	

	if(i<0)
		printf("\ncould not fork");
	


	else if(i==0)
	{
		
		execl("./p2_child","p2_child",delay,str,NULL);
		printf("\ncould not run child process");
	}
	
	

	else
	{
		wait(&status);
		printf("Please Enter Delay and String\n");
		scanf("%f",&delay1);
		scanf("%s",str2);
		while(1)
		{
			printf("%s\n",str2);
			sleep(delay1);
			printf("%s\n",str);
			sleep(delay);
		}
		
	}
	
}