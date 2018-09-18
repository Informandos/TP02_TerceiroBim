CREATE TABLE "atracao" (
"seq_atracao" int8 NOT NULL DEFAULT nextval('atracao_seq_atracao_seq'::regclass),
"cod_cidade_atracao" int8 NOT NULL DEFAULT nextval('atracao_cod_cidade_atracao_seq'::regclass),
"cod_tipo_atracao" int4 NOT NULL DEFAULT nextval('atracao_cod_tipo_atracao_seq'::regclass),
"nom_atracao" varchar COLLATE "default" NOT NULL,
"nro_latitude" float8 NOT NULL,
"nro_longitude" float8 NOT NULL,
CONSTRAINT "atracao_pkey" PRIMARY KEY ("seq_atracao", "cod_cidade_atracao", "cod_tipo_atracao") ,
CONSTRAINT "atracao_seq_atracao_key" UNIQUE ("seq_atracao")
)
WITHOUT OIDS;

ALTER TABLE "atracao" OWNER TO "postgres";

CREATE TABLE "avaliacao_comentario" (
"seq_avaliacao" int8 NOT NULL DEFAULT nextval('avaliacao_comentario_seq_avaliacao_seq'::regclass),
"seq_comentario" int8 NOT NULL DEFAULT nextval('avaliacao_comentario_seq_comentario_seq'::regclass),
"cod_usuario" int8 NOT NULL DEFAULT nextval('avaliacao_comentario_cod_usuario_seq'::regclass),
"avaliacao" char(1) COLLATE "default" NOT NULL,
CONSTRAINT "avaliacao_comentario_pkey" PRIMARY KEY ("seq_avaliacao", "seq_comentario", "cod_usuario") ,
CONSTRAINT "avaliacao_comentario_seq_avaliacao_key" UNIQUE ("seq_avaliacao")
)
WITHOUT OIDS;

ALTER TABLE "avaliacao_comentario" OWNER TO "postgres";

CREATE TABLE "avaliacao_diario" (
"seq_avaliacao" int8 NOT NULL DEFAULT nextval('avaliacao_diario_seq_avaliacao_seq'::regclass),
"cod_diario" int8 NOT NULL DEFAULT nextval('avaliacao_diario_cod_diario_seq'::regclass),
"cod_usuario" int8 NOT NULL DEFAULT nextval('avaliacao_diario_cod_usuario_seq'::regclass),
"avaliacao" char(1) COLLATE "default" NOT NULL,
CONSTRAINT "avaliacao_diario_pkey" PRIMARY KEY ("seq_avaliacao", "cod_diario", "cod_usuario") ,
CONSTRAINT "avaliacao_diario_seq_avaliacao_key" UNIQUE ("seq_avaliacao")
)
WITHOUT OIDS;

ALTER TABLE "avaliacao_diario" OWNER TO "postgres";

CREATE TABLE "cidade" (
"cod_cidade" int4 NOT NULL DEFAULT nextval('cidade_cod_cidade_seq'::regclass),
"cod_estado" int2 NOT NULL DEFAULT nextval('cidade_cod_estado_seq'::regclass),
"nom_cidade" varchar(255) COLLATE "default" NOT NULL,
CONSTRAINT "cidade_pkey" PRIMARY KEY ("cod_cidade", "cod_estado") ,
CONSTRAINT "cidade_cod_cidade_key" UNIQUE ("cod_cidade")
)
WITHOUT OIDS;

ALTER TABLE "cidade" OWNER TO "postgres";

CREATE TABLE "comentario" (
"seq_comentario" int8 NOT NULL DEFAULT nextval('comentario_seq_comentario_seq'::regclass),
"cod_diario" int8 NOT NULL DEFAULT nextval('comentario_cod_diario_seq'::regclass),
"cod_autor_comentario" int8 NOT NULL DEFAULT nextval('comentario_cod_autor_comentario_seq'::regclass),
"dat_publicacao" date,
"txt_comentario" varchar COLLATE "default" NOT NULL,
CONSTRAINT "comentario_pkey" PRIMARY KEY ("seq_comentario", "cod_diario", "cod_autor_comentario") ,
CONSTRAINT "comentario_seq_comentario_key" UNIQUE ("seq_comentario")
)
WITHOUT OIDS;

ALTER TABLE "comentario" OWNER TO "postgres";

CREATE TABLE "dia" (
"seq_dia" int8 NOT NULL DEFAULT nextval('dia_seq_dia_seq'::regclass),
"cod_diario" int8 NOT NULL DEFAULT nextval('dia_cod_diario_seq'::regclass),
"txt_dia" varchar COLLATE "default" NOT NULL,
"ordem_dia" int4,
"data_dia" varchar(255) COLLATE "default",
CONSTRAINT "dia_pkey" PRIMARY KEY ("seq_dia", "cod_diario") ,
CONSTRAINT "dia_seq_dia_key" UNIQUE ("seq_dia")
)
WITHOUT OIDS;

ALTER TABLE "dia" OWNER TO "postgres";

CREATE TABLE "dia_atracao" (
"seq_dia_atracao" int8 NOT NULL DEFAULT nextval('dia_atracao_seq_dia_atracao_seq'::regclass),
"seq_atracao" int8 NOT NULL DEFAULT nextval('dia_atracao_seq_atracao_seq'::regclass),
"seq_dia" int8 NOT NULL DEFAULT nextval('dia_atracao_seq_dia_seq'::regclass),
CONSTRAINT "dia_atracao_pkey" PRIMARY KEY ("seq_dia_atracao", "seq_atracao", "seq_dia") ,
CONSTRAINT "dia_atracao_seq_dia_atracao_key" UNIQUE ("seq_dia_atracao")
)
WITHOUT OIDS;

ALTER TABLE "dia_atracao" OWNER TO "postgres";

CREATE TABLE "diario" (
"cod_diario" int8 NOT NULL DEFAULT nextval('diario_cod_diario_seq'::regclass),
"cod_usuario" int8 NOT NULL DEFAULT nextval('diario_cod_usuario_seq'::regclass),
"nom_diario" varchar COLLATE "default" NOT NULL,
"dat_publicacao" date,
"dat_inicio_viagem" date,
"dat_fim_viagem" date,
"txt_diario" varchar COLLATE "default" NOT NULL,
"tipo_diario" char NOT NULL,
CONSTRAINT "diario_pkey" PRIMARY KEY ("cod_diario", "cod_usuario") ,
CONSTRAINT "diario_cod_diario_key" UNIQUE ("cod_diario")
)
WITHOUT OIDS;

ALTER TABLE "diario" OWNER TO "postgres";

CREATE TABLE "estado" (
"cod_estado" int2 NOT NULL DEFAULT nextval('estado_cod_estado_seq'::regclass),
"sigla" varchar(2) COLLATE "default" NOT NULL,
"nom_estado" varchar(30) COLLATE "default" NOT NULL,
CONSTRAINT "estado_pkey" PRIMARY KEY ("cod_estado") 
)
WITHOUT OIDS;

ALTER TABLE "estado" OWNER TO "postgres";

CREATE TABLE "foto" (
"seq_foto" int8 NOT NULL DEFAULT nextval('foto_seq_foto_seq'::regclass),
"seq_dia" int8 NOT NULL DEFAULT nextval('foto_seq_dia_seq'::regclass),
"foto" bytea,
CONSTRAINT "foto_pkey" PRIMARY KEY ("seq_foto", "seq_dia") ,
CONSTRAINT "foto_seq_foto_key" UNIQUE ("seq_foto")
)
WITHOUT OIDS;

ALTER TABLE "foto" OWNER TO "postgres";

CREATE TABLE "tag" (
"cod_tag" int4 NOT NULL DEFAULT nextval('tag_cod_tag_seq'::regclass),
"desc_tag" varchar COLLATE "default" NOT NULL,
CONSTRAINT "tag_pkey" PRIMARY KEY ("cod_tag") 
)
WITHOUT OIDS;

ALTER TABLE "tag" OWNER TO "postgres";

CREATE TABLE "tag_diario" (
"seq_tag_diario" int8 NOT NULL DEFAULT nextval('tag_diario_seq_tag_diario_seq'::regclass),
"cod_diario" int8 NOT NULL DEFAULT nextval('tag_diario_cod_diario_seq'::regclass),
"cod_tag" int8 NOT NULL DEFAULT nextval('tag_diario_cod_tag_seq'::regclass),
CONSTRAINT "tag_diario_pkey" PRIMARY KEY ("seq_tag_diario", "cod_diario", "cod_tag") ,
CONSTRAINT "tag_diario_seq_tag_diario_key" UNIQUE ("seq_tag_diario")
)
WITHOUT OIDS;

ALTER TABLE "tag_diario" OWNER TO "postgres";

CREATE TABLE "tipo_atracao" (
"cod_tipo_atracao" int2 NOT NULL DEFAULT nextval('tipo_atracao_cod_tipo_atracao_seq'::regclass),
"desc_tipo_atracao" varchar COLLATE "default" NOT NULL,
CONSTRAINT "tipo_atracao_pkey" PRIMARY KEY ("cod_tipo_atracao") 
)
WITHOUT OIDS;

ALTER TABLE "tipo_atracao" OWNER TO "postgres";

CREATE TABLE "usuario" (
"cod_usuario" int8 NOT NULL DEFAULT nextval('usuario_cod_usuario_seq'::regclass),
"nom_usuario" varchar(255) COLLATE "default" NOT NULL,
"sobrenome_usuario" varchar(255) COLLATE "default" NOT NULL,
"txt_email" varchar(255) COLLATE "default" NOT NULL,
"txt_senha" varchar(255) COLLATE "default" NOT NULL,
"img_perfil" bytea,
"sexo" varchar(9) COLLATE "default" NOT NULL,
"dat_nascimento" date,
"cod_cidade" int4 NOT NULL DEFAULT nextval('usuario_cod_cidade_seq'::regclass),
CONSTRAINT "usuario_pkey" PRIMARY KEY ("cod_usuario") 
)
WITHOUT OIDS;

ALTER TABLE "usuario" OWNER TO "postgres";

CREATE TABLE "usuario_tag" (
"seq_usuario_tag" int8 NOT NULL DEFAULT nextval('usuario_tag_seq_usuario_tag_seq'::regclass),
"cod_usuario" int8 NOT NULL DEFAULT nextval('usuario_tag_cod_usuario_seq'::regclass),
"cod_tag" int4 NOT NULL DEFAULT nextval('usuario_tag_cod_tag_seq'::regclass),
CONSTRAINT "usuario_tag_pkey" PRIMARY KEY ("seq_usuario_tag", "cod_usuario", "cod_tag") ,
CONSTRAINT "usuario_tag_seq_usuario_tag_key" UNIQUE ("seq_usuario_tag")
)
WITHOUT OIDS;

ALTER TABLE "usuario_tag" OWNER TO "postgres";


ALTER TABLE "atracao" ADD CONSTRAINT "_copy_1" FOREIGN KEY ("cod_tipo_atracao") REFERENCES "tipo_atracao" ("cod_tipo_atracao") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "atracao" ADD CONSTRAINT "atracao_cod_cidade_atracao_fkey" FOREIGN KEY ("cod_cidade_atracao") REFERENCES "cidade" ("cod_cidade") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "avaliacao_comentario" ADD CONSTRAINT "avaliacao_comentario_seq_comentario_fkey" FOREIGN KEY ("seq_comentario") REFERENCES "comentario" ("seq_comentario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "avaliacao_comentario" ADD CONSTRAINT "_copy_1" FOREIGN KEY ("cod_usuario") REFERENCES "usuario" ("cod_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "avaliacao_diario" ADD CONSTRAINT "avaliacao_diario_cod_diario_fkey" FOREIGN KEY ("cod_diario") REFERENCES "diario" ("cod_diario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "avaliacao_diario" ADD CONSTRAINT "_copy_1" FOREIGN KEY ("cod_usuario") REFERENCES "usuario" ("cod_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "cidade" ADD CONSTRAINT "cidade_cod_estado_fkey" FOREIGN KEY ("cod_estado") REFERENCES "estado" ("cod_estado") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "comentario" ADD CONSTRAINT "_copy_1" FOREIGN KEY ("cod_autor_comentario") REFERENCES "usuario" ("cod_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "comentario" ADD CONSTRAINT "comentario_cod_diario_fkey" FOREIGN KEY ("cod_diario") REFERENCES "diario" ("cod_diario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "dia" ADD CONSTRAINT "dia_cod_diario_fkey" FOREIGN KEY ("cod_diario") REFERENCES "diario" ("cod_diario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "dia_atracao" ADD CONSTRAINT "_copy_1" FOREIGN KEY ("seq_dia") REFERENCES "dia" ("seq_dia") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "dia_atracao" ADD CONSTRAINT "dia_atracao_seq_atracao_fkey" FOREIGN KEY ("seq_atracao") REFERENCES "atracao" ("seq_atracao") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "diario" ADD CONSTRAINT "diario_cod_usuario_fkey" FOREIGN KEY ("cod_usuario") REFERENCES "usuario" ("cod_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "foto" ADD CONSTRAINT "foto_seq_dia_fkey" FOREIGN KEY ("seq_dia") REFERENCES "dia" ("seq_dia") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "tag_diario" ADD CONSTRAINT "tag_diario_cod_diario_fkey" FOREIGN KEY ("cod_diario") REFERENCES "diario" ("cod_diario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "tag_diario" ADD CONSTRAINT "_copy_1" FOREIGN KEY ("cod_tag") REFERENCES "tag" ("cod_tag") MATCH FULL ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "usuario" ADD CONSTRAINT "usuario_cod_cidade_fkey" FOREIGN KEY ("cod_cidade") REFERENCES "cidade" ("cod_cidade") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "usuario_tag" ADD CONSTRAINT "usuario_tag_cod_usuario_fkey" FOREIGN KEY ("cod_usuario") REFERENCES "usuario" ("cod_usuario") ON DELETE NO ACTION ON UPDATE NO ACTION;
ALTER TABLE "usuario_tag" ADD CONSTRAINT "_copy_1" FOREIGN KEY ("cod_tag") REFERENCES "tag" ("cod_tag") MATCH FULL ON DELETE NO ACTION ON UPDATE NO ACTION;

