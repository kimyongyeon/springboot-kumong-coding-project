package kyy.springbootkumongcodingproject.controller.common;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class ErrorController {

    class ErrorResponse {
        String code;
        String msg;

        @Override
        public String toString() {
            return "ErrorResponse{" +
                    "code='" + code + '\'' +
                    ", msg='" + msg + '\'' +
                    '}';
        }
    }

    @ExceptionHandler(NullPointerException.class)
    public String nullex(Exception e) {
        System.err.println(e.getMessage());
        return "null Error 담당자에게 연락 바랍니다.";
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegal(Exception e) {
        System.err.println(e.getMessage());
        return "illegal Error 담당자에게 연락 바랍니다.";
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<String> bindException(Exception e) {
        System.err.println(e.getMessage());
        return new ResponseEntity<String>("bindException 오류 입니다.", new HttpHeaders(), 500);
    }
}
