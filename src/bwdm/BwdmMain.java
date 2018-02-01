package bwdm;

import bwdm.boundaryValueAnalysisUnit.BvaUnitMain;
import bwdm.informationStore.ConditionAndReturnValueList;
import bwdm.informationStore.InformationExtractor;
import bwdm.symbolicExecutionUnit.SeUnitMain;
import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class BwdmMain {


	private static String buildDate = "2018-1-24 PM19:03(JST)";


	public static void main(String[] args) throws LexException, ParserException, IOException {
		TimeMeasure tm = new TimeMeasure();
		tm.start();

		exeBWDM(args);


		tm.finish();
		tm.printResult();

	}


	static void exeBWDM(String[] args) throws IOException, LexException, ParserException {

		/**コマンド引数に応じて、下記の1-5の出力時の情報の表示フラグをON/OFFする**/

		//1 諸情報 ファイルパス、生成したテストケース総数、関数、引数情報  default:OFF
		boolean showStandardInfo = false;

		//2 生成した境界値に関する情報  default:OFF  -a
		boolean showBvsInfo = false;

		//3 記号実行時に生成した条件式と戻り値に関する情報  default:OFF  -i
		boolean showSeConditionsInfo = false;

		//4 境界値テストケース  default:ON  -b
		boolean showBvTestcases = true;

		//5 記号実行テストケース  default:ON  -s
		boolean showSeTestcases = true;

		/**情報フラグ終わり**/



		//ヘルプ表示のフラグ  default:OFF  -h
		//ヘルプがONになったら、テストケース生成は行わない　ヘルプを出して終了
		boolean showHelp = false;
		//バージョン表示のフラグ default:OFF -v
		//バージョン表示がONになった場合も、テストケース生成は行わない　バージョンを表示して終了
		boolean showVersion = false;
		//コンソール表示かテキストファイル書き出しかのフラグ  default:コンソール表示  -f
		boolean displayOnConsole = true;


		//コマンド引数のパース
		switch (args.length) {
			case 1: //ヘルプ表示 or バージョン表示 or オプション指定ミス or ファイル指定による通常実行の4パターン
				if (args[0].contains("-h")) { //ヘルプ表示フラグON
					showHelp = true;
					break;
				} else if(args[0].contains("-v")) { //バージョン表示フラグON
					showVersion = true;
					break;
				} else if (args[0].contains("-")) {//オプション指定ミス ミスを指摘し、ヘルプ表示フラグON
					System.out.println("不明なオプション: " + args[0]);
					showHelp = true;
					break;
				} else { //オプション無しの実行
					//オプションに変更は無く、諸情報とテストケースのみをコンソール表示
					break;
				}

			case 2: //指定オプションによりフラグをON/OFF
				//オプションが-だけ or オプション側に-が無い or ファイルパス側に-がある or オプションに変なものが混ざっている
				if ( args[0].equals("-") || !args[0].contains("-") || args[1].contains("-") || !hasOnlyOptionChar(args[0]) ) {
					//ミスを指摘し、ヘルプ表示フラグON
					System.out.println("不明なオプション: " + args[0]);
					showHelp = true;
					break;
				}


				//ここ以降は、オプション指定が書式に沿った入力(のはず)
				if (args[0].contains("h")) { //ヘルプ　ヘルプだけ表示
					showHelp = true;
					break;
				}
				if (args[0].contains("v")) { //バージョン　バージョンだけ表示
					showVersion = true;
					break;
				}
				if (args[0].contains("n")) { //VDM仕様の基本情報を表示
					showStandardInfo = true;
				}

				if (args[0].contains("f")) { //ファイル書き出しON コンソール表示は行わない
					displayOnConsole = false;
				}
				if (args[0].contains("b")) { //境界値分析によるテストケースのみ出力
					showSeTestcases = false;
				}
				if (args[0].contains("s")) { //記号実行によるテストケースのみ出力
					showBvTestcases = false;
				}
				if (args[0].contains("b") && args[0].contains("s")){ //両方指定されていたらどちらも出力
					showBvTestcases = true;
					showSeTestcases = true;
				}

				if (args[0].contains("a")) { //生成した境界値に関する情報を出力
					showBvsInfo = true;
				}
				if (args[0].contains("i")) { //記号実行時に生成した条件式と戻り値の情報を出力
					showSeConditionsInfo = true;
				}
				break;

			default: //コマンドライン引数の数がおかしい
				//ミスを指摘し、ヘルプ表示フラグON
				System.out.println("エラー : 引数の数が不正.");
				showHelp = true;
				break;
		}


		if(showVersion) {
			System.out.println("BWDM (Boundary Values/VDM)");
			System.out.println("Automatic Testcase Generation Tool based on VDM++ Specification");
			System.out.println("Version : 2.0");
			System.out.println("Built date : " + buildDate);
			System.out.println("Copyright (C) 2018, Hiroki Tachiyama (University of Miyazaki).");
			System.exit(0);
		}

		/**ヘルプがONなら、表示して終了**/
		if (showHelp) {
			System.out.println("書式: bwdm [-naivhfbs] [file_name]\n");
			System.out.println("オプション一覧");
			System.out.println("追加表示");
			System.out.println("-n : VDM仕様の基本情報を表示する");
			System.out.println("-a : 境界値分析で生成した境界値を表示");
			System.out.println("-i : 記号実行で生成した条件式を表示");
			System.out.println();
			System.out.println("テストケース絞り込み");
			System.out.println("-b : 境界値分析によるテストケースのみ出力");
			System.out.println("-s : 記号実行によるテストケースのみ出力");
			System.out.println();
			System.out.println("出力先");
			System.out.println("-f : テキストファイル<file_name.tc>に書出\n" +
					"     デフォルト：コンソール表示");
			System.out.println();
			System.out.println("その他");
			System.out.println("-v : バージョン表示");
			System.out.println("-h : ヘルプを表示");

			System.exit(0);
		}




		/**テストケース生成処理**/
		String vdmPath = null;
		switch (args.length) {
			case 1:
				vdmPath = args[0];
				break;
			case 2:
				vdmPath = args[1];
				break;
		}
		InformationExtractor extractInformation = new InformationExtractor(vdmPath);
		BvaUnitMain bvaUnitMain = new BvaUnitMain(extractInformation);
		SeUnitMain seUnitMain = new SeUnitMain(extractInformation);




		/**オプションに従って出力文字列を生成**/
		String buf = "";

		//1 諸情報
		if(showStandardInfo) {
			buf += "ファイルパス : " + new File(vdmPath).getCanonicalPath() + "\n";
			buf += "関数名 : " + extractInformation.getFunctionName() + "\n";
			buf += "引数の型 : ";
			for (int i = 0; i < extractInformation.getArgumentTypes().size(); i++) {
				buf += extractInformation.getParameters().get(i) + ":"
						+ extractInformation.getArgumentTypes().get(i) + " ";
			}
			buf += "\n";
			buf += "戻り値の型 : " + extractInformation.getReturnValue() + "\n";
			int bvTestcaseNum =
					bvaUnitMain.getBoundaryValueAnalyzer().getInputDataList().size();
			int seTestcaseNum =
					seUnitMain.getSe().getInputDataList().size();
			buf += "生成テストケース数 : " + (bvTestcaseNum + seTestcaseNum) + "件";
			buf += "(" + "境界値分析:" + bvTestcaseNum + "/記号実行:" + seTestcaseNum + ")";
			buf += "\n\n";
		}

		//2 境界値情報
		if(showBvsInfo) {
			buf += "各引数の境界値\n";
			HashMap<String, ArrayList<Long>> bvsList =
					bvaUnitMain.getBoundaryValueAnalyzer().getBoundaryValueList();
			ArrayList<String> parameters = extractInformation.getParameters();
			for (int i = 0; i < parameters.size(); i++) {
				String currentPrm = parameters.get(i);
				ArrayList<Long> bvs = bvsList.get(currentPrm);
				buf += currentPrm + " : ";
				for (Long bv : bvs) {
					buf += bv + " ";
				}
				buf += "\n";
			}
			buf += "\n";
		}

		//3 記号実行情報
		if(showSeConditionsInfo) {
			buf += "記号実行情報\n";
			ConditionAndReturnValueList carvList = extractInformation.getConditionAndReturnValueList();
			buf += "戻り値の数 : " + carvList.size + "\n";

			for(int i=0; i<carvList.size; i++) {
				buf += "制約 : ";
				ConditionAndReturnValueList.ConditionAndReturnValue carv =
						carvList.getConditionAndReturnValues().get(i);
				ArrayList conditions = carv.getConditions();
				ArrayList bools = carv.getBools();

				//はじめの一つ以外は、前に and をつけてくっつける
				if((boolean)bools.get(0)) {
					buf += conditions.get(0) + " ";
				} else {
					buf += "!( " + conditions.get(0) + " ) ";
				}

				for(int j=1; j<conditions.size(); j++) { //はじめの一個以外
					if((boolean)bools.get(j)) {
						buf += "and "+ conditions.get(j);
					} else {
						buf += "and !( " + conditions.get(j) + " ) ";
					}
				}

				buf += ", 戻り値 : " + carv.getReturnStr() + "\n";

			}

			buf += "\n";

		}

		//4 境界値テストケース
		if(showBvTestcases) {
			buf += "境界値分析によるテストケース\n";
			buf += bvaUnitMain.getAllTestcasesByBv();
			buf+= "\n";
		}

		//5 記号実行テストケース
		if(showSeTestcases) {
			buf += "記号実行によるテストケース\n";
			buf += seUnitMain.getAllTestcasesBySe();
			buf+= "\n";
		}

		//コンソール表示 or テキスト出力
		if(displayOnConsole) {
			System.out.print(buf);
		} else {
			File outputFile = new File(extractInformation.getVdmFilePath().replace("vdmpp", "tc"));
			FileWriter fw = new FileWriter(outputFile);
			fw.write(buf);
			fw.close();
		}

	}





	//オプションで使える文字以外のものが含まれていないかの判定
	static boolean hasOnlyOptionChar(String _optionStr) {
		//オプション文字として使えるものを消していって、最後に何か残っていたらダウト
		String optionStr = _optionStr;

		optionStr = optionStr.replace("-", "");
		optionStr = optionStr.replace("v", "");
		optionStr = optionStr.replace("n", "");
		optionStr = optionStr.replace("f", "");
		optionStr = optionStr.replace("b", "");
		optionStr = optionStr.replace("s", "");
		optionStr = optionStr.replace("a", "");
		optionStr = optionStr.replace("i", "");
		optionStr = optionStr.replace("h", "");

		if(optionStr.equals("")) return true;
		else                     return false;
	}

}
