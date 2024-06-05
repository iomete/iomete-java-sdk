package com.iomete.sdk.client;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

public class HttpClientFactory {
    private static volatile HttpClientFactory instance;

    private HttpClientFactory() {
    }

    public static HttpClientFactory getInstance() {
        if (instance == null) {
            synchronized (HttpClientFactory.class) {
                if (instance == null) {
                    instance = new HttpClientFactory();
                }
            }
        }
        return instance;
    }

    private static final int DEFAULT_CONN_TIMEOUT = 10_000;
    private static final int DEFAULT_SOCKET_TIMEOUT = 60_000;

    public CloseableHttpClient createHttpClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        // Set the maximum number of total open connections
        connectionManager.setMaxTotal(200);
        // Set the maximum number of concurrent connections per route, which is 20 by default
        connectionManager.setDefaultMaxPerRoute(100);

        RequestConfig config = RequestConfig.custom()
                .setConnectTimeout(DEFAULT_CONN_TIMEOUT)
                .setSocketTimeout(DEFAULT_SOCKET_TIMEOUT)
                .build();

        return HttpClients.custom()
                .setConnectionManager(connectionManager)
                .setDefaultRequestConfig(config)
                .build();
    }
}
