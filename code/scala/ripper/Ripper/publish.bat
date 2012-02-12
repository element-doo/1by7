@echo off

echo Will upload the classes, source and documentation to the ivy/m2 server
call "%~dp0sbt.bat" %* publish
