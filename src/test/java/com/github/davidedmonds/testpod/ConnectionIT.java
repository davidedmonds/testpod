/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.github.davidedmonds.testpod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

@ExtendWith(TestPodExtension.class)
class ConnectionIT {
    @DisplayName("A connection with Kubernetes is established")
    @Test void canTalkToKubernetes() {

    }
}