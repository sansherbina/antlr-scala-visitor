package com.test.antlr_scala_visitor.walker;

import java.util.Collections;

public class TreePrintWalkListener implements WalkListener {
    private static final String INDENT = String.join("", Collections.nCopies(20, "="));
    private static final String TREE_NODE_START_SYMBOL = ">";

    @Override
    public void enterPackage(String packageName, int levelInTree) {
        printWithIndent("package " + packageName, levelInTree);
    }

    @Override
    public void enterPackageObject(int levelInTree) {
        printWithIndent("package object definition", levelInTree);
    }

    @Override
    public void enterImport(String importName, int levelInTree) {
        printWithIndent("import " + importName, levelInTree);
    }

    @Override
    public void enterClass(String className, int levelInTree) {
        printWithIndent("class " + className, levelInTree);
    }

    @Override
    public void enterObject(String objectName, int levelInTree) {
        printWithIndent("object " + objectName, levelInTree);
    }

    @Override
    public void enterTrait(String traitName, int levelInTree) {
        printWithIndent("trait " + traitName, levelInTree);
    }

    @Override
    public void enterMethod(String methodName, int levelInTree) {
        printWithIndent("method " + methodName, levelInTree);
    }

    private void printWithIndent(String text, int levelInTree) {
        StringBuffer line = new StringBuffer();
        for (int i = 0; i < levelInTree; i++) {
            line.append(INDENT);
        }
        line.append(TREE_NODE_START_SYMBOL);
        line.append(text);

        System.out.println(line.toString());
    }
}
