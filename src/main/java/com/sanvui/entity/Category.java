package com.sanvui.entity;

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

@Setter @Getter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Indexed
@Table(name ="Category")
public class Category {

    @Id
    @Column(name = "ca_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int caId;

    @NonNull
    @Column(name = "ca_Name", columnDefinition = "nvarchar(50)" , unique = true)
    @NotNull(message = "Name can't be null.")
    @Size(min = 3, message = "{name.min}")
    @Field(termVector = TermVector.YES)
    private String caName;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    List<News> news;

    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true, fetch =FetchType.LAZY)
    List<Products> products;

    public Category(int caId, @NonNull String caName) {
        this.caId = caId;
        this.caName = caName;
    }

    @Override
    public String toString() {
        return "Catelory{" +
                "caId=" + caId +
                ", caName='" + caName + '\'' +
                '}';
    }
}
