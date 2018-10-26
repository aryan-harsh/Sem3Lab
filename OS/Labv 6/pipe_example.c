#include<stdio.h>
#include<unistd.h>
#include<stdlib.h>

int main(){

    int pipefd[2];
    int pid;
    char recv[32];
 
    pipe(pipefd);

    switch(pid=fork()){

    case -1:perror("fork");
            exit(1);

    case 0:				      /* in child process */
          close(pipefd[0]);		      /* close unnecessary pipefd  */
          FILE *out = fdopen(pipefd[1],"w");   /* open file descriptor as a stream  */
          fprintf(out,"Hello World\n");       /* write to out stream */
          break;

   default:				      /* in parent process */	
          close(pipefd[1]);		      /* close unnecessary pipefd  */
          FILE *in = fdopen(pipefd[0],"r");   /* open file descriptor as a stream  */
          fscanf(in,"%s",recv);               /* read from in stream  */
          printf("%s",recv);
          break;
   }

}
