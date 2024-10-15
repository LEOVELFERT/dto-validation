package com.dto_validation.dto_validation;

import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static com.dto_validation.dto_validation.constants.ExceptionConstants.ERRORS;
@Component
public class ErrorMapper {
    public Map<String,Object> getListOfErrors(List<String> errors){
        Map<String,Object> errorMap=new LinkedHashMap<>();
        errorMap.put(ERRORS,errors);
        return errorMap;
    }
}
