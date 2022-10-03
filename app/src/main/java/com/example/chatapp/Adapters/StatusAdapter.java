package com.example.chatapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatapp.Models.User;
import com.example.chatapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class StatusAdapter extends RecyclerView.Adapter<StatusAdapter.ViewHolder> implements Filterable
{

    ArrayList<User> list = new ArrayList<>();
    ArrayList<User> example;

    Context context;
    final FirebaseDatabase database = FirebaseDatabase.getInstance();

    public StatusAdapter(ArrayList<User> list, Context context)

    {
        this.list = list;
        this.context = context;
        example = new ArrayList<User>(list);
    }


    public ArrayList<User> getList() {
        return list;
    }

    public void setList(ArrayList<User> list) {
        this.list = list;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Context getContext() {
        return context;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_contact, parent, false);

        return new StatusAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        User user = list.get(position);
        Picasso.get().load(user.getProfilepic()).placeholder(R.drawable.ic_user).into(holder.image);
        holder.userName.setText(user.getUserName());




        holder.button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                database.getReference().child("Users").child(FirebaseAuth.getInstance().getUid()).child("Friends").
                        child(user.getUserId()).setValue(user.getUserId());
                database.getReference().child("Users").child(user.getUserId()).child("Friends").
                        child(FirebaseAuth.getInstance().getUid()).setValue(FirebaseAuth.getInstance().getUid());

                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter()
    {
        return SearchFilter;
    }

    private Filter SearchFilter  = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<User> filteredList = new ArrayList<User>();

            if (constraint == null || constraint.length() ==0)
            {
                filteredList.addAll(example);
            }
            else
            {
                String filterPattern = constraint.toString().toLowerCase().trim();

                for (User item : example)
                {
                    if (item.getUserName().toLowerCase().contains(filterPattern))
                    {
                        filteredList.add(item);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values= filteredList;

            return  results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
                list.clear();
                list.addAll((ArrayList) results.values);
                notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder
    {

        ImageView image;
        TextView userName;
        Button button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profile_image);
            userName = itemView.findViewById(R.id.username);
            button = itemView.findViewById(R.id.addFriend);
        }




    }


}
