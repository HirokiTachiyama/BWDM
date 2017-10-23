#ifndef _CONDITION_LIST_
#define _CONDITION_LIST_


enum State_of_make_condition_list{Doing , Not_doing};
enum Condition_branch_node_type{undecided,and,or,leef,true_node,false_node};

typedef struct condition_branch_tree{
	enum Condition_branch_node_type type;
	struct condition_branch_tree *right;
	struct condition_branch_tree *left;
	int single_condition_id;
}condition_branch_tree;

typedef struct single_condition_list{
	int single_condition_id;
	char *str;
	struct single_condition_list *next;
}single_condition_list;

typedef struct lexeme_list{
	int lexeme_id;
	char *lexeme_str;
	struct lexeme_list *next;
	struct lexeme_list *back;
}lexeme_list;

typedef struct condition_list{
	int node_id;
	enum Condition_type type;
	lexeme_list lexeme_list_root;
	condition_branch_tree *condition_branch_tree_root;
	struct condition_list *next;
}condition_list;

//condition_list.c
enum State_of_make_condition_list condition_list_state();


//condition_branch_tree.c
condition_branch_tree *make_condition_branch_tree_root();
void look_condition_branch_tree(condition_branch_tree *target);
enum Function_result condition_branch_tree_return_true(condition_branch_tree *target_tree);
void condition_branch_tree_free(condition_branch_tree *target);
void free_single_condition_list();

#endif