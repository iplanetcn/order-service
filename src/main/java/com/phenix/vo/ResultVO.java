package com.phenix.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.experimental.Accessors;

/**
 * HTTP最外层数据
 */
@Data
@Accessors(chain = true)
@NoArgsConstructor
@JsonPropertyOrder({"code", "message"})
public class ResultVO {
    @NonNull
    private Integer code;
    @NonNull
    @JsonProperty("message")
    private String message;
}
