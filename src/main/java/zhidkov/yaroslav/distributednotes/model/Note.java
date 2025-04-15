package zhidkov.yaroslav.distributednotes.model;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;


@Getter
@Setter
public class Note {

    @Id
    public final String id;

    @NotBlank
    public String title;

    @NotBlank
    public String content;

    public Note(String id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

}