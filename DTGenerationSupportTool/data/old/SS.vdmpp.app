new OmlDocument("../data/SS.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "problem_example",
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
              "problem_function",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlNatType(5,20),
                new OmlSeq0Type(new OmlCharType(5,34),5,27),
                5,
                20
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("a",6,22)
                ],6,21)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBracketedExpression(new OmlBinaryExpression(
                    new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "a",
                        7,
                        12
                      ),
                      new OmlBinaryOperator(4,7,14),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(4,7,18),7,18),
                      7,
                      14
                    ),
                    new OmlBinaryOperator(27,7,20),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,7,22),7,22),
                    7,
                    20
                  ),7,11),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "a",
                        8,
                        16
                      ),
                      new OmlBinaryOperator(23,8,18),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(92,8,20),8,20),
                      8,
                      18
                    ),8,15),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("96, 100, 104, ...",9,14),9,14),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("..., 84, 88, 92",11,12),11,12),
                    8,
                    13
                  ),
                  [
                    
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("others",13,6),13,6),
                  7,
                  9
                ),
                false,
                false,
                7,
                9
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
    new OmlLexem(1,7,433,"problem_example",2),
    new OmlLexem(3,1,312,"functions",1),
    new OmlLexem(5,1,433,"problem_function",2),
    new OmlLexem(5,18,58,":",0),
    new OmlLexem(5,20,352,"nat",1),
    new OmlLexem(5,24,264,"->",0),
    new OmlLexem(5,27,390,"seq",1),
    new OmlLexem(5,31,360,"of",1),
    new OmlLexem(5,34,274,"char",1),
    new OmlLexem(6,5,433,"problem_function",2),
    new OmlLexem(6,21,40,"(",0),
    new OmlLexem(6,22,433,"a",2),
    new OmlLexem(6,23,41,")",0),
    new OmlLexem(6,25,333,"==",0),
    new OmlLexem(7,9,317,"if",1),
    new OmlLexem(7,11,40,"(",0),
    new OmlLexem(7,12,433,"a",2),
    new OmlLexem(7,14,348,"mod",1),
    new OmlLexem(7,18,435,"4",0),
    new OmlLexem(7,20,299,"=",0),
    new OmlLexem(7,22,435,"0",0),
    new OmlLexem(7,23,41,")",0),
    new OmlLexem(7,25,410,"then",1),
    new OmlLexem(8,13,317,"if",1),
    new OmlLexem(8,15,40,"(",0),
    new OmlLexem(8,16,433,"a",2),
    new OmlLexem(8,18,313,">",0),
    new OmlLexem(8,20,435,"92",0),
    new OmlLexem(8,22,41,")",0),
    new OmlLexem(8,24,410,"then",1),
    new OmlLexem(9,14,437,"\"96, 100, 104, ...\"",0),
    new OmlLexem(10,6,296,"else",1),
    new OmlLexem(11,12,437,"\"..., 84, 88, 92\"",0),
    new OmlLexem(12,2,296,"else",1),
    new OmlLexem(13,6,437,"\"others\"",0),
    new OmlLexem(13,14,59,";",0),
    new OmlLexem(15,1,298,"end",1),
    new OmlLexem(15,5,433,"problem_example",2)
  ]
)
