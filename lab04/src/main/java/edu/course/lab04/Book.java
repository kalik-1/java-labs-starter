package edu.course.lab04;
import java.util.Random;

public final class Book{

    private final String isbn;
    private final String title;
    Random rand = new Random();

    Book(String title, String isbn) {
        this.title = title;
        //перегруз конструктора класса бук с парсингом ISBN
        if(isbn.length() != 13) {
            throw new IllegalArgumentException("Input correct number of numbers for ISBN generator (total: 13)");
        }
        else{
            String assignee = ""; //по умолчанию null
            for(int i = 0; i < 13; i++) {
                if((i == 3) | (i == 4) | (i == 7) | (i == 12)) {
                    assignee += "-";
                }
                assignee += isbn.charAt(i);
            }
            this.isbn = assignee;
        }
    }

    Book(String title) {
        this.title = title;
        this.isbn = Integer.toString((int) (Math.random() * 900) + 100) + "-" + 
        Integer.toString(rand.nextInt(9)) + "-" + Integer.toString((int) (Math.random() * 900) + 100) + 
        "-" + Integer.toString((int) (Math.random() * 90000) + 10000) + "-" + Integer.toString(rand.nextInt(9));
    }

    public String getTitle() {
        return this.title;
    }

    public String getISBN() {
        return this.isbn;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((isbn == null) ? 0 : isbn.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Book other = (Book) obj;
        if (isbn == null) {
            if (other.isbn != null)
                return false;
        } else if (!isbn.equals(other.isbn))
            return false;
        return true;
    }

}

