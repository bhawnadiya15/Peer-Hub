package com.example.peerhub;

public class Comment {
    private String postId;
    private String username;
    private String text;

    public Comment() {
        // Required for Firebase
    }

    public Comment(String postId, String username, String text) {
        this.postId = postId;
        this.username = username;
        this.text = text;
    }

    public String getPostId() {
        return postId;
    }

    public String getUsername() {
        return username;
    }

    public String getText() {
        return text;
    }
}
