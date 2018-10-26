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

    key = 5678; /* Some key to uniquely identify shared memory */

    /* Create Segment */

    if((shmid=shmget(key,SHMSIZE,IPC_CREAT | 0666))< 0){
        perror("shmget");
        exit(1);
    }

   /* Attach the segment to our data space */

    if((shm=shmat(shmid,NULL,0))==(char *) -1){
        perror("shmat");
        exit(0);
    }

  /* Now put something into shared memory */

    s = shm;
    for(c = 'A';c<='Z';c++)
       *s++=c;
    *s=0; /* End with NULL termination */

  /* Wait until the other process changes the first character to '*' the shared memory */

     while(*shm != '*');
       sleep(1);
     exit(0);

}
        
