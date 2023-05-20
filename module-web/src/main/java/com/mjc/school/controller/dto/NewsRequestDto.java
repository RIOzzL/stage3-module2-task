package com.mjc.school.controller.dto;

import com.mjc.school.controller.validation.restriction.Size;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class NewsRequestDto {
    private Long id;
    @Size(min = 5, max = 30)
    private String title;
    @Size(min = 5, max = 255)
    private String content;

    private LocalDateTime createDate;

    private LocalDateTime lastUpdatedDate;

    private Long authorId;
}
