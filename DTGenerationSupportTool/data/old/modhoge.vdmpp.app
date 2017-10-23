new OmlDocument("../data/modhoge.vdmpp",
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
              new OmlScope(2,4,8),
              4,
              1
            ),
            new OmlExplicitFunction(
              "mod関数",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlNatType(4,23),
                new OmlSeq0Type(new OmlCharType(4,37),4,30),
                4,
                23
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("input",5,9)
                ],5,8)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBracketedExpression(new OmlBinaryExpression(
                    new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "input",
                        6,
                        9
                      ),
                      new OmlBinaryOperator(4,6,15),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(3,6,19),6,19),
                      6,
                      15
                    ),
                    new OmlBinaryOperator(27,6,21),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,6,23),6,23),
                    6,
                    21
                  ),6,8),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "input",
                          7,
                          10
                        ),
                        new OmlBinaryOperator(4,7,16),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(5,7,20),7,20),
                        7,
                        16
                      ),
                      new OmlBinaryOperator(27,7,22),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,7,24),7,24),
                      7,
                      22
                    ),7,9),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("FizzBuzz",8,9),8,9),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("3",10,9),10,9),
                    7,
                    7
                  ),
                  [
                    new OmlElseIfExpression(
                      new OmlBracketedExpression(new OmlBinaryExpression(
                        new OmlBinaryExpression(
                          new OmlName(
                            nil,
                            "input",
                            11,
                            12
                          ),
                          new OmlBinaryOperator(4,11,18),
                          new OmlSymbolicLiteralExpression(new OmlNumericLiteral(5,11,22),11,22),
                          11,
                          18
                        ),
                        new OmlBinaryOperator(27,11,24),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,11,26),11,26),
                        11,
                        24
                      ),11,11),
                      new OmlSymbolicLiteralExpression(new OmlTextLiteral("5",12,7),12,7),
                      11,
                      5
                    )
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("other",14,7),14,7),
                  6,
                  5
                ),
                false,
                false,
                6,
                5
              ),
              new OmlFunctionTrailer(
                nil,
                nil,
                0,
                0
              ),
              4,
              15
            ),
            4,
            15
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
    new OmlLexem(1,7,433,"Mod",2),
    new OmlLexem(2,1,312,"functions",1),
    new OmlLexem(4,1,403,"static",1),
    new OmlLexem(4,8,375,"public",1),
    new OmlLexem(4,15,433,"mod関数",2),
    new OmlLexem(4,21,58,":",0),
    new OmlLexem(4,23,352,"nat",1),
    new OmlLexem(4,27,264,"->",0),
    new OmlLexem(4,30,390,"seq",1),
    new OmlLexem(4,34,360,"of",1),
    new OmlLexem(4,37,274,"char",1),
    new OmlLexem(5,3,433,"mod関数",2),
    new OmlLexem(5,8,40,"(",0),
    new OmlLexem(5,9,433,"input",2),
    new OmlLexem(5,14,41,")",0),
    new OmlLexem(5,16,333,"==",0),
    new OmlLexem(6,5,317,"if",1),
    new OmlLexem(6,8,40,"(",0),
    new OmlLexem(6,9,433,"input",2),
    new OmlLexem(6,15,348,"mod",1),
    new OmlLexem(6,19,435,"3",0),
    new OmlLexem(6,21,299,"=",0),
    new OmlLexem(6,23,435,"0",0),
    new OmlLexem(6,24,41,")",0),
    new OmlLexem(6,26,410,"then",1),
    new OmlLexem(7,7,317,"if",1),
    new OmlLexem(7,9,40,"(",0),
    new OmlLexem(7,10,433,"input",2),
    new OmlLexem(7,16,348,"mod",1),
    new OmlLexem(7,20,435,"5",0),
    new OmlLexem(7,22,299,"=",0),
    new OmlLexem(7,24,435,"0",0),
    new OmlLexem(7,25,41,")",0),
    new OmlLexem(7,27,410,"then",1),
    new OmlLexem(8,9,437,"\"FizzBuzz\"",0),
    new OmlLexem(9,7,296,"else",1),
    new OmlLexem(10,9,437,"\"3\"",0),
    new OmlLexem(11,5,297,"elseif",1),
    new OmlLexem(11,11,40,"(",0),
    new OmlLexem(11,12,433,"input",2),
    new OmlLexem(11,18,348,"mod",1),
    new OmlLexem(11,22,435,"5",0),
    new OmlLexem(11,24,299,"=",0),
    new OmlLexem(11,26,435,"0",0),
    new OmlLexem(11,27,41,")",0),
    new OmlLexem(11,29,410,"then",1),
    new OmlLexem(12,7,437,"\"5\"",0),
    new OmlLexem(13,5,296,"else",1),
    new OmlLexem(14,7,437,"\"other\"",0),
    new OmlLexem(14,14,59,";",0),
    new OmlLexem(16,1,298,"end",1),
    new OmlLexem(16,5,433,"Mod",2)
  ]
)
