CREATE ROLE demo_host LOGIN PASSWORD 'demo_host' NOINHERIT CREATEDB;
CREATE SCHEMA demo_host AUTHORIZATION demo_host;

CREATE ROLE demo_world LOGIN PASSWORD 'demo_world' NOINHERIT CREATEDB;
CREATE SCHEMA demo_world AUTHORIZATION demo_world;

CREATE ROLE demo_guest LOGIN PASSWORD 'demo_guest' NOINHERIT CREATEDB;
CREATE SCHEMA demo_guest AUTHORIZATION demo_guest;

CREATE ROLE demo_story LOGIN PASSWORD 'demo_story' NOINHERIT CREATEDB;
CREATE SCHEMA demo_story AUTHORIZATION demo_story;

CREATE ROLE demo_dashboard LOGIN PASSWORD 'demo_dashboard' NOINHERIT CREATEDB;
CREATE SCHEMA demo_dashboard AUTHORIZATION demo_dashboard;
