package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author: VuiSK
 * @created: 06/11/2021-1:53 PM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Indexed
@ToString
@Table(name = "news")
public class News {

    @Id
    @Column(name = "news_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int newsId;

    @NonNull
    @Column(name = "ca_id",nullable = false)
    private int ca_Id;

    @NonNull
    @NotNull
    @Column(name = "date", columnDefinition = "datetime")
    private LocalDateTime date;

    @NonNull
    @Column( name = "title", columnDefinition = "nvarchar(200)")
    @Size(max = 200, message = "{name.max}")
    @Field(termVector = TermVector.YES)
    private String title;

    @NonNull
    @Field(termVector = TermVector.YES)
    @Column( name = "description", columnDefinition = "nvarchar(2000)")
    @Size(max = 2000, message = "{name.max}")
    private String description;

    @NonNull
    @Column( name = "comments", columnDefinition = "nvarchar(200)")
    private String comments;

//    mapper
    @ManyToOne
    @JoinColumn(name ="ca_id", referencedColumnName = "ca_id"
            , insertable = false, updatable = false)
    @JsonBackReference(value = "news")
    @ToString.Exclude
    private Category category;

}
