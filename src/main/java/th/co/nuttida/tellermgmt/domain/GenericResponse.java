package th.co.nuttida.tellermgmt.domain;

import lombok.Data;

@Data
public class GenericResponse {
    boolean valid;
    String message;
    
    public GenericResponse(boolean valid, String message) {
        this.valid = valid;
        this.message = message;
    }
}
