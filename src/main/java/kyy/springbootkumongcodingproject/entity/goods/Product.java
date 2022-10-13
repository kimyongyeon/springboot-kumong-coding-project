package kyy.springbootkumongcodingproject.entity.goods;

import kyy.springbootkumongcodingproject.entity.BaseEntity;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Table(name = "product")
public class Product extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_number")
    private Long number;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Integer price;

    @Column(nullable = false)
    private Integer stock;

    // 나는 주인이 아니다.
    @OneToOne(mappedBy = "product")
    @ToString.Exclude
    private ProductDetail productDetail;

    @ManyToOne
    @JoinColumn(name = "provider_id")
    @ToString.Exclude
    private Provider provider;

    @ManyToMany
    @ToString.Exclude
    private List<Producer> producerList = new ArrayList<>();

    public void addProducer(Producer producer) {
        this.producerList.add(producer);
    }
}