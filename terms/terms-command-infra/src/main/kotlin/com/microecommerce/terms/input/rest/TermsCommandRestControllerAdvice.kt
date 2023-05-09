package com.microecommerce.terms.input.rest

import com.microecommerce.terms.openapi.model.ErrorResponse
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.time.ZonedDateTime

@ControllerAdvice
class TermsCommandRestControllerAdvice {

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        request: HttpServletRequest,
        exception: IllegalArgumentException
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorResponse(
                timestamp = ZonedDateTime.now().toOffsetDateTime(),
                status = HttpStatus.NOT_FOUND.value(),
                error = HttpStatus.NOT_FOUND.reasonPhrase,
                message = exception.message ?: "Not Found",
                path = request.servletPath
            )
        )
    }

    @ExceptionHandler(IllegalStateException::class)
    fun handleIllegalStateException(
        request: HttpServletRequest,
        exception: IllegalStateException
    ): ResponseEntity<ErrorResponse> {
        return ResponseEntity.badRequest().body(
            ErrorResponse(
                timestamp = ZonedDateTime.now().toOffsetDateTime(),
                status = HttpStatus.BAD_REQUEST.value(),
                error = HttpStatus.BAD_REQUEST.reasonPhrase,
                message = exception.message ?: "Bad Request",
                path = request.servletPath
            )
        )
    }
}
