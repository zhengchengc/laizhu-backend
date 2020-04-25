package com.gorixl.laizhumainbackend.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "blogs")
public class Blog extends AuditModel{

    @Id
    @GeneratedValue(generator = "blog_generator")
    @SequenceGenerator(
            name = "blog_generator",
            sequenceName = "blog_sequence",
            initialValue = 0
    )
    private Long id;

    @NotNull
    @Size(min = 1, max = 30)
    private String title;

    @Column(columnDefinition = "text")
    private String context;
}
