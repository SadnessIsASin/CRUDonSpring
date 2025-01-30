package com.example.whats_happened.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UuidGenerator;

import java.sql.Types;
import java.time.LocalDateTime;
import java.util.UUID;


@Entity
@Table(name = "incidents2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Incident {


    @Id
    @UuidGenerator
    @JdbcTypeCode(Types.VARCHAR)
    private UUID id;

    private String name;

    private String description;

    private String status;

    private String location;

    private String type;

    private LocalDateTime createdAt;

    private LocalDateTime closedAt;


}
