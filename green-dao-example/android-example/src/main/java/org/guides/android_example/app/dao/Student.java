package org.guides.android_example.app.dao;

// THIS CODE IS GENERATED BY greenDAO, EDIT ONLY INSIDE THE "KEEP"-SECTIONS

// KEEP INCLUDES - put your custom includes here
// KEEP INCLUDES END
/**
 * Entity mapped to table STUDENT.
 */
public class Student implements java.io.Serializable {

    /** Not-null value. */
    private String name;
    private long id;
    private Integer age;

    // KEEP FIELDS - put your custom fields here
    // KEEP FIELDS END

    public Student() {
    }

    public Student(long id) {
        this.id = id;
    }

    public Student(String name, long id, Integer age) {
        this.name = name;
        this.id = id;
        this.age = age;
    }

    /** Not-null value. */
    public String getName() {
        return name;
    }

    /** Not-null value; ensure this value is available before it is saved to the database. */
    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    // KEEP METHODS - put your custom methods here
    // KEEP METHODS END

}
