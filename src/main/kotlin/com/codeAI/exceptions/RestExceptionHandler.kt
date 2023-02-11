package com.codeAI.exceptions

import org.hibernate.HibernateException
import org.hibernate.exception.ConstraintViolationException
import javax.ws.rs.core.Response
import javax.ws.rs.ext.ExceptionMapper
import javax.ws.rs.ext.Provider

@Provider
class RestExceptionHandler : ExceptionMapper<HibernateException> {
    override fun toResponse(exception: HibernateException?): Response {
        if (hasExceptionInChain(exception, ConstraintViolationException::class.java)) {
            val constraintViolationException = exception as ConstraintViolationException
            if (hasPostgresErrorCode(constraintViolationException, "23505")) {
                return Response.status(Response.Status.CONFLICT).build()
            }
        }
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build()

    }
}

fun hasExceptionInChain(throwable: Throwable?, exceptionClass: Class<ConstraintViolationException>): Boolean {
    return throwable != null && (exceptionClass.isAssignableFrom(throwable.javaClass) || hasExceptionInChain(
        throwable.cause,
        exceptionClass
    ))
}

fun hasPostgresErrorCode(throwable: Throwable?, errorCode: String): Boolean {
    return throwable != null && (errorCode == throwable.message || hasPostgresErrorCode(throwable.cause, errorCode))
}

