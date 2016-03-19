    create table administrator (
        user_id bigint not null,
        user_email varchar(255) unique not null,
        user_firstname varchar(255),
        user_lastname varchar(255),
        user_name varchar(255) unique not null,
        user_password varchar(255) not null,
        can_delete int,
        can_read int,
        can_update int,
        primary key (user_id)
    );

    create table customer (
        user_id bigint not null,
        user_email varchar(255) unique not null,
        user_firstname varchar(255),
        user_lastname varchar(255),
        user_name varchar(255) unique not null,
        user_password varchar(255) not null,
        addr_complement varchar(255),
        addr_coordinates varchar(255),
        addr_country varchar(255) not null,
        addr_post_code varchar(255) not null,
        addr_street varchar(255),
        addr_city varchar(255) not null,
        birth_date date,
        registration_date timestamp,
        primary key (user_id)
    );
    
    create table image (
        image_id bigint not null,
        imag_title varchar(255) not null,
        imag_description varchar(255),
        imag_data blob not null,
        primary key (image_id)
    );
    
    create table qualitylabel (
        qualitylabel_id bigint not null,
        official_name varchar(255) unique not null,
        acronym varchar(255) unique not null,
        definition varchar(255),
        fk_image_id bigint CONSTRAINT fk_qualitylabel_image REFERENCES image(image_id),
        primary key (qualitylabel_id)
    );
    
    create table designation (
        designation_id bigint not null,
        addr_complement varchar(255),
        addr_coordinates varchar(255),
        addr_country varchar(255) not null,
        addr_post_code varchar(255) not null,
        addr_street varchar(255),
        addr_city varchar(255) not null,
        legal_act varchar(255),
        registered_name varchar(255) unique not null,
        transcripted_name varchar(255),
        fk_qualitylabel_id bigint CONSTRAINT fk_designation_qualitylabel REFERENCES qualitylabel(qualitylabel_id) not null,
        fk_image_id bigint CONSTRAINT fk_designation_image REFERENCES image(image_id),
        primary key (designation_id)
    );

    create table enterprise (
        enterprise_id bigint not null,
        addr_complement varchar(255),
        addr_coordinates varchar(255),
        addr_country varchar(255) not null,
        addr_post_code varchar(255) not null,
        addr_street varchar(255),
        addr_city varchar(255) not null,
        creation_date date,
        legal_identification varchar(255) not null,
        legal_name varchar(255) unique not null,
        number_employees bigint,
        registration_date timestamp,
        trade_name varchar(255) unique not null,
        primary key (enterprise_id)
    );

    create table seller (
        user_id bigint not null,
        user_email varchar(255) unique not null,
        user_firstname varchar(255),
        user_lastname varchar(255),
        user_name varchar(255) unique not null,
        user_password varchar(255) not null,
        fk_enterprise_id bigint CONSTRAINT fk_seller_enterprise REFERENCES enterprise(enterprise_id) not null,
        primary key (user_id)
    );

    create table site (
        site_id bigint not null,
        addr_complement varchar(255),
        addr_coordinates varchar(255),
        addr_country varchar(255) not null,
        addr_post_code varchar(255) not null,
        addr_street varchar(255),
        addr_city varchar(255) not null,
        legal_identification varchar(255) not null,
        site_name varchar(255) not null,
        fk_enterprise_id bigint CONSTRAINT fk_site_enterprise REFERENCES enterprise(enterprise_id) not null,
        primary key (site_id)
    );

    create table product (
        product_id bigint not null,
        description varchar(255),
        quantity bigint,
        price double,
        last_update timestamp,
        title varchar(255) not null,
        fk_site_id bigint CONSTRAINT fk_product_site REFERENCES site(site_id) not null,
        fk_designation_id bigint CONSTRAINT fk_product_designation REFERENCES designation(designation_id),
        primary key (product_id)
    );

    create sequence hibernate_sequence as bigint start with 1;

    create table hibernate_sequences (
         sequence_name varchar(255),
         next_val bigint
    );