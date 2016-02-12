CREATE SEQUENCE seq_courses START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS "public"."course" (
  "id"         INT8 DEFAULT nextval('seq_courses' :: REGCLASS) NOT NULL PRIMARY KEY,
  "name" VARCHAR(20),
  "lecturer_name"  VARCHAR(20),
  "hours"     INT8);