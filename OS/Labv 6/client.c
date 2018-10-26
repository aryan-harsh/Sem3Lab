#include<stdio.h>
#include<stdlib.h>
#include<sys/types.h>
#include<sys/shm.h>
#include<sys/types.h>

#define SHMSIZE	27  /* Size of the shared memory */

main(){

    char c;
    int shmid;
    key_t key;
    char *shm,*s;

    key = 5678; /* We need to get the segment named '5678' crated by the server */

    /* Locate the Segment */

    if((shmid=shmget(key,SHMSIZE,IPC_CREAT | 0666))< 0){
        perror("shmget");
        exit(1);
    }

   /* Attach the segment to our data space */

    if((shm=shmat(shmid,NULL,0))==(char *) -1){
        perror("shmat");
        exit(0);
    }

  /* Now read whatever server has put in into shared memory */

    
    for(s=shm; *s!=0;s++)
       putchar(*s);
    putchar('\n');

  /* Finally change the first character of the segment to '*', indicating we have read the segment */

     *shm = '*';
     exit(0);

}
        
