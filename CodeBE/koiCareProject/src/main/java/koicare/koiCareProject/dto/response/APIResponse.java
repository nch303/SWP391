package koicare.koiCareProject.dto.response;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

//Annotation dùng để ẩn cái giá trị null trong json result
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class APIResponse<T> {
    //đây là mã mặc định, chỉ ra những trường hợp đúng
    int code = 1000;
    String message;
    T result;
}
