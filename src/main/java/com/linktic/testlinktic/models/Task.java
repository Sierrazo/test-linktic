package com.linktic.testlinktic.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "TASK")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID")
    private Long id;

    @Basic
    @Column(name = "TITLE")
    private String title;

    @Basic
    @Column(name = "DESCRIPTION")
    private String description;

    @Basic
    @Column(name = "DUE_DATE")
    private String dueDate;

    @Basic
    @Column(name = "STATUS")
    private String status;
}
