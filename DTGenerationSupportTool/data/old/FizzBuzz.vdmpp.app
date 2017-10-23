new OmlDocument("../data/FizzBuzz.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "FizzBuzz",
      [
        
      ],
      nil,
      [
        new OmlFunctionDefinitions([
          new OmlFunctionDefinition(
            new OmlAccessDefinition(
              false,
              true,
              new OmlScope(2,3,3),
              3,
              3
            ),
            new OmlExplicitFunction(
              "FizzBuzz関数",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlNatType(3,30),
                new OmlSeq0Type(new OmlCharType(3,44),3,37),
                3,
                30
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("入力",4,14)
                ],4,13)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBinaryExpression(
                    new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "入力",
                        5,
                        8
                      ),
                      new OmlBinaryOperator(4,5,11),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(3,5,15),5,15),
                      5,
                      11
                    ),
                    new OmlBinaryOperator(27,5,17),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,5,19),5,19),
                    5,
                    17
                  ),
                  new OmlIfExpression(
                    new OmlBinaryExpression(
                      new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "入力",
                          6,
                          10
                        ),
                        new OmlBinaryOperator(4,6,13),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(5,6,17),6,17),
                        6,
                        13
                      ),
                      new OmlBinaryOperator(27,6,19),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,6,21),6,21),
                      6,
                      19
                    ),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("FizzBuzz!",7,9),7,9),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("Fizz",9,9),9,9),
                    6,
                    7
                  ),
                  [
                    new OmlElseIfExpression(
                      new OmlBinaryExpression(
                        new OmlBinaryExpression(
                          new OmlName(
                            nil,
                            "入力",
                            10,
                            12
                          ),
                          new OmlBinaryOperator(4,10,15),
                          new OmlSymbolicLiteralExpression(new OmlNumericLiteral(5,10,19),10,19),
                          10,
                          15
                        ),
                        new OmlBinaryOperator(27,10,21),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,10,23),10,23),
                        10,
                        21
                      ),
                      new OmlSymbolicLiteralExpression(new OmlTextLiteral("Buzz",11,7),11,7),
                      10,
                      5
                    )
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("数字",13,7),13,7),
                  5,
                  5
                ),
                false,
                false,
                5,
                5
              ),
              new OmlFunctionTrailer(
                nil,
                nil,
                0,
                0
              ),
              3,
              17
            ),
            3,
            17
          )
        ],2,1)
      ],
      false,
      1,
      1
    )
  ],0,0),
  [
    new OmlLexem(1,1,275,"class",1),
    new OmlLexem(1,7,433,"FizzBuzz",2),
    new OmlLexem(2,1,312,"functions",1),
    new OmlLexem(3,3,375,"public",1),
    new OmlLexem(3,10,403,"static",1),
    new OmlLexem(3,17,433,"FizzBuzz関数",2),
    new OmlLexem(3,28,58,":",0),
    new OmlLexem(3,30,352,"nat",1),
    new OmlLexem(3,34,264,"->",0),
    new OmlLexem(3,37,390,"seq",1),
    new OmlLexem(3,41,360,"of",1),
    new OmlLexem(3,44,274,"char",1),
    new OmlLexem(4,3,433,"FizzBuzz関数",2),
    new OmlLexem(4,13,40,"(",0),
    new OmlLexem(4,14,433,"入力",2),
    new OmlLexem(4,16,41,")",0),
    new OmlLexem(4,18,333,"==",0),
    new OmlLexem(5,5,317,"if",1),
    new OmlLexem(5,8,433,"入力",2),
    new OmlLexem(5,11,348,"mod",1),
    new OmlLexem(5,15,435,"3",0),
    new OmlLexem(5,17,299,"=",0),
    new OmlLexem(5,19,435,"0",0),
    new OmlLexem(5,22,410,"then",1),
    new OmlLexem(6,7,317,"if",1),
    new OmlLexem(6,10,433,"入力",2),
    new OmlLexem(6,13,348,"mod",1),
    new OmlLexem(6,17,435,"5",0),
    new OmlLexem(6,19,299,"=",0),
    new OmlLexem(6,21,435,"0",0),
    new OmlLexem(6,23,410,"then",1),
    new OmlLexem(7,9,437,"\"FizzBuzz!\"",0),
    new OmlLexem(8,7,296,"else",1),
    new OmlLexem(9,9,437,"\"Fizz\"",0),
    new OmlLexem(10,5,297,"elseif",1),
    new OmlLexem(10,12,433,"入力",2),
    new OmlLexem(10,15,348,"mod",1),
    new OmlLexem(10,19,435,"5",0),
    new OmlLexem(10,21,299,"=",0),
    new OmlLexem(10,23,435,"0",0),
    new OmlLexem(10,25,410,"then",1),
    new OmlLexem(11,7,437,"\"Buzz\"",0),
    new OmlLexem(12,5,296,"else",1),
    new OmlLexem(13,7,437,"\"数字\"",0),
    new OmlLexem(15,1,298,"end",1),
    new OmlLexem(15,5,433,"FizzBuzz",2)
  ]
)
