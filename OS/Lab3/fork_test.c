#include<stdio.h>
#include<sys/types.h>
#include<sys/wait.h>
#include<stdlib.h>
 #include <unistd.h>

int main(){

int frv,pid;
int status;

 frv =fork();
 if(frv>0){
  printf("I am in the parent, my process id  = %d, my parent's process id = %d, the child's process id  = %d and return value of the fork  = %d\n",getpid(),getppid(),frv,frv);
  //return value of fork here will be the pid of the child created
  pid = wait(&status);//this will force parent to wait till child gets executed, because if we dont wait,
  					  //it can happen that parent completes and chil becomes orphan.
  					  //to know when child is executed, we use &status.
printf("I am in the parent after the child exited and the exited child's process id  = %d \n ",pid );

}

else{
printf("I am in the child, my process id  = %d,  and my parents's process id = %d and return value of the fork  = %d\n",getpid(),getppid(),frv);
//return value of fork here will be zero as it is parent
exit(0);
}



}

