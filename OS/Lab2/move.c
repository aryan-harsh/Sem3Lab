#include<stdio.h>
#include<fcntl.h>
int main(int argc, char *argv[]){
	char *file1, *file2;
	file1 = argv[1];
	file2 = argv[2];
	int fd1,fd2,n;
	fd1 = open(file1,O_WRONLY);
	fd2 = open(file2, O_RDONLY);
	char buf[100000];
	n = read(fd2, buf, 100000);
	write(fd1,buf, strlen(buf));
	remove(file2);
}
