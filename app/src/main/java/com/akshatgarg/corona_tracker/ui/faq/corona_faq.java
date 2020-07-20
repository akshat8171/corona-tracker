package com.akshatgarg.corona_tracker.ui.faq;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.akshatgarg.corona_tracker.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class corona_faq extends AppCompatActivity {
    faq_adapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_corona_faq);
        ImageButton back = (ImageButton) findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                corona_faq.super.onBackPressed();
            }
        });
        // get the listview
        expListView = (ExpandableListView) findViewById(R.id.expandableListView);

        // preparing list data
        prepareListData();

        listAdapter = new faq_adapter(this, listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);

        // Listview Group click listener
        expListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {

            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                // Toast.makeText(getApplicationContext(),
                // "Group Clicked " + listDataHeader.get(groupPosition),
                // Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        // Listview Group expanded listener
        expListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {

            @Override
            public void onGroupExpand(int groupPosition) {
            }
        });

        // Listview Group collasped listener
        expListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {

            @Override
            public void onGroupCollapse(int groupPosition) {
            }
        });

        // Listview on child click listener
        expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,
                                        int groupPosition, int childPosition, long id) {
                return false;
            }
        });
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("What is novel coronavirus");
        listDataHeader.add("Which are the first symptoms of the coronavirus disease?");
        listDataHeader.add("Can Coronavirus disease be caught from a person who has no symptoms?");
        listDataHeader.add("What is a safe distance from others to protect against the coronavirus disease?");
        listDataHeader.add("How does a virus spread?");
        listDataHeader.add("Will warm weather stop the outbrreak of COVID-19");
        listDataHeader.add("How can I protect myself");
        listDataHeader.add("Can I get COVID-19 from my pets or other animals");
        listDataHeader.add("Can the virus that causes COVID-19 spread through treated drinking water");
        listDataHeader.add("Is there a vaccine drug or treatment for COVID-19");
        listDataHeader.add("Can water transmit COVID-19?");

        // Adding child data
        List<String> q1 = new ArrayList<String>();
        q1.add("A novel coronavirus is a new coronavirus that has not been previously identified. The virus causing coronavirus disease 2019 (COVID-19), is not the same as the coronaviruses that commonly circulate among humans and cause mild illness, like the common cold.");
        List<String> q2 = new ArrayList<String>();
        q2.add("The virus can cause a range of symptoms, ranging from mild illness to pneumonia. Symptoms of the disease are fever, cough, sore throat and headaches. In severe cases difficulty in breathing and deaths can occur");
        List<String> q3 = new ArrayList<String>();
        q3.add("The main way the disease spreads is through respiratory droplets expelled by someone who is coughing. The risk of catching COVID-19 from someone with no symptoms at all is very low. However, many people with COVID-19 experience only mild symptoms. This is particularly true at the early stages of the disease. It is therefore possible to catch COVID-19 from someone who has, for example, just a mild cough and does not feel ill.");
        List<String> q4 = new ArrayList<String>();
        q4.add("Maintain at least 1 metre (3 feet) distance between yourself and others. Why? When someone coughs, sneezes, or speaks they spray small liquid droplets from their nose or mouth which may contain virus. If you are too close, you can breathe in the droplets, including the COVID-19 virus if the person has the disease.");
        List<String> q5 = new ArrayList<String>();
        q5.add("The virus that causes COVID-19 is thought to spread mainly from person to person, mainly through respiratory droplets produced when an infected person coughs, sneezes, or talks. These droplets can land in the mouths or noses of people who are nearby or possibly be inhaled into the lungs. Spread is more likely when people are in close contact with one another (within about 6 feet).\n" +
                "\n" +
                "COVID-19 seems to be spreading easily and sustainably in the community (“community spread”) in many affected geographic areas. Community spread means people have been infected with the virus in an area, including some who are not sure how or where they became infected.");
        List<String> q6 = new ArrayList<String>();
        q6.add("It is not yet known whether weather and temperature affect the spread of COVID-19. Some other viruses, like those that cause the common cold and flu, spread more during cold weather months but that does not mean it is impossible to become sick with these viruses during other months.  There is much more to learn about the transmissibility, severity, and other features associated with COVID-19 and investigations are ongoing.");
        List<String> q7 = new ArrayList<String>();
        q7.add("There is currently no vaccine to prevent coronavirus disease 2019 (COVID-19).\n" +
                "The best way to prevent illness is to avoid being exposed to this virus.\n" +
                "The virus is thought to spread mainly from person-to-person.\n" +
                "Between people who are in close contact with one another (within about 6 feet).\n" +
                "Through respiratory droplets produced when an infected person coughs, sneezes or talks.\n" +
                "These droplets can land in the mouths or noses of people who are nearby or possibly be inhaled into the lungs.\n" +
                "Some recent studies have suggested that COVID-19 may be spread by people who are not showing symptoms.");
        List<String> q8 = new ArrayList<String>();
        q8.add("At this time, there is no evidence that animals play a significant role in spreading the virus that causes COVID-19. Based on the limited information available to date, the risk of animals spreading COVID-19 to people is considered to be low.  A small number of pets have been reported to be infected with the virus that causes COVID-19, mostly after contact with people with COVID-19.\n" +
                "\n" +
                "Pets have other types of coronaviruses that can make them sick, like canine and feline coronaviruses. These other coronaviruses cannot infect people and are not related to the current COVID-19 outbreak.");
        List<String> q9 = new ArrayList<String>();
        q9.add("The virus that causes COVID-19 has not been detected in treated drinking water. Water treatment plants use filters and disinfectants to remove or kill germs, like the virus that causes COVID-19. The Environmental Protection Agency regulates water treatment plants to ensure that treated water is safe to drink.\n" +
                "\n" +
                "Currently, there is no evidence that the virus that causes COVID-19 can be spread to people by drinking treated water. COVID-19 is spread mainly through close contact from person-to-person.");
        List<String> q10 = new ArrayList<String>();
        q10.add("Not yet. To date, there is no vaccine and no specific antiviral medicine to prevent or treat COVID-2019. However, those affected should receive care to relieve symptoms. People with serious illness should be hospitalized. Most patients recover thanks to supportive care. \n" +
                "Possible vaccines and some specific drug treatments are under investigation. They are being tested through clinical trials. \n");
        List<String> q11 = new ArrayList<String>();
        q11.add("Although persistence in drinking-water is possible, there is no evidence from surrogate human coronaviruses that they are present in surface or groundwater sources or transmitted through contaminated drinking water");
        List<String> q12 = new ArrayList<String>();
        q12.add("A novel coronavirus");
        List<String> q13 = new ArrayList<String>();
        q13.add("A novel coronavirus");
        List<String> q14 = new ArrayList<String>();
        q14.add("A novel coronavirus");

        listDataChild.put(listDataHeader.get(0), q1);
        listDataChild.put(listDataHeader.get(1), q2);
        listDataChild.put(listDataHeader.get(2), q3);
        listDataChild.put(listDataHeader.get(3), q4);
        listDataChild.put(listDataHeader.get(4), q5);
        listDataChild.put(listDataHeader.get(5), q6);
        listDataChild.put(listDataHeader.get(6), q7);
        listDataChild.put(listDataHeader.get(7), q8);
        listDataChild.put(listDataHeader.get(8), q9);
        listDataChild.put(listDataHeader.get(9), q10);
        listDataChild.put(listDataHeader.get(10), q11);


    }
}