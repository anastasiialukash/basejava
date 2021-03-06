package com.urise.webapp.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.Arrays;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class OrganisationSection extends Section {

    private static final long serialVersionUID = 1L;
    private List<Organisation> orgList;

    public OrganisationSection(List<Organisation> orgList) {
        this.orgList = orgList;
    }

    public OrganisationSection(Organisation... organisations) {
        this(Arrays.asList(organisations));
    }

    public List<Organisation> getOrgList() {
        return orgList;
    }

    public OrganisationSection() {}

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
