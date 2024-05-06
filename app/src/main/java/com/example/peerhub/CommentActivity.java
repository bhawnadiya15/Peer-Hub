package com.example.peerhub;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CommentActivity extends AppCompatActivity {
    private EditText editTextComment;
    private Button buttonAddComment;
    private RecyclerView recyclerViewComments;
    private View imageViewNoComments; // Corrected variable name
    private List<String> commentList;
    private CommentAdapter commentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_page);

        editTextComment = findViewById(R.id.edit_comment);
        buttonAddComment = findViewById(R.id.submit_comment);
        recyclerViewComments = findViewById(R.id.recycler_view_comments);
        imageViewNoComments = findViewById(R.id.no_comments); // Corrected view ID
        commentList = new ArrayList<>();
        commentAdapter = new CommentAdapter(commentList);
        recyclerViewComments.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewComments.setAdapter(commentAdapter);

        // Initially, show the background image if commentList is empty
        updateBackgroundImageVisibility();

        buttonAddComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String comment = editTextComment.getText().toString().trim();
                if (!comment.isEmpty()) {
                    addComment(comment);
                    editTextComment.setText("");
                }
            }
        });
    }

    private void addComment(String comment) {
        commentList.add(comment);
        commentAdapter.notifyItemInserted(commentList.size() - 1);
        recyclerViewComments.scrollToPosition(commentList.size() - 1);

        // After adding a comment, update the visibility of the background image
        updateBackgroundImageVisibility();
    }

    // Method to update the visibility of the background image based on the comment list
    private void updateBackgroundImageVisibility() {
        if (commentList.isEmpty()) {
            imageViewNoComments.setVisibility(View.VISIBLE); // Show background image
        } else {
            imageViewNoComments.setVisibility(View.GONE); // Hide background image
        }
    }
}