package com.programmingtechie.testservice.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "test_generator")
    @SequenceGenerator(name = "test_generator", sequenceName = "test_seq", allocationSize = 1)
    private Long id;
    private String title;
    private String shortDescription;
    private Boolean enable;
    private int duration;
}
