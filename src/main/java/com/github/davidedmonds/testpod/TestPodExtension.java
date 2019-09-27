package com.github.davidedmonds.testpod;

import io.kubernetes.client.ApiException;
import java.io.IOException;
import org.junit.jupiter.api.extension.Extension;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TestPodExtension implements Extension {
    private static final Logger log = LoggerFactory.getLogger(TestPodExtension.class);

    private final KubernetesClient kubernetesClient;

    public TestPodExtension() throws IOException, ApiException {
        log.info("⎈ Connecting to Kubernetes ⎈");
        kubernetesClient = new KubernetesClient();
        String metadata = kubernetesClient.testConnection();
        log.info("⎈ Kubernetes connection established ⎈\n\\{ {} \\}", metadata);
    }
}
