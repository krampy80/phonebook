package common.exception;

import hu.hugo.common.ReasonCodeType;

public class BusinessException extends BaseException {

	
	public BusinessException(String message){
		super(message);
	}
	
	public BusinessException(ReasonCodeType reasonCode, String message){
		super(reasonCode,message);	
	}
}
