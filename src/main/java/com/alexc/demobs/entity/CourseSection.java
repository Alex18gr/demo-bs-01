package com.alexc.demobs.entity;

import com.alexc.demobs.entity.Resource.Resource;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity @Data
@Table(name = "course_section")
public class CourseSection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_course_section")
    private int courseSectionId;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(
            cascade = {CascadeType.PERSIST,CascadeType.MERGE,
                    CascadeType.DETACH, CascadeType.REFRESH}
    )
    @JoinColumn(name = "course_id", nullable = false)
    protected Course course;

    @ManyToMany(fetch = FetchType.LAZY,
    cascade = {CascadeType.PERSIST, CascadeType.MERGE,
            CascadeType.DETACH, CascadeType.REFRESH})
    @JoinTable(
            name = "course_section_has_resource",
            joinColumns = {
                    @JoinColumn(name = "course_section_id_course_section"),
                    @JoinColumn(name = "course_section_course_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "resource_id"),
                    @JoinColumn(name = "resource_course_id")
            }

    )
    private List<Resource> resources;

}
