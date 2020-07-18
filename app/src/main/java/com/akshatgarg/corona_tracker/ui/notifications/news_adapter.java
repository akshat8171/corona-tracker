package com.akshatgarg.corona_tracker.ui.notifications;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.akshatgarg.corona_tracker.R;
import com.akshatgarg.corona_tracker.ui.dashboard.ExampleAdapter;
import com.akshatgarg.corona_tracker.ui.dashboard.example_item;

import java.util.ArrayList;
import java.util.List;

public class news_adapter extends RecyclerView.Adapter<news_adapter.ExampleViewHolder>{
    private Context mContext;
    private ArrayList<news_item> mExampleList;
    private ExampleAdapter.OnItemClickListener mListener;

    public news_adapter(Context context, ArrayList<news_item> exampleList) {
        mContext = context;
        mExampleList = exampleList;
    }
    @Override
    public news_adapter.ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new news_adapter.ExampleViewHolder(v);
    }
    @Override
    public void onBindViewHolder(news_adapter.ExampleViewHolder holder, int position) {
        news_item currentItem = mExampleList.get(position);

        String news_item = currentItem.get_news();

        holder.aa.setText(news_item);
    }
    @Override
    public int getItemCount() {

        return mExampleList.size();
    }
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView aa;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            aa = itemView.findViewById(R.id.news);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            mListener.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
