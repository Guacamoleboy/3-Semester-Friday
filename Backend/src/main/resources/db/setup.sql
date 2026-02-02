/*

    Lavet af Jonas Meinert Larsen
    Fredagsprojekt - Backend
    3. Semester

    Sidst opdateret:
    Dato: 02/02-2026

*/

DROP TABLE IF EXISTS
baseline_individual,
baseline,
questions,
client_diagnoses,
medication,
medication_client,
diagnoses,
clients,
users,
roles
CASCADE;

CREATE TABLE roles (
id UUID PRIMARY KEY,
name VARCHAR(50) UNIQUE NOT NULL                                                                -- CLIENT, CLINIC, CLINICIAN
);

CREATE TABLE users (
id UUID PRIMARY KEY,
username VARCHAR(100) UNIQUE NOT NULL,                                                          -- Username
email_hash TEXT NOT NULL,                                                                       -- Safety (GDPR)
password_hash TEXT NOT NULL,                                                                    -- Safety (GDPR)
role_id UUID NOT NULL REFERENCES roles(id) ON DELETE RESTRICT,                                  -- Role
created_at TIMESTAMP DEFAULT NOW()
);

CREATE TABLE clients (
id_hash INT PRIMARY KEY,                                                                        -- CPR
id_ending INT NOT NULL,                                                                         -- Last 4 digits
clinician_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,                              -- Issuer
access_code VARCHAR(10) UNIQUE NOT NULL,                                                        -- Specific task
created_at TIMESTAMP DEFAULT NOW()                                                              -- Start
);

CREATE TABLE diagnoses (
id SERIAL PRIMARY KEY,
name VARCHAR(50) UNIQUE NOT NULL,                                                               -- Name
description TEXT                                                                                -- Description
);

CREATE TABLE medication (
id SERIAL PRIMARY KEY,
name VARCHAR(50) UNIQUE NOT NULL,                                                               -- Medication Name
description TEXT                                                                                -- Small description
);

CREATE TABLE medication_client (
id SERIAL PRIMARY KEY,
name VARCHAR(50) UNIQUE NOT NULL,                                                               -- Medication name
amount INT NOT NULL,                                                                            -- 1,2,3 etc
timeline VARCHAR(50) NOT NULL                                                                   -- Daily, Twice Daily
);

CREATE TABLE client_diagnoses (
id SERIAL PRIMARY KEY,
client_id INT NOT NULL REFERENCES clients(id_hash) ON DELETE CASCADE,                           -- Client
diagnosis_id INT NOT NULL REFERENCES diagnoses(id) ON DELETE CASCADE,                           -- Diagnose
start_date DATE NOT NULL DEFAULT CURRENT_DATE                                                   -- Start
UNIQUE(client_id, diagnosis_id)
);

CREATE TABLE questions (
id SERIAL PRIMARY KEY,
diagnosis_id INT NOT NULL REFERENCES diagnoses(id) ON DELETE CASCADE,                           -- Diagnose
description TEXT NOT NULL,                                                                      -- Question
value SMALLINT NOT NULL DEFAULT 6                                                               -- To prevent mid
);

CREATE TABLE baseline (
id SERIAL PRIMARY KEY,
client_id INT NOT NULL REFERENCES clients(id_hash) ON DELETE CASCADE,                           -- Client
diagnosis_id INT NOT NULL REFERENCES diagnoses(id) ON DELETE CASCADE,                           -- Diagnose
start_date DATE NOT NULL,                                                                       -- Start
end_date DATE NOT NULL                                                                          -- End
);

CREATE TABLE baseline_individual (
id SERIAL PRIMARY KEY,
baseline_id INT NOT NULL REFERENCES baseline(id) ON DELETE CASCADE,                             -- Baseline
client_id INT NOT NULL REFERENCES clients(id_hash) ON DELETE CASCADE,                           -- Client
question_id INT NOT NULL REFERENCES questions(id) ON DELETE CASCADE,                            -- Question
date DATE NOT NULL DEFAULT CURRENT_DATE,                                                        -- Per day
value NUMERIC(4,2) NOT NULL,                                                                    -- Valuation
notes TEXT,                                                                                     -- Optional
created_at TIMESTAMP DEFAULT NOW()                                                              -- Snapshot
);