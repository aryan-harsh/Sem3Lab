#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <stdlib.h>

int main(){
	int pid,status;
	char op1[10],op2[10],sign;

	printf("Enter operand1 then operator then operand2:\n");
	scanf("%s %c %s",op1,&sign,op2);
	pid = fork();

	if(pid==0){
		if(execl("child",&op1,&op2,&sign,NULL)<0)
			perror("error");
		}
	else if(pid>0){
		waitpid(pid, &status, 0);
		printf("result %d\n",WEXITSTATUS(status));
	}
	exit(0);
}
