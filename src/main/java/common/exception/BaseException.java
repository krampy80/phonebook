package common.exception;

import hu.hugo.common.ReasonCodeType;

public class BaseException extends Exception {
	
	private ReasonCodeType reasonCode;
	
	public BaseException(String message){
		super(message);
	}
	
	public BaseException(ReasonCodeType reasonCode, String message){
		this(message);
		this.reasonCode=reasonCode;
	}

	public ReasonCodeType getReasonCode() {
		return reasonCode;
	}

	public void setReasonCode(ReasonCodeType reasonCode) {
		this.reasonCode = reasonCode;
	}

}
