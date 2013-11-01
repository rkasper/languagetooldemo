package com.kasperowski.languagetooldemo;

import java.io.IOException;
import java.util.List;

import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.Rule;
import org.languagetool.rules.RuleMatch;

public class LanguageToolDemo {
	public String correct(String toBeCorrected) {
		StringBuffer corrected = new StringBuffer();

		try {
			JLanguageTool langTool = new JLanguageTool(new AmericanEnglish());
			for (Rule rule : langTool.getAllRules()) {
				if (!rule.isSpellingRule()) {
					langTool.disableRule(rule.getId());
				}
			}

			int prevCorrectionPos = 0;

			// Find and correct all the misspelled words in toBeCorrected.
			List<RuleMatch> matches = langTool.check(toBeCorrected);
			for (RuleMatch match : matches) {
				// Replace the misspelled word with the first suggested
				// correction.
				int from = match.getFromPos();
				int to = match.getToPos();
				List<String> suggestedCorrections = match
						.getSuggestedReplacements();
				for (String suggestedCorrection : suggestedCorrections) {
					corrected = corrected.append(toBeCorrected.substring(
							prevCorrectionPos, from));
					corrected = corrected.append(suggestedCorrection);
					prevCorrectionPos = to;
					break;
				}
			}
			corrected = corrected.append(toBeCorrected.substring(
					prevCorrectionPos, toBeCorrected.length()));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return corrected.toString();
	}
}
