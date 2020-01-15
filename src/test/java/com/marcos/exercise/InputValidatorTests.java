package com.marcos.exercise;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class InputValidatorTests {

	private InputValidator inputValidator = InputValidator.getInstance();

	@Test
	void validateTrueSequence() {
		String testSequence = "3;6;7;8;9";
		Assertions.assertTrue(inputValidator.isSequence(testSequence));
	}

	@Test
	void validateSingleElementSequence() {
		String testSequence = "3";
		Assertions.assertTrue(inputValidator.isSequence(testSequence));
	}

	@Test
	void validateFalseSequence() {
		String testSequence = "3;x;7;x;9";
		Assertions.assertFalse(inputValidator.isSequence(testSequence));
	}

	@Test
	void validateBadFormat() {
		String testSequence = "abcd";
		Assertions.assertFalse(inputValidator.isSequence(testSequence));
	}

	@Test
	void validateBadFormatCommand() {
		String testSequence = "+5";
		Assertions.assertFalse(inputValidator.isSequence(testSequence));
	}

}
