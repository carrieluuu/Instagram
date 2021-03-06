package com.example.instagram.feed;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.instagram.Post;
import com.example.instagram.R;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.Date;

public class DetailActivity extends AppCompatActivity {

    public static final String TAG = "DetailActivity";

    Post post;

    private ImageView ivImage;
    private TextView tvUsername;
    private TextView tvDescription;
    private TextView tvTimestamp;
    private TextView tvUsernameSmall;
    private ImageView ivProfilePic;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ivImage = findViewById(R.id.ivImage);
        tvUsername = findViewById(R.id.tvUsername);
        tvDescription = findViewById(R.id.tvDescription);
        tvTimestamp = findViewById(R.id.tvTimestamp);
        tvUsernameSmall = findViewById(R.id.tvUsernameSmall);
        ivProfilePic = findViewById(R.id.ivProfilePic);


        post = (Post) Parcels.unwrap(getIntent().getParcelableExtra(Post.class.getSimpleName()));
        Log.d(TAG, String.format("Showing details for '%s'", post.getDescription()));

        tvUsername.setText(post.getUser().getUsername());
        tvDescription.setText(post.getDescription());
        Date createdAt = post.getCreatedAt();
        tvTimestamp.setText(Post.calculateTimeAgo(createdAt));
        tvUsernameSmall.setText(post.getUser().getUsername());

        ParseFile image = post.getImage();
        if (image != null) {
            Glide.with(this).load(image.getUrl()).into(ivImage);
        }

        ParseFile profilePic = post.getUser().getParseFile("profilePic");
        if (profilePic != null) {
            Glide.with(this).load(profilePic.getUrl()).apply(RequestOptions.circleCropTransform()).into(ivProfilePic);
        }

    }

}