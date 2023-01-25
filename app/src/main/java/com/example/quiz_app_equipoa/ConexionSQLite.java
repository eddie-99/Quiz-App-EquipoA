package com.example.quiz_app_equipoa;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionSQLite extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Quiz.db";

    public ConexionSQLite(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE users " +
                "(user_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " username TEXT NOT NULL," +
                " email TEXT NOT NULL," +
                " password TEXT NOT NULL)");

        db.execSQL("CREATE TABLE questions " +
                "(question_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " question_text TEXT NOT NULL," +
                " correct_answer INTEGER NOT NULL," +
                " category_id INTEGER," +
                " FOREIGN KEY (category_id) REFERENCES categories(category_id))");

        db.execSQL("CREATE TABLE answers " +
                "(answer_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " question_id INTEGER," +
                " answer_text TEXT NOT NULL," +
                " FOREIGN KEY (question_id) REFERENCES questions(question_id))");

        db.execSQL("CREATE TABLE categories " +
                "(category_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " category_name TEXT NOT NULL)");

        db.execSQL("CREATE TABLE quiz_history " +
                "(quiz_history_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " user_id INTEGER," +
                " question_id INTEGER," +
                " answer_id INTEGER," +
                " quiz_date INTEGER NOT NULL," +
                " FOREIGN KEY (user_id) REFERENCES users(user_id)," +
                " FOREIGN KEY (question_id) REFERENCES questions(question_id)," +
                " FOREIGN KEY (answer_id) REFERENCES answers(answer_id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS users");
        db.execSQL("DROP TABLE IF EXISTS questions");
        db.execSQL("DROP TABLE IF EXISTS answers");
        db.execSQL("DROP TABLE IF EXISTS categories");
        db.execSQL("DROP TABLE IF EXISTS quiz_history");
        onCreate(db);
    }
}

/*
*
users:
user_id (primary key)
username
email
password
*
questions:
question_id (primary key)
question_text
correct_answer
category (foreign key to categories.category_id)
*
answers:
answer_id (primary key)
question_id (foreign key to questions.question_id)
answer_text
*
categories:
category_id (primary key)
category_name
*
quiz_history:
quiz_history_id (primary key)
user_id (foreign key to users.user_id)
question_id (foreign key to questions.question_id)
answer_id (foreign key to answers.answer_id)
quiz_date
*
En este esquema se puede ver que hay varias tablas:
users: guarda información de los usuarios
questions: guarda las preguntas del cuestionario
answers: guarda las posibles respuestas a las preguntas
categories: guarda las categorías a las que pertenecen las preguntas.
quiz_history: guarda el historial de preguntas y respuestas de los usuarios.
* */