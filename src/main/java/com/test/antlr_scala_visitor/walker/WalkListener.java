package com.test.antlr_scala_visitor.walker;

public interface WalkListener {

    void enterPackage(String packageName, int levelInTree);

    void enterPackageObject(int levelInTree);

    void enterImport(String importName, int levelInTree);

    void enterClass(String className, int levelInTree);

    void enterObject(String objectName, int levelInTree);

    void enterTrait(String traitName, int levelInTree);

    void enterMethod(String methodName, int levelInTree);
}
