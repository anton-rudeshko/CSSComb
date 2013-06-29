package com.rudeshko.csscomb;

import com.intellij.lang.css.CSSLanguage;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.LangDataKeys;
import com.intellij.psi.PsiFile;

public class CssCombAction extends AnAction {
    public void actionPerformed(AnActionEvent e) {
        PsiFile psiFile = getPsiFileFromActionEvent(e);
        if (psiFile != null) {
            psiFile.acceptChildren(new CSSCombVisitor());
        }
    }

    @Override
    public void update(AnActionEvent e) {
        e.getPresentation().setEnabled(isCss(e));
    }

    private boolean isCss(AnActionEvent e) {
        PsiFile psiFile = getPsiFileFromActionEvent(e);
        return psiFile != null && psiFile.getLanguage().isKindOf(CSSLanguage.INSTANCE);

    }

    private static PsiFile getPsiFileFromActionEvent(AnActionEvent e) {
        return e.getData(LangDataKeys.PSI_FILE);
    }
}
