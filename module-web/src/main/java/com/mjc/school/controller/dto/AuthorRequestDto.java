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
public class AuthorRequestDto {
    private Long id;
    @Size(min = 3, max = 15)
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdateDate;
}
