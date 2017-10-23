package past_BWDM_classes.bwdm;

import com.fujitsu.vdmj.lex.LexException;
import com.fujitsu.vdmj.syntax.ParserException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import workspace.InputDataGeneratorBySyntaxTree;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;


public class BwdmMain extends Application{

	private String vdmPath, dtPath;

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(final Stage primaryStage) throws ParserException, LexException, IOException {

		//時間計測
    	
    	
		TimeMeasure tm = new TimeMeasure();
		tm.start();


    	InputDataGeneratorBySyntaxTree tree = new InputDataGeneratorBySyntaxTree("DTGenerationSupportTool/data/problem.if_else");


		tm.finish();
		tm.printResult();


    	HBox hbox = new HBox();
        VBox vboxLeft = new VBox();
        VBox vboxRight = new VBox();

        hbox.getChildren().addAll(vboxLeft, vboxRight);


        //デシジョンテーブル選択ボタンとラベル
        Button dtBtn = new Button();
        dtBtn.setText("Select decision table");
        final FileChooser fc2 = new FileChooser();
        fc2.setTitle("Select decision table");
        fc2.setInitialDirectory(new File("DTGenerationSupportTool\\data"));
        final Label dtLbl = new Label();
        dtBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File importFile = fc2.showOpenDialog(primaryStage);
                if (importFile != null) {
                	dtPath = importFile.getPath().toString();
                    dtLbl.setText(dtPath);
                }
            }
        });
        vboxLeft.getChildren().addAll(dtBtn, dtLbl);

        //VDMファイル選択ボタンとラベル
        Button vdmBtn = new Button();
        vdmBtn.setText("Select vdm++ specific");
        final FileChooser fc = new FileChooser();
        fc.setTitle("Select vdm++ specific");
        fc.setInitialDirectory(new File("DTGenerationSupportTool\\data"));
        final Label vdmLbl = new Label();
        vdmBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                File importFile = fc.showOpenDialog(primaryStage);
                if (importFile != null) {
                	vdmPath = importFile.getPath();
					new AnalyzedData(vdmPath, dtPath, "");

                	AnalyzedData.printInformation();

                	String lblText = new String("");
                	lblText += "Selected File:" + vdmPath + "\n\n";
                	try {
    					lblText += AnalyzedData.getSpecificationAllText();
    				} catch (FileNotFoundException e) {
    					// TODO 自動生成された catch ブロック
    					e.printStackTrace();
    				} catch (IOException e) {
    					// TODO 自動生成された catch ブロック
    					e.printStackTrace();
    				}
                    vdmLbl.setText(lblText);
                }
            }
        });
        vboxLeft.getChildren().addAll(vdmBtn, vdmLbl);

    	//境界値分析実行ボタンとラベル
    	Button bvBtn = new Button();
        final Label bvLabel = new Label();
        bvBtn.setText("Do Boundary Value Anlysis");
        bvBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        		//抽出した情報から境界値分析、入力値生成
        		try {
					new BoundaryValueAnalyze();
				} catch (ParserException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (LexException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				} catch (IOException e) {
					// TODO 自動生成された catch ブロック
					e.printStackTrace();
				}
        		BoundaryValueAnalyze.printBoundaryValueTable();
        		BoundaryValueAnalyze.printInputValue();
            	bvLabel.setText(BoundaryValueAnalyze.getBoundaryValueTableString());
            }
        });
        vboxRight.getChildren().addAll(bvBtn, bvLabel);

    	//テストケース表示ボタン
    	Button indicateBtn = new Button();
        final Label indicateLbl = new Label();
        indicateBtn.setText("Indicate Testcases");
        indicateBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
        		//入力値をif条件文判定してkey作成
        		new EvaluationOfConditions();
        		new DecisionTable(dtPath);

        		String[][] inputData = BoundaryValueAnalyze.getInputData();
        		ArrayList<String> evaluationResult = EvaluationOfConditions.getEvaluationResult();


        		HashMap<String, String> booleanSequenceToAction = DecisionTable.getBooleanSequenceToAction();
        		String str = "";

    			for(int i=0; i<inputData.length; i++){
    				str += "(" + String.join(",", inputData[i]) + ") --> " +
    						booleanSequenceToAction.get(evaluationResult.get(i)) + "\n";
    			}

            	indicateLbl.setText(str);
            }
        });
        vboxRight.getChildren().addAll(indicateBtn, indicateLbl);


    	//テストケースファイル出力ボタン
    	Button outputTestcaseBtn = new Button();
        final Label outputTestcaseLbl = new Label();
        outputTestcaseBtn.setText("Output Testcases");
        outputTestcaseBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
            	outputBoundaryValueTestcase(true);

            	outputTestcaseLbl.setText("Testcase file is written");
            }
        });
        vboxRight.getChildren().addAll(outputTestcaseBtn, outputTestcaseLbl);



        StackPane root = new StackPane();
        root.getChildren().addAll(hbox);

        Scene scene = new Scene(root, 640, 480);

        primaryStage.setTitle("BWDMwithGUI");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


	public static void outputBoundaryValueTestcase(Boolean fileOrStandardOutput){

		String[][] inputData = BoundaryValueAnalyze.getInputData();
		ArrayList<String> evaluationResult = EvaluationOfConditions.getEvaluationResult();

		new DecisionTable(AnalyzedData.getCsvFilePath());

		System.out.println("引数の個数:" + AnalyzedData.getArgumentTypes().size() + "\n");
		System.out.print("引数型: ");
		for(int i=0; i<AnalyzedData.getArgumentTypes().size(); i++){
			System.out.print(AnalyzedData.getArgumentTypes().get(i) + " ");
		}
		System.out.println("\n\n入力値 --> 期待出力値");


		if(fileOrStandardOutput) {
			FileWriter outputFile=null;

			//ファイル作成、引数個、引数の書き込み
			try {
				outputFile =
						new FileWriter(new File(AnalyzedData.getVdmFilePath().replace(".vdmpp", "") + "Testcase.csv"));
				//引数の個数の書き込み
				outputFile.write("引数の個数:" + AnalyzedData.getArgumentTypes().size() + "\n\n");
				//引数型の書き込み
				String tmp = new String();
				for(int i=0; i<AnalyzedData.getArgumentTypes().size(); i++){
					tmp = tmp +"第"+(i+1)+"引数:"+ AnalyzedData.getArgumentTypes().get(i) +",";
				}
				outputFile.write("引数型:," + tmp + "\n\n");
				outputFile.write("テストケースNo. 入力データ  --> 期待出力データ" + "\n");
				System.out.println("ファイル作成成功！");

				for(int i=0; i<inputData.length; i++){
					try {
						outputFile.write("No."+(i+1)+","+String.join(",", inputData[i]) + ",-->,\"" +
								DecisionTable.getBooleanSequenceToAction().get(evaluationResult.get(i)) + "\"\n");
						System.out.println("ファイル書き込み成功！"+evaluationResult.get(i));
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("ファイル書き込みしくった！");
					}
				}

				outputFile.close();
				System.out.println("ファイルくろーず成功！");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("ファイル作成しくった！");
			}
		} else {
			for(int i=0; i<inputData.length; i++){
				System.out.println("("+String.join(",", inputData[i]) + ") --> " +
						DecisionTable.getBooleanSequenceToAction().get(evaluationResult.get(i)));
			}
		}


	}


}



/*


public class BwdmMain {

	public static void main(String[] args) throws ParserException, LexException, IOException {
		//時間計測
//		TimeMeasure tm = new TimeMeasure();
	//	tm.start();

		Boolean fileMakeFlag = true;



		try {
			new AnalyzedData("mod2.vdmpp", "mod2.csv", "DTGenerationSupportTool/data/");

			//new AnalyzedData("mod4.vdmpp", "mod4.csv", "Cygwin/data/");
			//new AnalyzedData("FizzBuzz.vdmpp", "FizzBuzz.csv", "Cygwin/data/");
			//new AnalyzedData("modtohoho.vdmpp", "modtohoho.csv", "Cygwin/data/");
			//new AnalyzedData("Nest.vdmpp", "Nest.csv", "Cygwin/data/");
			//new AnalyzedData("Mix.vdmpp", "Mix.csv", "Cygwin/data/");
			//new AnalyzedData("mix.vdmpp", "mix.csv", "Cygwin/data/");
			//new AnalyzedData("mod2.vdmpp", "mod2.csv", "Cygwin/data/");

			//new AnalyzedData("mod1.vdmpp", "mod1.csv", "Cygwin/data/");

			//new AnalyzedData("conditionNum5.vdmpp","conditionNum5.csv","Cygwin/data/");
			//new AnalyzedData("conditionNum10.vdmpp","conditionNum10.csv","Cygwin/data/");
			//new AnalyzedData("conditionNum15.vdmpp","conditionNum15.csv","Cygwin/data/");


			//new AnalyzedData("FizzBuzz.vdmpp", "FizzBuzz.csv", "Cygwin/data/");
			//new AnalyzedData("Arg2_20160126.vdmpp", "Arg2_20160126.csv", "./");
			//new AnalyzedData("Arg1.vdmpp",
			//		 "Arg1.csv",
			//		 "Cygwin/data/");
			//new AnalyzedData("Arg2_doubleVariables.vdmpp",
			//			 "Arg2_doubleVariables.csv",
			//				 "Cygwin/data/");
			//new AnalyzedData("Arg2_chapter3SampleUTF.vdmpp",
			//		 "Arg2_chapter3SampleUTF.csv",
			//		 "Cygwin/data/");
			//AnalyzedData.printInformation();
		} catch (LexException | ParserException e) {
			e.printStackTrace();
		}

		//抽出した情報から境界値分析、入力値生成
		new BoundaryValueAnalyze();
		BoundaryValueAnalyze.printBoundaryValueTable();
		BoundaryValueAnalyze.printInputValue();

		//入力値をif条件文判定してkey作成
		new EvaluationOfConditions();
		EvaluationOfConditions.printEvaluationResult();

		outputBoundaryValueTestcase(fileMakeFlag);

		//tm.finish();
		//tm.printResult();

		System.out.println("／(^o^)＼");

	}


	public static void outputBoundaryValueTestcase(Boolean fileOrStandardOutput){

		String[][] inputData = BoundaryValueAnalyze.getInputData();
		ArrayList<String> evaluationResult = EvaluationOfConditions.getEvaluationResult();

		new DecisionTable(AnalyzedData.getCsvFilePath());

		System.out.println("引数の個数:" + AnalyzedData.getArgumentTypes().size() + "\n");
		System.out.print("引数型: ");
		for(int i=0; i<AnalyzedData.getArgumentTypes().size(); i++){
			System.out.print(AnalyzedData.getArgumentTypes().get(i) + " ");
		}
		System.out.println("\n\n入力値 --> 期待出力値");


		if(fileOrStandardOutput) {
			FileWriter outputFile=null;

			//ファイル作成、引数個、引数の書き込み
			try {
				outputFile =
						new FileWriter(new File(AnalyzedData.getVdmFilePath().replace(".vdmpp", "") + "Testcase.csv"));
				//引数の個数の書き込み
				outputFile.write("引数の個数:" + AnalyzedData.getArgumentTypes().size() + "\n\n");
				//引数型の書き込み
				String tmp = new String();
				for(int i=0; i<AnalyzedData.getArgumentTypes().size(); i++){
					tmp = tmp +"第"+(i+1)+"引数:"+ AnalyzedData.getArgumentTypes().get(i) +",";
				}
				outputFile.write("引数型:," + tmp + "\n\n");
				outputFile.write("テストケースNo. 入力データ  --> 期待出力データ" + "\n");
				System.out.println("ファイル作成成功！");

				for(int i=0; i<inputData.length; i++){
					try {
						outputFile.write("No."+(i+1)+","+String.join(",", inputData[i]) + ",-->,\"" +
								DecisionTable.getBooleanSequenceToAction().get(evaluationResult.get(i)) + "\"\n");
						System.out.println("ファイル書き込み成功！"+evaluationResult.get(i));
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("ファイル書き込みしくった！");
					}
				}

				outputFile.close();
				System.out.println("ファイルくろーず成功！");
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("ファイル作成しくった！");
			}
		} else {
			for(int i=0; i<inputData.length; i++){
				System.out.println("("+String.join(",", inputData[i]) + ") --> " +
						DecisionTable.getBooleanSequenceToAction().get(evaluationResult.get(i)));
			}
		}


	}


}
*/
