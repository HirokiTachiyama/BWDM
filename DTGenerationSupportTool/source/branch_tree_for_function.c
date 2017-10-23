#include "header_for_make_decision_table_from_vdm.h"


void study_what_formula(branch_tree_node **target_place,enum Condition_type type);
void if_formula_process(branch_tree_node *target_node,enum Condition_type type);
void action_formula_process(branch_tree_node *target_node,enum Condition_type type);
branch_tree_node *make_new_branch_tree_node();
void let_formula_process(branch_tree_node **target_node,enum Condition_type type);
void return_bool_value_action_process(branch_tree_node *target_node);
void add_pre_and_post_condition_for_function();
void add_pre_condition_for_function();
void add_post_condition_for_function();
enum Function_result begin_or_end_add_pre_and_post_condition();
enum Function_result end_action_formula_process();

branch_tree_node *extract_branch_tree_from_function(){
	make_new_action_list();
	
	add_pre_and_post_condition_for_function();
	lexeme_list_in_function_index_init();
	
	while(1){
		if(get_lexeme_id_from_lexeme_node_in_function() == LEXEME_ID_TWO_EQUAL)
			break;
		
		if(can_go_next_lexeme_node_in_function() == Result_false){
			fprintf(stderr,"関数定義に==がない\n");
			exit(EXIT_FAILURE);
		}
		go_next_lexeme_node_in_function();
	}
	
	go_next_lexeme_node_in_function();

	branch_tree_node *root;
	study_what_formula(&root,normal_condition);
	return root;
	
}


void study_what_formula(branch_tree_node **target_place,enum Condition_type type){
	
	if(get_lexeme_id_from_lexeme_node_in_function() == LEXEME_ID_ELSE)
		go_next_lexeme_node_in_function();
	
	switch(get_lexeme_id_from_lexeme_node_in_function()){
	  case LEXEME_ID_IF:
		*target_place = make_new_branch_tree_node();
		if_formula_process(*target_place,type);
		break;
		
	  case LEXEME_ID_ELSEIF:
		*target_place = make_new_branch_tree_node();
		if_formula_process(*target_place,type);
		break;
		
	  case LEXEME_ID_LET:
		let_formula_process(target_place,type);
		break;
		
	  default:
		*target_place = make_new_branch_tree_node();
		action_formula_process(*target_place,type);
		break;
	}
}

void if_formula_process(branch_tree_node *target_node,enum Condition_type type){
	char lexeme_buf[BUFSIZE];
	int lexeme_id;
	go_next_lexeme_node_in_function();
	begin_add_a_condition_node(type);
	target_node->type = branch_node;
	target_node->condition_id = get_condition_id_about_under_construction();
	
	while(get_lexeme_id_from_lexeme_node_in_function() != LEXEME_ID_THEN){
		get_str_from_lexeme_node_in_function(lexeme_buf);
		lexeme_id = get_lexeme_id_from_lexeme_node_in_function();
		add_lexeme_data_for_condition_node(lexeme_id,lexeme_buf);
		go_next_lexeme_node_in_function();
	}
	end_add_a_condition_node();
	go_next_lexeme_node_in_function();
	study_what_formula(&(target_node->true_branch),type);
	study_what_formula(&(target_node->false_branch),type);
}



void action_formula_process(branch_tree_node *target_node,enum Condition_type type){
	//関数型がブール型であれば、動作を条件に
	if(get_return_value_type_from_function_node() == bool_type){
		if(option_not_output_return_bool_value_in_function() == Result_false){
			return_bool_value_action_process(target_node);
			return;
		}
	}
	
	char str_buf[BUFSIZE];
	int lexeme_id = 0;
	if(type != normal_condition){
		goto NEXT;
	}
	
	char action_name[BUFSIZE];
	int action_name_len = 0;
	int lexeme_counter = 0;
	
	while(end_action_formula_process() == Result_false){
		if(lexeme_counter > 0){
			sprintf(action_name + action_name_len," ");
			action_name_len++;
		}
		
		get_str_from_lexeme_node_in_function(action_name + action_name_len);
		action_name_len = strlen(action_name);
		go_next_lexeme_node_in_function();
		lexeme_counter++;
	}
	
	target_node->action_id = add_action_node(action_name);
	target_node->type = action;
	target_node = NULL;
	return;
	
	
  NEXT:
	begin_add_a_condition_node(type);
	while(end_action_formula_process() == Result_false){
		get_str_from_lexeme_node_in_function(str_buf);
		lexeme_id = get_lexeme_id_from_lexeme_node_in_function();
		add_lexeme_data_for_condition_node(lexeme_id,str_buf);
		go_next_lexeme_node_in_function();
	}
	end_add_a_condition_node();

}


void let_formula_process(branch_tree_node **target_node,enum Condition_type type){
	char buf[BUFSIZE];
	int buf_len = 0;
	go_next_lexeme_node_in_function();
	int lexeme_counter = 0;
	while(get_lexeme_id_from_lexeme_node_in_function() != LEXEME_ID_IN){
		if(lexeme_counter > 0){
			sprintf(buf + buf_len," ");
			buf_len++;
		}		
		get_str_from_lexeme_node_in_function(buf + buf_len);
		buf_len = strlen(buf);
		go_next_lexeme_node_in_function();
		lexeme_counter++;
	}
	
	
	go_next_lexeme_node_in_function();
	study_what_formula(target_node,type);
	
	
}

void add_pre_and_post_condition_for_function(){
	while(begin_or_end_add_pre_and_post_condition() == Result_false)
		go_next_lexeme_node_in_function();
	
	if(can_go_next_lexeme_node_in_function() == Result_false)
		return;
	
	switch(get_lexeme_id_from_lexeme_node_in_function()){
	  case LEXEME_ID_PRE:
		add_pre_condition_for_function();
		break;
		
	  case LEXEME_ID_POST:
		add_post_condition_for_function();
		break;
	}
	
}

void add_pre_condition_for_function(){
	char lexeme_buf[BUFSIZE];
	int lexeme_id;
	go_next_lexeme_node_in_function();
	branch_tree_node *tmp_node;
	study_what_formula(&tmp_node,pre_condition);
	add_pre_and_post_condition_for_function();
}



void add_post_condition_for_function(){
	char lexeme_buf[BUFSIZE];
	int lexeme_id;
	go_next_lexeme_node_in_function();
	begin_add_a_condition_node(post_condition);
	
	while(begin_or_end_add_pre_and_post_condition() != Result_true){
		get_str_from_lexeme_node_in_function(lexeme_buf);
		lexeme_id = get_lexeme_id_from_lexeme_node_in_function();
		add_lexeme_data_for_condition_node(lexeme_id,lexeme_buf);
		go_next_lexeme_node_in_function();
	}
	end_add_a_condition_node();
	
	add_pre_and_post_condition_for_function();
}

enum Function_result begin_or_end_add_pre_and_post_condition(){
	if(can_go_next_lexeme_node_in_function() == Result_false)
		return Result_true;
	
	switch(get_lexeme_id_from_lexeme_node_in_function()){
		case LEXEME_ID_PRE:
		case LEXEME_ID_POST:
		return Result_true;
		
	  default:
		break;
	}
	return Result_false;

}

enum Function_result end_action_formula_process(){
	if(can_go_next_lexeme_node_in_function() == Result_false)
		return Result_true;
	
	switch(get_lexeme_id_from_lexeme_node_in_function()){
	  case LEXEME_ID_ELSE:
	  case LEXEME_ID_ELSEIF:
	  case LEXEME_ID_IF:
	  case LEXEME_ID_LET:
	  case LEXEME_ID_THEN:
	  case LEXEME_ID_PRE:
	  case LEXEME_ID_POST:
	  case LEXEME_ID_SEMICOLON:
			return Result_true;
		
	  default:
		return Result_false;
	}
}


void return_bool_value_action_process(branch_tree_node *target_node){
	char lexeme_buf[BUFSIZE];
	int lexeme_id;
	begin_add_a_condition_node(normal_condition);
	target_node->condition_id = get_condition_id_about_under_construction();
	
	while(end_action_formula_process() == Result_false){
		get_str_from_lexeme_node_in_function(lexeme_buf);
		lexeme_id = get_lexeme_id_from_lexeme_node_in_function();
		add_lexeme_data_for_condition_node(lexeme_id,lexeme_buf);
		go_next_lexeme_node_in_function();
	}
	target_node->true_branch = make_new_branch_tree_node();
	target_node->false_branch = make_new_branch_tree_node();
	
	char function_name_buf[BUFSIZE];
	get_function_name_from_function_node(function_name_buf);
	
	target_node->true_branch->action_id = add_action_node(function_name_buf);
	target_node->true_branch->type = action;
	target_node->false_branch->type = action;

	end_add_a_condition_node();
	if(can_go_next_lexeme_node_in_function() == Result_true)
		go_next_lexeme_node_in_function();

	
}

branch_tree_node *make_new_branch_tree_node(){
	branch_tree_node *new_node = (branch_tree_node *)malloc(sizeof(branch_tree_node));
	new_node->type = not_yet_decided;
	new_node->condition_id = -1;
	new_node->action_id = -1;
	new_node->next = NULL;
	new_node->true_branch = NULL;
	new_node->false_branch = NULL;
	
	return new_node;
}


