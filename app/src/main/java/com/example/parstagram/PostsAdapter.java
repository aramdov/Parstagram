package com.example.parstagram;

import androidx.annotation.NonNull;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;


public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // returns a view then take the view and set it inside the viewholder
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position); // get the post location (index)
        // take viewholder passed in and bind the data of the post to the viewholder
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvUsername;
        private TextView tvDescription;
        private ImageView ivImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // in the viewholder constructor we identify the views according to an id inside
            tvUsername = itemView.findViewById(R.id.tvUsername);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivImage = itemView.findViewById(R.id.ivImage);

        }

        public void bind(Post post) {
            // bind the post data to the view elements. need access to view elements in order to do it with references
            tvDescription.setText(post.getDescription());
            tvUsername.setText(post.getUser().getUsername());

            ParseFile image = post.getImage();
            if (image != null);
                // error checking to make sure the image has a value
                Glide.with(context).load(post.getImage().getUrl()).into(ivImage);
        }
    }
    
    
    
    
    
    
    
}
