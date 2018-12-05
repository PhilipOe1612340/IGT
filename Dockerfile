FROM node:10.13.0-slim
WORKDIR /tmp/
COPY package.json package-lock.json ./
COPY src ./src
RUN npm install && npm run build
EXPOSE 5000
CMD ["npm", "run", "server"]