@echo off
echo Setting up Online Store Database...
echo.

REM MySQL path - adjust if needed
set MYSQL_PATH=C:\Program Files\MySQL\MySQL Server 8.0\bin

REM Database credentials
set DB_USER=root
set DB_PASSWORD=12345
set DB_NAME=online_store

echo Creating database: %DB_NAME%
"%MYSQL_PATH%\mysql.exe" -u %DB_USER% -p%DB_PASSWORD% -e "CREATE DATABASE IF NOT EXISTS %DB_NAME%;"

if %ERRORLEVEL% NEQ 0 (
    echo Error creating database!
    pause
    exit /b 1
)

echo Database created successfully!
echo.
echo Running SQL script...
"%MYSQL_PATH%\mysql.exe" -u %DB_USER% -p%DB_PASSWORD% %DB_NAME% < onlineStore.sql

if %ERRORLEVEL% NEQ 0 (
    echo Error running SQL script!
    pause
    exit /b 1
)

echo.
echo Database setup completed successfully!
echo You can now start the Spring Boot application.
pause

