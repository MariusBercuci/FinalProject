package ro.sda.finalproject.backend.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Data
@Table(name = "product_images")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductImage implements Serializable {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
@Column(name = "product_image_id")
    private Long id;
    private String imageName;
    private String imageType;
    @Column(length = 50_000_000)
    private byte[] picByte;


}
