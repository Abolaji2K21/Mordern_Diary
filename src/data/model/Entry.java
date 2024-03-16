package data.model;

import java.time.LocalDateTime;

public class Entry {
    private int entry_Id;
    private String title;
    private String body;
    private LocalDateTime dateCreated = LocalDateTime.now();
    private String username;

    public Entry() {
    }

  public Entry(int entry_Id, String title, String body,String username) {
      this.entry_Id = entry_Id;
      this.title = title;
      this.body = body;
      this.username = username;
  }

  public int getEntry_Id() {
      return entry_Id;
  }

  public String getTitle() {
      return title;
  }

  public String getBody() {
      return body;
  }

    public void setEntry_Id(int entry_Id) {
        this.entry_Id = entry_Id;
    }

    public void setTitle(String title) {
      this.title = title;
  }

  public void setBody(String body) {
      this.body = body;
  }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
  public String toString(){
      return String.format("Entry ID: %d, Title: %s, Body: %s, Author: %s", entry_Id, title, body, username);
  }

}
