package com.companyhub.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record RepoContentPutRequestBody(
    @JsonProperty("content") String content,
    @JsonProperty("sha") String hash,
    @JsonProperty("message") String message) {}
