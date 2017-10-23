make_decisiontable: main_function.o lexeme_list.o class_definition.o function_definition.o \
common_func_for_class.o look_internal_data.o condition_list.o branch_tree_for_function.o \
action_list.o condition_branch_tree.o decision_table.o command_line_argument.o \
filename_control.o make_all_decision_table.o operation_definition.o order_make_operation_decisiontable.o \
branch_tree.o

	gcc -o make_decisiontable main_function.o lexeme_list.o class_definition.o function_definition.o \
common_func_for_class.o look_internal_data.o condition_list.o branch_tree_for_function.o \
action_list.o condition_branch_tree.o decision_table.o command_line_argument.o \
filename_control.o make_all_decision_table.o operation_definition.o order_make_operation_decisiontable.o \
branch_tree.o



main_function.o: main_function.c
	gcc -c -std=c99 main_function.c

	
class_definition.o: ./class_definition/class_definition.c
	gcc -c -std=c99 ./class_definition/class_definition.c

function_definition.o :./class_definition/function_definition.c
	gcc -c -std=c99 ./class_definition/function_definition.c
	
common_func_for_class.o :./class_definition/common_func_for_class.c
	gcc -c -std=c99 ./class_definition/common_func_for_class.c
	
look_internal_data.o :look_internal_data.c
	gcc -c -std=c99 look_internal_data.c
	
condition_list.o :./condition_list/condition_list.c
	gcc -c -std=c99 ./condition_list/condition_list.c
	
branch_tree_for_function.o :branch_tree_for_function.c
	gcc -c -std=c99 branch_tree_for_function.c
	
action_list.o :action_list.c
	gcc -c -std=c99 action_list.c
	
	
condition_branch_tree.o :./condition_list/condition_branch_tree.c
	gcc -c -std=c99 ./condition_list/condition_branch_tree.c
	
decision_table.o :decision_table.c
	gcc -c -std=c99 decision_table.c
	
command_line_argument.o :command_line_argument.c
	gcc -c -std=c99 command_line_argument.c
	
filename_control.o :filename_control.c
	gcc -c -std=c99 filename_control.c
	
make_all_decision_table.o:make_all_decision_table.c
	gcc -c -std=c99 make_all_decision_table.c
	
operation_definition.o : ./class_definition/operation_definition.c
	gcc -c -std=c99 ./class_definition/operation_definition.c
	
order_make_operation_decisiontable.o :order_make_operation_decisiontable.c
	gcc -c -std=c99 order_make_operation_decisiontable.c
	
branch_tree.o : branch_tree.c
	gcc -c -std=c99 branch_tree.c
	
lexeme_liust.o:lexeme_list.c
	gcc -c -std=c99 lexeme_list.c
	
