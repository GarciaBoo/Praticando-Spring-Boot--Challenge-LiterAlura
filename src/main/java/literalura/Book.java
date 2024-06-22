package literalura;

import java.util.List;

public class Book {
    private String title;
    private List<Author> authors;
    private String language; // Idioma do livro
    private int downloadCount; // Contador de downloads

    public static class Author {
        private String name;
        private int birthYear;
        private int deathYear;

        public Author() {
        }

        public Author(String name, int birthYear, int deathYear) {
            this.name = name;
            this.birthYear = birthYear;
            this.deathYear = deathYear;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getBirthYear() {
            return birthYear;
        }

        public void setBirthYear(int birthYear) {
            this.birthYear = birthYear;
        }

        public int getDeathYear() {
            return deathYear;
        }

        public void setDeathYear(int deathYear) {
            this.deathYear = deathYear;
        }
    }

    public Book() {
    }

    public Book(String title, List<Author> authors, String language, int downloadCount) {
        this.title = title;
        this.authors = authors;
        this.language = language;
        this.downloadCount = downloadCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getDownloadCount() {
        return downloadCount;
    }

    public void setDownloadCount(int downloadCount) {
        this.downloadCount = downloadCount;
    }
}