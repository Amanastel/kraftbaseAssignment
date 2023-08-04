package com.kraftbase.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyErrorDetails> customerExceptionHandler(CustomerException ce, WebRequest req){
				
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(ce.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
	}


    @ExceptionHandler(ProductException.class)
    public ResponseEntity<MyErrorDetails> productExceptionHandler(ProductException ce, WebRequest req){

        MyErrorDetails err= new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ce.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(OrdersException.class)
    public ResponseEntity<MyErrorDetails> orderExceptionHandler(OrdersException ce, WebRequest req){

        MyErrorDetails err= new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ce.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(WalletException.class)
    public ResponseEntity<MyErrorDetails> walletExceptionHandler(WalletException ce, WebRequest req){

        MyErrorDetails err= new MyErrorDetails();
        err.setTimestamp(LocalDateTime.now());
        err.setMessage(ce.getMessage());
        err.setDetails(req.getDescription(false));

        return new ResponseEntity<MyErrorDetails>(err, HttpStatus.BAD_REQUEST);
    }


	@ExceptionHandler(Exception.class)
	public ResponseEntity<MyErrorDetails> otherExceptionHandler(Exception se, WebRequest req){
		
		MyErrorDetails err= new MyErrorDetails();
			err.setTimestamp(LocalDateTime.now());
			err.setMessage(se.getMessage());
			err.setDetails(req.getDescription(false));
				
		return new ResponseEntity<MyErrorDetails>(err, HttpStatus.INTERNAL_SERVER_ERROR);	
	}

}
