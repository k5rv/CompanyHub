package com.companyhub.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public record MergeBranchPostRequestBody(
    @JsonProperty("head") String head, @JsonProperty("base") String base) {}
