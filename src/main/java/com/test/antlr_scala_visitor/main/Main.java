package com.test.antlr_scala_visitor.main;

import com.test.antlr_scala_visitor.ScalaLexer;
import com.test.antlr_scala_visitor.ScalaParser;
import com.test.antlr_scala_visitor.walker.ScalaWalker;
import com.test.antlr_scala_visitor.walker.TreePrintWalkListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import java.io.IOException;
import java.io.InputStream;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Single package tree");
        printTreeForSourceFile("test-single-package.scala");

        System.out.println();
        System.out.println("Multi package tree");
        printTreeForSourceFile("test-multi-package.scala");

        System.out.println();
        System.out.println("Package object tree");
        printTreeForSourceFile("test-package-object.scala");
    }

    private static void printTreeForSourceFile(String fileName) throws IOException {
        InputStream fileStream = Main.class.getClassLoader().getResourceAsStream(fileName);
        CharStream inputStream = CharStreams.fromStream(fileStream);
        ScalaLexer scalaLexer = new ScalaLexer(inputStream);
        CommonTokenStream commonTokenStream = new CommonTokenStream(scalaLexer);
        ScalaParser scalaParser = new ScalaParser(commonTokenStream);


        ScalaWalker scalaWalker = new ScalaWalker();
        TreePrintWalkListener treePrintWalkListener = new TreePrintWalkListener();
        scalaWalker.walk(scalaParser.compilationUnit(), treePrintWalkListener);
    }
}
