package aiss.GitHubMiner.utils;

import org.springframework.http.HttpHeaders;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

public class funciones {

        public static String getNextPageUrl(HttpHeaders headers) {
        String result = null;

        // If there is no link header, return null
        List<String> linkHeader = headers.get("Link");
        if (linkHeader == null)
            return null;

        // If the header contains no links, return null
        String links = linkHeader.get(0);
        if (links == null || links.isEmpty())
            return null;

        // Return the next page URL or null if none.
        for (String token : links.split(", ")) {
            if (token.endsWith("rel=\"next\"")) {
                // Found the next page. This should look something like
                // <https://api.github.com/repos?page=3&per_page=100>; rel="next"
                int idx = token.indexOf('>');
                result = token.substring(1, idx);
                break;
            }
        }

        return result;
    }


    public static LocalDateTime StringToLocalDateTime(String dateToParse) {

        Instant instant = Instant.parse(dateToParse);
        LocalDateTime parsedDate = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);

        return parsedDate;
    }

    public static String titleFromMessage(String message) { // <-- el tittle va a ser la primera frase del message
        String tittle = null;
        if (message.length() == 0 || !message.contains(".")) {
            tittle = message;
        } else {
            String[] array = message.split(".");
            if (array.length > 0) {
                tittle = array[0];
            }
        }
        return tittle;
    }

    public static LocalDate dateParse(String string) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH);
        LocalDate localDate = LocalDate.parse(string, formatter);
        return localDate;
    }
}

