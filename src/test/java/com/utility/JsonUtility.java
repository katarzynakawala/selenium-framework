package com.utility;

import com.constans.Env;
import com.google.gson.Gson;
import com.ui.pojos.Config;
import com.ui.pojos.Environment;

import java.io.*;

public class JsonUtility {

    private JsonUtility() {
    }

    public static String getEnvironmentUrl(Env environment) {

        String path = System.getProperty("user.dir")
                + File.separator + "config"
                + File.separator + "config.json";

        Gson gson = new Gson();

        try (Reader reader = new FileReader(path)) {
            Config config = gson.fromJson(reader, Config.class);
            Environment env = config.getEnvironments().get(environment.name());

            if (env == null) {
                throw new IllegalArgumentException(
                        "Environment '" + environment + "' not found in config.json");
            }

            return env.getUrl();

        } catch (IOException e) {
            throw new RuntimeException("Failed to load json file: " + path, e);
        }
    }
}