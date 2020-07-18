package com.akshatgarg.corona_tracker.ui.dashboard;

public class example_item {
    private String mstate;
    private int mtotal_death;
    private int mtotal_case;
    private int mtotal_recover;
    private int mactive;
    public example_item(String country, int total_case,  int active ,int total_death,int total_recover) {
        mstate = country;
        mtotal_case = total_case;
        mtotal_death = total_death;
        mtotal_recover =  total_recover;
        mactive = active;
    }
    public String get_country() {
        return mstate;
    }
    public int total_death() {
        return (mtotal_death);
    }
    public int total_recover() {
        return (mtotal_recover);
    }
    public int total_case() {
        return (mtotal_case);
    }
    public int active() {
        return (mactive);
    }
}