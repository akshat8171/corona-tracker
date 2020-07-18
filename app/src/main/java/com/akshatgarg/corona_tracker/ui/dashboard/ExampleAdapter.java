package com.akshatgarg.corona_tracker.ui.dashboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.akshatgarg.corona_tracker.R;

import java.util.ArrayList;
import java.util.List;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> implements Filterable {
    private Context mContext;
    private ArrayList<example_item> mExampleList;
    private ArrayList<example_item> exampleListFull;
    private OnItemClickListener mListener;


    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }
    public ExampleAdapter(Context context, ArrayList<example_item> exampleList) {
        mContext = context;
        this.mExampleList = exampleList;
        exampleListFull = new ArrayList<>(exampleList);
    }
    @Override
    public ExampleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.single_row, parent, false);
        return new ExampleViewHolder(v);
    }
    @Override
    public void onBindViewHolder(ExampleViewHolder holder, int position) {
        example_item currentItem = mExampleList.get(position);

        String country = currentItem.get_country();
        int total_case = currentItem.total_case();
        int total_death = currentItem.total_death();
        int total_reco = currentItem.total_recover();
        int active = currentItem.active();

        holder.name.setText(country);
        holder.t_case.setText(""+total_case);
        holder.t_death.setText(""+total_death);
        holder.t_recov.setText(""+total_reco);
        holder.active.setText(""+active);
    }
    @Override
    public int getItemCount() {

        return mExampleList.size();
    }
    public class ExampleViewHolder extends RecyclerView.ViewHolder {
        public TextView name , t_case , t_death , t_recov , active ;

        public ExampleViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.state_name);
            t_case = itemView.findViewById(R.id.t_case);
            t_death = itemView.findViewById(R.id.t_death);
            t_recov = itemView.findViewById(R.id.t_recover);
            active = itemView.findViewById(R.id.active);

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
    @Override
    public Filter getFilter() {
        return exampleFilter;
    }
    private Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            List<example_item> filteredList = new ArrayList<>();
            if (constraint == null || constraint.length() == 0) {
                filteredList.addAll(exampleListFull);
            } else {
                String filterPattern = constraint.toString().toLowerCase().trim();
                for (example_item item : exampleListFull) {
                    if (item.get_country().toLowerCase().contains(filterPattern)) {
                        filteredList.add(item);
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = filteredList;
            return results;
        }
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            mExampleList.clear();
            mExampleList.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };
}