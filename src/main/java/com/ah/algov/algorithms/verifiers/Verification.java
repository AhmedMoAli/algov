package com.ah.algov.algorithms.verifiers;

/**
 * 
 * Class represents verification results, failed verification will hold the
 * reason behind failure whether it is compilation issue or failing test case
 * for example.
 *
 */
public class Verification {

	private boolean failed;
	private String failureMSG;

	public boolean isFailed() {
		return failed;
	}

	public void setFailed(boolean failed) {
		this.failed = failed;
	}

	public String getFailureMSG() {
		return failureMSG;
	}

	public void setFailureMSG(String failureMSG) {
		this.failureMSG = failureMSG;
	}

}
