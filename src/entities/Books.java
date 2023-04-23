/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entities;

/**
 * 
 * @author Maxwell
 * @author Renan
 */
public class Books {

    private String id;
    private String authorFirstName;
    private String authorLastName;
    private String title;
    private String genre;

    public Books(String id, String authorFirstName, String authorLastName, String title, String genre) {
        this.id = id;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
        this.title = title;
        this.genre = genre;
    }

    // getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public void setAuthorFirstName(String authorFirstName) {
        this.authorFirstName = authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }

    public void setAuthorLastName(String authorLastName) {
        this.authorLastName = authorLastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    // toString method
    @Override
    public String toString() {
        return "Book{"
                + "id='" + id + '\''
                + ", authorFirstName='" + authorFirstName + '\''
                + ", authorLastName='" + authorLastName + '\''
                + ", title='" + title + '\''
                + ", genre='" + genre + '\''
                + '}';
    }

}
