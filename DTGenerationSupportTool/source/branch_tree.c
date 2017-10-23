#include "header_for_make_decision_table_from_vdm.h"

enum Bool_value search_function_branch_tree(int action_number,branch_tree_node *target_node);


void make_decision_table_for_function(branch_tree_node *root){
	/*------------------------------------------------------------------------------------
	この関数は記述部にあたる。
	どのクラス定義も実行経路データからデシジョンテーブルを生成する際の規則は同じだと思うが、
	デシジョンテーブルにクラス定義名(関数名 : FizzBuzz など)を記述するようにしたため、
	それぞれのクラス定義から実行経路データ以外の情報を取得する必要があり、この記述部は関数定義にしか使えない。
	---------------------------------------------------------------------------------------
	*/
	char buf[BUFSIZE];
	begin_make_decision_table();
	get_class_name_from_class_node(buf);
	input_class_name_for_decision_table(buf);
	input_definition_type_for_decision_table(function);
	get_function_name_from_function_node(buf);
	input_definition_name_for_decision_table(buf);
	input_condition_num_for_decision_table(get_single_condition_number());
	input_action_num_for_decision_table(get_action_node_number());
	for(int s_condition_id = 0;s_condition_id < get_single_condition_number();s_condition_id++){
		search_single_condition_str(s_condition_id,buf);
		input_condition_name_for_decision_table(buf);
	}
	for(int action_id = 0;action_id < get_action_node_number();action_id++){
		get_action_name(action_id,buf);
		input_action_name_for_decision_table(buf);
	}
	if(option_action_value_empty_in_function() == Result_true)
	return;
	make_new_bool_value_array_for_search_condition_branch_tree();
	int line_num = get_action_node_number();
	int row_num = get_row_num();
	enum Bool_value input_bool_value;
	
	for(int row_id = 0; row_id < row_num ;row_id++){
		set_bool_value_for_search_condition_branch_tree(row_id);
		for(int line_id = 0;line_id < line_num;line_id++){

			input_bool_value = search_function_branch_tree(line_id,root);
			if(pre_or_post_condition_is_true() == Result_false)
				input_bool_value = value_false;

			input_bool_value_for_action(line_id,row_id,input_bool_value);
		}
	}
	
	
	return;
}

enum Bool_value search_function_branch_tree(int action_number,branch_tree_node *target_node){	
	if(target_node->type == action){
		if(target_node->action_id == action_number)
			return value_true;
		
		return value_false;
	}
	if(condition_is_true(target_node->condition_id) == Result_true)
		return search_function_branch_tree(action_number,target_node->true_branch);
	
	return search_function_branch_tree(action_number,target_node->false_branch);
}


void free_function_branch_tree(branch_tree_node *target){
	if(target->next != NULL)
		free_function_branch_tree(target->next);
	
	if(target->true_branch != NULL)
		free_function_branch_tree(target->true_branch);
	
	if(target->false_branch != NULL)
		free_function_branch_tree(target->false_branch);
	
	free(target);
	
}
