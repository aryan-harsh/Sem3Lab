#include<stdio.h>
#include<fcntl.h>
#include<sys/stat.h>
int main(int argc, char *argv[]){
	int fd = open(argv[1], O_RDONLY);
	int size = lseek(fd, 0, SEEK_END);
	printf("size of the file is %d\n",size);
	printf("number of blocks = %d\n",8*(size/4096 + 1));
	//using stat command
	struct stat st;
	stat(argv[1],&st);
	printf("file size using stat command is %d\n",st.st_size);
	printf("number of blocks using stat command is %d\n",st.st_blocks);
}
