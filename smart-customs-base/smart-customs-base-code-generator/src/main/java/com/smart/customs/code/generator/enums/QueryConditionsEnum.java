package com.smart.customs.code.generator.enums;

import com.smart.customs.code.generator.constants.GeneratorConstants;
import lombok.Getter;

import java.util.Arrays;

/**
 * 查询条件枚举
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @CreateTime 2024/9/4 - 23:40
 */
@Getter
public enum QueryConditionsEnum {

    EQUAL("equal", "eq", "等于"),
    NO_EQUAL("noEqual", "ne", "不等于"),
    LIKE("like", "like", "模糊匹配"),
    LEFT_LIKE("leftLike", "likeLeft", "左模糊匹配"),
    RIGHT_LIKE("rightLike", "likeRight", "右模糊匹配"),
    GREATER_THAN("greaterThan", "gt", "大于"),
    GREATER_THAN_OR_EQUAL("greaterThanOrEqual", "ge", "大于等于"),
    LESS_THAN("lessThan", "lt", "小于"),
    LESS_THAN_OR_EQUAL("lessThanOrEqual", "le", "小于等于"),
    IN("in", "in", "包含"),
    NOT_IN("notIn", "notIn", "不包含"),
    BETWEEN("between", "between", "在区间内"),
    NO_BETWEEN("noBetween", "notBetween", "不在区间内");

    /**
     * 查询条件编码
     */
    private final String code;

    /**
     * Mybatis Plus 查询条件值
     */
    private final String mpValue;

    /**
     * 查询条件描述
     */
    private final String description;

    QueryConditionsEnum(String code, String mpValue, String description) {
        this.code = code;
        this.mpValue = mpValue;
        this.description = description;
    }

    /**
     * 通过代码获取 Mybatis Plus 查询条件值
     *
     * @param code 代码
     * @return {@link String } Mybatis Plus 查询条件值
     * @author payne.zhuang
     * @CreateTime 2024-09-05 - 10:21:06
     */
    public static String getMpValueByCode(String code) {
        return Arrays.stream(QueryConditionsEnum.values())
                .filter(condition -> condition.getCode().equals(code))
                .map(QueryConditionsEnum::getMpValue)
                .findFirst()
                .orElse(GeneratorConstants.EMPTY);
    }
}
