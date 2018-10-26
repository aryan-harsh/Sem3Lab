/* ---------------------------------------------------------------- */
/* PROGRAM  fork-05.c                                               */
/*    This is a modification to fork-04.c.  Now wait() is treated   */
/* as a procedure, since the returned process ID is unimportant to  */
/* this program.                                                    */
/* ---------------------------------------------------------------- */

#include  <stdio.h>
#include  <string.h>
#include  <sys/types.h>

#define   MAX_COUNT  100
#define   BUF_SIZE   100

void  ChildProcess(char [], char []);

void  main(void)
{
     pid_t   pid1, pid2, pid;
     int     status;
     int     i;
     char    buf[BUF_SIZE];

     printf("*** Parent is about to fork process 1 ***\n");
     if ((pid1 = fork()) < 0) {
          printf("Failed to fork process 1\n");
          exit(1);
     }
     else if (pid1 == 0) 
          ChildProcess("First", "   ");

     printf("*** Parent is about to fork process 2 ***\n");
     if ((pid2 = fork()) < 0) {
          printf("Failed to fork process 2\n");
          exit(1);
     }
     else if (pid2 == 0) 
          ChildProcess("Second", "      ");

     sprintf(buf, "*** Parent enters waiting status .....\n");
     write(1, buf, strlen(buf));
     wait(&status);
     sprintf(buf, "*** Parent detects a child process was done ***\n");
     write(1, buf, strlen(buf));
     wait(&status);
     printf("*** Parent detects another child process was done ***\n");
     printf("*** Parent exits ***\n");
     exit(0);
}

void  ChildProcess(char *number, char *space)
{
     pid_t  pid;
     int    i;
     char   buf[BUF_SIZE];
     
     pid = getpid();
     sprintf(buf, "%s%s child process starts (pid = %d)\n", 
             space, number, pid);
     write(1, buf, strlen(buf));
     for (i = 1; i <= MAX_COUNT; i++) {
          sprintf(buf, "%s%s child's output, value = %d\n", 
                  space, number, i); 
          write(1, buf, strlen(buf));
     }
     sprintf(buf, "%s%s child (pid = %d) is about to exit\n", 
             space, number, pid);
     write(1, buf, strlen(buf));
     exit(0);
}

