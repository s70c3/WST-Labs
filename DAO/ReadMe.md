# Data access for metro stations.

## Entity

```create table metro_stations
(
  id           bigserial not null
    constraint stations_pkey
    primary key,
  name         varchar(200),
  isend        boolean,
  city         varchar(200),
  line         varchar(200),
  station_type varchar(200)
);
```
