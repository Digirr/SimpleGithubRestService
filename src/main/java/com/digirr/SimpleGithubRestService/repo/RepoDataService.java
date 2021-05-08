package com.digirr.SimpleGithubRestService.repo;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

@Service
public class RepoDataService {

    private JSONObject json;

    public boolean validJSON(String owner, String repo_name) {
        try {
            String myURI = "https://api.github.com/repos/" + owner + "/" + repo_name;
            json = readJsonFromUrl(myURI);
            return true;
        } catch (IOException e) { return false; }
    }

    public Object getData(String owner, String repo_name) {
        if(validJSON(owner, repo_name)){
            return new RepoData(
                    json.get("full_name").toString(),
                    json.get("description").toString(),
                    json.get("clone_url").toString(),
                    Integer.parseInt(json.get("stargazers_count").toString())
            );
        }
        return "<center><H1>The repository at this address does not exist</H1></center>";
    }

    public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        } finally {
            is.close();
        }
    }
    private String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

}
