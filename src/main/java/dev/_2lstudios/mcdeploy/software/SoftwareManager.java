package dev._2lstudios.mcdeploy.software;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import dev._2lstudios.mcdeploy.errors.SoftwareFetchException;
import dev._2lstudios.mcdeploy.utils.DownloadUtils;

public class SoftwareManager {
    public Map<String, Software> softwares;

    public SoftwareManager() {
        this.softwares = new HashMap<>();
    }

    private void downloadHandler() throws IOException {
        String url = "https://github.com/sammwyy/minecraft-db/raw/main/data/servers.json";
        String raw = DownloadUtils.readStringFromURL(url);

        Type mapType = new TypeToken<Map<String, Software>>() {
        }.getType();
        Map<String, Software> softwares = new Gson().fromJson(raw, mapType);
        this.softwares = softwares;

        for (Entry<String, Software> entry : this.softwares.entrySet()) {
            entry.getValue().id = entry.getKey();
        }
    }

    public void download() throws SoftwareFetchException {
        try {
            this.downloadHandler();
        } catch (IOException e) {
            throw new SoftwareFetchException(e);
        }
    }

    public Software getSoftware(String id) {
        return this.softwares.get(id);
    }
}
