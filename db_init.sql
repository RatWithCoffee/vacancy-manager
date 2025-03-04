DROP TABLE IF EXISTS manager;

DROP TABLE IF EXISTS vacancy;

DROP TABLE IF EXISTS candidate;

CREATE TABLE manager (
                          id SERIAL PRIMARY KEY,                          -- Уникальный идентификатор менеджера
                          first_name VARCHAR(100) NOT NULL,               -- Имя менеджера
                          last_name VARCHAR(100) NOT NULL,                -- Фамилия менеджера
                          email VARCHAR(255) UNIQUE NOT NULL,             -- Электронная почта менеджера
                          phone VARCHAR(20),                              -- Телефон менеджера
);

CREATE TABLE vacancy (
                           id SERIAL PRIMARY KEY,                          -- Уникальный идентификатор вакансии
                           title VARCHAR(255) NOT NULL,                    -- Название вакансии
                           description VARCHAR,                               -- Описание вакансии
                           salary DECIMAL(10, 2),                          -- Зарплата по вакансии
                           posted_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,  -- Дата и время публикации вакансии
                           manager_id INT REFERENCES managers(id) ON DELETE SET NULL, -- Менеджер, который курирует вакансию
);

CREATE TABLE candidate (
                            id SERIAL PRIMARY KEY,                          -- Уникальный идентификатор соискателя
                            first_name VARCHAR(100) NOT NULL,               -- Имя соискателя
                            last_name VARCHAR(100) NOT NULL,                -- Фамилия соискателя
                            email VARCHAR(255) UNIQUE NOT NULL,             -- Электронная почта соискателя
                            phone VARCHAR(20),                              -- Телефон соискателя
                            cv TEXT,                                        -- Ссылка на резюме соискателя
);

