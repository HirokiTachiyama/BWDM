#include "header_for_make_decision_table_from_vdm.h"

#define THIS_FILE_NAME "action_list.c"

enum State_of_action_list{On,Off};


typedef struct action_list{
	int action_node_id;
	char *action_name;
	struct action_list *next;
}action_list;


action_list *make_new_action_node(char *action_buf);



static action_list action_list_root;
static action_list *action_list_index;
static int action_node_counter = 0;
static enum State_of_action_list state = Off;



void make_new_action_list(){
		if(state != Off){
		fprintf(stderr,"%sのmake_new_action_listでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"動作のリストがあるのに呼ばれた\n");
		exit(EXIT_FAILURE);
	}
	state = On;
	
	action_list_index = &(action_list_root);
	action_list_index->next = NULL;
	action_node_counter = 0;
	
}

int add_action_node(char *action_buf){
		if(state != On){
		fprintf(stderr,"%sのadd_action_listでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"動作のリストがないのに呼ばれた\n");
		exit(EXIT_FAILURE);
	}
	action_list *tmp_index = action_list_root.next;
	while(tmp_index != NULL){
		if(strcmp(tmp_index->action_name,action_buf) == 0)
			return tmp_index->action_node_id;
		
		tmp_index = tmp_index->next;
	}
	
	
	action_list_index->next = make_new_action_node(action_buf);
	action_node_counter++;
	action_list_index = action_list_index->next;
	int this_action_node_id = action_list_index->action_node_id;
	
	return this_action_node_id;
}



action_list *make_new_action_node(char *action_buf){
	action_list *new_action_node = malloc(sizeof(action_list));
	new_action_node->action_node_id = action_node_counter;
	
	new_action_node->action_name = (char *)malloc(sizeof(char) *(strlen(action_buf) +1));
	sprintf(new_action_node->action_name,"%s",action_buf);
	new_action_node->next = NULL;
	return new_action_node;
}


//------------action_list内のデータ参照

int get_action_node_number(){
	
	return action_node_counter;
}



void get_action_name(int node_id,char buf[BUFSIZE]){
	if(state != On){
		fprintf(stderr,"%sのget_action_nameでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"動作のリストがないのに呼ばれた\n");
		exit(EXIT_FAILURE);
	}
	
	if(node_id < 0 || action_node_counter <= node_id){
		fprintf(stderr,"%sのget_action_nameでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"idの値が不適切\n");
		exit(EXIT_FAILURE);
	}
	
	action_list *index = action_list_root.next;
	
	while(index != NULL){
		if(index->action_node_id == node_id){
			sprintf(buf,"%s",index->action_name);
			return;
		}
		index = index->next;
	}
		fprintf(stderr,"%sのget_action_nameでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"動作が見つからない\n");
		exit(EXIT_FAILURE);
	
}



void free_action_list(){
	if(state != On){
		fprintf(stderr,"%sのfree_action_listでエラー\n",THIS_FILE_NAME);
		fprintf(stderr,"動作のリストがないのに呼ばれた\n");
		exit(EXIT_FAILURE);
	}
	
	action_list *index = &action_list_root;
	action_list *index_next =index->next ;
	
	while(index_next != NULL){
		index = index_next;
		index_next = index_next->next;
		free(index->action_name);
		free(index);
	}
	action_list_root.next = NULL;

	action_node_counter = 0;
	state = Off;
	return;
}


