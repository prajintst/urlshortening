package com.techgentsia.shorturl.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.URL;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

@Entity
@Setter
@Getter
@Table(name = "short_urls")
public class ShortUrl {

    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    @NotBlank
    @URL
    @Column(name = "url", unique = true,length = 2048)
    private String url;

    @Column(name = "is_alive")
    @JsonIgnore
    private Boolean isAlive = true;

    @Transient
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String shortUrl;
}