package com.mikulex;

import java.util.List;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Task {
    
    private @Id @GeneratedValue Long id;

    private String title;
    private String content;
    private List<String> checkList;

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

    public List<String> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<String> checkList) {
        this.checkList = checkList;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if(Objects.isNull(obj)) return false;
        if(!(obj instanceof Task)) return false;

        Task task = (Task) obj;
        return this.id == task.id
        && this.content.equals(task.content)
        && this.title.equals(task.title)
        && this.checkList.equals(task.checkList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, content, title, checkList);
    }

}
