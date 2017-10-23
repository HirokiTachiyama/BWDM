new OmlDocument("../data/Arg2_doubleVariables.vdmpp",
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
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(2,7,11),7,11),
                    new OmlBinaryOperator(2,7,13),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(3,7,15),7,15),
                    7,
                    13
                  ),7,10),
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("a<b",8,11),8,11),
                  [
                    
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("a>=b",10,5),10,5),
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
    new OmlLexem(7,11,435,"2",0),
    new OmlLexem(7,13,337,"<",0),
    new OmlLexem(7,15,435,"3",0),
    new OmlLexem(7,16,41,")",0),
    new OmlLexem(7,18,410,"then",1),
    new OmlLexem(8,11,437,"\"a<b\"",0),
    new OmlLexem(9,8,296,"else",1),
    new OmlLexem(10,5,437,"\"a>=b\"",0),
    new OmlLexem(11,1,298,"end",1),
    new OmlLexem(11,5,433,"Sample",2)
  ]
)
