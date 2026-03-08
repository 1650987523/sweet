package com.sweet.service.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.springframework.util.StringUtils;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class PostgresqlJsonbTypeHandler extends BaseTypeHandler<Map<String, Object>> {

    private static final ObjectMapper MAPPER = new ObjectMapper();
    private static final Map<String, Object> EMPTY_MAP = Collections.emptyMap();

    // 写入数据库：仅核心逻辑，异常直接包装
    @Override
    public void setNonNullParameter(PreparedStatement ps, int i, Map<String, Object> parameter, JdbcType jdbcType) throws SQLException {
        try {
            ps.setObject(i, MAPPER.writeValueAsString(parameter), JdbcType.OTHER.TYPE_CODE);
        } catch (Exception e) {
            throw new SQLException("PostgresqlJsonbTypeHandler JSON序列化失败", e);
        }
    }

    // 读取数据库：三个方法统一调用，减少重复代码
    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, String columnName) throws SQLException {
        return parse(rs.getString(columnName));
    }

    @Override
    public Map<String, Object> getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
        return parse(rs.getString(columnIndex));
    }

    @Override
    public Map<String, Object> getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
        return parse(cs.getString(columnIndex));
    }


    /**
     * 解析JSON方法
     * @param jsonStr
     * @return
     */
    private Map<String, Object> parse(String jsonStr) {
        if (!StringUtils.hasText(jsonStr)) {
            return EMPTY_MAP;
        }
        try {
            return MAPPER.readValue(jsonStr, new HashMap<String, Object>().getClass());
        } catch (Exception e) {
            return EMPTY_MAP;
        }
    }
}
