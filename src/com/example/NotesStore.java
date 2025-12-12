package com.example;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class NotesStore {
    private static final String FILE_PATH = "data/notes.csv";
    
    public static int addNote(String text) throws IOException {
        Files.createDirectories(Paths.get("data"));
        List<String[]> notes = readAllNotes();
        
        int newId = 1;
        if (!notes.isEmpty()) {
            int maxId = 0;
            for (String[] note : notes) {
                int id = Integer.parseInt(note[0]);
                if (id > maxId) maxId = id;
            }
            newId = maxId + 1;
        }
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(newId + ";" + text);
            writer.newLine();
        }
        
        return newId;
    }
    
    public static List<String[]> getAllNotes() throws IOException {
        return readAllNotes();
    }
    
    // НОВЫЙ МЕТОД: удаление заметки по ID
    public static boolean removeNoteById(int id) throws IOException {
        List<String[]> notes = readAllNotes();
        boolean removed = false;
        
        // Ищем заметку с нужным ID
        for (Iterator<String[]> iterator = notes.iterator(); iterator.hasNext(); ) {
            String[] note = iterator.next();
            if (Integer.parseInt(note[0]) == id) {
                iterator.remove();
                removed = true;
                break;
            }
        }
        
        // Если нашли и удалили - перезаписываем файл
        if (removed) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                for (String[] note : notes) {
                    writer.write(note[0] + ";" + note[1]);
                    writer.newLine();
                }
            }
        }
        
        return removed;
    }
    
    private static List<String[]> readAllNotes() throws IOException {
        List<String[]> notes = new ArrayList<>();
        File file = new File(FILE_PATH);
        
        if (!file.exists()) return notes;
        
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(";", 2);
                if (parts.length == 2) notes.add(parts);
            }
        }
        
        return notes;
    }
}
