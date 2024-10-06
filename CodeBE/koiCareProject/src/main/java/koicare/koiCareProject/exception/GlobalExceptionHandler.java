package koicare.koiCareProject.exception;


import jakarta.validation.ConstraintViolationException;
import koicare.koiCareProject.dto.response.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    //dùng để bắt các Exception ngẫu nhiên chưa setup
//    @ExceptionHandler(value = Exception.class)
//    ResponseEntity<APIResponse> handlingException(RuntimeException exception) {
//        APIResponse apiResponse = new APIResponse();
//
//        //set-up code và message cho Error
//        apiResponse.setCode(ErrorCode.UNCATEGORIZED_EXCEPTION.getCode());
//        apiResponse.setMessage(ErrorCode.UNCATEGORIZED_EXCEPTION.getMessage());
//
//        return ResponseEntity.badRequest().body(apiResponse);
//    }

    //AppException dùng để format các lỗi đã được throw trong một Exception cụ thể, khác với RuntimeException
    @ExceptionHandler(value = AppException.class)
    ResponseEntity<APIResponse> handlingAppException(AppException exception){
        //ví dụ trong Account khi throw AppException(Mã code) ta sẽ vào ErrorCode để xem code đó là lỗi nào
        ErrorCode errorCode = exception.getErrorCode();
        APIResponse apiResponse = new APIResponse();

        //set-up code và message cho Error
        apiResponse.setCode(errorCode.getCode());
        apiResponse.setMessage(errorCode.getMessage());

        return ResponseEntity.badRequest().body(apiResponse);
    }



    //trả về lỗi được Valid trong EntityRequest
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleValidation(MethodArgumentNotValidException exception){

        String message = "";

        for(FieldError fieldError: exception.getBindingResult().getFieldErrors()){
            message += fieldError.getDefaultMessage() + "\n";
        }

        return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
    }

    // Bắt lỗi ConstraintViolationException
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<String> handleConstraintViolation(ConstraintViolationException exception) {
        String message = "";

        // Lấy tất cả các vi phạm constraint và tạo thông điệp
        List<String> errorMessages = exception.getConstraintViolations()
                .stream()
                .map(violation -> violation.getMessage())
                .collect(Collectors.toList());

        // Gộp tất cả thông điệp lỗi vào một chuỗi
        message += String.join("\n", errorMessages);

        return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
    }
}
