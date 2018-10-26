#include<stdio.h>
#include<string.h>

int main()
{
	int fd;
	char arr[100];

	char *path="/iiita/myfifo";
	mkfifo(path,0666);

	fd=open(myfifo,O_RDONLY);

	read(fd,arr,strlen(arr)+1);

	while(strcmp(arr,"exit")!=0)
	{
		printf("%s\n",arr);
		read(fd,arr,strlen(arr)+1);
	}

	close(fd);
	return 0;
}