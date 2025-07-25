mvn spring-boot:run &

APP_PID=$!

chmod a+x ./tests/.github/workflows/wait-for-it.sh

./tests/.github/workflows/wait-for-it.sh -t 60 localhost:8080

netstat -tulnp || true

wait $APP_PID