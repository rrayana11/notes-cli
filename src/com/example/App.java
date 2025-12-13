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
            switch (command) {
                case "add":
                    handleAddCommand(params);
                    break;
                case "list":
                    handleListCommand();
                    break;
                case "rm":  // НОВАЯ КОМАНДА
                    handleRemoveCommand(params);
                    break;
                default:
                    System.out.println("Unknown command: " + command);
                    printHelp();
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    private static Map<String, String> parseArgs(String[] args) {
        Map<String, String> map = new HashMap<>();
        
        for (String arg : args) {
            if (arg.startsWith("--")) {
                String keyValue = arg.substring(2);
                String[] parts = keyValue.split("=", 2);
                if (parts.length == 2) {
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
            System.out.println("Error: text is required for add command");
            System.out.println("Example: --cmd=add --text='Your note'");
            return;
        }
        
        int newId = NotesStore.addNote(text);
        System.out.println("Note added with ID: " + newId);
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
    
    // НОВЫЙ МЕТОД: обработка команды удаления
    private static void handleRemoveCommand(Map<String, String> params) throws Exception {
        String idStr = params.get("id");
        
        if (idStr == null || idStr.trim().isEmpty()) {
            System.out.println("Error: id is required for rm command");
            System.out.println("Example: --cmd=rm --id=1");
            return;
        }
        
        try {
            int id = Integer.parseInt(idStr);
            boolean removed = NotesStore.removeNoteById(id);
            
            if (removed) {
                System.out.println("Note #" + id + " removed.");
            } else {
                System.out.println("Not found #" + id);
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: id must be a number");
        }
    }
    
    private static void printHelp() {
        System.out.println("Usage:");
        System.out.println("  --cmd=add --text='Note text'    Add new note");
        System.out.println("  --cmd=list                      List all notes");
        System.out.println("  --cmd=rm --id=<ID>              Remove note by ID");
        System.out.println("");
        System.out.println("Examples:");
        System.out.println("  java -cp src com.example.App --cmd=add --text='Buy milk'");
        System.out.println("  java -cp src com.example.App --cmd=list");
        System.out.println("  java -cp src com.example.App --cmd=rm --id=1");
    }
}
