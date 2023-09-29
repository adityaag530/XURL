package com.crio.shorturl;

import java.util.HashMap;
import java.util.Map.Entry;

class XUrlImpl implements XUrl {

    private HashMap<String, String> map = new HashMap<>();
    private HashMap<String, Integer> mapCount = new HashMap<>();

    // DELETE METHOD
    @Override
    public String delete(String longUrl) {
        map.remove(longUrl);
        return null;
    }

    // GET HIT COUNT
    @Override
    public Integer getHitCount(String longUrl) {
        return mapCount.getOrDefault(longUrl,0);
    }

    // GET URL
    @Override
    public String getUrl(String shortUrl) {

        
        if (!map.containsValue(shortUrl)) {
            return null;
        } else {
            for (Entry<String, String> entry : map.entrySet()) {
               
                if (shortUrl.equals(entry.getValue())) {
                    mapCount.put(entry.getKey(), mapCount.getOrDefault(entry.getKey(),0)+1);
                    return entry.getKey();
                }
            }
        }
        return null;
    }

    // REGISTER URL WITH SINGLE PARAMETER
    @Override
    public String registerNewUrl(String longUrl) {

        if (map.containsKey(longUrl)) {
            // return the short url
            return map.get(longUrl);
        } else {
            // create a new short Url
            String shortUrl = "http://short.url/" + getAlphaNumericString();
            map.put(longUrl, shortUrl);
            return shortUrl;
        }
    }

    // REGISTER URL WITH TWO PARAMETERS
    @Override
    public String registerNewUrl(String longUrl, String shortUrl) {

        if (map.containsValue(shortUrl)) {
            return null;
        } else {
            map.put(longUrl, shortUrl);
            return shortUrl;
        }
    }


    // FUNCTION TO RETURN ALPHANUMERIC STRING OF LENGTH 9
    public String getAlphaNumericString() {
        // chose a Character random from this String
        String AlphaNumericString =
                "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";
        // create StringBuffer size of AlphaNumericString
        StringBuilder sb = new StringBuilder(9);
        for (int i = 0; i < 9; i++) {
            // generate a random number between
            // 0 to AlphaNumericString variable length
            int index = (int) (AlphaNumericString.length() * Math.random());
            // add Character one by one in end of sb
            sb.append(AlphaNumericString.charAt(index));
        }
        return sb.toString();
    }
}
