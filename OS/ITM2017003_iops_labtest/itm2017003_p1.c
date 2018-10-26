#include<stdio.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<unistd.h>
#include<fcntl.h>
#include<string.h>

int main()
{
	int i,j;
	float delay;
	char str[200];
	printf("Please Enter Delay and String\n");
	scanf("%f",&delay);
	scanf("%s",str);
	while(1)
	{
		printf("%s\n",str);
		sleep(delay);
	}
}