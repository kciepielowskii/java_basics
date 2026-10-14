package lesson6;

public class Book {
    private final String title;
    private final String author;
    private int pages;

    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        setNumberOfPages(pages);
    }
    public void setNumberOfPages(int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("The number of pages must be positive");
        }
        this.pages = pages;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getPages() { return pages; }

    @Override
    public String toString() {
        return "Title: " + title + "\nAuthor: " + author + "\nPages: " + pages + "\n";
    }
}
