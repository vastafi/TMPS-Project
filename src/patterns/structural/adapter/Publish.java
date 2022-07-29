package patterns.structural.adapter;

public class Publish implements Periodicals{
    public String publisher;
    public int publishDate;

    public String getPublisher() {
        return publisher;
    }

    @Override
    public int getPublishYear() {
        return 0;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(int publishDate) {
        this.publishDate = publishDate;
    }
}
