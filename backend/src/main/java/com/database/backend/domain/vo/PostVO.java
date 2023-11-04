package com.database.backend.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @描述
 * @日期 2023/10/11 9:58
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostVO implements Serializable {
    private Integer postId;
    private Integer userId;
    private String title;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
}
