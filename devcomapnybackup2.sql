--
-- PostgreSQL database dump
--

-- Dumped from database version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)
-- Dumped by pg_dump version 12.9 (Ubuntu 12.9-0ubuntu0.20.04.1)

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
-- Name: auth; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.auth (
    login character varying[] NOT NULL,
    password character varying[] NOT NULL,
    i integer NOT NULL
);


ALTER TABLE public.auth OWNER TO postgres;

--
-- Name: auth_i_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.auth_i_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.auth_i_seq OWNER TO postgres;

--
-- Name: auth_i_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.auth_i_seq OWNED BY public.auth.i;


--
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
-- Name: buildings_building_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.buildings_building_id_seq OWNED BY public.buildings.building_id;


--
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
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    client_id integer NOT NULL,
    client_name character varying(16),
    client_surname character varying(16) NOT NULL,
    client_pesel character varying(16) NOT NULL,
    login character varying(16),
    password character varying(128)
);


ALTER TABLE public.clients OWNER TO postgres;

--
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
-- Name: clients_client_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.clients_client_id_seq OWNED BY public.clients.client_id;


--
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
-- Name: constructions_construction_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.constructions_construction_id_seq OWNED BY public.constructions.construction_id;


--
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
-- Name: flats_flat_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.flats_flat_id_seq OWNED BY public.flats.flat_id;


--
-- Name: machines; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.machines (
    machine_id integer NOT NULL,
    machine_reg character varying(12)
);


ALTER TABLE public.machines OWNER TO postgres;

--
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
-- Name: machines_machine_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.machines_machine_id_seq OWNED BY public.machines.machine_id;


--
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
-- Name: sales_sale_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.sales_sale_id_seq OWNED BY public.sales.sale_id;


--
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
-- Name: workers_worker_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.workers_worker_id_seq OWNED BY public.workers.worker_id;


--
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
-- Name: auth i; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auth ALTER COLUMN i SET DEFAULT nextval('public.auth_i_seq'::regclass);


--
-- Name: buildings building_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings ALTER COLUMN building_id SET DEFAULT nextval('public.buildings_building_id_seq'::regclass);


--
-- Name: clients client_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients ALTER COLUMN client_id SET DEFAULT nextval('public.clients_client_id_seq'::regclass);


--
-- Name: constructions construction_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.constructions ALTER COLUMN construction_id SET DEFAULT nextval('public.constructions_construction_id_seq'::regclass);


--
-- Name: flats flat_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats ALTER COLUMN flat_id SET DEFAULT nextval('public.flats_flat_id_seq'::regclass);


--
-- Name: machines machine_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines ALTER COLUMN machine_id SET DEFAULT nextval('public.machines_machine_id_seq'::regclass);


--
-- Name: sales sale_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales ALTER COLUMN sale_id SET DEFAULT nextval('public.sales_sale_id_seq'::regclass);


--
-- Name: workers worker_id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workers ALTER COLUMN worker_id SET DEFAULT nextval('public.workers_worker_id_seq'::regclass);


--
-- Data for Name: auth; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.auth (login, password, i) FROM stdin;
\.


--
-- Data for Name: buildings; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buildings (building_id, bulding_city, building_postal, building_street, building_address) FROM stdin;
1	Koszalin	76-115	Zwycięstwa	30
2	Koszalin\n	76-115	Zwycięstwa	31
4	Koszalin	76-115	Mickiewicza	15
\.


--
-- Data for Name: buildings_constructions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.buildings_constructions (construction_id, building_id, building_start, building_end) FROM stdin;
1	1	2020-01-30	2021-01-31
1	2	2020-01-30	\N
3	4	2019-01-06	2019-09-22
\.


--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (client_id, client_name, client_surname, client_pesel, login, password) FROM stdin;
14	Dimitry	Glukhovsky	10987654321	\N	\N
12	Andrzej	Wajdaaa	22013502222	\N	\N
2	Damian	Kapłon	99010604391	\N	\N
10	Maciej	Zawad	12324122352	\N	\N
1	Damian	NieKapłon	9901060451	\N	\N
3	Natalia	Kapłon	99010604291	\N	\N
9	Kapłon	Damian	88992211444	\N	\N
15	Nikodem	Owsiuk	10234567890	\N	\N
16	Nikodem	Dowcipnik	10234567892	\N	\N
17	Bartek	Nowak	21212121211	\N	\N
13	Bartek	Bartek	12345678910	admin	$2a$12$5MhgtjLD4eJtWUU/R3vYLuaxMCuDIWVuLqwe2GLjE3UT3ihBSSPwq
4	Michael	Scott	78665753998	\N	\N
18	Jim	Halpert	7812118971	\N	\N
6	Kacper	Padykuła	99999999999	khawirar	$2a$12$Rf4GrM0g3YQmi/3T/ld6MOdRs3/O.L7M.NQ9SHhQHdtrwLhCL7EWS
\.


--
-- Data for Name: constructions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.constructions (construction_id, construction_name, construction_city, construction_street) FROM stdin;
1	Osiedle Kwiatowe	Koszalin	Zwyciestwa
2	Osiedle Rzymskie	Koszalin	Mickiewicza
3	Flower Estate	Koszalin	Zwycięstwa
\.


--
-- Data for Name: flats; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flats (flat_id, client_id, building_id, flat_number, sale_id) FROM stdin;
11	\N	2	31	\N
8	\N	1	1	1
19	\N	2	1	14
26	\N	1	9	\N
27	\N	1	10	\N
28	\N	1	11	\N
30	\N	2	2	\N
33	\N	1	4	16
22	\N	1	5	17
32	\N	1	3	20
18	\N	1	2	13
23	\N	1	6	\N
25	\N	1	8	23
31	\N	2	3	24
24	\N	1	7	25
\.


--
-- Data for Name: machines; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.machines (machine_id, machine_reg) FROM stdin;
1	ZKO8GG2
2	GD12HK2
3	ZK88LG1
\.


--
-- Data for Name: machines_constructions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.machines_constructions (construction_id, machine_id, machine_start, machine_end) FROM stdin;
1	1	2020-12-30	2020-12-30
1	2	2020-12-30	2021-01-06
\.


--
-- Data for Name: sales; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sales (sale_id, building_id, client_id, sale_value, sale_assign_date, sale_payment_date, sale_identity) FROM stdin;
1	1	1	400000	2021-12-02	2021-12-03	02/12/2021/0001
13	1	3	300000	2021-12-12	2021-12-12	12/12/2021/0003
14	2	3	500000	2021-12-12	2021-12-12	12/12/2021/0012
16	1	9	300000	2021-12-13	2021-12-13	13/12/2021/0002
17	1	3	632000	2021-12-02	2021-12-02	02/12/2021/1288
20	1	13	799999	2021-12-14	2021-12-14	14/12/2021/0003
23	1	13	799999	2021-12-14	2021-12-14	14/12/2021/0102
24	2	13	345345	2021-12-14	2021-12-14	14/12/2021/0105
25	1	6	450999.99	2021-12-19	2021-12-21	19/12/2021/0001
\.


--
-- Data for Name: workers; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workers (worker_id, worker_name, worker_surname, worker_pesel) FROM stdin;
\.


--
-- Data for Name: workes_constructions; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.workes_constructions (construction_id, worker_id, worker_start, worker_end) FROM stdin;
\.


--
-- Name: auth_i_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.auth_i_seq', 1, false);


--
-- Name: buildings_building_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.buildings_building_id_seq', 4, true);


--
-- Name: clients_client_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.clients_client_id_seq', 18, true);


--
-- Name: constructions_construction_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.constructions_construction_id_seq', 3, true);


--
-- Name: flats_flat_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.flats_flat_id_seq', 33, true);


--
-- Name: machines_machine_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.machines_machine_id_seq', 3, true);


--
-- Name: sales_sale_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.sales_sale_id_seq', 25, true);


--
-- Name: workers_worker_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.workers_worker_id_seq', 1, false);


--
-- Name: auth auth_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.auth
    ADD CONSTRAINT auth_pkey PRIMARY KEY (i);


--
-- Name: buildings pk_buildings; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings
    ADD CONSTRAINT pk_buildings PRIMARY KEY (building_id);


--
-- Name: buildings_constructions pk_buildings_constructions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings_constructions
    ADD CONSTRAINT pk_buildings_constructions PRIMARY KEY (construction_id, building_id);


--
-- Name: clients pk_clients; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT pk_clients PRIMARY KEY (client_id);


--
-- Name: constructions pk_constructions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.constructions
    ADD CONSTRAINT pk_constructions PRIMARY KEY (construction_id);


--
-- Name: flats pk_flats; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT pk_flats PRIMARY KEY (flat_id);


--
-- Name: machines pk_machines; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines
    ADD CONSTRAINT pk_machines PRIMARY KEY (machine_id);


--
-- Name: machines_constructions pk_machines_constructions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines_constructions
    ADD CONSTRAINT pk_machines_constructions PRIMARY KEY (construction_id, machine_id);


--
-- Name: sales pk_sales; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT pk_sales PRIMARY KEY (sale_id);


--
-- Name: workers pk_workers; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workers
    ADD CONSTRAINT pk_workers PRIMARY KEY (worker_id);


--
-- Name: workes_constructions pk_workes_constructions; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workes_constructions
    ADD CONSTRAINT pk_workes_constructions PRIMARY KEY (construction_id, worker_id);


--
-- Name: building_c_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX building_c_fk ON public.buildings_constructions USING btree (building_id);


--
-- Name: building_c_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX building_c_pk ON public.buildings_constructions USING btree (construction_id, building_id);


--
-- Name: buildings_flats_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX buildings_flats_fk ON public.flats USING btree (building_id);


--
-- Name: buildings_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX buildings_pk ON public.buildings USING btree (building_id);


--
-- Name: buildings_sales_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX buildings_sales_fk ON public.sales USING btree (building_id);


--
-- Name: clients_flats_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX clients_flats_fk ON public.flats USING btree (client_id);


--
-- Name: clients_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX clients_pk ON public.clients USING btree (client_id);


--
-- Name: clients_sales_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX clients_sales_fk ON public.sales USING btree (client_id);


--
-- Name: construction_b_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX construction_b_fk ON public.buildings_constructions USING btree (construction_id);


--
-- Name: construction_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX construction_fk ON public.machines_constructions USING btree (construction_id);


--
-- Name: construction_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX construction_pk ON public.machines_constructions USING btree (construction_id, machine_id);


--
-- Name: construction_w_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX construction_w_pk ON public.workes_constructions USING btree (construction_id, worker_id);


--
-- Name: constructions_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX constructions_pk ON public.constructions USING btree (construction_id);


--
-- Name: flats_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX flats_pk ON public.flats USING btree (flat_id);


--
-- Name: machine_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX machine_fk ON public.machines_constructions USING btree (machine_id);


--
-- Name: machines_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX machines_pk ON public.machines USING btree (machine_id);


--
-- Name: sales_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX sales_pk ON public.sales USING btree (sale_id);


--
-- Name: worker_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX worker_fk ON public.workes_constructions USING btree (worker_id);


--
-- Name: workers_fk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX workers_fk ON public.workes_constructions USING btree (construction_id);


--
-- Name: workers_pk; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX workers_pk ON public.workers USING btree (worker_id);


--
-- Name: buildings_constructions fk_building_buildings_building; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings_constructions
    ADD CONSTRAINT fk_building_buildings_building FOREIGN KEY (building_id) REFERENCES public.buildings(building_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: buildings_constructions fk_building_construct_construc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.buildings_constructions
    ADD CONSTRAINT fk_building_construct_construc FOREIGN KEY (construction_id) REFERENCES public.constructions(construction_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: flats fk_flats_buildings_building; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT fk_flats_buildings_building FOREIGN KEY (building_id) REFERENCES public.buildings(building_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: flats fk_flats_clients_f_clients; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT fk_flats_clients_f_clients FOREIGN KEY (client_id) REFERENCES public.clients(client_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: machines_constructions fk_machines_construct_construc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines_constructions
    ADD CONSTRAINT fk_machines_construct_construc FOREIGN KEY (construction_id) REFERENCES public.constructions(construction_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: machines_constructions fk_machines_machines__machines; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.machines_constructions
    ADD CONSTRAINT fk_machines_machines__machines FOREIGN KEY (machine_id) REFERENCES public.machines(machine_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: sales fk_sales_buildings_building; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT fk_sales_buildings_building FOREIGN KEY (building_id) REFERENCES public.buildings(building_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: sales fk_sales_clients_s_clients; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sales
    ADD CONSTRAINT fk_sales_clients_s_clients FOREIGN KEY (client_id) REFERENCES public.clients(client_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: workes_constructions fk_workes_c_construci_construc; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workes_constructions
    ADD CONSTRAINT fk_workes_c_construci_construc FOREIGN KEY (construction_id) REFERENCES public.constructions(construction_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: workes_constructions fk_workes_c_workers_w_workers; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.workes_constructions
    ADD CONSTRAINT fk_workes_c_workers_w_workers FOREIGN KEY (worker_id) REFERENCES public.workers(worker_id) ON UPDATE RESTRICT ON DELETE RESTRICT;


--
-- Name: flats one_sale_many_flats; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flats
    ADD CONSTRAINT one_sale_many_flats FOREIGN KEY (sale_id) REFERENCES public.sales(sale_id);


--
-- PostgreSQL database dump complete
--
