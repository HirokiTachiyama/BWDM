new OmlDocument("../data/problem.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "ProblemClass",
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
              "problemFunction",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlNatType(5,19),
                new OmlSeq0Type(new OmlCharType(5,33),5,26),
                5,
                19
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("a",6,19)
                ],6,18)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBracketedExpression(new OmlBinaryExpression(
                    new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "a",
                        7,
                        8
                      ),
                      new OmlBinaryOperator(4,7,10),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(4,7,14),7,14),
                      7,
                      10
                    ),
                    new OmlBinaryOperator(27,7,16),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,7,18),7,18),
                    7,
                    16
                  ),7,7),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "a",
                        8,
                        10
                      ),
                      new OmlBinaryOperator(23,8,12),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(92,8,14),8,14),
                      8,
                      12
                    ),8,9),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a mod 4 = 0 and a > 92",9,9),9,9),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a mod 4 = 0 and !(a > 92)",11,9),11,9),
                    8,
                    7
                  ),
                  [
                    
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("!(a mod 4 = 0)",13,7),13,7),
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
    new OmlLexem(1,7,433,"ProblemClass",2),
    new OmlLexem(3,1,312,"functions",1),
    new OmlLexem(5,1,433,"problemFunction",2),
    new OmlLexem(5,17,58,":",0),
    new OmlLexem(5,19,352,"nat",1),
    new OmlLexem(5,23,264,"->",0),
    new OmlLexem(5,26,390,"seq",1),
    new OmlLexem(5,30,360,"of",1),
    new OmlLexem(5,33,274,"char",1),
    new OmlLexem(6,3,433,"problemFunction",2),
    new OmlLexem(6,18,40,"(",0),
    new OmlLexem(6,19,433,"a",2),
    new OmlLexem(6,20,41,")",0),
    new OmlLexem(6,22,333,"==",0),
    new OmlLexem(7,5,317,"if",1),
    new OmlLexem(7,7,40,"(",0),
    new OmlLexem(7,8,433,"a",2),
    new OmlLexem(7,10,348,"mod",1),
    new OmlLexem(7,14,435,"4",0),
    new OmlLexem(7,16,299,"=",0),
    new OmlLexem(7,18,435,"0",0),
    new OmlLexem(7,19,41,")",0),
    new OmlLexem(7,21,410,"then",1),
    new OmlLexem(8,7,317,"if",1),
    new OmlLexem(8,9,40,"(",0),
    new OmlLexem(8,10,433,"a",2),
    new OmlLexem(8,12,313,">",0),
    new OmlLexem(8,14,435,"92",0),
    new OmlLexem(8,16,41,")",0),
    new OmlLexem(8,18,410,"then",1),
    new OmlLexem(9,9,437,"\"a mod 4 = 0 and a > 92\"",0),
    new OmlLexem(10,7,296,"else",1),
    new OmlLexem(11,9,437,"\"a mod 4 = 0 and !(a > 92)\"",0),
    new OmlLexem(12,5,296,"else",1),
    new OmlLexem(13,7,437,"\"!(a mod 4 = 0)\"",0),
    new OmlLexem(13,23,59,";",0),
    new OmlLexem(15,1,298,"end",1),
    new OmlLexem(15,5,433,"ProblemClass",2)
  ]
)
