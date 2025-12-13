Команды
- `--cmd=rm --id=<ID>` - удалить заметку по указанному ID
 История версий (SemVer)
- **v1.0.0** - начальный релиз с командами `add` и `list`
- **v1.1.0** - добавлена команда `rm` для удаления заметок по ID

**Выполни:**
```bash
cat > README.md << 'EOF'
# Notes CLI - Консольное приложение для заметок

**Автор:** [rrayana11](https://github.com/rrayana11)

Простое консольное приложение на Java для управления текстовыми заметками.

## Команды
- `--cmd=add --text="Текст заметки"` - добавить новую заметку
- `--cmd=list` - показать список всех заметок  
- `--cmd=rm --id=<ID>` - удалить заметку по указанному ID

## Запуск (локально)
```bash
javac src/com/example/*.java
java -cp src com.example.App --cmd=add --text="Первая заметка"
java -cp src com.example.App --cmd=list
java -cp src com.example.App --cmd=rm --id=1
```

## Запуск через Docker
```bash
docker build -t notes-cli .
docker run --rm -v "$PWD/data:/app/data" notes-cli --cmd=add --text="Тест"
docker run --rm -v "$PWD/data:/app/data" notes-cli --cmd=list
```

## История версий (SemVer)
- **v1.0.0** - начальный релиз с командами `add` и `list`
- **v1.1.0** - добавлена команда `rm` для удаления заметок по ID
