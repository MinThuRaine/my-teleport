package com.my.teleport.system.agent.service.application.handler;


import com.my.teleport.system.domain.data.ApiGlobalErrorResponse;
import com.my.teleport.system.domain.exception.GeneralPlatformDomainRuleException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class PlatformDomainRuleExceptionHandler {

    @ExceptionHandler(value = {GeneralPlatformDomainRuleException.class})
    public ResponseEntity<ApiGlobalErrorResponse> handleException(GeneralPlatformDomainRuleException exception) {
        log.error(exception.getGlobalisationMessageCode(), exception.getDefaultUserMessage(), exception);
        final ApiGlobalErrorResponse errorResponse = ApiGlobalErrorResponse.domainRuleViolation(
                exception.getGlobalisationMessageCode(), exception.getDefaultUserMessage());

        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorResponse);
    }

}
