package com.test.antlr_scala_visitor.main;

import com.test.antlr_scala_visitor.ScalaLexer;
import com.test.antlr_scala_visitor.ScalaParser;
import com.test.antlr_scala_visitor.visitors.TemporaryScalaVisitor;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Main {
    public static void main(String[] args) {
        CharStream inputStream = CharStreams.fromString(
                "case class A ()");
        ScalaLexer scalaLexer = new ScalaLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(scalaLexer);
        ScalaParser scalaParser = new ScalaParser(commonTokenStream);
        TemporaryScalaVisitor temporaryScalaVisitor = new TemporaryScalaVisitor();

        String visitngResult = temporaryScalaVisitor.visit(scalaParser.compilationUnit());

        System.out.println(visitngResult);
    }
}
