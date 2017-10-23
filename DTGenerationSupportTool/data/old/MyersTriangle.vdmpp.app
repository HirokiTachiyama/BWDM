new OmlDocument("../data/MyersTriangle.vdmpp",
  new OmlSpecifications([
    new OmlClass(
      "MyersTriangle",
      [
        
      ],
      nil,
      [
        new OmlFunctionDefinitions([
          new OmlFunctionDefinition(
            new OmlAccessDefinition(
              false,
              false,
              new OmlScope(2,3,1),
              3,
              1
            ),
            new OmlExplicitFunction(
              "三角形",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlProductType(
                  new OmlProductType(
                    new OmlIntType(3,14),
                    new OmlIntType(3,20),
                    3,
                    18
                  ),
                  new OmlIntType(3,26),
                  3,
                  24
                ),
                new OmlBoolType(3,33),
                3,
                24
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("A",4,9),
                  new OmlPatternIdentifier("B",4,11),
                  new OmlPatternIdentifier("C",4,13)
                ],4,8)
              ],
              new OmlFunctionBody(
                new OmlLetExpression(
                  [
                    new OmlValueShape(
                      new OmlPatternIdentifier("周",5,13),
                      nil,
                      new OmlBinaryExpression(
                        new OmlBinaryExpression(
                          new OmlName(
                            nil,
                            "A",
                            5,
                            17
                          ),
                          new OmlBinaryOperator(24,5,19),
                          new OmlName(
                            nil,
                            "B",
                            5,
                            21
                          ),
                          5,
                          19
                        ),
                        new OmlBinaryOperator(24,5,23),
                        new OmlName(
                          nil,
                          "C",
                          5,
                          25
                        ),
                        5,
                        23
                      ),
                      5,
                      13
                    )
                  ],
                  new OmlBinaryExpression(
                    new OmlBinaryExpression(
                      new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "周",
                          6,
                          13
                        ),
                        new OmlBinaryOperator(23,6,15),
                        new OmlBracketedExpression(new OmlBinaryExpression(
                          new OmlSymbolicLiteralExpression(new OmlNumericLiteral(2,6,18),6,18),
                          new OmlBinaryOperator(20,6,20),
                          new OmlName(
                            nil,
                            "A",
                            6,
                            22
                          ),
                          6,
                          20
                        ),6,17),
                        6,
                        15
                      ),
                      new OmlBinaryOperator(10,6,25),
                      new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "周",
                          6,
                          29
                        ),
                        new OmlBinaryOperator(23,6,31),
                        new OmlBracketedExpression(new OmlBinaryExpression(
                          new OmlSymbolicLiteralExpression(new OmlNumericLiteral(2,6,34),6,34),
                          new OmlBinaryOperator(20,6,36),
                          new OmlName(
                            nil,
                            "B",
                            6,
                            38
                          ),
                          6,
                          36
                        ),6,33),
                        6,
                        31
                      ),
                      6,
                      25
                    ),
                    new OmlBinaryOperator(10,6,41),
                    new OmlBinaryExpression(
                      new OmlName(
                        nil,
                        "周",
                        6,
                        45
                      ),
                      new OmlBinaryOperator(23,6,47),
                      new OmlBracketedExpression(new OmlBinaryExpression(
                        new OmlSymbolicLiteralExpression(new OmlNumericLiteral(2,6,50),6,50),
                        new OmlBinaryOperator(20,6,52),
                        new OmlName(
                          nil,
                          "C",
                          6,
                          54
                        ),
                        6,
                        52
                      ),6,49),
                      6,
                      47
                    ),
                    6,
                    41
                  ),
                  5,
                  9
                ),
                false,
                false,
                5,
                9
              ),
              new OmlFunctionTrailer(
                nil,
                nil,
                0,
                0
              ),
              3,
              8
            ),
            3,
            8
          ),
          new OmlFunctionDefinition(
            new OmlAccessDefinition(
              false,
              false,
              new OmlScope(2,8,1),
              8,
              1
            ),
            new OmlExplicitFunction(
              "形状判断",
              [
                
              ],
              new OmlPartialFunctionType(
                new OmlProductType(
                  new OmlProductType(
                    new OmlIntType(8,15),
                    new OmlIntType(8,21),
                    8,
                    19
                  ),
                  new OmlIntType(8,27),
                  8,
                  25
                ),
                new OmlSeq0Type(new OmlCharType(8,41),8,34),
                8,
                25
              ),
              [
                new OmlParameter([
                  new OmlPatternIdentifier("A",9,10),
                  new OmlPatternIdentifier("B",9,12),
                  new OmlPatternIdentifier("C",9,14)
                ],9,9)
              ],
              new OmlFunctionBody(
                new OmlIfExpression(
                  new OmlApplyExpression(
                    new OmlName(
                      nil,
                      "三角形",
                      10,
                      12
                    ),
                    [
                      new OmlName(
                        nil,
                        "A",
                        10,
                        16
                      ),
                      new OmlName(
                        nil,
                        "B",
                        10,
                        18
                      ),
                      new OmlName(
                        nil,
                        "C",
                        10,
                        20
                      )
                    ],
                    10,
                    15
                  ),
                  new OmlIfExpression(
                    new OmlBracketedExpression(new OmlBinaryExpression(
                      new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "A",
                          11,
                          17
                        ),
                        new OmlBinaryOperator(27,11,18),
                        new OmlName(
                          nil,
                          "B",
                          11,
                          19
                        ),
                        11,
                        18
                      ),
                      new OmlBinaryOperator(10,11,21),
                      new OmlBinaryExpression(
                        new OmlName(
                          nil,
                          "B",
                          11,
                          25
                        ),
                        new OmlBinaryOperator(27,11,26),
                        new OmlName(
                          nil,
                          "C",
                          11,
                          27
                        ),
                        11,
                        26
                      ),
                      11,
                      21
                    ),11,16),
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("正三角形かつ二等辺三角形",12,17),12,17),
                    [
                      new OmlElseIfExpression(
                        new OmlBracketedExpression(new OmlBinaryExpression(
                          new OmlBinaryExpression(
                            new OmlBinaryExpression(
                              new OmlName(
                                nil,
                                "A",
                                13,
                                21
                              ),
                              new OmlBinaryOperator(27,13,22),
                              new OmlName(
                                nil,
                                "B",
                                13,
                                23
                              ),
                              13,
                              22
                            ),
                            new OmlBinaryOperator(22,13,25),
                            new OmlBinaryExpression(
                              new OmlName(
                                nil,
                                "B",
                                13,
                                28
                              ),
                              new OmlBinaryOperator(27,13,29),
                              new OmlName(
                                nil,
                                "C",
                                13,
                                30
                              ),
                              13,
                              29
                            ),
                            13,
                            25
                          ),
                          new OmlBinaryOperator(22,13,32),
                          new OmlBinaryExpression(
                            new OmlName(
                              nil,
                              "C",
                              13,
                              35
                            ),
                            new OmlBinaryOperator(27,13,36),
                            new OmlName(
                              nil,
                              "A",
                              13,
                              37
                            ),
                            13,
                            36
                          ),
                          13,
                          32
                        ),13,20),
                        new OmlSymbolicLiteralExpression(new OmlTextLiteral("二等辺三角形",14,17),14,17),
                        13,
                        13
                      )
                    ],
                    new OmlSymbolicLiteralExpression(new OmlTextLiteral("不等辺三角形",16,17),16,17),
                    11,
                    13
                  ),
                  [
                    
                  ],
                  new OmlSymbolicLiteralExpression(new OmlTextLiteral("非三角形",18,13),18,13),
                  10,
                  9
                ),
                false,
                false,
                10,
                9
              ),
              new OmlFunctionTrailer(
                nil,
                nil,
                0,
                0
              ),
              8,
              8
            ),
            8,
            8
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
    new OmlLexem(1,7,433,"MyersTriangle",2),
    new OmlLexem(2,1,312,"functions",1),
    new OmlLexem(3,1,375,"public",1),
    new OmlLexem(3,8,433,"三角形",2),
    new OmlLexem(3,12,58,":",0),
    new OmlLexem(3,14,324,"int",1),
    new OmlLexem(3,18,42,"*",0),
    new OmlLexem(3,20,324,"int",1),
    new OmlLexem(3,24,42,"*",0),
    new OmlLexem(3,26,324,"int",1),
    new OmlLexem(3,30,264,"->",0),
    new OmlLexem(3,33,270,"bool",1),
    new OmlLexem(4,5,433,"三角形",2),
    new OmlLexem(4,8,40,"(",0),
    new OmlLexem(4,9,433,"A",2),
    new OmlLexem(4,10,44,",",0),
    new OmlLexem(4,11,433,"B",2),
    new OmlLexem(4,12,44,",",0),
    new OmlLexem(4,13,433,"C",2),
    new OmlLexem(4,14,41,")",0),
    new OmlLexem(4,15,333,"==",0),
    new OmlLexem(5,9,339,"let",1),
    new OmlLexem(5,13,433,"周",2),
    new OmlLexem(5,15,299,"=",0),
    new OmlLexem(5,17,433,"A",2),
    new OmlLexem(5,19,43,"+",0),
    new OmlLexem(5,21,433,"B",2),
    new OmlLexem(5,23,43,"+",0),
    new OmlLexem(5,25,433,"C",2),
    new OmlLexem(5,27,319,"in",1),
    new OmlLexem(6,13,433,"周",2),
    new OmlLexem(6,15,313,">",0),
    new OmlLexem(6,17,40,"(",0),
    new OmlLexem(6,18,435,"2",0),
    new OmlLexem(6,20,42,"*",0),
    new OmlLexem(6,22,433,"A",2),
    new OmlLexem(6,23,41,")",0),
    new OmlLexem(6,25,262,"and",1),
    new OmlLexem(6,29,433,"周",2),
    new OmlLexem(6,31,313,">",0),
    new OmlLexem(6,33,40,"(",0),
    new OmlLexem(6,34,435,"2",0),
    new OmlLexem(6,36,42,"*",0),
    new OmlLexem(6,38,433,"B",2),
    new OmlLexem(6,39,41,")",0),
    new OmlLexem(6,41,262,"and",1),
    new OmlLexem(6,45,433,"周",2),
    new OmlLexem(6,47,313,">",0),
    new OmlLexem(6,49,40,"(",0),
    new OmlLexem(6,50,435,"2",0),
    new OmlLexem(6,52,42,"*",0),
    new OmlLexem(6,54,433,"C",2),
    new OmlLexem(6,55,41,")",0),
    new OmlLexem(6,56,59,";",0),
    new OmlLexem(8,1,375,"public",1),
    new OmlLexem(8,8,433,"形状判断",2),
    new OmlLexem(8,13,58,":",0),
    new OmlLexem(8,15,324,"int",1),
    new OmlLexem(8,19,42,"*",0),
    new OmlLexem(8,21,324,"int",1),
    new OmlLexem(8,25,42,"*",0),
    new OmlLexem(8,27,324,"int",1),
    new OmlLexem(8,31,264,"->",0),
    new OmlLexem(8,34,390,"seq",1),
    new OmlLexem(8,38,360,"of",1),
    new OmlLexem(8,41,274,"char",1),
    new OmlLexem(9,5,433,"形状判断",2),
    new OmlLexem(9,9,40,"(",0),
    new OmlLexem(9,10,433,"A",2),
    new OmlLexem(9,11,44,",",0),
    new OmlLexem(9,12,433,"B",2),
    new OmlLexem(9,13,44,",",0),
    new OmlLexem(9,14,433,"C",2),
    new OmlLexem(9,15,41,")",0),
    new OmlLexem(9,16,333,"==",0),
    new OmlLexem(10,9,317,"if",1),
    new OmlLexem(10,12,433,"三角形",2),
    new OmlLexem(10,15,40,"(",0),
    new OmlLexem(10,16,433,"A",2),
    new OmlLexem(10,17,44,",",0),
    new OmlLexem(10,18,433,"B",2),
    new OmlLexem(10,19,44,",",0),
    new OmlLexem(10,20,433,"C",2),
    new OmlLexem(10,21,41,")",0),
    new OmlLexem(10,23,410,"then",1),
    new OmlLexem(11,13,317,"if",1),
    new OmlLexem(11,16,40,"(",0),
    new OmlLexem(11,17,433,"A",2),
    new OmlLexem(11,18,299,"=",0),
    new OmlLexem(11,19,433,"B",2),
    new OmlLexem(11,21,262,"and",1),
    new OmlLexem(11,25,433,"B",2),
    new OmlLexem(11,26,299,"=",0),
    new OmlLexem(11,27,433,"C",2),
    new OmlLexem(11,28,41,")",0),
    new OmlLexem(11,30,410,"then",1),
    new OmlLexem(12,17,437,"\"正三角形かつ二等辺三角形\"",0),
    new OmlLexem(13,13,297,"elseif",1),
    new OmlLexem(13,20,40,"(",0),
    new OmlLexem(13,21,433,"A",2),
    new OmlLexem(13,22,299,"=",0),
    new OmlLexem(13,23,433,"B",2),
    new OmlLexem(13,25,363,"or",1),
    new OmlLexem(13,28,433,"B",2),
    new OmlLexem(13,29,299,"=",0),
    new OmlLexem(13,30,433,"C",2),
    new OmlLexem(13,32,363,"or",1),
    new OmlLexem(13,35,433,"C",2),
    new OmlLexem(13,36,299,"=",0),
    new OmlLexem(13,37,433,"A",2),
    new OmlLexem(13,38,41,")",0),
    new OmlLexem(13,40,410,"then",1),
    new OmlLexem(14,17,437,"\"二等辺三角形\"",0),
    new OmlLexem(15,13,296,"else",1),
    new OmlLexem(16,17,437,"\"不等辺三角形\"",0),
    new OmlLexem(17,9,296,"else",1),
    new OmlLexem(18,13,437,"\"非三角形\"",0),
    new OmlLexem(18,19,59,";",0),
    new OmlLexem(19,1,298,"end",1),
    new OmlLexem(19,5,433,"MyersTriangle",2)
  ]
)
