package com.meet.covitrack;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.NumberFormat;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    int m = 1;
    Context context;
    List<ModelClass> countryList;

    public Adapter(Context context, List<ModelClass> countryList) {
        this.context = context;
        this.countryList = countryList;
    }

    @NonNull
    @Override
    public Adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.country_list_layout, null, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Adapter.ViewHolder holder, int position) {

        ModelClass modelClass = countryList.get(position);
        if (m == 1) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getCases()))); // it shows the cases with coma - numberFormatter
        } else if (m == 2) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getRecovered())));
        } else if (m == 3) {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getDeaths())));
        } else {
            holder.cases.setText(NumberFormat.getInstance().format(Integer.parseInt(modelClass.getActive()))); // it shows the cases with coma

        }
        holder.country.setText(modelClass.getCountry());

    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView cases, country;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cases = itemView.findViewById(R.id.cases);
            country = itemView.findViewById(R.id.countryName);
        }
    }


    public void filter(String charText) {
//        "Cases","Deaths","Recovered","Active"
        switch (charText) {
            case "Cases":
                m = 1;
                break;
            case "Recovered":
                m = 2;
                break;
            case "Deaths":
                m = 3;
                break;
            default:
                m = 4;
                break;

        }
        notifyDataSetChanged();
    }
}
