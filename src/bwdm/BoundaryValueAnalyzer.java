package bwdm;

import java.util.ArrayList;
import java.util.HashMap;

public class BoundaryValueAnalyzer {

	final int intMax  = Integer.MAX_VALUE;
	final int intMin  = Integer.MIN_VALUE;
	final int natMax  = Integer.MAX_VALUE * 2;
	final int natMin  = 0;
	final int nat1Max = Integer.MAX_VALUE * 2 + 1;
	final int nat1Min = 1;

	private HashMap boundaryValueTable;

	public BoundaryValueAnalyzer(InformationExtractor _information) {
		//generation of instance of each parameter
		boundaryValueTable = new HashMap<String, ArrayList<Integer>>();
		_information.getParameters().forEach(p -> {
			boundaryValueTable.put(p, new ArrayList<Integer>());
		});

		generateTypeBoundaryValue(_information);
		generateIfConditionalBoundaryValue(_information);

//		_information.getParameters().forEach(p -> {
//			ArrayList al = (ArrayList) boundaryValueTable.get(p);
//			al.forEach(a ->{
//				System.out.println("**" + a + "**");
//			});
//		});
	}

	private void generateTypeBoundaryValue(InformationExtractor _information) {
		ArrayList<String> parameters = _information.getParameters();
		ArrayList<String> argumentTypes = _information.getArgumentTypes();

		for(int i=0; i<argumentTypes.size(); i++) {
			String parameter = parameters.get(i);
			String argumentType = argumentTypes.get(i);
			ArrayList al = (ArrayList) boundaryValueTable.get(parameter);
			switch (argumentType) {
				case "int":
					al.add(intMax);
					al.add(intMin);
					break;
				case "nat":
					al.add(natMax);
					al.add(natMin);
					break;
				case "nat1":
					al.add(nat1Max);
					al.add(nat1Min);
					break;
				default:
					break;
			}
		}
	}


	private void generateIfConditionalBoundaryValue(InformationExtractor _information) {
		HashMap allIfConditions = _information.getIfConditions();

		_information.getParameters().forEach( parameter -> {
			ArrayList ifConditionsOfEachParameter =
					(ArrayList) allIfConditions.get(parameter);


		});

	}



}
