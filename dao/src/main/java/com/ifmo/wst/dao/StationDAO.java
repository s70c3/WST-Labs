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

    public List<Station> findAll() {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT *  FROM metro_stations");
            List<Station> result = rsToEntities(statement.getResultSet());
            System.out.println(result);
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


    public List<Station> findById(int id) {
        try {
            Statement statement = connection.createStatement();
            statement.execute("SELECT *  FROM metro_stations where id="+String.valueOf(id));
            List<Station> result = rsToEntities(statement.getResultSet());
            System.out.println(result);
            if (result.isEmpty()) return null;
            return result;

        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }


    public long create(String name, Boolean isend, String line, String city, String type) {
        try {
            connection.setAutoCommit(false);
            long newId;
            try (Statement idStatement = connection.createStatement()) {
                idStatement.execute("SELECT nextval('metro_stations_id_seq') nextval");
                try (ResultSet rs = idStatement.getResultSet()) {
                    rs.next();
                    newId = rs.getLong("nextval");
                }
            }
            try (PreparedStatement stmnt = connection.prepareStatement(
                    "INSERT INTO metro_stations(id, name,  isend, line, city, station_type) " +
                            "VALUES ( ?, ?, ?, ?, ?,?)")) {
                stmnt.setLong(1, newId);
                stmnt.setString(2, name);
                stmnt.setBoolean(3, isend);
                stmnt.setString(4, line);
                stmnt.setString(5, city);
                stmnt.setString(6, type);
                int count = stmnt.executeUpdate();
                if (count == 0) {
                    throw new RuntimeException("Could not execute query");
                }
            }
            connection.commit();
            connection.setAutoCommit(true);
            return newId;
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public List<Station> read(String name, String city, String line, Boolean isend, String type) {


        if (Stream.of(name, line, isend, city, type).allMatch(Objects::isNull)) {
            return findAll();
        }
        Query query = new BuildQuery()
                .tableName(TABLE_NAME)
                .selectColumns(ID_COLUMN, NAME_COLUMN, LINE_COLUMN, END_COLUMN, TYPE_COLUMN, CITY_COLUMN)
                .condition(new Condition(NAME_COLUMN, name, String.class))
                .condition(new Condition(LINE_COLUMN, line, String.class))
                .condition(new Condition(END_COLUMN, isend, Boolean.class))
                .condition(new Condition(TYPE_COLUMN, type, String.class))
                .condition(new Condition(CITY_COLUMN, city, String.class))
                .buildReadStatementQuery();


        try {
            PreparedStatement ps = connection.prepareStatement(query.getQueryString());
            query.initPreparedStatement(ps);
            ResultSet rs = ps.executeQuery();
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE,rs.toString());
            return rsToEntities(rs);
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public int update(long id, String name, Boolean isend, String line, String city, String type) {
        try {
            connection.setAutoCommit(true);
            String updateStr = "UPDATE metro_stations SET name = ?, isend = ?, line = ?, city = ?, station_type = ? WHERE id = ?";
            try (PreparedStatement stmnt = connection.prepareStatement(updateStr)) {
                stmnt.setString(1, name);
                stmnt.setBoolean(2, isend);
                stmnt.setString(3, line);
                stmnt.setString(4, city);
                stmnt.setString(5, type);
                stmnt.setLong(6, id);
                int updated = stmnt.executeUpdate();
                return updated;
            }
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
        }
    }

    public int delete(long id) {
        try {
            connection.setAutoCommit(true);
            try (PreparedStatement ps = connection.prepareStatement("DELETE FROM metro_stations WHERE id = ?")) {
                ps.setLong(1, id);
                return ps.executeUpdate();
            }
        } catch (SQLException ex) {
            Logger.getLogger(StationDAO.class.getName()).log(Level.SEVERE, null, ex);
            return -1;
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
        String line = rs.getString("line");
        String type = rs.getString("station_type");
        String city = rs.getString("city");
        Boolean isEnd = rs.getBoolean("isend");

        return new Station(id, name, line, isEnd, type, city);

    }
}