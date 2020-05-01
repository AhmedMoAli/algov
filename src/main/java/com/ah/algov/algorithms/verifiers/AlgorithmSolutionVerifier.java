package com.ah.algov.algorithms.verifiers;

/**
 * 
 * Algorithm verifiers that verifies the proposed solution against this
 * algorithm.
 *
 */
public interface AlgorithmSolutionVerifier {

	/**
	 * 
	 * Checks whether a solution is valid or no through running test cases against
	 * it and observing the outcome.
	 * 
	 * @param rawProposedSolution the raw proposed solution.
	 * @return {@link Verification} verification of the algorithm.
	 */
	Verification isValidSolution(String rawProposedSolution);

}
