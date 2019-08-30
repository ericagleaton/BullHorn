/**
 * ou are to write an application that is kind of like twitter. It will allow a user to
 post a message, and everyone else to see all the messages that have been posted.

 It will not allow for following, retweets, direct messages or profiles. It is a
 very basic application, but one that proves what you have learned.

 Build an application that allows you to add MESSAGEs, list them and view them (like Lesson 10)

 MESSAGEs should look like this:

 MESSAGE
 long id
 String content
 String posteddate (If you'd like, you can make this data type 'Date')
 String sentby

 the home page ("/") path should point to a list of all MESSAGES

 every page (or template) should have link (or button) to the add path
 ("/add") which is the new MESSAGE form
 */
package com.example.demo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String content;

    @NotNull
    private String posteddate;

    @NotNull
    private String sentby;

    @NotNull
    private String file;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPosteddate() {
        return posteddate;
    }

    public void setPosteddate(String posteddate) {
        this.posteddate = posteddate;
    }

    public String getSentby() {
        return sentby;
    }

    public void setSentby(String sentby) {
        this.sentby = sentby;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}

