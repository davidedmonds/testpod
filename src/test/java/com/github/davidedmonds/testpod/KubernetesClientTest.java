package com.github.davidedmonds.testpod;

import io.kubernetes.client.ApiException;
import io.kubernetes.client.apis.CoreV1Api;
import io.kubernetes.client.models.V1APIResourceBuilder;
import io.kubernetes.client.models.V1APIResourceListBuilder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class KubernetesClientTest {
    @Mock
    private CoreV1Api api;

    @DisplayName("When Startup is successful, return success metadata")
    @Test
    void returnsMetadataWhenStarted() throws ApiException {
        when(api.getAPIResources())
                .thenReturn(new V1APIResourceListBuilder()
                        .withApiVersion("3.1415")
                        .withResources(
                                new V1APIResourceBuilder().withName("one").build(),
                                new V1APIResourceBuilder().withName("two").build(),
                                new V1APIResourceBuilder().withName("three").build())
                        .build());

        KubernetesClient client = new KubernetesClient(api);
        assertThat(client.testConnection())
                .contains("API Version: 3.1415", "Resources Available: [one,two,three]");
    }
}