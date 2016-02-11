    create table tr_admin (
        user_id bigint not null,
        email varchar(255) unique,
        first_name varchar(255),
        last_name varchar(255),
        user_name varchar(255) unique,
        user_password varchar(255),
        can_delete boolean,
        can_read boolean,
        can_update boolean,
        primary key (user_id)
    );

    create table tr_customer (
        user_id bigint not null,
        email varchar(255) unique,
        first_name varchar(255),
        last_name varchar(255),
        user_name varchar(255) unique,
        user_password varchar(255),
        address_complement varchar(255),
        address_coordinates varchar(255),
        address_country varchar(255),
        address_post_code varchar(255),
        address_street varchar(255),
        address_town varchar(255),
        date_birth date,
        date_signup timestamp,
        primary key (user_id)
    );

    create table tr_enterprise (
        enterprise_id bigint not null,
        address_complement varchar(255),
        address_coordinates varchar(255),
        address_country varchar(255),
        address_post_code varchar(255),
        address_street varchar(255),
        address_town varchar(255),
        date_creation date,
        legal_identification varchar(255),
        legal_name varchar(255) unique,
        number_employees bigint,
        date_signup timestamp,
        trade_name varchar(255) unique,
        primary key (enterprise_id)
    );

    create table tr_image (
        image_id bigint not null,
        image blob,
        primary key (image_id)
    );

    create table tr_seller (
        user_id bigint not null,
        email varchar(255) unique,
        first_name varchar(255),
        last_name varchar(255),
        user_name varchar(255) unique,
        user_password varchar(255),
        enterprise_id bigint CONSTRAINT seller_enterprise_id REFERENCES tr_enterprise,
        primary key (user_id)
    );

    create table tr_site (
        site_id bigint not null,
        address_complement varchar(255),
        address_coordinates varchar(255),
        address_country varchar(255),
        address_post_code varchar(255),
        address_street varchar(255),
        address_town varchar(255),
        legal_identification varchar(255),
        site_name varchar(255),
        enterprise_id bigint CONSTRAINT site_enterprise_id REFERENCES tr_enterprise,
        primary key (site_id)
    );

    create table tr_product (
        product_id bigint not null,
        description varchar(255),
        quantity bigint,
        registration_date timestamp,
        title varchar(255),
        site_id bigint CONSTRAINT product_site_id REFERENCES tr_site,
        primary key (product_id)
    );
    
    create sequence hibernate_sequences;

    create table hibernate_sequences (
         sequence_name varchar(255),
         sequence_next_hi_value bigint 
    );