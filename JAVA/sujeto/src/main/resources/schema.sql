CREATE TABLE SUJETO
(
    ID       BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    NOMBRE    VARCHAR(100),
    APELLIDO  VARCHAR(100),
    DNI       VARCHAR(100),
);