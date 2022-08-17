package com.flipperplz.dayzdev.language.rap.parser;

import com.flipperplz.dayzdev.language.rap.RapLanguage;
import com.flipperplz.dayzdev.language.rap.lexer.RapLexerAdapter;
import com.flipperplz.dayzdev.language.rap.psi.RapElementTypes;
import com.flipperplz.dayzdev.language.rap.psi.RapPsiFile;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public class RapParserDefinition implements ParserDefinition {
    public static final TokenSet COMMENTS = TokenSet.create(
            RapElementTypes.EMPTY_DELIMITED_COMMENT,
            RapElementTypes.DELIMITED_COMMENT,
            RapElementTypes.SINGLE_LINE_COMMENT
    );
    public static final TokenSet STRINGS = TokenSet.create(
            RapElementTypes.LITERALSTRING
    );
    public static final IFileElementType FILE = new IFileElementType(
            RapLanguage.INSTANCE
    );

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new RapLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new RapParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return STRINGS;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode node) {
        return RapElementTypes.Factory.createElement(node);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider viewProvider) {
        return new RapPsiFile(viewProvider);
    }
}
