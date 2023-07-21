CREATE TABLE IF NOT EXISTS meal (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  image_url TEXT,
  recipe TEXT,
  video_url TEXT,
  themealdb_id TEXT,
  added_timestamp INTEGER
);

CREATE TABLE IF NOT EXISTS ingredient (
  id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
  name TEXT NOT NULL,
  measure TEXT NOT NULL,
  meal_id INTEGER,
  FOREIGN KEY (meal_id) REFERENCES meal(id)
);
