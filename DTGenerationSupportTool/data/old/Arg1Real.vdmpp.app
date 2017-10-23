new OmlDocument("../data/Arg1Real.vdmpp",
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
                new OmlRealType(5,18),
                new OmlSeq0Type(new OmlCharType(5,33),5,26),
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
                    new OmlSymbolicLiteralExpression(new OmlRealLiteral(5.3,7,15),7,15),
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
                      new OmlSymbolicLiteralExpression(new OmlRealLiteral(0.2,8,21),8,21),
                      8,
                      19
                    ),8,15),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("0.2 < a < 5.3",9,17),9,17),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a < 0.2",11,17),11,17),
                    8,
                    13
                  ),
                  [
                    new OmlElseIfExpression(
                      new OmlBracketedExpression(new OmlBinaryExpression(
                        new OmlSymbolicLiteralExpression(new OmlRealLiteral(12.5,12,14),12,14),
                        new OmlBinaryOperator(31,12,19),
                        new OmlName(
                          nil,
                          "a",
                          12,
                          22
                        ),
                        12,
                        19
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
                          new OmlSymbolicLiteralExpression(new OmlRealLiteral(20.9,13,20),13,20),
                          13,
                          18
                        ),13,14),
                        new OmlSymbolicLiteralExpression(new OmlTextLiteral("12.5 <= a < 20.9",14,10),14,10),
                        [
                          
                        ],
                        new OmlSymbolicLiteralExpression(new OmlTextLiteral("20.9 <= a",16,15),16,15),
                        13,
                        12
                      ),
                      12,
                      7
                    )
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("5.3<=a<12.5",18,13),18,13),
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
    new OmlLexem(5,18,380,"real",1),
    new OmlLexem(5,23,264,"->",0),
    new OmlLexem(5,26,390,"seq",1),
    new OmlLexem(5,30,360,"of",1),
    new OmlLexem(5,33,274,"char",1),
    new OmlLexem(6,5,433,"SampleFunction",2),
    new OmlLexem(6,19,40,"(",0),
    new OmlLexem(6,20,433,"a",2),
    new OmlLexem(6,21,41,")",0),
    new OmlLexem(6,23,333,"==",0),
    new OmlLexem(7,8,317,"if",1),
    new OmlLexem(7,10,40,"(",0),
    new OmlLexem(7,11,433,"a",2),
    new OmlLexem(7,13,337,"<",0),
    new OmlLexem(7,15,436,"5.3",0),
    new OmlLexem(7,18,41,")",0),
    new OmlLexem(7,20,410,"then",1),
    new OmlLexem(8,13,317,"if",1),
    new OmlLexem(8,15,40,"(",0),
    new OmlLexem(8,17,433,"a",2),
    new OmlLexem(8,19,313,">",0),
    new OmlLexem(8,21,436,"0.2",0),
    new OmlLexem(8,25,41,")",0),
    new OmlLexem(8,27,410,"then",1),
    new OmlLexem(9,17,437,"\"0.2 < a < 5.3\"",0),
    new OmlLexem(10,13,296,"else",1),
    new OmlLexem(11,17,437,"\"a < 0.2\"",0),
    new OmlLexem(12,7,297,"elseif",1),
    new OmlLexem(12,13,40,"(",0),
    new OmlLexem(12,14,436,"12.5",0),
    new OmlLexem(12,19,338,"<=",0),
    new OmlLexem(12,22,433,"a",2),
    new OmlLexem(12,23,41,")",0),
    new OmlLexem(12,25,410,"then",1),
    new OmlLexem(13,12,317,"if",1),
    new OmlLexem(13,14,40,"(",0),
    new OmlLexem(13,16,433,"a",2),
    new OmlLexem(13,18,337,"<",0),
    new OmlLexem(13,20,436,"20.9",0),
    new OmlLexem(13,25,41,")",0),
    new OmlLexem(13,27,410,"then",1),
    new OmlLexem(14,10,437,"\"12.5 <= a < 20.9\"",0),
    new OmlLexem(15,12,296,"else",1),
    new OmlLexem(16,15,437,"\"20.9 <= a\"",0),
    new OmlLexem(17,7,296,"else",1),
    new OmlLexem(18,13,437,"\"5.3<=a<12.5\"",0),
    new OmlLexem(18,26,59,";",0),
    new OmlLexem(20,1,298,"end",1),
    new OmlLexem(20,5,433,"Sample",2)
  ]
)
