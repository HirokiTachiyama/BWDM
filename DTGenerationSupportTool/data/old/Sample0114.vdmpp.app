new OmlDocument("../data/Sample0114.vdmpp",
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
              "関数２",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlProductType(
                  new OmlIntType(5,7),
                  new OmlNatType(5,11),
                  5,
                  10
                ),
                new OmlSeq0Type(new OmlCharType(5,25),5,18),
                5,
                10
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("a",6,9),
                  new OmlPatternIdentifier("b",6,12)
                ],6,8)
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
                    new OmlBinaryOperator(31,7,13),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(10,7,16),7,16),
                    7,
                    13
                  ),7,10),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(4,8,13),8,13),
                      new OmlBinaryOperator(31,8,15),
                      new OmlName(
                        nil,
                        "b",
                        8,
                        18
                      ),
                      8,
                      15
                    ),8,12),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a<=10 & 4<=b",9,11),9,11),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("a<=10 & 4>b.",11,5),11,5),
                    8,
                    10
                  ),
                  [
                    
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("a>10",13,12),13,12),
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
    new OmlLexem(5,1,433,"関数２",2),
    new OmlLexem(5,5,58,":",0),
    new OmlLexem(5,7,324,"int",1),
    new OmlLexem(5,10,42,"*",0),
    new OmlLexem(5,11,352,"nat",1),
    new OmlLexem(5,15,264,"->",0),
    new OmlLexem(5,18,390,"seq",1),
    new OmlLexem(5,22,360,"of",1),
    new OmlLexem(5,25,274,"char",1),
    new OmlLexem(6,5,433,"関数２",2),
    new OmlLexem(6,8,40,"(",0),
    new OmlLexem(6,9,433,"a",2),
    new OmlLexem(6,10,44,",",0),
    new OmlLexem(6,12,433,"b",2),
    new OmlLexem(6,13,41,")",0),
    new OmlLexem(6,15,333,"==",0),
    new OmlLexem(7,8,317,"if",1),
    new OmlLexem(7,10,40,"(",0),
    new OmlLexem(7,11,433,"a",2),
    new OmlLexem(7,13,338,"<=",0),
    new OmlLexem(7,16,435,"10",0),
    new OmlLexem(7,18,41,")",0),
    new OmlLexem(7,20,410,"then",1),
    new OmlLexem(8,10,317,"if",1),
    new OmlLexem(8,12,40,"(",0),
    new OmlLexem(8,13,435,"4",0),
    new OmlLexem(8,15,338,"<=",0),
    new OmlLexem(8,18,433,"b",2),
    new OmlLexem(8,19,41,")",0),
    new OmlLexem(8,21,410,"then",1),
    new OmlLexem(9,11,437,"\"a<=10 & 4<=b\"",0),
    new OmlLexem(10,7,296,"else",1),
    new OmlLexem(11,5,437,"\"a<=10 & 4>b.\"",0),
    new OmlLexem(12,7,296,"else",1),
    new OmlLexem(13,12,437,"\"a>10\"",0),
    new OmlLexem(13,18,59,";",0),
    new OmlLexem(14,1,298,"end",1),
    new OmlLexem(14,5,433,"Sample",2),
    new OmlLexem(16,1,276,"-- 関数１ : int -> seq of char",3),
    new OmlLexem(17,1,276,"--  関数１(a) ==",3),
    new OmlLexem(18,1,276,"--    if(a <= 5) then",3),
    new OmlLexem(19,1,276,"--      \"a = ... 3, 4, 5\"",3),
    new OmlLexem(20,1,276,"--    else",3),
    new OmlLexem(21,1,276,"--      \"a = 6, 7, 8, ...\";",3),
    new OmlLexem(24,1,276,"--int : a",3),
    new OmlLexem(25,1,276,"--nat : b",3),
    new OmlLexem(26,1,276,"--intMax   : aMax   : 10",3),
    new OmlLexem(27,1,276,"--intMax+1 : aMax+1 : 11",3),
    new OmlLexem(28,1,276,"--natMin   : bMin   : 4",3),
    new OmlLexem(29,1,276,"--natMin-1 : bMin-1 : 3",3),
    new OmlLexem(31,1,276,"--(intMin,4)(intMin,3)(intMin,natMax)(intMin,natMax+1)",3),
    new OmlLexem(32,1,276,"--(intMin-1,4)(intMin-1,3)(intMin-1,natMax)(intMin-1,natMax+1)",3),
    new OmlLexem(33,1,276,"--(10,4)(10,3)(10,natMax)(10,natMax+1)",3),
    new OmlLexem(34,1,276,"--(11,4)(11,3)(11,natMax)(11,natMax+1)",3)
  ]
)
