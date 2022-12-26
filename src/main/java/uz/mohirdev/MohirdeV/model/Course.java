package uz.mohirdev.MohirdeV.model;

public class Course {
    private Long id;

    private String name;

    public Course(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Course() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
