/*
 * The query in Derby dialect creates all the tables of the model 
 * so the memory database can be used in tests.
 * It should be updated each time a change is made to an entity.
 */

	-- Table administrator from entity Administrator child of AbstractUser
    CREATE TABLE administrator (
        user_id BIGINT NOT NULL,					-- from entity AbstractUser
        user_email VARCHAR(255) UNIQUE NOT NULL,	-- from entity AbstractUser
        user_firstname VARCHAR(255),				-- from entity AbstractUser
        user_lastname VARCHAR(255),					-- from entity AbstractUser
        user_name VARCHAR(255) UNIQUE NOT NULL,		-- from entity AbstractUser
        user_password VARCHAR(255) NOT NULL,		-- from entity AbstractUser
        can_delete INT,
        can_read INT,
        can_update INT,
        PRIMARY KEY (user_id)
    );

    -- Table customer from entity Customer child of AbstractUser
    CREATE TABLE customer (
        user_id BIGINT NOT NULL,					-- from entity AbstractUser
        user_email VARCHAR(255) UNIQUE NOT NULL,	-- from entity AbstractUser
        user_firstname VARCHAR(255),				-- from entity AbstractUser
        user_lastname VARCHAR(255),					-- from entity AbstractUser
        user_name VARCHAR(255) UNIQUE NOT NULL,		-- from entity AbstractUser
        user_password VARCHAR(255) NOT NULL,		-- from entity AbstractUser
        addr_street VARCHAR(255),					-- from embeddable Address
        addr_complement VARCHAR(255),				-- from embeddable Address
        addr_post_code VARCHAR(255) NOT NULL,		-- from embeddable Address
        addr_city VARCHAR(255) NOT NULL,			-- from embeddable Address
        addr_country VARCHAR(255) NOT NULL,			-- from embeddable Address
        addr_coordinates VARCHAR(255),				-- from embeddable Address
        birth_date DATE,
        registration_date TIMESTAMP,
        PRIMARY KEY (user_id)
    );
    
    -- Table image from entity Image
    CREATE TABLE image (
        image_id BIGINT NOT NULL,
        imag_title VARCHAR(255) NOT NULL,
        imag_description LONG VARCHAR,
        imag_data BLOB NOT NULL,
        PRIMARY KEY (image_id)
    );
    
    -- Table qualitylabel from entity Label
    CREATE TABLE qualitylabel (
        qualitylabel_id BIGINT NOT NULL,
        official_name VARCHAR(255) UNIQUE NOT NULL,
        acronym VARCHAR(255) UNIQUE NOT NULL,
        definition LONG VARCHAR,
        fk_image_id BIGINT CONSTRAINT fk_qualitylabel_image REFERENCES image(image_id),
        PRIMARY KEY (qualitylabel_id)
    );
    
    -- Table designation from entity Designation
    CREATE TABLE designation (
        designation_id BIGINT NOT NULL,
        addr_street VARCHAR(255),					-- from embeddable Address
        addr_complement VARCHAR(255),				-- from embeddable Address
        addr_post_code VARCHAR(255) NOT NULL,		-- from embeddable Address
        addr_city VARCHAR(255) NOT NULL,			-- from embeddable Address
        addr_country VARCHAR(255) NOT NULL,			-- from embeddable Address
        addr_coordinates VARCHAR(255),				-- from embeddable Address
        definition LONG VARCHAR,
        legal_act VARCHAR(255),
        registered_name VARCHAR(255) UNIQUE NOT NULL,
        transcripted_name VARCHAR(255),
        fk_image_id BIGINT CONSTRAINT fk_designation_image REFERENCES image(image_id),
        PRIMARY KEY (designation_id)
    );
    
    -- Table designationlabel join table from entities Designation and Label
    CREATE TABLE designationlabel (
		fk_designation_id BIGINT NOT NULL CONSTRAINT fk_designation_label REFERENCES designation(designation_id),
		fk_qualitylabel_id BIGINT NOT NULL CONSTRAINT fk_label_designation REFERENCES qualitylabel(qualitylabel_id)
    );

    -- Table enterprise from entity Enterprise
    CREATE TABLE enterprise (
        enterprise_id BIGINT NOT NULL,
        addr_street VARCHAR(255),					-- from embeddable Address
        addr_complement VARCHAR(255),				-- from embeddable Address
        addr_post_code VARCHAR(255) NOT NULL,		-- from embeddable Address
        addr_city VARCHAR(255) NOT NULL,			-- from embeddable Address
        addr_country VARCHAR(255) NOT NULL,			-- from embeddable Address
        addr_coordinates VARCHAR(255),				-- from embeddable Address
        creation_date DATE,
        description LONG VARCHAR,
        legal_identification VARCHAR(255) NOT NULL,
        legal_name VARCHAR(255) UNIQUE NOT NULL,
        number_employees BIGINT,
        registration_date TIMESTAMP,
        trade_name VARCHAR(255) UNIQUE NOT NULL,
        PRIMARY KEY (enterprise_id)
    );

    -- Table seller from entity Seller child of AbstractUser
    CREATE TABLE seller (
        user_id BIGINT NOT NULL,					-- from entity AbstractUser
        user_email VARCHAR(255) UNIQUE NOT NULL,	-- from entity AbstractUser
        user_firstname VARCHAR(255),				-- from entity AbstractUser
        user_lastname VARCHAR(255),					-- from entity AbstractUser
        user_name VARCHAR(255) UNIQUE NOT NULL,		-- from entity AbstractUser
        user_password VARCHAR(255) NOT NULL,		-- from entity AbstractUser
        fk_enterprise_id BIGINT CONSTRAINT fk_seller_enterprise REFERENCES enterprise(enterprise_id) NOT NULL,
        PRIMARY KEY (user_id)
    );

    -- Table site from entity Site
    CREATE TABLE site (
        site_id BIGINT NOT NULL,
        addr_street VARCHAR(255),					-- from embeddable Address
        addr_complement VARCHAR(255),				-- from embeddable Address
        addr_post_code VARCHAR(255) NOT NULL,		-- from embeddable Address
        addr_city VARCHAR(255) NOT NULL,			-- from embeddable Address
        addr_country VARCHAR(255) NOT NULL,			-- from embeddable Address
        addr_coordinates VARCHAR(255),				-- from embeddable Address
        description LONG VARCHAR,
        legal_identification VARCHAR(255) NOT NULL,
        site_name VARCHAR(255) NOT NULL,
        fk_enterprise_id BIGINT CONSTRAINT fk_site_enterprise REFERENCES enterprise(enterprise_id) NOT NULL,
        PRIMARY KEY (site_id)
    );

    -- Table product from entity Product
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

    -- Table hibernate_sequences (from hibernate)
    CREATE TABLE hibernate_sequences (
         sequence_name VARCHAR(255),
         next_val BIGINT
    );

    -- Sequence hibernate_sequence (from hibernate)
    CREATE SEQUENCE hibernate_sequence AS BIGINT START WITH 1;