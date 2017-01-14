package com.example.happy.hac;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

/**
 * Created by Happy on 10/19/2016.
 */

public class hac_hospital_fragment extends Fragment{

    ProgressBar progressBar ;
    Button button , ambulance_btn;
    ImageView imageView;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.hac_hospital_fragment, container, false);
        button = (Button) view.findViewById(R.id.hac_hospital_fragment_hospital_ambulance);
        progressBar = (ProgressBar) view.findViewById(R.id.hac_hospital_fragment_circular);
        imageView = (ImageView) view.findViewById(R.id.hac_hospital_fragment_image_view);
        ambulance_btn = (Button) view.findViewById(R.id.hac_hospital_fragment_private_agency_ambulance);
        progressBar.setVisibility(View.GONE);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getContext(), hac_hospital_map_path.class);
                startActivity(intent);
                progressBar.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                button.setText("Loading...");
            }
        });

        ambulance_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  intent = new Intent(getContext(), hac_ambulance_agency.class);
                startActivity(intent);
                progressBar.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.GONE);
                button.setText("Loading...");
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        button.setText("Hosptial and Ambulance");
    }
}
