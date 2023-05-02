package aiss.GitHubMiner.utils;

import aiss.GitHubMiner.models.Commit;
import aiss.GitHubMiner.models.CommitDef;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class adaptadores {
    public static CommitDef transforma(Commit commitRaw) {
        // quiero transformar del commit original a mi commitDef:
        String id = commitRaw.getSha();
        String message = commitRaw.getCommit().getMessage();
        String tittle = titleFromMessage(message);
        String authorName = commitRaw.getCommit().getAuthor().getName();
        String authorEmail = commitRaw.getCommit().getAuthor().getEmail();
        LocalDate authoredDate = dateParse(commitRaw.getCommit().getAuthor().getDate());
        String commiterName = commitRaw.getCommit().getCommitter().getName();
        String commiterEmail = commitRaw.getCommit().getCommitter().getEmail();
        String commitedDate = commitRaw.getCommit().getCommitter().getDate();
        String url = commitRaw.getUrl();
        return new CommitDef(id, tittle, message, authorName, authorEmail, authoredDate, commiterName, commiterEmail, commitedDate, url);
    }

    private static String titleFromMessage(String message) { // <-- el tittle va a ser la primera frase del message
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
