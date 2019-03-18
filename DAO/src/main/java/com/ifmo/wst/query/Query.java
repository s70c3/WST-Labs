package com.ifmo.wst.query;

import com.ifmo.wst.dao.AllConditions;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class Query {


    private final String queryString;
    private final List<AllConditions> conditions;


    public Query(String queryString, List<AllConditions> conditions) {
        this.queryString = queryString;
        this.conditions = conditions;
    }

    public void initPreparedStatement(PreparedStatement ps) throws SQLException {
        int i = 1;
        for (AllConditions condition : conditions) {
            Class<?> valueClass = condition.getType();
            int sqlType = classToSQLType(valueClass);
            if (condition.getValue() == null) {
                ps.setNull(i, sqlType);
            } else {
                switch (sqlType) {
                    case Types.INTEGER:
                        ps.setLong(i, (Integer) condition.getValue());
                        break;
                    case Types.VARCHAR:
                        ps.setString(i, (String) condition.getValue());
                        break;
                    default:
                        throw new RuntimeException(condition.toString());
                }

            }
            ++i;
        }
    }

    private int classToSQLType(Class<?> aClass) {
        if (aClass == Integer.class) {
            return Types.INTEGER;
        } else if (aClass == String.class) {
            return Types.VARCHAR;
        }
            throw new IllegalArgumentException(aClass.getName());
    }

    public String getQueryString() {
        return queryString;
    }
}
