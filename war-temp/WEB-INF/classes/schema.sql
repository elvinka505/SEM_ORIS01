-- Удаляем таблицы если существуют (для пересоздания)
DROP TABLE IF EXISTS reviews CASCADE;
DROP TABLE IF EXISTS bookings CASCADE;
DROP TABLE IF EXISTS schedules CASCADE;
DROP TABLE IF EXISTS tours CASCADE;
DROP TABLE IF EXISTS users CASCADE;

-- Таблица пользователей
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(100) UNIQUE NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       role VARCHAR(50) DEFAULT 'user',
                       created_at TIMESTAMP DEFAULT now()
);

-- Таблица туров
CREATE TABLE tours (
                       id SERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       description TEXT,
                       destination VARCHAR(255),
                       price NUMERIC(10, 2) NOT NULL,
                       img VARCHAR(500),
                       created_at TIMESTAMP DEFAULT now()
);

-- Таблица расписаний (один тур - много расписаний) O2M
CREATE TABLE schedules (
                           id SERIAL PRIMARY KEY,
                           tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
                           start_date TIMESTAMP NOT NULL,
                           end_date TIMESTAMP NOT NULL,
                           location VARCHAR(255),
                           description TEXT,
                           available_seats INTEGER DEFAULT 0,
                           created_at TIMESTAMP DEFAULT now()
);

-- Таблица бронирований (пользователь бронирует тур) O2M
CREATE TABLE bookings (
                          id SERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
                          booking_date DATE NOT NULL,
                          status VARCHAR(50) DEFAULT 'pending',
                          created_at TIMESTAMP DEFAULT now()
);

-- Таблица отзывов (пользователь оставляет отзыв на тур) O2M
CREATE TABLE reviews (
                         id SERIAL PRIMARY KEY,
                         tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
                         user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                         text TEXT,
                         rating INTEGER CHECK (rating >= 1 AND rating <= 5),
                         created_at TIMESTAMP DEFAULT now()
);

-- Индексы для оптимизации
CREATE INDEX idx_tours_name ON tours(name);
CREATE INDEX idx_tours_destination ON tours(destination);
CREATE INDEX idx_bookings_user ON bookings(user_id);
CREATE INDEX idx_bookings_tour ON bookings(tour_id);
CREATE INDEX idx_reviews_tour ON reviews(tour_id);
CREATE INDEX idx_reviews_user ON reviews(user_id);
CREATE INDEX idx_schedules_tour ON schedules(tour_id);

-- Вставка тестовых данных
-- Admin пользователь (пароль: admin123 - будет захеширован через BCrypt)
INSERT INTO users (username, email, password, first_name, last_name, role)
VALUES ('admin', 'admin@travelplanner.ru', '$2a$10$N9qo8uLOickgx2ZMRZoMye1gqLTJzVxRnkHEZbKDGqDXfXKpKK.Ui', 'Админ', 'Админов', 'admin');

-- Обычный пользователь (пароль: user123)
INSERT INTO users (username, email, password, first_name, last_name, role)
VALUES ('user', 'user@example.com', '$2a$10$92IXUNpkjO0rOQ5byMi.Ye4oKoEa3Ro9llC/.og/at2.uheWG/igi', 'Иван', 'Петров', 'user');

-- Туры
INSERT INTO tours (name, description, destination, price, img) VALUES
                                                                   ('Париж - город огней', 'Незабываемое путешествие в столицу Франции. Эйфелева башня, Лувр, Собор Парижской Богоматери ждут вас!', 'Париж, Франция', 85000.00, '/static/img/paris.jpg'),
                                                                   ('Романтическая Венеция', 'Прогулка на гондолах, площадь Сан-Марко, мост Риальто. Окунитесь в атмосферу итальянской романтики!', 'Венеция, Италия', 72000.00, '/static/img/venice.jpg'),
                                                                   ('Токио - город будущего', 'Современные технологии и древние традиции. Императорский дворец, Акихабара, гора Фудзи.', 'Токио, Япония', 150000.00, '/static/img/tokyo.jpg'),
                                                                   ('Барселона - жемчужина Испании', 'Готический квартал, Саграда Фамилия, Парк Гуэль. Гауди и средиземноморское побережье!', 'Барселона, Испания', 68000.00, '/static/img/barcelona.jpg'),
                                                                   ('Таинственный Стамбул', 'Босфор, Голубая мечеть, дворец Топкапы. Мост между Европой и Азией.', 'Стамбул, Турция', 55000.00, '/static/img/istanbul.jpg');

-- Расписания для туров
INSERT INTO schedules (tour_id, start_date, end_date, location, description, available_seats) VALUES
                                                                                                  (1, '2025-12-15 09:00:00', '2025-12-22 18:00:00', 'Париж', 'Зимний тур в Париж', 15),
                                                                                                  (1, '2026-03-10 10:00:00', '2026-03-17 19:00:00', 'Париж', 'Весенний тур в Париж', 20),
                                                                                                  (2, '2025-11-20 08:00:00', '2025-11-26 20:00:00', 'Венеция', 'Осенняя Венеция', 12),
                                                                                                  (3, '2026-04-05 11:00:00', '2026-04-15 22:00:00', 'Токио', 'Сезон цветения сакуры', 10),
                                                                                                  (4, '2026-06-01 09:00:00', '2026-06-08 18:00:00', 'Барселона', 'Летняя Барселона', 18);

COMMIT;
