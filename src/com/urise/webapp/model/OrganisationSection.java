package com.urise.webapp.model;

import java.util.List;

public class OrganisationSection extends AbstractSection {

    private final List<Organisation> orgList;

    public OrganisationSection(List<Organisation> orgList) {
        this.orgList = orgList;
    }

    public List<Organisation> getOrgList() {
        return orgList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrganisationSection that = (OrganisationSection) o;

        return orgList.equals(that.orgList);
    }

    @Override
    public int hashCode() {
        return orgList.hashCode();
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
