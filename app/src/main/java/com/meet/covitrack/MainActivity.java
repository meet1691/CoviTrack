package com.meet.covitrack;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.hbb20.CountryCodePicker;
import com.meet.covitrack.databinding.ActivityMainBinding;

import org.eazegraph.lib.charts.PieChart;
import org.eazegraph.lib.models.PieModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    CountryCodePicker countryCodePicker;
    TextView todayTotal, total, todayDeaths, deaths, recovered, todayRecovered, active, todayActive;
    String country; // hold users selected country
    TextView filter; // recycleView TextView
    Spinner spinner;
    String[] types = {"Cases", "Deaths", "Recovered", "Active"};
    private List<ModelClass> modelClassList;
    private List<ModelClass> modelClassList2; // 1 is for normal activity and second one is for recyclerView
    PieChart pieChart;
    RecyclerView countryRecycler;
    com.meet.covitrack.Adapter adapter;
    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Objects.requireNonNull(getSupportActionBar()).hide(); // to hide the action bar
        countryCodePicker = binding.countryCodes;
        active = binding.activeCase;
//        todayActive = binding.todayActiveCase;
        deaths = binding.deathsCase;
        todayDeaths = binding.todayDeathsCase;
        recovered = binding.recoveredCase;
        todayRecovered = binding.todayRecoveredCase;
        total = binding.totalCase;
        todayTotal = binding.todayTotalCase;

        pieChart = binding.pieChart;
        spinner = binding.spinner;
        filter = binding.filterCases;
        countryRecycler = binding.recyclerCountryView;

        modelClassList = new ArrayList<>();
        modelClassList2 = new ArrayList<>();

        // for handling Spinner
        spinner.setOnItemSelectedListener(this);
        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, types);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(arrayAdapter);


        adapter = new Adapter(this, modelClassList2);
        countryRecycler.setLayoutManager(new LinearLayoutManager(this));
        countryRecycler.setHasFixedSize(true);
        countryRecycler.setAdapter(adapter);

        ApiUtilities.getApiInterface().getCountryData().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                modelClassList2.addAll(Objects.requireNonNull(response.body()));
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });


        // if user change any country

        countryCodePicker.setAutoDetectedCountry(true);
        country = countryCodePicker.getSelectedCountryName();

        countryCodePicker.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            // if user select this from country picker than we have to use method
            public void onCountrySelected() {
                country = countryCodePicker.getSelectedCountryName();
                fetchData();
            }
        });
        // normal this function is called base pr out location
        fetchData();


    }

    private void fetchData() {

        // for handling graph we call object of Api

        ApiUtilities.getApiInterface().getCountryData().enqueue(new Callback<List<ModelClass>>() {
            @Override
            public void onResponse(Call<List<ModelClass>> call, Response<List<ModelClass>> response) {
                assert response.body() != null;
                modelClassList.addAll(response.body());
                for (int i = 0; i < modelClassList.size(); i++) {
                    //we need to list all country list data so use for and for comparison also use this

                    if (modelClassList.get(i).getCountry().equals(country)) {
                        active.setText(modelClassList.get(i).getActive());
                        todayDeaths.setText(modelClassList.get(i).getTodayDeaths());
                        deaths.setText(modelClassList.get(i).getDeaths());
                        recovered.setText(modelClassList.get(i).getRecovered());
                        todayRecovered.setText(modelClassList.get(i).getTodayRecovered());
                        total.setText(modelClassList.get(i).getCases());
                        todayTotal.setText(modelClassList.get(i).getTodayCases());

                        // om graph we have to update 4 things so we uses this method


                        int cases, active, recovered, deaths;
                        cases = Integer.parseInt(modelClassList.get(i).getCases());
                        active = Integer.parseInt(modelClassList.get(i).getActive());
                        recovered = Integer.parseInt(modelClassList.get(i).getRecovered());
                        deaths = Integer.parseInt(modelClassList.get(i).getDeaths());


                        updateGraph(cases, active, recovered, deaths);
                    }
                }

            }

            @Override
            public void onFailure(Call<List<ModelClass>> call, Throwable t) {

            }
        });
    }

    private void updateGraph(int cases, int active, int recovered, int deaths) {
        // setting this 4 parameter to graph

//        first clear graph
        pieChart.clearChart();
        pieChart.addPieSlice(new PieModel("Cases", cases, Color.parseColor("#FFEB3B")));
        pieChart.addPieSlice(new PieModel("Deaths", deaths, Color.parseColor("#7C170F")));
        pieChart.addPieSlice(new PieModel("Active", active, Color.parseColor("#FF4CAF50")));
        pieChart.addPieSlice(new PieModel("Recovered", recovered, Color.parseColor("#4455B1")));
        pieChart.startAnimation();

    }


    // this is for spinner item
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

        // logic we already written at onBindViewHolder
        String item = types[i];
        filter.setText(item);
        adapter.filter(item);


    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}