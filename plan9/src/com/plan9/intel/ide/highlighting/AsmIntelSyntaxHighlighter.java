/*
 * Copyright 2013-2015 Sergey Ignatov, Alexander Zolotov, Florin Patan, Stuart Carnie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.plan9.intel.ide.highlighting;

import com.plan9.intel.lang.core.AsmIntelParserDefinition;
import com.plan9.intel.lang.core.lexer.AsmIntelLexerAdapter;
import com.plan9.intel.lang.core.psi.AsmIntelTypes;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static com.plan9.intel.ide.highlighting.AsmIntelSyntaxHighlightingColors.*;

public class AsmIntelSyntaxHighlighter extends SyntaxHighlighterBase {

  private static final Map<IElementType, TextAttributesKey> ATTRIBUTES = new HashMap<IElementType, TextAttributesKey>();

  static {
    fillMap(ATTRIBUTES, LINE_COMMENT, AsmIntelParserDefinition.LINE_COMMENT);
    fillMap(ATTRIBUTES, INSTRUCTION, AsmIntelTypes.INSTRUCTION);
    fillMap(ATTRIBUTES, STRING, AsmIntelTypes.STRING);
    fillMap(ATTRIBUTES, LABEL, AsmIntelTypes.LABEL);
    fillMap(ATTRIBUTES, FLAG, AsmIntelTypes.FLAG);

    fillMap(ATTRIBUTES, AsmIntelParserDefinition.KEYWORDS, KEYWORD);
  }

  @NotNull
  @Override
  public Lexer getHighlightingLexer() {
    return new AsmIntelLexerAdapter();
  }

  @NotNull
  public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
    return pack(ATTRIBUTES.get(tokenType));
  }
}
