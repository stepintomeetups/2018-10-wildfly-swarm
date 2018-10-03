insert into world (id, name) values ('OUTSIDE', 'Való világ');
insert into world (id, name) values ('WESTWORLD', 'West World');
insert into world (id, name) values ('SHOGUNWORLD', 'Shōgunworld');
insert into world (id, name) values ('THERAJ', 'The Raj');

-- Való világ
insert into place (id, world_id, name, sector) values ('COLD_STORAGE', 'OUTSIDE', 'Hűtőház', 'C0');
insert into place (id, world_id, name, sector) values ('CONTROL_ROOM', 'OUTSIDE', 'Vezérlő terem', 'C0');

-- WestWorld
insert into place (id, world_id, name, sector) values ('SWEETWATER', 'WESTWORLD', 'Sweetwater', 'C2');
insert into place (id, world_id, name, sector) values ('ESCALANTE', 'WESTWORLD', 'Escalante', 'C10');
insert into place (id, world_id, name, sector) values ('LAS_MUDAS', 'WESTWORLD', 'Las Mudas', 'C3');
insert into place (id, world_id, name, sector) values ('MARIPOSA_SALOON', 'WESTWORLD', 'Mariposa Saloon', 'C2');
insert into place (id, world_id, name, sector) values ('TRAIN_PLATFORM', 'WESTWORLD', 'Train Platform', 'C1');

-- ShogunWorld
insert into place (id, world_id, name, sector) values ('OLD_TOWN', 'SHOGUNWORLD', 'Old Town', 'C12');

-- The Raj
insert into place (id, world_id, name, sector) values ('THE_CAMP', 'THERAJ', 'The Camp', 'C26');