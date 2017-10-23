#include "../header_for_make_decision_table_from_vdm.h"
#include "header_for_condition_list.h"

#define THIS_FILE_NAME "condition_list.c"


static condition_list condition_list_root;
static condition_list *condition_list_index = &condition_list_root;
static enum State_of_make_condition_list state = Not_doing;
static int condition_node_counter = 0;

lexeme_list *make_new_lexeme_node_for_condition_list(int lexeme_id,char *lexeme_str,lexeme_list *back_node);
condition_list *make_new_condition_node();
void free_condition_node(condition_list *target_node);

void begin_add_a_condition_node(enum Condition_type type){
	if(state != Not_doing){
		fprintf(stderr,"%sのbegin_input_conditionでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"条件式の入力中なのに呼ばれた\n");
		exit(EXIT_FAILURE);
	}
	state = Doing;
	condition_list_index->next = make_new_condition_node();
	condition_list_index->next->type = type;
	condition_list_index = condition_list_index->next;
}



//一つの条件式が長い場合、処理に時間がかかるので注意
void add_lexeme_data_for_condition_node(int lexeme_id,char *lexeme_str){
	if(state != Doing){
		fprintf(stderr,"%sのadd_lexeme_data_for_condition_nodeでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"条件式の入力を開始してないのに呼ばれた\n");
		exit(EXIT_FAILURE);
	}
	
	if(lexeme_str == NULL){
		fprintf(stderr,"%sのadd_lexeme_data_for_condition_nodeでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"ヌルポインターを渡された\n");
		exit(EXIT_FAILURE);
	}
	
	int condition_lexeme_len = strlen(lexeme_str);
	if(condition_lexeme_len < 1){
		fprintf(stderr,"%sのadd_lexeme_data_for_condition_nodeでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"文字列の長さが0以下\n");
		exit(EXIT_FAILURE);
	}
	
	lexeme_list *lexeme_list_index = &(condition_list_index->lexeme_list_root);
	while(lexeme_list_index->next != NULL)
		lexeme_list_index = lexeme_list_index->next;
	
	lexeme_list *back_node = lexeme_list_index;
	if(lexeme_list_index == &(condition_list_index->lexeme_list_root))
				back_node = NULL;
		
	lexeme_list_index->next = make_new_lexeme_node_for_condition_list(lexeme_id,lexeme_str,back_node);
	
}



void end_add_a_condition_node(){
	if(state != Doing){
		fprintf(stderr,"%sのend_add_a_condition_nodeでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"条件式の入力を開始してないのに呼ばれた\n");
		exit(EXIT_FAILURE);
	}
	
	condition_list_index->condition_branch_tree_root = make_condition_branch_tree_root(condition_list_index->lexeme_list_root.next);
	
	state = Not_doing;
}



condition_list *make_new_condition_node(){
	condition_list *new_condition_node = (condition_list *)malloc(sizeof(condition_list));
	new_condition_node->next = NULL;
	new_condition_node->lexeme_list_root.next = NULL;
	new_condition_node->node_id = condition_node_counter;
	new_condition_node->condition_branch_tree_root = NULL;
	new_condition_node->type = undefined;
	condition_node_counter++;
	return new_condition_node;
	
}

lexeme_list *make_new_lexeme_node_for_condition_list(int lexeme_id,char *lexeme_str,lexeme_list *back_node){
	lexeme_list *new_lexeme_node = (lexeme_list *)malloc(sizeof(lexeme_list));
	new_lexeme_node->next = NULL;
	new_lexeme_node->back = back_node;
	new_lexeme_node->lexeme_id = lexeme_id;
	new_lexeme_node->lexeme_str = (char *)malloc(sizeof(char) * (strlen(lexeme_str) + 1));
	sprintf(new_lexeme_node->lexeme_str,"%s",lexeme_str);
	return new_lexeme_node;
}



//condition_listからのデータ参照
int get_condition_id_about_under_construction(){
	if(state != Doing){
	fprintf(stderr,"%sの \n get_id_about_under_constructionでエラー\n",THIS_FILE_NAME);
	fprintf(stderr,"条件式の入力中ではないのに呼ばれた\n");
	exit(EXIT_FAILURE);
	}
	return condition_list_index->node_id;
	
}


int get_condition_number(){
	if(state != Not_doing){
	fprintf(stderr,"%sの \n get_condition_number()でエラー\n",THIS_FILE_NAME);
	fprintf(stderr,"条件式の入力中なのに呼ばれた\n");
	exit(EXIT_FAILURE);
	}
	
	return condition_node_counter;
}


enum State_of_make_condition_list condition_list_state(){
	return state;
}

enum Function_result pre_or_post_condition_is_true(){
	condition_list *index = condition_list_root.next;
	
	for(int condition_counter = 0; condition_counter < condition_node_counter;condition_counter++){
		if(index->type == pre_condition || index->type == post_condition)
			if(condition_branch_tree_return_true(index->condition_branch_tree_root) == Result_false)
				return Result_false;
		index = index->next;
	}
	return Result_true;
}


enum Function_result condition_is_true(int condition_id){
	condition_list *index = condition_list_root.next;
	
	for(int condition_counter = 0; condition_counter < condition_id;condition_counter++){
		index = index->next;
	}
	
	return condition_branch_tree_return_true(index->condition_branch_tree_root);
}


void free_condition_list(){
	
	condition_list *index = &condition_list_root;
	condition_list *index_next = index->next;
	
	while(index_next != NULL){
		index = index_next;
		index_next = index_next->next;
		free_condition_node(index);
	}
	condition_node_counter = 0;
	state = Not_doing;
	condition_list_root.next = NULL;
	condition_list_index = &condition_list_root;
	free_single_condition_list();
}

void free_condition_node(condition_list *target_node){
	lexeme_list *index = &(target_node->lexeme_list_root);
	lexeme_list *index_next = index->next;
	
	while(index_next != NULL){
		index = index_next;
		index_next = index_next->next;
		free(index->lexeme_str);
		free(index);
	}
	condition_branch_tree_free(target_node->condition_branch_tree_root);
	
	free(target_node);
}


void look_condition_list(){
	
	lexeme_list *index;
	condition_list *c_index = condition_list_root.next;
	while(c_index != NULL){
		index = c_index->lexeme_list_root.next;
		while(index != NULL){
			printf("%s : ",index->lexeme_str);
			printf("%d\n",index->lexeme_id);
			index = index->next;
		}
		look_condition_branch_tree(c_index->condition_branch_tree_root);
	
		printf("\n");
		c_index = c_index->next;
	}
}

