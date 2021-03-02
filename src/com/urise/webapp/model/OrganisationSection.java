package com.urise.webapp.model;

import java.util.ArrayList;

public class OrganisationSection extends Section {

    public ArrayList<Organisation> orgList;

    public OrganisationSection(ArrayList<Organisation> orgList) {
        this.orgList = orgList;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Organisation org : orgList) {
            builder.append(org.toString());
        }
        return builder.toString();
    }
}
