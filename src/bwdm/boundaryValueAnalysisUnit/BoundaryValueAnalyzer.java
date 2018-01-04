package bwdm.boundaryValueAnalysisUnit;

import bwdm.informationStore.InformationExtractor;
import bwdm.Util;

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

	private HashMap boundaryValueList;
	private ArrayList<HashMap<String, Long>> inputDataList;


	public BoundaryValueAnalyzer(InformationExtractor _information) {
		//generation of instance of each parameter
		boundaryValueList = new HashMap<String, ArrayList<Integer>>();
		_information.getParameters().forEach(p -> {
			boundaryValueList.put(p, new ArrayList<Integer>());
		});

		generateTypeBoundaryValue(_information);
		generateIfConditionalBoundaryValue(_information);

		//remove overlapped values
		ArrayList<String> parameters = _information.getParameters();
		for(int i = 0; i < boundaryValueList.size(); i++) {
			String parameter = parameters.get(i);
			ArrayList bvs = (ArrayList) boundaryValueList.get(parameter);
			bvs = (ArrayList) bvs.stream().distinct().collect(Collectors.toList());
			boundaryValueList.put(parameter, bvs);
		}

		makeInputDataList(_information);
	}

	public HashMap getBoundaryValueList() { return boundaryValueList; }
	public ArrayList<HashMap<String, Long>> getInputDataList() { return inputDataList; }
	public void printAllInputValue() {
		inputDataList.forEach(inputData -> {
			inputData.forEach((k, v) -> {
				System.out.print(v + " ");
			});
			System.out.println();
		});
	}


	private void generateTypeBoundaryValue(InformationExtractor _information) {
		ArrayList<String> parameters = _information.getParameters();
		ArrayList<String> argumentTypes = _information.getArgumentTypes();

		for(int i=0; i<argumentTypes.size(); i++) {
			String parameter = parameters.get(i);
			String argumentType = argumentTypes.get(i);
			ArrayList bvs = (ArrayList) boundaryValueList.get(parameter);
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
				String operator = ((HashMap<String, String>) condition).get("operator");
				String right  = ((HashMap<String, String>) condition).get("right");
				ArrayList bvs = (ArrayList) boundaryValueList.get(parameter);

				long bv1=0, bv2=0, value=0;
				if(Util.isNumber(left)) {
					value = Long.parseLong(left);
					switch (operator) {
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

				} else if(Util.isNumber(right)) {
					value = Long.parseLong(right);
					switch (operator) {
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

	private void makeInputDataList(InformationExtractor _information) {
		ArrayList parameters = _information.getParameters();
		inputDataList = new ArrayList<HashMap<String, Long>>();

		//最初の一つ目
		String first_prm = (String) parameters.get(0);
		ArrayList<Integer> first_bvs = (ArrayList) boundaryValueList.get(first_prm);
		for(int i=0; i<first_bvs.size(); i++) {
			inputDataList.add(new HashMap());
			HashMap hm = inputDataList.get(i);
			hm.put(first_prm, first_bvs.get(i));
		}

		//それ以降
		parameters.forEach(p -> {
			if( !p.equals(first_prm) ) { //最初の要素以外に対して
				ArrayList current_bvs = (ArrayList) boundaryValueList.get(p);

				//inputDataListの第一引数のみを登録した状態
				ArrayList inputDataListInitialState = (ArrayList) inputDataList.clone();

				for(int i=0; i<current_bvs.size() - 1; i++) {
					ArrayList inputDataListTmpTmp = new ArrayList<Long>();
					inputDataListInitialState.forEach(inputDataOriginal -> {
						//inputDataを複製
						HashMap inputData = new HashMap<String, Long>();
						((HashMap) inputDataOriginal).forEach((k, v) -> {
							inputData.put(k, v);
						});

						inputDataListTmpTmp.add(inputData);
					});
					inputDataList.addAll(inputDataListTmpTmp);
				}
				int repeatTimesOfInsert = 0;
				for(int j=0; j<current_bvs.size(); j++) {
					long currentBv = (long) current_bvs.get(j);
					int offset = repeatTimesOfInsert*inputDataListInitialState.size();
					for(int k=0; k<inputDataListInitialState.size(); k++) {
						HashMap inputData = inputDataList.get(k + offset);
						inputData.put(p, currentBv);
					}
					repeatTimesOfInsert++;
				}

			}
		});
	} //end makeInputDatList

}
