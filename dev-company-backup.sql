--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

-- Started on 2021-12-12 11:59:12 CET

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- TOC entry 202 (class 1259 OID 16385)
-- Name: buildings; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.buildings (
    building_id integer NOT NULL,
    bulding_city character varying(16) NOT NULL,
    building_postal character varying(6) NOT NULL,
    building_street character varying(16) NOT NULL,
    building_address integer NOT NULL
);


ALTER TABLE public.buildings OWNER TO postgres;

--
-- TOC entry 203 (class 1259 OID 16388)
-- Name: buildings_building_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.buildings_building_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.buildings_building_id_seq OWNER TO postgres;

--
-- TOC entry 3086 (class 0 OID 0)
-- Dependencies: 203
-- Name: buildings_building_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.buildings_building_id_seq OWNED BY public.buildings.building_id;


--
-- TOC entry 204 (class 1259 OID 16390)
-- Name: buildings_constructions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.buildings_constructions (
    construction_id integer NOT NULL,
    building_id integer NOT NULL,
    building_start date,
    building_end date
);


ALTER TABLE public.buildings_constructions OWNER TO postgres;

--
-- TOC entry 205 (class 1259 OID 16393)
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    client_id integer NOT NULL,
    client_name character varying(16),
    client_surname character varying(16) NOT NULL,
    client_pesel character varying(16) NOT NULL
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- TOC entry 206 (class 1259 OID 16396)
-- Name: clients_client_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.clients_client_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.clients_client_id_seq OWNER TO postgres;

--
-- TOC entry 3087 (class 0 OID 0)
-- Dependencies: 206
-- Name: clients_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clients_client_id_seq OWNED BY public.clients.client_id;


--
-- TOC entry 207 (class 1259 OID 16398)
-- Name: constructions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.constructions (
    construction_id integer NOT NULL,
    construction_name character varying(16),
    construction_city character varying(16) NOT NULL,
    construction_street character varying(16) NOT NULL
);


ALTER TABLE public.constructions OWNER TO postgres;

--
-- TOC entry 208 (class 1259 OID 16401)
-- Name: constructions_construction_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.constructions_construction_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.constructions_construction_id_seq OWNER TO postgres;

--
-- TOC entry 3088 (class 0 OID 0)
-- Dependencies: 208
-- Name: constructions_construction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.constructions_construction_id_seq OWNED BY public.constructions.construction_id;


--
-- TOC entry 209 (class 1259 OID 16403)
-- Name: flats; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flats (
    flat_id integer NOT NULL,
    client_id integer,
    building_id integer,
    flat_number integer NOT NULL,
    sale_id integer
);


ALTER TABLE public.flats OWNER TO postgres;

--
-- TOC entry 210 (class 1259 OID 16406)
-- Name: flats_flat_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.flats_flat_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.flats_flat_id_seq OWNER TO postgres;

--
-- TOC entry 3089 (class 0 OID 0)
-- Dependencies: 210
-- Name: flats_flat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.flats_flat_id_seq OWNED BY public.flats.flat_id;


--
-- TOC entry 211 (class 1259 OID 16408)
-- Name: machines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.machines (
    machine_id integer NOT NULL,
    machine_reg character varying(12)
);


ALTER TABLE public.machines OWNER TO postgres;

--
-- TOC entry 212 (class 1259 OID 16411)
-- Name: machines_constructions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.machines_constructions (
    construction_id integer NOT NULL,
    machine_id integer NOT NULL,
    machine_start date,
    machine_end date
);


ALTER TABLE public.machines_constructions OWNER TO postgres;

--
-- TOC entry 213 (class 1259 OID 16414)
-- Name: machines_machine_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.machines_machine_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.machines_machine_id_seq OWNER TO postgres;

--
-- TOC entry 3090 (class 0 OID 0)
-- Dependencies: 213
-- Name: machines_machine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.machines_machine_id_seq OWNED BY public.machines.machine_id;


--
-- TOC entry 214 (class 1259 OID 16416)
-- Name: sales; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sales (
    sale_id integer NOT NULL,
    building_id integer,
    client_id integer,
    sale_value double precision,
    sale_assign_date date,
    sale_payment_date date,
    sale_identity character varying(32)
);


ALTER TABLE public.sales OWNER TO postgres;

--
-- TOC entry 215 (class 1259 OID 16419)
-- Name: sales_sale_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.sales_sale_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.sales_sale_id_seq OWNER TO postgres;

--
-- TOC entry 3091 (class 0 OID 0)
-- Dependencies: 215
-- Name: sales_sale_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sales_sale_id_seq OWNED BY public.sales.sale_id;


--
-- TOC entry 216 (class 1259 OID 16421)
-- Name: workers; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workers (
    worker_id integer NOT NULL,
    worker_name character varying(16),
    worker_surname character varying(16) NOT NULL,
    worker_pesel character varying(16) NOT NULL
);


ALTER TABLE public.workers OWNER TO postgres;

--
-- TOC entry 217 (class 1259 OID 16424)
-- Name: workers_worker_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.workers_worker_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.workers_worker_id_seq OWNER TO postgres;

--
-- TOC entry 3092 (class 0 OID 0)
-- Dependencies: 217
-- Name: workers_worker_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.workers_worker_id_seq OWNED BY public.workers.worker_id;


--
-- TOC entry 218 (class 1259 OID 16426)
-- Name: workes_constructions; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.workes_constructions (
    construction_id integer NOT NULL,
    worker_id integer NOT NULL,
    worker_start date,
    worker_end date
);


ALTER TABLE public.workes_constructions OWNER TO postgres;

--
-- TOC entry 2880 (class 2604 OID 16429)
-- Name: buildings building_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings ALTER COLUMN building_id SET DEFAULT nextval('public.buildings_building_id_seq'::regclass);


--
-- TOC entry 2881 (class 2604 OID 16430)
-- Name: clients client_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN client_id SET DEFAULT nextval('public.clients_client_id_seq'::regclass);


--
-- TOC entry 2882 (class 2604 OID 16431)
-- Name: constructions construction_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.constructions ALTER COLUMN construction_id SET DEFAULT nextval('public.constructions_construction_id_seq'::regclass);


--
-- TOC entry 2883 (class 2604 OID 16432)
-- Name: flats flat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats ALTER COLUMN flat_id SET DEFAULT nextval('public.flats_flat_id_seq'::regclass);


--
-- TOC entry 2884 (class 2604 OID 16433)
-- Name: machines machine_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines ALTER COLUMN machine_id SET DEFAULT nextval('public.machines_machine_id_seq'::regclass);


--
-- TOC entry 2885 (class 2604 OID 16434)
-- Name: sales sale_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales ALTER COLUMN sale_id SET DEFAULT nextval('public.sales_sale_id_seq'::regclass);


--
-- TOC entry 2886 (class 2604 OID 16435)
-- Name: workers worker_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workers ALTER COLUMN worker_id SET DEFAULT nextval('public.workers_worker_id_seq'::regclass);


--
-- TOC entry 3064 (class 0 OID 16385)
-- Dependencies: 202
-- Data for Name: buildings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buildings (building_id, bulding_city, building_postal, building_street, building_address) FROM stdin;
1	Koszalin	76-115	Zwycięstwa	30
2	Koszalin\n	76-115	Zwycięstwa	31
4	Koszalin	76-115	Mickiewicza	15
\.


--
-- TOC entry 3066 (class 0 OID 16390)
-- Dependencies: 204
-- Data for Name: buildings_constructions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buildings_constructions (construction_id, building_id, building_start, building_end) FROM stdin;
1	1	2020-01-30	2021-01-31
1	2	2020-01-30	\N
3	4	2019-01-06	2019-09-22
\.


--
-- TOC entry 3067 (class 0 OID 16393)
-- Dependencies: 205
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (client_id, client_name, client_surname, client_pesel) FROM stdin;
3	Natalia	Kaplon	99010604291
4	Michael	Scott	78665753
6	Kacper	Padykuła	999999999
9	Damian	Kapłon	8899221144
2	Damian	NieKapłon	99010604391
1	Wojciech	NieKapłon	94020224382
10	Maciej	XD	1232412235
11	dasda	asdasd	123412425
12	Andrzej	Wajda	22013502222
13	Harry	Potter	9312
\.


--
-- TOC entry 3069 (class 0 OID 16398)
-- Dependencies: 207
-- Data for Name: constructions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.constructions (construction_id, construction_name, construction_city, construction_street) FROM stdin;
1	Osiedle Kwiatowe	Koszalin	Zwyciestwa
2	Osiedle Rzymskie	Koszalin	Mickiewicza
3	Flower Estate	Koszalin	Zwycięstwa
\.


--
-- TOC entry 3071 (class 0 OID 16403)
-- Dependencies: 209
-- Data for Name: flats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flats (flat_id, client_id, building_id, flat_number, sale_id) FROM stdin;
9	\N	1	2	\N
11	\N	2	31	\N
12	\N	2	1	\N
13	\N	2	3	\N
10	\N	1	30	\N
8	\N	1	1	1
\.


--
-- TOC entry 3073 (class 0 OID 16408)
-- Dependencies: 211
-- Data for Name: machines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.machines (machine_id, machine_reg) FROM stdin;
1	ZKO8GG2
2	GD12HK2
3	ZK88LG1
\.


--
-- TOC entry 3074 (class 0 OID 16411)
-- Dependencies: 212
-- Data for Name: machines_constructions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.machines_constructions (construction_id, machine_id, machine_start, machine_end) FROM stdin;
1	1	2020-12-30	2020-12-30
1	2	2020-12-30	2021-01-06
\.


--
-- TOC entry 3076 (class 0 OID 16416)
-- Dependencies: 214
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sales (sale_id, building_id, client_id, sale_value, sale_assign_date, sale_payment_date, sale_identity) FROM stdin;
1	1	1	400000	2021-12-02	2021-12-03	02/12/2021/0001
2	1	1	0	\N	\N	02/12/2021/0002
8	1	3	500000	2021-08-20	2021-08-20	20/20/2020
9	1	3	450000	2021-08-20	2021-08-20	20/20/2020/0003
\.


--
-- TOC entry 3078 (class 0 OID 16421)
-- Dependencies: 216
-- Data for Name: workers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workers (worker_id, worker_name, worker_surname, worker_pesel) FROM stdin;
\.


--
-- TOC entry 3080 (class 0 OID 16426)
-- Dependencies: 218
-- Data for Name: workes_constructions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workes_constructions (construction_id, worker_id, worker_start, worker_end) FROM stdin;
\.


--
-- TOC entry 3093 (class 0 OID 0)
-- Dependencies: 203
-- Name: buildings_building_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.buildings_building_id_seq', 4, true);


--
-- TOC entry 3094 (class 0 OID 0)
-- Dependencies: 206
-- Name: clients_client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_client_id_seq', 13, true);


--
-- TOC entry 3095 (class 0 OID 0)
-- Dependencies: 208
-- Name: constructions_construction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.constructions_construction_id_seq', 3, true);


--
-- TOC entry 3096 (class 0 OID 0)
-- Dependencies: 210
-- Name: flats_flat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.flats_flat_id_seq', 16, true);


--
-- TOC entry 3097 (class 0 OID 0)
-- Dependencies: 213
-- Name: machines_machine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.machines_machine_id_seq', 3, true);


--
-- TOC entry 3098 (class 0 OID 0)
-- Dependencies: 215
-- Name: sales_sale_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sales_sale_id_seq', 9, true);


--
-- TOC entry 3099 (class 0 OID 0)
-- Dependencies: 217
-- Name: workers_worker_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.workers_worker_id_seq', 1, false);


--
-- TOC entry 2889 (class 2606 OID 16437)
-- Name: buildings pk_buildings; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings
    ADD CONSTRAINT pk_buildings PRIMARY KEY (building_id);


--
-- TOC entry 2894 (class 2606 OID 16439)
-- Name: buildings_constructions pk_buildings_constructions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings_constructions
    ADD CONSTRAINT pk_buildings_constructions PRIMARY KEY (construction_id, building_id);


--
-- TOC entry 2897 (class 2606 OID 16441)
-- Name: clients pk_clients; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT pk_clients PRIMARY KEY (client_id);


--
-- TOC entry 2900 (class 2606 OID 16443)
-- Name: constructions pk_constructions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.constructions
    ADD CONSTRAINT pk_constructions PRIMARY KEY (construction_id);


--
-- TOC entry 2905 (class 2606 OID 16445)
-- Name: flats pk_flats; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT pk_flats PRIMARY KEY (flat_id);


--
-- TOC entry 2908 (class 2606 OID 16447)
-- Name: machines pk_machines; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT pk_machines PRIMARY KEY (machine_id);


--
-- TOC entry 2913 (class 2606 OID 16449)
-- Name: machines_constructions pk_machines_constructions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines_constructions
    ADD CONSTRAINT pk_machines_constructions PRIMARY KEY (construction_id, machine_id);


--
-- TOC entry 2917 (class 2606 OID 16451)
-- Name: sales pk_sales; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT pk_sales PRIMARY KEY (sale_id);


--
-- TOC entry 2920 (class 2606 OID 16453)
-- Name: workers pk_workers; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workers
    ADD CONSTRAINT pk_workers PRIMARY KEY (worker_id);


--
-- TOC entry 2924 (class 2606 OID 16455)
-- Name: workes_constructions pk_workes_constructions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workes_constructions
    ADD CONSTRAINT pk_workes_constructions PRIMARY KEY (construction_id, worker_id);


--
-- TOC entry 2890 (class 1259 OID 16456)
-- Name: building_c_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX building_c_fk ON public.buildings_constructions USING btree (building_id);


--
-- TOC entry 2891 (class 1259 OID 16457)
-- Name: building_c_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX building_c_pk ON public.buildings_constructions USING btree (construction_id, building_id);


--
-- TOC entry 2901 (class 1259 OID 16458)
-- Name: buildings_flats_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX buildings_flats_fk ON public.flats USING btree (building_id);


--
-- TOC entry 2887 (class 1259 OID 16459)
-- Name: buildings_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX buildings_pk ON public.buildings USING btree (building_id);


--
-- TOC entry 2914 (class 1259 OID 16460)
-- Name: buildings_sales_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX buildings_sales_fk ON public.sales USING btree (building_id);


--
-- TOC entry 2902 (class 1259 OID 16461)
-- Name: clients_flats_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX clients_flats_fk ON public.flats USING btree (client_id);


--
-- TOC entry 2895 (class 1259 OID 16462)
-- Name: clients_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX clients_pk ON public.clients USING btree (client_id);


--
-- TOC entry 2915 (class 1259 OID 16463)
-- Name: clients_sales_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX clients_sales_fk ON public.sales USING btree (client_id);


--
-- TOC entry 2892 (class 1259 OID 16464)
-- Name: construction_b_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX construction_b_fk ON public.buildings_constructions USING btree (construction_id);


--
-- TOC entry 2909 (class 1259 OID 16465)
-- Name: construction_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX construction_fk ON public.machines_constructions USING btree (construction_id);


--
-- TOC entry 2910 (class 1259 OID 16466)
-- Name: construction_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX construction_pk ON public.machines_constructions USING btree (construction_id, machine_id);


--
-- TOC entry 2922 (class 1259 OID 16467)
-- Name: construction_w_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX construction_w_pk ON public.workes_constructions USING btree (construction_id, worker_id);


--
-- TOC entry 2898 (class 1259 OID 16468)
-- Name: constructions_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX constructions_pk ON public.constructions USING btree (construction_id);


--
-- TOC entry 2903 (class 1259 OID 16469)
-- Name: flats_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX flats_pk ON public.flats USING btree (flat_id);


--
-- TOC entry 2911 (class 1259 OID 16471)
-- Name: machine_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX machine_fk ON public.machines_constructions USING btree (machine_id);


--
-- TOC entry 2906 (class 1259 OID 16472)
-- Name: machines_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX machines_pk ON public.machines USING btree (machine_id);


--
-- TOC entry 2918 (class 1259 OID 16473)
-- Name: sales_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX sales_pk ON public.sales USING btree (sale_id);


--
-- TOC entry 2925 (class 1259 OID 16474)
-- Name: worker_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX worker_fk ON public.workes_constructions USING btree (worker_id);


--
-- TOC entry 2926 (class 1259 OID 16475)
-- Name: workers_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX workers_fk ON public.workes_constructions USING btree (construction_id);


--
-- TOC entry 2921 (class 1259 OID 16476)
-- Name: workers_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX workers_pk ON public.workers USING btree (worker_id);


--
-- TOC entry 2927 (class 2606 OID 16477)
-- Name: buildings_constructions fk_building_buildings_building; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings_constructions
    ADD CONSTRAINT fk_building_buildings_building FOREIGN KEY (building_id) REFERENCES public.buildings(building_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2928 (class 2606 OID 16482)
-- Name: buildings_constructions fk_building_construct_construc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings_constructions
    ADD CONSTRAINT fk_building_construct_construc FOREIGN KEY (construction_id) REFERENCES public.constructions(construction_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2929 (class 2606 OID 16487)
-- Name: flats fk_flats_buildings_building; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT fk_flats_buildings_building FOREIGN KEY (building_id) REFERENCES public.buildings(building_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2930 (class 2606 OID 16492)
-- Name: flats fk_flats_clients_f_clients; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT fk_flats_clients_f_clients FOREIGN KEY (client_id) REFERENCES public.clients(client_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2932 (class 2606 OID 16497)
-- Name: machines_constructions fk_machines_construct_construc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines_constructions
    ADD CONSTRAINT fk_machines_construct_construc FOREIGN KEY (construction_id) REFERENCES public.constructions(construction_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2933 (class 2606 OID 16502)
-- Name: machines_constructions fk_machines_machines__machines; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines_constructions
    ADD CONSTRAINT fk_machines_machines__machines FOREIGN KEY (machine_id) REFERENCES public.machines(machine_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2934 (class 2606 OID 16507)
-- Name: sales fk_sales_buildings_building; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT fk_sales_buildings_building FOREIGN KEY (building_id) REFERENCES public.buildings(building_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2935 (class 2606 OID 16512)
-- Name: sales fk_sales_clients_s_clients; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT fk_sales_clients_s_clients FOREIGN KEY (client_id) REFERENCES public.clients(client_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2936 (class 2606 OID 16522)
-- Name: workes_constructions fk_workes_c_construci_construc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workes_constructions
    ADD CONSTRAINT fk_workes_c_construci_construc FOREIGN KEY (construction_id) REFERENCES public.constructions(construction_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2937 (class 2606 OID 16527)
-- Name: workes_constructions fk_workes_c_workers_w_workers; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workes_constructions
    ADD CONSTRAINT fk_workes_c_workers_w_workers FOREIGN KEY (worker_id) REFERENCES public.workers(worker_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- TOC entry 2931 (class 2606 OID 16532)
-- Name: flats one_sale_many_flats; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT one_sale_many_flats FOREIGN KEY (sale_id) REFERENCES public.sales(sale_id);


-- Completed on 2021-12-12 11:59:12 CET

--
-- PostgreSQL database dump complete
--

