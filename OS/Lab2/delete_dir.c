#include<stdio.h>
#include<dirent.h>
#include<sys/stat.h>
#include<malloc.h>
#include<string.h>
#include<sys/types.h>
#include<errno.h>

int removedir(char path[500]){
	DIR *pdir = NULL;
	struct dirent* p = NULL;
	struct stat stat;
	pdir = opendir(path);
	char temp[500];
	
	if(pdir == NULL){
		return -1;
	}
	
	while((p = readdir(pdir)) != NULL){
		if((strcmp(p->d_name,".") == 0) || (strcmp(p->d_name, "..") == 0)){
			continue;
		}
		else{
			strcpy(temp,path);
			path = strcat(path, "/");
			path = strcat(path, p->d_name);
		}
		if(stat(path, &stat)){
			printf("Hello\n");
		}
		else{
			if(S_ISDIR(stat.st_mode)){
				removedir(path);
				strcpy(path, temp);
			}
			else{
				unlink(path);
				strcpy(path, temp);
			}	
		}
	}
	rmdir(path); 
}

int main(){
	char path[500];
	printf("enter Directory name\n");
	scanf("%s",path);
	int res=removedir(path_dir);
	printf("\n %d", res);
	
}
