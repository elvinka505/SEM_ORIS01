-- Users
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       first_name VARCHAR(100),
                       last_name VARCHAR(100),
                       email VARCHAR(150) UNIQUE NOT NULL,
                       password_hash VARCHAR(200) NOT NULL,
                       role VARCHAR(50) DEFAULT 'USER',
                       created_at TIMESTAMP DEFAULT now()
);

-- Tours
CREATE TABLE tours (
                       id SERIAL PRIMARY KEY,
                       title VARCHAR(255) NOT NULL,
                       description TEXT,
                       price NUMERIC(10,2),
                       created_at TIMESTAMP DEFAULT now()
);

-- Schedules (one tour can have many schedules)
CREATE TABLE schedules (
                           id SERIAL PRIMARY KEY,
                           tour_id INTEGER NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
                           start_date DATE NOT NULL,
                           seats INTEGER DEFAULT 0
);

-- Bookings (user books a schedule)
CREATE TABLE bookings (
                          id SERIAL PRIMARY KEY,
                          user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                          schedule_id INTEGER NOT NULL REFERENCES schedules(id) ON DELETE CASCADE,
                          created_at TIMESTAMP DEFAULT now()
);

-- Reviews
CREATE TABLE reviews (
                         id SERIAL PRIMARY KEY,
                         tour_id INTEGER NOT NULL REFERENCES tours(id) ON DELETE CASCADE,
                         user_id INTEGER NOT NULL REFERENCES users(id) ON DELETE CASCADE,
                         text TEXT,
                         rating INTEGER,
                         created_at TIMESTAMP DEFAULT now()
);
