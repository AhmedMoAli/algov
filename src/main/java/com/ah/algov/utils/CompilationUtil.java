package com.ah.algov.utils;

import java.lang.reflect.Method;

import org.mdkt.compiler.InMemoryJavaCompiler;

import com.ah.algov.controllers.errorhandling.VerificationException;

/**
 * 
 * Utility class for compiling java code in memory.
 *
 */
public class CompilationUtil {

	/**
	 * 
	 * Compiles and return the method to invoke.
	 * 
	 * @param className  the class name.
	 * @param classCode  the code to compile.
	 * @param methodName the method that we are interested in.
	 * @param parameters the parameters to this method.
	 * @return method matching the parameters we sent for this newly compiled class.
	 */
	public static Method compileAndReturnMethod(final String className, final String classCode, String methodName,
			Class<?>... parameters) {
		try {
			Class<?> compiledClass = InMemoryJavaCompiler.newInstance().compile(className, classCode);
			return compiledClass.getMethod(methodName, parameters);

		} catch (Exception e) {
			throw new VerificationException("Compilation failed " + e.getMessage());
		}
	}
}
