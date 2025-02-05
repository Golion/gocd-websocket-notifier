package com.matt_richardson.gocd.websocket_notifier;

import com.thoughtworks.go.plugin.api.logging.Logger;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.io.File;

public class PluginConfig {
    private static Logger LOGGER = Logger.getLoggerFor(GoNotificationPlugin.class);
    private static final String PLUGIN_CONF = "gocd-websocket-notifier.conf";
    private int port = 8887;
    private String pingUrl = "http://localhost/ci-receiver";

    public PluginConfig() {
        String userHome = System.getProperty("user.home");
        File pluginConfig = new File(userHome + File.separator + PLUGIN_CONF);
        if (!pluginConfig.exists()) {
            LOGGER.warn(String.format("Config file %s was not found in %s. Using default values.", PLUGIN_CONF, userHome));
        }
        else {
            Config config = ConfigFactory.parseFile(pluginConfig);
            if (config.hasPath("port")) {
                setPort(config.getInt("port"));
            }
            if (config.hasPath("url")) {
                setPingUrl(config.getString("url"));
            }
        }
    }

    public int getPort() { return port; }
    public void setPort(int port) { this.port = port; }

    public String getPingUrl() { return pingUrl; }
    public void setPingUrl(String pingUrl) { this.pingUrl = pingUrl; }
}
