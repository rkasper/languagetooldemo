package com.kasperowski.languagetooldemo;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * Unit test for the LanguageTool demo
 */
public class LanguageToolDemoTest {
	private final LanguageToolDemo languageTool = new LanguageToolDemo();

	/**
	 * Asserts that the spelling corrector makes the expected correction.
	 * 
	 * @param toBeCorrected
	 * @param expectedCorrection
	 */
	private void assertSpellingCorrected(String toBeCorrected,
			String expectedCorrection) {
		String actualCorrection = languageTool.correct(toBeCorrected);
		assertEquals(expectedCorrection, actualCorrection);
	}

	@Test
	public void correctsOneWordAtATime() {
		assertSpellingCorrected("speling", "spelling");
		assertSpellingCorrected("extenzion", "extension");
	}

	@Test
	public void doesntCorrectProperlySpelledWord() {
		assertSpellingCorrected("correct", "correct");
	}

	@Test
	public void correctsTwoWords() {
		assertSpellingCorrected("corect speling", "correct spelling");
		assertSpellingCorrected("correct incorect", "correct incorrect");
		assertSpellingCorrected("incorect correct", "incorrect correct");
	}

	@Test
	public void correctsOneSentence() {
		String expectedCorrection = "There is a misspelled word in this sentence.";
		assertSpellingCorrected("There is a misspelled word in this sentenc.",
				expectedCorrection);
		assertSpellingCorrected("There is a misspeled word in this sentence.",
				expectedCorrection);
	}

	@Test
	public void correctsOneParagraph() {
		String expectedCorrection = "Five score years ago, a great American, in whose symbolic shadow we stand today, signed the Emancipation Proclamation. This momentous decree came as a great beacon light of hope to millions of Negro slaves who had been seared in the flames of withering injustice. It came as a joyous daybreak to end the long night of their captivity.";

		// No errors
		assertSpellingCorrected(expectedCorrection, expectedCorrection);

		// One error
		assertSpellingCorrected(
				"Five score years ago, a great American, in whose sympolic shadow we stand today, signed the Emancipation Proclamation. This momentous decree came as a great beacon light of hope to millions of Negro slaves who had been seared in the flames of withering injustice. It came as a joyous daybreak to end the long night of their captivity.",
				expectedCorrection);

		// Two errors
		assertSpellingCorrected(
				"Five score years ago, a great American, in whose sympolic shadow we stand today, signed the Emancipation Proclamation. This momentous decree came as a great beacon light of hope to milions of Negro slaves who had been seared in the flames of withering injustice. It came as a joyous daybreak to end the long night of their captivity.",
				expectedCorrection);

		// Three errors
		assertSpellingCorrected(
				"Five score years ago, a great American, in whose sympolic shadow we stand today, signed the Emancipation Proclamation. This momentous decree came as a great beacon light of hope to milions of Negro slaves who had been seared in the flames of withering injustice. It came as a joyus daybreak to end the long night of their captivity.",
				expectedCorrection);
	}

	@Test
	public void correctsManyParagraphs() {
		String expectedCorrection = "I am happy to join with you today in what will go down in history as the greatest demonstration for freedom in the history of our nation."
				+ "\n\nFive score years ago, a great American, in whose symbolic shadow we stand today, signed the Emancipation Proclamation. This momentous decree came as a great beacon light of hope to millions of Negro slaves who had been seared in the flames of withering injustice. It came as a joyous daybreak to end the long night of their captivity."
				+ "\n\nBut one hundred years later, the Negro still is not free. One hundred years later, the life of the Negro is still sadly crippled by the manacles of segregation and the chains of discrimination. One hundred years later, the Negro lives on a lonely island of poverty in the midst of a vast ocean of material prosperity. One hundred years later, the Negro is still languished in the corners of American society and finds himself an exile in his own land. And so we've come here today to dramatize a shameful condition."
				+ "\n\nIn a sense we've come to our nation's capital to cash a check. When the architects of our republic wrote the magnificent words of the Constitution and the Declaration of Independence, they were signing a promissory note to which every American was to fall heir. This note was a promise that all men, yes, black men as well as white men, would be guaranteed the \"unalienable Rights\" of \"Life, Liberty and the pursuit of Happiness.\" It is obvious today that America has defaulted on this promissory note, insofar as her citizens of color are concerned. Instead of honoring this sacred obligation, America has given the Negro people a bad check, a check which has come back marked \"insufficient funds.\"";

		// No errors
		assertSpellingCorrected(expectedCorrection, expectedCorrection);
		
		// One error
		assertSpellingCorrected("I am happy to join with you tooday in what will go down in history as the greatest demonstration for freedom in the history of our nation."
				+ "\n\nFive score years ago, a great American, in whose symbolic shadow we stand today, signed the Emancipation Proclamation. This momentous decree came as a great beacon light of hope to millions of Negro slaves who had been seared in the flames of withering injustice. It came as a joyous daybreak to end the long night of their captivity."
				+ "\n\nBut one hundred years later, the Negro still is not free. One hundred years later, the life of the Negro is still sadly crippled by the manacles of segregation and the chains of discrimination. One hundred years later, the Negro lives on a lonely island of poverty in the midst of a vast ocean of material prosperity. One hundred years later, the Negro is still languished in the corners of American society and finds himself an exile in his own land. And so we've come here today to dramatize a shameful condition."
				+ "\n\nIn a sense we've come to our nation's capital to cash a check. When the architects of our republic wrote the magnificent words of the Constitution and the Declaration of Independence, they were signing a promissory note to which every American was to fall heir. This note was a promise that all men, yes, black men as well as white men, would be guaranteed the \"unalienable Rights\" of \"Life, Liberty and the pursuit of Happiness.\" It is obvious today that America has defaulted on this promissory note, insofar as her citizens of color are concerned. Instead of honoring this sacred obligation, America has given the Negro people a bad check, a check which has come back marked \"insufficient funds.\"",
				expectedCorrection);
		
		// Two errors
		assertSpellingCorrected("I am happy to join with you tooday in what will go down in history as the greatest demonstration for freedom in the history of our nation."
				+ "\n\nFive score years ago, a great American, in whose symbolik shadow we stand today, signed the Emancipation Proclamation. This momentous decree came as a great beacon light of hope to millions of Negro slaves who had been seared in the flames of withering injustice. It came as a joyous daybreak to end the long night of their captivity."
				+ "\n\nBut one hundred years later, the Negro still is not free. One hundred years later, the life of the Negro is still sadly crippled by the manacles of segregation and the chains of discrimination. One hundred years later, the Negro lives on a lonely island of poverty in the midst of a vast ocean of material prosperity. One hundred years later, the Negro is still languished in the corners of American society and finds himself an exile in his own land. And so we've come here today to dramatize a shameful condition."
				+ "\n\nIn a sense we've come to our nation's capital to cash a check. When the architects of our republic wrote the magnificent words of the Constitution and the Declaration of Independence, they were signing a promissory note to which every American was to fall heir. This note was a promise that all men, yes, black men as well as white men, would be guaranteed the \"unalienable Rights\" of \"Life, Liberty and the pursuit of Happiness.\" It is obvious today that America has defaulted on this promissory note, insofar as her citizens of color are concerned. Instead of honoring this sacred obligation, America has given the Negro people a bad check, a check which has come back marked \"insufficient funds.\"",
				expectedCorrection);
		
		// Three errors
		assertSpellingCorrected("I am happy to join with you tooday in what will go down in history as the greatest demonstration for freedom in the history of our nation."
				+ "\n\nFive score years ago, a great American, in whose symbolik shadow we stand today, signed the Emancipation Proclamation. This momentous decree came as a great beacon light of hope to millions of Negro slaves who had been seared in the flames of withering injustice. It came as a joyous daybreak to end the long night of their captivity."
				+ "\n\nBut one hundred years later, the Negro still is not free. One hundred years later, the life of the Negro is still sadly crippled by the manacles of segregation and the chains of discrimination. One hundred years later, the Negro lives on a lonely island of poverty in the midst of a vast ocean of material prosperity. One hundred years later, the Negro is still languished in the corners of American society and finds himself an exile in his own land. And so we've come here today to dramatize a shameful condition."
				+ "\n\nIn a sense we've come to our nation's capital to cash a czeck. When the architects of our republic wrote the magnificent words of the Constitution and the Declaration of Independence, they were signing a promissory note to which every American was to fall heir. This note was a promise that all men, yes, black men as well as white men, would be guaranteed the \"unalienable Rights\" of \"Life, Liberty and the pursuit of Happiness.\" It is obvious today that America has defaulted on this promissory note, insofar as her citizens of color are concerned. Instead of honoring this sacred obligation, America has given the Negro people a bad check, a check which has come back marked \"insufficient funds.\"",
				expectedCorrection);
	}
}
