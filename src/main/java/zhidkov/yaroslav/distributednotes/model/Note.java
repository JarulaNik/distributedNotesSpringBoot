package zhidkov.yaroslav.distributednotes.model;


import jakarta.persistence.*;

import lombok.*;


@Entity
@Table(name = "note")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    public String title;

    public String content;

    public String image;
}