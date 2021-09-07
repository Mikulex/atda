package com.mikulex.atda;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {

    private @Id @GeneratedValue Long id;

    private String title;
    private String content;
    private boolean finished;

    public Task() {
    }

    public Task(String title, String content) {
        this.title = title;
        this.content = content;
        this.finished = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isFinished(){
        return finished;
    }

    public void setFinished(boolean finished){
        this.finished = finished;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (Objects.isNull(obj))
            return false;
        if (!(obj instanceof Task))
            return false;

        Task task = (Task) obj;
        return this.id == task.id && this.content.equals(task.content) && this.title.equals(task.title) && this.finished == task.finished;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, title);
    }

}
