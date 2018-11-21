#include <stdio.h>
#include <pthread.h>
#include <semaphore.h>
#include <stdlib.h>
sem_t mutex0,mutex1,mutex2;
int b0,b1;

void * count(){
	for(int i=1;i<=1001;i++){
		sem_wait(&mutex0);
		b0 = i;
		if(b0==1001){
			b0=0;
		}
		sem_post(&mutex1);
	}
	return (void*) NULL;
}

void *count2(){
	while(1){
		sem_wait(&mutex1);
		if(b0==0){
			b1 = b0;
			sem_post(&mutex2);
			break;
			return (void*) NULL;
		}
		if(b0%2!=0){
			b1=b0;
			sem_post(&mutex2);
		}
		else{
			sem_post(&mutex0);
		}
	}
		
}
void *count3(){
	while(1){
		sem_wait(&mutex2);
		if(b1==0){
			sem_post(&mutex0);
			break;
			return (void*) NULL;
		}
		if(b1%3!=0){
			printf("%d\n", b1);
		}
		sem_post(&mutex0);
	}
}
int main(){
	sem_init(&mutex0, 0, 1);
	sem_init(&mutex1, 0, 0);
	sem_init(&mutex2, 0, 0);

	pthread_t p0,p1,p2;
	if(pthread_create(&p0,NULL,count,NULL)){
		printf("\n ERROR creating thread 1");
      	exit(1);
	}

	if(pthread_create(&p1,NULL,count2,NULL)){
		printf("\n ERROR creating thread 1");
      	exit(1);
	}

	if(pthread_create(&p2,NULL,count3,NULL)){
		printf("\n ERROR creating thread 1");
      	exit(1);
	}
	if(pthread_join(p0, NULL))	/* wait for the thread 1 to finish */
    {
      printf("\n ERROR joining thread");
      exit(1);
    }

    if(pthread_join(p1, NULL))        /* wait for the thread 2 to finish */
    {
      printf("\n ERROR joining thread");
      exit(1);
    }

    if(pthread_join(p2, NULL))        /* wait for the thread 2 to finish */
    {
      printf("\n ERROR joining thread");
      exit(1);
    }

    pthread_exit(NULL);

}