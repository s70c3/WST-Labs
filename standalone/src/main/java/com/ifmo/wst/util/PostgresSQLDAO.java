package com.ifmo.wst.util;

import com.ifmo.wst.standalone.ConnectionUtil;
import com.ifmo.wst.entity.Station;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PostgresSQLDAO {
    public static  Station getStationInfo(ResultSet rs) {
        Station station;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int line = rs.getInt("line");
            int deepness = rs.getInt("deepness");
            boolean isEnd = rs.getBoolean("isEnd");
            int start_work_hour = rs.getInt("start_work_hour");
            int end_work_hour = rs.getInt("end_work_hour");
            int start_work_minute = rs.getInt("start_work_minute");
            int end_work_minute = rs.getInt("end_work_minute");
            return new Station(id,  name, line, deepness, isEnd, start_work_hour, start_work_minute, end_work_hour, end_work_minute);

        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static List<Station> getStationsByQuery(Connection connection, String query) {
        List<Station> stations = new ArrayList<Station>();

        try {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Station station = PostgresSQLDAO.getStationInfo(rs);
                stations.add(station);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PostgresSQLDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return stations;
    }

    public List<Station> getStations() {
        List<Station> stations = new ArrayList<Station>();
        Connection connection = ConnectionUtil.getConnection();
        return PostgresSQLDAO.getStationsByQuery(connection, "Select * from metro_stations");
    }

}
