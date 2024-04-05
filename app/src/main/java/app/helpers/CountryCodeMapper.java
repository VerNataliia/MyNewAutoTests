package app.helpers;

import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.TreeMap;

public class CountryCodeMapper {

    private static final Map<String, String> COUNTRY_CODE_MAP = new TreeMap<>();

    static {
        // Manually add custom codes for specific countries
        //https://www.iban.com/country-codes
        COUNTRY_CODE_MAP.put("American Samoa", "ASM");
        COUNTRY_CODE_MAP.put("Antarctica", "ATA");
        COUNTRY_CODE_MAP.put("Réunion", "REU");
        COUNTRY_CODE_MAP.put("Bolivia (Plurinational State of)", "BOL");
        COUNTRY_CODE_MAP.put("Bonaire, Sint Eustatius and Saba", "BES");
        COUNTRY_CODE_MAP.put("British Indian Ocean Territory (the)", "IOT");
        COUNTRY_CODE_MAP.put("Cayman Islands (the)", "CYM");
        COUNTRY_CODE_MAP.put("Central African Republic (the)", "CAF");
        COUNTRY_CODE_MAP.put("Cocos Islands", "CCK");
        COUNTRY_CODE_MAP.put("Comoros (the)", "COM");
        COUNTRY_CODE_MAP.put("Congo (the Democratic Republic of the)", "COD");
        COUNTRY_CODE_MAP.put("Congo (the)", "COG");
        COUNTRY_CODE_MAP.put("Cook Islands (the)", "COK");
        COUNTRY_CODE_MAP.put("Côte d'Ivoire", "CIV");
        COUNTRY_CODE_MAP.put("Dominican Republic (the)", "DOM");
        COUNTRY_CODE_MAP.put("Falkland Islands (the) [Malvinas]", "FLK");
        COUNTRY_CODE_MAP.put("Faroe Islands (the)", "FRO");
        COUNTRY_CODE_MAP.put("French Southern Territories (the)", "ATF");
        COUNTRY_CODE_MAP.put("Gambia (the)", "GMB");
        COUNTRY_CODE_MAP.put("Guinea-Bissau", "GNB");
        COUNTRY_CODE_MAP.put("Heard Island and McDonald Islands", "HMD");
        COUNTRY_CODE_MAP.put("Holy See (the)", "VAT");
        COUNTRY_CODE_MAP.put("Iran (Islamic Republic of)", "IRN");
        COUNTRY_CODE_MAP.put("Korea (the Democratic People's Republic of)", "PRK");
        COUNTRY_CODE_MAP.put("Korea (the Republic of)", "KOR");
        COUNTRY_CODE_MAP.put("Lao People's Democratic Republic (the)", "LAO");
        COUNTRY_CODE_MAP.put("Marshall Islands (the)", "MHL");
        COUNTRY_CODE_MAP.put("Micronesia (Federated States of)", "FSM");
        COUNTRY_CODE_MAP.put("Moldova (the Republic of)", "MDA");
        COUNTRY_CODE_MAP.put("Netherlands (the)", "NLD");
        COUNTRY_CODE_MAP.put("Niger (the)", "NER");
        COUNTRY_CODE_MAP.put("Northern Mariana Islands (the)", "MNP");
        COUNTRY_CODE_MAP.put("Palestine, State of", "PSE");
        COUNTRY_CODE_MAP.put("Philippines (the)", "PHL");
        COUNTRY_CODE_MAP.put("Republic of North Macedonia", "MKD");
        COUNTRY_CODE_MAP.put("Russian Federation (the)", "RUS");
        COUNTRY_CODE_MAP.put("Saint Helena, Ascension and Tristan da Cunha", "SHN");
        COUNTRY_CODE_MAP.put("Saint Kitts and Nevis", "KNA");
        COUNTRY_CODE_MAP.put("Saint Martin (French part)", "MAF");
        COUNTRY_CODE_MAP.put("Saint Pierre and Miquelon", "SPM");
        COUNTRY_CODE_MAP.put("Saint Vincent and the Grenadines", "VCT");
        COUNTRY_CODE_MAP.put("Sint Maarten (Dutch part)", "SXM");
        COUNTRY_CODE_MAP.put("South Georgia and the South Sandwich Islands", "SGS");
        COUNTRY_CODE_MAP.put("Svalbard and Jan Mayen", "SJM");
        COUNTRY_CODE_MAP.put("Syrian Arab Republic", "SYR");
        COUNTRY_CODE_MAP.put("Taiwan (Province of China)", "TWN");
        COUNTRY_CODE_MAP.put("Tanzania, United Republic of", "TZA");
        COUNTRY_CODE_MAP.put("Turks and Caicos Islands (the)", "TCA");
        COUNTRY_CODE_MAP.put("United Arab Emirates (the)", "ARE");
        COUNTRY_CODE_MAP.put("United Kingdom of Great Britain and Northern Ireland", "GBR");
        COUNTRY_CODE_MAP.put("United States Minor Outlying Islands (the)", "UMI");
        COUNTRY_CODE_MAP.put("United States of America (the)", "USA");
        COUNTRY_CODE_MAP.put("Venezuela (Bolivarian Republic of)", "VEN");
        COUNTRY_CODE_MAP.put("Virgin Islands (British)", "VGB");
        COUNTRY_CODE_MAP.put("Virgin Islands (U.S.)", "VIR");
        COUNTRY_CODE_MAP.put("Åland Islands", "ALA");

        // Populate the map with country names and their 3-letter ISO codes from locales
        for (Locale locale : Locale.getAvailableLocales()) {
            String country = locale.getDisplayCountry(Locale.ENGLISH);
            if (!country.isEmpty()) {
                try {
                    String isoCountry = locale.getISO3Country();
                    COUNTRY_CODE_MAP.putIfAbsent(country, isoCountry);
                } catch (MissingResourceException e) {
                    // Skip if there's no 3-letter code available
                    System.err.println("Missing 3-letter code for country: " + country);
                }
            }
        }
    }

    public static String getCountryCode(String countryName) {
        return COUNTRY_CODE_MAP.getOrDefault(countryName, "Unknown");
    }

    public static void main(String[] args) {
        String testCountry = "Antarctica";
        System.out.println(testCountry + " has the 3-letter code: " + getCountryCode(testCountry));
    }
}