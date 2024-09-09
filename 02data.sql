-- Insert data into company
INSERT INTO company (name) VALUES
                               ('Luxmed'),
                               ('Medonet'),
                               ('Medica'),
                               ('NFZ');

-- Insert data into department
INSERT INTO department (name, company_id) VALUES
                                              ('Engineering', 1), -- Luxmed
                                              ('Marketing', 1),   -- Luxmed
                                              ('Finance', 2),     -- Medonet
                                              ('Research', 3);    -- Medica

-- Insert data into manager
INSERT INTO manager (name, email, phone_number) VALUES
                                                    ('Alicja Bachleda', 'alicja.b@luxmed.com', '123-456-7890'),
                                                    ('Boban Markowicz', 'boban.m@luxmed.com', '234-567-8901'),
                                                    ('Igor Zygfrydowicz', 'igor.z@luxmed.com', '345-678-9012'),
                                                    ('Diana Angielska', 'diana.a@medonet.com', '456-789-0123'),
                                                    ('Ewa Jabko', 'ewa.j@medica.com', '567-890-1234');

-- Insert data into project
INSERT INTO project (name, manager_id) VALUES
                                           ('Website Redesign', 1),  -- Alicja Bachleda
                                           ('API Development', 2),   -- Boban Markowicz
                                           ('SEO Optimization', 3),  -- Igor Zygfrydowicz
                                           ('Investment Strategy', 4), -- Diana Angielska
                                           ('Product Research', 5);  -- Ewa Jabko

-- Insert data into team
INSERT INTO team (name, department_id, project_id) VALUES
                                                       ('Backend Team', 1, NULL),  -- Engineering Department, no specific project
                                                       ('Frontend Team', 1, NULL), -- Engineering Department, no specific project
                                                       ('SEO Team', 2, NULL),      -- Marketing Department, no specific project
                                                       ('Investment Team', 3, 4),  -- Finance Department, linked to Investment Strategy project
                                                       ('R&D Team', 4, 5);         -- Research Department, linked to Product Research project