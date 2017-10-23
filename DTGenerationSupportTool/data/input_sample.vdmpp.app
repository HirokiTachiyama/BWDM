new OmlDocument("../data/input_sample.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "SampleClass",
      [
        
      ],
      nil,
      [
        new OmlFunctionDefinitions([
          new OmlFunctionDefinition(
            new OmlAccessDefinition(
              false,
              false,
              new OmlScope(3,5,1),
              0,
              0
            ),
            new OmlExplicitFunction(
              "sampleFunction",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlProductType(
                  new OmlIntType(5,18),
                  new OmlNatType(5,22),
                  5,
                  21
                ),
                new OmlSeq0Type(new OmlCharType(5,36),5,29),
                5,
                21
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("a",6,18),
                  new OmlPatternIdentifier("b",6,21)
                ],6,17)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBracketedExpression(new OmlBinaryExpression(
                    new OmlName(
                      nil,
                      "a",
                      7,
                      8
                    ),
                    new OmlBinaryOperator(31,7,10),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(10,7,13),7,13),
                    7,
                    10
                  ),7,7),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlUnaryExpression(
                        new OmlUnaryOperator(15,8,10),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(1,8,11),8,11),
                        8,
                        10
                      ),
                      new OmlBinaryOperator(2,8,13),
                      new OmlName(
                        nil,
                        "a",
                        8,
                        15
                      ),
                      8,
                      13
                    ),8,9),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a <= 10 and -1 < a",9,9),9,9),
                    [
                      
                    ],
                    new OmlIfExpression(
                      new OmlBracketedExpression(new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "b",
                          10,
                          15
                        ),
                        new OmlBinaryOperator(23,10,17),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,10,19),10,19),
                        10,
                        17
                      ),10,14),
                      new OmlSymbolicLiteralExpression(new OmlTextLiteral("a <= 10 and !(-1 > a) and b > 0",11,3),11,3),
                      [
                        
                      ],
                      new OmlSymbolicLiteralExpression(new OmlTextLiteral("a <= 10 and !(-1 > a) and !(b > 0)",13,9),13,9),
                      10,
                      12
                    ),
                    8,
                    7
                  ),
                  [
                    
                  ],
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "b",
                        15,
                        10
                      ),
                      new OmlBinaryOperator(31,15,12),
                      new OmlUnaryExpression(
                        new OmlUnaryOperator(15,15,15),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(3,15,16),15,16),
                        15,
                        15
                      ),
                      15,
                      12
                    ),15,9),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("!(a <= 10) and b <= -3",16,9),16,9),
                    [
                      
                    ],
                    new OmlIfExpression(
                      new OmlBracketedExpression(new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "a",
                          17,
                          15
                        ),
                        new OmlBinaryOperator(2,17,17),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(20,17,19),17,19),
                        17,
                        17
                      ),17,14),
                      new OmlSymbolicLiteralExpression(new OmlTextLiteral("!(a <= 10) and !(b <= -3) and a < 20",18,6),18,6),
                      [
                        
                      ],
                      new OmlSymbolicLiteralExpression(new OmlTextLiteral("!(a <= 10) and !(b <= -3) and !(a < 20)",20,9),20,9),
                      17,
                      12
                    ),
                    15,
                    7
                  ),
                  7,
                  5
                ),
                false,
                false,
                7,
                5
              ),
              new OmlFunctionTrailer(
                nil,
                nil,
                0,
                0
              ),
              5,
              1
            ),
            5,
            1
          )
        ],3,1)
      ],
      false,
      1,
      1
    )
  ],0,0),
  [
    new OmlLexem(1,1,275,"class",1),
    new OmlLexem(1,7,433,"SampleClass",2),
    new OmlLexem(3,1,312,"functions",1),
    new OmlLexem(5,1,433,"sampleFunction",2),
    new OmlLexem(5,16,58,":",0),
    new OmlLexem(5,18,324,"int",1),
    new OmlLexem(5,21,42,"*",0),
    new OmlLexem(5,22,352,"nat",1),
    new OmlLexem(5,26,264,"->",0),
    new OmlLexem(5,29,390,"seq",1),
    new OmlLexem(5,33,360,"of",1),
    new OmlLexem(5,36,274,"char",1),
    new OmlLexem(6,3,433,"sampleFunction",2),
    new OmlLexem(6,17,40,"(",0),
    new OmlLexem(6,18,433,"a",2),
    new OmlLexem(6,19,44,",",0),
    new OmlLexem(6,21,433,"b",2),
    new OmlLexem(6,22,41,")",0),
    new OmlLexem(6,24,333,"==",0),
    new OmlLexem(7,5,317,"if",1),
    new OmlLexem(7,7,40,"(",0),
    new OmlLexem(7,8,433,"a",2),
    new OmlLexem(7,10,338,"<=",0),
    new OmlLexem(7,13,435,"10",0),
    new OmlLexem(7,15,41,")",0),
    new OmlLexem(7,17,410,"then",1),
    new OmlLexem(8,7,317,"if",1),
    new OmlLexem(8,9,40,"(",0),
    new OmlLexem(8,10,45,"-",0),
    new OmlLexem(8,11,435,"1",0),
    new OmlLexem(8,13,337,"<",0),
    new OmlLexem(8,15,433,"a",2),
    new OmlLexem(8,16,41,")",0),
    new OmlLexem(8,18,410,"then",1),
    new OmlLexem(9,9,437,"\"a <= 10 and -1 < a\"",0),
    new OmlLexem(10,7,296,"else",1),
    new OmlLexem(10,12,317,"if",1),
    new OmlLexem(10,14,40,"(",0),
    new OmlLexem(10,15,433,"b",2),
    new OmlLexem(10,17,313,">",0),
    new OmlLexem(10,19,435,"0",0),
    new OmlLexem(10,20,41,")",0),
    new OmlLexem(10,22,410,"then",1),
    new OmlLexem(11,3,437,"\"a <= 10 and !(-1 > a) and b > 0\"",0),
    new OmlLexem(12,7,296,"else",1),
    new OmlLexem(13,9,437,"\"a <= 10 and !(-1 > a) and !(b > 0)\"",0),
    new OmlLexem(14,5,296,"else",1),
    new OmlLexem(15,7,317,"if",1),
    new OmlLexem(15,9,40,"(",0),
    new OmlLexem(15,10,433,"b",2),
    new OmlLexem(15,12,338,"<=",0),
    new OmlLexem(15,15,45,"-",0),
    new OmlLexem(15,16,435,"3",0),
    new OmlLexem(15,17,41,")",0),
    new OmlLexem(15,19,410,"then",1),
    new OmlLexem(16,9,437,"\"!(a <= 10) and b <= -3\"",0),
    new OmlLexem(17,7,296,"else",1),
    new OmlLexem(17,12,317,"if",1),
    new OmlLexem(17,14,40,"(",0),
    new OmlLexem(17,15,433,"a",2),
    new OmlLexem(17,17,337,"<",0),
    new OmlLexem(17,19,435,"20",0),
    new OmlLexem(17,21,41,")",0),
    new OmlLexem(17,23,410,"then",1),
    new OmlLexem(18,6,437,"\"!(a <= 10) and !(b <= -3) and a < 20\"",0),
    new OmlLexem(19,7,296,"else",1),
    new OmlLexem(20,9,437,"\"!(a <= 10) and !(b <= -3) and !(a < 20)\"",0),
    new OmlLexem(20,50,59,";",0),
    new OmlLexem(21,2,298,"end",1),
    new OmlLexem(21,6,433,"SampleClass",2)
  ]
)
