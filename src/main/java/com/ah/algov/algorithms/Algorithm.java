package com.ah.algov.algorithms;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.core.io.ClassPathResource;

import com.ah.algov.algorithms.verifiers.AlgorithmSolutionVerifier;

/**
 * 
 * Algorithm interface that holds various methods for algorithm.
 *
 */
public interface Algorithm {

	/**
	 * gets the algorithm's name.
	 * 
	 * @return algorithm name.
	 */
	String getName();

	/**
	 * gets the algorithm template.
	 * 
	 * @return the algorithm template which the user needs to fill and submit to
	 *         verify his solution.
	 */
	String getTemplate();

	/**
	 * The verifier to use for this algorithm to check correctness.
	 * 
	 * @return {@link AlgorithmSolutionVerifier}
	 */
	AlgorithmSolutionVerifier solutionVerifier();

	/**
	 * Default implementation for loading the template.
	 * 
	 * @return the template content.
	 */
	default String loadTemplate() {

		String template = "";
		try {
			File resource = new ClassPathResource(
					"algorithms/" + getClass().getSimpleName().toLowerCase() + ".java.template").getFile();
			template = new String(Files.readAllBytes(resource.toPath()));

		} catch (IOException e) {
			e.printStackTrace();
		}

		return template;
	}
}
