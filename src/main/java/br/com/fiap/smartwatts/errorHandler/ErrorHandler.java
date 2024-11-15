package br.com.fiap.smartwatts.errorHandler;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.naming.ServiceUnavailableException;

@ControllerAdvice
public class ErrorHandler {

    // Tratamento para erro 404 - Página não encontrada
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoHandlerFoundException.class)
    public String handleNotFound(NoHandlerFoundException ex, Model model) {
        model.addAttribute("message", "A página que você procura não foi encontrada.");
        return "errors/404"; // Nome da view de erro 404
    }


    // Tratamento para erro 403 - Proibido (sem permissão)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public String handleAccessDeniedException(AccessDeniedException ex, Model model) {
        model.addAttribute("message", "Você não tem permissão para acessar este recurso.");
        return "errors/403"; // Nome da view de erro 403
    }

    // Tratamento para erro 405 - Método não permitido
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handleMethodNotAllowed(HttpRequestMethodNotSupportedException ex, Model model) {
        model.addAttribute("message", "Método HTTP não permitido para este recurso.");
        return "errors/405"; // Nome da view de erro 405
    }


    // Tratamento para erro 500 - Erro interno no servidor
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleInternalServerError(Exception ex, Model model) {
        model.addAttribute("message", "Ocorreu um erro inesperado no servidor.");
        return "errors/500"; // Nome da view de erro 500
    }

    // Tratamento para erro 503 - Serviço indisponível
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    @ExceptionHandler(ServiceUnavailableException.class) // Use a exceção apropriada para sua aplicação
    public String handleServiceUnavailableException(ServiceUnavailableException ex, Model model) {
        model.addAttribute("message", "O serviço está temporariamente indisponível.");
        return "errors/503"; // Nome da view de erro 503
    }
}
