#include "header_for_make_decision_table_from_vdm.h"
#define THIS_FILE_NAME "look_internal_date.c"

void look_lexeme_list(){
	char lexeme_str_buf[BUFSIZE];
	
	lexeme_list_index_init();
	while(can_go_next_lexeme_node() != Result_false){
		memset(lexeme_str_buf,NULLCHAR,BUFSIZE);
		printf("%d :",get_lexeme_id_from_lexeme_node());
		
		get_lexeme_str_from_lexeme_node(lexeme_str_buf);
		printf("%s\n",lexeme_str_buf);
		go_next_lexeme_node();
	}
}


void print_access_modifier(enum Access_modifier modifier);
void print_static_info(enum Function_result is_index_point_static_definition);
void printf_return_type();


void look_class_list(){
	char buf[BUFSIZE];
	class_list_index_init();
	
	while(can_go_next_class_node() == Result_true){
		get_class_name_from_class_node(buf);
		printf("\n\nclass_name : %s\n",buf);
		while(can_go_next_function_node() == Result_true){
			get_function_name_from_function_node(buf);
			printf("\nfunction_name : %s\n",buf);
			print_access_modifier(get_access_modifier_from_function_node());
			print_static_info(is_this_function_static());
			printf_return_type();
			while(can_go_next_lexeme_node_in_function() == Result_true){
				get_str_from_lexeme_node_in_function(buf);
				printf("%d : %s\n",get_lexeme_id_from_lexeme_node_in_function(),buf);
				go_next_lexeme_node_in_function();
			}
			go_next_function_node();
		}
		
		while(can_go_next_operation_node() == Result_true){
			get_operation_name_from_operation_node(buf);
			printf("\noperation_name : %s\n",buf);
			print_access_modifier(get_access_modifier_from_operation_node());
			print_static_info(is_this_operation_static());
			while(can_go_next_lexeme_node_in_operation() == Result_true){
				get_str_from_lexeme_node_in_operation(buf);
				printf("%d : %s\n",get_lexeme_id_from_lexeme_node_in_operation(),buf);
				go_next_lexeme_node_in_operation();
			}
			go_next_operation_node();
		}

		
		
		
		go_next_class_node();
	
	}
	return;
}

void print_access_modifier(enum Access_modifier modifier){
	switch(modifier){
	  case private:
		printf("Access_modifier : private\n");
		break;
		
	  case protected:
		printf("Access_modifier : protected\n");
		break;
		
	  case public:
		printf("Access_modifier : public\n");
		break;
		
	  default:
		fprintf(stderr,"%sのprint_access_modifier()でエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"アクセス修飾子の不一致\n");
		break;
	}
	return;
	
}


void print_static_info(enum Function_result is_index_point_static_definition){
	if(is_index_point_static_definition == Result_true){
		printf("static? : yes\n");
	}else{
		printf("static? : no\n");
	}
	
	return;
}

void printf_return_type(){
	printf("戻り値の型 :");
	switch(get_return_value_type_from_function_node()){
	  case bool_type:
		printf("bool型\n");
		break;
	  default:
		printf("未定義\n");
		break;
				}
	return;
}

void look_action_list(){
	char buf[BUFSIZE];
	int action_num = get_action_node_number();
	for(int id = 0; id < action_num;id++){
	get_action_name(id,buf);
	printf("%s\n",buf);
	}
}