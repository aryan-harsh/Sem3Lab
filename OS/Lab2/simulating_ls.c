#include<stdio.h>
#include<dirent.h>

int main(){
char d[10];
DIR *p;
struct dirent *d1;
printf("Enter the directory name\n");
scanf("%s",d);

p=opendir(d);
 if(p==NULL){
  perror("can't find directory");
  exit(-1);
}

while(d1=readdir(p))
 printf("%s\n",d1->d_name);
}
  
