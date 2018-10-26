#include<stdio.h>
#include<string.h>

int main()
{
	int fd;
	char arr[100];

	fd=open(myfifo,O_WRONLY);


	while(1)
	{
		scanf("%s",arr);
		write(fd,arr,strlen(arr)+1);

		if(strcmp(arr,"exit")==0)
			break;
	}

	close(fd);
}