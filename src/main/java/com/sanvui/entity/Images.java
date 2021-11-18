package com.sanvui.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Size;

/**
 * @author: VuiSK
 * @created: 07/11/2021-9:26 AM
 * @mailto: sanvankhanh@gmail.com
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @RequiredArgsConstructor
@Entity
@Table(name = "Images")
public class Images {
    @Id
    @Column(name = "image_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imageId;

    @NonNull
    @Column(name = "product_Id")
    private int product_Id;

    @Column(name = "image_Name")
    @Size(min = 2, message = "{name.min}")
    private String imageName;

    @ManyToOne
    @JoinColumn(name = "product_Id", referencedColumnName = "product_Id"
            ,updatable = false, insertable = false)
    private Products product;

    @Override
    public String toString() {
        return "Images{" +
                "imageId=" + imageId +
                ", product_Id=" + product_Id +
                ", imageName='" + imageName + '\'' +
                '}';
    }
}
