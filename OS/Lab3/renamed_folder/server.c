#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>
#include <stdio.h>
#include <unistd.h>

int main(int argc, char **argv){
	int a,b,res;
	a= atoi(argv[0]);
	b= atoi(argv[1]);
	if(*argv[2]=='+')
		res = a+b;
	else
		res = a-b;
	return res;
}
