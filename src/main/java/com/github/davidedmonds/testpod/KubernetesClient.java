package com.github.davidedmonds.testpod;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1APIResource;
import io.kubernetes.client.models.V1APIResourceList;
import io.kubernetes.client.util.Config;
import java.io.IOException;
import java.util.stream.Collectors;

class KubernetesClient {
    private final CoreV1Api api;

    KubernetesClient() throws IOException {
        this(new CoreV1Api(Config.defaultClient()));
    }

    KubernetesClient(CoreV1Api api) {
        this.api = api;
    }

    String testConnection() throws ApiException {
        V1APIResourceList apiResources = api.getAPIResources();
        return String.format("API Version: %s\nResources Available: [%s]",
                apiResources.getApiVersion(),
                apiResources.getResources().stream()
                        .map(V1APIResource::getName)
                        .collect(Collectors.joining(","))
        );
    }
}
