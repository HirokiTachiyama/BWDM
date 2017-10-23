#include "../header_for_make_decision_table_from_vdm.h"
#include "header_for_class.h"
#define THIS_FILE_NAME "function_definition.c"

void extract_function_definition(class_list *target_class_node);
lexeme_list *extract_lexeme_list_for_function();
function_list *make_new_function_node();
enum Function_result end_whole_function_definition();
enum Function_result end_a_function_definition();
enum Variable_type extract_return_type();

void extract_function_definition(class_list *target_class_node){
	go_next_lexeme_node();

	function_list *function_index = &(target_class_node->function_definitions);
	while(end_whole_function_definition() == Result_false){
		function_index->next = make_new_function_node();
		function_index = function_index->next;
		function_index->modifier = extract_access_modifier_information();
		function_index->is_static_function = extract_static_information();
		function_index->function_name = get_lexeme_str_without_escape_string();
		function_index->return_type = extract_return_type();
		function_index->lexeme_list_root.next = extract_lexeme_list_for_function();
	}
	return;
}



lexeme_list *extract_lexeme_list_for_function(){
	go_next_lexeme_node();
	lexeme_list *tmp_lexeme_root = make_new_lexeme_node();
	lexeme_list *lexeme_index = tmp_lexeme_root;
	
	
	while(end_a_function_definition() == Result_false){
		lexeme_index->next = make_new_lexeme_node();
		lexeme_index = lexeme_index->next;
		lexeme_index->str = get_lexeme_str_without_escape_string();
		lexeme_index->lexeme_id = get_lexeme_id_from_lexeme_node();
		go_next_lexeme_node();
	}

	lexeme_list *real_lexeme_root = tmp_lexeme_root->next;
	if(real_lexeme_root  == NULL){
		fprintf(stderr,"%sのextract_lexeme_list_for_functionでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"function_listのルートが存在しない\n");
	}
	
	free(tmp_lexeme_root);	
	return real_lexeme_root;
}



function_list *make_new_function_node(){
	function_list *new_function_node = malloc(sizeof(function_list));
	new_function_node->next = NULL;
	return new_function_node;
}

enum Function_result end_whole_function_definition(){
	if(end_a_class_definition() == Result_true)
		return Result_true;
	
	if(lexeme_id_is_appointname_definition_type() == Result_true)
		return Result_true;
	
	return Result_false;
}

enum Function_result end_a_function_definition(){
	if(end_whole_function_definition() == Result_true)
		return Result_true;
	
	if(lexeme_id_define_other_function() == Result_true)
		return Result_true;
	
	if(get_lexeme_id_from_lexeme_node() == LEXEME_ID_SEMICOLON){
		go_next_lexeme_node();
		return Result_true;
	}
	return Result_false;
}

enum Variable_type extract_return_type(){
	while(get_lexeme_id_from_lexeme_node() != LEXEME_ID_RIGHT_ARRAY){
		go_next_lexeme_node();
	}
	go_next_lexeme_node();
	
	switch(get_lexeme_id_from_lexeme_node()){
	  case LEXEME_ID_BOOL:
		return bool_type;
		
	  default:
		return unknown_type;
			}
	
}


//---------------------------functionに関する情報の参照------------------------




static function_list *function_index_to_see = NULL;
static lexeme_list *lexeme_index_to_see = NULL;

void function_list_index_init(class_list *target_class){
	if(target_class == NULL){
		fprintf(stderr,"%sのfunction_list_index_initでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"NULLのポインターを渡された\n");
		return;
	}
	function_index_to_see = target_class->function_definitions.next;
	lexeme_list_in_function_index_init();
	return;
}

void lexeme_list_in_function_index_init(){
	if(function_index_to_see == NULL){
		fprintf(stderr,"%sのlexeme_list_in_function_index_initでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"NULLのポインターを参照\n");
		return;
	}
	lexeme_index_to_see = function_index_to_see->lexeme_list_root.next;
	return;
}

void go_next_function_node(){
	if(function_index_to_see == NULL){
		fprintf(stderr,"%sのgo_next_function_nodeでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return;
	}
	
	function_index_to_see = function_index_to_see->next;
	if(function_index_to_see != NULL)
		lexeme_list_in_function_index_init();
	return;
}

enum Access_modifier get_access_modifier_from_function_node(){
	if(function_index_to_see == NULL){
		fprintf(stderr,"%sのget_access_modifier_from_function_node()でエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		exit(EXIT_FAILURE);
	}	
	return function_index_to_see->modifier;
}

void go_next_lexeme_node_in_function(){
	if(lexeme_index_to_see == NULL){
		fprintf(stderr,"%sのgo_next_lexeme_node_in_functionでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return;
	}
	
	lexeme_index_to_see = lexeme_index_to_see->next;
	return;
}

enum Function_result can_go_next_function_node(){
	if(function_index_to_see != NULL)
		return Result_true;
	
	return Result_false;
}

enum Function_result can_go_next_lexeme_node_in_function(){
	if(lexeme_index_to_see != NULL)
		return Result_true;
	
	return Result_false;
}

void get_function_name_from_function_node(char *function_name_buf){
	if(function_index_to_see == NULL){
		fprintf(stderr,"%sのget_function_name_from_function_node()でエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return;
	}
	sprintf(function_name_buf,"%s",function_index_to_see->function_name);
	
	return;
}

enum Function_result is_this_function_static(){
	if(function_index_to_see->is_static_function == yes)
		return Result_true;
	
	return Result_false;
}

enum Variable_type get_return_value_type_from_function_node(){
	return function_index_to_see->return_type;
}


void get_str_from_lexeme_node_in_function(char *str_buf){
	if(function_index_to_see == NULL){
		fprintf(stderr,"%sのget_str_from_lexeme_node_in_functionでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return;
	}
	
	sprintf(str_buf,"%s",lexeme_index_to_see->str);
	return;
}


int get_lexeme_id_from_lexeme_node_in_function(){
		if(function_index_to_see == NULL){
		fprintf(stderr,"%sのget_lexeme_id_from_lexeme_node_in_functionでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"indexがNULLなのに呼び出された\n");
		return -1;
	}
	
	return lexeme_index_to_see->lexeme_id;
}



