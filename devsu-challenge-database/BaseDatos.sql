create table persons (
  id SERIAL primary key not null,
  name VARCHAR(100),
  gender VARCHAR(100),
  age INTEGER,
  person_id VARCHAR(50),
  address VARCHAR(100),
  phone VARCHAR(50)
);

create table clients (
  id INTEGER not null,
  password_hash VARCHAR(100),
  client_id VARCHAR(100),
  status VARCHAR(50),
  creation_date DATE default CURRENT_DATE,
  updated_date DATE,
  user_who_updates VARCHAR(50),
  foreign key (id) 
  references persons(id)
);

create table accounts (
    id SERIAL primary key,
    client_id INTEGER not null,
    account_number VARCHAR(100) not null,
    account_type VARCHAR(50) not null,
    initial_balance DOUBLE PRECISION not null,
    current_balance DOUBLE PRECISION not null,
    status VARCHAR(50) not null,
    creation_date TIMESTAMP default CURRENT_DATE,
    updated_date TIMESTAMP,
    user_who_updates VARCHAR(50)
);

create table movements (
    id SERIAL primary key,
    account_id INTEGER NOT NULL,
    movement_date TIMESTAMP default CURRENT_DATE,
    value DOUBLE PRECISION not null,
    final_balance DOUBLE PRECISION not null,
    type VARCHAR(50) not null,
    status VARCHAR(50) not null,
    user_who_create VARCHAR(50),
    foreign key (account_id) 
  	references accounts(id)
);
