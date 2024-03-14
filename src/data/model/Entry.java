package data.model;

import java.time.LocalDate;

public class Entry {
    private int entry_Id;
    private String title;
    private String body;
    private final LocalDate dateCreated = LocalDate.now();
    private String username;


  public Entry(int entry_Id, String title, String body,String username) {
      if (entry_Id <= 0 ){
          throw new IllegalArgumentException("Entry ID must be a positive integer");
      }
      if (title == null || title.isEmpty()){
          throw new IllegalArgumentException("Title cannot be null or empty");
      }
      if (body == null || body.isEmpty()){
          throw new IllegalArgumentException("Body cannot be null or empty");
      }

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

  public void setTitle(String title) {
      if(title == null || title.isEmpty()){
          throw new IllegalArgumentException("Title cannot be null or empty");
      }
      this.title = title;
  }

  public void setBody(String body) {
      if(body == null || body.isEmpty()){
          throw new IllegalArgumentException("Body cannot be null or empty");
      }
      this.body = body;
  }

  @Override
  public String toString(){
      return String.format("Entry ID: %d, Title: %s, Body: %s, Author: %s", entry_Id, title, body, username);
  }

}
