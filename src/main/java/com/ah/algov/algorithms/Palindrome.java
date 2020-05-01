package com.ah.algov.algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ah.algov.algorithms.verifiers.AlgorithmSolutionVerifier;

/**
 * 
 * Concrete implementation of palindrome algorithm.
 *
 */
@Component
public class Palindrome implements Algorithm {

	private String template;

	@Autowired
	@Qualifier(value = "palindromeVerifier")
	AlgorithmSolutionVerifier palindromAlgorithmVerifier;

	@Override
	public String getName() {

		return "Palindrome";
	}

	@Override
	public String getTemplate() {
		if (null == template) {
			template = loadTemplate();
		}
		return template;
	}

	@Override
	public AlgorithmSolutionVerifier solutionVerifier() {
		return palindromAlgorithmVerifier;
	}

}
