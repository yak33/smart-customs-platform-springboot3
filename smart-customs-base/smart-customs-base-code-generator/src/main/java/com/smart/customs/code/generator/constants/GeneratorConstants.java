package com.smart.customs.code.generator.constants;

/**
 * 代码生成器常量
 *
 * @Author payne.zhuang <paynezhuang@gmail.com>
 * @CreateTime 2024/8/28 - 15:31
 */
@SuppressWarnings("squid:S2386")
public final class GeneratorConstants {

    // 逻辑删除字段
    public static final String LOGIC_DELETE_COLUMN = "is_deleted";
    // 无需处理的默认字段
    public static final String[] IGNORE_COLUMNS = {"id", "create_user_id", "update_user", "update_user_id", "update_time", LOGIC_DELETE_COLUMN};
    // 父级实体默认字段
    public static final String[] SUPER_ENTITY_COLUMNS = {"id", "create_user", "create_user_id", "create_time", "update_user", "update_user_id", "update_time", LOGIC_DELETE_COLUMN};
    public static final String COMMA = ",";
    public static final String COLON = ":";
    public static final String DOT = ".";
    public static final String DASH = "-";
    public static final String EMPTY = "";
    public static final String UNDERSCORE = "_";
    public static final String Y = "1";
    public static final String N = "0";
    public static final String INPUT = "input";
    public static final String SELECT = "select";
    public static final String RADIO = "radio";
    public static final String EQUAL = "equal";
    public static final String NO_EQUAL = "no_equal";
    public static final String LIKE = "like";
    public static final String NO_LIKE = "no_like";
    private GeneratorConstants() {

    }
}
