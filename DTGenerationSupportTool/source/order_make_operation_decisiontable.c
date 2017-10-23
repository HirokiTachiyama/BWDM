#include "header_for_make_decision_table_from_vdm.h"


void order_make_operation_decisiontable(){
	char name[BUFSIZE];
	while(can_go_next_operation_node() == Result_true){
		get_operation_name_from_operation_node(name);
		output_operation_decision_table(name);
		go_next_operation_node();
	}
}