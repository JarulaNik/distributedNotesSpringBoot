package zhidkov.yaroslav.distributednotes.model;


import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Note {

    public String id;

    public String title;

    public String content;

    public String image;

    public Note(String id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

}