@echo off

for %%? in ("%~dp0.") do set PARENT=%%~n?

call "%~dp0\..\sbt.bat" "project %PARENT%" %*
