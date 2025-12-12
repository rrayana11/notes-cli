package com.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class NotesStore {
    // Путь к файлу с заметками
    private static final String FILE_PATH = "data/notes.csv";
    
    // Добавить заметку и вернуть её ID
    public static int addNote(String text) throws IOException {
        // Создаем папку data, если её нет
        Files.createDirectories(Paths.get("data"));
        
        // Читаем все заметки, чтобы узнать максимальный ID
        List<String[]> notes = readAllNotes();
        
        // Находим новый ID
        int newId = 1;
        if (!notes.isEmpty()) {
            int maxId = 0;
            for (String[] note : notes) {
                int id = Integer.parseInt(note[0]);
                if (id > maxId) {
                    maxId = id;
                }
            }
            newId = maxId + 1;
        }
        
        // Формируем строку для файла
        String noteLine = newId + ";" + text;
        
        // Открываем файл в режиме ДОБАВЛЕНИЯ (append)
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(noteLine);
            writer.newLine();
        }
        
        return newId;
    }
    
    // Получить все заметки
    public static List<String[]> getAllNotes() throws IOException {
        return readAllNotes();
    }
    
    // Приватный метод для чтения файла
    private static List<String[]> readAllNotes() throws IOException {
        List<String[]> notes = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        // Если файла нет - возвращаем пустой список
        if (!file.exists()) {
            return notes;
        }
        
        // Читаем файл построчно
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Разделяем на ID и текст
                String[] parts = line.split(";", 2);
                if (parts.length == 2) {
                    notes.add(parts);
                }
            }
        }
        
        return notes;
    }
}
