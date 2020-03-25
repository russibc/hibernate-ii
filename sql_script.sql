DROP DATABASE bruna; 

CREATE DATABASE bruna; 

USE bruna; 

CREATE TABLE pessoa 
  ( 
     id           INT(11) NOT NULL, 
     ultimonome   VARCHAR(255) NOT NULL, 
     primeironome VARCHAR(255) NOT NULL, 
     idade        INT(11) NOT NULL, 
     profissao    VARCHAR(30) NOT NULL, 
     PRIMARY KEY (id) 
  ); 

CREATE TABLE pedido 
  ( 
     id           INT(11) NOT NULL, 
     numeropedido INT(11) NOT NULL, 
     pessoaId     INT(11) NOT NULL, 
     valor        INT(11) NOT NULL, 
     PRIMARY KEY (id), 
     FOREIGN KEY (pessoaId) REFERENCES pessoa(id) 
  ); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (1, 
             'Cavalcante', 
             'Tamer', 
             28, 
             'Professor'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (2, 
             'Bueno', 
             'Lucas', 
             25, 
             'Professor'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (3, 
             'Wachinski', 
             'Glaucio', 
             39, 
             'Professor'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (4, 
             'Barreto', 
             'Luciano', 
             33, 
             'Professor'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (5, 
             'Pereira', 
             'Flavio', 
             40, 
             'Professor'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (6, 
             'Barbosa', 
             'Denilson', 
             40, 
             'Professor'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (7, 
             'Temer', 
             'Michel', 
             99, 
             'Presidente'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (8, 
             'Buarque', 
             'Chico', 
             73, 
             'Músico'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (9, 
             'Carlos', 
             'Roberto', 
             76, 
             'Músico'); 

INSERT INTO pessoa 
            (id, 
             ultimonome, 
             primeironome, 
             idade, 
             profissao) 
VALUES      (10, 
             'Junior', 
             'Neymar', 
             25, 
             'Jogador'); 

INSERT INTO pedido 
            (id, 
             numeropedido, 
             pessoaid, 
             valor) 
VALUES      (1, 
             123, 
             1, 
             3123); 

INSERT INTO pedido 
            (id, 
             numeropedido, 
             pessoaid, 
             valor) 
VALUES      (2, 
             124, 
             1, 
             13445); 

INSERT INTO pedido 
            (id, 
             numeropedido, 
             pessoaid, 
             valor) 
VALUES      (3, 
             125, 
             1, 
             12344); 

INSERT INTO pedido 
            (id, 
             numeropedido, 
             pessoaid, 
             valor) 
VALUES      (4, 
             126, 
             2, 
             87879); 

INSERT INTO pedido 
            (id, 
             numeropedido, 
             pessoaid, 
             valor) 
VALUES      (5, 
             127, 
             2, 
             12266); 

INSERT INTO pedido 
            (id, 
             numeropedido, 
             pessoaid, 
             valor) 
VALUES      (6, 
             128, 
             3, 
             223); 

INSERT INTO pedido 
            (id, 
             numeropedido, 
             pessoaid, 
             valor) 
VALUES      (7, 
             129, 
             3, 
             77898); 

SELECT DISTINCT profissao 
FROM   pessoa; 

SELECT primeironome 
FROM   pessoa 
WHERE  ( idade > 35 ); 

SELECT ultimonome 
FROM   pessoa 
WHERE  profissao LIKE 'p%'; 

SELECT ultimonome 
FROM   pessoa 
WHERE  ( idade >= 30 ) 
       AND ( idade <= 40 ); 

SELECT * 
FROM   pessoa 
WHERE  id IN ( 1, 5, 7, 10 ); 

CREATE TABLE endereco 
  ( 
     id     INT(11) NOT NULL PRIMARY KEY, 
     rua    VARCHAR(250) NOT NULL, 
     cidade VARCHAR(50) NOT NULL, 
     estado VARCHAR(50) NOT NULL, 
     cep    VARCHAR(10) NOT NULL 
  ); 

CREATE TABLE aluno 
  ( 
     id         INT(11) NOT NULL PRIMARY KEY, 
     nome       VARCHAR(100) NOT NULL, 
     enderecoid INT(11) NOT NULL, 
     FOREIGN KEY(enderecoid) REFERENCES endereco(id) 
  ); 