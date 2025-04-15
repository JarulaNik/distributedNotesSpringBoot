package zhidkov.yaroslav.distributednotes.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;

import javax.annotation.processing.Generated;

@Getter
@Setter
@Data
public class User {

    @Generated(value = "UUID")
    @UUID
    @NotNull
    private final String userId;

//    private String userName;
//
//    @Email
//    private String email;
//
//    private String password;
//
//    private String role;
//
//    private String createdAt;
//
//    private boolean isBlocked;
}
