package patterns.structural.adapter;

import internal.models.Library;

public class Book extends Publish {
    private Library library;
    private int id, year, accessGrade;
    private String name, author, category;
    private boolean available;

    private int user_id;
    public Book(){}
    public Book(Library library, int id){
        this.library = library;
        this.id = id;
        this.year = 0;
        this.accessGrade = 0;
        this.name = "unknown";
        this.author = "unknown";
        this.category = "unknown";
        this.available = true;
    }

    //for creating model
    public Book(Library library, int year, int accessGrade, String name, String author, String category, String publisher, int publishDate) {
        this.name = name;
        this.author = author;
        this.year = year;
        this.category = category;
        this.accessGrade = accessGrade;
        this.publisher =publisher;
        this.publishDate = publishDate;
        this.library = library;
        this.available = true;
    }

    //for reading from database
    public Book(int id, String name, String author, String category, int year, int accessGrade, boolean available, String publisher, int publishDate, Library library, int user_id) {
        this.library = library;
        this.id = id;
        this.year = year;
        this.accessGrade = accessGrade;
        this.name = name;
        this.author = author;
        this.category = category;
        this.available = available;
        this.publisher =publisher;
        this.publishDate = publishDate;
        this.user_id = user_id;
    }

    public void toStringBook(){
        System.out.println("id: \t" + getId());
        System.out.println("name: \t" + getName());
        System.out.println("author: \t" + getAuthor());
        System.out.println("year: \t" + getYear());
        System.out.println("accessGrade: \t" + getAccessGrade());
        System.out.println("available: \t" + isAvailable());
        System.out.println("publisher: \t" + getPublisher());
        System.out.println("publishDate: \t" + getPublishDate());
        System.out.println("library_id: \t" + library.getId());
        if(!isAvailable()){
            System.out.println("user_id: \t" + user_id);
        }
    }

    public Book takeBook(){
        this.available = false;
        return this;
    }
    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAccessGrade() {
        return accessGrade;
    }

    public void setAccessGrade(int accessGrade) {
        this.accessGrade = accessGrade;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    //adapter part
    @Override
    public int getPublishYear() {
        return publishDate;
    }
}

