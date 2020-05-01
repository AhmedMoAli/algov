package com.ah.algov.algorithms;

import static org.junit.jupiter.api.Assertions.assertFalse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;

import com.ah.algov.algorithms.verifiers.Verification;

@SpringBootTest
public class PalindromeTest {

	@Autowired
	Palindrome palindrome;

	@Autowired
	private ResourceLoader loader;

	@Test
	public void testPalindromeAlgorithm() throws IOException {
		final File rawInput = this.loader.getResource("classpath:PalindromRawSolution.input").getFile();
		String content = new String(Files.readAllBytes(rawInput.toPath()));
		Verification verification = palindrome.solutionVerifier().isValidSolution(content);
		
		assertFalse(verification.isFailed());
	}
}
