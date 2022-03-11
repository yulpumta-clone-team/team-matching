package com.projectmatching.app.domain.team.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class TeamCommentDto {
    private Long comment_id;
    private Long parent_id;
    private Boolean secret;
    private String content;
    private LocalDateTime create_at;
}
