/*
 * The query in Derby dialect creates all the tables of the model 
 * so the memory database can be used in tests.
 * It should be updated each time a change is made to an entity.
 */

	-- Entity administrator
    CREATE TABLE administrator (
        user_id BIGINT NOT NULL,
        user_email VARCHAR(255) UNIQUE NOT NULL,
        user_firstname VARCHAR(255),
        user_lastname VARCHAR(255),
        user_name VARCHAR(255) UNIQUE NOT NULL,
        user_password VARCHAR(255) NOT NULL,
        can_delete INT,
        can_read INT,
        can_update INT,
        PRIMARY KEY (user_id)
    );

    -- Entity customer
    CREATE TABLE customer (
        user_id BIGINT NOT NULL,
        user_email VARCHAR(255) UNIQUE NOT NULL,
        user_firstname VARCHAR(255),
        user_lastname VARCHAR(255),
        user_name VARCHAR(255) UNIQUE NOT NULL,
        user_password VARCHAR(255) NOT NULL,
        addr_complement VARCHAR(255),
        addr_coordinates VARCHAR(255),
        addr_country VARCHAR(255) NOT NULL,
        addr_post_code VARCHAR(255) NOT NULL,
        addr_street VARCHAR(255),
        addr_city VARCHAR(255) NOT NULL,
        birth_date DATE,
        registration_date TIMESTAMP,
        PRIMARY KEY (user_id)
    );
    
    -- Entity image
    CREATE TABLE image (
        image_id BIGINT NOT NULL,
        imag_title VARCHAR(255) NOT NULL,
        imag_description LONG VARCHAR,
        imag_data BLOB NOT NULL,
        PRIMARY KEY (image_id)
    );
    
    -- Entity qualitylabel
    CREATE TABLE qualitylabel (
        qualitylabel_id BIGINT NOT NULL,
        official_name VARCHAR(255) UNIQUE NOT NULL,
        acronym VARCHAR(255) UNIQUE NOT NULL,
        definition LONG VARCHAR,
        fk_image_id BIGINT CONSTRAINT fk_qualitylabel_image REFERENCES image(image_id),
        PRIMARY KEY (qualitylabel_id)
    );
    
    -- Entity designation
    CREATE TABLE designation (
        designation_id BIGINT NOT NULL,
        addr_complement VARCHAR(255),
        addr_coordinates VARCHAR(255),
        addr_country VARCHAR(255) NOT NULL,
        addr_post_code VARCHAR(255) NOT NULL,
        addr_street VARCHAR(255),
        addr_city VARCHAR(255) NOT NULL,
        definition LONG VARCHAR,
        legal_act VARCHAR(255),
        registered_name VARCHAR(255) UNIQUE NOT NULL,
        transcripted_name VARCHAR(255),
        fk_qualitylabel_id BIGINT CONSTRAINT fk_designation_qualitylabel REFERENCES qualitylabel(qualitylabel_id) NOT NULL,
        fk_image_id BIGINT CONSTRAINT fk_designation_image REFERENCES image(image_id),
        PRIMARY KEY (designation_id)
    );

    -- Entity enterprise
    CREATE TABLE enterprise (
        enterprise_id BIGINT NOT NULL,
        addr_complement VARCHAR(255),
        addr_coordinates VARCHAR(255),
        addr_country VARCHAR(255) NOT NULL,
        addr_post_code VARCHAR(255) NOT NULL,
        addr_street VARCHAR(255),
        addr_city VARCHAR(255) NOT NULL,
        creation_date DATE,
        description LONG VARCHAR,
        legal_identification VARCHAR(255) NOT NULL,
        legal_name VARCHAR(255) UNIQUE NOT NULL,
        number_employees BIGINT,
        registration_date TIMESTAMP,
        trade_name VARCHAR(255) UNIQUE NOT NULL,
        PRIMARY KEY (enterprise_id)
    );

    -- Entity seller
    CREATE TABLE seller (
        user_id BIGINT NOT NULL,
        user_email VARCHAR(255) UNIQUE NOT NULL,
        user_firstname VARCHAR(255),
        user_lastname VARCHAR(255),
        user_name VARCHAR(255) UNIQUE NOT NULL,
        user_password VARCHAR(255) NOT NULL,
        fk_enterprise_id BIGINT CONSTRAINT fk_seller_enterprise REFERENCES enterprise(enterprise_id) NOT NULL,
        PRIMARY KEY (user_id)
    );

    -- Entity site
    CREATE TABLE site (
        site_id BIGINT NOT NULL,
        addr_complement VARCHAR(255),
        addr_coordinates VARCHAR(255),
        addr_country VARCHAR(255) NOT NULL,
        addr_post_code VARCHAR(255) NOT NULL,
        addr_street VARCHAR(255),
        addr_city VARCHAR(255) NOT NULL,
        description LONG VARCHAR,
        legal_identification VARCHAR(255) NOT NULL,
        site_name VARCHAR(255) NOT NULL,
        fk_enterprise_id BIGINT CONSTRAINT fk_site_enterprise REFERENCES enterprise(enterprise_id) NOT NULL,
        PRIMARY KEY (site_id)
    );

    -- Entity product
    CREATE TABLE product (
        product_id BIGINT NOT NULL,
        description LONG VARCHAR,
        quantity INTEGER,
        price NUMERIC(19,2),
        last_update TIMESTAMP,
        title VARCHAR(255) NOT NULL,
        fk_site_id BIGINT CONSTRAINT fk_product_site REFERENCES site(site_id) NOT NULL,
        fk_designation_id BIGINT CONSTRAINT fk_product_designation REFERENCES designation(designation_id),
        PRIMARY KEY (product_id)
    );

    -- Table hibernate_sequences (hibernate)
    CREATE TABLE hibernate_sequences (
         sequence_name VARCHAR(255),
         next_val BIGINT
    );

    -- Sequence hibernate_sequence (hibernate)
    CREATE SEQUENCE hibernate_sequence AS BIGINT START WITH 1;