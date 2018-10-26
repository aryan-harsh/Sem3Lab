#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<sys/types.h>
int main(int argc, char* argv[])
{
	pid_t parent=getpid();
	int status;
	float x=atof(argv[1]);	
	int n=atoi(argv[2]);
	pid_t ret=fork();
	// for error handling
	if(ret==-1){
		printf("Error in forking!");
		exit(-1);
	}
	else if(ret>0)
	{
		//printf("Waiting\n");
		wait(&status);
		printf("Parent(PID=%d) : Done\n",parent);
		exit(0);
	}
	else
	{
		//printf("Child Called\n");
		//calling child using execvp 
        	execvp("./childProcess",argv);
        	exit(0);
		//execl("childProcess.c", "childProcess",x,n,NULL);
		//exec("childProcess.c","//provide the arguments x and n");
	}

}
