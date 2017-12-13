@echo off
REM Licensed Material - Property of IBM 
REM (C) Copyright IBM Corp. 2002, 2003 - All Rights Reserved. 
REM US Government Users Restricted Rights - Use, duplication or disclosure 
REM restricted by GSA ADP Schedule Contract with IBM Corp. 
REM 
REM DISCLAIMER OF WARRANTIES.
REM The following [enclosed] code is sample code created by IBM
REM Corporation. This sample code is not part of any standard or IBM
REM product and is provided to you solely for the purpose of assisting
REM you in the development of your applications.  The code is provided
REM "AS IS". IBM MAKES NO WARRANTIES, EXPRESS OR IMPLIED, INCLUDING BUT
REM NOT LIMITED TO THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
REM FOR A PARTICULAR PURPOSE, REGARDING THE FUNCTION OR PERFORMANCE OF
REM THIS CODE.  THIS CODE MAY CONTAIN ERRORS.  IBM shall not be liable
REM for any damages arising out of your use of the sample code, even
REM if it has been advised of the possibility of such damages.
REM 
REM This batch file fires off the property file parse or comparison.
REM
setlocal

if "%JAVA_HOME%" EQU "" goto NO_JAVA

set CLASSPATH=%CLASSPATH%;..\com.ibm.etools.validation.core_5.1.0\runtime\common.jar
set CLASSPATH=%CLASSPATH%;.\runtime\propertiesValidator.jar
if "%1" == "" goto syntax

%JAVA_HOME%\bin\java com.ibm.etools.parser.Main %1 %2 %3
goto :end

:NO_JAVA
echo.
echo The JAVA_HOME environment variable must be set to the JRE directory.
echo e.g., set JAVA_HOME=C:\JRE
echo where JRE has a subdirectory named "bin"
echo.
goto :end

:syntax
%JAVA_HOME%\bin\java com.ibm.etools.parser.Main

:end
endlocal
@echo on