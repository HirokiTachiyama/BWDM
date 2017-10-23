
java -classpath "../parser/VDM.jar:../parser/org.overturetool.parser.jar" org.overturetool.parser.imp.TestParser -eUTF-8 -pp -O ../data/$1
./../source/make_decisiontable $1.app $2 $3 $4
