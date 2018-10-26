#include<stdio.h>
#include<fcntl.h>
#include<string.h>
int main(int argc, char *argv[]){
	if(argc==2){
		int fd;
		char *file;
		file = argv[1];
		char buf[100000];
		fd = open(file, O_RDONLY);
		int n = read(fd, buf, 100000);
		//printf("%s",buf);
		n = write(1,buf,strlen(buf));
		close(fd);
	}
	else if(argc==3){
		int fd1, fd2;
		char *file1, *file2;
		file1 = argv[1];
		file2 = argv[2];
		fd1 = open(file1, O_RDONLY);
		fd2 = open(file2, O_RDONLY);
		char buf[100000];
		int n = read(fd1, buf, 100000);
		char buf2[100000];
		int n2 = read(fd2, buf2, 100000);
		int fd3 = open(file1, O_WRONLY);
		write(fd3, buf, strlen(buf));
		write(fd3, "\n", 1);
		write(fd3, buf2, strlen(buf2));
		close(fd1);
		close(fd2);
		close(fd3);
	}
}
