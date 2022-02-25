package com.projectmatching.app.controller.team;


import com.projectmatching.app.config.resTemplate.ResponeException;
import com.projectmatching.app.config.resTemplate.ResponseTemplate;
import com.projectmatching.app.domain.team.dto.TeamRequestDto;
import com.projectmatching.app.service.team.TeamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static com.projectmatching.app.constant.ResponseTemplateStatus.EMPTY_TEAM_NAME;
import static com.projectmatching.app.constant.ResponseTemplateStatus.SUCCESS;


@Slf4j
@RequiredArgsConstructor
@RestController
public class TeamController {
    private final TeamService teamService;

    /**
     *
     * team 생성
     */
    @PostMapping("/team")
    public ResponseTemplate<Long> save(@RequestBody TeamRequestDto requestDto){
        if(requestDto.getName()==null) return ResponseTemplate.of(EMPTY_TEAM_NAME);

        try {
            Long result = teamService.save(requestDto);
            return ResponseTemplate.of(SUCCESS, result);
        }catch(ResponeException e){
            return ResponseTemplate.of(e.getStatus());
        }
    }
}
