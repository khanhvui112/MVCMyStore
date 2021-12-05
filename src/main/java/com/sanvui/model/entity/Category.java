package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sanvui.model.dto.CategoryDto;
import com.sanvui.model.dto.resp.CategoryRespDto;
import lombok.*;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.TermVector;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author: VuiSK
 * @created: 06/11/2021-1:38 PM
 * @mailto: sanvankhanh@gmail.com
 */

@SqlResultSetMapping(
        name = "categoryCustomResultMapping",
        classes = {
                @ConstructorResult(
                        targetClass = CategoryRespDto.class,
                        columns = {
                                @ColumnResult(name = "ca_id", type = Integer.class),
                                @ColumnResult(name = "ca_name", type = String.class),
                                @ColumnResult(name = "ca_link", type = String.class),
                        }
                )
        }
)
@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Indexed
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "ca_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int caId;

    @Column(name = "ca_name", columnDefinition = "nvarchar(50)", unique = true)
    @NotNull(message = "Name can't be null.")
    @Size(min = 3, message = "{name.min}")
    @Field(termVector = TermVector.YES)
    private String caName;

    @Column(name = "ca_link", columnDefinition = "varchar(100)", unique = true)
    @NotNull(message = "Name can't be null.")
    @Size(min = 3, message = "{name.min}")
    private String caLink;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "news")
    @ToString.Exclude
    private List<News> news;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL
            , orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference(value = "category")
    @ToString.Exclude
    private List<Products> products;

}
