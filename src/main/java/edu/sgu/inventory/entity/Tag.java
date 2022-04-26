package edu.sgu.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Data
@Table(name = "tag")
@Entity
public class Tag {
  @Id
  @Column(name = "tag_id")
  private  String tagId;
  @JoinColumn(referencedColumnName = "product_id",name = "product_id")
  @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
  @JsonIgnore
  private  Product product;
  private  Integer count;
  private String rssi;
}
