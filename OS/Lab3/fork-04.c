/* ----------------------------------------------------------------- */
/* PROGRAM fork-04.c                                                 */
/*    This one is identical to fork-03.c, except that the parent     */
/* process also does some computation.                               */
/* ----------------------------------------------------------------- */

#include  <stdio.h>
#include  <string.h>
#include  <sys/types.h>

#define   MAX_COUNT  200
#define   BUF_SIZE   100

void  ChildProcess(char [], char []);    /* child process prototype  */
void  ParentProcess(void);               /* parent process prototype */

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

     ParentProcess();
     sprintf(buf, "*** Parent enters waiting status .....\n");
     write(1, buf, strlen(buf));
     pid = wait(&status);
     sprintf(buf, "*** Parent detects process %d was done ***\n", pid);
     write(1, buf, strlen(buf));
     pid = wait(&status);
     printf("*** Parent detects process %d is done ***\n", pid);
     printf("*** Parent exits ***\n");
     exit(0);
}

/* ----------------------------------------------------------------- */
/* FUNCTION  ParentProcess :                                         */
/*    This function computes and displays all Armstrong numbers in   */
/* the range of 0 and 9999.  A four digit number is an Armstrong     */
/* number if and only if the sum of individual digits raised to the  */
/* fourth power is equal to the number itself.                       */
/* ----------------------------------------------------------------- */

#define  QUAD(x)  (x*x*x*x)

void  ParentProcess(void)
{
     int  a, b, c, d;
     int  abcd, a4b4c4d4;
     int  count = 0;
     char buf[BUF_SIZE];
     
     sprintf(buf, "Parent is about to compute the Armstrong numbers\n");
     write(1, buf, strlen(buf));
     for (a = 0; a <= 9; a++)
          for (b = 0; b <= 9; b++)
               for (c = 0; c <= 9; c++)
                    for (d = 0; d <= 9; d++) {
                         abcd     = a*1000 + b*100 + c*10 + d;
                         a4b4c4d4 = QUAD(a) + QUAD(b) + QUAD(c) + QUAD(d);
                         if (abcd == a4b4c4d4) {
                              sprintf(buf, "From parent: "
                                      "the %d Armstrong number is %d\n",
                                      ++count, abcd);
                              write(1, buf, strlen(buf));
                         }
                    }
     sprintf(buf, "From parent: there are %d Armstrong numbers\n", count);
     write(1, buf, strlen(buf));
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

