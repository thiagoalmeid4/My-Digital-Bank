package com.thiago.bank.err.exhandlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.thiago.bank.err.CpfInvalid;
import com.thiago.bank.err.InsufficientFunds;
import com.thiago.bank.err.KeyExists;
import com.thiago.bank.err.KeyInvalid;
import com.thiago.bank.err.PasswordInvalid;
import com.thiago.bank.err.UserExists;
import com.thiago.bank.err.UserNotFound;

@ControllerAdvice 
public class MyExceptionsHandlers extends ResponseEntityExceptionHandler{
    
    //Exceptions User
    @ExceptionHandler(CpfInvalid.class)
    public ResponseEntity<?> cpfInvalid (CpfInvalid e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST );    
    }

    @ExceptionHandler(PasswordInvalid.class)
    public ResponseEntity<?> passwordInvalid (PasswordInvalid e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserExists.class)
    public ResponseEntity<?> userExists (UserExists e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UserNotFound.class)
    public ResponseEntity<?> userNotFound(UserNotFound e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    //-----------------------------------------------------------------------------
    //Exceptions Transaction
    @ExceptionHandler(KeyExists.class)
    public ResponseEntity<?> keyExist (KeyExists e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(KeyInvalid.class)
    public ResponseEntity<?> keyInvalid (KeyInvalid e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    
    @ExceptionHandler(InsufficientFunds.class)
    public ResponseEntity<?> insufficientFunds (InsufficientFunds e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.PAYMENT_REQUIRED);
    }

}
