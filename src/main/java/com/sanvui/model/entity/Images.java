package com.sanvui.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author: VuiSK
 * @created: 07/11/2021-9:26 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@ToString
@Table(name = "images")
public class Images {
    @Id
    @Column(name = "image_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @NonNull
    @Column(name = "product_id")
    private int product_id;

    @Column(name = "image_name")
    @Size(min = 2, message = "{name.min}")
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "product_id", referencedColumnName = "product_id"
            , updatable = false, insertable = false)
    @JsonBackReference(value = "image")
    @ToString.Exclude
    private Products product;

}
