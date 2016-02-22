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
	description text,
	category_name text,
	category_description text
	
);


