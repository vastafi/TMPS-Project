package patterns.creational.prototype;

import internal.models.Library;

public class Student extends User{
    private String faculty;
    private String group;


    public Student( String name, String surname, int birthYear, String university, int accessGrade, Library library, String faculty, String group) {
        super(name, surname, birthYear, university, accessGrade, library);
        this.faculty = faculty;
        this.group = group;
    }
    public Student(int id, String name, String surname, int birthYear, String university, int accessGrade, Library library, String faculty, String group) {
        super(id, name, surname, birthYear, university, accessGrade, library);
        this.faculty = faculty;
        this.group = group;
    }
    public Student(){}

    Student(Student source){
        super(source);
        this.faculty = source.faculty;
        this.group = source.group;
    }
    @Override
    public void toStringUser(){
        System.out.println("id: \t" + getId());
        System.out.println("Name: \t" + getName());
        System.out.println("Surname: \t" + getSurname());
        System.out.println("Birth year: \t" + getBirthYear());
        System.out.println("University: \t" + getUniversity());
        System.out.println("Access grade: \t" + getAccessGrade());
        System.out.println("Library: \t" + getLibrary().getName());
        System.out.println("Faculty: \t" + getFaculty());
        System.out.println("Group: \t" + getGroup());
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }


    @Override
    public Student clone() {
        return new Student(this);
    }
}
