package org.sungsung.youthpolicy.domain.dto.policy.publicData;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ResultDTO {

    @JsonProperty("youthPolicyList")
    private List<PolicyDTO> policyList;
}
