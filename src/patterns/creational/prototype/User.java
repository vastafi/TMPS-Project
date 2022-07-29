package patterns.creational.prototype;

import internal.models.Library;
import patterns.behavioral.command.SelectCommand;
import patterns.structural.adapter.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class User {
    private int id;
    private String name;
    private String surname;
    private int birthYear;
    private String university;
    private int accessGrade;

    private Library library;

    private ArrayList<Book> books = new ArrayList<>();

    public User() {
    }

    public User( String name, String surname, int birthYear, String university, int accessGrade, Library library) {
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
        this.university = university;
        this.accessGrade = accessGrade;
        this.library = library;
    }
    public User(int id, String name, String surname, int birthYear, String university, int accessGrade, Library library) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.birthYear = birthYear;
        this.university = university;
        this.accessGrade = accessGrade;
        this.library = library;
    }

    public User(User source){
        this();
        this.id = source.id;
        this.name = source.name;
        this.surname = source.surname;
        this.birthYear = source.birthYear;
        this.university = source.university;
        this.accessGrade = source.accessGrade;
        this.library = source.library;
    }

    public void toStringUser(){
        System.out.println("id: \t" + getId());
        System.out.println("Name: \t" + getName());
        System.out.println("Surname: \t" + getSurname());
        System.out.println("Birth year: \t" + getBirthYear());
        System.out.println("University: \t" + getUniversity());
        System.out.println("Access grade: \t" + getAccessGrade());
        System.out.println("Library: \t" + getLibrary().getName());
    }

    public void goToLibrary(){
        if (library.workTracker.isActiveWork()){

            List<Book> books = library.getAllBooks();
            Random random = new Random();
            int nr = random.nextInt(books.size());

            readBook(books.get(nr));

        }else {
            System.out.println("Library is closed, come back next time");
        }
    }

    public ArrayList<Book> stringToBooks(String strBooks){
        SelectCommand s = new SelectCommand();

        String str[] = strBooks.split(",");
        ArrayList<Book> all = new ArrayList<Book>();

        for(String book_id: str){
            all.add(s.getBookByID(Integer.parseInt(book_id)));
        }
        return all;
    }
    public void readBook(Book book){
        library.giveBook(book, this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public int getAccessGrade() {
        return accessGrade;
    }

    public void setAccessGrade(int accessGrade) {
        this.accessGrade = accessGrade;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public String getBooksID(){
        ArrayList<String > str = new ArrayList<>();
        for (Book book : books){
            str.add(book.getId()+"");
        }
        return str.toString();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }

    public abstract User clone();
}
