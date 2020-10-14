package com.test.antlr_scala_visitor.walker;

import com.test.antlr_scala_visitor.ScalaParser;

import java.util.stream.Collectors;

public class ScalaWalker {
    public void walk(ScalaParser.CompilationUnitContext compilationUnit, WalkListener walkListener) {
        int levelInTree = 0;

        if (compilationUnit.qualId() != null) {
            for (ScalaParser.QualIdContext qualId : compilationUnit.qualId()) {
                walkPackageName(qualId, walkListener, levelInTree);
            }
        }

        if (compilationUnit.topStatSeq() != null) {
            walkTopStatementSeq(compilationUnit.topStatSeq(), walkListener, levelInTree);
        }
    }

    private void walkPackageName(ScalaParser.QualIdContext qualId, WalkListener walkListener, int levelInTree) {
        String packageNameCommaSeparated = qualId.Id().stream().map(q -> q.getSymbol().getText()).collect(Collectors.joining(","));
        walkListener.enterPackage(packageNameCommaSeparated, levelInTree);
    }

    private void walkTopStatementSeq(ScalaParser.TopStatSeqContext topStatSeqContext, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        if (topStatSeqContext.topStat() != null) {
            for (ScalaParser.TopStatContext topStatement : topStatSeqContext.topStat()) {
                walkTopStatement(topStatement, walkListener, nextLevelInTree);
            }
        }
    }

    private void walkTopStatement(ScalaParser.TopStatContext topStatement, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        if (topStatement.tmplDef() != null) {
            walkTmpl(topStatement.tmplDef(), walkListener, nextLevelInTree);
        }
        if (topStatement.import_() != null) {
            walkImportBlock(topStatement.import_(), walkListener, nextLevelInTree);
        }
        if (topStatement.packaging() != null) {
            walkPackaging(topStatement.packaging(), walkListener, nextLevelInTree);
        }
        if (topStatement.packageObject() != null) {
            walkPackageObject(topStatement.packageObject(), walkListener, nextLevelInTree);
        }
    }

    private void walkImportBlock(ScalaParser.Import_Context import_context, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        if (import_context.importExpr() != null) {
            for (ScalaParser.ImportExprContext importExprContext : import_context.importExpr()) {
                walkImportExpression(importExprContext, walkListener, nextLevelInTree);
            }
        }
    }

    private void walkPackageObject(ScalaParser.PackageObjectContext packageObjectContext, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        walkListener.enterPackageObject(levelInTree);
        if (packageObjectContext.objectDef() != null) {
            walkObject(packageObjectContext.objectDef(), walkListener, nextLevelInTree);
        }
    }

    private void walkTmpl(ScalaParser.TmplDefContext tmplDefContext, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        if (tmplDefContext.classDef() != null) {
            walkClass(tmplDefContext.classDef(), walkListener, nextLevelInTree);
        } else if (tmplDefContext.objectDef() != null) {
            walkObject(tmplDefContext.objectDef(), walkListener, nextLevelInTree);
        } else if (tmplDefContext.traitDef() != null) {
            walkTrait(tmplDefContext.traitDef(), walkListener, nextLevelInTree);
        }
    }

    private void walkClass(ScalaParser.ClassDefContext classDefContext, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        walkListener.enterClass(classDefContext.Id().getSymbol().getText(), levelInTree);
        if (classDefContext.classTemplateOpt() != null
                && classDefContext.classTemplateOpt().classTemplate() != null
                && classDefContext.classTemplateOpt().classTemplate().templateBody() != null) {
            walkTemplateBody(classDefContext.classTemplateOpt().classTemplate().templateBody(), walkListener, nextLevelInTree);
        }
    }

    private void walkObject(ScalaParser.ObjectDefContext objectDefContext, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        walkListener.enterObject(objectDefContext.Id().getSymbol().getText(), levelInTree);
        if (objectDefContext.classTemplateOpt() != null
                && objectDefContext.classTemplateOpt().classTemplate() != null
                && objectDefContext.classTemplateOpt().classTemplate().templateBody() != null) {
            walkTemplateBody(objectDefContext.classTemplateOpt().classTemplate().templateBody(), walkListener, nextLevelInTree);
        }
    }

    private void walkTrait(ScalaParser.TraitDefContext traitDefContext, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        walkListener.enterTrait(traitDefContext.Id().getSymbol().getText(), levelInTree);
        if (traitDefContext.traitTemplateOpt() != null
                && traitDefContext.traitTemplateOpt().templateBody() != null) {
            walkTemplateBody(traitDefContext.traitTemplateOpt().templateBody(), walkListener, nextLevelInTree);
        }
    }

    private void walkTemplateBody(ScalaParser.TemplateBodyContext templateBodyContext, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        if (templateBodyContext.templateStat() != null) {
            for (ScalaParser.TemplateStatContext templateStatContext : templateBodyContext.templateStat()) {
                walkTemplateStat(templateStatContext, walkListener, nextLevelInTree);
            }
        }
    }

    private void walkTemplateStat(ScalaParser.TemplateStatContext templateStatContext, WalkListener walkListener, int levelInTree) {
        // TODO will be implemented later. This is class/object/trait body
    }

    private void walkImportExpression(ScalaParser.ImportExprContext importExprContext, WalkListener walkListener, int levelInTree) {
        String importLine = importExprContext.getText(); // import itself is complicated structure. Import parsing is out of scope now
        walkListener.enterImport(importLine, levelInTree);
    }

    private void walkPackaging(ScalaParser.PackagingContext packagingContext, WalkListener walkListener, int levelInTree) {
        int nextLevelInTree = nextLevelInTree(levelInTree);

        walkPackageName(packagingContext.qualId(), walkListener, nextLevelInTree);
        if (packagingContext.topStatSeq() != null) {
            walkTopStatementSeq(packagingContext.topStatSeq(), walkListener, nextLevelInTree);
        }
    }

    private int nextLevelInTree(int levelInTree) {
        return levelInTree + 1;
    }
}
