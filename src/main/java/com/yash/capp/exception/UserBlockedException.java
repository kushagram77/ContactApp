package com.yash.capp.exception;

/**
 *
 * @author Kushagra Mishra
 */
public class UserBlockedException extends Exception{

    public UserBlockedException() {
    }

    public UserBlockedException(String errDesc) {
        super(errDesc);
    }    
}
