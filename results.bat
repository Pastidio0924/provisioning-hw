@echo off
echo Testing Voxloud Provisioning API...

echo.
echo Test 1: Desk Device with Override
curl http://localhost:8080/api/v1/provisioning/aa-bb-cc-11-22-33

echo.
echo Test 2: Conference Device with Override
curl http://localhost:8080/api/v1/provisioning/dd-ee-ff-44-55-66

echo.
echo Test 3: Desk Device without Override
curl http://localhost:8080/api/v1/provisioning/11-22-33-aa-bb-cc

echo.
echo Test 4: Conference Device without Override
curl http://localhost:8080/api/v1/provisioning/44-55-66-dd-ee-ff

echo.
echo Test 5: Non-existent Device
curl http://localhost:8080/api/v1/provisioning/00-00-00-00-00-00

echo.
echo Tests completed!
pause 