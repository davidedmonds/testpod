package com.github.davidedmonds.testpod;

import io.kubernetes.client.ApiClient;
import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1APIResource;
import io.kubernetes.client.models.V1APIResourceList;
import io.kubernetes.client.util.Config;
import java.io.IOException;
import java.util.stream.Collectors;
import org.junit.jupiter.api.extension.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPodExtension implements Extension {
    private static final Logger log = LoggerFactory.getLogger(TestPodExtension.class);

    private final ApiClient apiClient;
    private final CoreV1Api api;

    public TestPodExtension() throws IOException, ApiException {
        log.info("⎈ Connecting to Kubernetes ⎈");
        apiClient = Config.defaultClient();
        api = new CoreV1Api(apiClient);
        V1APIResourceList apiResources = api.getAPIResources();
        log.info("⎈ Kubernetes connection established ⎈\n" +
                "API Version: {}\n" +
                "Resources Available: [{}]",
                apiResources.getApiVersion(),
                apiResources.getResources().stream()
                        .map(V1APIResource::getName)
                        .collect(Collectors.joining(","))
        );
    }
}
