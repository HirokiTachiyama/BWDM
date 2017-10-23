new OmlDocument("../data/Arg2_chapter3SampleUTF.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "Sample",
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
              "SampleFunction",
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
                  new OmlPatternIdentifier("a",6,20),
                  new OmlPatternIdentifier("b",6,23)
                ],6,19)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBracketedExpression(new OmlBinaryExpression(
                    new OmlName(
                      nil,
                      "a",
                      7,
                      11
                    ),
                    new OmlBinaryOperator(31,7,13),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(10,7,16),7,16),
                    7,
                    13
                  ),7,10),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlUnaryExpression(
                        new OmlUnaryOperator(15,8,13),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(1,8,14),8,14),
                        8,
                        13
                      ),
                      new OmlBinaryOperator(2,8,16),
                      new OmlName(
                        nil,
                        "a",
                        8,
                        18
                      ),
                      8,
                      16
                    ),8,12),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは-1より大きく10以下です",9,11),9,11),
                    [
                      new OmlElseIfExpression(
                        new OmlBracketedExpression(new OmlBinaryExpression(
                          new OmlName(
                            nil,
                            "b",
                            10,
                            14
                          ),
                          new OmlBinaryOperator(23,10,16),
                          new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,10,18),10,18),
                          10,
                          16
                        ),10,13),
                        new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは-1以下で、bは0より大きいです",11,9),11,9),
                        10,
                        7
                      )
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは-1以下で、bは0以下です",13,5),13,5),
                    8,
                    10
                  ),
                  [
                    
                  ],
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "b",
                        15,
                        14
                      ),
                      new OmlBinaryOperator(31,15,16),
                      new OmlUnaryExpression(
                        new OmlUnaryOperator(15,15,19),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(3,15,20),15,20),
                        15,
                        19
                      ),
                      15,
                      16
                    ),15,12),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは10より大きく、bは-3以下です",16,12),16,12),
                    [
                      new OmlElseIfExpression(
                        new OmlBracketedExpression(new OmlBinaryExpression(
                          new OmlName(
                            nil,
                            "a",
                            17,
                            17
                          ),
                          new OmlBinaryOperator(2,17,19),
                          new OmlSymbolicLiteralExpression(new OmlNumericLiteral(20,17,21),17,21),
                          17,
                          19
                        ),17,16),
                        new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは10より大きく20未満で、bは-3より大きいです",18,12),18,12),
                        17,
                        10
                      )
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは20以上で、bは-3より大きいです",20,5),20,5),
                    15,
                    10
                  ),
                  7,
                  8
                ),
                false,
                false,
                7,
                8
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
    new OmlLexem(1,7,433,"Sample",2),
    new OmlLexem(3,1,312,"functions",1),
    new OmlLexem(5,1,433,"SampleFunction",2),
    new OmlLexem(5,16,58,":",0),
    new OmlLexem(5,18,324,"int",1),
    new OmlLexem(5,21,42,"*",0),
    new OmlLexem(5,22,352,"nat",1),
    new OmlLexem(5,26,264,"->",0),
    new OmlLexem(5,29,390,"seq",1),
    new OmlLexem(5,33,360,"of",1),
    new OmlLexem(5,36,274,"char",1),
    new OmlLexem(6,5,433,"SampleFunction",2),
    new OmlLexem(6,19,40,"(",0),
    new OmlLexem(6,20,433,"a",2),
    new OmlLexem(6,21,44,",",0),
    new OmlLexem(6,23,433,"b",2),
    new OmlLexem(6,24,41,")",0),
    new OmlLexem(6,26,333,"==",0),
    new OmlLexem(7,8,317,"if",1),
    new OmlLexem(7,10,40,"(",0),
    new OmlLexem(7,11,433,"a",2),
    new OmlLexem(7,13,338,"<=",0),
    new OmlLexem(7,16,435,"10",0),
    new OmlLexem(7,18,41,")",0),
    new OmlLexem(7,20,410,"then",1),
    new OmlLexem(8,10,317,"if",1),
    new OmlLexem(8,12,40,"(",0),
    new OmlLexem(8,13,45,"-",0),
    new OmlLexem(8,14,435,"1",0),
    new OmlLexem(8,16,337,"<",0),
    new OmlLexem(8,18,433,"a",2),
    new OmlLexem(8,19,41,")",0),
    new OmlLexem(8,21,410,"then",1),
    new OmlLexem(9,11,437,"\"aは-1より大きく10以下です\"",0),
    new OmlLexem(10,7,297,"elseif",1),
    new OmlLexem(10,13,40,"(",0),
    new OmlLexem(10,14,433,"b",2),
    new OmlLexem(10,16,313,">",0),
    new OmlLexem(10,18,435,"0",0),
    new OmlLexem(10,19,41,")",0),
    new OmlLexem(10,21,410,"then",1),
    new OmlLexem(11,9,437,"\"aは-1以下で、bは0より大きいです\"",0),
    new OmlLexem(12,3,296,"else",1),
    new OmlLexem(13,5,437,"\"aは-1以下で、bは0以下です\"",0),
    new OmlLexem(14,8,296,"else",1),
    new OmlLexem(15,10,317,"if",1),
    new OmlLexem(15,12,40,"(",0),
    new OmlLexem(15,14,433,"b",2),
    new OmlLexem(15,16,338,"<=",0),
    new OmlLexem(15,19,45,"-",0),
    new OmlLexem(15,20,435,"3",0),
    new OmlLexem(15,22,41,")",0),
    new OmlLexem(15,24,410,"then",1),
    new OmlLexem(16,12,437,"\"aは10より大きく、bは-3以下です\"",0),
    new OmlLexem(17,10,297,"elseif",1),
    new OmlLexem(17,16,40,"(",0),
    new OmlLexem(17,17,433,"a",2),
    new OmlLexem(17,19,337,"<",0),
    new OmlLexem(17,21,435,"20",0),
    new OmlLexem(17,23,41,")",0),
    new OmlLexem(17,25,410,"then",1),
    new OmlLexem(18,12,437,"\"aは10より大きく20未満で、bは-3より大きいです\"",0),
    new OmlLexem(19,3,296,"else",1),
    new OmlLexem(20,5,437,"\"aは20以上で、bは-3より大きいです\"",0),
    new OmlLexem(20,26,59,";",0),
    new OmlLexem(21,1,298,"end",1),
    new OmlLexem(21,5,433,"Sample",2)
  ]
)
