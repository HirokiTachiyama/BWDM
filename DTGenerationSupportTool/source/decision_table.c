#include "header_for_make_decision_table_from_vdm.h"


#define THIS_FILE_NAME "decision_table.c"
#define DELIMCHAR ","
#define ENCLOSE_SYMBOL "\""

enum State_of_decision_table {yet,state_input_class_name,state_input_definition_type,
	state_input_definition_name,state_input_condition_num,state_input_action_num,
	state_input_condition_name,state_input_action_name,state_input_action_bool_value};

typedef struct decision_table{
	char *class_name;
	enum Definition_type type;
	char *definition_name;
	int condition_num;
	int action_num;
	int row_num;
	char **condition_name;
	char **action_name;
	enum Bool_value **condition_decision_table;
	enum Bool_value **action_decision_table;
	
}decision_table;


FILE *output_fileopen();
void output_name(FILE *fp,decision_table target_table);
void output_number(FILE *fp,decision_table target_table);
void output_condition(FILE *fp,decision_table target_table);
void output_action(FILE *fp,decision_table target_table);
void free_decision_table(decision_table *target_table);

decision_table *make_new_decision_table();
enum Bool_value **make_new_bool_value_array(int line_num,int row_num);
char **make_new_char_array(int line_num);
void fill_condition_decision_table(enum Bool_value **target,int condition_num);

void output_one_field(FILE *fp,char *str);
void output_delimchar(FILE *fp);
void output_return(FILE *fp);

static enum State_of_decision_table state = yet;
static decision_table *my_table;
char *make_str_array(char *buf);
enum Bool_value decide_periodically_bool_value(int cycle,int this_number);



void begin_make_decision_table(){
	
	if(state != yet){
		fprintf(stderr,"%sのbegin_make_decision_tableでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"デシジョンテーブル作成開始前の状態ではない\n");
		exit(EXIT_FAILURE);
	}
	my_table = make_new_decision_table();
	
	state =  state_input_class_name;
}



decision_table *make_new_decision_table(){
	decision_table *new_decision_table = (decision_table *)malloc(sizeof(decision_table));
	return new_decision_table;
}



void input_class_name_for_decision_table(char *class_name){
	if(state != state_input_class_name){
		fprintf(stderr,"%sのinput_class_nameでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"クラス名入力状態ではない\n");
		exit(EXIT_FAILURE);
	}
	my_table->class_name = make_str_array(class_name);
	
	state = state_input_definition_type;
}




void input_definition_type_for_decision_table(enum Definition_type definition_type){
	if(state != state_input_definition_type){
		fprintf(stderr,"%sのinput_class_nameでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"定義タイプの入力状態ではない\n");
		exit(EXIT_FAILURE);
	}
	my_table->type = definition_type;
	
	state = state_input_definition_name;
}



void input_definition_name_for_decision_table(char *definition_name){
	if(state != state_input_definition_name){
		fprintf(stderr,"%sのinput_definition_nameでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"定義名の入力状態ではない\n");
		exit(EXIT_FAILURE);
	}
	my_table->definition_name = make_str_array(definition_name);
	state = state_input_condition_num;
}



void input_condition_num_for_decision_table(int condition_num){
	if(state != state_input_condition_num){
		fprintf(stderr,"%sのinput_condition_numでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"条件数の入力待ち状態ではない\n");
		exit(EXIT_FAILURE);
	}
		if(condition_num < 0){
		fprintf(stderr,"%sのinput_condition_numでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"入力された条件数の値が異常\n");
		fprintf(stderr,"入力値 : %d\n",condition_num);
		exit(EXIT_FAILURE);
	}
	my_table->condition_num = condition_num;
	my_table->row_num = pow(2,condition_num);
	my_table->condition_decision_table = make_new_bool_value_array(my_table->condition_num,my_table->row_num);
	my_table->condition_name = make_new_char_array(condition_num);
	fill_condition_decision_table(my_table->condition_decision_table,my_table->condition_num);
	state = state_input_action_num;
	
}



void input_action_num_for_decision_table(int action_num){
	if(state != state_input_action_num){
		fprintf(stderr,"%sのinput_action_numでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"条件の入力待ち状態ではない\n");
		exit(EXIT_FAILURE);
	}
		if(action_num < 1){
		fprintf(stderr,"%sのinput_action_numでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"入力された動作数の値が異常\n");
		fprintf(stderr,"入力値 : %d\n",action_num);
		exit(EXIT_FAILURE);
	}
	
	my_table->action_num = action_num;
	my_table->action_decision_table = make_new_bool_value_array(action_num,my_table->row_num);
	my_table->action_name = make_new_char_array(action_num);
	if(my_table->condition_num > 0){
		state = state_input_condition_name;
	}else{
		state = state_input_action_name;
	}
}



void input_condition_name_for_decision_table(char *condition_name){
	if(state != state_input_condition_name){
		fprintf(stderr,"%sのinput_condition_name_for_decision_tableでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"条件名の入力待ち状態ではない\n");
		exit(EXIT_FAILURE);
	}
	
	int s_condition_id = 0;
	for(s_condition_id = 0; s_condition_id< my_table->condition_num;s_condition_id++)
	if(my_table->condition_name[s_condition_id] == NULL){
		my_table->condition_name[s_condition_id] = make_str_array(condition_name);
		break;
	}
	if(s_condition_id == my_table->condition_num - 1)
		state = state_input_action_name;
}



void input_action_name_for_decision_table(char *action_name){
	if(state != state_input_action_name){
		fprintf(stderr,"%sのinput_action_name_for_decision_tableでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"動作名の入力待ち状態ではない\n");
		exit(EXIT_FAILURE);
	}
	int action_id = 0;
	for(action_id = 0; action_id < my_table->action_num;action_id++)
	if(my_table->action_name[action_id] == NULL){
		my_table->action_name[action_id] = make_str_array(action_name);
		break;
	}
	if(action_id == my_table->action_num - 1)
		state = state_input_action_bool_value;
}



void input_bool_value_for_action(int line_id,int row_id,enum Bool_value result){
	my_table->action_decision_table[line_id][row_id] = result;
	return;
}



void output_decision_table(){
	FILE *fp = output_fileopen();
	output_name(fp,*my_table);
	output_number(fp,*my_table);
	output_condition(fp,*my_table);
	output_action(fp,*my_table);
	free_decision_table(my_table);
	fclose(fp);
	return;
}



FILE *output_fileopen(){
	FILE *fp;
	static int output_counter = 0;
	char filename_buf[BUFSIZE];
	
	get_output_filename(filename_buf);
	output_counter++;
	if(output_counter <= 1){
		if((fp = fopen(filename_buf,"w")) == NULL){
			fprintf(stderr,"ファイル出力時のファイルオープンに失敗\n");
			exit(EXIT_FAILURE);
		}
		return fp;
	}
	if((fp = fopen(filename_buf,"a")) == NULL){
		fprintf(stderr,"ファイル出力時のファイルオープンに失敗\n");
		exit(EXIT_FAILURE);
	}
	return fp;
}



void output_number(FILE *fp,decision_table target_table){
	char number_buf[BUFSIZE];
	output_delimchar(fp);
	for(int row_id = 0; row_id < target_table.row_num;row_id++){
		output_delimchar(fp);
		sprintf(number_buf,"#%d",row_id + 1);
		output_one_field(fp,number_buf);
	}
	output_return(fp);
	return;
}



void output_name(FILE *fp,decision_table target_table){
	char class_name_buf[BUFSIZE];
	char definition_name_buf[BUFSIZE];
	
	sprintf(class_name_buf,"クラス名 : %s",target_table.class_name);
	output_one_field(fp,class_name_buf);
	output_return(fp);
	
	if(target_table.type == function){
		sprintf(definition_name_buf,"関数名 : %s",target_table.definition_name);
		output_one_field(fp,definition_name_buf);
		output_return(fp);
		return;
	}
	return;
}



void output_condition(FILE *fp,decision_table target_table){

	//ここから、通常時の処理
	for(int condition_id = 0;condition_id < target_table.condition_num;condition_id++){
		output_one_field(fp,"Condition");
		output_delimchar(fp);
		output_one_field(fp,target_table.condition_name[condition_id]);
		for(int row_id = 0;row_id < target_table.row_num;row_id++){
			if(target_table.condition_decision_table[condition_id][row_id] == value_true){
				output_delimchar(fp);
				output_one_field(fp,"T");
			}else if(target_table.condition_decision_table[condition_id][row_id] == value_false){
				output_delimchar(fp);
				output_one_field(fp,"F");
			}
		}
		output_return(fp);
	}
	return;
}



void output_action(FILE *fp,decision_table target_table){
	for(int action_id = 0;action_id < target_table.action_num;action_id++){
		output_one_field(fp,"Action");
		output_delimchar(fp);
		output_one_field(fp,target_table.action_name[action_id]);
		for(int row_id = 0;row_id < target_table.row_num;row_id++){
			if(target_table.action_decision_table[action_id][row_id] == value_true){
				output_delimchar(fp);
				output_one_field(fp,"T");
			}else if(target_table.action_decision_table[action_id][row_id] == value_false){
				output_delimchar(fp);
				output_one_field(fp,"F");
			}
		}
		output_return(fp);
	}
	output_return(fp);
	output_return(fp);
	output_return(fp);

	return;
}



void free_decision_table(decision_table *target_table){
	state = yet;
	for(int condition_id = 0;condition_id < target_table->condition_num;condition_id++){
		free(target_table->condition_decision_table[condition_id]);
		free(target_table->condition_name[condition_id]);
	}
	free(target_table->condition_decision_table);
	free(target_table->condition_name);
	
	for(int action_id = 0;action_id < target_table->action_num;action_id++){
		free(target_table->action_decision_table[action_id]);
		free(target_table->action_name[action_id]);
	}
	
	free(target_table->action_decision_table);
	free(target_table->action_name);
	
	free(target_table->class_name);
	free(target_table->definition_name);
	free(target_table);
	return;
}


//------------------下位の処理--------------------------

enum Bool_value **make_new_bool_value_array(int _line_num,int _row_num){
	enum Bool_value **new_array = (enum Bool_value **)malloc(sizeof(enum Bool_value *) * _line_num);
	for(int line = 0; line < _line_num;line++)
		new_array[line] = (enum Bool_value *)malloc(sizeof(enum Bool_value) * _row_num);
	
	for(int line = 0;line < _line_num;line++)
		for(int row = 0; row < _row_num;row++)
			new_array[line][row] = no_value;
	
		return new_array;
}



char **make_new_char_array(int line_num){
	char **new_array = (char **)malloc(sizeof(char **)*line_num);
	for(int line = 0; line < line_num; line++)
		new_array[line] = NULL;
	
	return new_array;
}



char *make_str_array(char *buf){
	int str_len = strlen(buf);
	char *new_str = (char *)malloc(sizeof(char) * (str_len +1) );
	sprintf(new_str,"%s",buf);
	
	return new_str;
}



void fill_condition_decision_table(enum Bool_value **target,int condition_num){
	int row_num = pow(2,condition_num);
	for(int condition_id = 0;condition_id < condition_num;condition_id++)
		for(int row_id = 0;row_id < row_num; row_id++)
	my_table->condition_decision_table[condition_id][row_id] = decide_periodically_bool_value(pow(2,condition_num - condition_id),row_id);
		}


enum Bool_value decide_periodically_bool_value(int cycle,int this_number){
	int over_num = this_number % cycle;
	if(over_num < cycle / 2 )
		return value_true;
	return value_false;
}

void output_one_field(FILE *fp,char *str){
	if(ENCLOSE_SYMBOL == NULL){
		fprintf(fp,"%s",str);
		
	}else if(strcmp(ENCLOSE_SYMBOL,"\"") == 0){
		char tmp_buf[BUFSIZE];
		int tmp_buf_index = 0;
		int source_len = strlen(str);
		for(int source_index = 0;source_index < source_len;source_index++){
			if(str[source_index] == '\"'){
				tmp_buf[tmp_buf_index] = '\"';
				tmp_buf_index++;
					}
			
			tmp_buf[tmp_buf_index] = str[source_index];
			tmp_buf_index++;
		}
		tmp_buf[tmp_buf_index] = '\0';
		fprintf(fp,"%s%s%s",ENCLOSE_SYMBOL,tmp_buf,ENCLOSE_SYMBOL);
		
	}else{
		fprintf(fp,"%s%s%s",ENCLOSE_SYMBOL,str,ENCLOSE_SYMBOL);
	}
}


void output_delimchar(FILE *fp){
	fprintf(fp,"%s",DELIMCHAR);
	
}


void output_return(FILE *fp){
	fprintf(fp,"\n");
	
}



//--------------一時的--------------------------
void output_operation_decision_table(char *name){
	FILE *fp = output_fileopen();
	fprintf(fp,"操作名 : %s\n",name);
	fprintf(fp,"No_decision_table\n\n\n");
	fclose(fp);
}

//------------------decision_tableへの参照--------------------

enum Bool_value get_bool_value_from_condition_decision_table(int line_id,int row_id){
	if(state != state_input_action_bool_value){
		fprintf(stderr,"%sのinput_bool_value_for_decision_tableでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"動作の真偽値の入力待ち状態ではない\n");
		exit(EXIT_FAILURE);
	}
	return my_table->condition_decision_table[line_id][row_id];
}


int get_row_num(){
	return my_table->row_num;
}




//-------------------データの表示------------------

void look_decision_table(){
	printf("クラス名 : %s\n",my_table->class_name);
	if(my_table->type == function)
	printf("定義の種類 : 関数\n");
	printf("定義名 : %s\n",my_table->definition_name);
	printf("条件数: %d\n",my_table->condition_num);
	printf("動作数 : %d\n",my_table->action_num);
	printf("列数 : %d\n",my_table->row_num);
	printf("条件一覧\n");
	for(int i = 0; i<my_table->condition_num;i++)
		printf("%d : %s\n",i,my_table->condition_name[i]);
	printf("動作一覧\n");
	for(int i = 0; i < my_table->action_num;i++)
		printf("%d : %s\n",i,my_table->action_name[i]);
	printf("条件のデシジョンテーブル\n");
	for(int line_id = 0;line_id < my_table->condition_num;line_id++){
	for(int row_id = 0;row_id < my_table->row_num;row_id++){
		if(my_table->condition_decision_table[line_id][row_id] == value_true){
		printf("T ");
		}else if(my_table->condition_decision_table[line_id][row_id] == value_false){
			printf("F ");
		}else{
			printf("  ");
			}
		}
		printf("\n");
	}
		for(int line_id = 0;line_id < my_table->action_num;line_id++){
	for(int row_id = 0;row_id < my_table->row_num;row_id++){
		if(my_table->action_decision_table[line_id][row_id] == value_true){
		printf("T ");
		}else if(my_table->action_decision_table[line_id][row_id] == value_false){
			printf("F ");
		}else{
			printf("  ");
			}
		}
		printf("\n");
	}
	
	
	return;
}



