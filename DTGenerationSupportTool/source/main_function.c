#include "header_for_make_decision_table_from_vdm.h"
#include <unistd.h>


int main(int argc, char *argv[]){
	char command[BUFSIZE];
	//構文解析を行う
	//shellスクリプトを呼び出す
	//shellスクリプトがparserに構文解析をさせる
	sprintf(command,"sh ../run/make_decisiontable2.sh %s",argv[1]);
	system(command);
	sleep(3);
	printf("%s\n",command);
	
	//指定されたファイル名、オプション機能を読み取る
	extract_filename(argv[1]);
	extract_command_line_argument(argc,argv);
	//構文解析結果のファイル(vdmpp)を読み込み
	make_lexeme_list();
	
	//コメントアウトを取ると、読み取ったトークンとトークンIDを表示
	//look_lexeme_list();
	
	//クラス定義ごとにトークンを分割
	extract_class_definition();
	
	//コメントアウトを取ると、分割したクラス定義ごとのトークンを表示
	//look_class_list();
	
	//デシジョンテーブル生成
	make_all_decision_table();
			return 0;
}