chmod a+x ./tests/.github/workflows/wait-for-it.sh

java -jar ./target/shareit-0.0.1-SNAPSHOT.jar &

APP_PID=$!

./tests/.github/workflows/wait-for-it.sh -t 60 localhost:8080

netstat -tulnp || true

wait $APP_PID