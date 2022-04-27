CREATE SEQUENCE hibernate_sequence START 1;

CREATE TABLE sectors
(
    id        bigint PRIMARY KEY,
    version   bigint       NOT NULL DEFAULT 0,
    name      varchar(255) NOT NULL,
    parent_sector_id bigint REFERENCES sectors
);

CREATE TABLE sector_entries
(
    id              bigint PRIMARY KEY,
    version         bigint       NOT NULL DEFAULT 0,
    name            varchar(255) NOT NULL,
    agreed_to_terms boolean      NOT NULL
);

CREATE TABLE sector_entry_sectors
(
    sector_entry_id bigint REFERENCES sector_entries,
    sector_id       bigint REFERENCES sectors,
    PRIMARY KEY (sector_entry_id, sector_id)
);