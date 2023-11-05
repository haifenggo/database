package com.database.backend.domain.queryForm;

import com.database.backend.util.PageParam;
import lombok.Data;
import lombok.Getter;

/**
 * @描述
 * @日期 2023/11/5 15:11
 */
@Data
public class PostForm extends PageParam {

    Integer userId;

    String username;


}
