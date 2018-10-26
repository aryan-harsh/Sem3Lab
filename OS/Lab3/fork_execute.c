#include<stdio.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdlib.h>
#include<unistd.h>

int main(){

int pid;
int status;

 pid =fork();
 if(pid>0){
  pid=wait(&status);
 }

else{
execlp("ls","",NULL);
exit(0);
}



}

