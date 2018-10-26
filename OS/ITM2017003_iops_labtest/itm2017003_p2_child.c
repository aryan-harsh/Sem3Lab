#include<stdio.h>
#include<sys/wait.h>
#include<sys/types.h>
#include<unistd.h>
#include<fcntl.h>
#include<string.h>
#include<math.h>
#include<stdlib.h>

int main(int args, char *argv[])
{
	int status;
	int i,j;
	float delay;
	char str[200];
	delay=atof(argv[1]);
	strcpy(str,argv[2]);
	while(1)
	{
		printf("%s\n",str);
		sleep(delay);
	}
	exit(status);
}