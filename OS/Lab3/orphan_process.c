#include<stdio.h>
#include <sys/types.h>
#include <unistd.h>
 
int main()
{
    // Create a child process      
    int i = fork();
 
    if (i > 0)
        printf("in parent process :  pid = %d \t parent id = %d \n",getpid(),getppid());
 
    // Note that pid is 0 in child process
    // and negative if fork() fails
    else if (i == 0)
    {
        sleep(30);
        printf("in child process :  pid = %d \t parent id = %d\n",getpid(),getppid());
    }
 
    return 0;
}
