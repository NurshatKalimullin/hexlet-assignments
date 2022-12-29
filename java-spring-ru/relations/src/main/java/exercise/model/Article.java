package exercise.model;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.GeneratedValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.GenerationType;

// BEGIN
@Entity
@Getter
@Setter
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;  //первичный ключ, должен генерироваться автоматически
    private String name;  //название статьи
    @Lob
    private String body;  //содержание статьи
    //содержит связанную сущность Category, категорию, которой принадлежит статья.
    //Статья связанна с категорией связью "многие к одному".
    @ManyToOne
    private Category category;
}
// END
