#include "../header_for_make_decision_table_from_vdm.h"
#include "header_for_class.h"
#define THIS_FILE_NAME "./class_definition/class_definition.c"


static class_list class_root;
static class_list *index_class = NULL;

void add_new_class_definition();
char *get_class_name();
void make_new_class_definition_node();
void extract_each_definition(class_list *index_class);

void extract_class_definition(){
	lexeme_list_index_init();
	index_class = &class_root;

	index_class->next = NULL;
	
	while(end_whole_class_definition() == Result_false){
		if(get_lexeme_id_from_lexeme_node() == LEXEME_ID_CLASS)
			add_new_class_definition();
		if(can_go_next_lexeme_node() != Result_false)
			go_next_lexeme_node(index_class);
	}
	
	
	return;
}


void add_new_class_definition(){
	//この時のlexeme_idはclassのid
	if(get_lexeme_id_from_lexeme_node() != LEXEME_ID_CLASS){
		fprintf(stderr,"class_definition.cのadd_new_class_definitionでエラー\n");
		fprintf(stderr,"クラス定義開始時のIDがCLASSのIDと一致しない\n");
		exit(EXIT_FAILURE);
	}
	make_new_class_definition_node();
	index_class->class_name = get_class_name();
	go_next_lexeme_node();
	
	//このときのlexeme_idは定義の種類(function operationなど)
	while(end_a_class_definition() == Result_false){
		extract_each_definition(index_class);
	}
	return;
}

char *get_class_name(){
	go_next_lexeme_node();
	return get_lexeme_str_without_escape_string();
}

void make_new_class_definition_node(){
	index_class->next = (class_list *)malloc(sizeof(class_list));
	index_class = index_class->next;
	index_class->next = NULL;
	index_class->operation_definitions.next = NULL;
	index_class->function_definitions.next= NULL;
	
	return;
}


void extract_each_definition(class_list *index_class){
	switch(get_lexeme_id_from_lexeme_node()){
	  case LEXEME_ID_FUNCTIONS:
		extract_function_definition(index_class);
		break;
		
	  case LEXEME_ID_TYPES:
		while(get_lexeme_id_from_lexeme_node() != LEXEME_ID_FUNCTIONS && end_a_class_definition() == Result_false){
			go_next_lexeme_node();
		}
		break;
		
	  case LEXEME_ID_OPERATIONS:
		extract_operation_definition(index_class);
		break;
		
		
	  default:
		fprintf(stderr,"%sのwrite_each_definition()でエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"LEXEME_IDがどの定義の種類を示すLEXEME_IDとも一致しない\n");
		fprintf(stderr,"LEXEME_ID : %d\n",get_lexeme_id_from_lexeme_node());
		fprintf(stderr,"システムを終了します\n");
		exit(EXIT_FAILURE);
		break;
	}
	
	return;
}



//-------------classへの参照----------------------

void class_list_index_init(){
	index_class = class_root.next;
	function_list_index_init(index_class);
	operation_list_index_init(index_class);
	return;
}

void go_next_class_node(){
	if(index_class == NULL){
		fprintf(stderr,"%sのgo_next_class_node()でエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"index_class->nextがNULLなのに呼び出された\n");
		return;
	}
	index_class = index_class->next;
	if(index_class != NULL){
		function_list_index_init(index_class);
		operation_list_index_init(index_class);
	}
	return;
}

enum Function_result can_go_next_class_node(){
	if(index_class != NULL)
		return Result_true;
	
	return Result_false;
}


void get_class_name_from_class_node(char *class_name_buf){
	if(index_class == NULL){
		fprintf(stderr,"%sのget_class_name_from_class_node()でエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"index_classがNULLなのに呼び出された\n");
		return;
	}
	sprintf(class_name_buf,"%s",index_class->class_name);
	return;
}



