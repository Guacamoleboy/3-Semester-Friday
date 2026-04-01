@echo off

cls
echo ## ------------------------------------------ ##
echo      Starting MoodMap Docker Containers   
echo ## ------------------------------------------ ##
echo.

docker compose start db pgadmin

pause