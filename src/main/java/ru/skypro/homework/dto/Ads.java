package ru.skypro.homework.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class Ads {
    @Schema(description = "общее количество объявлений")
    private int count;
    private List<Ad> results;

    public Ads() {
    }

    public int getCount() {
        return this.count;
    }

    public List<Ad> getResults() {
        return this.results;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setResults(List<Ad> results) {
        this.results = results;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Ads)) return false;
        final Ads other = (Ads) o;
        if (!other.canEqual((Object) this)) return false;
        if (this.getCount() != other.getCount()) return false;
        final Object this$results = this.getResults();
        final Object other$results = other.getResults();
        if (this$results == null ? other$results != null : !this$results.equals(other$results)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Ads;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + this.getCount();
        final Object $results = this.getResults();
        result = result * PRIME + ($results == null ? 43 : $results.hashCode());
        return result;
    }

    public String toString() {
        return "Ads(count=" + this.getCount() + ", results=" + this.getResults() + ")";
    }
}
