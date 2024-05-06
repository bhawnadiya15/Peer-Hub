package com.example.peerhub;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class Education extends Fragment {

    private String universityCollege;
    private String degreeSpecialization;
    private String fromDate;
    private String toDate;
    private boolean currentlyStudying;
    private String cgpaPercentage;

    public Education(String universityCollege, String degreeSpecialization, String fromDate, String toDate, boolean currentlyStudying, String cgpaPercentage) {
        this.universityCollege = universityCollege;
        this.degreeSpecialization = degreeSpecialization;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.currentlyStudying = currentlyStudying;
        this.cgpaPercentage = cgpaPercentage;
    }

    public Education () {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_education, container, false);
    }

    // Getters and setters
    public String getUniversityCollege() {
        return universityCollege;
    }

    public void setUniversityCollege(String universityCollege) {
        this.universityCollege = universityCollege;
    }

    public String getDegreeSpecialization() {
        return degreeSpecialization;
    }

    public void setDegreeSpecialization(String degreeSpecialization) {
        this.degreeSpecialization = degreeSpecialization;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public boolean isCurrentlyStudying() {
        return currentlyStudying;
    }

    public void setCurrentlyStudying(boolean currentlyStudying) {
        this.currentlyStudying = currentlyStudying;
    }

    public String getCgpaPercentage() {
        return cgpaPercentage;
    }

    public void setCgpaPercentage(String cgpaPercentage) {
        this.cgpaPercentage = cgpaPercentage;
    }
}
