#include "header_for_make_decision_table_from_vdm.h"

#define THIS_FILE_NAME "filename_control.c"

static char filename[BUFSIZE];

void extract_filename(char *input_filename){
	if(input_filename == NULL){
		fprintf(stderr,"%sのextract_filenameでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"ファイル名が指定されていない\n");
		exit(EXIT_FAILURE);
	}
	int index = 0;
	
	while(1){
		if(input_filename[index] == '\0'){
			fprintf(stderr,"%sのextract_filenameでエラー\n",THIS_FILE_NAME);
			fprintf(stderr,"文字列にコンマが含まれてない\n");
			exit(EXIT_FAILURE);
		}
		if(input_filename[index] == '.')
		break;
		
		index++;
	}

	if(strcmp(EXTENSION_OF_INPUTFILE,input_filename + index) != 0){
		printf("%s\n",input_filename + index);
		fprintf(stderr,"%sのextract_filenameでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"入力の拡張子が指定されたものではない\n");
		exit(EXIT_FAILURE);
		}
	
	strncpy(filename,input_filename,index);
	filename[index] = '\0';
	
}

void get_parser_command(char *buf){
	sprintf(buf,"%s%s%s","java -classpath \"../parser/VDM.jar;../parser/org.overturetool.parser.jar\" org.overturetool.parser.imp.TestParser -eUTF-8 -pp -O ../data/",filename,".vdmpp");
}

void get_input_filename(char *filename_buf){
	sprintf(filename_buf,"%s%s%s",PATH_DATA_DIRECTORY,filename,EXTENSION_OF_APP);
	return;
}


void get_output_filename(char *filename_buf){
	sprintf(filename_buf,"%s%s%s",PATH_DATA_DIRECTORY,filename,EXTENSION_OF_OUTPUTFILE);
	
	return;
}

