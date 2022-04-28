--
-- PostgreSQL database dump
--

-- Dumped from database version 14.2 (Debian 14.2-1.pgdg110+1)
-- Dumped by pg_dump version 14.2 (Debian 14.2-1.pgdg110+1)

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
-- Name: flyway_schema_history; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.flyway_schema_history (
    installed_rank integer NOT NULL,
    version character varying(50),
    description character varying(200) NOT NULL,
    type character varying(20) NOT NULL,
    script character varying(1000) NOT NULL,
    checksum integer,
    installed_by character varying(100) NOT NULL,
    installed_on timestamp without time zone DEFAULT now() NOT NULL,
    execution_time integer NOT NULL,
    success boolean NOT NULL
);


ALTER TABLE public.flyway_schema_history OWNER TO postgres;

--
-- Name: hibernate_sequence; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.hibernate_sequence
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.hibernate_sequence OWNER TO postgres;

--
-- Name: sector_entries; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sector_entries (
    id bigint NOT NULL,
    version bigint DEFAULT 0 NOT NULL,
    name character varying(255) NOT NULL,
    agreed_to_terms boolean NOT NULL
);


ALTER TABLE public.sector_entries OWNER TO postgres;

--
-- Name: sector_entry_sectors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sector_entry_sectors (
    sector_entry_id bigint NOT NULL,
    sector_id bigint NOT NULL
);


ALTER TABLE public.sector_entry_sectors OWNER TO postgres;

--
-- Name: sectors; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.sectors (
    id bigint NOT NULL,
    version bigint DEFAULT 0 NOT NULL,
    name character varying(255) NOT NULL,
    parent_sector_id bigint
);


ALTER TABLE public.sectors OWNER TO postgres;

--
-- Data for Name: flyway_schema_history; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.flyway_schema_history (installed_rank, version, description, type, script, checksum, installed_by, installed_on, execution_time, success) FROM stdin;
1	1	create schema	SQL	V1__create_schema.sql	-2104890743	postgres	2022-04-28 12:19:15.31166	14	t
2	2	add sectors	SQL	V2__add_sectors.sql	-1886876946	postgres	2022-04-28 12:19:15.340248	5	t
\.


--
-- Data for Name: sector_entries; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sector_entries (id, version, name, agreed_to_terms) FROM stdin;
\.


--
-- Data for Name: sector_entry_sectors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sector_entry_sectors (sector_entry_id, sector_id) FROM stdin;
\.


--
-- Data for Name: sectors; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.sectors (id, version, name, parent_sector_id) FROM stdin;
1	0	Manufacturing	\N
19	0	Construction materials	1
18	0	Electronics and Optics	1
6	0	Food and Beverage	1
342	0	Bakery &amp; confectionery products	6
43	0	Beverages	6
42	0	Fish &amp; fish products	6
40	0	Meat &amp; meat products	6
39	0	Milk &amp; dairy products	6
437	0	Other	6
378	0	Sweets &amp; snack food	6
13	0	Furniture	1
389	0	Bathroom/sauna	13
385	0	BedRoom	13
390	0	Children’s room	13
98	0	Kitchen	13
101	0	Living room	13
392	0	Office	13
394	0	Other (Furniture)	13
341	0	Outdoor	13
99	0	Project furniture	13
12	0	Machinery	1
94	0	Machinery components	12
91	0	Machinery equipment/tools	12
224	0	Manufacture of machinery	12
97	0	Maritime	12
271	0	Aluminium and steel workboats	97
269	0	Boat/Yacht building	97
230	0	Ship repair and conversion	97
93	0	Metal structures	12
408	0	Other	12
227	0	Repair and maintenance service	12
11	0	Metalworking	1
67	0	Construction of metal structures	11
263	0	Houses and building	11
267	0	Metal products	11
542	0	Metal works	11
75	0	CNC-machining	542
62	0	Forgings, Fasteners	542
69	0	Gas, Plasma, Laser cutting	542
66	0	MIG, TIG, Aluminum welding	542
9	0	Plastic and Rubber	1
54	0	Packaging	9
556	0	Plastic goods	9
559	0	Plastic processing technology	9
55	0	Blowing	559
57	0	Moulding	559
53	0	Plastics welding and processing	559
560	0	Plastic profiles	9
5	0	Printing	1
148	0	Advertising	5
150	0	Book/Periodicals printing	5
145	0	Labelling and packaging printing	5
7	0	Textile and Clothing	1
44	0	Clothing	7
45	0	Textile	7
8	0	Wood	1
337	0	Other (Wood)	8
51	0	Wooden building materials	8
47	0	Wooden houses	8
3	0	Other	\N
37	0	Creative industries	3
29	0	Energy technology	3
33	0	Environment	3
2	0	Service	\N
25	0	Business services	2
35	0	Engineering	2
28	0	Information Technology and Telecommunications	2
581	0	Data processing, Web portals, E-marketing	28
576	0	Programming, Consultancy	28
121	0	Software, Hardware	28
122	0	Telecommunications	28
22	0	Tourism	2
141	0	Translation services	2
21	0	Transport and Logistics	2
111	0	Air	21
114	0	Rail	21
112	0	Road	21
113	0	Water	21
\.


--
-- Name: hibernate_sequence; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.hibernate_sequence', 1, false);


--
-- Name: flyway_schema_history flyway_schema_history_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.flyway_schema_history
    ADD CONSTRAINT flyway_schema_history_pk PRIMARY KEY (installed_rank);


--
-- Name: sector_entries sector_entries_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sector_entries
    ADD CONSTRAINT sector_entries_pkey PRIMARY KEY (id);


--
-- Name: sector_entry_sectors sector_entry_sectors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sector_entry_sectors
    ADD CONSTRAINT sector_entry_sectors_pkey PRIMARY KEY (sector_entry_id, sector_id);


--
-- Name: sectors sectors_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sectors
    ADD CONSTRAINT sectors_pkey PRIMARY KEY (id);


--
-- Name: flyway_schema_history_s_idx; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX flyway_schema_history_s_idx ON public.flyway_schema_history USING btree (success);


--
-- Name: sector_entry_sectors sector_entry_sectors_sector_entry_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sector_entry_sectors
    ADD CONSTRAINT sector_entry_sectors_sector_entry_id_fkey FOREIGN KEY (sector_entry_id) REFERENCES public.sector_entries(id);


--
-- Name: sector_entry_sectors sector_entry_sectors_sector_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sector_entry_sectors
    ADD CONSTRAINT sector_entry_sectors_sector_id_fkey FOREIGN KEY (sector_id) REFERENCES public.sectors(id);


--
-- Name: sectors sectors_parent_sector_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.sectors
    ADD CONSTRAINT sectors_parent_sector_id_fkey FOREIGN KEY (parent_sector_id) REFERENCES public.sectors(id);


--
-- PostgreSQL database dump complete
--

