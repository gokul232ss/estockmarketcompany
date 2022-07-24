package com.fse.estockmarketcompany.config;


import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fse.estockmarketcompany.exception.CommonInternalException;
import com.fse.estockmarketcompany.model.response.ErrResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonConfig {

    /**
     * method exception
     * defining response for all user-defined exception across this microservice
     *
     * @param exception captured exception object
     * @return ResponseEntity object
     */
    @ExceptionHandler({
            CommonInternalException.class})
    public ResponseEntity<ErrResponse> exception(Exception exception) {
        String res = "Invalid";
        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        if (exception instanceof CommonInternalException) {
            httpStatus = ((CommonInternalException) exception).getCode();
            res = ((CommonInternalException) exception).getMessageLocal();
        }
        return new ResponseEntity<>(new ErrResponse(httpStatus.value() + "", res), httpStatus);
    }

    /**
     * Bean for Object Mapper
     *
     * @return ObjectMapper
     */
    @Bean
    public ObjectMapper getObjectMapper() {
        ObjectMapper ob = new ObjectMapper();
        ob.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        return ob;
    }

}

