#include "header_for_make_decision_table_from_vdm.h"

//構造体定義
typedef struct lexeme_list{
	int lexeme_id;
	char *lexeme_str;
	struct lexeme_list *next;
}lexeme_list;

//静的変数定義
static lexeme_list *lexeme_list_index = NULL;
static lexeme_list lexeme_list_root;

//プロトタイプ宣言
int count_char_num(char *target_str,int comma_num);
void extract_lexeme_data(char *line_buf,int *lexeme_id,char *lexeme_str_buf);
char *read_line_from_vdmappfile(FILE *fp,char *line_buf);
void insert_lexeme_data_for_lexeme_list(FILE *fp);
FILE *file_open();
lexeme_list *make_next_lexeme_node(int lexeme_id,char *lexeme_str);
lexeme_list *make_empty_lexeme_node();


//----------------------語彙素リストの作成-------------------------------
void make_lexeme_list(){
	lexeme_list_root.next = NULL;
	lexeme_list_index = &(lexeme_list_root);
	
	FILE *fp = file_open();
	insert_lexeme_data_for_lexeme_list(fp);

	fclose(fp);
	return;
}


FILE *file_open(){
	FILE *fp;
	char filename_buf[BUFSIZE];
	get_input_filename(filename_buf);
	if((fp = fopen(filename_buf,"r")) == NULL){
		fprintf(stderr,"ファイル読み込みに失敗\n");
		exit(EXIT_FAILURE);
	}
	return fp;
}


void insert_lexeme_data_for_lexeme_list(FILE *fp){
	char line_buf[BUFSIZE];
	char lexeme_str_buf[BUFSIZE];
	int lexeme_id;
	
	//vdmappファイルからnew_OmlLexemという語を含む一行を抜き出す
	while(read_line_from_vdmappfile(fp,line_buf) != NULL){
		extract_lexeme_data(line_buf,&lexeme_id,lexeme_str_buf);
		lexeme_list_index->next = make_next_lexeme_node(lexeme_id,lexeme_str_buf);
		lexeme_list_index = lexeme_list_index->next;
	}
	return;
}

char *read_line_from_vdmappfile(FILE *fp,char *line_buf){
	char tmp_buf[BUFSIZE];
	regex_t reg;
	regcomp(&reg,"new OmlLexem", REG_EXTENDED|REG_NOSUB);
	char *return_value_from_fgets;
	
	memset(line_buf,NULLCHAR,BUFSIZE);
	while(1){
		return_value_from_fgets = fgets(tmp_buf,BUFSIZE,fp);
		if(return_value_from_fgets == NULL)
			break;
	
		if(regexec(&reg, tmp_buf, 0, NULL, 0) != REG_NOMATCH){
			sprintf(line_buf,"%s",tmp_buf);
			break;
		}
		memset(tmp_buf,NULLCHAR,BUFSIZE);
	}
	return return_value_from_fgets;
}


void extract_lexeme_data(char *line_buf,int *lexeme_id,char *lexeme_str_buf){
	int char_num_till_seconed_comma = count_char_num(line_buf,2);
	int char_num_till_third_comma = count_char_num(line_buf,3);
	int char_num_till_fourth_comma = count_char_num(line_buf,4);
	char tmp_id_buf[BUFSIZE];	
	
	memset(tmp_id_buf,NULLCHAR,BUFSIZE);
	memset(lexeme_str_buf,NULLCHAR,BUFSIZE);
	
	//idをノードに書き込む
	//idは2つめのコンマと3つめのコンマの間
	strncpy(tmp_id_buf,line_buf + char_num_till_seconed_comma,char_num_till_third_comma - char_num_till_seconed_comma - 1);
	*lexeme_id = atoi(tmp_id_buf);
	
	//語彙素をノードに書き込む
	//語彙素は3つめのコンマと4つめのコンマの間
	strncpy(lexeme_str_buf,line_buf + char_num_till_third_comma,char_num_till_fourth_comma - char_num_till_third_comma- 1);
	
	//上の処理でコンマは書き込めない
	if(*lexeme_id == LEXEME_ID_COMMA)
		sprintf(lexeme_str_buf,"\",\"");
	
	
	return;
}


int count_char_num(char *target_str,int comma_num){
	int char_num = 0;
	int find_comma_num = 0;
	
	while(find_comma_num < comma_num){
		if(target_str[char_num] == ',')
			find_comma_num++;
		char_num++;
		
	}
	return char_num;
}

lexeme_list *make_next_lexeme_node(int lexeme_id,char *lexeme_str){
	lexeme_list *next_lexeme_node = make_empty_lexeme_node();	
	next_lexeme_node->lexeme_id = lexeme_id;
	next_lexeme_node->lexeme_str = (char *)malloc(sizeof(char) * (strlen(lexeme_str) + 1));
	sprintf(next_lexeme_node->lexeme_str,"%s",lexeme_str);

	return next_lexeme_node;
}


lexeme_list *make_empty_lexeme_node(){
	lexeme_list *new_lexeme_node;
	
	new_lexeme_node = (lexeme_list *)malloc(sizeof(lexeme_list));
	new_lexeme_node->next = NULL;
	return new_lexeme_node;
}




//---------------語彙素のデータへの参照------------------

void lexeme_list_index_init(){
	lexeme_list_index = &(lexeme_list_root);
	go_next_lexeme_node();
	return;	
}

void go_next_lexeme_node(){	
	if(lexeme_list_index == NULL){
		fprintf(stderr,"\"go_next_lexeme_list_node\"で不正な移動操作\n");
		return;
	}
	lexeme_list_index = lexeme_list_index->next;
	return;
}

enum Function_result can_go_next_lexeme_node(){
	if(lexeme_list_index == NULL)
		return Result_false;
	return Result_true;
}

int get_lexeme_id_from_lexeme_node(){
	if(lexeme_list_index == NULL){
		fprintf(stderr,"get_lexeme_id_from_lexeme_nodeでエラー\n");
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return -1;
	}
	return lexeme_list_index->lexeme_id;
}


void get_lexeme_str_from_lexeme_node(char *target_buf){
		if(lexeme_list_index == NULL){
			fprintf(stderr,"get_lexeme_str_from_lexeme_nodeでエラー\n");
			fprintf(stderr,"indexがNULLなのに呼び出された\n");
			return;
		}

	sprintf(target_buf,"%s",lexeme_list_index->lexeme_str);
	return;
}



char *get_lexeme_str_without_escape_string(){
	char lexeme_str_buf[BUFSIZE];
	char lexeme_str_without_escape_string_buf[BUFSIZE];
	
	get_lexeme_str_from_lexeme_node(lexeme_str_buf);
	int lexeme_str_buf_len = strlen(lexeme_str_buf);
	
	if(lexeme_str_buf_len <= 0){
		fprintf(stderr,"get_lexeme_str_without_escape_stringでエラー\n");
		fprintf(stderr,"文字数が0の配列を受け取った\n");
		return NULL;
	}


	//
	//if(lexeme_str_buf[0] != '\"' ||lexeme_str_buf[lexeme_str_buf_len - 1] != '\"'){
	//	fprintf(stderr,"get_lexeme_str_without_escape_stringでエラー\n");
	//	fprintf(stderr,"配列の最初と最後が\"ではない\n");
	//	return NULL;
	//}
	
	int index_lexeme_str_without_escape_string = 0;
	//最初と最後の「"」は飛ばす
	for(int i = 1;i < lexeme_str_buf_len - 1;i++){
		if(lexeme_str_buf[i] == '\\')
			continue;
		
		lexeme_str_without_escape_string_buf[index_lexeme_str_without_escape_string] = lexeme_str_buf[i];
		index_lexeme_str_without_escape_string++;
	}
	lexeme_str_without_escape_string_buf[index_lexeme_str_without_escape_string] = '\0';
	char *lexeme_str = (char *)malloc(sizeof(char) * (strlen(lexeme_str_without_escape_string_buf) + 1));
	sprintf(lexeme_str,"%s",lexeme_str_without_escape_string_buf);
	
	return lexeme_str;
}


