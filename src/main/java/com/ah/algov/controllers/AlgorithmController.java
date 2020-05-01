package com.ah.algov.controllers;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ah.algov.algorithms.Algorithm;
import com.ah.algov.algorithms.verifiers.Verification;
import com.ah.algov.dto.AlgorithmDTO;
import com.ah.algov.dto.AlgorithmIdentifierDTO;

/**
 * REST Controller for handling Algorithm actions.
 */
@RestController
@RequestMapping("/api/v1")
public class AlgorithmController {

	@Autowired
	private Map<String, Algorithm> algorithms;
	@Autowired
	private ModelMapper modellMapper;

	/**
	 * Returns a list of available algorithms.
	 * 
	 * @return List of {@link AlgorithmIdentifierDTO}.
	 */
	@GetMapping(path = "/algorithms", produces = "application/json")
	public ResponseEntity<List<AlgorithmIdentifierDTO>> getAlgorithms() {

		List<AlgorithmIdentifierDTO> algortihmDTOs = algorithms.entrySet().stream().map(entry -> {
			AlgorithmIdentifierDTO algorithm = new AlgorithmIdentifierDTO();
			algorithm.setId(entry.getKey());
			algorithm.setName(entry.getValue().getName());

			return algorithm;
		}).collect(Collectors.toList());

		return new ResponseEntity<>(algortihmDTOs, HttpStatus.OK);
	}

	/**
	 * Returns a specific algorithm.
	 * 
	 * @param algorithm the algorithm to return.
	 * 
	 * @return {@link AlgorithmDTO}.
	 */
	@GetMapping(path = "/algorithms/{algorithm}", produces = "application/json")
	public ResponseEntity<AlgorithmDTO> getAlgorithm(@PathVariable("algorithm") final String algorithm) {

		Algorithm selectedAlgorithm = algorithms.get(algorithm);
		if (null == selectedAlgorithm) {
			throw new IllegalArgumentException("Algorithm not found!");
		}

		return new ResponseEntity<>(modellMapper.map(selectedAlgorithm, AlgorithmDTO.class), HttpStatus.OK);
	}

	/***
	 * Verifies the proposed solution (whether it passes unit tests or no).
	 * 
	 * @param algorithm           algorithm to verify the proposed solution against.
	 * @param rawProposedSolution the raw proposed solution.
	 * @return {@link Verification} object that contains the results plus details in
	 *         case of failed verification.
	 */
	@PostMapping(path = "/verify/{algorithm}", produces = "application/json")
	public ResponseEntity<Verification> verifyAlgorithm(@PathVariable("algorithm") final String algorithm,
			@RequestBody final String rawProposedSolution) {

		Algorithm algorithmToVerify = algorithms.get(algorithm);
		if (null == algorithmToVerify) {
			throw new IllegalArgumentException("Algorithm not found!");
		}
		Verification verification;

		try {
			verification = algorithmToVerify.solutionVerifier().isValidSolution(rawProposedSolution);
		} catch (Exception exp) {
			verification = new Verification();
			verification.setFailed(true);
			verification.setFailureMSG(exp.getMessage());
		}

		return new ResponseEntity<>(verification, HttpStatus.OK);
	}
}
