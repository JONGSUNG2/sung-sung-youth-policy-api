package org.sungsung.youthpolicy.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.PolicyDTO;
import org.sungsung.youthpolicy.domain.dto.policy.publicData.RootDTO;
import org.sungsung.youthpolicy.service.publicData.PublicDataService;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
public class PublicDataController {
    private final PublicDataService publicDataService;

    @GetMapping("/api/welfare")
    public List<PolicyDTO> getData() throws Exception {

        RootDTO root = publicDataService.getPublicData();
//        List<PolicyDTO> policyDTOList = root.getResult().getPolicyList();
//       publicDataService.writePublicData(policyDTOList);
        return root.getResult().getPolicyList();

    }
}
