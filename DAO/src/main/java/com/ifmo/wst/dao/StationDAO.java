package com.ifmo.wst.dao;

import com.ifmo.wst.entity.Station;
import com.ifmo.wst.query.Query;
import com.ifmo.wst.query.BuildQuery;


import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class StationDAO {

    private final String TABLE_NAME = "metro_station";
    private final String ID_COLUMN = "id";
    private final String NAME_COLUMN = "name";
    private final String DEEPNESS_COLUMN = "deepness";
    private final String LINE_COLUMN = "line";
    private final String START_HOUR_COLUMN = "start_work_hour";
    private final String START_MINUTE_COLUMN = "start_work_minute";

    private final String END_HOUR_COLUMN = "end_work_hour";
    private final String END_MINUTE_COLUMN = "end_work_minute";

    private final DataSource dataSource;

    public StationDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Station> findAll() throws SQLException {
//        log.debug("Find all query");
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            statement.execute("SELECT id, initiator, reason, method, planet, date FROM exterminatus");
            List<Station> result = rsToEntities(statement.getResultSet());
            return result;
        }

    }

    public List<Station> filter(int id, String name, int line, int deepness, boolean isEnd, int startWorkHour, int startWorkMinute, int endWorkHour, int endWorkMinute) throws SQLException {
        //log.debug("Filter with args: {} {} {} {} {} {}",id, name, line, deepness, isEnd, startWorkHour, startWorkMinute, endWorkHour, endWorkMinute);
        if (Stream.of(id, name, line, deepness, isEnd, startWorkHour, startWorkMinute, endWorkHour, endWorkMinute).allMatch(Objects::isNull)) {
          //  log.debug("Args are empty");
            return findAll();
        }
        Query query = new BuildQuery()
                .tableName(TABLE_NAME)
                .selectColumns(ID_COLUMN, NAME_COLUMN, DEEPNESS_COLUMN, LINE_COLUMN, START_HOUR_COLUMN, START_MINUTE_COLUMN, END_HOUR_COLUMN, END_MINUTE_COLUMN)
                .condition(new AllConditions(ID_COLUMN, id, Integer.class))
                .condition(new AllConditions(NAME_COLUMN, name, String.class))
                .condition(new AllConditions(LINE_COLUMN, line, Integer.class))
                .condition(new AllConditions(DEEPNESS_COLUMN, deepness, Integer.class))
                .condition(new AllConditions(START_HOUR_COLUMN, startWorkHour, Integer.class))
                .condition(new AllConditions(START_MINUTE_COLUMN, startWorkMinute, Integer.class))
                .condition(new AllConditions(END_HOUR_COLUMN, startWorkHour, Integer.class))
                .condition(new AllConditions(END_MINUTE_COLUMN, startWorkMinute, Integer.class))
                .buildPreparedStatementQuery();

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement ps = connection.prepareStatement(query.getQueryString());
            query.initPreparedStatement(ps);
            ResultSet rs = ps.executeQuery();
            return rsToEntities(rs);
        }

    }

    private List<Station> rsToEntities(ResultSet rs) throws SQLException {
        List<Station> result = new ArrayList<>();
        while (rs.next()) {
            result.add(resultSetToEntity(rs));
        }
//        log.debug("Result set was converted to entity list {}", result);
        return result;
    }

    private Station resultSetToEntity(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int line = rs.getInt("line");
        int deepness = rs.getInt("deepness");
        boolean isEnd = rs.getBoolean("isEnd");
        int start_work_hour = rs.getInt("start_work_hour");
        int end_work_hour = rs.getInt("end_work_hour");
        int start_work_minute = rs.getInt("start_work_minute");
        int end_work_minute = rs.getInt("end_work_minute");
        return new Station(id, name, line, deepness, isEnd, start_work_hour, start_work_minute, end_work_hour, end_work_minute);

    }
}