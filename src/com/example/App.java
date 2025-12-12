package com.example;

import java.util.*;

public class App {
    public static void main(String[] args) {
        System.out.println("=== Notes CLI v1.0 ===");
        
        Map<String, String> params = parseArgs(args);
        String command = params.get("cmd");
        
        if (command == null) {
            printHelp();
            return;
        }
        
        try {
            if (command.equals("add")) {
                handleAddCommand(params);
            } else if (command.equals("list")) {
                handleListCommand();
            } else {
                System.out.println("Неизвестная команда: " + command);
                printHelp();
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
    
    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String keyValue = arg.substring(2);
                String[] parts = keyValue.split("=", 2);
                if (parts.length == 2) {
                    // Убираем кавычки вокруг текста, если они есть
                    String value = parts[1].replaceAll("^\"|\"$", "");
                    map.put(parts[0], value);
                }
            }
        }
        
        return map;
    }
    
    private static void handleAddCommand(Map<String, String> params) throws Exception {
        String text = params.get("text");
        
        if (text == null || text.trim().isEmpty()) {
            System.out.println("Ошибка: нужно указать текст заметки");
            System.out.println("Пример: --cmd=add --text='Купить молоко'");
            return;
        }
        
        // Используем NotesStore для сохранения
        int newId = NotesStore.addNote(text);
        System.out.println("Заметка добавлена с ID: " + newId);
    }
    
    private static void handleListCommand() throws Exception {
        List<String[]> notes = NotesStore.getAllNotes();
        
        if (notes.isEmpty()) {
            System.out.println("(empty)");
        } else {
            for (String[] note : notes) {
                System.out.println(note[0] + ";" + note[1]);
            }
        }
    }
    
    private static void printHelp() {
        System.out.println("Справка по использованию:");
        System.out.println("  --cmd=add --text='Текст заметки'  - Добавить заметку");
        System.out.println("  --cmd=list                       - Показать все заметки");
        System.out.println("");
        System.out.println("Примеры:");
        System.out.println("  java -cp src com.example.App --cmd=add --text='Купить хлеб'");
        System.out.println("  java -cp src com.example.App --cmd=list");
    }
}
