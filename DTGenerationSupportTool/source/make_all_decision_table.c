#include "header_for_make_decision_table_from_vdm.h"


void make_all_decision_table(){
	class_list_index_init();
	
	//関数定義のデシジョンテーブル生成
	while(can_go_next_function_node() == Result_true){
		//実行経路データ、条件データ、動作データの抽出
		//実行経路データのルートノード
		branch_tree_node *root;
		root = extract_branch_tree_from_function();
		
		//デシジョンテーブル生成
		make_decision_table_for_function(root);
		
		//look_decision_table();
		//look_action_list();
		//look_condition_list();
		//デシジョンテーブルを出力
		output_decision_table();
		
		//各データの初期化
		free_function_branch_tree(root);
		free_action_list();
		free_condition_list();
		go_next_function_node();
	}

	
	//操作定義名をcsv形式で出力
	//一時的なもの、操作定義に対するデシジョンテーブルを生成するよう拡張する際は、消す予定
	//order_make_operation_decisiontable();
	
	
}