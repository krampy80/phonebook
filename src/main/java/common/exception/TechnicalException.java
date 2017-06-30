package common.exception;

import hu.hugo.common.ReasonCodeType;

public class TechnicalException extends BaseException{
	
	public TechnicalException(String message){
		super(message);
	}
	
	public TechnicalException(ReasonCodeType reasonCode, String message){
		super(reasonCode,message);	
	}

}
