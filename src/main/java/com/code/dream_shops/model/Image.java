package com.code.dream_shops.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.ManyToAny;

import java.sql.Blob;
import java.sql.Types;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String fileName;
    private String fileType;
    private String downloadUrl;

    @Lob
    @Column(name = "image")
    @JdbcTypeCode(Types.BINARY)
    private byte[] image;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product; //m:1
}
