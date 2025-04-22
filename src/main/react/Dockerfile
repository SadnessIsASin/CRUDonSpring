# Этап 1: Сборка приложения
FROM node:16 AS builder

# Установка рабочей директории
WORKDIR /app

# Копирование package.json и package-lock.json
COPY package*.json ./

# Установка зависимостей
RUN npm install

# Копирование исходного кода
COPY . .

# Задание переменных окружения для сборки
ARG REACT_APP_API_URL
ENV REACT_APP_API_URL=${REACT_APP_API_URL}

# Сборка приложения
RUN npm run build

# Этап 2: Запуск приложения через nginx
FROM nginx:alpine

# Копирование собранных файлов в nginx
COPY --from=builder /app/build /usr/share/nginx/html

# Открытие порта 80
EXPOSE 80

# Запуск nginx
CMD ["nginx", "-g", "daemon off;"]