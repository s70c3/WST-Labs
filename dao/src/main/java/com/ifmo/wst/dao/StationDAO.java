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
    private final String NAME_COLUMN = "name";
    private final String END_COLUMN = "isend";
    private final String TYPE_COLUMN = "station_type";
    private final String LINE_COLUMN = "line";
    private final String CITY_COLUMN = "city";

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

    public List<Station> read(String name,  String city, String line, Boolean isend, String type) throws SQLException {

        Logger.getLogger(SimplePostgresSQLDAO.class.getName()).log(Level.SEVERE, name+city+line+type);

        if (Stream.of(name, line, isend, city, type).allMatch(Objects::isNull)) {
            return findAll();
        }
        Query query = new BuildQuery()
                .tableName(TABLE_NAME)
                .selectColumns(NAME_COLUMN, LINE_COLUMN, END_COLUMN, TYPE_COLUMN, CITY_COLUMN)
                .condition(new Condition(NAME_COLUMN, name, String.class))
                .condition(new Condition(LINE_COLUMN, line, String.class))
                .condition(new Condition(END_COLUMN, isend, Boolean.class))
                .condition(new Condition(TYPE_COLUMN, type, String.class))
                .condition(new Condition(CITY_COLUMN, city, String.class))
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
        String name = rs.getString("name");
        String line = rs.getString("line");
        String type = rs.getString("station_type");
        String city = rs.getString("city");
        Boolean isEnd = rs.getBoolean("isend");

        return new Station(name, line, isEnd, type, city);

    }
}