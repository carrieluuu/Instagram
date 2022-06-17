package com.example.instagram.adapters;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.instagram.feed.DetailActivity;
import com.example.instagram.Post;
import com.example.instagram.R;
import com.parse.ParseFile;

import org.parceler.Parcels;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {
    public static final String TAG = "PostsAdapter";

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvUsername;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvUsernameSmall;
        private ImageView ivProfilePic;



        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername = itemView.findViewById(R.id.tvUsername);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvUsernameSmall = itemView.findViewById(R.id.tvUsernameSmall);
            ivProfilePic = itemView.findViewById(R.id.ivProfilePic);
            itemView.setOnClickListener(this);

        }

        public void bind(Post post) {
            // Bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());
            tvUsernameSmall.setText(post.getUser().getUsername());

            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }

            ParseFile profilePic = post.getUser().getParseFile("profilePic");
            if (profilePic != null) {
                Glide.with(context).load(profilePic.getUrl()).apply(RequestOptions.circleCropTransform()).into(ivProfilePic);
            }
        }

        public void onClick(View v) {
            Log.i(TAG, "Clicked post");
            // gets item position
            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                Post post = posts.get(position);
                // create intent for the new activity
                Intent i = new Intent(context, DetailActivity.class);
                // serialize the movie using parceler, use its short name as a key
                i.putExtra(Post.class.getSimpleName(), Parcels.wrap(post));
                // show the activity
                context.startActivity(i);
            }
        }
    }
}