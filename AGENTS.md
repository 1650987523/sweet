# project overview

this is project overview.

- Language: Java
- Build tool: Maven
- Framework: Spring Boot 3
- Testing Framework: JUnit

## 项目定位
**🎓 本项目主要用于供大家学习和研究，非企业级生产框架。**

## 部署地址
**🌐 线上环境**: [https://wanghengrun.shop](https://wanghengrun.shop)

Description of the architecture

project structure describe here

The project uses `.env` for configuration.

List your backing services, such as database, message queue, external services, etc.

- Main database: PostgreSQL
- Table Naming: Singular, prefer `account` instead of `accounts`

The project uses Maven to build and run the project, and some tasks as following:

- Build: `just build`
- Run: `just start`

For other tasks, the project uses [just](https://github.com/casey/just) as the task runner,
and the available recipes are as below:

- `just build`: build the project


- `8080`: web server listen port, and main page is http://localhost:8080