create table users
(
  id bigint not null
    constraint users_pkey
    primary key,
  password varchar(255),
  username varchar(255),
  date_of_birth date,
  first_name varchar(255),
  last_name varchar(255)
)
;

create index users_username_index
  on users (username)
;

create table groups
(
  id bigint not null
    constraint groups_pkey
    primary key,
  name varchar(255)
)
;

create table users_groups
(
  user_id bigint not null
    constraint user_id_not_null
    references users,
  group_id bigint not null
    constraint group_id_not_null
    references groups,
  constraint users_groups_pkey
  primary key (user_id, group_id)
)
;

-- enhanced sequence
create sequence hibernate_sequence;
