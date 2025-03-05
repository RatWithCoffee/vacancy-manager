DROP TABLE IF EXISTS manager;

DROP TABLE IF EXISTS vacancy;

DROP TABLE IF EXISTS candidate;

CREATE TABLE manager (
                          id SERIAL PRIMARY KEY,                          -- Уникальный идентификатор менеджера
                          first_name VARCHAR(100) NOT NULL,               -- Имя менеджера
                          last_name VARCHAR(100) NOT NULL,                -- Фамилия менеджера
                          patronymic VARCHAR(100) NOT NULL,
                          email VARCHAR(255) UNIQUE NOT NULL,             -- Электронная почта менеджера
                          phone VARCHAR(20)                              -- Телефон менеджера
);

CREATE TABLE vacancy (
                           id SERIAL PRIMARY KEY,                          -- Уникальный идентификатор вакансии
                           title VARCHAR(255) NOT NULL,                    -- Название вакансии
                           description VARCHAR,                               -- Описание вакансии
                           salary DECIMAL(10, 2),                          -- Зарплата по вакансии
                           manager_id INT REFERENCES manager(id) ON DELETE SET NULL -- Менеджер, который курирует вакансию
);

CREATE TABLE candidate (
                            id SERIAL PRIMARY KEY,                          -- Уникальный идентификатор соискателя
                            first_name VARCHAR(100) NOT NULL,               -- Имя соискателя
                            last_name VARCHAR(100) NOT NULL,                -- Фамилия соискателя
                            email VARCHAR(255) UNIQUE NOT NULL,             -- Электронная почта соискателя
                            phone VARCHAR(20),                              -- Телефон соискателя
                            cv TEXT,                                        -- Ссылка на резюме соискателя
                            vacancy_id INT REFERENCES vacancy(id) ON DELETE CASCADE
);

INSERT INTO manager (first_name, last_name, patronymic, email, phone) VALUES
                                                                          ('Иван', 'Иванов', 'Иванович', 'ivanov@example.com', '+7 (900) 123-45-67'),
                                                                          ('Мария', 'Петрова', 'Петровна', 'petrova@example.com', '+7 (900) 234-56-78'),
                                                                          ('Алексей', 'Сидоров', 'Алексеевич', 'sidorov@example.com', '+7 (900) 345-67-89'),
                                                                          ('Екатерина', 'Михайлова', 'Викторовна', 'mihailova@example.com', '+7 (900) 456-78-90');

-- Вставка данных в таблицу vacancy
INSERT INTO vacancy (title, description, salary, manager_id) VALUES
                                                                 ('Менеджер по продажам', 'Поиск и привлечение новых клиентов, ведение переговоров, заключение контрактов.', 50000.00, 1),
                                                                 ('Системный администратор', 'Настройка и обслуживание серверного оборудования, настройка сетевой инфраструктуры.', 60000.00, 2),
                                                                 ('Маркетолог', 'Разработка и внедрение маркетинговых стратегий, анализ рынка и конкурентов.', 70000.00, 3),
                                                                 ('HR-менеджер', 'Подбор персонала, проведение собеседований, участие в разработке внутренней политики компании.', 55000.00, 4);

