package com.meet.covitrack;

public class ModelClass {
    // pass that value that we want to fetch from the api
//
//    {
//        "updated": 1655875958559,
//            "country": "Afghanistan",
//            "countryInfo": {
//        "_id": 4,
//                "iso2": "AF",
//                "iso3": "AFG",
//                "lat": 33,
//                "long": 65,
//                "flag": "https://disease.sh/assets/img/flags/af.png"
//    },
// EG :    "cases": 181808,
//            "todayCases": 0,
//            "deaths": 7715,
//            "todayDeaths": 0,
//            "recovered": 163856,
//            "todayRecovered": 0,
//            "active": 10237,

    // name must be same name according to api otherwise not work

    String cases;
    String todayCases;
    String recovered;
    String todayDeaths;
    String todayRecovered;
    String active;
    String country;
    String deaths;

    public ModelClass(String cases, String todayCases, String recovered, String todayDeaths, String todayRecovered, String active, String country, String deaths) {
        this.cases = cases;
        this.todayCases = todayCases;
        this.recovered = recovered;
        this.todayDeaths = todayDeaths;
        this.todayRecovered = todayRecovered;
        this.active = active;
        this.country = country;
        this.deaths = deaths;
    }

    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }


}
