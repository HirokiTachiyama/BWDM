new OmlDocument("../data/modtohoho.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "hoge",
      [
        
      ],
      nil,
      [
        new OmlFunctionDefinitions([
          new OmlFunctionDefinition(
            new OmlAccessDefinition(
              false,
              false,
              new OmlScope(2,5,1),
              5,
              1
            ),
            new OmlExplicitFunction(
              "func",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlProductType(
                  new OmlIntType(5,15),
                  new OmlNatType(5,21),
                  5,
                  19
                ),
                new OmlSeq0Type(new OmlCharType(5,34),5,27),
                5,
                19
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("a",6,6),
                  new OmlPatternIdentifier("b",6,9)
                ],6,5)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBracketedExpression(new OmlBinaryExpression(
                    new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "a",
                        7,
                        6
                      ),
                      new OmlBinaryOperator(4,7,8),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(5,7,12),7,12),
                      7,
                      8
                    ),
                    new OmlBinaryOperator(27,7,14),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,7,16),7,16),
                    7,
                    14
                  ),7,5),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "b",
                        8,
                        8
                      ),
                      new OmlBinaryOperator(31,8,10),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(10,8,13),8,13),
                      8,
                      10
                    ),8,7),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a5 && b<=5",9,7),9,7),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a5",11,7),11,7),
                    8,
                    5
                  ),
                  [
                    
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("other",13,5),13,5),
                  7,
                  3
                ),
                false,
                false,
                7,
                3
              ),
              new OmlFunctionTrailer(
                nil,
                nil,
                0,
                0
              ),
              5,
              8
            ),
            5,
            8
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
    new OmlLexem(1,7,433,"hoge",2),
    new OmlLexem(3,1,312,"functions",1),
    new OmlLexem(5,1,375,"public",1),
    new OmlLexem(5,8,433,"func",2),
    new OmlLexem(5,13,58,":",0),
    new OmlLexem(5,15,324,"int",1),
    new OmlLexem(5,19,42,"*",0),
    new OmlLexem(5,21,352,"nat",1),
    new OmlLexem(5,24,264,"->",0),
    new OmlLexem(5,27,390,"seq",1),
    new OmlLexem(5,31,360,"of",1),
    new OmlLexem(5,34,274,"char",1),
    new OmlLexem(6,1,433,"func",2),
    new OmlLexem(6,5,40,"(",0),
    new OmlLexem(6,6,433,"a",2),
    new OmlLexem(6,7,44,",",0),
    new OmlLexem(6,9,433,"b",2),
    new OmlLexem(6,10,41,")",0),
    new OmlLexem(6,11,333,"==",0),
    new OmlLexem(7,3,317,"if",1),
    new OmlLexem(7,5,40,"(",0),
    new OmlLexem(7,6,433,"a",2),
    new OmlLexem(7,8,348,"mod",1),
    new OmlLexem(7,12,435,"5",0),
    new OmlLexem(7,14,299,"=",0),
    new OmlLexem(7,16,435,"0",0),
    new OmlLexem(7,17,41,")",0),
    new OmlLexem(7,19,410,"then",1),
    new OmlLexem(8,5,317,"if",1),
    new OmlLexem(8,7,40,"(",0),
    new OmlLexem(8,8,433,"b",2),
    new OmlLexem(8,10,338,"<=",0),
    new OmlLexem(8,13,435,"10",0),
    new OmlLexem(8,15,41,")",0),
    new OmlLexem(8,17,410,"then",1),
    new OmlLexem(9,7,437,"\"a5 && b<=5\"",0),
    new OmlLexem(10,5,296,"else",1),
    new OmlLexem(11,7,437,"\"a5\"",0),
    new OmlLexem(12,3,296,"else",1),
    new OmlLexem(13,5,437,"\"other\"",0),
    new OmlLexem(13,12,59,";",0),
    new OmlLexem(15,1,298,"end",1),
    new OmlLexem(15,5,433,"hoge",2)
  ]
)
