--
-- PostgreSQL database dump
--

-- Dumped from database version 10.1
-- Dumped by pg_dump version 10.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

DROP TABLE IF EXISTS agence CASCADE;
DROP TABLE IF EXISTS client CASCADE;
DROP TABLE IF EXISTS compte CASCADE;
DROP TABLE IF EXISTS operation CASCADE;
--
-- Name: agence; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE agence (
    numero integer NOT NULL,
    nom character varying(255) NOT NULL,
    adresse character varying(255) DEFAULT NULL::character varying
);


ALTER TABLE agence OWNER TO postgres;

--
-- Name: agence_numero_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE agence_numero_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE agence_numero_seq OWNER TO postgres;

--
-- Name: agence_numero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE agence_numero_seq OWNED BY agence.numero;


--
-- Name: client; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE client (
    numero integer NOT NULL,
    nom character varying(50) NOT NULL,
    prenom character varying(60) NOT NULL,
    agence integer NOT NULL
);


ALTER TABLE client OWNER TO postgres;

--
-- Name: client_numero_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE client_numero_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE client_numero_seq OWNER TO postgres;

--
-- Name: client_numero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE client_numero_seq OWNED BY client.numero;


--
-- Name: compte; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE compte (
    numero integer NOT NULL,
    libelle character varying(255) NOT NULL,
    sens character varying(2) NOT NULL,
    solde integer DEFAULT 0 NOT NULL,
    client integer NOT NULL
);


ALTER TABLE compte OWNER TO postgres;

--
-- Name: compte_numero_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE compte_numero_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE compte_numero_seq OWNER TO postgres;

--
-- Name: compte_numero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE compte_numero_seq OWNED BY compte.numero;


--
-- Name: operation; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE operation (
    numero integer NOT NULL,
    date timestamp without time zone DEFAULT CURRENT_TIMESTAMP NOT NULL,
    sens character varying(2) NOT NULL,
    montant integer DEFAULT 0 NOT NULL,
    libelle character varying(255) NOT NULL,
    compte integer NOT NULL
);


ALTER TABLE operation OWNER TO postgres;

--
-- Name: operation_numero_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE operation_numero_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE operation_numero_seq OWNER TO postgres;

--
-- Name: operation_numero_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE operation_numero_seq OWNED BY operation.numero;


--
-- Name: agence numero; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY agence ALTER COLUMN numero SET DEFAULT nextval('agence_numero_seq'::regclass);


--
-- Name: client numero; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY client ALTER COLUMN numero SET DEFAULT nextval('client_numero_seq'::regclass);


--
-- Name: compte numero; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY compte ALTER COLUMN numero SET DEFAULT nextval('compte_numero_seq'::regclass);


--
-- Name: operation numero; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY operation ALTER COLUMN numero SET DEFAULT nextval('operation_numero_seq'::regclass);


--
-- Data for Name: agence; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: client; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: compte; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Data for Name: operation; Type: TABLE DATA; Schema: public; Owner: postgres
--



--
-- Name: agence_numero_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('agence_numero_seq', 1, false);


--
-- Name: client_numero_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('client_numero_seq', 1, false);


--
-- Name: compte_numero_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('compte_numero_seq', 1, false);


--
-- Name: operation_numero_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('operation_numero_seq', 1, false);


--
-- Name: agence agence_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY agence
    ADD CONSTRAINT agence_pkey PRIMARY KEY (numero);


--
-- Name: client client_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_pkey PRIMARY KEY (numero);


--
-- Name: compte compte_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY compte
    ADD CONSTRAINT compte_pkey PRIMARY KEY (numero);


--
-- Name: operation operation_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY operation
    ADD CONSTRAINT operation_pkey PRIMARY KEY (numero);


--
-- Name: agence_numero_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX agence_numero_uindex ON agence USING btree (numero);


--
-- Name: client_numero_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX client_numero_uindex ON client USING btree (numero);


--
-- Name: compte_numero_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX compte_numero_uindex ON compte USING btree (numero);


--
-- Name: operation_numero_uindex; Type: INDEX; Schema: public; Owner: postgres
--

CREATE UNIQUE INDEX operation_numero_uindex ON operation USING btree (numero);


--
-- Name: client client_agence_numero_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY client
    ADD CONSTRAINT client_agence_numero_fk FOREIGN KEY (agence) REFERENCES agence(numero) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: compte compte_client_numero_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY compte
    ADD CONSTRAINT compte_client_numero_fk FOREIGN KEY (client) REFERENCES client(numero) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: operation operation_compte_numero_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY operation
    ADD CONSTRAINT operation_compte_numero_fk FOREIGN KEY (compte) REFERENCES compte(numero) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

