new OmlDocument("../data/mod.vdmpp",
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
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("サァんwww",7,9),7,9),
                  [
                    new OmlElseIfExpression(
                      new OmlBracketedExpression(new OmlBinaryExpression(
                        new OmlBinaryExpression(
                          new OmlName(
                            nil,
                            "input",
                            9,
                            13
                          ),
                          new OmlBinaryOperator(4,9,19),
                          new OmlSymbolicLiteralExpression(new OmlNumericLiteral(7,9,23),9,23),
                          9,
                          19
                        ),
                        new OmlBinaryOperator(27,9,25),
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,9,27),9,27),
                        9,
                        25
                      ),9,12),
                      new OmlSymbolicLiteralExpression(new OmlTextLiteral("7(紳士)",10,9),10,9),
                      9,
                      5
                    )
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("数字(真顔)",13,7),13,7),
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
    new OmlLexem(7,9,437,"\"サァんwww\"",0),
    new OmlLexem(9,5,297,"elseif",1),
    new OmlLexem(9,12,40,"(",0),
    new OmlLexem(9,13,433,"input",2),
    new OmlLexem(9,19,348,"mod",1),
    new OmlLexem(9,23,435,"7",0),
    new OmlLexem(9,25,299,"=",0),
    new OmlLexem(9,27,435,"0",0),
    new OmlLexem(9,28,41,")",0),
    new OmlLexem(9,30,410,"then",1),
    new OmlLexem(10,9,437,"\"7(紳士)\"",0),
    new OmlLexem(12,5,296,"else",1),
    new OmlLexem(13,7,437,"\"数字(真顔)\"",0),
    new OmlLexem(13,15,59,";",0),
    new OmlLexem(15,1,298,"end",1),
    new OmlLexem(15,5,433,"Mod",2)
  ]
)
