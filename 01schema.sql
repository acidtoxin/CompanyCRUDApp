-- Drop tables if they exist
DROP TABLE IF EXISTS team CASCADE;
DROP TABLE IF EXISTS project CASCADE;
DROP TABLE IF EXISTS department CASCADE;
DROP TABLE IF EXISTS manager CASCADE;
DROP TABLE IF EXISTS company CASCADE;

-- Create Company Table
CREATE TABLE company (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

-- Create Department Table
CREATE TABLE department (
                            id SERIAL PRIMARY KEY,
                            name VARCHAR(255) NOT NULL,
                            company_id BIGINT NOT NULL,
                            CONSTRAINT fk_company
                                FOREIGN KEY (company_id)
                                    REFERENCES company (id)
                                    ON DELETE CASCADE
);

-- Create Manager Table
CREATE TABLE manager (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         email VARCHAR(255) NOT NULL,
                         phone_number VARCHAR(50) NOT NULL
);

-- Create Project Table
CREATE TABLE project (
                         id SERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL,
                         manager_id BIGINT,
                         CONSTRAINT fk_manager
                             FOREIGN KEY (manager_id)
                                 REFERENCES manager (id)
                                 ON DELETE CASCADE
);

-- Create Team Table
CREATE TABLE team (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL,
                      department_id BIGINT NOT NULL,
                      project_id BIGINT,
                      CONSTRAINT fk_department
                          FOREIGN KEY (department_id)
                              REFERENCES department (id)
                              ON DELETE CASCADE,
                      CONSTRAINT fk_project
                          FOREIGN KEY (project_id)
                              REFERENCES project (id)
                              ON DELETE CASCADE
);