#ifndef _HEADER_FOR_CLASS
#define _HEADER_FOR_CLASS

enum Is_static {yes,no};



typedef struct lexeme_str_and_id_list{
	int lexeme_id;
	char *str;
	struct lexeme_str_and_id_list *next;
}lexeme_list;



typedef struct function_definition_list{
	char *function_name;
	enum Access_modifier modifier;
	enum Variable_type return_type;
	enum Is_static is_static_function;
	lexeme_list lexeme_list_root;
	struct function_definition_list *next;
}function_list;


typedef struct operation_definition_list{
	char *operation_name;
	enum Access_modifier modifier;
	enum Is_static is_static_operation;
	lexeme_list lexeme_list_root;
	struct operation_definition_list *next;
}operation_list;



typedef struct class_definition_list{
	char *class_name;
	lexeme_list subclass_of;
	lexeme_list types;
	lexeme_list values;
	lexeme_list instance_variables;
	function_list function_definitions;
	operation_list operation_definitions;
	lexeme_list sync;
	lexeme_list thread;
	struct class_definition_list *next;
}class_list;



//function_definition.c
void extract_function_definition(class_list *target_class_node);
void function_list_index_init(class_list *target_class);

//operation_definition.c
void extract_operation_definition(class_list *target_class_node);
void operation_list_index_init(class_list *target_class);


//common_func_for_class_definition.c
lexeme_list *make_new_lexeme_node();
enum Function_result end_whole_class_definition();
enum Function_result end_a_class_definition();
enum Access_modifier extract_access_modifier_information();
enum Is_static extract_static_information();
enum Function_result lexeme_id_define_other_function();
enum Function_result lexeme_id_define_other_operation();
enum Function_result lexeme_id_is_appointname_definition_type();

#endif

