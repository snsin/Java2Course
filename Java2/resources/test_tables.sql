drop table public.categories;
drop table public.records;
drop table public.accounts;
drop table public.users;

CREATE TABLE IF NOT EXISTS public.users (
	id SERIAL PRIMARY KEY,
	login TEXT NOT NULL UNIQUE,
	pass TEXT NOT NULL
);

CREATE TABLE IF NOT EXISTS public.accounts (
	id SERIAL PRIMARY KEY,
	user_id integer REFERENCES public.users,
	balance numeric,
	description text
);

CREATE TABLE IF NOT EXISTS public.records (
	id SERIAL PRIMARY KEY,
	account_id integer REFERENCES public.accounts,
	transfer integer CHECK(transfer = -1 OR transfer = 1),
	date timestamp,
	amount numeric,
	description text
	
);




create table if not exists public.categories_names (
	id SERIAL PRIMARY KEY,
	name text NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS public.categories (
	id SERIAL PRIMARY KEY,
	record_id integer references public.records,
	name text references public.categories_names (name),
	description text
);

