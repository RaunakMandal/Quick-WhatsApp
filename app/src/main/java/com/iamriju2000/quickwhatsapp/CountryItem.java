package com.iamriju2000.quickwhatsapp;

public class CountryItem {
    private final String countryName;
    private final int flagImage;

    public CountryItem(String countryName, int flagImage) {
        this.countryName = countryName;
        this.flagImage = flagImage;
    }

    public String getCountryName() {
        return countryName;
    }

    public int getFlagImage() {
        return flagImage;
    }
}
