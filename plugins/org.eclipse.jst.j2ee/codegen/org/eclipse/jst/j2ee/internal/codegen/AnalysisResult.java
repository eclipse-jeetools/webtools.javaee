/*******************************************************************************
 * Copyright (c) 2003, 2004 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 * IBM Corporation - initial API and implementation
 *******************************************************************************/
package org.eclipse.jst.j2ee.internal.codegen;



import java.util.List;



/**
 * The result of a single generator's analysis. A generator may or may not produce an analysis
 * result (i.e. analyze may return null). Detailed analysis of the results reported by domain
 * specific generators will require some knowledge of the domain. For minimal reporting support, the
 * objects created by the domain specific generators and placed in this object should have useful
 * toString implementations. Some generation framework implementations may extend this class to
 * enable the modification of generation output via analysis result instances. See the documentation
 * for the domain specific generators to see if and how that is accomplished.
 * 
 * <p>
 * <b>NOTE: There has not yet been a code generation solution that makes significant use of analysis
 * results. Therefore, the potential for change for this class is much higher than it is for the
 * rest of the framework. </b>
 */
public class AnalysisResult {
	/**
	 * The minimum status value is 0.
	 */
	public static final int MIN_STATUS = 0;
	/**
	 * Status codes in the range 0 - 999 are of no particular importance to the generation caller.
	 */
	public static final int NONE = MIN_STATUS;
	/**
	 * Status codes in the range 1000 - 1999 are of informational importance to the generation
	 * caller.
	 */
	public static final int INFORMATIONAL = 1000;
	/**
	 * Status codes in the range 2000 - 2999 are of warning importance to the generation caller.
	 */
	public static final int WARNING = 2000;
	/**
	 * Status codes in the range 3000 - 3999 are of error importance to the generation caller.
	 */
	public static final int ERROR = 3000;
	/**
	 * The maximum status value is 3999.
	 */
	public static final int MAX_STATUS = 3999;
	/**
	 * The default reason code is none.
	 */
	public static final int NO_REASON_CODE = 0;
	/**
	 * Reason codes 0-99 are reserved for use by the base framework. Various target language
	 * frameworks may use overlapping codes from 100-999
	 */
	public static final int MIN_FW_REASON_CODE = 100;
	/**
	 * Reason codes 100-999 are reserved for use by the various generation frameworks. Various
	 * target language frameworks may use overlapping codes from 100-999. Codes greater than or
	 * equal to 1000 are for use by users of the various generation frameworks. Overlap between
	 * implementations is expected.
	 */
	public static final int MIN_USER_REASON_CODE = 1000;
	private Object fFrom = null;
	private Object fTo = null;
	private Object fReason = null;
	private int fReasonCode = NO_REASON_CODE;
	private int fStatus = NONE;
	private List fChildResults = null;
	private int fMaximumDescendantStatus = NONE;
	private static final String DEFAULT_REASON = BaseCodeGenResourceHandler.getString("No_analysis_reason_provide_INFO_"); //$NON-NLS-1$ = "No analysis reason provided for result, from \"{0}\" to \"{1}\"."
	private static final String MISSING = BaseCodeGenResourceHandler.getString("_missing_"); //$NON-NLS-1$ = "<missing>"

	/**
	 * Constructs an empty result. At least a reason should be set before reporting this result.
	 */
	public AnalysisResult() {
		super();
	}

	/**
	 * Adds the child result to this result if the parm is not null. When this method returns, this
	 * instance's maximum descendant status will be set to the maximum of:
	 * <ul>
	 * <li>this instance's maximum descendant status upon entry
	 * <li>the child's status
	 * <li>the child's maximum descendant status
	 * </ul>
	 * 
	 * @param newChildResult
	 *            AnalysisResult
	 */
	public void addChildResult(AnalysisResult newChildResult) {
		if (newChildResult != null) {
			getChildResults().add(newChildResult);
			int maxChildResult = (newChildResult.getStatus() > newChildResult.getMaximumDescendantStatus()) ? newChildResult.getStatus() : newChildResult.getMaximumDescendantStatus();
			if (maxChildResult > getMaximumDescendantStatus())
				setMaximumDescendantStatus(maxChildResult);
		}
	}

	/**
	 * Returns the list of child analysis results.
	 * 
	 * @return java.util.List
	 */
	public List getChildResults() {
		if (fChildResults == null)
			fChildResults = new java.util.ArrayList();
		return fChildResults;
	}

	/**
	 * Returns an object that describes the target object as it is before this generation. Returns
	 * null if the target is new.
	 * 
	 * @return java.lang.Object
	 */
	public java.lang.Object getFrom() {
		return fFrom;
	}

	/**
	 * Returns the maximum status of all analysis result descendants ({@link AnalysisResult#NONE},
	 * {@link AnalysisResult#INFORMATIONAL},{@link AnalysisResult#WARNING}or
	 * {@link AnalysisResult#ERROR}).
	 * 
	 * @return int
	 */
	public int getMaximumDescendantStatus() {
		return fMaximumDescendantStatus;
	}

	/**
	 * Returns an object that describes why this change is being made to the target object. Returns
	 * null if no reason is specified.
	 * 
	 * @return java.lang.Object
	 */
	public java.lang.Object getReason() {
		return fReason;
	}

	/**
	 * A reason code to be used programmatically to take action on a recoverable result. The default
	 * is {@link AnalysisResult#NO_REASON_CODE}. Values {@link AnalysisResult#NO_REASON_CODE}
	 * through ({@link AnalysisResult#MIN_USER_REASON_CODE}- 1) are reserved for use by the
	 * generation frameworks.
	 * 
	 * @return int
	 */
	public int getReasonCode() {
		return fReasonCode;
	}

	/**
	 * Returns the status of this result ({@link AnalysisResult#NONE},
	 * {@link AnalysisResult#INFORMATIONAL},{@link AnalysisResult#WARNING}or
	 * {@link AnalysisResult#ERROR}).
	 * 
	 * @return int
	 */
	public int getStatus() {
		return fStatus;
	}

	/**
	 * Returns an object that describes the target object as it will be after this generation.
	 * Returns null if the target is being deleted.
	 * 
	 * @return java.lang.Object
	 */
	public java.lang.Object getTo() {
		return fTo;
	}

	/**
	 * Sets an object that describes the target object as it is before this generation. May be set
	 * to null if the target is new.
	 */
	public void setFrom(java.lang.Object newFrom) {
		fFrom = newFrom;
	}

	/**
	 * Used to set the status of this result. An IllegalArgumentException is thrown if the parameter
	 * is not one of the valid values.
	 * 
	 * @param newMaximumDescendantStatus
	 *            int
	 */
	public void setMaximumDescendantStatus(int newMaximumDescendantStatus) {
		validateStatus(newMaximumDescendantStatus);
		fMaximumDescendantStatus = newMaximumDescendantStatus;
	}

	/**
	 * Sets an object that describes why this change is being made to the target object. Generators
	 * should always set this, but it is not gauranteed by the framework. Care is advised.
	 */
	public void setReason(java.lang.Object newReason) {
		fReason = newReason;
	}

	/**
	 * A reason code to be used programmatically to take action on a recoverable result. The default
	 * is {@link AnalysisResult#NO_REASON_CODE}. Values {@link AnalysisResult#NO_REASON_CODE}
	 * through ({@link AnalysisResult#MIN_USER_REASON_CODE}- 1) are reserved for use by the
	 * generation frameworks.
	 * 
	 * @param newReasonCode
	 *            int
	 */
	public void setReasonCode(int newReasonCode) {
		fReasonCode = newReasonCode;
	}

	/**
	 * Used to set the status of this result. An IllegalArgumentException is thrown if the parameter
	 * is not one of the valid values.
	 * 
	 * @param newStatus
	 *            int
	 */
	public void setStatus(int newStatus) {
		validateStatus(newStatus);
		fStatus = newStatus;
	}

	/**
	 * Sets an object that describes the target object as it will be after this generation. May be
	 * set to null if the target being deleted.
	 */
	public void setTo(java.lang.Object newTo) {
		fTo = newTo;
	}

	/**
	 * Returns the reason converted to string with the from and to (converted to string) inserted.
	 */
	public String toString() {
		String reason = DEFAULT_REASON;
		if (getReason() != null)
			reason = getReason().toString();
		String from = MISSING;
		if (getFrom() != null)
			from = getFrom().toString();
		String to = MISSING;
		if (getTo() != null)
			from = getTo().toString();
		return java.text.MessageFormat.format(reason, new String[]{from, to});
	}

	/**
	 * Used to validate that the status is a valid value. An IllegalArgumentException is thrown if
	 * the parameter is not one of the valid values.
	 * 
	 * @param newStatus
	 *            int
	 */
	protected void validateStatus(int newStatus) {
		if ((newStatus < MIN_STATUS) || (newStatus > MAX_STATUS))
			throw new IllegalArgumentException(BaseCodeGenResourceHandler.getString("Analysis_status_out_of_ran_EXC_")); //$NON-NLS-1$ = "Analysis status out of range."
	}
}