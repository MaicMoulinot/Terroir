    create table tr_admin (
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

    create table tr_customer (
        user_id bigint not null,
        user_email varchar(255) unique not null,
        user_firstname varchar(255),
        user_lastname varchar(255),
        user_name varchar(255) unique not null,
        user_password varchar(255) not null,
        address_complement varchar(255),
        address_coordinates varchar(255),
        address_country varchar(255) not null,
        address_post_code varchar(255) not null,
        address_street varchar(255),
        address_city varchar(255) not null,
        date_birth date,
        date_signup timestamp,
        primary key (user_id)
    );

    create table tr_enterprise (
        enterprise_id bigint not null,
        address_complement varchar(255),
        address_coordinates varchar(255),
        address_country varchar(255) not null,
        address_post_code varchar(255) not null,
        address_street varchar(255),
        address_city varchar(255) not null,
        date_creation date,
        legal_identification varchar(255) not null,
        legal_name varchar(255) unique not null,
        number_employees bigint,
        date_signup timestamp,
        trade_name varchar(255) unique not null,
        primary key (enterprise_id)
    );

    create table tr_image (
        image_id bigint not null,
        title varchar(255) not null,
        description varchar(255),
        image_data blob not null,
        primary key (image_id)
    );

    create table tr_seller (
        user_id bigint not null,
        user_email varchar(255) unique not null,
        user_firstname varchar(255),
        user_lastname varchar(255),
        user_name varchar(255) unique not null,
        user_password varchar(255) not null,
        enterprise_enterprise_id bigint CONSTRAINT fk_seller_enterprise REFERENCES tr_enterprise(enterprise_id) not null,
        primary key (user_id)
    );

    create table tr_site (
        site_id bigint not null,
        address_complement varchar(255),
        address_coordinates varchar(255),
        address_country varchar(255) not null,
        address_post_code varchar(255) not null,
        address_street varchar(255),
        address_city varchar(255) not null,
        legal_identification varchar(255) not null,
        site_name varchar(255) not null,
        enterprise_enterprise_id bigint CONSTRAINT fk_site_enterprise REFERENCES tr_enterprise(enterprise_id) not null,
        primary key (site_id)
    );

    create table tr_product (
        product_id bigint not null,
        description varchar(255),
        quantity bigint,
        registration_date timestamp,
        title varchar(255) not null,
        site_site_id bigint CONSTRAINT fk_product_site REFERENCES tr_site(site_id) not null,
        primary key (product_id)
    );

    create sequence hibernate_sequence as bigint start with 1;

    create table hibernate_sequences (
         sequence_name varchar(255),
         next_val bigint
    );