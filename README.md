Консольное приложение для управления заметками на Java**

![Java](https://img.shields.io/badge/Java-11%2F17-blue)
![Docker](https://img.shields.io/badge/Docker-✔-blue)
![GitHub Actions](https://img.shields.io/badge/CI%2FCD-GitHub_Actions-green)
![SemVer](https://img.shields.io/badge/Versioning-SemVer-orange)

Автор: [rrayana11](https://github.com/rrayana11)  
Репозиторий: https://github.com/rrayana11/notes-cli

О проекте

Notes CLI — это простое консольное приложение для создания, просмотра и управления текстовыми заметками. Данные сохраняются в локальный файл и не теряются между запусками.

Проект демонстрирует полный цикл современной разработки ПО:
- Разработка на Java с использованием ООП
- Контроль версий через Git с workflow Git Flow
- Непрерывная интеграция через GitHub Actions
- Контейнеризация через Docker
- Семантическое версионирование (SemVer)

Структура проекта

notes-cli/
├── src/com/example/          # Исходный код Java
│   ├── App.java             # Точка входа, обработка команд
│   └── NotesStore.java      # Логика работы с данными
├── data/                    # Хранение заметок
│   └── notes.csv           # Файл данных (CSV формат)
├── .github/workflows/       # CI/CD конфигурация
│   └── ci.yml              # GitHub Actions workflow
├── Dockerfile              # Конфигурация Docker
├── .gitignore              # Игнорируемые файлы
└── README.md               # Документация


Команды

| Команда | Аргументы | Описание | Пример |
|---------|-----------|----------|--------|
| `add` | `--text="Текст"` | Добавляет новую заметку | `--cmd=add --text="Купить молоко"` |
| `list` | — | Показывает все заметки | `--cmd=list` |
| `rm` | `--id=<ID>` | Удаляет заметку по ID | `--cmd=rm --id=1` |



-----Примеры использования

Локальный запуск
javac src/com/example/*.java
java -cp src com.example.App --cmd=add --text="Первая заметка"
java -cp src com.example.App --cmd=list
java -cp src com.example.App --cmd=rm --id=1

Через Docker
docker build -t notes-cli .
docker run --rm -v "$PWD/data:/app/data" notes-cli --cmd=add --text="Тест"

Запуск через Docker

Приложение упаковано в Docker-контейнер для гарантированной работы в любой среде.


1. Сборка образа
docker build -t notes-cli .

2. Создание папки для данных
mkdir -p ./data

3. Запуск контейнера
docker run --rm -v "$PWD/data:/app/data" notes-cli --cmd=add --text="Заметка из Docker"
docker run --rm -v "$PWD/data:/app/data" notes-cli --cmd=list

4. Проверка сохранения данных
cat ./data/notes.csv

Процесс разработки

Проект разработан с использованием Git Flow:

main (v1.0.0)                    main (v1.1.0)
    │                                  ▲
    ├─ feature/remove-note ────┐       │
    │ (разработка команды rm)  ├─ PR ──┘
    └──────────────────────────┘


Технологии

- Java 11/17 — основной язык разработки
- Git — контроль версий
- GitHub Actions — CI/CD (непрерывная интеграция)
- Docker — контейнеризация приложения
- Semantic Versioning — управление версиями
- CSV формат — хранение данных

CI/CD Pipeline

При каждом push и pull request автоматически выполняется:
1. Клонирование репозитория
2. Установка Java 17
3. Компиляция Java-кода
4. Отчет об успехе/ошибке

Статус CI: ![Java CI](https://github.com/rrayana11/notes-cli/actions/workflows/ci.yml/badge.svg)

Автор:rrayana11  
GitHub:https://github.com/rrayana11  
Репозиторий:https://github.com/rrayana11/notes-cli  
