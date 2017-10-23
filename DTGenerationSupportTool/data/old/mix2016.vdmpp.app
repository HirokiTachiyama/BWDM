new OmlDocument("../data/mix.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "MixSpecification",
      [
        
      ],
      nil,
      [
        new OmlFunctionDefinitions([
          new OmlFunctionDefinition(
            new OmlAccessDefinition(
              false,
              false,
              new OmlScope(3,5,3),
              0,
              0
            ),
            new OmlExplicitFunction(
              "mix",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlProductType(
                  new OmlNatType(5,8),
                  new OmlIntType(5,14),
                  5,
                  12
                ),
                new OmlSeq0Type(new OmlCharType(5,28),5,21),
                5,
                12
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("arg1",6,10),
                  new OmlPatternIdentifier("arg2",6,17)
                ],6,8)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlBracketedExpression(new OmlBinaryExpression(
                    new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "arg1",
                        7,
                        11
                      ),
                      new OmlBinaryOperator(4,7,16),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(2,7,20),7,20),
                      7,
                      16
                    ),
                    new OmlBinaryOperator(27,7,22),
                    new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,7,24),7,24),
                    7,
                    22
                  ),7,9),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,8,13),8,13),
                      new OmlBinaryOperator(31,8,15),
                      new OmlName(
                        nil,
                        "arg2",
                        8,
                        18
                      ),
                      8,
                      15
                    ),8,12),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral(" arg1:even arg2:positive",9,11),9,11),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral(" arg1:even arg2:negative",11,11),11,11),
                    8,
                    9
                  ),
                  [
                    
                  ],
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "arg2",
                        13,
                        13
                      ),
                      new OmlBinaryOperator(2,13,18),
                      new OmlSymbolicLiteralExpression(new OmlNumericLiteral(0,13,20),13,20),
                      13,
                      18
                    ),13,11),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral(" arg1:odd arg2:negative",14,11),14,11),
                    [
                      
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral(" arg1:odd arg2:positive",16,11),16,11),
                    13,
                    9
                  ),
                  7,
                  7
                ),
                false,
                false,
                7,
                7
              ),
              new OmlFunctionTrailer(
                nil,
                nil,
                0,
                0
              ),
              5,
              3
            ),
            5,
            3
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
    new OmlLexem(1,7,433,"MixSpecification",2),
    new OmlLexem(3,1,312,"functions",1),
    new OmlLexem(5,3,433,"mix",2),
    new OmlLexem(5,6,58,":",0),
    new OmlLexem(5,8,352,"nat",1),
    new OmlLexem(5,12,42,"*",0),
    new OmlLexem(5,14,324,"int",1),
    new OmlLexem(5,18,264,"->",0),
    new OmlLexem(5,21,390,"seq",1),
    new OmlLexem(5,25,360,"of",1),
    new OmlLexem(5,28,274,"char",1),
    new OmlLexem(6,5,433,"mix",2),
    new OmlLexem(6,8,40,"(",0),
    new OmlLexem(6,10,433,"arg1",2),
    new OmlLexem(6,15,44,",",0),
    new OmlLexem(6,17,433,"arg2",2),
    new OmlLexem(6,22,41,")",0),
    new OmlLexem(6,24,333,"==",0),
    new OmlLexem(7,7,317,"if",1),
    new OmlLexem(7,9,40,"(",0),
    new OmlLexem(7,11,433,"arg1",2),
    new OmlLexem(7,16,348,"mod",1),
    new OmlLexem(7,20,435,"2",0),
    new OmlLexem(7,22,299,"=",0),
    new OmlLexem(7,24,435,"0",0),
    new OmlLexem(7,25,41,")",0),
    new OmlLexem(7,27,410,"then",1),
    new OmlLexem(8,9,317,"if",1),
    new OmlLexem(8,12,40,"(",0),
    new OmlLexem(8,13,435,"0",0),
    new OmlLexem(8,15,338,"<=",0),
    new OmlLexem(8,18,433,"arg2",2),
    new OmlLexem(8,23,41,")",0),
    new OmlLexem(8,25,410,"then",1),
    new OmlLexem(9,11,437,"\" arg1:even arg2:positive\"",0),
    new OmlLexem(10,9,296,"else",1),
    new OmlLexem(11,11,437,"\" arg1:even arg2:negative\"",0),
    new OmlLexem(12,7,296,"else",1),
    new OmlLexem(13,9,317,"if",1),
    new OmlLexem(13,11,40,"(",0),
    new OmlLexem(13,13,433,"arg2",2),
    new OmlLexem(13,18,337,"<",0),
    new OmlLexem(13,20,435,"0",0),
    new OmlLexem(13,21,41,")",0),
    new OmlLexem(13,23,410,"then",1),
    new OmlLexem(14,11,437,"\" arg1:odd arg2:negative\"",0),
    new OmlLexem(15,9,296,"else",1),
    new OmlLexem(16,11,437,"\" arg1:odd arg2:positive\"",0),
    new OmlLexem(16,36,59,";",0),
    new OmlLexem(18,1,298,"end",1),
    new OmlLexem(18,5,433,"MixSpecification",2)
  ]
)
