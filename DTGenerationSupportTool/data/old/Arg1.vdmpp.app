new OmlDocument("../data/Arg1.vdmpp",
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
                new OmlIntType(5,18),
                new OmlSeq0Type(new OmlCharType(5,32),5,25),
                5,
                18
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("a",6,20)
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
                    new OmlBinaryOperator(2,7,13),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(5,7,15),7,15),
                    7,
                    13
                  ),7,10),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "a",
                        8,
                        17
                      ),
                      new OmlBinaryOperator(23,8,19),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,8,21),8,21),
                      8,
                      19
                    ),8,15),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("0 < a < 5",9,17),9,17),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a < 0",11,17),11,17),
                    8,
                    13
                  ),
                  [
                    new OmlElseIfExpression(
                      new OmlBracketedExpression(new OmlBinaryExpression(
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(12,12,14),12,14),
                        new OmlBinaryOperator(31,12,17),
                        new OmlName(
                          nil,
                          "a",
                          12,
                          20
                        ),
                        12,
                        17
                      ),12,13),
                      new OmlIfExpression(
                        new OmlBracketedExpression(new OmlBinaryExpression(
                          new OmlName(
                            nil,
                            "a",
                            13,
                            16
                          ),
                          new OmlBinaryOperator(2,13,18),
                          new OmlSymbolicLiteralExpression(new OmlNumericLiteral(20,13,20),13,20),
                          13,
                          18
                        ),13,14),
                        new OmlSymbolicLiteralExpression(new OmlTextLiteral("12 <= a < 20",14,10),14,10),
                        [
                          
                        ],
                        new OmlSymbolicLiteralExpression(new OmlTextLiteral("20 <= a",16,15),16,15),
                        13,
                        12
                      ),
                      12,
                      7
                    )
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("others",18,13),18,13),
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
    new OmlLexem(5,22,264,"->",0),
    new OmlLexem(5,25,390,"seq",1),
    new OmlLexem(5,29,360,"of",1),
    new OmlLexem(5,32,274,"char",1),
    new OmlLexem(6,5,433,"SampleFunction",2),
    new OmlLexem(6,19,40,"(",0),
    new OmlLexem(6,20,433,"a",2),
    new OmlLexem(6,21,41,")",0),
    new OmlLexem(6,23,333,"==",0),
    new OmlLexem(7,8,317,"if",1),
    new OmlLexem(7,10,40,"(",0),
    new OmlLexem(7,11,433,"a",2),
    new OmlLexem(7,13,337,"<",0),
    new OmlLexem(7,15,435,"5",0),
    new OmlLexem(7,16,41,")",0),
    new OmlLexem(7,18,410,"then",1),
    new OmlLexem(8,13,317,"if",1),
    new OmlLexem(8,15,40,"(",0),
    new OmlLexem(8,17,433,"a",2),
    new OmlLexem(8,19,313,">",0),
    new OmlLexem(8,21,435,"0",0),
    new OmlLexem(8,23,41,")",0),
    new OmlLexem(8,25,410,"then",1),
    new OmlLexem(9,17,437,"\"0 < a < 5\"",0),
    new OmlLexem(10,13,296,"else",1),
    new OmlLexem(11,17,437,"\"a < 0\"",0),
    new OmlLexem(12,7,297,"elseif",1),
    new OmlLexem(12,13,40,"(",0),
    new OmlLexem(12,14,435,"12",0),
    new OmlLexem(12,17,338,"<=",0),
    new OmlLexem(12,20,433,"a",2),
    new OmlLexem(12,21,41,")",0),
    new OmlLexem(12,23,410,"then",1),
    new OmlLexem(13,12,317,"if",1),
    new OmlLexem(13,14,40,"(",0),
    new OmlLexem(13,16,433,"a",2),
    new OmlLexem(13,18,337,"<",0),
    new OmlLexem(13,20,435,"20",0),
    new OmlLexem(13,23,41,")",0),
    new OmlLexem(13,25,410,"then",1),
    new OmlLexem(14,10,437,"\"12 <= a < 20\"",0),
    new OmlLexem(15,12,296,"else",1),
    new OmlLexem(16,15,437,"\"20 <= a\"",0),
    new OmlLexem(17,7,296,"else",1),
    new OmlLexem(18,13,437,"\"others\"",0),
    new OmlLexem(18,21,59,";",0),
    new OmlLexem(20,1,298,"end",1),
    new OmlLexem(20,5,433,"Sample",2)
  ]
)
