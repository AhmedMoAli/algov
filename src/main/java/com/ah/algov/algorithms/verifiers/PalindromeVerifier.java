package com.ah.algov.algorithms.verifiers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.ah.algov.AlgoVApplication;
import com.ah.algov.controllers.errorhandling.VerificationException;
import com.ah.algov.utils.CompilationUtil;

/**
 * 
 * An implementation of palindrome verification class, this class is the whole
 * purpose of AlgoV application, mainly it is to consume the palindromer hosted
 * on AWS.
 * 
 * Palindromer is a service that generates list strings that contains some
 * palindromes and some non-palindroms string and the service accepts back
 * results identifying which strings are palindrome.
 *
 */
@Component
public class PalindromeVerifier implements AlgorithmSolutionVerifier {

	@Value("${algorithm.palindrome.url:palindromer-bd7e0fc867d57915.elb.us-east-1.amazonaws.com}")
	private String palindromerURL;
	@Value("${algorithm.palindrome.port:7777}")
	private int palindromerPort;

	@Override
	public Verification isValidSolution(String rawProposedAlgorithmSolution) {

		Verification verifiedSolution = new Verification();
		Method methodToInvoke = CompilationUtil.compileAndReturnMethod("Solution", rawProposedAlgorithmSolution,
				"isPalindrome", String.class);

		Object classInstance;
		try {
			classInstance = methodToInvoke.getDeclaringClass().newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new VerificationException("Invocation Exception " + e.getMessage());
		}

		try (Socket socket = new Socket(palindromerURL, palindromerPort)) {

			InputStream input = socket.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));

			// reads a line of text
			String line;
			while (!(line = reader.readLine()).contains("!!!")) {

				LoggerFactory.getLogger(AlgoVApplication.class).info(" Retrieved line: " + line);

				String[] splitStrings = line.split(" ");

				StringBuffer stringBuffer = new StringBuffer();

				for (String string : splitStrings) {
					if (isPalindrom(methodToInvoke, classInstance, string)) {
						stringBuffer.append(string);
						stringBuffer.append(" ");
					}
				}

				// send data to server
				OutputStream output = socket.getOutputStream();

				PrintWriter writer = new PrintWriter(output, false);
				writer.print(stringBuffer.toString() + "\n");
				writer.flush();

			}
			LoggerFactory.getLogger(AlgoVApplication.class).info(" Retrieved line: " + line);
			if (line.contains("error")) {
				verifiedSolution.setFailed(true);
				verifiedSolution.setFailureMSG(line);
			}

		} catch (IOException e) {
			throw new VerificationException("Communication exception " + e.getMessage());
		}

		return verifiedSolution;
	}

	private boolean isPalindrom(Method methodToInvoke, Object classInstance, String str) {
		try {
			return Boolean.valueOf(methodToInvoke.invoke(classInstance, str).toString());

		} catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
			throw new VerificationException("Invocation Exception" + e.getMessage());
		}
	}

}
