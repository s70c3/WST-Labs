package com.ifmo.wst.dao;

import com.ifmo.wst.entity.Station;
import com.ifmo.wst.query.BuildQuery;
import com.ifmo.wst.query.Query;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

//import com.ifmo.wst.query.Query;
//import com.ifmo.wst.query.BuildQuery;
//

public class StationDAO {

    private final String TABLE_NAME = "metro_stations";
    private final String ID_COLUMN = "id";
    private final String NAME_COLUMN = "name";
    private final String DEEPNESS_COLUMN = "deepness";
    private final String ISEND_COLUMN = "isend";
    private final String LINE_COLUMN = "line";
    private final String START_HOUR_COLUMN = "start_work_hour";
    private final String START_MINUTE_COLUMN = "start_work_minute";

    private final String END_HOUR_COLUMN = "end_work_hour";
    private final String END_MINUTE_COLUMN = "end_work_minute";

    private final Connection connection;

    public StationDAO(Connection connection) {
        this.connection = connection;
    }

    public StationDAO() {
        this.connection = ConnectionUtil.getConnection();

    }

    public List<Station> findAll() throws SQLException {
//        log.debug("Find all query");
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT *  FROM metro_stations");
            List<Station> result = rsToEntities(statement.getResultSet());
            System.out.println(result);
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(SimplePostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public List<Station> filter(Integer id, String name, Integer line, Integer deepness, Boolean isEnd, Integer startWorkHour, Integer startWorkMinute, Integer endWorkHour, Integer endWorkMinute) throws SQLException {


        if (Stream.of(id, name, line, deepness, isEnd, startWorkHour, startWorkMinute, endWorkHour, endWorkMinute).allMatch(Objects::isNull)) {
            return findAll();
        }


        Query query = new BuildQuery()
                .tableName(TABLE_NAME)
                .selectColumns(ID_COLUMN, NAME_COLUMN, DEEPNESS_COLUMN, LINE_COLUMN, ISEND_COLUMN, START_HOUR_COLUMN, START_MINUTE_COLUMN, END_HOUR_COLUMN, END_MINUTE_COLUMN)
                .condition(new Condition(ID_COLUMN, id, Integer.class))
                .condition(new Condition(NAME_COLUMN, name, String.class))
                .condition(new Condition(LINE_COLUMN, line, Integer.class))
                .condition(new Condition(ISEND_COLUMN, isEnd, Boolean.class))
                .condition(new Condition(DEEPNESS_COLUMN, deepness, Integer.class))
                .condition(new Condition(START_HOUR_COLUMN, startWorkHour, Integer.class))
                .condition(new Condition(START_MINUTE_COLUMN, startWorkMinute, Integer.class))
                .condition(new Condition(END_HOUR_COLUMN, startWorkHour, Integer.class))
                .condition(new Condition(END_MINUTE_COLUMN, startWorkMinute, Integer.class))
                .buildPreparedStatementQuery();

        try {
            PreparedStatement ps = connection.prepareStatement(query.getQueryString());
            query.initPreparedStatement(ps);
            ResultSet rs = ps.executeQuery();
            return rsToEntities(rs);
        } catch (SQLException ex) {
            Logger.getLogger(SimplePostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }



    private List<Station> rsToEntities(ResultSet rs) throws SQLException {
        List<Station> result = new ArrayList<>();
        while (rs.next()) {
            result.add(resultSetToEntity(rs));
        }
        return result;
    }

    private Station resultSetToEntity(ResultSet rs) throws SQLException {
        Integer id = rs.getInt("id");
        String name = rs.getString("name");
        Integer line = rs.getInt("line");
        Integer deepness = rs.getInt("deepness");
        boolean isEnd = rs.getBoolean("isEnd");
        int start_work_hour = rs.getInt("start_work_hour");
        int end_work_hour = rs.getInt("end_work_hour");
        int start_work_minute = rs.getInt("start_work_minute");
        int end_work_minute = rs.getInt("end_work_minute");
        return new Station(id, name, line, deepness, isEnd, start_work_hour, start_work_minute, end_work_hour, end_work_minute);

    }
}