#include "../header_for_make_decision_table_from_vdm.h"
#include "header_for_class.h"
#define THIS_FILE_NAME "operation_definition.c"


lexeme_list *operation_lexeme_list_for_operation();
operation_list *make_new_operation_node();
enum Function_result end_whole_operation_definition();
enum Function_result end_a_operation_definition();
enum Variable_type operation_return_type();



void extract_operation_definition(class_list *target_class_node){
	go_next_lexeme_node();
		operation_list *operation_index = &(target_class_node->operation_definitions);
	while(end_whole_operation_definition() == Result_false){
		operation_index->next = make_new_operation_node();
		operation_index = operation_index->next;
		operation_index->modifier = extract_access_modifier_information();
		operation_index->is_static_operation = extract_static_information();
		operation_index->operation_name = get_lexeme_str_without_escape_string();
		operation_index->lexeme_list_root.next = operation_lexeme_list_for_operation();
	}
	return;
	
}



lexeme_list *operation_lexeme_list_for_operation(){
	go_next_lexeme_node();
	lexeme_list *tmp_lexeme_root = make_new_lexeme_node();
	lexeme_list *lexeme_index = tmp_lexeme_root;
	
	
	while(end_a_operation_definition() == Result_false){
		lexeme_index->next = make_new_lexeme_node();
		lexeme_index = lexeme_index->next;
		lexeme_index->str = get_lexeme_str_without_escape_string();
		lexeme_index->lexeme_id = get_lexeme_id_from_lexeme_node();
		go_next_lexeme_node();
	}

	lexeme_list *real_lexeme_root = tmp_lexeme_root->next;
	if(real_lexeme_root  == NULL){
		fprintf(stderr,"%sのoperation_lexeme_list_for_operationでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"operation_listのルートが存在しない\n");
	}
	
	free(tmp_lexeme_root);	
	return real_lexeme_root;
}



operation_list *make_new_operation_node(){
	operation_list *new_operation_node = malloc(sizeof(operation_list));
	new_operation_node->next = NULL;
	return new_operation_node;
}

enum Function_result end_whole_operation_definition(){
	if(end_a_class_definition() == Result_true)
		return Result_true;
	
	if(lexeme_id_is_appointname_definition_type() == Result_true)
		return Result_true;
	
	return Result_false;
}

enum Function_result end_a_operation_definition(){
	if(end_whole_operation_definition() == Result_true)
		return Result_true;
	
	if(lexeme_id_define_other_operation() == Result_true)
		return Result_true;
	
	if(get_lexeme_id_from_lexeme_node() == LEXEME_ID_SEMICOLON){
		go_next_lexeme_node();
		return Result_true;
	}
	return Result_false;
}



//---------------------------operationに関する情報の参照------------------------


static operation_list *operation_index_to_see = NULL;
static lexeme_list *lexeme_index_to_see = NULL;

void operation_list_index_init(class_list *target_class){
	if(target_class == NULL){
		operation_index_to_see = NULL;
		return;
	}
	operation_index_to_see = target_class->operation_definitions.next;
	lexeme_list_in_operation_index_init();
	return;
}

void lexeme_list_in_operation_index_init(){
	if(operation_index_to_see == NULL){
		lexeme_index_to_see = NULL;
		return;
	}
	lexeme_index_to_see = operation_index_to_see->lexeme_list_root.next;
	return;
}

void go_next_operation_node(){
	if(operation_index_to_see == NULL){
		fprintf(stderr,"%sのgo_next_operation_nodeでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return;
	}
	
	operation_index_to_see = operation_index_to_see->next;
	if(operation_index_to_see != NULL)
		lexeme_list_in_operation_index_init();
	return;
}

enum Access_modifier get_access_modifier_from_operation_node(){
	if(operation_index_to_see == NULL){
		fprintf(stderr,"%sのget_access_modifier_from_operation_node()でエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		exit(EXIT_FAILURE);
	}	
	return operation_index_to_see->modifier;
}

void go_next_lexeme_node_in_operation(){
	if(lexeme_index_to_see == NULL){
		fprintf(stderr,"%sのgo_next_lexeme_node_in_operationでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return;
	}
	
	lexeme_index_to_see = lexeme_index_to_see->next;
	return;
}

enum Function_result can_go_next_operation_node(){
	if(operation_index_to_see != NULL)
		return Result_true;
	
	return Result_false;
}

enum Function_result can_go_next_lexeme_node_in_operation(){
	if(lexeme_index_to_see != NULL)
		return Result_true;
	
	return Result_false;
}

void get_operation_name_from_operation_node(char *operation_name_buf){
	if(operation_index_to_see == NULL){
		fprintf(stderr,"%sのget_operation_name_from_operation_node()でエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return;
	}
	sprintf(operation_name_buf,"%s",operation_index_to_see->operation_name);
	
	return;
}

enum Function_result is_this_operation_static(){
	if(operation_index_to_see->is_static_operation == yes)
		return Result_true;
	
	return Result_false;
}



void get_str_from_lexeme_node_in_operation(char *str_buf){
	if(operation_index_to_see == NULL){
		fprintf(stderr,"%sのget_str_from_lexeme_node_in_operationでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return;
	}
	
	sprintf(str_buf,"%s",lexeme_index_to_see->str);
	return;
}


int get_lexeme_id_from_lexeme_node_in_operation(){
		if(operation_index_to_see == NULL){
		fprintf(stderr,"%sのget_lexeme_id_from_lexeme_node_in_operationでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return -1;
	}
	
	return lexeme_index_to_see->lexeme_id;
}

