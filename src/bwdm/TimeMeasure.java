package bwdm;

import java.io.*;
import java.text.NumberFormat;

/**
*処理速度を管理するクラス.
*@author Yukichi Tamura
*/
public class TimeMeasure {

	/**ナノ秒から秒に換算するための割数.*/
	private static final int DIVISOR = 1000000000;

	/**NumberFormatインスタンス.*/
	private static final NumberFormat nf = NumberFormat.getInstance();

	/**PrintWriterインスタンス.*/
	private static PrintWriter pw;

	/**計測開始時間.*/
	private double startTime;

	/**計測終了時間.*/
	private double endTime;

	/**
	*Constructor.
	*/
	TimeMeasure() {
		//フォーマットを10桁で指定
		nf.setMaximumFractionDigits(10);
	}
	/**
	*Constructor.
	*@param filename 計測結果を書き出すファイル
	*/
	TimeMeasure(String filename) {
		this();
		try {
			pw  = new PrintWriter(new OutputStreamWriter(new FileOutputStream(new File(filename), true)));
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	*計測開始時間をセット.
	*/
	public void start() {
		this.startTime = System.nanoTime();
	}

	/**
	*計測終了時間をセット.
	*/
	public void finish() {
		this.endTime = System.nanoTime();
	}

	/**
	*計測結果を取得.
	*@return 指定範囲の処理にかかった時間を秒で返す
	*/
	public String getResult() {
		return nf.format((this.endTime - this.startTime) / DIVISOR);
	}

	/**
	*計測結果を出力.
	*指定範囲の処理にかかった時間を秒で出力する.
	*/
	public void printResult() {
		System.out.println(getResult());
	}

	/**
	*計測結果をファイルに出力.
	*指定範囲の処理にかかった時間を秒でファイルに追加して書き出す.
	*/
	public void writeResult() {
		pw.println(getResult());
		pw.close();
	}

}
