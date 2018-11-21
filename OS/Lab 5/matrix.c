#include <stdio.h>
#include <stdlib.h>
#include <pthread.h>

  
float matrix1[100][100]; 
float matrix2[100][100]; 
float resultant_matrix[100][100]; 
int thread_row = 0; 
int number_of_rows, number_of_columns;

int matrix1_rows = 0, matrix2_rows = 0, resultant_matrix_rows = 0, matrix1_columns = 0, matrix2_columns = 0, resultant_matrix_columns = 0;
  
void* matrix_multiply(void* arg) 
{ 
    int j, k;
    int val = (int)arg;
    for (j = 0; j < matrix2_columns; j++){
        for (k = 0; k < matrix2_rows; k++){
            resultant_matrix[val][j] += matrix1[val][k] * matrix2[k][j]; 
        } 
    }  
} 
  

int main(int argc, char *argv[]) 
{ 
    FILE *f1, *f2;
    int i, j;

    if ((f1 = fopen(argv[1], "r")) == NULL){
        exit(-1);
    }
        
    char str[100];//for (j = 0; j < matrix2_columns; j++){
     //   for (k = 0; k < matrix2_rows; k++){
      //      resultant_matrix[val][j] += matrix1[val][k] * matrix2[k][j]; 
     //   } 
   // }  

    int a, b;
    fgets(str, 100, f1);
    sscanf(str, "%d%d", &a, &b);
	matrix1_rows = a;
	matrix1_columns = b;
    for (i = 0; i < a; i++){
        for (j = 0; j < b; j++){
            fgets(str, 100, f1);
            if(str[0]=='#') {
                j--;
            }
            else {
                sscanf(str, "%f", &matrix1[i][j]);
            }
        }
    }
    int c = a, d = b;
    if ((f2=fopen(argv[2], "r")) == NULL)
        exit(-1);
    fgets(str, 100, f2);
    sscanf(str, "%d%d", &a, &b);
	matrix2_rows = a;
	matrix2_columns = b;
	resultant_matrix_rows = c;
	resultant_matrix_columns = b;
    for (i = 0; i < a; ++i){
        for (j = 0; j < b; ++j){
            fgets(str, 100, f2);
            if(str[0]=='#') {
                j--;
            }
            else {
                sscanf(str, "%f", &matrix2[i][j]);
            }
        }
    }

    
    pthread_t tid[matrix1_rows]; 
  
    for (i = 0; i < matrix1_rows; i++) { 
        pthread_create(&tid[i], NULL, &matrix_multiply, i); 
    } 
  
    for (i = 0; i < matrix1_rows; i++){
        pthread_join(tid[i], NULL);
    }
    for (i = 0; i < matrix1_rows; i++) { 
        for (j = 0; j < matrix2_columns; j++){
            printf("%f ", resultant_matrix[i][j]); 
        }       
        printf("\n");
    } 
    return 0; 
} 
