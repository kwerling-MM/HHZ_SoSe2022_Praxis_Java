@echo off
del .\"HHZ Java 20220415.pptx"

if "%1"=="" (
        echo Missing checkin comment
        rem exit 1
) ELSE (
rem echo %1
rem goto end
git add --all
git commit -a -m "%*"
git push
:end
echo Done
)
