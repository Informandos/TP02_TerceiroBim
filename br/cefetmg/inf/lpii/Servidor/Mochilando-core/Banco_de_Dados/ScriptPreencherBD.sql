-- Estado
insert into estado (sigla, nom_estado) values ('MG', 'Minas Gerais');
insert into estado (sigla, nom_estado) values ('SP', 'São Paulo');
insert into estado (sigla, nom_estado) values ('RJ', 'Rio de Janeiro');

-- Cidade
insert into cidade (cod_estado, nom_cidade) values ((select cod_estado from estado where sigla like 'MG'), 'Belo Horizonte');
insert into cidade (cod_estado, nom_cidade) values ((select cod_estado from estado where sigla like 'SP'), 'São Paulo');
insert into cidade (cod_estado, nom_cidade) values ((select cod_estado from estado where sigla like 'RJ'), 'Rio de Janeiro');

-- Usuário
-- Senha: 1234567
insert into usuario (nom_usuario, sobrenome_usuario, txt_email, txt_senha, sexo, dat_nascimento, cod_cidade) values ('João', 'da Silva', 'joaosilva@email.com', 'fcea920f7412b5da7be0cf42b8c93759', 'M', '1990-03-07', (select cod_cidade from cidade where nom_cidade like 'São Paulo'));
-- Senha: 7654321
insert into usuario (nom_usuario, sobrenome_usuario, txt_email, txt_senha, sexo, dat_nascimento, cod_cidade) values ('Pedro', 'Alvares', 'pedroalvares@email.com', 'f0898af949a373e72a4f6a34b4de9090', 'M', '1994-11-26', (select cod_cidade from cidade where nom_cidade like 'Belo Horizonte'));
-- Senha: 1357926
insert into usuario (nom_usuario, sobrenome_usuario, txt_email, txt_senha, sexo, dat_nascimento, cod_cidade) values ('Maria', 'Pereira', 'mariapereira@email.com', '50cc5c8c54e4ddcfa4ef839dfec7f6a2', 'F', '1996-07-30', (select cod_cidade from cidade where nom_cidade like 'Rio de Janeiro'));

-- Diário
insert into diario (cod_usuario, nom_diario, dat_publicacao, dat_inicio_viagem, dat_fim_viagem, txt_diario) values ((select cod_usuario from usuario where nom_usuario like 'João'), 'Viagem à Belo Horizonte-MG', '2018-05-05', '2018-05-05', '2018-05-12', 'Viagem para BH');
insert into diario (cod_usuario, nom_diario, dat_publicacao, dat_inicio_viagem, dat_fim_viagem, txt_diario) values ((select cod_usuario from usuario where nom_usuario like 'Pedro'), 'Viagem à São Paulo-SP', '2018-06-04', '2018-06-04', '2018-06-11', 'Viagem para SP');
insert into diario (cod_usuario, nom_diario, dat_publicacao, dat_inicio_viagem, dat_fim_viagem, txt_diario) values ((select cod_usuario from usuario where nom_usuario like 'Maria'), 'Viagem ao Rio de Janeiro-RJ', '2018-04-07', '2018-04-07', '2018-04-14', 'Viagem para RJ');

-- Comentário
insert into comentario (cod_diario, cod_autor_comentario, dat_publicacao, txt_comentario) values ((select cod_diario from diario where nom_diario like 'Viagem à Belo Horizonte-MG'), (select cod_usuario from usuario where nom_usuario like 'Maria'), '2018-05-06', 'Que legal');
insert into comentario (cod_diario, cod_autor_comentario, dat_publicacao, txt_comentario) values ((select cod_diario from diario where nom_diario like 'Viagem à São Paulo-SP'), (select cod_usuario from usuario where nom_usuario like 'João'), '2018-06-05', 'Top');
insert into comentario (cod_diario, cod_autor_comentario, dat_publicacao, txt_comentario) values ((select cod_diario from diario where nom_diario like 'Viagem ao Rio de Janeiro-RJ'), (select cod_usuario from usuario where nom_usuario like 'Pedro'), '2018-04-08', 'Que bonito');

-- Dia
insert into dia (cod_diario, txt_dia, ordem_dia, data_dia) values ((select cod_diario from diario where nom_diario like 'Viagem à Belo Horizonte-MG'), 'Dia na Praça da Liberdade', 1, '2018-05-06');
insert into dia (cod_diario, txt_dia, ordem_dia, data_dia) values ((select cod_diario from diario where nom_diario like 'Viagem à São Paulo-SP'), 'Dia visitando o Parque Ibirapuera', 1, '2018-06-05');
insert into dia (cod_diario, txt_dia, ordem_dia, data_dia) values ((select cod_diario from diario where nom_diario like 'Viagem ao Rio de Janeiro-RJ'), 'Dia conhecendo o Cristo Redentor', 1, '2018-04-08');

-- Tipo Atração
insert into tipo_atracao (desc_tipo_atracao) values ('Praça');
insert into tipo_atracao (desc_tipo_atracao) values ('Parque');
insert into tipo_atracao (desc_tipo_atracao) values ('7 Maravilhas');

-- Atração
insert into atracao (cod_cidade_atracao, cod_tipo_atracao, nom_atracao, nro_latitude, nro_longitude) values ((select cod_cidade from cidade where nom_cidade like 'Belo Horizonte'), (select cod_tipo_atracao from tipo_atracao where desc_tipo_atracao like 'Praça'), 'Praça da Liberdade', 1, 1);
insert into atracao (cod_cidade_atracao, cod_tipo_atracao, nom_atracao, nro_latitude, nro_longitude) values ((select cod_cidade from cidade where nom_cidade like 'São Paulo'), (select cod_tipo_atracao from tipo_atracao where desc_tipo_atracao like 'Parque'), 'Parque Ibirapuera', 2, 2);
insert into atracao (cod_cidade_atracao, cod_tipo_atracao, nom_atracao, nro_latitude, nro_longitude) values ((select cod_cidade from cidade where nom_cidade like 'Rio de Janeiro'), (select cod_tipo_atracao from tipo_atracao where desc_tipo_atracao like '7 Maravilhas'), 'Cristo Redentor', 3, 3);

-- Dia Atração
insert into dia_atracao (seq_atracao, seq_dia) values ((select seq_atracao from atracao where nro_latitude = 1), (select seq_dia from dia where data_dia = '2018-05-06'));
insert into dia_atracao (seq_atracao, seq_dia) values ((select seq_atracao from atracao where nro_latitude = 2), (select seq_dia from dia where data_dia = '2018-06-05'));
insert into dia_atracao (seq_atracao, seq_dia) values ((select seq_atracao from atracao where nro_latitude = 3), (select seq_dia from dia where data_dia = '2018-04-08'));

-- Foto
insert into foto (seq_dia) values ((select seq_dia from dia where data_dia = '2018-05-06'));
insert into foto (seq_dia) values ((select seq_dia from dia where data_dia = '2018-06-05'));
insert into foto (seq_dia) values ((select seq_dia from dia where data_dia = '2018-04-08'));

-- Tag
insert into tag (desc_tag) values ('Cidade');
insert into tag (desc_tag) values ('Parque');
insert into tag (desc_tag) values ('Montanha');
insert into tag (desc_tag) values ('Interior');
insert into tag (desc_tag) values ('Praça');
insert into tag (desc_tag) values ('7 Maravilhas');
insert into tag (desc_tag) values ('Museu');
insert into tag (desc_tag) values ('Praia');	

-- Tag Diário
insert into tag_diario (cod_diario, cod_tag) values ((select cod_diario from diario where nom_diario like 'Viagem à Belo Horizonte-MG'), (select cod_tag from tag where desc_tag like 'Praça'));
insert into tag_diario (cod_diario, cod_tag) values ((select cod_diario from diario where nom_diario like 'Viagem à São Paulo-SP'), (select cod_tag from tag where desc_tag like 'Parque'));
insert into tag_diario (cod_diario, cod_tag) values ((select cod_diario from diario where nom_diario like 'Viagem ao Rio de Janeiro-RJ'), (select cod_tag from tag where desc_tag like '7 Maravilhas'));

-- Usuário Tag
insert into usuario_tag (cod_usuario, cod_tag) values ((select cod_usuario from usuario where nom_usuario like 'João'), (select cod_tag from tag where desc_tag like 'Belo Horizonte'));
insert into usuario_tag (cod_usuario, cod_tag) values ((select cod_usuario from usuario where nom_usuario like 'Pedro'), (select cod_tag from tag where desc_tag like 'São Paulo'));
insert into usuario_tag (cod_usuario, cod_tag) values ((select cod_usuario from usuario where nom_usuario like 'Maria'), (select cod_tag from tag where desc_tag like 'Rio de Janeiro'));

-- Avaliação Comentário
insert into avaliacao_comentario (seq_comentario, cod_usuario, avaliacao) values ((select seq_comentario from comentario where txt_comentario like 'Top'), (select cod_usuario from usuario where nom_usuario like 'João'), 'N');
insert into avaliacao_comentario (seq_comentario, cod_usuario, avaliacao) values ((select seq_comentario from comentario where txt_comentario like 'Que bonito'), (select cod_usuario from usuario where nom_usuario like 'Pedro'), 'P');
insert into avaliacao_comentario (seq_comentario, cod_usuario, avaliacao) values ((select seq_comentario from comentario where txt_comentario like 'Que legal'), (select cod_usuario from usuario where nom_usuario like 'Maria'), 'P');

-- Avaliação Diário
insert into avaliacao_diario (cod_diario, cod_usuario, avaliacao) values ((select cod_diario from diario where nom_diario like 'Viagem à Belo Horizonte-MG'), (select cod_usuario from usuario where nom_usuario like 'João'), 'P');
insert into avaliacao_diario (cod_diario, cod_usuario, avaliacao) values ((select cod_diario from diario where nom_diario like 'Viagem à São Paulo-SP'), (select cod_usuario from usuario where nom_usuario like 'Pedro'), 'N');
insert into avaliacao_diario (cod_diario, cod_usuario, avaliacao) values ((select cod_diario from diario where nom_diario like 'Viagem ao Rio de Janeiro-RJ'), (select cod_usuario from usuario where nom_usuario like 'Maria'), 'N');