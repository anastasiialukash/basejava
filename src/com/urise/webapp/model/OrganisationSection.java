package com.urise.webapp.model;

import java.util.ArrayList;
import java.util.List;

public class OrganisationSection extends AbstractSection {

    private final List<Organisation> orgList;

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
