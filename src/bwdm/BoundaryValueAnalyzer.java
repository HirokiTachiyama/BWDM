package bwdm;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class BoundaryValueAnalyzer {

	final long intMax  = Integer.MAX_VALUE;
	final long intMin  = Integer.MIN_VALUE;
	final long natMax  = intMax * 2;
	final long natMin  = 0;
	final long nat1Max = natMax + 1;
	final long nat1Min = 1;

	private HashMap boundaryValueTable;
	private long[][] inputData;


	public BoundaryValueAnalyzer(InformationExtractor _information) {
		//generation of instance of each parameter
		boundaryValueTable = new HashMap<String, ArrayList<Integer>>();
		_information.getParameters().forEach(p -> {
			boundaryValueTable.put(p, new ArrayList<Integer>());
		});

		generateTypeBoundaryValue(_information);
		generateIfConditionalBoundaryValue(_information);

		//remove overlapped values
		boundaryValueTable.forEach((k, bvs) -> {
			bvs = ((ArrayList) bvs).stream().distinct().collect(Collectors.toList());
		});

		makeInputData(_information);
		for(int i=0; i<inputData.length; i++) {
			System.out.print("i:"+i+" ->");
			for(int j=0; j<inputData[0].length; j++) {
				System.out.print(j+",");
			}
			System.out.println();
		}

	}

	private void generateTypeBoundaryValue(InformationExtractor _information) {
		ArrayList<String> parameters = _information.getParameters();
		ArrayList<String> argumentTypes = _information.getArgumentTypes();

		for(int i=0; i<argumentTypes.size(); i++) {
			String parameter = parameters.get(i);
			String argumentType = argumentTypes.get(i);
			ArrayList bvs = (ArrayList) boundaryValueTable.get(parameter);
			switch (argumentType) {
				case "int":
					bvs.add(intMax);
					bvs.add(intMin);
					break;
				case "nat":
					bvs.add(natMax);
					bvs.add(natMin);
					break;
				case "nat1":
					bvs.add(nat1Max);
					bvs.add(nat1Min);
					break;
				default:
					break;
			}
		}
	}


	private void generateIfConditionalBoundaryValue(InformationExtractor _information) {
		HashMap allIfConditions = _information.getIfConditions();

		allIfConditions.forEach( (parameter, ifConditions) ->{
			((ArrayList) ifConditions).forEach(condition -> { //condition : HashMap<String, String>
				String left   = ((HashMap<String, String>) condition).get("left");
				String symbol = ((HashMap<String, String>) condition).get("symbol");
				String right  = ((HashMap<String, String>) condition).get("right");
				System.out.println("cond"+left+symbol+right+"tion");
				ArrayList bvs = (ArrayList) boundaryValueTable.get(parameter);

				long bv1=0, bv2=0, value=0;
				if(isNumber(left)) {
					value = Long.parseLong(left);
					switch (symbol) {
						case "<=":
							bv1 = value;
							bv2 = bv1 - 1;
							break;
						case "<":
							bv1 = value + 1;
							bv2 = bv1 - 1;
							break;
						case ">=":
							bv1 = value;
							bv2 = bv1 + 1;
							break;
						case ">":
							bv1 = value + 1;
							bv2 = bv1 + 1;
							break;
						case "mod":
							bv1 = value;
							bv2 = value + 1;
							bvs.add(value - 1);
							break;
					}

				} else if(isNumber(right)) {
					value = Long.parseLong(right);
					switch (symbol) {
						case "<=":
							bv1 = value;
							bv2 = bv1 + 1;
							break;
						case "<":
							bv1 = value + 1;
							bv2 = bv1 - 1;
							break;
						case ">=":
							bv1 = value;
							bv2 = bv1 + 1;
							break;
						case ">":
							bv1 = value + 1;
							bv2 = bv1 - 1;
							break;
						case "mod":
							bv1 = value;
							bv2 = value + 1;
							bvs.add(value - 1);
							break;
					}
				}

				bvs.add(bv1);
				bvs.add(bv2);

			});

		});
	}

	private boolean isNumber(String num) {
		try {
			Integer.parseInt(num);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}


	@SuppressWarnings("unchecked")
	private void makeInputData(InformationExtractor _information){


		int inputDataNumber = 1;

		ArrayList parameters = _information.getParameters();
		int[] nums = new int[parameters.size()];
		int nums_count=0;
		parameters.forEach(p -> {
			 *= ((ArrayList)boundaryValueTable.get(p)).size();
		});

		inputData = new long[inputDataNumber][];
		for(int i=0; i<inputDataNumber; i++) {
			inputData[i] = new long[_information.getParameters().size()];
		}

		parameters = _information.getParameters();
		String[] args = new String[parameters.size()];
		for(int i=0; i<args.length; i++) {
			args[i] = (String) parameters.get(i);
		}

		switch(_information.getParameters().size()){
			case 0:
				System.out.println("no parameter, exit.");
				System.exit(-1);
				break;
			case 1:
				nTimesInsert(
						1,
						0,
						0,
						(ArrayList) boundaryValueTable.get(args[0]));
				break;
			case 2:
				nTimesInsert(
						((ArrayList) boundaryValueTable.get(args[1])).size(),
						0,
						0,
						(ArrayList) boundaryValueTable.get(args[0]));
				for(int i=0; i<inputData.length; i+=((ArrayList)boundaryValueTable.get(1)).size()){
					nTimesInsert(
							1,
							i,
							1,
							(ArrayList) boundaryValueTable.get(args[1]));
				}
				break;
			default:
				System.out.println("Invalid arguments");
				System.exit(-1);
				break;
		}

	}

	//与えられたArrayList<String>を一つの要素につきtimes回ずつ
	//inputDataのlineNumber行目columnNumber列目, lineNumber+1行目columnNumber列目,,,と入れていく
	private void nTimesInsert(int times, int lineNumber, int columnNumber, ArrayList _bv){
		int currentLineNumber=0;
		for(int i=0; i<_bv.size(); i++){ //argsの要素一つ一つに対して
			for(int j=0; j<times; j++){
				inputData[lineNumber+currentLineNumber][columnNumber] = (long)_bv.get(i);
				currentLineNumber++;
			}
		}
	}



}
