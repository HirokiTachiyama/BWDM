#include "header_for_make_decision_table_from_vdm.h"

#define ACTION_VALUE_EMPTY_IN_FUNCTION "-ef"
#define NOT_OUTPUT_RETURN_BOOL_VALUE_IN_FUNCTION "-nb"
#define NOT_USE_LOGIC_OPERATION "-nl"
#define RUN_FAST "-sp"
enum Switch{on,off};

static enum Switch action_value_empty_in_function = off;
static enum Switch not_output_return_bool_value_in_function = off;
static enum Switch not_use_logic_operation = off;
static enum Switch run_fast = off;


void input_command_line_argument(char *argv);

void extract_command_line_argument(int argc,char **argv){
	for(int i = 0; i < argc; i++)
		input_command_line_argument(argv[i]);
	
	return;
}

void input_command_line_argument(char *argv){
	if(strcmp(argv,ACTION_VALUE_EMPTY_IN_FUNCTION) == 0)
		action_value_empty_in_function = on;
	
	if(strcmp(argv,NOT_OUTPUT_RETURN_BOOL_VALUE_IN_FUNCTION) == 0){
		not_output_return_bool_value_in_function = on;
	}
	
	if(strcmp(argv,RUN_FAST) == 0){
		run_fast = on;
	}
	
	if(strcmp(argv,NOT_USE_LOGIC_OPERATION) == 0){
		not_use_logic_operation = on;
	}
	
	return;
}


enum Function_result option_action_value_empty_in_function(){
	if(action_value_empty_in_function == on)
		return Result_true;
	return Result_false;
}

enum Function_result option_not_output_return_bool_value_in_function(){
	if(not_output_return_bool_value_in_function == on)
		return Result_true;
	return Result_false;
}

enum Function_result option_run_fast(){
	if(run_fast == on)
		return Result_true;
	return Result_false;
}

enum Function_result option_not_use_logic_operation(){
	if(not_use_logic_operation == on)
		return Result_true;
	return Result_false;
}


