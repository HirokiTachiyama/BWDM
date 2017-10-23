#include "../header_for_make_decision_table_from_vdm.h"
#include "header_for_condition_list.h"

#define THIS_FILE_NAME "./condition_list/condition_branch_tree"

condition_branch_tree *make_condition_branch_tree_node(lexeme_list *first_lexeme,lexeme_list *last_lexeme);
condition_branch_tree *make_condition_branch_tree_leef_node(lexeme_list *first,lexeme_list *last);
int add_single_condition_node(lexeme_list *first,lexeme_list *last);
condition_branch_tree *make_new_condition_branch_tree_node();
int count_condition_branch_tree_depth(condition_branch_tree *target_tree);
void print_condition_tree(int depth,condition_branch_tree *target_node);


static int single_condition_node_counter = 0;
static single_condition_list single_condition_list_root = {0};
static enum Bool_value *bool_value_array;

condition_branch_tree *make_condition_branch_tree_root(lexeme_list *first_lexeme){
	lexeme_list *index = first_lexeme;
	if(index == NULL){
		fprintf(stderr,"%sのmake_condition_branch_treeでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"リストの長さが0のlexeme_listを読みこんだ\n");
		exit(EXIT_FAILURE);
	}
	while(index->next != NULL)
		index = index->next;
	
	lexeme_list *last_lexeme = index;
	
	condition_branch_tree *root = make_condition_branch_tree_node(first_lexeme,last_lexeme);
	return root;
}


condition_branch_tree *make_condition_branch_tree_node(lexeme_list *first_lexeme,lexeme_list *last_lexeme){
	lexeme_list *index = first_lexeme;
	int parenthesis_counter = 0;
	lexeme_list *last_and_lexeme = NULL;
	lexeme_list *last_or_lexeme = NULL;
	
	if(option_not_use_logic_operation() == Result_true)
	return make_condition_branch_tree_leef_node(first_lexeme,last_lexeme);

	
	while(index != NULL){
		if(index == NULL){
			fprintf(stderr,"%sのmake_condition_branch_tree_nodeエラー\n",THIS_FILE_NAME);
			fprintf(stderr,"last_lexemeが見つからない\n");
			exit(EXIT_FAILURE);
		}
		
		if(index->lexeme_id == LEXEME_ID_LANGLE)
			parenthesis_counter++;
		
		if(index->lexeme_id == LEXEME_ID_RANGLE)
			parenthesis_counter--;
		
		if(parenthesis_counter < 0){
			fprintf(stderr,"%sのmake_condition_branch_tree_nodeエラー\n",THIS_FILE_NAME);
			fprintf(stderr,"parenthesis_counterの値が0以下になった\n");
			exit(EXIT_FAILURE);
		}
		
		if(index->lexeme_id == LEXEME_ID_AND && parenthesis_counter == 0)
			last_and_lexeme = index;
		
		if(index->lexeme_id == LEXEME_ID_OR && parenthesis_counter == 0)
			last_or_lexeme = index;
		
		if(index == last_lexeme)
			break;
		index = index->next;
	}
	
	
	
	if(parenthesis_counter != 0){
			fprintf(stderr,"%sのmake_condition_branch_tree_nodeエラー\n",THIS_FILE_NAME);
			fprintf(stderr,"parenthesis_counterの値が0で終わらない\n");
			exit(EXIT_FAILURE);
	}
	
	if(last_and_lexeme == NULL && last_or_lexeme == NULL ){
		if(first_lexeme->lexeme_id == LEXEME_ID_LANGLE &&last_lexeme->lexeme_id == LEXEME_ID_RANGLE)
			return make_condition_branch_tree_node(first_lexeme->next,last_lexeme->back);
		
		return make_condition_branch_tree_leef_node(first_lexeme,last_lexeme);
	}
	
	
		condition_branch_tree *target_node = make_new_condition_branch_tree_node();
	
	if(last_or_lexeme != NULL){
		target_node->type = or;
		target_node->left = make_condition_branch_tree_node(first_lexeme,last_or_lexeme->back);
		target_node->right = make_condition_branch_tree_node(last_or_lexeme->next,last_lexeme);
		return target_node;
	}else if(last_and_lexeme != NULL){
		target_node->type = and;
		target_node->left = make_condition_branch_tree_node(first_lexeme,last_and_lexeme->back);
		target_node->right = make_condition_branch_tree_node(last_and_lexeme->next,last_lexeme);
		return target_node;
	}
	
	return NULL;
}



condition_branch_tree *make_condition_branch_tree_leef_node(lexeme_list *first,lexeme_list *last){
	condition_branch_tree *new_node = make_new_condition_branch_tree_node();
	if(first == last && first->lexeme_id == LEXEME_ID_TRUE){
		new_node->type = true_node;
		return new_node;
	}else if(first == last && first->lexeme_id == LEXEME_ID_FALSE){
		new_node->type = false_node;
		return new_node;
	}
	
	new_node->type = leef;
	new_node->single_condition_id = add_single_condition_node(first,last);
	return new_node;
}

int add_single_condition_node(lexeme_list *first,lexeme_list *last){
	char buf[BUFSIZE];
	lexeme_list *index = first;
	int buf_len = 0;
	int lexeme_counter = 0;
	while(index != NULL){
		if(lexeme_counter > 0){
			sprintf(buf + buf_len," ");
			buf_len++;
		}
		sprintf(buf + buf_len,"%s",index->lexeme_str);
		buf_len = strlen(buf);
		if(index == last)
			break;
		index = index->next;
		lexeme_counter++;
	}
	
	single_condition_list *condition_index = &(single_condition_list_root);
	while(condition_index->next != NULL){
		condition_index = condition_index->next;
		if(strcmp(buf,condition_index->str) == 0)
			return condition_index->single_condition_id;
	}
	
	single_condition_list *new_node = (single_condition_list *)malloc(sizeof(single_condition_list));
	new_node->next = NULL;
	
	new_node->single_condition_id = single_condition_node_counter;
	single_condition_node_counter++;
	
	new_node->str = (char *)malloc(sizeof(char) * (strlen(buf) + 1));
	sprintf(new_node->str,"%s",buf);

	condition_index->next = new_node;
	
	return new_node->single_condition_id;
}


condition_branch_tree *make_new_condition_branch_tree_node(){
	condition_branch_tree *new_node = (condition_branch_tree *)malloc(sizeof(condition_branch_tree));
	new_node->type = undecided;
	new_node->right = NULL;
	new_node->left = NULL;
	new_node->single_condition_id = -1;
	
	return new_node;
}




//ここから条件単体のリストか条件木への参照
void search_single_condition_str(int single_condition_id,char buf[BUFSIZE]){
	if(condition_list_state() != Not_doing){
	fprintf(stderr,"%sの \n search_single_condition_strでエラー\n",THIS_FILE_NAME);
	fprintf(stderr,"条件式の入力中なのに呼ばれた\n");
	exit(EXIT_FAILURE);
	}
	
	if(single_condition_id < 0 || single_condition_node_counter < single_condition_id){
	fprintf(stderr,"%sの \n get_single_condition_str()でエラー\n",THIS_FILE_NAME);
	fprintf(stderr,"入力されたidが不適切\n");
	exit(EXIT_FAILURE);
	}
	single_condition_list *index = single_condition_list_root.next;
	while(index != NULL){
		if(index->single_condition_id == single_condition_id){
			sprintf(buf,"%s",index->str);
			return;
		}
		index = index->next;
		
	}
	fprintf(stderr,"%sの \n get_single_condition_str()でエラー\n",THIS_FILE_NAME);
	fprintf(stderr,"入力されたidと一致するノードが見つからない\n");
	exit(EXIT_FAILURE);
	
}

int get_single_condition_number(){
	if(condition_list_state() != Not_doing){
	fprintf(stderr,"%sの \n get_single_condition_number()でエラー\n",THIS_FILE_NAME);
	fprintf(stderr,"条件式の入力中なのに呼ばれた\n");
	exit(EXIT_FAILURE);
	}

	return single_condition_node_counter;
}


int count_condition_branch_tree_depth(condition_branch_tree *target_tree){
	enum Condition_branch_node_type type = target_tree->type;
	if(leef == type)
		return 1;
	int left_depth = count_condition_branch_tree_depth(target_tree->left);
	int right_depth = count_condition_branch_tree_depth(target_tree->right);
	int large_depth = left_depth;
	if(large_depth < right_depth)
		large_depth = right_depth;
	
	return large_depth + 1;
}


void make_new_bool_value_array_for_search_condition_branch_tree(){
	if(condition_list_state() != Not_doing){
	fprintf(stderr,"%sの \n make_new_bool_value_array()でエラー\n",THIS_FILE_NAME);
	fprintf(stderr,"条件式の入力中なのに呼ばれた\n");
	exit(EXIT_FAILURE);
	}
	int longth = get_single_condition_number();
	bool_value_array = (enum Bool_value *)malloc(sizeof(enum Bool_value) * longth);
	for(int i = 0;i<longth;i++)
		bool_value_array[i] = no_value;
	return;
}

void set_bool_value_for_search_condition_branch_tree(int row_id){
	int longth = get_single_condition_number();
	for(int line_id = 0;line_id < longth; line_id++)
	bool_value_array[line_id] = get_bool_value_from_condition_decision_table(line_id,row_id);
	
}

enum Function_result condition_branch_tree_return_true(condition_branch_tree *target_tree){
	if(target_tree->type == true_node){
		return Result_true;
	}else if(target_tree->type == false_node){
		return Result_false;
	}
	
	if(target_tree->type == leef){
		if(bool_value_array[target_tree->single_condition_id] == value_true){
			return Result_true;
		}else if(bool_value_array[target_tree->single_condition_id] == value_false){
			return Result_false;
		}else{
			fprintf(stderr,"%sの \n condition_branch_tree_return_true()でエラー\n",THIS_FILE_NAME);
			fprintf(stderr,"登録されている真偽値が不正\n");
			exit(EXIT_FAILURE);
		}
	}
	
	if(target_tree->type == and){
		enum Function_result right_true = condition_branch_tree_return_true(target_tree->right);
		enum Function_result left_true = condition_branch_tree_return_true(target_tree->left);
		if(right_true == Result_true && left_true == Result_true)
			return Result_true;
		
		return Result_false;
	}
	
	if(target_tree->type == or){
		enum Function_result right_true = condition_branch_tree_return_true(target_tree->right);
		enum Function_result left_true = condition_branch_tree_return_true(target_tree->left);
		if(right_true == Result_true || left_true == Result_true)
			return Result_true;
		
		return Result_false;
	}
	fprintf(stderr,"%sの \n condition_branch_tree_return_true()でエラー\n",THIS_FILE_NAME);
	fprintf(stderr,"登録されているbranch_treeのノードタイプが不適切\n");
	exit(EXIT_FAILURE);
}


void look_condition_branch_tree(condition_branch_tree *target){
	int target_depth = count_condition_branch_tree_depth(target);
	printf("%d\n",target_depth);
	for(int i = 0; i< target_depth; i++){
		print_condition_tree(i,target);
		printf("\n");
	}
}

void print_condition_tree(int depth,condition_branch_tree *target_node){
	
	if(target_node == NULL)
	return;
	
	if(depth > 0){
		print_condition_tree(depth - 1, target_node->left);
		print_condition_tree(depth - 1,target_node->right);
		return;
	}

	enum Condition_branch_node_type type = target_node->type;
	switch(type){
	  case and:
		printf("and  ");
		return;
		
	  case or:
		printf("or  ");
		return;
		
	  case leef:
		printf("%d  ",target_node->single_condition_id);
		return;
		
	  case true_node:
		printf("true  ");
		
	  case false_node:
		printf("false");
		
	  case undecided:
		return;
	}
}

void condition_branch_tree_free(condition_branch_tree *target){
	if(target->right != NULL)
		condition_branch_tree_free(target->right);
	
	if(target->left != NULL)
		condition_branch_tree_free(target->left);
	
	free(target);
}

void free_single_condition_list(){
	single_condition_node_counter = 0;
	single_condition_list *index = &single_condition_list_root;
	single_condition_list *index_next = index->next;

	while(index_next != NULL){
		index = index_next;
		index_next = index_next->next;
		free(index->str);
		free(index);
	}
	single_condition_list_root.next = NULL;
	
}
