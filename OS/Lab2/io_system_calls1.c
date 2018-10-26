
#include<stdio.h>
#include<fcntl.h>
int main(){

char buff[100],fn[10];
int fd,n;

printf("Enter the filename\n");

scanf("%s",fn);

fd = open(fn,O_RDONLY);
n = read(fd,buff,100);

n = write(1,buff,n);
printf("%d\n",n);

close(fd);

}

