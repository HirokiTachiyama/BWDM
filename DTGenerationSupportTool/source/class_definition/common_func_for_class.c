#include "../header_for_make_decision_table_from_vdm.h"
#include "header_for_class.h"

#define THIS_FILE_NAME "class_definition/common_func_for_class.c"

lexeme_list *make_new_lexeme_node(){
	lexeme_list *new_lexeme_node = (lexeme_list *)malloc(sizeof(lexeme_list));
	new_lexeme_node->next = NULL;
	return new_lexeme_node;
}



//処理開始時はlexeme_node_indexがアクセス修飾子を参照している
//処理終了後のlexeme_node_indexはアクセス修飾子記述の次のノードを見る
enum Access_modifier extract_access_modifier_information(){
	switch(get_lexeme_id_from_lexeme_node()){
		case LEXEME_ID_PUBLIC:
		go_next_lexeme_node();
		return public;
		
	  case LEXEME_ID_PRIVATE:
		go_next_lexeme_node();
		return private;
		
	  case LEXEME_ID_PROTECTED:
		go_next_lexeme_node();
		return protected;
		
	  default:
		break;
	}
	/*
	fprintf(stderr,"%sのextract_access_modifier_information()でエラーの可能性\n",THIS_FILE_NAME);
	fprintf(stderr,"アクセス修飾子のIDが定義されてないか仕様書がアクセス修飾子を省略している\n");
	fprintf(stderr,"LEXEME_ID: %d\n",get_lexeme_id_from_lexeme_node());
	*/
	return private;
}




//処理終了後のlexeme_node_indexはstatic記述の後のノードを見る
enum Is_static extract_static_information(){
	if(get_lexeme_id_from_lexeme_node() == LEXEME_ID_STATIC){
		go_next_lexeme_node();
		return yes;
	}
	return no;
}

enum Function_result lexeme_id_define_other_function(){
	
	switch(get_lexeme_id_from_lexeme_node()){
		case LEXEME_ID_PUBLIC:
		return Result_true;
		
	  case LEXEME_ID_PRIVATE:
		return Result_true;
		
	  case LEXEME_ID_PROTECTED:
		return Result_true;
	  default:
		break;
	}
	
	return Result_false;
	
}

enum Function_result lexeme_id_define_other_operation(){
	
	switch(get_lexeme_id_from_lexeme_node()){
		case LEXEME_ID_PUBLIC:
		return Result_true;
		
	  case LEXEME_ID_PRIVATE:
		return Result_true;
		
	  case LEXEME_ID_PROTECTED:
		return Result_true;
	  default:
		break;
	}
	
	return Result_false;
	
}



enum Function_result lexeme_id_is_appointname_definition_type(){
	switch(get_lexeme_id_from_lexeme_node()){
	  case LEXEME_ID_TYPES:
		return Result_true;
		
	  case LEXEME_ID_VALUES:
		return Result_true;
		
	  case LEXEME_ID_INSTANCE_VARIABLES:
		return Result_true;
		 
		case LEXEME_ID_FUNCTIONS:
		return Result_true;
		
	  case LEXEME_ID_OPERATIONS:
		return Result_true;
		
	  case LEXEME_ID_SYNC:
		return Result_true;
		
	  case LEXEME_ID_THREAD:
		return Result_true;

	  default:
		break;
	}
	return Result_false;
	
}

enum Function_result end_whole_class_definition(){
	if(can_go_next_lexeme_node() == Result_false)
		return Result_true;
	
	return Result_false;
}
enum Function_result end_a_class_definition(){
	if(end_whole_class_definition() == Result_true)
		return Result_true;
	
	if(get_lexeme_id_from_lexeme_node() == LEXEME_ID_END)
		return Result_true;
	
	return Result_false;
}
