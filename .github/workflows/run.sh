# Делаем скрипт ожидания доступным
chmod +x ./tests/.github/workflows/wait-for-it.sh

# Запускаем приложение
java -jar ./target/shareit-0.0.1-SNAPSHOT.jar &

APP_PID=$!

# Ждём, пока приложение поднимется
./tests/.github/workflows/wait-for-it.sh -t 60 localhost:8080

# Выводим список прослушиваемых портов (для отладки)
netstat -tulnp || true

# Ждём завершения приложения (необязательно, можно убрать)
wait $APP_PID