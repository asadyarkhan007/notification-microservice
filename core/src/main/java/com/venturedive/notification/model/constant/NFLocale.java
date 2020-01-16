package com.venturedive.notification.model.constant;

public enum NFLocale {
  EN_US("en", "English", "US", "United States"),
  AR_SA("ar", "Arabic", "SA", "Saudia Arabia");

  private String languageCode;
  private String language;
  private String country;
  private String countryCode;

  NFLocale(String languageCode, String language, String countryCode, String country) {
    this.languageCode = languageCode;
    this.language = language;
    this.countryCode = countryCode;
    this.country = country;
  }

  public String getLanguageCode() {
    return languageCode;
  }

  public String getLanguage() {
    return language;
  }

  public String getCountry() {
    return country;
  }

  public String getCountryCode() {
    return countryCode;
  }
}
