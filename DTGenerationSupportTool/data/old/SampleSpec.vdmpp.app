new OmlDocument("../data/SampleSpec.vdmpp",
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
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは5未満です",8,10),8,10),
                  [
                    
                  ],
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(12,9,16),9,16),
                      new OmlBinaryOperator(31,9,19),
                      new OmlName(
                        nil,
                        "a",
                        9,
                        22
                      ),
                      9,
                      19
                    ),9,15),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは12以上です",10,10),10,10),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("aは5以上かつ12未満です",12,10),12,10),
                    9,
                    13
                  ),
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
    new OmlLexem(6,22,333,"==",0),
    new OmlLexem(7,8,317,"if",1),
    new OmlLexem(7,10,40,"(",0),
    new OmlLexem(7,11,433,"a",2),
    new OmlLexem(7,13,337,"<",0),
    new OmlLexem(7,15,435,"5",0),
    new OmlLexem(7,16,41,")",0),
    new OmlLexem(7,18,410,"then",1),
    new OmlLexem(8,10,437,"\"aは5未満です\"",0),
    new OmlLexem(9,8,296,"else",1),
    new OmlLexem(9,13,317,"if",1),
    new OmlLexem(9,15,40,"(",0),
    new OmlLexem(9,16,435,"12",0),
    new OmlLexem(9,19,338,"<=",0),
    new OmlLexem(9,22,433,"a",2),
    new OmlLexem(9,23,41,")",0),
    new OmlLexem(9,25,410,"then",1),
    new OmlLexem(10,10,437,"\"aは12以上です\"",0),
    new OmlLexem(11,8,296,"else",1),
    new OmlLexem(12,10,437,"\"aは5以上かつ12未満です\"",0),
    new OmlLexem(12,25,59,";",0),
    new OmlLexem(13,1,298,"end",1),
    new OmlLexem(13,5,433,"Sample",2)
  ]
)
