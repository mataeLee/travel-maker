package kr.sm.itaewon.travelmaker.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Article{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="article_id")
    private long articleId;

    @NotNull
    @Column(name="location_id")
    private long locationId;

    @NotNull
    @Column(name="customer_id")
    private long customerId;

    @Column(name="image")
    private String image;

    @Column(name="summary")
    private String summary;

//    @Column(name="link_id")
//    private long linkId;
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @ManyToOne(targetEntity = Link.class, fetch = FetchType.LAZY)
    private Link link;

    @Column(name="reg_date")
    private Timestamp reg_date;

}