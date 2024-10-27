create table if not exists teams
(
    id   serial
    primary key,
    name varchar(15) not null
    );

create table if not exists drivers
(
    id         serial
    primary key,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    team_id    integer
    constraint teams___fk
    references teams
    );

create table if not exists races
(
    id   serial
    primary key,
    name varchar(20) not null
    );

create table if not exists driver_races
(
    id           serial
    primary key,
    driver_id    integer  not null
    constraint driver_races___driver_id___fk
    references drivers,
    race_id      integer  not null
    constraint driver_races___racer_id___fk
    references races,
    finished_for time,
    points       smallint not null
);