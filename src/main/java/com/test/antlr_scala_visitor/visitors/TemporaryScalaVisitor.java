package com.test.antlr_scala_visitor.visitors;

import com.test.antlr_scala_visitor.ScalaBaseVisitor;
import com.test.antlr_scala_visitor.ScalaParser;

public class TemporaryScalaVisitor extends ScalaBaseVisitor<String> {
    @Override
    public String visitCompilationUnit(ScalaParser.CompilationUnitContext ctx) {
        return ctx.getText();
    }
}


