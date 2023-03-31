package be.vdab.cheese.domain;

import jakarta.persistence.Access;
import jakarta.persistence.AccessType;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
@Access(AccessType.FIELD)
public class Website {
    private String url;

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Website website)) return false;
        return Objects.equals(url, website.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(url);
    }
}
