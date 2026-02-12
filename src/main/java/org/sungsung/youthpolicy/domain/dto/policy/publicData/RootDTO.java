package org.sungsung.youthpolicy.domain.dto.policy.publicData;

//사용안하는 필드 무시하기

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class RootDTO {
    @JsonProperty("result")
    private ResultDTO result;
}
