package com.accenture.transactionservice.exception.validation;

import javax.persistence.criteria.CriteriaBuilder;

public class InvalidDateRangeException extends ValidationException{

    private static Integer CODE = 4;

    private static String INVALID_DATE_RANGE_MSG = "La fecha del pago no es actual o proxima";

    public InvalidDateRangeException() {super(INVALID_DATE_RANGE_MSG, CODE);}

}
