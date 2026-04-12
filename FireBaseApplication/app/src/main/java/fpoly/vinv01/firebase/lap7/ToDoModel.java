package fpoly.vinv01.firebase.lap7;

import java.util.HashMap;

public class ToDoModel {
    private String id;
    private String title;
    private String comment;
    private String date;
    private String type;
    private int status;

    public ToDoModel() {
    }

    public ToDoModel(String id, String title, String comment, String date, String type, int status) {
        this.id = id;
        this.title = title;
        this.comment = comment;
        this.date = date;
        this.type = type;
        this.status = status;
    }

    public HashMap<String, Object> coverHashMap() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        hashMap.put("title", title);
        hashMap.put("comment", comment);
        hashMap.put("date", date);
        hashMap.put("type", type);
        hashMap.put("status", status);
        return hashMap;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
