package com.ifmo.wst.dao;


import com.ifmo.wst.entity.Station;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimplePostgresSQLDAO {

    private Connection connection;

    public SimplePostgresSQLDAO(Connection connection) {
        this.connection = connection;
    }

    public SimplePostgresSQLDAO() {
       this.connection = ConnectionUtil.getConnection();

    }
    public static Station getStationInfo(ResultSet rs) {
        Station station;

        try {
            String name = rs.getString("name");
            String line = rs.getString("line");
            String type = rs.getString("type");
            String city = rs.getString("city");
            Boolean isEnd = rs.getBoolean("isEnd");

            return new Station(name, line, isEnd, type, city);
        } catch (SQLException ex) {
            Logger.getLogger(SimplePostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<Station> getStationsByQuery(Connection connection, String query) {
        List<Station> stations = new ArrayList<Station>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Station station = SimplePostgresSQLDAO.getStationInfo(rs);
                stations.add(station);
            }
        } catch (SQLException ex) {
            Logger.getLogger(SimplePostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stations;
    }


    public List<Station> getAllStations() {
        return SimplePostgresSQLDAO.getStationsByQuery(connection, "Select * from metro_stations");
    }

    public Station getStationByName(String name) {
        Station station = SimplePostgresSQLDAO.getStationsByQuery(connection, "SELECT * FROM metro_stations where name=" + name).get(0);
        return station;
    }

    public List<Station> getStationsByLine(int line) {
        return SimplePostgresSQLDAO.getStationsByQuery(connection, "SELECT * FROM metro_stations where line=" + line);
    }

    public List<Station> getStationsBySmth(String parameters) {
        return SimplePostgresSQLDAO.getStationsByQuery(connection, "SELECT * FROM metro_stations where " + parameters + "=" + parameters);

    }

}
