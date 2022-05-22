package uz.pdp.task_2_9_1.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class RegisterDTO {
    @NotNull
    private String fullName;

    @NotNull
    private String email;

    @NotNull
    private String password;

}