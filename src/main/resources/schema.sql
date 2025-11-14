-- Таблица пользователей
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
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
                       created_at TIMESTAMP DEFAULT now()
);

-- Таблица расписаний (один тур - много расписаний)
CREATE TABLE schedules (
                           id SERIAL PRIMARY KEY,
                           tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
                           start_date DATE NOT NULL,
                           end_date DATE NOT NULL,
                           location VARCHAR(255),
                           description TEXT,
                           available_seats INTEGER DEFAULT 0,
                           created_at TIMESTAMP DEFAULT now()
);

-- Таблица бронирований (пользователь бронирует расписание)
CREATE TABLE bookings (
                          id SERIAL PRIMARY KEY,
                          user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
                          booking_date DATE NOT NULL,
                          status VARCHAR(50) DEFAULT 'pending',
                          created_at TIMESTAMP DEFAULT now()
);

-- Таблица отзывов
CREATE TABLE reviews (
                         id SERIAL PRIMARY KEY,
                         tour_id BIGINT NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
                         user_id BIGINT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                         text TEXT,
                         rating INTEGER CHECK (rating >= 1 AND rating <= 5),
                         created_at TIMESTAMP DEFAULT now()
);
CREATE TABLE IF NOT EXISTS user_tour_wishlist (
                                                  user_id BIGINT NOT NULL,
                                                  tour_id BIGINT NOT NULL,
                                                  added_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                                  PRIMARY KEY (user_id, tour_id),
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (tour_id) REFERENCES tour(id) ON DELETE CASCADE
    );

-- Индексы для оптимизации
CREATE INDEX idx_tours_name ON tours(name);
CREATE INDEX idx_bookings_user ON bookings(user_id);
CREATE INDEX idx_bookings_tour ON bookings(tour_id);
CREATE INDEX idx_reviews_tour ON reviews(tour_id);
CREATE INDEX idx_schedules_tour ON schedules(tour_id);
