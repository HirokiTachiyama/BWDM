new OmlDocument("../data/mod2.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "Mod",
      [
        
      ],
      nil,
      [
        new OmlFunctionDefinitions([
          new OmlFunctionDefinition(
            new OmlAccessDefinition(
              false,
              true,
              new OmlScope(2,5,8),
              5,
              1
            ),
            new OmlExplicitFunction(
              "mod関数",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlIntType(5,23),
                new OmlSeq0Type(new OmlCharType(5,37),5,30),
                5,
                23
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("a",6,9)
                ],6,8)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBracketedExpression(new OmlBinaryExpression(
                    new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "a",
                        7,
                        9
                      ),
                      new OmlBinaryOperator(4,7,11),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(3,7,15),7,15),
                      7,
                      11
                    ),
                    new OmlBinaryOperator(27,7,17),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,7,19),7,19),
                    7,
                    17
                  ),7,8),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "a",
                          8,
                          11
                        ),
                        new OmlBinaryOperator(4,8,13),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(5,8,17),8,17),
                        8,
                        13
                      ),
                      new OmlBinaryOperator(27,8,19),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,8,21),8,21),
                      8,
                      19
                    ),8,10),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("FizzBuzz",9,9),9,9),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("Fizz",11,9),11,9),
                    8,
                    7
                  ),
                  [
                    
                  ],
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "a",
                          13,
                          10
                        ),
                        new OmlBinaryOperator(4,13,12),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(5,13,16),13,16),
                        13,
                        12
                      ),
                      new OmlBinaryOperator(27,13,18),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,13,20),13,20),
                      13,
                      18
                    ),13,9),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("Buzz",14,9),14,9),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("数字",16,9),16,9),
                    13,
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
              15
            ),
            5,
            15
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
    new OmlLexem(1,7,433,"Mod",2),
    new OmlLexem(3,1,312,"functions",1),
    new OmlLexem(5,1,403,"static",1),
    new OmlLexem(5,8,375,"public",1),
    new OmlLexem(5,15,433,"mod関数",2),
    new OmlLexem(5,21,58,":",0),
    new OmlLexem(5,23,324,"int",1),
    new OmlLexem(5,27,264,"->",0),
    new OmlLexem(5,30,390,"seq",1),
    new OmlLexem(5,34,360,"of",1),
    new OmlLexem(5,37,274,"char",1),
    new OmlLexem(6,3,433,"mod関数",2),
    new OmlLexem(6,8,40,"(",0),
    new OmlLexem(6,9,433,"a",2),
    new OmlLexem(6,10,41,")",0),
    new OmlLexem(6,12,333,"==",0),
    new OmlLexem(7,5,317,"if",1),
    new OmlLexem(7,8,40,"(",0),
    new OmlLexem(7,9,433,"a",2),
    new OmlLexem(7,11,348,"mod",1),
    new OmlLexem(7,15,435,"3",0),
    new OmlLexem(7,17,299,"=",0),
    new OmlLexem(7,19,435,"0",0),
    new OmlLexem(7,20,41,")",0),
    new OmlLexem(7,22,410,"then",1),
    new OmlLexem(8,7,317,"if",1),
    new OmlLexem(8,10,40,"(",0),
    new OmlLexem(8,11,433,"a",2),
    new OmlLexem(8,13,348,"mod",1),
    new OmlLexem(8,17,435,"5",0),
    new OmlLexem(8,19,299,"=",0),
    new OmlLexem(8,21,435,"0",0),
    new OmlLexem(8,22,41,")",0),
    new OmlLexem(8,24,410,"then",1),
    new OmlLexem(9,9,437,"\"FizzBuzz\"",0),
    new OmlLexem(10,7,296,"else",1),
    new OmlLexem(11,9,437,"\"Fizz\"",0),
    new OmlLexem(12,5,296,"else",1),
    new OmlLexem(13,7,317,"if",1),
    new OmlLexem(13,9,40,"(",0),
    new OmlLexem(13,10,433,"a",2),
    new OmlLexem(13,12,348,"mod",1),
    new OmlLexem(13,16,435,"5",0),
    new OmlLexem(13,18,299,"=",0),
    new OmlLexem(13,20,435,"0",0),
    new OmlLexem(13,21,41,")",0),
    new OmlLexem(13,23,410,"then",1),
    new OmlLexem(14,9,437,"\"Buzz\"",0),
    new OmlLexem(15,7,296,"else",1),
    new OmlLexem(16,9,437,"\"数字\"",0),
    new OmlLexem(16,13,59,";",0),
    new OmlLexem(17,1,298,"end",1),
    new OmlLexem(17,5,433,"Mod",2)
  ]
)
