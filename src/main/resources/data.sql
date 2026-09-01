INSERT INTO UTENTE (username, password, ruolo)
SELECT 'admin', '$2y$10$RiWgNHdhSoWmyYRU6jOgB.4sU/WRX5sxd4C0/4OhtjWW9jp2W2pTG', 'ADMIN'
    WHERE NOT EXISTS (
    SELECT 1
    FROM UTENTE
    WHERE username = 'admin'
);